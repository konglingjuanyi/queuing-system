package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0116;
import com.qunar.ops.oaengine.model.Formson0116Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0116Mapper {
    int countByExample(Formson0116Example example);

    int deleteByExample(Formson0116Example example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0116 record);

    int insertSelective(Formson0116 record);

    List<Formson0116> selectByExample(Formson0116Example example);

    Formson0116 selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0116 record, @Param("example") Formson0116Example example);

    int updateByExample(@Param("record") Formson0116 record, @Param("example") Formson0116Example example);

    int updateByPrimaryKeySelective(Formson0116 record);

    int updateByPrimaryKey(Formson0116 record);
}