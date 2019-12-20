package com.example.milktea.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.List;

import com.example.milktea.dto.ProductStapleDTO;
import com.example.milktea.dto.ProductTypeDTO;
import com.example.milktea.mapper.ProductStapleDOMapper;
import com.example.milktea.mapper.ProductTypeDOMapper;
import com.example.milktea.pojo.*;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.milktea.mapper.ProductDOMapper;
import com.example.milktea.pojo.ProductDOExample.Criteria;
import com.example.milktea.service.ProductService;
import com.example.common.dto.SearchDTO;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDOMapper productMapper;

    @Autowired
    private ProductStapleDOMapper productStapleDOMapper;

    @Autowired
    private ProductTypeDOMapper productTypeDOMapper;

    @Override
    @Transactional(readOnly = true)
    public PageInfo<ProductDO> pageList(SearchDTO<ProductDO> query) {
        ProductDOExample proExample = new ProductDOExample();
        ProductStapleDOExample stapleExample = new ProductStapleDOExample();
        ProductTypeDOExample typeExample = new ProductTypeDOExample();
        Criteria criteria = proExample.createCriteria();
        if (query.getEntity().getName() != null) {
            criteria.andNameLike(LIKE + query.getEntity().getName() + LIKE);
        }
        if (query.getEntity().getTypeId() != null) {
            criteria.andTypeIdEqualTo(query.getEntity().getTypeId());
        }
        if (query.getEntity().getRecommend() != null) {
            criteria.andRecommendEqualTo(query.getEntity().getRecommend());
        }
        PageHelper.startPage(query.getPage(), query.getLimit(), query.getSort());
        List<ProductDO> prolist = productMapper.selectByExample(proExample);
        List<ProductStapleDO> staplelist = productStapleDOMapper.selectByExample(stapleExample);
        List<ProductTypeDO> typelist = productTypeDOMapper.selectByExample(typeExample);

        for (ProductDO productDO : prolist) {
            for (ProductTypeDO typeDO : typelist) {
                if (productDO.getTypeId().equals(typeDO.getId())) {
                    productDO.setProductTypeDTO(new ProductTypeDTO(typeDO.getId(), typeDO.getName()));
                }
            }

            List<Long> ids = new ArrayList<>();
            List<String> names = new ArrayList<>();
            for (ProductStapleDO stapleDO : staplelist) {
                Long[] staples = (Long[]) ConvertUtils.convert(productDO.getStaples().split(","), Long.class);
                for (Long staple : staples) {
                    if (staple.equals(stapleDO.getId())) {
                        ids.add(stapleDO.getId());
                        names.add(stapleDO.getName());
                    }
                }
            }
            productDO.setProductStapleDTO(new ProductStapleDTO(ids.toArray(new Long[ids.size()]), names.toArray(new String[names.size()])));
        }
        return new PageInfo<>(prolist);
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

