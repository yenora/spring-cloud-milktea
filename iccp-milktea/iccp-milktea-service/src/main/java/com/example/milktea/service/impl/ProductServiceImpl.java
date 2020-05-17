package com.example.milktea.service.impl;

import static com.example.common.vo.JSONResultVO.CODE_SUCCESS;
import static com.example.common.vo.JSONResultVO.CODE_ERROR;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.common.util.PageResult;
import com.example.common.util.TypeChangeUtils;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.mapper.ProductStapleDOMapper;
import com.example.milktea.mapper.ProductTypeDOMapper;
import com.example.milktea.pojo.*;
import org.apache.commons.lang3.StringUtils;
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

    private static final String SPLIT = ",";

    @Autowired
    private ProductDOMapper productMapper;

    @Autowired
    private ProductStapleDOMapper productStapleDOMapper;

    @Autowired
    private ProductTypeDOMapper productTypeDOMapper;

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO pageList(SearchDTO<ProductDO> query) {
        ProductDOExample proExample = new ProductDOExample();
        Criteria criteria = proExample.createCriteria();
        if (StringUtils.isNotBlank(query.getEntity().getName())) {
            criteria.andNameLike(LIKE + query.getEntity().getName() + LIKE);
        }
        if (query.getEntity().getTypeId() != null) {
            criteria.andTypeIdEqualTo(query.getEntity().getTypeId());
        }
        if (query.getEntity().getRecommend() != null) {
            criteria.andRecommendEqualTo(query.getEntity().getRecommend());
        }
        PageHelper.startPage(query.getPage(), query.getLimit(), query.getSort());
        List<ProductDO> prolist = productMapper.selectByExampleWithBLOBs(proExample);
        return JSONResultVO.builder()
                .data(PageResult.build(new PageInfo<>(this.genProductVOList(prolist))))
                .code(CODE_SUCCESS)
                .message("产品信息分页列表查询成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO get(Long id) {
        checkNotNull(id, "param id is null");
        return JSONResultVO.builder()
                .data(productMapper.selectByPrimaryKey(id))
                .code(CODE_SUCCESS)
                .message("产品信息详情查询成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO add(ProductDO record) {
        if (record.getPrice() == null) {
            record.setPrice(new BigDecimal(0));
        }
        if (record.getRecommend() == null) {
            record.setRecommend(1);
        }
        record.setCreateTime(LocalDateTime.now());
        record.setSales(0L);
        StringBuilder stapleBuilder = new StringBuilder();
        for (String str : TypeChangeUtils.longToString(record.getStapleId())) {
            stapleBuilder.append(str).append(SPLIT);
        }
        record.setStapleIds(stapleBuilder.deleteCharAt(stapleBuilder.length() - 1).toString());
        productMapper.insertSelective(record);
        return JSONResultVO.builder()
                .code(CODE_SUCCESS)
                .message("产品信息添加成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO delete(Long id) {
        checkNotNull(id, "param id is null");
        productMapper.deleteByPrimaryKey(id);
        return JSONResultVO.builder()
                .code(CODE_SUCCESS)
                .message("产品信息删除成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO update(ProductDO record) {
        checkNotNull(record.getId(), "record's id is null");
        productMapper.updateByPrimaryKeySelective(record);
        return JSONResultVO.builder()
                .code(CODE_SUCCESS)
                .message("产品信息修改成功").build();
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
                .data(productMapper.selectByExampleWithBLOBs(example))
                .code(CODE_SUCCESS)
                .message("产品信息列表查询成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO getBy(ProductDO query) {
        ProductDOExample example = new ProductDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        List<ProductDO> result = productMapper.selectByExampleWithBLOBs(example);
        checkState(result.size() < 2, "multy result by query");
        if (result.isEmpty()) {
            return JSONResultVO.builder()
                    .code(CODE_ERROR)
                    .message("产品信息列表查询失败").build();
        }
        return JSONResultVO.builder()
                .data(result.get(0))
                .code(CODE_SUCCESS)
                .message("产品信息列表查询成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO listBySize(Integer size) {
        List<ProductDO> prolist = productMapper.selectBySizeWithBLOBs(size);
        return JSONResultVO.builder()
                .data(this.genProductVOList(prolist))
                .code(CODE_SUCCESS)
                .message("产品信息列表查询成功").build();
    }

    private List<ProductDO> genProductVOList(List<ProductDO> prolist) {
        List<ProductStapleDO> staplelist = productStapleDOMapper.selectByExample(new ProductStapleDOExample());
        List<ProductTypeDO> typelist = productTypeDOMapper.selectByExample(new ProductTypeDOExample());

        Map<Long, String> type = typelist.stream().collect(Collectors.toMap(ProductTypeDO::getId, ProductTypeDO::getName));
        Map<Long, String> staple = staplelist.stream().collect(Collectors.toMap(ProductStapleDO::getId, ProductStapleDO::getName));

        StringBuilder stapleBuilder = new StringBuilder();

        prolist.forEach(product -> {
            product.setType(type.get(product.getTypeId()));
            if (StringUtils.isNotBlank(product.getStapleIds())) {
                String[] staples = product.getStapleIds().split(SPLIT);
                for (String id : staples) {
                    stapleBuilder.append(staple.get(Long.parseLong(id))).append(SPLIT);
                }
                product.setStaple(stapleBuilder.deleteCharAt(stapleBuilder.length() - 1).toString());
                product.setStapleId(TypeChangeUtils.StringArray2LongArray(staples));
            }
        });
        return prolist;
    }
}

