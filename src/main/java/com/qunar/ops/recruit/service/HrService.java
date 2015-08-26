package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.HrMapper;
import com.qunar.ops.recruit.dao.InterviewerMapper;
import com.qunar.ops.recruit.model.Hr;
import com.qunar.ops.recruit.model.HrExample;
import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.model.InterviewerExample;

@Component
public class HrService {

	@Autowired
	InterviewerMapper interMapper;
	@Autowired
	HrMapper hrMapper;
	
	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
//		if(cityName != null)
//			criteria.andCityEqualTo(cityName);
//		example.setOffset(offset);
////		System.out.println("hahahahhahaa-----");
//		example.setLimit(limit);
//		System.out.println(offset+" "+limit+" "+start+" "+end+ " "+cityName);
		return interMapper.selectByExample(example);
	}

	public void addInterviewer(Interviewer inter) {
		interMapper.insert(inter);
	}

	/**
	 * 通过ID获取面试官信息
	 * @param id 
	 * @return
	 */
	public Interviewer getInterviewersById(String id) {
		// TODO Auto-generated method stub
		return interMapper.selectByPrimaryKey(Integer.valueOf(id));
	}

	public Interviewer getInterviewersByUserName(String username) {
		InterviewerExample ie = new InterviewerExample();
		ie.createCriteria().andUserNameEqualTo(username);
		List<Interviewer> inter = interMapper.selectByExample(ie);
		if(inter != null && inter.size() > 0){
			return inter.get(0);
		}
		return null;
	}

	public Hr getHrByUserNameAndPass(String username,String password) {
		HrExample hr = new HrExample();
		HrExample.Criteria criteria = hr.createCriteria();
		criteria.andUserNameEqualTo(username);
		criteria.andPassWordEqualTo(password);
		List<Hr> inter = hrMapper.selectByExample(hr);
		if(inter != null && inter.size() > 0){
			return inter.get(0);
		}
		return null;
	}

}
