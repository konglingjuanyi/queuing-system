package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0118Log;
import com.qunar.ops.oaengine.model.Formson0118LogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0118LogMapper {
    int countByExample(Formson0118LogExample example);

    int deleteByExample(Formson0118LogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0118Log record);

    int insertSelective(Formson0118Log record);

    List<Formson0118Log> selectByExample(Formson0118LogExample example);

    Formson0118Log selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0118Log record, @Param("example") Formson0118LogExample example);

    int updateByExample(@Param("record") Formson0118Log record, @Param("example") Formson0118LogExample example);

    int updateByPrimaryKeySelective(Formson0118Log record);

    int updateByPrimaryKey(Formson0118Log record);
}