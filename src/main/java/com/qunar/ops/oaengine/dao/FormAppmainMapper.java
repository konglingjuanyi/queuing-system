package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.FormAppmain;
import com.qunar.ops.oaengine.model.FormAppmainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FormAppmainMapper {
    int countByExample(FormAppmainExample example);

    int deleteByExample(FormAppmainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FormAppmain record);

    int insertSelective(FormAppmain record);

    List<FormAppmain> selectByExample(FormAppmainExample example);

    FormAppmain selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FormAppmain record, @Param("example") FormAppmainExample example);

    int updateByExample(@Param("record") FormAppmain record, @Param("example") FormAppmainExample example);

    int updateByPrimaryKeySelective(FormAppmain record);

    int updateByPrimaryKey(FormAppmain record);
}