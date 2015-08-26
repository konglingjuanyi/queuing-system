package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.PhaseInterviewerMapper;
import com.qunar.ops.recruit.dao.PhaseMapper;
import com.qunar.ops.recruit.model.Phase;
import com.qunar.ops.recruit.model.PhaseExample;
import com.qunar.ops.recruit.model.PhaseInterviewer;

@Component
public class PhaseInterviewService {

	@Autowired
	PhaseInterviewerMapper interMapper;
	@Autowired
	PhaseMapper pmMapper;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void addPhaseInterviewInfo(String all_value, String phase, String year) {
		// TODO Auto-generated method stub
		String[] all_citys=all_value.split("\\|");
		Set sval=new HashSet();
		for(int i=0;i<all_citys.length;i++){
			String[] all_rooms=all_citys[i].split(",");
			for(int j=0;j<all_rooms.length;j++){
				String[] city_room=all_rooms[j].split("_");
				//allcitys=allcitys+city_room[0]+"|";
				sval.add(city_room[0]);
				PhaseInterviewer pi=this.createPhaseInterview(city_room,phase,year);
				interMapper.insert(pi);
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
		pi.setStatus(0);
		pi.setTwoCount(0);
		pi.setYear(year);
		return pi;
	}

	
}
