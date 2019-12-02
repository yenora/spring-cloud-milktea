package com.example.common.service;

import com.github.pagehelper.PageInfo;
import com.example.common.dto.SearchDTO;

/**
 * @Description: 统一的抽象方法接口，所有的服务类都要实现这个接口
 * @Author: yaos
 * @Date: 2019-11-29 09:50
 * @Version: V1.0
 **/
public interface AbstractService<T> {
    String LIKE = "%";

    T get(java.lang.Long aLong);

    int add(T t);

    int update(T t);

    int updateCAS(T t);

    int delete(java.lang.Long aLong);

    PageInfo<T> pageList(SearchDTO<T> searchDTO);

    java.util.List<T> listBy(T t);

    T getBy(T t);
}
