package com.example.common.service;

import com.example.common.vo.JSONResultVO;
import com.example.common.dto.SearchDTO;

/**
 * @Description: 统一的抽象方法接口，所有的服务类都要实现这个接口
 * @Author: yaos
 * @Date: 2019-11-29 09:50
 * @Version: V1.0
 **/
public interface AbstractService<T> {
    String LIKE = "%";

    JSONResultVO pageList(SearchDTO<T> searchDTO);

    JSONResultVO get(Long aLong);

    JSONResultVO add(T t);

    JSONResultVO update(T t);

    JSONResultVO updateCAS(T t);

    JSONResultVO delete(Long aLong);

    JSONResultVO listBy(T t);

    JSONResultVO getBy(T t);
}
