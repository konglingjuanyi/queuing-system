package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.JoinMapper;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.model.connect_table.InterviewerInfoToPage;

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


	public List<InterviewerInfoToPage> getInterviewerInfoToPages() {
		return joinMapper.getInterviewersInfoToPages("2015", "2015秋季校园招聘", "北京");
	}


	public InterviewerInfoToPage getInterviewerInfoToPage(int id) {
		return joinMapper.getInterviewerInfoToPages("2015", "2015秋季校园招聘", "北京", id);
	}
	


}
