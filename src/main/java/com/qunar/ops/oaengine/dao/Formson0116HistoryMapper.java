package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0116History;
import com.qunar.ops.oaengine.model.Formson0116HistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0116HistoryMapper {
    int countByExample(Formson0116HistoryExample example);

    int deleteByExample(Formson0116HistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0116History record);

    int insertSelective(Formson0116History record);

    List<Formson0116History> selectByExample(Formson0116HistoryExample example);

    Formson0116History selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0116History record, @Param("example") Formson0116HistoryExample example);

    int updateByExample(@Param("record") Formson0116History record, @Param("example") Formson0116HistoryExample example);

    int updateByPrimaryKeySelective(Formson0116History record);

    int updateByPrimaryKey(Formson0116History record);
}