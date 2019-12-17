package com.example.milktea.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.example.common.util.GenericMap;
import com.example.common.vo.JSONResultVO;
import com.example.common.util.AESUtils;
import com.example.common.util.JsonUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.milktea.mapper.AdminDOMapper;
import com.example.milktea.pojo.AdminDO;
import com.example.milktea.pojo.AdminDOExample;
import com.example.milktea.pojo.AdminDOExample.Criteria;
import com.example.milktea.service.AdminService;
import com.example.common.dto.SearchDTO;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDOMapper adminMapper;

    @Value("${admin.key:aaaabbbbccccdddd}")
    private String key;
    @Value("${admin.iv:1234567887654321}")
    private String iv;

    @Override
    @Transactional(readOnly = true)
    public PageInfo<AdminDO> pageList(SearchDTO<AdminDO> query) {
        AdminDOExample example = new AdminDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<AdminDO> list = adminMapper.selectByExample(example);
        PageInfo<AdminDO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public AdminDO get(Long id) {
        checkNotNull(id, "param id is null");
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int add(AdminDO record) {
        return adminMapper.insertSelective(record);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        checkNotNull(id, "param id is null");
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int update(AdminDO record) {
        checkNotNull(record.getId(), "record's id is null");
        return adminMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateCAS(AdminDO record) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdminDO> listBy(AdminDO query) {
        AdminDOExample example = new AdminDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        return adminMapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminDO getBy(AdminDO query) {
        AdminDOExample example = new AdminDOExample();
        Criteria criteria = example.createCriteria();
        if (query.getId() != null) {
            criteria.andIdEqualTo(query.getId());
        }
        if (query.getTel() != null) {
            criteria.andTelEqualTo(query.getTel());
        }
        List<AdminDO> result = adminMapper.selectByExample(example);
        checkState(result.size() < 2, "multy result by query");
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO login(AdminDO record) {
        checkNotNull(record.getName(), "param name is null");
        checkNotNull(record.getPassword(), "param password is null");
        List<AdminDO> result = adminMapper.login(record);
        checkState(result.size() < 2, "multy result by query");
        if (result.isEmpty()) {
            return JSONResultVO.builder().data(null).code(-1).msg("没有此登录信息，检查后重试").build();
        }

        AdminDO adminDO = result.get(0);
        adminDO.setToken(AESUtils.encryptByCS7(
                JsonUtils.objectToJson(
                        new GenericMap.Builder<String, String>()
                                .put("id", adminDO.getId().toString())
                                .put("tel", adminDO.getTel())
                                .put("stamp", Instant.now()
                                        .plusMillis(TimeUnit.HOURS.toMillis(8))
                                        .getEpochSecond() + "")
                                .builder().map()),
                key,
                iv));

        return JSONResultVO.builder()
                .code(1)
                .data(adminDO)
                .msg("登录成功").build();
    }

    @Override
    public JSONResultVO info(String token) {
        checkNotNull(token, "token is null");
        Map<String, String> data = JsonUtils.jsonToMap(AESUtils.decryptByCS7(token, key, iv));

        if (data == null) {
            return JSONResultVO.builder().data(null).code(-1).msg("令牌校验失败").build();
        }

        long millis = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)).getEpochSecond()
                - Long.parseLong(data.get("stamp"));

        // Token过期时间为一天
        if (millis > 86400) {
            return JSONResultVO.builder().data(null).code(50014).msg("令牌已过期，请重新登录").build();
        }

        AdminDO info = ((AdminService) AopContext.currentProxy()).getBy(
                AdminDO.builder()
                        .id(Long.parseLong(data.get("id").trim()))
                        .tel(data.get("tel").trim()).build());

        info.setToken(token);

        return JSONResultVO.builder()
                .data(info)
                .code(1)
                .msg("获取管理员账户信息成功").build();
    }

    @Override
    public void logout(String token) {
        // 注销账户，暂时没想好怎么写，写搞个空接口
    }
}

