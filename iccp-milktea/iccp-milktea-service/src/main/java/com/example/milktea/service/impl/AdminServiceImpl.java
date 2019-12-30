package com.example.milktea.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.time.Instant;
import java.time.LocalDateTime;
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

    @Value("${token.key:DKfBOcdEoRMxcPOi}")
    private String key;
    @Value("${token.iv:2019120120200630}")
    private String iv;
    @Value("${registry.avatar:http://127.0.0.1:5000/spring-cloud-milktea/default/avatar.gif}")
    private String avatar;

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
    public JSONResultVO get(Long id) {
        checkNotNull(id, "param id is null");
        return JSONResultVO.builder()
                .data(adminMapper.selectByPrimaryKey(id))
                .code(1)
                .msg("管理员信息详情查询成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO add(AdminDO record) {
        if (record.getCreateTime() == null) {
            record.setCreateTime(LocalDateTime.now());
        }
        if (record.getAvatar() == null) {
            record.setAvatar(avatar);
        }
        return JSONResultVO.builder()
                .data(adminMapper.insertSelective(record))
                .code(1)
                .msg("管理员信息添加成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO delete(Long id) {
        checkNotNull(id, "param id is null");
        return JSONResultVO.builder()
                .data(adminMapper.deleteByPrimaryKey(id))
                .code(1)
                .msg("管理员信息删除成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO update(AdminDO record) {
        checkNotNull(record.getId(), "record's id is null");
        if (adminMapper.updateByPrimaryKeySelective(record) > 0) {
            record.setToken(generateToken(record));
            return JSONResultVO.builder()
                    .data(record)
                    .code(1)
                    .msg("管理员信息修改成功").build();
        } else {
            return JSONResultVO.builder()
                    .data(record)
                    .code(-1)
                    .msg("管理员信息修改失败").build();
        }
    }

    @Override
    @Transactional
    public JSONResultVO updateCAS(AdminDO record) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO listBy(AdminDO query) {
        AdminDOExample example = new AdminDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        return JSONResultVO.builder()
                .data(adminMapper.selectByExample(example))
                .code(1)
                .msg("管理员信息列表查询成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO getBy(AdminDO query) {
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
            JSONResultVO.builder()
                    .data(null)
                    .code(-1)
                    .msg("管理员信息列表查询不到此记录").build();
        }
        return JSONResultVO.builder()
                .data(result.get(0))
                .code(1)
                .msg("管理员信息列表查询成功").build();
    }

    @Override
    public JSONResultVO getNameArray(String key) {
        if (this.key.equals(key)) {
            return JSONResultVO.builder()
                    .data(adminMapper.selectAdminNameList().toArray())
                    .code(1)
                    .msg("获取管理员账号列表成功").build();
        } else {
            return JSONResultVO.builder()
                    .data("")
                    .code(-1)
                    .msg("秘钥错误，获取管理员账号列表失败").build();
        }
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
        adminDO.setToken(generateToken(adminDO));

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

        return ((AdminService) AopContext.currentProxy()).getBy(
                AdminDO.builder()
                        .id(Long.parseLong(data.get("id").trim()))
                        .tel(data.get("tel").trim()).build());
    }

    @Override
    public void logout(String token) {
        // 注销账户，暂时没想好怎么写，写搞个空接口
    }

    public String generateToken(AdminDO record) {
        return AESUtils.encryptByCS7(
                JsonUtils.objectToJson(
                        new GenericMap.Builder<String, String>()
                                .put("id", record.getId().toString())
                                .put("tel", record.getTel())
                                .put("stamp", Instant.now()
                                        .plusMillis(TimeUnit.HOURS.toMillis(8))
                                        .getEpochSecond() + "")
                                .builder().map()),
                key,
                iv);
    }
}

