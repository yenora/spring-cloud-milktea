package com.example.milktea.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.milktea.mapper.ProductDOMapper;
import com.example.milktea.pojo.ProductDO;
import com.example.milktea.pojo.ProductDOExample;
import com.example.milktea.pojo.ProductDOExample.Criteria;
import com.example.milktea.service.ProductService;
import com.example.common.dto.SearchDTO;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDOMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public PageInfo<ProductDO> pageList(SearchDTO<ProductDO> query) {
        ProductDOExample example = new ProductDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        PageHelper.startPage(query.getPage(), query.getLimit(), query.getSort());
        List<ProductDO> list = productMapper.selectByExample(example);
        PageInfo<ProductDO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDO get(Long id) {
        checkNotNull(id, "param id is null");
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int add(ProductDO record) {
        return productMapper.insertSelective(record);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        checkNotNull(id, "param id is null");
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int update(ProductDO record) {
        checkNotNull(record.getId(), "record's id is null");
        return productMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateCAS(ProductDO record) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDO> listBy(ProductDO query) {
        ProductDOExample example = new ProductDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        return productMapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDO getBy(ProductDO query) {
        ProductDOExample example = new ProductDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        List<ProductDO> result = productMapper.selectByExample(example);
        checkState(result.size() < 2, "multy result by query");
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}

