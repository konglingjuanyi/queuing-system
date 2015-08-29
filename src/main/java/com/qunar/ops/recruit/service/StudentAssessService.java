package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.StudentAssessMapper;
import com.qunar.ops.recruit.model.StudentAssess;
import com.qunar.ops.recruit.model.StudentAssessExample;

@Component
public class StudentAssessService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	StudentAssessMapper saMapper;
	
	public StudentAssess getStudentAssessByStudentId(int id) {
		StudentAssessExample example = new StudentAssessExample();
		StudentAssessExample.Criteria criteria = example.createCriteria();
		criteria.andStudenIdEqualTo(id);
		List<StudentAssess> list = saMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public StudentAssess createStudentAssess(Map<String,String> vars){
		StudentAssess stu=new StudentAssess();
		
		return stu;
	}

}
