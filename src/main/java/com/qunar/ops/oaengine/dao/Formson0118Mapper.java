package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0118;
import com.qunar.ops.oaengine.model.Formson0118Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0118Mapper {
    int countByExample(Formson0118Example example);

    int deleteByExample(Formson0118Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Formson0118 record);

    int insertSelective(Formson0118 record);

    List<Formson0118> selectByExample(Formson0118Example example);

    Formson0118 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Formson0118 record, @Param("example") Formson0118Example example);

    int updateByExample(@Param("record") Formson0118 record, @Param("example") Formson0118Example example);

    int updateByPrimaryKeySelective(Formson0118 record);

    int updateByPrimaryKey(Formson0118 record);
}