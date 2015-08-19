package com.qunar.ops.recruit.dao;

import java.util.List;

import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.model.InterviewerExample;

public interface InterviewerMapper {
    int countByExample(InterviewerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Interviewer record);

    int insertSelective(Interviewer record);

    Interviewer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Interviewer record);

    int updateByPrimaryKey(Interviewer record);

	List<Interviewer> getAll();
	
	List<Interviewer> getInterviewers(int offset, int limit);
}