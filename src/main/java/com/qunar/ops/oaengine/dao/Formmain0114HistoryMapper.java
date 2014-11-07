package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formmain0114History;
import com.qunar.ops.oaengine.model.Formmain0114HistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formmain0114HistoryMapper {
    int countByExample(Formmain0114HistoryExample example);

    int deleteByExample(Formmain0114HistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Formmain0114History record);

    int insertSelective(Formmain0114History record);

    List<Formmain0114History> selectByExample(Formmain0114HistoryExample example);

    Formmain0114History selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Formmain0114History record, @Param("example") Formmain0114HistoryExample example);

    int updateByExample(@Param("record") Formmain0114History record, @Param("example") Formmain0114HistoryExample example);

    int updateByPrimaryKeySelective(Formmain0114History record);

    int updateByPrimaryKey(Formmain0114History record);
}