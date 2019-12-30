package com.example.milktea.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.common.vo.JSONResultVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.milktea.mapper.ProductStapleDOMapper;
import com.example.milktea.pojo.ProductStapleDO;
import com.example.milktea.pojo.ProductStapleDOExample;
import com.example.milktea.pojo.ProductStapleDOExample.Criteria;
import com.example.milktea.service.ProductStapleService;
import com.example.common.dto.SearchDTO;

@Service
public class ProductStapleServiceImpl implements ProductStapleService {

    @Autowired
    private ProductStapleDOMapper productStapleMapper;

    @Override
    @Transactional(readOnly = true)
    public PageInfo<ProductStapleDO> pageList(SearchDTO<ProductStapleDO> query) {
        ProductStapleDOExample example = new ProductStapleDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<ProductStapleDO> list = productStapleMapper.selectByExample(example);
        PageInfo<ProductStapleDO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO get(Long id) {
        checkNotNull(id, "param id is null");
        return JSONResultVO.builder()
                .data(productStapleMapper.selectByPrimaryKey(id))
                .code(1)
                .msg("产品原料信息详情查询成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO add(ProductStapleDO record) {
        return JSONResultVO.builder()
                .data(productStapleMapper.insertSelective(record))
                .code(1)
                .msg("产品原料信息添加成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO delete(Long id) {
        checkNotNull(id, "param id is null");
        return JSONResultVO.builder()
                .data(productStapleMapper.deleteByPrimaryKey(id))
                .code(1)
                .msg("产品原料信息删除成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO update(ProductStapleDO record) {
        checkNotNull(record.getId(), "record's id is null");
        return JSONResultVO.builder()
                .data(productStapleMapper.updateByPrimaryKeySelective(record))
                .code(1)
                .msg("产品原料信息修改成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO updateCAS(ProductStapleDO record) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO listBy(ProductStapleDO query) {
        ProductStapleDOExample example = new ProductStapleDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        return JSONResultVO.builder()
                .data(productStapleMapper.selectByExample(example))
                .code(1)
                .msg("产品原料信息列表查询成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO getBy(ProductStapleDO query) {
        ProductStapleDOExample example = new ProductStapleDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        List<ProductStapleDO> result = productStapleMapper.selectByExample(example);
        checkState(result.size() < 2, "multy result by query");
        if (result.isEmpty()) {
            return JSONResultVO.builder()
                    .data(null)
                    .code(-1)
                    .msg("产品原料信息列表查询失败").build();
        }
        return JSONResultVO.builder()
                .data(result.get(0))
                .code(1)
                .msg("产品原料信息列表查询成功").build();
    }
}

