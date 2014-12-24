package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0115History;
import com.qunar.ops.oaengine.model.Formson0115HistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0115HistoryMapper {
    int countByExample(Formson0115HistoryExample example);

    int deleteByExample(Formson0115HistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0115History record);

    int insertSelective(Formson0115History record);

    List<Formson0115History> selectByExample(Formson0115HistoryExample example);

    Formson0115History selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0115History record, @Param("example") Formson0115HistoryExample example);

    int updateByExample(@Param("record") Formson0115History record, @Param("example") Formson0115HistoryExample example);

    int updateByPrimaryKeySelective(Formson0115History record);

    int updateByPrimaryKey(Formson0115History record);
}