package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.qunar.ops.recruit.dao.PhaseMapper;
import com.qunar.ops.recruit.model.Phase;
import com.qunar.ops.recruit.model.PhaseExample;

@Component
public class PhaseService {

	@Autowired
	PhaseMapper phMapper;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<Phase> getPhaseInfos() {
		// TODO Auto-generated method stub
		PhaseExample ie = new PhaseExample();
		ie.setOrderByClause("phase_name");
		return phMapper.selectByExample(ie);
	}

	public Phase createPhaseInfo(Map<String, String> vars) {
		// TODO Auto-generated method stub
		Phase ph = new Phase();
		String yearInfo=vars.get("yearInfo");
		String phaseName=vars.get("phaseName");
		String id=vars.get("updid");
		if(id!=null){
			ph.setId(Integer.parseInt(id));
		}
		ph.setCityName("");
		ph.setPhaseName(phaseName);
		ph.setYearInfo(yearInfo);
		return ph;
	}

	public Object getPhaseInfoBy(String yearInfo, String phaseName) {
		// TODO Auto-generated method stub
		PhaseExample example = new PhaseExample();
		PhaseExample.Criteria criteria = example.createCriteria();
		criteria.andYearInfoEqualTo(yearInfo);
		criteria.andPhaseNameEqualTo(phaseName);
		List<Phase> inter = phMapper.selectByExample(example);
		if(inter != null && inter.size() > 0){
			return inter.get(0);
		}
		return null;
	}

	public void addPhaseInfo(Phase inter) {
		// TODO Auto-generated method stub
		phMapper.insert(inter);
	}

	public Phase getFirstPhase() {
		PhaseExample example = new PhaseExample();
		PhaseExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("id desc");
		List<Phase> list = phMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public List<Phase> getPhases() {
		PhaseExample example = new PhaseExample();
		PhaseExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("id desc");
		List<Phase> list = phMapper.selectByExample(example);
		return list;
	}

	public Phase getCityByPhase(String phase) {
		PhaseExample example = new PhaseExample();
		PhaseExample.Criteria criteria = example.createCriteria();
		criteria.andPhaseNameEqualTo(phase);
		List<Phase> list = phMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public List<Phase> getPhaseAndCityByYear(String year) {
		PhaseExample example = new PhaseExample();
		PhaseExample.Criteria criteria = example.createCriteria();
		criteria.andYearInfoEqualTo(year);
		List<Phase> list = phMapper.selectByExample(example);
		return list;
	}

	public List<Phase> getPhaseInfos(Integer currentPage, Integer pageSize) {
		PhaseExample ie = new PhaseExample();
		ie.setOrderByClause("phase_name");
		PageHelper.startPage(currentPage, pageSize);
		return phMapper.selectByExample(ie);
	}

	public List<Phase> getCitysByYearAndPhase(String year, String phase) {
		PhaseExample example = new PhaseExample();
		PhaseExample.Criteria criteria = example.createCriteria();
		criteria.andYearInfoEqualTo(year);
		criteria.andPhaseNameEqualTo(phase);
		List<Phase> list = phMapper.selectByExample(example);
		return list;
	}

	
}
