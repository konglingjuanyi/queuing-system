package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.GroupMember;
import com.qunar.ops.oaengine.model.GroupMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupMemberMapper {
    int countByExample(GroupMemberExample example);

    int deleteByExample(GroupMemberExample example);

    int insert(GroupMember record);

    int insertSelective(GroupMember record);

    List<GroupMember> selectByExample(GroupMemberExample example);

    int updateByExampleSelective(@Param("record") GroupMember record, @Param("example") GroupMemberExample example);

    int updateByExample(@Param("record") GroupMember record, @Param("example") GroupMemberExample example);
}