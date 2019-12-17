package com.example.milktea.service;

import com.example.common.service.AbstractService;
import com.example.milktea.pojo.ProductStapleDO;

public interface ProductStapleService extends AbstractService<ProductStapleDO>{
    /**
     * 根据id查询原料名称
     *
     * @param ids id
     * @return 名字拼接
     */
    String getByIds(String ids);
}