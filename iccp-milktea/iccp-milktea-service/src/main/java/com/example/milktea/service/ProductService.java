package com.example.milktea.service;

import com.example.common.service.AbstractService;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.pojo.ProductDO;

public interface ProductService extends AbstractService<ProductDO>{
    /**
     * 查询指定数量的列表
     */
    JSONResultVO listBySize(Integer size);
}