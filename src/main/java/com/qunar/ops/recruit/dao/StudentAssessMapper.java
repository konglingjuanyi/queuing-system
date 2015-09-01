package com.qunar.ops.recruit.dao;

import com.qunar.ops.recruit.model.StudentAssess;
import com.qunar.ops.recruit.model.StudentAssessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentAssessMapper {
    int countByExample(StudentAssessExample example);

    int deleteByExample(StudentAssessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentAssess record);

    int insertSelective(StudentAssess record);

    List<StudentAssess> selectByExample(StudentAssessExample example);

    StudentAssess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentAssess record, @Param("example") StudentAssessExample example);

    int updateByExample(@Param("record") StudentAssess record, @Param("example") StudentAssessExample example);

    int updateByPrimaryKeySelective(StudentAssess record);

    int updateByPrimaryKey(StudentAssess record);
}