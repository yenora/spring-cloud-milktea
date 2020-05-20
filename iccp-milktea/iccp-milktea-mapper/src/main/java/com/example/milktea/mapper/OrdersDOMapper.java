package com.example.milktea.mapper;

import com.example.milktea.pojo.OrdersDO;
import com.example.milktea.pojo.OrdersDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdersDOMapper {
    int countByExample(OrdersDOExample example);

    int deleteByExample(OrdersDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrdersDO record);

    int insertSelective(OrdersDO record);

    List<OrdersDO> selectByExample(OrdersDOExample example);

    OrdersDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrdersDO record, @Param("example") OrdersDOExample example);

    int updateByExample(@Param("record") OrdersDO record, @Param("example") OrdersDOExample example);

    int updateByPrimaryKeySelective(OrdersDO record);

    int updateByPrimaryKey(OrdersDO record);
}