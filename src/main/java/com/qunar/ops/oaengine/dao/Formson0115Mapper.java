package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0115;
import com.qunar.ops.oaengine.model.Formson0115Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0115Mapper {
    int countByExample(Formson0115Example example);

    int deleteByExample(Formson0115Example example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0115 record);

    int insertSelective(Formson0115 record);

    List<Formson0115> selectByExample(Formson0115Example example);

    Formson0115 selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0115 record, @Param("example") Formson0115Example example);

    int updateByExample(@Param("record") Formson0115 record, @Param("example") Formson0115Example example);

    int updateByPrimaryKeySelective(Formson0115 record);

    int updateByPrimaryKey(Formson0115 record);
}