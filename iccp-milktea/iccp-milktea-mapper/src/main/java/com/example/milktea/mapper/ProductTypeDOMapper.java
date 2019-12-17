package com.example.milktea.mapper;

import com.example.milktea.pojo.ProductTypeDO;
import com.example.milktea.pojo.ProductTypeDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeDOMapper {
    int countByExample(ProductTypeDOExample example);

    int deleteByExample(ProductTypeDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductTypeDO record);

    int insertSelective(ProductTypeDO record);

    List<ProductTypeDO> selectByExample(ProductTypeDOExample example);

    ProductTypeDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductTypeDO record, @Param("example") ProductTypeDOExample example);

    int updateByExample(@Param("record") ProductTypeDO record, @Param("example") ProductTypeDOExample example);

    int updateByPrimaryKeySelective(ProductTypeDO record);

    int updateByPrimaryKey(ProductTypeDO record);
}
