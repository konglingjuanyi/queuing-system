package com.qunar.ops.recruit.dao;

import com.qunar.ops.recruit.model.Student;


public interface JoinMapper {

	String getFirstInterviewRoomNumber(Student s);
	String getSecondInterviewRoomNumber(Student s);
}