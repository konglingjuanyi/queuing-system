package com.qunar.ops.recruit.dao;

import com.qunar.ops.recruit.model.StudentAssessImg;
import com.qunar.ops.recruit.model.StudentAssessImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentAssessImgMapper {
    int countByExample(StudentAssessImgExample example);

    int deleteByExample(StudentAssessImgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentAssessImg record);

    int insertSelective(StudentAssessImg record);

    List<StudentAssessImg> selectByExample(StudentAssessImgExample example);

    StudentAssessImg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentAssessImg record, @Param("example") StudentAssessImgExample example);

    int updateByExample(@Param("record") StudentAssessImg record, @Param("example") StudentAssessImgExample example);

    int updateByPrimaryKeySelective(StudentAssessImg record);

    int updateByPrimaryKey(StudentAssessImg record);
}