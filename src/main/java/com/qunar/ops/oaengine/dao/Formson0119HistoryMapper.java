package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0119History;
import com.qunar.ops.oaengine.model.Formson0119HistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0119HistoryMapper {
    int countByExample(Formson0119HistoryExample example);

    int deleteByExample(Formson0119HistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0119History record);

    int insertSelective(Formson0119History record);

    List<Formson0119History> selectByExample(Formson0119HistoryExample example);

    Formson0119History selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0119History record, @Param("example") Formson0119HistoryExample example);

    int updateByExample(@Param("record") Formson0119History record, @Param("example") Formson0119HistoryExample example);

    int updateByPrimaryKeySelective(Formson0119History record);

    int updateByPrimaryKey(Formson0119History record);
}