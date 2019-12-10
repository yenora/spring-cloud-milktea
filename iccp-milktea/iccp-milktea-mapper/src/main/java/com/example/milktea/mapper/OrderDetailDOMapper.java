package com.example.milktea.mapper;

import com.example.milktea.pojo.OrderDetailDO;
import com.example.milktea.pojo.OrderDetailDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailDOMapper {
    int countByExample(OrderDetailDOExample example);

    int deleteByExample(OrderDetailDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderDetailDO record);

    int insertSelective(OrderDetailDO record);

    List<OrderDetailDO> selectByExample(OrderDetailDOExample example);

    OrderDetailDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderDetailDO record, @Param("example") OrderDetailDOExample example);

    int updateByExample(@Param("record") OrderDetailDO record, @Param("example") OrderDetailDOExample example);

    int updateByPrimaryKeySelective(OrderDetailDO record);

    int updateByPrimaryKey(OrderDetailDO record);
}