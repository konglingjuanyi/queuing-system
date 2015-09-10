package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.StudentAssessImgMapper;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.model.StudentAssessImg;

@Component
public class StudentAssessImgService {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	StudentAssessImgMapper saMapper;

	public int insertImg(Student stu, String name) {
		// TODO Auto-generated method stub
		StudentAssessImg img = new StudentAssessImg();
		if(stu!=null){
			img.setImgName(name);
			img.setStudentId(stu.getId());
			return saMapper.insert(img);
		}
		return -1;
	}

}
