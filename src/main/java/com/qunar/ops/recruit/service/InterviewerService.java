package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.InterviewerMapper;
import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.model.InterviewerExample;
import com.qunar.ops.recruit.util.QUtils;

@Component
public class InterviewerService {

	@Autowired
	InterviewerMapper interMapper;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<Interviewer> getAllInterviewer() {
		return null;
	}

	public List<Interviewer> getInterviewers() {
		InterviewerExample ie = new InterviewerExample();
		return interMapper.selectByExample(ie);
	}

	public List<Interviewer> getInterviewers(int offset, int limit, Date start,
			Date end, String cityName) {
		return null;
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

	public Interviewer getInterviewerByNameAndPass(String username, String password) {
		InterviewerExample ie = new InterviewerExample();
		InterviewerExample.Criteria c = ie.createCriteria();
		c.andPasswordEqualTo(password);
		c.andUserNameEqualTo(username);
		List<Interviewer> inter = interMapper.selectByExample(ie);
		if(inter != null && inter.size() > 0){
			return inter.get(0);
		}
		return null;
	}

	public List<Interviewer> getInterviewers(int i, int pageSize, String phase,
			String city) {
		InterviewerExample example = new InterviewerExample();
		InterviewerExample.Criteria criteria = example.createCriteria();
		if(city != null)
			criteria.andCityEqualTo(city);
		if(phase != null)
			criteria.andPhaseEqualTo(phase);
//		example.setOffset(offset);
////		System.out.println("hahahahhahaa-----");
//		example.setLimit(limit);
//		System.out.println(offset+" "+limit+" "+start+" "+end+ " "+cityName);
		return interMapper.selectByExample(example);
	}
	
	
	public Interviewer createInterviewer(Map<String, String> vars) {
		Interviewer inter = new Interviewer();
		String new_city = vars.get("new_city");
		String new_name = vars.get("new_name");
		String new_job = vars.get("new_job");
		String new_user = vars.get("new_user");
		String new_password = vars.get("new_password");
		String new_startDate = vars.get("new_startDate");
		String new_endDate = vars.get("new_endDate");
		String new_role = vars.get("new_role");
		inter.setCity(new_city);
		inter.setUserName(new_user);
		inter.setPassword(new_password);
		Date startDate = QUtils.formatDate(new_startDate);
		Date endDate = QUtils.formatDate(new_endDate);
		inter.setOneCount(0);
		inter.setTwoCount(0);
		return inter;
	}

}
