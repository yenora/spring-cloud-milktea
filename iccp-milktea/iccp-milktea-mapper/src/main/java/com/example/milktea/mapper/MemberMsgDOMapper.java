package com.example.milktea.mapper;

import com.example.milktea.pojo.MemberMsgDO;
import com.example.milktea.pojo.MemberMsgDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberMsgDOMapper {
    int countByExample(MemberMsgDOExample example);

    int deleteByExample(MemberMsgDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberMsgDO record);

    int insertSelective(MemberMsgDO record);

    List<MemberMsgDO> selectByExampleWithBLOBs(MemberMsgDOExample example);

    List<MemberMsgDO> selectByExample(MemberMsgDOExample example);

    MemberMsgDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberMsgDO record, @Param("example") MemberMsgDOExample example);

    int updateByExampleWithBLOBs(@Param("record") MemberMsgDO record, @Param("example") MemberMsgDOExample example);

    int updateByExample(@Param("record") MemberMsgDO record, @Param("example") MemberMsgDOExample example);

    int updateByPrimaryKeySelective(MemberMsgDO record);

    int updateByPrimaryKeyWithBLOBs(MemberMsgDO record);

    int updateByPrimaryKey(MemberMsgDO record);
}