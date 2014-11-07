package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.FormUpdateLog;
import com.qunar.ops.oaengine.model.FormUpdateLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FormUpdateLogMapper {
    int countByExample(FormUpdateLogExample example);

    int deleteByExample(FormUpdateLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FormUpdateLog record);

    int insertSelective(FormUpdateLog record);

    List<FormUpdateLog> selectByExample(FormUpdateLogExample example);

    FormUpdateLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FormUpdateLog record, @Param("example") FormUpdateLogExample example);

    int updateByExample(@Param("record") FormUpdateLog record, @Param("example") FormUpdateLogExample example);

    int updateByPrimaryKeySelective(FormUpdateLog record);

    int updateByPrimaryKey(FormUpdateLog record);
}