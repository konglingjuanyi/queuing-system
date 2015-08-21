package com.qunar.ops.recruit.dao;

import com.qunar.ops.recruit.model.Phase;
import com.qunar.ops.recruit.model.PhaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhaseMapper {
    int countByExample(PhaseExample example);

    int deleteByExample(PhaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Phase record);

    int insertSelective(Phase record);

    List<Phase> selectByExample(PhaseExample example);

    Phase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Phase record, @Param("example") PhaseExample example);

    int updateByExample(@Param("record") Phase record, @Param("example") PhaseExample example);

    int updateByPrimaryKeySelective(Phase record);

    int updateByPrimaryKey(Phase record);
}