package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formson0119;
import com.qunar.ops.oaengine.model.Formson0119Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formson0119Mapper {
    int countByExample(Formson0119Example example);

    int deleteByExample(Formson0119Example example);

    int deleteByPrimaryKey(Long id);

    int insert(Formson0119 record);

    int insertSelective(Formson0119 record);

    List<Formson0119> selectByExample(Formson0119Example example);

    Formson0119 selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Formson0119 record, @Param("example") Formson0119Example example);

    int updateByExample(@Param("record") Formson0119 record, @Param("example") Formson0119Example example);

    int updateByPrimaryKeySelective(Formson0119 record);

    int updateByPrimaryKey(Formson0119 record);
}