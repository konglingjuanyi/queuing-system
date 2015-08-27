package com.qunar.ops.recruit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.service.StudentManageService;
import com.qunar.ops.recruit.util.RecruitConst;

@Controller
public class StudentManageController {

	@Autowired
	StudentManageService stuService;
	/**
	 * 候选人管理
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hr/getAllStudentInfos")
	public String getAllStudentInfos(HttpServletRequest request,  ModelMap model) {
		System.out.println("+++++++++++++++");
		return "/student_manage";
	}
	
	@RequestMapping(value="/hr/importStudentInfos",method=RequestMethod.POST)
	public String importStaffInfo(@RequestParam("fileField") MultipartFile clientFile,
			HttpServletResponse response, HttpServletRequest request,  ModelMap model) {
		// message记录导入相关的信息
		String fileType = "";
		try {
			String fileName = clientFile.getOriginalFilename();
			if("".equals(fileName)||fileName==null){
				fileType="";
			}else{
				fileType = fileName.substring(fileName.lastIndexOf(".") + 1,
						fileName.length());
			}
		} catch (Exception e) {
			fileType="";
		}

		if (!fileType.toLowerCase().equals("xls")
				&& !fileType.toLowerCase().equals("xlsx")) {
			model.addAttribute("importmessage", RecruitConst.ALREADY_EXIST_IMPORT_ERROR_MSG);
			return "/student_manage";
		}else{
			if (clientFile.getSize()!=0 && clientFile.getSize()>40000000) {
				model.addAttribute("importmessage", RecruitConst.ALREADY_EXIST_IMPORT_ERROR_MSG);
				return "/student_manage";
			}
		}
		List<Student> student =new ArrayList<Student>(); 
		Workbook wb = null;
		try {
			if(fileType.toLowerCase().equals("xlsx")){
				wb = new XSSFWorkbook(clientFile.getInputStream());
			}else{
				wb = new HSSFWorkbook(clientFile.getInputStream());
			}
			// 创建对Excel工作簿文件的引用
			Sheet sheet = wb.getSheetAt(0);
			// 获取到Excel文件中的所有行数
			int rows = sheet.getLastRowNum();
			// 遍历行---忽略标题行
			for (int i = 1; i <= rows; i++) {
				// 读取左上端单元格
				Row row = sheet.getRow(i);
				// 行不为空
				if (row != null) {
					Student stu=stuService.createStudent(row);
				}
			}
			model.addAttribute("importmessage", RecruitConst.ALREADY_EXIST_IMPORT_SUCCESS_MSG);
		} catch (Exception e) {
			model.addAttribute("importmessage", RecruitConst.ALREADY_EXIST_IMPORT_FAILE_MSG);
		}
		return "/student_manage";
	}
}
