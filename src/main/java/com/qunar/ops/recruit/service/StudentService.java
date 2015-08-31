package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.StudentMapper;
import com.qunar.ops.recruit.model.HrExample.Criteria;
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

	public List<Student> getOneList() {
		StudentExample se = new StudentExample();
		StudentExample.Criteria cr = se.createCriteria();
		cr.andFirstTryIsNull();
		cr.andTrueTimeIsNotNull();
		List<Student> ret = stuMapper.selectByExample(se);
		return ret;
	}

	public List<Student> getTwoList() {
		StudentExample se = new StudentExample();
		StudentExample.Criteria cr = se.createCriteria();
		cr.andFirstTryIsNotNull();
		cr.andSecondTryIsNull();
		List<Student> ret = stuMapper.selectByExample(se);
		return ret;
	}

	public void addStudentRealComeTimeIntoDB(Student stu, Date date) {

	}

	public void updateStudent(Student stu) {
		stuMapper.updateByPrimaryKeySelective(stu);
	}

	public int getNumberOfRoom(Student student) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void updateStudentNotSelective(Student stu) {
		stuMapper.updateByPrimaryKey(stu);
		
	}

	public List<Student> getStudentsByFirstTry(String userName) {
		StudentExample se = new StudentExample();
		StudentExample.Criteria cr = se.createCriteria();
		cr.andFirstTryEqualTo(userName);
		List<Student> ret = stuMapper.selectByExample(se);
		return ret;
	}
	


}
