package com.qunar.ops.recruit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.model.connect_table.InterviewerInfoToPage;


public interface JoinMapper {

	String getFirstInterviewRoomNumber(Student s);
	String getSecondInterviewRoomNumber(Student s);
	List<InterviewerInfoToPage> getInterviewersInfoToPages(@Param("year")String year, @Param("phase")String phase, @Param("city")String city, @Param("state")String state);
	InterviewerInfoToPage getInterviewerInfoToPages(@Param("year")String year, @Param("phase")String phase, @Param("city")String city, @Param("interid")int id);
}