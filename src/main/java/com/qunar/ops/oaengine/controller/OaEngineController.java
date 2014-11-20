package com.qunar.ops.oaengine.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.WorkflowManager;
import com.qunar.ops.oaengine.result.LaborRequest;
import com.qunar.ops.oaengine.result.ListInfo;
import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.result.TaskInfo;
import com.qunar.ops.oaengine.result.TaskResult;
import com.qunar.ops.oaengine.result.WebRequest;
import com.qunar.ops.oaengine.service.DefaultOaEngineService;
import com.qunar.ops.oaengine.service.IOAEngineService;
import com.qunar.ops.oaengine.service.MailSenderService;
import com.qunar.ops.oaengine.result.BaseResult;
import com.qunar.ops.oaengine.result.EmployeeInfo;
import com.qunar.ops.oaengine.result.dailysubmit.TaxiFaresInfo;

@Controller
public class OaEngineController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	protected WorkflowManager manager;
	@Autowired
	protected ProcessEngineFactoryBean processEngine;
	@Autowired
	private MailSenderService mailSenderService;
	@Autowired
	private IOAEngineService ioaEngineService;
	
	@RequestMapping(value = "/oa/test.html")
	public void index(HttpServletRequest request) {
		// mailSenderService.sender("nuby.zhang@qunar.com", new
		// String[]{"nuby.zhang@qunar.com"}, new
		// String[]{"nuby.zhang@qunar.com"}, "", "");
		Request req = new Request();
		req.setAmountMoney(200l);
		req.setDepartment("技术部");
		req.setDepartmentII("OPS");
		req.setOid("001");
		req.setReport2vp(true);
		req.setTbMoney(100l);
		// Object[] startWorkflow = this.manager.startWorkflow("oa_common",
		// "nuby.zhang", req);
		// ListInfo<TaskInfo> todoList = this.manager.todoList("test", "nuby",
		// 0, 10);
		TaskResult pass = this.manager.pass("5913", "nuby.zhang");
		// this.manager.endorse("5", "nuby", "nuby,abc");
		// List<TaskInfo> back = this.manager.back("nuby", "50962", "xxx");
		// this.manager.cancel("test", "001", "nuby.zhang", "reason");
		System.out.println("====");
	}

	@RequestMapping(value = "oa/employeeinfo")
	@ResponseBody
	public BaseResult webEmployeeInfo(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("USER_ID");
		if (userId == null || userId.length() == 0) {
			logger.warn("登陆用户为空，无法获取员工信息");
			return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
		}
		EmployeeInfo employeeInfo = new EmployeeInfo();
		try {
			employeeInfo = ioaEngineService.getEmployeeInfo(userId);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.warn("ACL限制，获取员工信息失败");
			return BaseResult.getErrorResult(-2, "ACL限制，获取员工信息失败");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String result[] = new String[] {
				employeeInfo.getAdName(),
				employeeInfo.getSn(),
				sdf.format(new Date(System.currentTimeMillis())),
				employeeInfo.getDepartmentI(),
				employeeInfo.getDepartmentIV()
		};
		return BaseResult.getSuccessResult(result);
	}
	
	@RequestMapping(value = "oa/laborhour")
	@ResponseBody
	public BaseResult webLaborHour(HttpServletRequest request, @RequestBody LaborRequest laborRequest) {
		String userId = (String) request.getSession().getAttribute("USER_ID");
		if (userId == null || userId.length() == 0) {
			logger.warn("登陆用户为空，无法获取员工信息");
			return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
		}
		String day = laborRequest.getWhichDay();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(day);
		} catch (ParseException e1) {
			e1.printStackTrace();
			String errMsg = "转换时间失败，请检查输入是否为yyyy-MM-dd样式";
			logger.warn(errMsg);
			return BaseResult.getErrorResult(-2, errMsg);
		}
		float laborHour = 0;
		try {
			laborHour = ioaEngineService.getLaborHour(userId, date);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			String errorMsg = "ACL限制，获取员工信息失败";
			logger.warn(errorMsg);
			return BaseResult.getErrorResult(-2, errorMsg);
		}
		String result[] = new String[] {
			String.valueOf(laborHour)
		};
		return BaseResult.getSuccessResult(result);
	}
	
	
	@RequestMapping(value = "oa/data")
	@ResponseBody
	public BaseResult webPostData(HttpServletRequest request, @RequestBody WebRequest webRequest) {
		String userId = (String) request.getSession().getAttribute("USER_ID");
		if (userId == null || userId.length() == 0) {
			logger.warn("登陆用户为空，无法获取员工信息");
			return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
		}
		Map<String, String> vars = webRequest.getVars();
		Map<String, String[]> tableMap = webRequest.getTableMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String table1[] = tableMap.get("table1");
		int len1 = table1.length;
		TaxiFaresInfo[] taxiFaresInfo = new TaxiFaresInfo[len1];
		for (int i = 0; i < len1; i++) {
			taxiFaresInfo[i] = new TaxiFaresInfo();
			taxiFaresInfo[i].setTaxiFaresTime(table1[0]);
			taxiFaresInfo[i].setTaxiFaresAddr(table1[1]);
			taxiFaresInfo[i].setTaxiFaresDest(table1[2]);
			taxiFaresInfo[i].setTaxiFaresTimeNew(table1[3]);
			taxiFaresInfo[i].setTaxiFaresUse(table1[4]);
			taxiFaresInfo[i].setTaxiFaresPeerPeople(table1[5]);
			taxiFaresInfo[i].setTaxiFaresWorkhour(new BigDecimal(table1[6]));
			taxiFaresInfo[i].setComment(table1[7]);
		}
		return BaseResult.getSuccessResult(result);
	}
	
}
