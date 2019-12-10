package com.example.milktea.mapper;

import com.example.milktea.pojo.ProductTypeDO;
import com.example.milktea.pojo.ProductTypeDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeDOMapper {
    int countByExample(ProductTypeDOExample example);

    int deleteByExample(ProductTypeDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductTypeDO record);

    int insertSelective(ProductTypeDO record);

    List<ProductTypeDO> selectByExampleWithBLOBs(ProductTypeDOExample example);

    List<ProductTypeDO> selectByExample(ProductTypeDOExample example);

    ProductTypeDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductTypeDO record, @Param("example") ProductTypeDOExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductTypeDO record, @Param("example") ProductTypeDOExample example);

    int updateByExample(@Param("record") ProductTypeDO record, @Param("example") ProductTypeDOExample example);

    int updateByPrimaryKeySelective(ProductTypeDO record);

    int updateByPrimaryKeyWithBLOBs(ProductTypeDO record);

    int updateByPrimaryKey(ProductTypeDO record);
}