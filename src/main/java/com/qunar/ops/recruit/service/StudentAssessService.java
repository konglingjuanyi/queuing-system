package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.JoinMapper;
import com.qunar.ops.recruit.dao.StudentAssessMapper;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.model.StudentAssess;
import com.qunar.ops.recruit.model.StudentAssessExample;
import com.qunar.ops.recruit.model.connect_table.InterviewerInfoToPage;
import com.qunar.ops.recruit.util.RecruitConst;

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



}
