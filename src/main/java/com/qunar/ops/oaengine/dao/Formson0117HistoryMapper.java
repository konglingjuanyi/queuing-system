package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0117History;
import com.qunar.ops.oaengine.model.Formson0117HistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0117HistoryMapper {
    int countByExample(Formson0117HistoryExample example);

    int deleteByExample(Formson0117HistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0117History record);

    int insertSelective(Formson0117History record);

    List<Formson0117History> selectByExample(Formson0117HistoryExample example);

    Formson0117History selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0117History record, @Param("example") Formson0117HistoryExample example);

    int updateByExample(@Param("record") Formson0117History record, @Param("example") Formson0117HistoryExample example);

    int updateByPrimaryKeySelective(Formson0117History record);

    int updateByPrimaryKey(Formson0117History record);
}