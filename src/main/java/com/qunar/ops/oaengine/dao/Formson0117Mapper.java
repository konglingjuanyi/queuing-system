package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0117;
import com.qunar.ops.oaengine.model.Formson0117Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0117Mapper {
    int countByExample(Formson0117Example example);

    int deleteByExample(Formson0117Example example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0117 record);

    int insertSelective(Formson0117 record);

    List<Formson0117> selectByExample(Formson0117Example example);

    Formson0117 selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0117 record, @Param("example") Formson0117Example example);

    int updateByExample(@Param("record") Formson0117 record, @Param("example") Formson0117Example example);

    int updateByPrimaryKeySelective(Formson0117 record);

    int updateByPrimaryKey(Formson0117 record);
}