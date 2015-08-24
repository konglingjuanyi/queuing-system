package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.JoinMapper;
import com.qunar.ops.recruit.model.Student;

@Component
public class JoinService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	JoinMapper joinMapper;


	public String getFirstInterviewRoomNumber(Student student) {
		String roomNumber = joinMapper.getFirstInterviewRoomNumber(student);
		return roomNumber;
	}


	public String getSecondInterviewRoomNumber(Student student) {
		String roomNumber = joinMapper.getSecondInterviewRoomNumber(student);
		return roomNumber;
	}
	


}
