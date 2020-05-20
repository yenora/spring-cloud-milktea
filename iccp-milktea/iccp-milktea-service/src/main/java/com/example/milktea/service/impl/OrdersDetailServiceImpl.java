package com.example.milktea.service.impl;

import com.example.common.dto.SearchDTO;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.mapper.OrdersDetailDOMapper;
import com.example.milktea.pojo.*;
import com.example.milktea.pojo.OrdersDetailDOExample.Criteria;
import com.example.milktea.service.OrdersDetailService;
import com.example.milktea.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.example.common.vo.JSONResultVO.CODE_SUCCESS;

@Service
public class OrdersDetailServiceImpl implements OrdersDetailService {

    @Autowired
    private OrdersDetailDOMapper ordersDetailDOMapper;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO pageList(SearchDTO<OrdersDetailDO> query) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO get(Long id) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional
    public JSONResultVO delete(Long id) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional
    public JSONResultVO update(OrdersDetailDO record) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional
    public JSONResultVO updateCAS(OrdersDetailDO record) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO getBy(OrdersDetailDO query) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional
    public JSONResultVO add(OrdersDetailDO record) {
        ordersDetailDOMapper.insertSelective(record);
        return JSONResultVO.builder()
                .code(CODE_SUCCESS)
                .message("订单详情信息添加成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO listBy(OrdersDetailDO query) {
        OrdersDetailDOExample example = new OrdersDetailDOExample();
        Criteria criteria = example.createCriteria();
        if (query.getOrderId() != null) {
            criteria.andOrderIdEqualTo(query.getOrderId());
        }
        if (query.getProductId() != null) {
            criteria.andProductIdEqualTo(query.getProductId());
        }
        return JSONResultVO.builder()
                .data(genOrderDetailVOList(ordersDetailDOMapper.selectByExample(example)))
                .code(CODE_SUCCESS)
                .message("订单详情信息列表查询成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO deleteBy(OrdersDetailDO query) {
        OrdersDetailDOExample example = new OrdersDetailDOExample();
        Criteria criteria = example.createCriteria();
        if (query.getOrderId() != null) {
            criteria.andOrderIdEqualTo(query.getOrderId());
        }
        if (query.getProductId() != null) {
            criteria.andProductIdEqualTo(query.getProductId());
        }
        ordersDetailDOMapper.deleteByExample(example);
        return JSONResultVO.builder()
                .code(CODE_SUCCESS)
                .message("订单详情信息删除成功").build();
    }

    private List<OrdersDetailDO> genOrderDetailVOList(List<OrdersDetailDO> orderDetailList) {
        orderDetailList.forEach(orderDetail -> {
            ProductDO product = (ProductDO) productService.get(orderDetail.getProductId()).getData();
            if (Objects.nonNull(product)) {
                orderDetail.setProduct(product);
            }
        });
        return orderDetailList;
    }
}

