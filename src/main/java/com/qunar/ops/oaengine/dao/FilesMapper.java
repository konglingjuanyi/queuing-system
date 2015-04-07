package com.qunar.ops.oaengine.dao;

import com.qunar.ops.oaengine.model.Files;
import com.qunar.ops.oaengine.model.FilesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FilesMapper {
    int countByExample(FilesExample example);

    int deleteByExample(FilesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Files record);

    int insertSelective(Files record);

    List<Files> selectByExampleWithBLOBs(FilesExample example);

    List<Files> selectByExample(FilesExample example);

    Files selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Files record, @Param("example") FilesExample example);

    int updateByExampleWithBLOBs(@Param("record") Files record, @Param("example") FilesExample example);

    int updateByExample(@Param("record") Files record, @Param("example") FilesExample example);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKeyWithBLOBs(Files record);

    int updateByPrimaryKey(Files record);
}