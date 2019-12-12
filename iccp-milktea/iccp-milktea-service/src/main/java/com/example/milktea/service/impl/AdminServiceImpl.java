package com.example.milktea.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import java.util.List;
import com.example.common.util.GenericMap;
import com.example.common.vo.JSONResultVO;
import com.jandar.common.util.AESUtils;
import com.jandar.common.util.JsonUtils;
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
        PageHelper.startPage(query.getPage(), query.getRows());
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
        //TODO edit your query condition
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
            return JSONResultVO.builder().data(null).code(-1).msg("failed").build();
        }

        AdminDO adminDO = result.get(0);

        String token = AESUtils.encryptByCS7(
                JsonUtils.objectToJson(
                        new GenericMap.Builder<String, String>()
                                .put("id", adminDO.toString())
                                .put("tel", adminDO.getTel())
                                .put("stamp", System.currentTimeMillis() + "")
                                .builder().map()),
                key,
                iv);

        adminDO.setToken(token);

        return JSONResultVO.builder()
                .code(1)
                .data(adminDO)
                .msg("success").build();
    }
}

