package com.qunar.ops.recruit.dao;

import com.qunar.ops.recruit.model.PhaseInterviewer;
import com.qunar.ops.recruit.model.PhaseInterviewerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhaseInterviewerMapper {
    int countByExample(PhaseInterviewerExample example);

    int deleteByExample(PhaseInterviewerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhaseInterviewer record);

    int insertSelective(PhaseInterviewer record);

    List<PhaseInterviewer> selectByExample(PhaseInterviewerExample example);

    PhaseInterviewer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhaseInterviewer record, @Param("example") PhaseInterviewerExample example);

    int updateByExample(@Param("record") PhaseInterviewer record, @Param("example") PhaseInterviewerExample example);

    int updateByPrimaryKeySelective(PhaseInterviewer record);

    int updateByPrimaryKey(PhaseInterviewer record);
}