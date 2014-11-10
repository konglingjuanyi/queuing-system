package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0118History;
import com.qunar.ops.oaengine.model.Formson0118HistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0118HistoryMapper {
    int countByExample(Formson0118HistoryExample example);

    int deleteByExample(Formson0118HistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0118History record);

    int insertSelective(Formson0118History record);

    List<Formson0118History> selectByExample(Formson0118HistoryExample example);

    Formson0118History selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0118History record, @Param("example") Formson0118HistoryExample example);

    int updateByExample(@Param("record") Formson0118History record, @Param("example") Formson0118HistoryExample example);

    int updateByPrimaryKeySelective(Formson0118History record);

    int updateByPrimaryKey(Formson0118History record);
}