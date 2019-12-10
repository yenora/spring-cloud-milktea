package com.example.milktea.mapper;

import com.example.milktea.pojo.MemberDO;
import com.example.milktea.pojo.MemberDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberDOMapper {
    int countByExample(MemberDOExample example);

    int deleteByExample(MemberDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberDO record);

    int insertSelective(MemberDO record);

    List<MemberDO> selectByExampleWithBLOBs(MemberDOExample example);

    List<MemberDO> selectByExample(MemberDOExample example);

    MemberDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberDO record, @Param("example") MemberDOExample example);

    int updateByExampleWithBLOBs(@Param("record") MemberDO record, @Param("example") MemberDOExample example);

    int updateByExample(@Param("record") MemberDO record, @Param("example") MemberDOExample example);

    int updateByPrimaryKeySelective(MemberDO record);

    int updateByPrimaryKeyWithBLOBs(MemberDO record);

    int updateByPrimaryKey(MemberDO record);
}