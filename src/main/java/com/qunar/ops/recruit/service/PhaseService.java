package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		return phMapper.selectByExample(ie);
	}

	
}
