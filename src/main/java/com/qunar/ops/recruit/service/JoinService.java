package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.JoinMapper;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.model.connect_table.InterviewerInfoToPage;
import com.qunar.ops.recruit.util.RecruitConst;

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


	public List<InterviewerInfoToPage> getInterviewerInfoToPages(String year, String phase, String city) {
		return joinMapper.getInterviewersInfoToPages(year, phase, city, RecruitConst.STUDENT_STATE_TWO_PASS);
	}

	public List<InterviewerInfoToPage> getInterviewerDetailsInfoToPages(String year, String phase, String city, int id) {
		return joinMapper.getInterviewerDetailsInfoToPages(year, phase, city, id, RecruitConst.STUDENT_STATE_TWO_PASS);
	}
	
	public void testqyd(){
	}
	


}
