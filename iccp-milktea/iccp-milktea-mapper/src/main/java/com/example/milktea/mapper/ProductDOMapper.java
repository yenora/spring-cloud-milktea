package com.example.milktea.mapper;

import com.example.milktea.pojo.ProductDO;
import com.example.milktea.pojo.ProductDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductDOMapper {
    int countByExample(ProductDOExample example);

    int deleteByExample(ProductDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductDO record);

    int insertSelective(ProductDO record);

    List<ProductDO> selectByExampleWithBLOBs(ProductDOExample example);

    List<ProductDO> selectByExample(ProductDOExample example);

    ProductDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductDO record, @Param("example") ProductDOExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductDO record, @Param("example") ProductDOExample example);

    int updateByExample(@Param("record") ProductDO record, @Param("example") ProductDOExample example);

    int updateByPrimaryKeySelective(ProductDO record);

    int updateByPrimaryKeyWithBLOBs(ProductDO record);

    int updateByPrimaryKey(ProductDO record);
}