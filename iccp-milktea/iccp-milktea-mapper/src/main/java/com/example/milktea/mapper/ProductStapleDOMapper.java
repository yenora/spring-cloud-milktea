package com.example.milktea.mapper;

import com.example.milktea.pojo.ProductStapleDO;
import com.example.milktea.pojo.ProductStapleDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStapleDOMapper {
    int countByExample(ProductStapleDOExample example);

    int deleteByExample(ProductStapleDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductStapleDO record);

    int insertSelective(ProductStapleDO record);

    List<ProductStapleDO> selectByExample(ProductStapleDOExample example);

    ProductStapleDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductStapleDO record, @Param("example") ProductStapleDOExample example);

    int updateByExample(@Param("record") ProductStapleDO record, @Param("example") ProductStapleDOExample example);

    int updateByPrimaryKeySelective(ProductStapleDO record);

    int updateByPrimaryKey(ProductStapleDO record);
}
