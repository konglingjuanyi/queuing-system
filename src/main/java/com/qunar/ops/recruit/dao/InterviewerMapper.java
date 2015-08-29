package com.qunar.ops.recruit.dao;

import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.model.InterviewerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InterviewerMapper {
    int countByExample(InterviewerExample example);

    int deleteByExample(InterviewerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Interviewer record);

    int insertSelective(Interviewer record);

    List<Interviewer> selectByExample(InterviewerExample example);

    Interviewer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Interviewer record, @Param("example") InterviewerExample example);

    int updateByExample(@Param("record") Interviewer record, @Param("example") InterviewerExample example);

    int updateByPrimaryKeySelective(Interviewer record);

    int updateByPrimaryKey(Interviewer record);
}