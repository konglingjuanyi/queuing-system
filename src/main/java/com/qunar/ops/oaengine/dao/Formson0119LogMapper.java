package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0119Log;
import com.qunar.ops.oaengine.model.Formson0119LogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0119LogMapper {
    int countByExample(Formson0119LogExample example);

    int deleteByExample(Formson0119LogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0119Log record);

    int insertSelective(Formson0119Log record);

    List<Formson0119Log> selectByExample(Formson0119LogExample example);

    Formson0119Log selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0119Log record, @Param("example") Formson0119LogExample example);

    int updateByExample(@Param("record") Formson0119Log record, @Param("example") Formson0119LogExample example);

    int updateByPrimaryKeySelective(Formson0119Log record);

    int updateByPrimaryKey(Formson0119Log record);
}