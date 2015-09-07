package com.qunar.ops.recruit.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.qunar.ops.recruit.model.PhaseInterviewer;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.model.StudentAssess;
import com.qunar.ops.recruit.model.StudentForm;
import com.qunar.ops.recruit.result.CommonRequest;
import com.qunar.ops.recruit.result.ResultPlusAdditionalInfo;
import com.qunar.ops.recruit.service.PhaseInterviewService;
import com.qunar.ops.recruit.service.StudentAssessService;
import com.qunar.ops.recruit.service.StudentManageService;
import com.qunar.ops.recruit.service.StudentService;
import com.qunar.ops.recruit.util.RecruitConst;
import com.qunar.ops.recruit.util.RecruitControllerUtils;

@Controller
public class StudentManageController {

	@Autowired
	StudentManageService stuService;
	@Autowired
	StudentAssessService assService;
	@Autowired
	StudentService studentService;
	@Autowired
	private PhaseInterviewService interService;
	
	@RequestMapping(value="/hr/importStudentInfos",method=RequestMethod.POST)
	@Transactional
	public String importStaffInfo(@RequestParam("fileField") MultipartFile clientFile,
			HttpServletResponse response, HttpServletRequest request,  RedirectAttributesModelMap  model) {
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
			model.addFlashAttribute("importmessage", RecruitConst.ALREADY_EXIST_IMPORT_ERROR_MSG);
			return "forward:/hr/getAllStudentInfos";
		}else{
			if (clientFile.getSize()!=0 && clientFile.getSize()>40000000) {
				model.addFlashAttribute("importmessage", RecruitConst.ALREADY_EXIST_IMPORT_ERROR_MSG);
				return "forward:/hr/getAllStudentInfos";
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
					student.add(stu);
				}
			}
			int flag=stuService.insertStudentInfo(student);
			if(flag>0){
				model.addFlashAttribute("importmessage", RecruitConst.ALREADY_EXIST_IMPORT_SUCCESS_MSG);
			}else{
				model.addFlashAttribute("importmessage", RecruitConst.ALREADY_EXIST_IMPORT_FAILE_MSG);
			}
		} catch (Exception e) {
			model.addFlashAttribute("importmessage", "出现异常");
			System.out.println(e);
			System.out.println(e.getMessage());
		}
		return "forward:/hr/getAllStudentInfos";
	}
	
	@RequestMapping(value = "/hr/gotoAddStudentInfo")
	public String gotoAddStudentInfo(HttpServletRequest request,  ModelMap model) throws Exception {
		return "/add_student";
	}
	
	@RequestMapping(value = "/hr/gotoUpdStudentInfo")
	public String gotoUpdStudentInfo(HttpServletRequest request,  ModelMap model, int id) throws Exception {
		Student stu=studentService.getStudentById(id);
		model.addAttribute("stu", stu);
		model.addAttribute("interviewTime", stu.getInterviewTime()==null?"":RecruitControllerUtils.dateToStr(stu.getInterviewTime()));
		model.addAttribute("workStart", stu.getWorkStart()==null?"":RecruitControllerUtils.dateToStr(stu.getWorkStart()));
		model.addAttribute("workEnd", stu.getWorkEnd()==null?"":RecruitControllerUtils.dateToStr(stu.getWorkEnd()));
		model.addAttribute("graduateDate", stu.getGraduateDate()==null?"":RecruitControllerUtils.dateToStr(stu.getGraduateDate()));
		model.addAttribute("payTime", stu.getPayTime()==null?"":RecruitControllerUtils.dateToStr(stu.getPayTime()));
		model.addAttribute("refuseDate", stu.getRefuseDate()==null?"":RecruitControllerUtils.dateToStr(stu.getRefuseDate()));
		return "/upd_student";
	}
	
	@RequestMapping(value = "/hr/AddStudentInfo")
	@Transactional
	public String  AddStudentInfo(HttpServletRequest request,@ModelAttribute("doaddform") StudentForm stu,BindingResult results){
		Student result=stuService.makeStudentBy(stu);
		int flag=stuService.insertOneStudentInfo(result);
		return "forward:/hr/getAllStudentInfos";
	}
	
	@RequestMapping(value = "/hr/updStudentInfo")
	@Transactional
	public String  updStudentInfo(HttpServletRequest request,@ModelAttribute("doupdform") StudentForm stu,BindingResult results){
		Student result=stuService.makeUpdStudentBy(stu);
		int flag=stuService.updateOneStudentInfo(result);
		return "forward:/hr/getAllStudentInfos";
	}
	
	@RequestMapping(value = "/hr/deleteStudentInfo")
	@Transactional
	public String  deleteStudentInfo(HttpServletRequest request, int id){
		stuService.deleteStudentInfoBy(id);
		return "forward:/hr/getAllStudentInfos";
	}
	
	@RequestMapping(value = "/hr/lead2StudentPage")
	public String lead2StudentPage(HttpServletRequest request,  ModelMap model) throws Exception {
		return "/student_manage";
	}
	
	/**
	 * 候选人管理
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/hr/getAllStudentInfos")
	public String getAllStudentInfos(HttpServletRequest request,  ModelMap model) throws Exception {
		List<Student> list=new ArrayList<Student>();
		String city=(String) request.getSession().getAttribute("city");
		String year=(String) request.getSession().getAttribute("year");
		String phase=(String) request.getSession().getAttribute("phase");
		if(city!=null && year!=null && phase!=null){
			List<PhaseInterviewer> viewlist =  interService.getSinglecityBy(city,year,phase);
			list=stuService.getAllStudentInfos(city,year,phase);
			List<ResultPlusAdditionalInfo> info=new LinkedList<ResultPlusAdditionalInfo>();
			if(list!=null && list.size()>0){
				for(Student stu:list){
					String str=RecruitControllerUtils.dateToStr(stu.getInterviewTime());
					ResultPlusAdditionalInfo val=new ResultPlusAdditionalInfo();
					val.setObj(stu);
					val.addStringInfo(str);
					info.add(val);
				}
			}
			model.addAttribute("list", info);
			model.addAttribute("viewlist", viewlist);
		}
		return "/student_manage_info";
	}
	
	@RequestMapping(value = "/hr/getStudentInfos")
	public String getStudentInfos(HttpServletRequest request,  ModelMap model, @RequestBody CommonRequest commonRequest) throws Exception {
		List<Student> list=new ArrayList<Student>();
		Map<String, String> vars = commonRequest.getVars();
		String name = vars.get("name");
		String school = vars.get("school");
		String profession = vars.get("profession");
		String state = vars.get("state");
		String city=(String) request.getSession().getAttribute("city");
		String year=(String) request.getSession().getAttribute("year");
		String phase=(String) request.getSession().getAttribute("phase");
		list=stuService.getStudentInofs(name, school, profession, state, city,year,phase);
		List<ResultPlusAdditionalInfo> info=new LinkedList<ResultPlusAdditionalInfo>();
		if(list!=null && list.size()>0){
			for(Student stu:list){
				String str=RecruitControllerUtils.dateToStr(stu.getInterviewTime());
				ResultPlusAdditionalInfo val=new ResultPlusAdditionalInfo();
				val.setObj(stu);
				val.addStringInfo(str);
				info.add(val);
			}
		}
		model.addAttribute("list", info);
		return "/student_manage_info";
	}
	
	@RequestMapping(value = "/hr/gotoSelectStudentInfo")
	public String gotoSelectStudentInfo(HttpServletRequest request, int id,  ModelMap model) throws Exception {
		StudentAssess stuAss=assService.getStudentAssessByStudentId(id);
		Student stu=studentService.getStudentById(id);
		model.addAttribute("stuAss", stuAss);
		model.addAttribute("stu", stu);
		return "/hr_select_student";
	}
	
	@RequestMapping(value = "/hr/AddHrStudentAssess")
	@Transactional
	public String  AddHrStudentAssess(HttpServletRequest request, int id, String hrName, String salay, String hrdetail, String hrConclusion, int sid){
		Student stu=new Student();
		stu.setId(sid);
		stu.setState(hrConclusion);
		StudentAssess sa=new StudentAssess();
		sa.setId(id);
		sa.setHrName(hrName);
		sa.setHrDetailIdea(hrdetail);
		sa.setHrConclusion(hrConclusion);
		if(salay!=null && !"".equals(salay)){
			sa.setHrSuggestSalary(Integer.valueOf(salay));
		}else{
			sa.setHrSuggestSalary(0);
		}
		assService.updateBy(sa);
		studentService.updateStudent(stu);
		return "forward:/hr/getAllStudentInfos";
	}
	
}
