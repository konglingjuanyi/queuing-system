package com.qunar.ops.recruit.dao;

import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.model.StudentExample;

public interface StudentMapper {
    int countByExample(StudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}