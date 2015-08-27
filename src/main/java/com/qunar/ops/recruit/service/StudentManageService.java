package com.qunar.ops.recruit.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.StudentMapper;
import com.qunar.ops.recruit.model.Student;

@Component
public class StudentManageService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	StudentMapper stuMapper;
	public Student createStudent(Row row) throws Exception {
		// TODO Auto-generated method stub
		Student stu=new Student();
		stu.setNoteNo(getStringCellValue(row.getCell(0)));
		stu.setYear(getStringCellValue(row.getCell(1)));
		stu.setPhaseNo(getStringCellValue(row.getCell(2)));
		String time=getStringCellValue(row.getCell(3));
		if("".equals(time)){
			stu.setInterviewTime(null);
		}else{
			stu.setInterviewTime(sdf.parse(time));
		}
		stu.setName(getStringCellValue(row.getCell(4)));
		return stu;
	}
	
	public String getStringCellValue(Cell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

}
