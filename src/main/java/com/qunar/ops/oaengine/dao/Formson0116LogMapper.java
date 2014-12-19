package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0116Log;
import com.qunar.ops.oaengine.model.Formson0116LogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0116LogMapper {
    int countByExample(Formson0116LogExample example);

    int deleteByExample(Formson0116LogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0116Log record);

    int insertSelective(Formson0116Log record);

    List<Formson0116Log> selectByExample(Formson0116LogExample example);

    Formson0116Log selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0116Log record, @Param("example") Formson0116LogExample example);

    int updateByExample(@Param("record") Formson0116Log record, @Param("example") Formson0116LogExample example);

    int updateByPrimaryKeySelective(Formson0116Log record);

    int updateByPrimaryKey(Formson0116Log record);
}