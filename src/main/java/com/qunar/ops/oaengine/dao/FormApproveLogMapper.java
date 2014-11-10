package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.FormApproveLog;
import com.qunar.ops.oaengine.model.FormApproveLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FormApproveLogMapper {
    int countByExample(FormApproveLogExample example);

    int deleteByExample(FormApproveLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FormApproveLog record);

    int insertSelective(FormApproveLog record);

    List<FormApproveLog> selectByExample(FormApproveLogExample example);

    FormApproveLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FormApproveLog record, @Param("example") FormApproveLogExample example);

    int updateByExample(@Param("record") FormApproveLog record, @Param("example") FormApproveLogExample example);

    int updateByPrimaryKeySelective(FormApproveLog record);

    int updateByPrimaryKey(FormApproveLog record);
}