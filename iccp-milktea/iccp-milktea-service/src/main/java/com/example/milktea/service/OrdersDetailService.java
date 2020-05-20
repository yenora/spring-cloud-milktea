package com.example.milktea.service;

import com.example.common.service.AbstractService;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.pojo.OrdersDetailDO;

public interface OrdersDetailService extends AbstractService<OrdersDetailDO> {

    JSONResultVO deleteBy(OrdersDetailDO query);

}