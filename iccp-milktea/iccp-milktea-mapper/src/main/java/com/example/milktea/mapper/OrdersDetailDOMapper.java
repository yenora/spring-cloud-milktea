package com.example.milktea.mapper;

import com.example.milktea.pojo.OrdersDetailDO;
import com.example.milktea.pojo.OrdersDetailDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdersDetailDOMapper {
    int countByExample(OrdersDetailDOExample example);

    int deleteByExample(OrdersDetailDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrdersDetailDO record);

    int insertSelective(OrdersDetailDO record);

    List<OrdersDetailDO> selectByExample(OrdersDetailDOExample example);

    OrdersDetailDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrdersDetailDO record, @Param("example") OrdersDetailDOExample example);

    int updateByExample(@Param("record") OrdersDetailDO record, @Param("example") OrdersDetailDOExample example);

    int updateByPrimaryKeySelective(OrdersDetailDO record);

    int updateByPrimaryKey(OrdersDetailDO record);
}