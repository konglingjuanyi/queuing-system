package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.InterviewerMapper;
import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.model.InterviewerExample;

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
		return null;
	}

	public List<Interviewer> getInterviewers(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Interviewer> getInterviewers(int offset, int limit, Date start,
			Date end, String cityName) {
		InterviewerExample example = new InterviewerExample();
		InterviewerExample.Criteria criteria = example.createCriteria();
		if(cityName != null)
			criteria.andCityEqualTo(cityName);
		if(start != null)
			criteria.andStartDateGreaterThanOrEqualTo(start);
//		example.setOffset(offset);
////		System.out.println("hahahahhahaa-----");
//		example.setLimit(limit);
//		System.out.println(offset+" "+limit+" "+start+" "+end+ " "+cityName);
		return interMapper.selectByExample(example);
	}

	public void addInterviewer(Interviewer inter) {
		interMapper.insert(inter);
	}

}
