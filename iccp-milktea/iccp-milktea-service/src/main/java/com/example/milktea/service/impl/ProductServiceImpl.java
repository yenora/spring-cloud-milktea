package com.example.milktea.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.List;

import com.example.common.vo.JSONResultVO;
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
    public JSONResultVO get(Long id) {
        checkNotNull(id, "param id is null");
        return JSONResultVO.builder()
                .data(productMapper.selectByPrimaryKey(id))
                .code(1)
                .msg("产品信息详情查询成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO add(ProductDO record) {
        return JSONResultVO.builder()
                .data(productMapper.insertSelective(record))
                .code(1)
                .msg("产品信息添加成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO delete(Long id) {
        checkNotNull(id, "param id is null");
        return JSONResultVO.builder()
                .data(productMapper.deleteByPrimaryKey(id))
                .code(1)
                .msg("产品信息删除成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO update(ProductDO record) {
        checkNotNull(record.getId(), "record's id is null");
        return JSONResultVO.builder()
                .data(productMapper.updateByPrimaryKeySelective(record))
                .code(1)
                .msg("产品信息修改成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO updateCAS(ProductDO record) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO listBy(ProductDO query) {
        ProductDOExample example = new ProductDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        return JSONResultVO.builder()
                .data(productMapper.selectByExample(example))
                .code(1)
                .msg("产品信息列表查询成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO getBy(ProductDO query) {
        ProductDOExample example = new ProductDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        List<ProductDO> result = productMapper.selectByExample(example);
        checkState(result.size() < 2, "multy result by query");
        if (result.isEmpty()) {
            return JSONResultVO.builder()
                    .data(null)
                    .code(-1)
                    .msg("产品信息列表查询失败").build();
        }
        return JSONResultVO.builder()
                .data(result.get(0))
                .code(1)
                .msg("产品信息列表查询成功").build();
    }
}

