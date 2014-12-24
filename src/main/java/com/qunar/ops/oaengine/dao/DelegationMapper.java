package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Delegation;
import com.qunar.ops.oaengine.model.DelegationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DelegationMapper {
    int countByExample(DelegationExample example);

    int deleteByExample(DelegationExample example);

    int insert(Delegation record);

    int insertSelective(Delegation record);

    List<Delegation> selectByExample(DelegationExample example);

    int updateByExampleSelective(@Param("record") Delegation record, @Param("example") DelegationExample example);

    int updateByExample(@Param("record") Delegation record, @Param("example") DelegationExample example);
}