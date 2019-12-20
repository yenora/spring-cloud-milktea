package com.example.milktea.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public ProductStapleDO get(Long id) {
        checkNotNull(id, "param id is null");
        return productStapleMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int add(ProductStapleDO record) {
        return productStapleMapper.insertSelective(record);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        checkNotNull(id, "param id is null");
        return productStapleMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int update(ProductStapleDO record) {
        checkNotNull(record.getId(), "record's id is null");
        return productStapleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateCAS(ProductStapleDO record) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductStapleDO> listBy(ProductStapleDO query) {
        ProductStapleDOExample example = new ProductStapleDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        return productStapleMapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductStapleDO getBy(ProductStapleDO query) {
        ProductStapleDOExample example = new ProductStapleDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        List<ProductStapleDO> result = productStapleMapper.selectByExample(example);
        checkState(result.size() < 2, "multy result by query");
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}

