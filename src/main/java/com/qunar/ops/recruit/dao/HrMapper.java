package com.qunar.ops.recruit.dao;

import com.qunar.ops.recruit.model.Hr;
import com.qunar.ops.recruit.model.HrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HrMapper {
    int countByExample(HrExample example);

    int deleteByExample(HrExample example);

    int insert(Hr record);

    int insertSelective(Hr record);

    List<Hr> selectByExample(HrExample example);

    int updateByExampleSelective(@Param("record") Hr record, @Param("example") HrExample example);

    int updateByExample(@Param("record") Hr record, @Param("example") HrExample example);
}