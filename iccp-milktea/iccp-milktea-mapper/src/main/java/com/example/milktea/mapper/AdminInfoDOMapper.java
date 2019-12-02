package com.example.milktea.mapper;

import com.example.milktea.pojo.AdminInfoDO;
import com.example.milktea.pojo.AdminInfoDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminInfoDOMapper {
    int countByExample(AdminInfoDOExample example);

    int deleteByExample(AdminInfoDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminInfoDO record);

    int insertSelective(AdminInfoDO record);

    List<AdminInfoDO> selectByExample(AdminInfoDOExample example);

    AdminInfoDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminInfoDO record, @Param("example") AdminInfoDOExample example);

    int updateByExample(@Param("record") AdminInfoDO record, @Param("example") AdminInfoDOExample example);

    int updateByPrimaryKeySelective(AdminInfoDO record);

    int updateByPrimaryKey(AdminInfoDO record);

    int updateByPrimaryKeySelectiveCAS(AdminInfoDO record);
}