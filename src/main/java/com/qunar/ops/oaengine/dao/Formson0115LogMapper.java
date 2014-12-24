package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0115Log;
import com.qunar.ops.oaengine.model.Formson0115LogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0115LogMapper {
    int countByExample(Formson0115LogExample example);

    int deleteByExample(Formson0115LogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0115Log record);

    int insertSelective(Formson0115Log record);

    List<Formson0115Log> selectByExample(Formson0115LogExample example);

    Formson0115Log selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0115Log record, @Param("example") Formson0115LogExample example);

    int updateByExample(@Param("record") Formson0115Log record, @Param("example") Formson0115LogExample example);

    int updateByPrimaryKeySelective(Formson0115Log record);

    int updateByPrimaryKey(Formson0115Log record);
}