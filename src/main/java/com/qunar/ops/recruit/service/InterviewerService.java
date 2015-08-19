package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.InterviewerMapper;
import com.qunar.ops.recruit.model.Interviewer;

@Component
public class InterviewerService {

	@Value("${backyard.apihost}")
	String backyardUrl;
	
	@Value("${backyard.apihost.bank}")
	String backyardBankUrl;
	
	@Value("${backyard.drt_vp}")
	String backyardDrt_vpUrl;
	
	@Value("${oa.apihost}")
	String oadUrl;
	
	@Autowired
	InterviewerMapper interMapper;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<Interviewer> getAllInterviewer() {
		return interMapper.getAll();
	}

	public List<Interviewer> getInterviewers(int offset, int limit) {
		// TODO Auto-generated method stub
		return interMapper.getInterviewers(offset, limit);
	}

}
