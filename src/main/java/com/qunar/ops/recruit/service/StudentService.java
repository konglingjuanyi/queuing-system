package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.StudentMapper;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.model.StudentExample;

@Component
public class StudentService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	StudentMapper stuMapper;

	public Student getStudentByPhone(String phone) {
		StudentExample se = new StudentExample();
		se.createCriteria().andPhoneEqualTo(phone);
		List<Student> ret = stuMapper.selectByExample(se);
		if(ret != null &&ret.size() > 0)
			return ret.get(0);
		else
			return null;
	}
	


}
