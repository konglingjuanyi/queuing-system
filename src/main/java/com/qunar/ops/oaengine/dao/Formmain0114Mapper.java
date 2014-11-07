package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Formmain0114;
import com.qunar.ops.oaengine.model.Formmain0114Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Formmain0114Mapper {
    int countByExample(Formmain0114Example example);

    int deleteByExample(Formmain0114Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Formmain0114 record);

    int insertSelective(Formmain0114 record);

    List<Formmain0114> selectByExample(Formmain0114Example example);

    Formmain0114 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Formmain0114 record, @Param("example") Formmain0114Example example);

    int updateByExample(@Param("record") Formmain0114 record, @Param("example") Formmain0114Example example);

    int updateByPrimaryKeySelective(Formmain0114 record);

    int updateByPrimaryKey(Formmain0114 record);
}