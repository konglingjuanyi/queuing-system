package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0117Log;
import com.qunar.ops.oaengine.model.Formson0117LogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0117LogMapper {
    int countByExample(Formson0117LogExample example);

    int deleteByExample(Formson0117LogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0117Log record);

    int insertSelective(Formson0117Log record);

    List<Formson0117Log> selectByExample(Formson0117LogExample example);

    Formson0117Log selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0117Log record, @Param("example") Formson0117LogExample example);

    int updateByExample(@Param("record") Formson0117Log record, @Param("example") Formson0117LogExample example);

    int updateByPrimaryKeySelective(Formson0117Log record);

    int updateByPrimaryKey(Formson0117Log record);
}