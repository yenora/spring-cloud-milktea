package service;

import com.github.pagehelper.PageInfo;
import dto.SearchDTO;

/**
 * @Description:
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
