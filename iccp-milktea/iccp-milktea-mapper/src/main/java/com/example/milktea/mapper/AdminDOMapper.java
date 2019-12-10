package com.example.milktea.mapper;

import com.example.milktea.pojo.AdminDO;
import com.example.milktea.pojo.AdminDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDOMapper {
    int countByExample(AdminDOExample example);

    int deleteByExample(AdminDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminDO record);

    int insertSelective(AdminDO record);

    List<AdminDO> selectByExample(AdminDOExample example);

    AdminDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminDO record, @Param("example") AdminDOExample example);

    int updateByExample(@Param("record") AdminDO record, @Param("example") AdminDOExample example);

    int updateByPrimaryKeySelective(AdminDO record);

    int updateByPrimaryKey(AdminDO record);
}