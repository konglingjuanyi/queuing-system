package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.PhaseInterviewerMapper;
import com.qunar.ops.recruit.dao.PhaseMapper;
import com.qunar.ops.recruit.model.Phase;
import com.qunar.ops.recruit.model.PhaseExample;
import com.qunar.ops.recruit.model.PhaseInterviewer;
import com.qunar.ops.recruit.model.PhaseInterviewerExample;
import com.qunar.ops.recruit.util.RecruitConst;

@Component
public class PhaseInterviewService {

	@Autowired
	PhaseInterviewerMapper interMapper;
	@Autowired
	PhaseMapper pmMapper;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void addPhaseInterviewInfo(String all_value, String phase, String year) {
		// TODO Auto-generated method stub
		PhaseInterviewer allval=new PhaseInterviewer();
		allval.setEnable("0");
		PhaseInterviewerExample example0 = new PhaseInterviewerExample();
		PhaseInterviewerExample.Criteria criteria0 = example0.createCriteria();
		criteria0.andYearEqualTo(year);
		criteria0.andPhaseEqualTo(phase);
		interMapper.updateByExampleSelective(allval, example0);
		
		String[] all_citys=all_value.split("\\|");
		Set sval=new HashSet();
		for(int i=0;i<all_citys.length;i++){
			String[] all_rooms=all_citys[i].split(",");
			for(int j=0;j<all_rooms.length;j++){
				String[] city_room=all_rooms[j].split("_");
				sval.add(city_room[0]);
				PhaseInterviewerExample example1 = new PhaseInterviewerExample();
				PhaseInterviewerExample.Criteria criteria1 = example1.createCriteria();
				criteria1.andYearEqualTo(year);
				criteria1.andPhaseEqualTo(phase);
				criteria1.andCityEqualTo(city_room[0]);
				criteria1.andIntervierNameEqualTo(city_room[1]);
				List<PhaseInterviewer> ishave=interMapper.selectByExample(example1);
				if(ishave.size()>0){
					PhaseInterviewer updpi=new PhaseInterviewer();
					updpi.setRoom(city_room[2]);
					updpi.setEnable(String.valueOf(1));
					interMapper.updateByExampleSelective(updpi, example1);
				}else{
					PhaseInterviewer pi=this.createPhaseInterview(city_room,phase,year);
					interMapper.insert(pi);
				}
			}
		}
		String allcitys = StringUtils.join(sval.toArray(), "|");
		Phase ph=new Phase();
		ph.setCityName(allcitys);
		PhaseExample example = new PhaseExample();
		PhaseExample.Criteria criteria = example.createCriteria();
		criteria.andYearInfoEqualTo(year);
		criteria.andPhaseNameEqualTo(phase);
		pmMapper.updateByExampleSelective(ph, example);
	}

	private PhaseInterviewer createPhaseInterview(String[] city_room, String phase, String year) {
		// TODO Auto-generated method stub
		PhaseInterviewer pi=new PhaseInterviewer();
		pi.setCity(city_room[0]);
		pi.setIntervierName(city_room[1]);
		pi.setRoom(city_room[2]);
		pi.setFirstFe(0);
		pi.setFirstQa(0);
		pi.setFirstRd(0);
		pi.setOneCount(0);
		pi.setPhase(phase);
		pi.setSecondFe(0);
		pi.setSecondQa(0);
		pi.setSecondRd(0);
		pi.setStatus(RecruitConst.STUDENT_STATE_NOT_REGIST);
		pi.setTwoCount(0);
		pi.setYear(year);
		pi.setEnable(String.valueOf(1));
		return pi;
	}

	public List<PhaseInterviewer> getPhaseInterviewerBy(String year, String phase) {
		// TODO Auto-generated method stub
		PhaseInterviewerExample example = new PhaseInterviewerExample();
		PhaseInterviewerExample.Criteria criteria = example.createCriteria();
		criteria.andYearEqualTo(year);
		criteria.andPhaseEqualTo(phase);
		criteria.andEnableEqualTo("1");
		List<PhaseInterviewer> list = interMapper.selectByExample(example);
		return list;
	}

	public List<PhaseInterviewer> getSinglecityBy(String city, String year, String phase) {
		// TODO Auto-generated method stub
		PhaseInterviewerExample example = new PhaseInterviewerExample();
		PhaseInterviewerExample.Criteria criteria = example.createCriteria();
		criteria.andCityEqualTo(city);
		criteria.andYearEqualTo(year);
		criteria.andPhaseEqualTo(phase);
		criteria.andEnableEqualTo("1");
		List<PhaseInterviewer> list = interMapper.selectByExample(example);
		return list;
	}

	public List<PhaseInterviewer> getPhaseIntersByName(String name) {
		PhaseInterviewerExample example = new PhaseInterviewerExample();
		PhaseInterviewerExample.Criteria criteria = example.createCriteria();
		criteria.andIntervierNameEqualTo(name);
		criteria.andEnableEqualTo("1");
		List<PhaseInterviewer> list = interMapper.selectByExample(example);
		return list;
	}

	public List<PhaseInterviewer> getPhasesByYearAndName(String year,
			String userName) {
		PhaseInterviewerExample example = new PhaseInterviewerExample();
		PhaseInterviewerExample.Criteria criteria = example.createCriteria();
		criteria.andYearEqualTo(year);
		criteria.andIntervierNameEqualTo(userName);
		criteria.andEnableEqualTo("1");
		List<PhaseInterviewer> list = interMapper.selectByExample(example);
		return list;
	}
	
	public List<PhaseInterviewer> getCitysByYearPhaseAndName(String year,
			String phase, String userName) {
		PhaseInterviewerExample example = new PhaseInterviewerExample();
		PhaseInterviewerExample.Criteria criteria = example.createCriteria();
		criteria.andYearEqualTo(year);
		criteria.andPhaseEqualTo(phase);
		criteria.andIntervierNameEqualTo(userName);
		criteria.andEnableEqualTo("1");
		List<PhaseInterviewer> list = interMapper.selectByExample(example);
		return list;
	}

	public PhaseInterviewer getPhaseInterviewerBy(String year, String phase,
			String city, String userName) {
		PhaseInterviewerExample example = new PhaseInterviewerExample();
		PhaseInterviewerExample.Criteria criteria = example.createCriteria();
		criteria.andYearEqualTo(year);
		criteria.andPhaseEqualTo(phase);
		criteria.andCityEqualTo(city);
		criteria.andIntervierNameEqualTo(userName);
		criteria.andEnableEqualTo("1");
		List<PhaseInterviewer> list = interMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public void update(PhaseInterviewer pi) {
		interMapper.updateByPrimaryKeySelective(pi);
		
	}

	
}
