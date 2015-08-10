package com.qunar.ops.oaengine.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qunar.flight.qmonitor.QMonitor;
import com.qunar.ops.oaengine.exception.AgentAlreadyExistsException;
import com.qunar.ops.oaengine.exception.CompareModelException;
import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.exception.ManagerFormException;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.DelegationManager;
import com.qunar.ops.oaengine.manager.Form0114Manager;
import com.qunar.ops.oaengine.manager.GroupManager;
import com.qunar.ops.oaengine.manager.LogManager;
import com.qunar.ops.oaengine.manager.LoginManager;
import com.qunar.ops.oaengine.manager.WorkflowManager;
import com.qunar.ops.oaengine.model.Delegation;
import com.qunar.ops.oaengine.model.Files;
import com.qunar.ops.oaengine.model.FormApproveLog;
import com.qunar.ops.oaengine.result.BaseResult;
import com.qunar.ops.oaengine.result.CommonRequest;
import com.qunar.ops.oaengine.result.DataResult;
import com.qunar.ops.oaengine.result.EmployeeInfo;
import com.qunar.ops.oaengine.result.FormRequest;
import com.qunar.ops.oaengine.result.ListInfo;
import com.qunar.ops.oaengine.result.dailysubmit.ApprovalInfo;
import com.qunar.ops.oaengine.result.dailysubmit.EmployeeRelationsFeesInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfoList;
import com.qunar.ops.oaengine.result.dailysubmit.HospitalityInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OtherCostsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OvertimeMealsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.TaxiFaresInfo;
import com.qunar.ops.oaengine.service.EmployeeInfoService;
import com.qunar.ops.oaengine.service.IOAEngineService;
import com.qunar.ops.oaengine.service.MailSenderService;
import com.qunar.ops.oaengine.service.PaymentService;
import com.qunar.ops.oaengine.task.PaymentQscheduleService;
import com.qunar.ops.oaengine.util.OAControllerUtils;
import com.qunar.ops.oaengine.util.OAEngineConst;
import com.qunar.ops.oaengine.util.QUtils;

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
	@Autowired
	private GroupManager groupManager;
	@Autowired
	private DelegationManager delegationManager;
	@Autowired
	private LogManager logManager;
	@Autowired
	private LoginManager loginManager;
	@Autowired
	private WorkflowManager workflowManager;
	@Autowired
	private Form0114Manager form0114Manager;
	@Autowired
	private EmployeeInfoService employeeInfoService;
	private String processKey = "oa_common";

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	protected PaymentService paymentService;
	
	@Autowired
	protected PaymentQscheduleService paymentQscheduleService;
	
	/**
	 * 修改登陆的用户名
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/user")
	@ResponseBody
	public BaseResult changeRootUser(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest, HttpServletResponse response) {
		Map<String, String> vars = commonRequest.getVars();
		String userId = vars.get("user");
		EmployeeInfo employeeInfo;
		try {
			employeeInfo = ioaEngineService.getEmployeeInfo(userId);
			QUtils.setUsername(response, "un", userId.toLowerCase(), true);
			QUtils.setUsername(response, "name", employeeInfo.getAdName(), true);
			QUtils.setUsername(response, "test-userid", userId.toLowerCase(), false);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			BaseResult.getSuccessResult("");
		}
		return BaseResult.getSuccessResult("");
	}
	
	/**
	 * 退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/logout")
	@ResponseBody
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		QUtils.setUsername(response, "un", null, true);
		QUtils.setUsername(response, "name", null, true);
		QUtils.setUsername(response, "test-userid", null, false);
		return welcom(request, null);
	}

	/**
	 * login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response2) {
		/*
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		String login = null;
		if(username != null && password != null){
			login = loginManager.login(username, password);
			if(login == null){
				EmployeeInfo employeeInfo;
				try {
					employeeInfo = ioaEngineService.getEmployeeInfo(username);
					String departmentI = employeeInfo.getDepartmentI();
					if(!"技术部".equals(departmentI) && !"财务部".equals(departmentI)){
						return welcom(request, "本系统目前只对技术部员工开放，报销请移驾<a href='http://oa.corp.qunar.com'>OA</a>");
					}
				} catch (RemoteAccessException e) {
					e.printStackTrace();
					logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
					return welcom(request, login);
				}
				QUtils.setUsername(response2, "un", username, true);
				QUtils.setUsername(response2, "name", employeeInfo.getAdName(), true);
				QUtils.setUsername(response2, "test-userid", username, false);
				return myApplyTodo(request);
			}
		}
		*/
		String login = null;
		HttpClient client = HttpClientBuilder.create().build();
		String token = request.getParameter("token");
		HttpGet method = new HttpGet(
				"http://qsso.corp.qunar.com/api/verifytoken.php?token=" + token);
		try {
			HttpResponse response = client.execute(method);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			JSONObject parseObject = JSON.parseObject(result.toString());
			String ret = parseObject.getString("ret");
			if (ret.equals("true")) {
				String userId = parseObject.getJSONObject("data").getString("userId");
				String adname = parseObject.getJSONObject("data").getJSONObject("userInfo").getString("ad_cn");
				//JSONArray dept = parseObject.getJSONObject("data").getJSONObject("userInfo").getJSONArray("dept");
				//String departmentI = dept.getString(0);
				EmployeeInfo employee = this.employeeInfoService.getEmployee(userId);
				String departmentI = employee.getDepartmentI();
				if(!"技术部".equals(departmentI) 
						&& !"财务部".equals(departmentI)
						&& !"内审部".equals(departmentI)
						&& !"旅游度假事业部".equals(departmentI)
						&& !"门票事业部".equals(departmentI)
						&& !"机票事业群".equals(departmentI)
						&& !"无线事业群".equals(departmentI)
						&& !"项目管理组".equals(departmentI)
						&& !"目的地服务事业部".equals(departmentI)
						&& !"liangliang.dou".equals(userId)
						&& !"cc.zhuang".equals(userId)
						&& !"sam.sum".equals(userId)
						&& !"tao.luo".equals(userId)
						&& !"wangtao.wang".equals(userId)
						){
					return welcom(request,"[只对以上部门开通权限，如有疑问请QTalk:lee.guo]");
				}
				QUtils.setUsername(response2, "un", userId.toLowerCase(), true);
				QUtils.setUsername(response2, "name", adname, true);
				QUtils.setUsername(response2, "test-userid", userId.toLowerCase(), false);
				QUtils.setUsername(response2, "cookie-time", System.currentTimeMillis()+"", false);
				return myApplyTodo(request);
			}
		} catch (Exception e) {
			logger.error("sso 验证失败", e);
			login = "sso 验证失败" + e.getMessage();
		}
		
		return welcom(request, login);
	}

	/**
	 * index
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/index.html")
	public ModelAndView welcom(HttpServletRequest request, String message) {
		ModelAndView mav = new ModelAndView("/oa/index");
		mav.addObject("message", message==null?"":message);
		mav.addObject("debug", OAControllerUtils.isDebug());
		return mav;
	}

	/**
	 * 我的申请报销页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/apply.html")
	public ModelAndView addApply(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("oa/apply");
		//String userId = (String) request.getSession().getAttribute("USER_ID");
		String userId = QUtils.getUsername(request);
		List<String[]> loans = new ArrayList<String[]>();
		try {
			loans = this.ioaEngineService.getLoans(userId);
		} catch (RemoteAccessException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
		mav.addObject("debug", OAControllerUtils.isDebug());
		mav.addObject("loans", loans);
		mav.addObject("formId", request.getParameter("formId")==null?"":request.getParameter("formId"));
		mav.addObject("taskId", request.getParameter("taskId")==null?"":request.getParameter("taskId"));
		mav.addObject("euid", request.getParameter("euid")==null?"":request.getParameter("euid"));
		return mav;
	}
	
	/**
	 * 综合查询页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/search.html")
	public ModelAndView search(HttpServletRequest request) {
		String userId = QUtils.getUsername(request);
		boolean inGroups = this.groupManager.inGroups(new String[] {"fin_check", "cashier"}, userId);
		ModelAndView mav = null;
		if(inGroups || OAControllerUtils.isDebug()){
			mav = new ModelAndView("oa/search");
		}else{
			mav = new ModelAndView("oa/forbidden");
		}
		return mav;
	}

	/**
	 * 核定页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/apply_ratify.html")
	public ModelAndView addApplyRatify(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("oa/apply_ratify");
		String userId = QUtils.getUsername(request);
		List<String[]> loans = new ArrayList<String[]>();
		try {
			loans = this.ioaEngineService.getLoans(userId);
		} catch (RemoteAccessException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
		mav.addObject("debug", OAControllerUtils.isDebug());
		mav.addObject("loans", loans);
		mav.addObject("formId", request.getParameter("formId"));
		mav.addObject("taskId", request.getParameter("taskId"));
		mav.addObject("dateFrom", request.getParameter("dateFrom"));
		mav.addObject("dateTo", request.getParameter("dateTo"));
		//mav.addObject("approveUser", new String(request.getParameter("approveUser").getBytes("iso-8859-1"),"UTF-8"));
		mav.addObject("approveUser", request.getParameter("approveUser"));
		String tk = this.workflowManager.getTaskKey(request.getParameter("taskId"));
		List<FormApproveLog> listPass=logManager.formAppreoveLogAllPassByFormId(
				Long.parseLong(request.getParameter("formId").toString()),tk,"pass");
		String approveUser="";
		
		//保存退回人的rtx_id
		List<String> backMemberArray = new ArrayList<String>();
		if(listPass!=null && listPass.size()>0) {
			for(int i=0;i<listPass.size();i++){
				//退回人去重
				if(approveUser.equals(listPass.get(i).getApproveUser())){
						continue;
				}
				String msg = listPass.get(i).getApproveUser()
						+"("+listPass.get(i).getApproveCname()+")";
				backMemberArray.add(msg);
				approveUser=listPass.get(i).getApproveUser();
			}
		}
		mav.addObject("backMemberArray",backMemberArray);
		return mav;
	}

	/**
	 * 我的申请，已经在流程中的页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/apply_todo.html")
	public ModelAndView myApplyTodo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("oa/apply_todo");
		mav.addObject("debug", OAControllerUtils.isDebug());
		return mav;
	}

	/**
	 * 我的申请，已经审批结束的页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/apply_history.html")
	public ModelAndView myApplyHistory(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("oa/apply_history");
		mav.addObject("debug", OAControllerUtils.isDebug());
		return mav;
	}
	

	/**
	 * 待审批
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/approve_todo.html")
	public ModelAndView approveTodo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("oa/approve_todo");
		mav.addObject("debug", OAControllerUtils.isDebug());
		String nowUserId = QUtils.getUsername(request);
		//获取当前用户是否为fin_check里的成员
	    Boolean  nowFlag=this.groupManager.inGroups(new String[] {"fin_check"}, nowUserId) ;
		mav.addObject("nowFlag",nowFlag);
		return mav;
	}

	/**
	 * 已审批
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/approve_history.html")
	public ModelAndView approveHistory(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("oa/approve_history");
		mav.addObject("debug", OAControllerUtils.isDebug());
		return mav;
	}

	/**
	 * 我的草稿
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/draft.html")
	public ModelAndView myDraft(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("oa/draft");
		mav.addObject("debug", OAControllerUtils.isDebug());
		return mav;
	}
	
	/**
	 * 设置代理人
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/setting/delegation.html")
	public ModelAndView delegation(HttpServletRequest request) {
		String userId = QUtils.getUsername(request);
		List<Delegation> users = delegationManager.findDelegationByMaster(userId);
		ModelAndView mav = new ModelAndView("oa/delegation");
		mav.addObject("debug", OAControllerUtils.isDebug());
		mav.addObject("users", users);
		return mav;
	}
	
	/**
	 * 增加代理人
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/setting/add_delegation")
	@ResponseBody
	public BaseResult AddDelegation(HttpServletRequest request, @RequestBody CommonRequest commonRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		String delegation = vars.get("delegation");
		if(delegation != null){
			try {
				EmployeeInfo einfo = this.ioaEngineService.getEmployeeInfo(delegation);
				if(einfo == null || einfo.getEnable() == 0){
					return BaseResult.getErrorResult(-1, "代理人没有找到，或已经离职");
				}
			} catch (RemoteAccessException e1) {
				e1.printStackTrace();
				return BaseResult.getErrorResult(-1, "代理人没有找到，或已经离职");
			}
			List<String> list = new ArrayList<String>();
			list.add(delegation);
			try {
				this.ioaEngineService.appendCandidate(processKey, userId, list);
			} catch (AgentAlreadyExistsException e) {
				return BaseResult.getErrorResult(-1, e.getMessage());
			}
			//this.delegationManager.appendDelegation(userId, list);
		}
		return BaseResult.getSuccessResult(null);
	}
	
	/**
	 * 删除代理人
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/setting/remove_delegation")
	@ResponseBody
	public BaseResult RemoveDelegation(HttpServletRequest request, @RequestBody CommonRequest commonRequest) {
		//String userId = (String) request.getSession().getAttribute("USER_ID");
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		String delegation = vars.get("delegation");
		if(delegation != null){
			List<String> list = new ArrayList<String>();
			list.add(delegation);
			this.ioaEngineService.removeCandidate(processKey, userId, list);
		}
		return BaseResult.getSuccessResult(null);
	}

	/**
	 * 我的申请页面需要获取的基本信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/employeeinfo")
	@ResponseBody
	public BaseResult webEmployeeInfo(HttpServletRequest request) {
		//String userId = (String) request.getSession().getAttribute("USER_ID");
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		EmployeeInfo employeeInfo;
		try {
			employeeInfo = ioaEngineService.getEmployeeInfo(userId);
			EmployeeInfo bankInfo = employeeInfoService.getEmployeeBankInfo(userId);
			employeeInfo.setBankCardNo(bankInfo.getBankCardNo());
			employeeInfo.setBankCity(bankInfo.getBankCity());
			employeeInfo.setBankName(bankInfo.getBankName());
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		String dep = employeeInfo.getDepartmentV();
		if(dep == null || dep.length() == 0){
			dep = employeeInfo.getDepartmentIV();
		}
		if(dep == null || dep.length() == 0){
			dep = employeeInfo.getDepartmentIII();
		}
		if(dep == null || dep.length() == 0){
			dep = employeeInfo.getDepartmentII();
		}
		if(dep == null || dep.length() == 0){
			dep = employeeInfo.getDepartmentI();
		}
		String result[] = new String[] {
				employeeInfo.getAdName(),
				employeeInfo.getSn(),
				OAControllerUtils.dateToStr(new Date(System.currentTimeMillis())),
				employeeInfo.getDepartmentI(), 
				dep,"",
				employeeInfo.getBankCardNo(),
				employeeInfo.getBankName()};
		return BaseResult.getSuccessResult(result);
	}
	

	/**
	 * 获取工时
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/labor")
	@ResponseBody
	public BaseResult webLaborHour(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		String day = vars.get("day");
		Date date;
		try {
			date = sdf.parse(day);
		} catch (ParseException e1) {
			e1.printStackTrace();
			logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
			return BaseResult.getErrorResult(OAEngineConst.DATE_FORMAT_ERROR,
					OAEngineConst.DATE_FORMAT_ERROR_MSG);
		}
		float laborHour = 0;
		try {
			laborHour = ioaEngineService.getLaborHour(userId, date);
		} catch (RemoteAccessException e) {
			logger.warn(e.getMessage(), e);
			laborHour = 0;
		}
		String result[] = new String[] { String.valueOf(laborHour) };
		return BaseResult.getSuccessResult(result);
	}
	
	/**
	 * 批量获取工时
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/batch_labor")
	@ResponseBody
	public BaseResult webBatchLaborHour(HttpServletRequest request, @RequestBody CommonRequest commonRequest) {
		String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long scope = 100;
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL, OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		String start = vars.get("start");
		String end = vars.get("end");
		DateTime startDate = DateTime.parse(start);
		DateTime endDate = DateTime.parse(end);
		long entryStartScope = 100;
		long entryEndScope = 100;
		try {
			 entryStartScope =(format.parse(nowDate).getTime()-format.parse(start).getTime())/(24*60*60*1000)+1;
			 entryEndScope =(format.parse(end).getTime()-format.parse(nowDate).getTime())/(24*60*60*1000)+1;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if(startDate!=null&&endDate!=null){
			if(scope > entryStartScope && entryStartScope >= 0){
					List<String> infos = new ArrayList<String>();// laborHour = 0;
					int days = Days.daysBetween(startDate, endDate).getDays();
					for(int i=0; i<=days; i++){
						try {
							DateTime date = startDate.plusDays(i);
							float laborHour = ioaEngineService.getLaborHour(userId, date.toDate());
							if(laborHour >= 11.5){
								infos.add(date.toString("yyyy-MM-dd")+":"+laborHour);
							}
						} catch (RemoteAccessException e) {
							if(!"今日没有打卡数据".equals(e.getMessage().trim()))e.printStackTrace();
							//logger.error(e.getMessage());
							continue;
						}
					}
					return BaseResult.getSuccessResult(infos);
				}
		}
		return BaseResult.getErrorResult(-1, "时间太过久远，请保持三个月范围内。");
	}

	/**
	 * 报销页面传到后端信息,存至各个表
	 * 
	 * @param request
	 * @param formRequest
	 * @return
	 */
	@RequestMapping(value = "oa/ratify")
	@ResponseBody
	public BaseResult Ratify(HttpServletRequest request,
			@RequestBody FormRequest formRequest) {
		//String userId = (String) request.getSession().getAttribute("USER_ID");
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		String cname = QUtils.getAdname(request);
		Map<String, String> vars = formRequest.getVars();
		Map<String, String[][]> tableMap = formRequest.getTableMap();
		boolean isRatify = true;
		boolean createFlag;
		FormInfo formInfo = new FormInfo();
		try {
			createFlag = constructFormInfo(formInfo, tableMap, vars, isRatify);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
			return BaseResult.getErrorResult(OAEngineConst.DATE_FORMAT_ERROR,
					OAEngineConst.DATE_FORMAT_ERROR_MSG);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
			return BaseResult.getErrorResult(-1, e.getMessage());
		}
		if (!createFlag) {
			String errorMsg = "没有任何报销内容，请检查";
			logger.warn(errorMsg);
			return BaseResult.getErrorResult(-1, errorMsg);
		}
		try {
			ioaEngineService.updateFormInfo(processKey, userId, cname, "" + formInfo.getId(), formInfo, true);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(OAEngineConst.ACL_LIMIT_ERROR,
					OAEngineConst.ACL_LIMIT_ERROR_MSG);
		} catch (CompareModelException e) {
			e.printStackTrace();
			logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
			return BaseResult.getErrorResult(OAEngineConst.DATE_FORMAT_ERROR,
					OAEngineConst.DATE_FORMAT_ERROR_MSG);
		} catch (FormNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(
					OAEngineConst.FORM_NOT_FOUND_ERROR,
					OAEngineConst.FORM_NOT_FOUND_ERROR_MSG);
		} catch (ManagerFormException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(OAEngineConst.MANAGER_LOCK_ERROR,
					OAEngineConst.MANAGER_LOCK_ERROR_MSG);
		}
		return BaseResult.getSuccessResult("0");
	}

	/**
	 * 报销页面传到后端信息,存至各个表
	 * 
	 * @param request
	 * @param formRequest
	 * @return
	 */
	@RequestMapping(value = "oa/data")
	@ResponseBody
	public BaseResult webPostData(HttpServletRequest request,
			@RequestBody FormRequest formRequest) {
		//String userId = (String) request.getSession().getAttribute("USER_ID");
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		String cname = QUtils.getAdname(request);
		Map<String, String> vars = formRequest.getVars();
		Map<String, String[][]> tableMap = formRequest.getTableMap();
		boolean isRatify = false;
		boolean createFlag;
		FormInfo formInfo = new FormInfo();
		try {
			createFlag = constructFormInfo(formInfo, tableMap, vars, isRatify);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
			return BaseResult.getErrorResult(-1, e.getMessage());
		} catch(NumberFormatException e){
			e.printStackTrace();
			logger.warn(e.getMessage());
			return BaseResult.getErrorResult(-1, e.getMessage());
		}
		if (!createFlag) {
			String errorMsg = "没有任何报销内容，请检查";
			logger.warn(errorMsg);
			return BaseResult.getErrorResult(-1, errorMsg);
		}
		// 这里的flag是判断是已经开始还是保存草稿
		boolean flag = formRequest.isFlag();

		long id = 0;
		if (formInfo.getId() == null) {
			try {
				EmployeeInfo info = this.ioaEngineService.getEmployeeInfo(userId);
				if (info != null) {
					formInfo.setFirstDep(info.getDepartmentI());
					formInfo.setSecDep(info.getDepartmentII());
					formInfo.setThridDep(info.getDepartmentIII());
					formInfo.setFourthDep(info.getDepartmentIV());
					formInfo.setFivethDep(info.getDepartmentV());
					formInfo.setCompany(info.getCompany());
				}
				EmployeeInfo bankInfo = this.employeeInfoService.getEmployeeBankInfo(userId);
				formInfo.setBankCity(bankInfo.getBankCity());
				formInfo.setBankName(bankInfo.getBankName());
				formInfo.setBankNumber(bankInfo.getBankCardNo());
			} catch (RemoteAccessException e) {
				e.printStackTrace();
				logger.warn(e.getMessage());
			}
		}
		if(formInfo.getId() != null){
			FormInfo _info = null;
			try {
				_info = this.ioaEngineService.getFormInfo(null, null, ""+formInfo.getId());
			} catch (FormNotFoundException e) {
			}
			if(_info == null){
				String errorMsg = "没有任何报销内容";
				logger.warn(errorMsg);
				return BaseResult.getErrorResult(-1, errorMsg);
			}
			if(!this.groupManager.inGroups(new String[] {"fin_check"}, userId) && !userId.equals(_info.getStartMemberId())){
				String errorMsg = "您无权修改此申请";
				logger.warn(errorMsg);
				return BaseResult.getErrorResult(-1, errorMsg);
			}
		}
		if (flag) {
			try {
				if (formInfo.getId() != null) {
					ioaEngineService.updateFormInfo(processKey, userId, cname, ""
							+ formInfo.getId(), formInfo, true);
				} else {
					//创建工单并发起流程
					ioaEngineService.createFormAndstart(processKey, userId, cname, formInfo);
				}
			} catch (RemoteAccessException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return BaseResult.getErrorResult(OAEngineConst.ACL_LIMIT_ERROR,
						OAEngineConst.ACL_LIMIT_ERROR_MSG);
			} catch (CompareModelException e) {
				e.printStackTrace();
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			} catch (FormNotFoundException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return BaseResult.getErrorResult(
						OAEngineConst.FORM_NOT_FOUND_ERROR,
						OAEngineConst.FORM_NOT_FOUND_ERROR_MSG);
			} catch (ManagerFormException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return BaseResult.getErrorResult(
						OAEngineConst.MANAGER_LOCK_ERROR,
						OAEngineConst.MANAGER_LOCK_ERROR_MSG);
			}
		} else {//暂存
			if (formInfo.getId() != null) {
				try {
					ioaEngineService.updateFormInfo(processKey, userId, cname, ""
							+ formInfo.getId(), formInfo, false);
				} catch (CompareModelException e) {
					e.printStackTrace();
					logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
					return BaseResult.getErrorResult(
							OAEngineConst.DATE_FORMAT_ERROR,
							OAEngineConst.DATE_FORMAT_ERROR_MSG);
				} catch (FormNotFoundException e) {
					e.printStackTrace();
					logger.error(e.getMessage());
					return BaseResult.getErrorResult(
							OAEngineConst.FORM_NOT_FOUND_ERROR,
							OAEngineConst.FORM_NOT_FOUND_ERROR_MSG);
				} catch (RemoteAccessException e) {
					e.printStackTrace();
					logger.error(e.getMessage());
					return BaseResult.getErrorResult(
							OAEngineConst.ACL_LIMIT_ERROR,
							OAEngineConst.ACL_LIMIT_ERROR_MSG);
				} catch (ManagerFormException e) {
					e.printStackTrace();
					logger.error(e.getMessage());
					return BaseResult.getErrorResult(
							OAEngineConst.MANAGER_LOCK_ERROR,
							OAEngineConst.MANAGER_LOCK_ERROR_MSG);
				}
			} else {
				ioaEngineService.createForm(processKey, userId, formInfo);
			}
		}
		return BaseResult.getSuccessResult(String.valueOf(formInfo.getId()));
	}

	/**
	 * 草稿箱中的申请
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/draft")
	@ResponseBody
	public BaseResult getAllDraftInfos(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		int noSize[] = OAControllerUtils.getPageNoAndSize(vars);
		int pageSize = noSize[0];
		int pageNo = noSize[1];
		FormInfoList formInfoList = ioaEngineService.getUserDraftList(
				processKey, userId, pageNo, pageSize);
		DataResult dataResult;
		try {
			dataResult = getAllTableInfos(formInfoList, userId, 5);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(OAEngineConst.ACL_LIMIT_ERROR,
					OAEngineConst.ACL_LIMIT_ERROR_MSG);
		}
		return BaseResult.getSuccessResult(dataResult);
	}

	/**
	 * 获取报销详情
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/apply_info")
	@ResponseBody
	public BaseResult getDraftDetailInfo(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		String formId = vars.get("formId");
		FormInfo formInfo;
		String[] approveInfo;
		try {
			formInfo = ioaEngineService.getFormInfo(processKey, userId, formId);
			if(formInfo == null){
				logger.warn(userId+"没有找到申请："+formId);
				return BaseResult.getErrorResult(-1, "申请未找到");
			} 
			/*if(!this.groupManager.inGroups(new String[] {"fin_check", "fin_check_mdd" }, userId) 
					&& !userId.equals(formInfo.getStartMemberId())){
				logger.warn(userId+"无权查看"+formInfo.getStartMemberId()+"申请");
				return BaseResult.getErrorResult(-1, "您无权查看此申请");
			}*/
			approveInfo = this._getApproveInfo(processKey, formId);
		} catch (FormNotFoundException e) {
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(OAEngineConst.FORM_NOT_FOUND_ERROR, OAEngineConst.FORM_NOT_FOUND_ERROR_MSG);
		}
		FormRequest formRequest = getApplyDetailInfo(formInfo, approveInfo);
		return BaseResult.getSuccessResult(formRequest);
	}

	/**
	 * 正在审批中的申请
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/todo")
	@ResponseBody
	public BaseResult getAllMyApplyTodoList(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		int noSize[] = OAControllerUtils.getPageNoAndSize(vars);
		int pageSize = noSize[0];
		int pageNo = noSize[1];
		String startTime = vars.get("startTime");
		String endTime = vars.get("endTime");
		Date _startTime = null;
		if (startTime != null) {
			try {
				_startTime = sdf.parse(startTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		Date _endTime = null;
		if (endTime != null) {
			try {
				_endTime = sdf.parse(endTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		FormInfoList formInfoList = ioaEngineService.getUserApplyList(
				processKey, userId, _startTime, _endTime, pageNo, pageSize);
		DataResult dataResult;
		try {
			dataResult = getAllTableInfos(formInfoList, userId, 1);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(OAEngineConst.ACL_LIMIT_ERROR,
					OAEngineConst.ACL_LIMIT_ERROR_MSG);
		}
		return BaseResult.getSuccessResult(dataResult);
	}
	
	
	/**
	 * 复制到草稿
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/clone")
	@ResponseBody
	public BaseResult cloneToDraft(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		String formId = vars.get("formId");
		try {
			this.ioaEngineService.cloneToDraft(userId, formId);
		} catch (FormNotFoundException e) {
			return BaseResult.getErrorResult(-1, e.getMessage());
		} catch (ManagerFormException e) {
			return BaseResult.getErrorResult(-1, e.getMessage());
		}
		return BaseResult.getSuccessResult("");
	}

	/**
	 * 已经结束的申请
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/history")
	@ResponseBody
	public BaseResult getAllMyApplyHistoryList(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		//String userId = (String) request.getSession().getAttribute("USER_ID");
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		int noSize[] = OAControllerUtils.getPageNoAndSize(vars);
		int pageSize = noSize[0];
		int pageNo = noSize[1];

		String startTime = vars.get("startTime");
		String endTime = vars.get("endTime");
		Date _startTime = null;
		if (startTime != null) {
			try {
				_startTime = sdf.parse(startTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		Date _endTime = null;
		if (endTime != null) {
			try {
				_endTime = sdf.parse(endTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		FormInfoList formInfoList = ioaEngineService.getUserApplyHisList(
				processKey, userId, _startTime, _endTime, pageNo, pageSize);
		DataResult dataResult;
		try {
			dataResult = getAllTableInfos(formInfoList, userId, 4);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(OAEngineConst.ACL_LIMIT_ERROR,
					OAEngineConst.ACL_LIMIT_ERROR_MSG);
		}
		return BaseResult.getSuccessResult(dataResult);
	}

	/**
	 * 待审批
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/approve_todo")
	@ResponseBody
	public BaseResult getAllApproveTodoList(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		// 默认一页显示50条数据
		Map<String, String> vars = commonRequest.getVars();
		int noSize[] = OAControllerUtils.getPageNoAndSize(vars);
		int pageSize = noSize[0];
		int pageNo = noSize[1];
		String startTime = vars.get("startTime");
		String endTime = vars.get("endTime");
		String approve_user = vars.get("approveUser");
		String fids = vars.get("fids");
		Map<String, Boolean> fidMap = new HashMap<String, Boolean>();
		if(fids != null){
			startTime = null;
			endTime = null;
			pageSize = Integer.MAX_VALUE;
			pageNo = 1;
			for(String id : fids.split(",")){
				fidMap.put(id, true);
			}
		}
		Date _startTime = null;
		if (startTime != null) {
			try {
				_startTime = sdf.parse(startTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		Date _endTime = null;
		if (endTime != null) {
			try {
				_endTime = sdf.parse(endTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		FormInfoList formInfoList = null;
		try {
			formInfoList = ioaEngineService.todoList(processKey, userId, _startTime, _endTime, approve_user, pageNo, pageSize);
			if(fids != null){
				List<FormInfo> infos = new ArrayList<FormInfo>();
				for(FormInfo info : formInfoList.getFormInfos()){
					if(fidMap.containsKey(""+info.getId())){
						infos.add(info);
					}
				}
				formInfoList.setFormInfos(infos);
			}
		} catch (FormNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(
					OAEngineConst.FORM_NOT_FOUND_ERROR,
					OAEngineConst.FORM_NOT_FOUND_ERROR_MSG);
		}
		DataResult dataResult;
		try {
			dataResult = getAllTableInfos(formInfoList, userId, 2);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(OAEngineConst.ACL_LIMIT_ERROR,
					OAEngineConst.ACL_LIMIT_ERROR_MSG);
		}
		return BaseResult.getSuccessResult(dataResult);
	}
	
	
	/**
	 * 解析excel，获得formId串
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/parse_xls")
	@ResponseBody
	public BaseResult getAllApproveTodoList(@RequestParam(value = "file", required = false) MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String res = "";
		try {
			InputStream fileInputStream = file.getInputStream();
			String extension = FilenameUtils.getExtension(fileName);
			if (extension.equals("xls")) {
				HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);
				HSSFSheet sheet = wb.getSheetAt(0);
				for(int idx = 2; idx <= sheet.getLastRowNum(); idx++){
					HSSFRow row = sheet.getRow(idx);
					if(row == null) continue;
					HSSFCell cell = row.getCell(16);
					if(cell == null) continue;
					int fid = (int)cell.getNumericCellValue();
					res += ""+fid+",";
				}
				wb.close();
			}
			if(res.length() > 0) res = res.substring(0, res.length()-1);
		} catch (Exception e) {
			logger.error("error on deploy process, because of file input stream", e);
		}
		return BaseResult.getSuccessResult(res);
	}
	
	/**
	 * 待审批导出
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/approve_todo_export")
	public void getAllApproveTodoListExport(HttpServletRequest request, HttpServletResponse response) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		int pageSize = Integer.MAX_VALUE;
		int pageNo = 0;
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String approve_user = request.getParameter("approve-user");
		Date _startTime = null;
		if (startTime != null) {
			try {
				_startTime = sdf.parse(startTime);
			} catch (ParseException e) {
			}
		}
		Date _endTime = null;
		if (endTime != null) {
			try {
				_endTime = sdf.parse(endTime);
			} catch (ParseException e) {
			}
		}
		FormInfoList formInfoList = null;
		try {
			formInfoList = ioaEngineService.todoList(processKey, userId, _startTime, _endTime, approve_user, pageNo, pageSize);
		} catch (FormNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		File file = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			HSSFWorkbook wb = new HSSFWorkbook();  
			HSSFSheet sheet = wb.createSheet("sheet1");  
			// 设置excel每列宽度  
		    sheet.setColumnWidth(0, 4000);  
		    sheet.setColumnWidth(1, 3500);  
		  
		    // 创建字体样式  
		    HSSFFont font = wb.createFont();  
		    font.setFontName("Verdana");  
		    font.setBoldweight((short) 100);  
		    font.setFontHeight((short) 300);  
		  
		    // 创建单元格样式  
		    HSSFCellStyle style = wb.createCellStyle();  
		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
		  
		    // 设置边框  
		    //style.setBottomBorderColor(HSSFColor.RED.index);  
		    //style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
		    //style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
		    //style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
		    //style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		  
		    style.setFont(font);// 设置字体 
		    
		    // 创建Excel的sheet的一行  
		    HSSFRow row = sheet.createRow(0);  
		    row.setHeight((short) 500);// 设定行的高度  
		    // 创建一个Excel的单元格  
		    HSSFCell cell = row.createCell(0);  
		  
		    // 合并单元格(startRow，endRow，startColumn，endColumn)  
		    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 27));  
		  
		    // 给Excel的单元格设置样式和赋值  
		    cell.setCellStyle(style);  
		    cell.setCellValue("日常费用报销查询-"+sdf.format(new Date()));  
		  
		    // 设置单元格内容格式  
		    //HSSFCellStyle style1 = wb.createCellStyle(); 
		  
		    row = sheet.createRow(1);  
		    cell = row.createCell(0);  
		    cell.setCellValue("RTX_ID");
		    cell = row.createCell(1);  
		    cell.setCellValue("部门编号");
		    cell = row.createCell(2);  
		    cell.setCellValue("一级部门");
		    cell = row.createCell(3);  
		    cell.setCellValue("二级部门");
		    cell = row.createCell(4);  
		    cell.setCellValue("三级部门");
		    cell = row.createCell(5);  
		    cell.setCellValue("四级部门");
		    cell = row.createCell(6);  
		    cell.setCellValue("五级部门");
		    cell = row.createCell(7);  
		    cell.setCellValue("人员编号");
		    cell = row.createCell(8);  
		    cell.setCellValue("申请人");
		    cell = row.createCell(9);  
		    cell.setCellValue("银行卡号");
		    cell = row.createCell(10);  
		    cell.setCellValue("开户银行");
		    cell = row.createCell(11);  
		    cell.setCellValue("开户所在地");
		    cell = row.createCell(12);  
		    cell.setCellValue("申请日期");
		    cell = row.createCell(13);  
		    cell.setCellValue("借款单流水号");
		    cell = row.createCell(14);  
		    cell.setCellValue("是否有借款");
		    cell = row.createCell(15);  
		    cell.setCellValue("借款金额");
		    cell = row.createCell(16);  
		    cell.setCellValue("流水号");
		    cell = row.createCell(17);  
		    cell.setCellValue("金额总计");
		    cell = row.createCell(18);  
		    cell.setCellValue("通信费金额总计");
		    cell = row.createCell(19);  
		    cell.setCellValue("其他费用金额总计");
		    cell = row.createCell(20);  
		    cell.setCellValue("加班餐费金额总计");
		    cell = row.createCell(21);  
		    cell.setCellValue("招待费金额总计");
		    cell = row.createCell(22);  
		    cell.setCellValue("员工关系费金额总计");
		    cell = row.createCell(23);  
		    cell.setCellValue("出租车费金额总计");
		    cell = row.createCell(24);  
		    cell.setCellValue("财务确认总计");
		    cell = row.createCell(25);  
		    cell.setCellValue("财务审核签字时间");
		    cell = row.createCell(26);  
		    cell.setCellValue("出纳办理签字");
		    cell = row.createCell(27);  
		    cell.setCellValue("出纳办理签字日期");
		    
		    int no = 2;
		    if(formInfoList != null)for(FormInfo info : formInfoList.getFormInfos()){
		    	row = sheet.createRow(no);  
			    cell = row.createCell(0);  
			    cell.setCellValue(info.getStartMemberId());
			    cell = row.createCell(1);  
			    cell.setCellValue("");
			    cell = row.createCell(2);  
			    cell.setCellValue(info.getFirstDep());
			    cell = row.createCell(3);  
			    cell.setCellValue(info.getSecDep());
			    cell = row.createCell(4);  
			    cell.setCellValue(info.getThridDep());
			    cell = row.createCell(5);  
			    cell.setCellValue(info.getFourthDep());
			    cell = row.createCell(6);  
			    cell.setCellValue(info.getFivethDep());
			    cell = row.createCell(7);  
			    cell.setCellValue(info.getSerialNumber());
			    cell = row.createCell(8);  
			    cell.setCellValue(info.getApplyUser());
			    cell = row.createCell(9);  
			    cell.setCellValue(info.getBankNumber());
			    cell = row.createCell(10);  
			    cell.setCellValue(info.getBankName());
			    cell = row.createCell(11);  
			    cell.setCellValue(info.getBankCity());
			    cell = row.createCell(12);  
			    cell.setCellValue(sdf.format(info.getApplyDate()));
			    String borrowInfo = info.getBorrowSN();
			    String borrowSn = "";
			    double borrowBillBalance = 0;
			    if(borrowInfo != null && borrowInfo.length() > 0)for(String _info : borrowInfo.split(";")){
			    	 String[] _tmpinfo = _info.split(",");
			    	 borrowSn += _tmpinfo[0];
			    	 String b = _tmpinfo[3];
					if( NumberUtils.isNumber(b)){
						borrowBillBalance += NumberUtils.toDouble(b);
					}
			    }
			    cell = row.createCell(13);  
			    cell.setCellValue(borrowSn);
			    cell = row.createCell(14);  
			    cell.setCellValue(borrowSn.length()==0?"否":"有");
			    cell = row.createCell(15);  
			    cell.setCellValue(borrowBillBalance);
			    cell = row.createCell(16);  
			    cell.setCellValue(info.getId());
			    cell = row.createCell(17);  
			    cell.setCellValue(info.getSumFinancialNotify()==null?OAControllerUtils.centMoneyToYuan(info.getMoneyAmount()):OAControllerUtils.centMoneyToYuan(info.getSumFinancialNotify()));
			    cell = row.createCell(18);  
			    cell.setCellValue(info.getCommunicationNotifyAmount()==null?OAControllerUtils.centMoneyToYuan(info.getCommunicationCosts()):OAControllerUtils.centMoneyToYuan(info.getCommunicationNotifyAmount()));
			    cell = row.createCell(19);  
			    cell.setCellValue(info.getOtherNotifyAmount()==null?OAControllerUtils.centMoneyToYuan(info.getSumOtherAmount()):OAControllerUtils.centMoneyToYuan(info.getOtherNotifyAmount()));
			    cell = row.createCell(20);  
			    cell.setCellValue(info.getOvertimeMealsNotifyAmount()==null?OAControllerUtils.centMoneyToYuan(info.getSumOvertimeMealsAmount()):OAControllerUtils.centMoneyToYuan(info.getOvertimeMealsNotifyAmount()));
			    cell = row.createCell(21);  
			    cell.setCellValue(info.getHospitalityNotifyAmount()==null?OAControllerUtils.centMoneyToYuan(info.getSumHospitalityAmount()):OAControllerUtils.centMoneyToYuan(info.getHospitalityNotifyAmount()));
			    cell = row.createCell(22);  
			    cell.setCellValue(info.getEmRelationsFeesNotify()==null?OAControllerUtils.centMoneyToYuan(info.getSumEmployeeRelationsFees()):OAControllerUtils.centMoneyToYuan(info.getEmRelationsFeesNotify()));
			    cell = row.createCell(23);  
			    cell.setCellValue(info.getTaxiFaresNotifyAmount()==null?OAControllerUtils.centMoneyToYuan(info.getSumTaxiFaresAmount()):OAControllerUtils.centMoneyToYuan(info.getTaxiFaresNotifyAmount()));
			    cell = row.createCell(24);  
			    cell.setCellValue(info.getSumFinancialNotify()==null?"":OAControllerUtils.centMoneyToYuan(info.getSumFinancialNotify()));
			    cell = row.createCell(25);  
			    cell.setCellValue(info.getFinancialDate()==null?"":sdf.format(info.getFinancialDate()));
			    cell = row.createCell(26);  
			    cell.setCellValue(info.getCashierSign()==null?"":info.getCashierSign());
			    cell = row.createCell(27);  
			    cell.setCellValue(info.getCashierDate()==null?"":sdf.format(info.getCashierDate()));
			    no++;
		    }
		    
		    
            file = File.createTempFile("日常报销", ".xls");
            FileOutputStream os = new FileOutputStream(file);
            wb.write(os);  
            wb.close();
		    os.close();
            InputStream fis = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));  
            byte[] buffer = new byte[fis.available()];  
            fis.read(buffer);  
            fis.close();  
            response.reset();  
            response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode("日常费用报销查询-"+sdf.format(new Date())+".xls", "UTF-8"));  
            response.addHeader("Content-Length", "" + file.length());  
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
            response.setContentType("application/vnd.ms-excel;charset=utf-8");  
            toClient.write(buffer);  
            toClient.flush();  
            toClient.close();
		  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(file != null){
				file.deleteOnExit();
			}
		}
	}

	/**
	 * 已经审批结束
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/approve_history")
	@ResponseBody
	public BaseResult getAllApproveHistoryList(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		//String userId = (String) request.getSession().getAttribute("USER_ID");
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		int noSize[] = OAControllerUtils.getPageNoAndSize(vars);
		int pageSize = noSize[0];
		int pageNo = noSize[1];
		String startTime = vars.get("startTime");
		String endTime = vars.get("endTime");
		String approve_user = vars.get("approveUser");
		Date _startTime = null;
		if (startTime != null) {
			try {
				_startTime = sdf.parse(startTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		Date _endTime = null;
		if (endTime != null) {
			try {
				_endTime = sdf.parse(endTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (OAControllerUtils.isNull(approve_user)) {
			approve_user = null;
		}
		// userId = vars.get("userId");
		FormInfoList formInfoList = null;
		formInfoList = ioaEngineService.historyList(userId, _startTime, _endTime, approve_user, pageNo, pageSize);
		DataResult dataResult;
		try {
			dataResult = getAllTableInfos(formInfoList, userId, 3);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(OAEngineConst.ACL_LIMIT_ERROR,
					OAEngineConst.ACL_LIMIT_ERROR_MSG);
		}
		return BaseResult.getSuccessResult(dataResult);
	}
	
	@RequestMapping(value="oa/upload_file",method=RequestMethod.POST)
	@ResponseBody
    public BaseResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException{
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		String fileName = file.getOriginalFilename();
		Files f = form0114Manager.saveFile(Long.parseLong(request.getParameter("formId")), userId, fileName, file.getBytes());
		return BaseResult.getSuccessResult(f);
    }
	
	@RequestMapping(value="oa/remove_file",method=RequestMethod.POST)
	@ResponseBody
    public BaseResult removeFile(HttpServletRequest request) throws IOException{
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		String fileId = request.getParameter("fileId");
		try {
			form0114Manager.removeFile(NumberUtils.toLong(fileId, 0), userId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
			return BaseResult.getErrorResult(-1, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
			return BaseResult.getErrorResult(-1, e.getMessage());
		}
		return BaseResult.getSuccessResult("");
    }
	
	@RequestMapping(value="oa/get_file",method=RequestMethod.POST)
	@ResponseBody
    public BaseResult getFile(HttpServletRequest request, @RequestBody CommonRequest commonRequest) throws IOException{
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		vars.get("formId");
		String formId = vars.get("formId");
		List<Files> list = form0114Manager.findFilesByFormId(Long.parseLong(formId));
		return BaseResult.getSuccessResult(list);
    }
	
	@RequestMapping(value = "oa/download_file",method=RequestMethod.GET)
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return;
		}
		String fileId = request.getParameter("fileId");
		Files file = this.form0114Manager.getFileById(NumberUtils.toLong(fileId));
		if(file == null){
			return;
		}
		File tfile = null;
		try {
			String fn = file.getFileName();
			tfile = File.createTempFile(fn, ".tmp");
			FileOutputStream os = new FileOutputStream(tfile);
			//wb.write(os);  
			os.write(file.getContent());
			os.close();
			InputStream fis = new BufferedInputStream(new FileInputStream(tfile.getAbsolutePath()));  
			byte[] buffer = new byte[fis.available()];  
			fis.read(buffer);  
			fis.close();  
			response.reset();  
			response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(file.getFileName(), "UTF-8"));  
			response.addHeader("Content-Length", "" + tfile.length());  
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
			response.setContentType("application/vnd.ms-excel;charset=utf-8");  
			toClient.write(buffer);  
			toClient.flush();  
			toClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(tfile != null){
				tfile.deleteOnExit();
			}
		}
	}
	
	
	/**
	 * 综合查询
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/admin_search")
	@ResponseBody
	public BaseResult search(HttpServletRequest request, @RequestBody CommonRequest commonRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		int noSize[] = OAControllerUtils.getPageNoAndSize(vars);
		int pageSize = noSize[0];
		int pageNo = noSize[1];
		String startTime = vars.get("startTime");
		String endTime = vars.get("endTime");
		String approveUser = vars.get("approveUser");
		String approveRtx = vars.get("approveRtx");
		String approveNo = vars.get("approveNo");
		String checkStartTime = vars.get("checkStartTime");
		String checkEndTime = vars.get("checkEndTime");
		String checkUser = vars.get("checkUser");
		String payStartTime = vars.get("payStartTime");
		String payEndTime = vars.get("payEndTime");
		String payUser = vars.get("payUser");
		String status = vars.get("status");
		Date _startTime = null;
		Date _endTime = null;
		Date _checkStartTime = null;
		Date _checkEndTime = null;
		Date _payStartTime = null;
		Date _payEndTime = null;
		if (startTime != null) {
			try {
				_startTime = sdf.parse(startTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (endTime != null) {
			try {
				_endTime = sdf.parse(endTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (checkStartTime != null) {
			try {
				_checkStartTime = sdf.parse(checkStartTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (checkEndTime != null) {
			try {
				_checkEndTime = sdf.parse(checkEndTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (payStartTime != null) {
			try {
				_payStartTime = sdf.parse(payStartTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (payEndTime != null) {
			try {
				_payEndTime = sdf.parse(payEndTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
				return BaseResult.getErrorResult(
						OAEngineConst.DATE_FORMAT_ERROR,
						OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (OAControllerUtils.isNull(approveUser)) {
			approveUser = null;
		}
		if (OAControllerUtils.isNull(approveRtx)) {
			approveRtx = null;
		}
		if (OAControllerUtils.isNull(approveNo)) {
			approveNo = null;
		}
		if (OAControllerUtils.isNull(checkUser)) {
			checkUser = null;
		}
		if (OAControllerUtils.isNull(payUser)) {
			payUser = null;
		}
		FormInfoList formInfoList = null;
		formInfoList = ioaEngineService.search(approveUser,approveRtx, approveNo, _startTime, _endTime, checkUser, _checkStartTime, _checkEndTime, payUser, _payStartTime, _payEndTime, status, pageNo, pageSize);
		DataResult dataResult;
		try {
			dataResult = getAllTableInfos(formInfoList, userId, 6);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(OAEngineConst.ACL_LIMIT_ERROR,
					OAEngineConst.ACL_LIMIT_ERROR_MSG);
		}
		return BaseResult.getSuccessResult(dataResult);
	}
	
	
	/**
	 * 综合查询导出
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/search_export")
	public void searchExport(HttpServletRequest request, HttpServletResponse response) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		int pageSize = Integer.MAX_VALUE;
		int pageNo = 0;
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String approveUser = request.getParameter("approveUser");
		String approveRtx = request.getParameter("approveRtx");
		String approveNo = request.getParameter("approveNo");
		String checkStartTime = request.getParameter("checkStartTime");
		String checkEndTime = request.getParameter("checkEndTime");
		String checkUser = request.getParameter("checkUser");
		String payStartTime = request.getParameter("payStartTime");
		String payEndTime = request.getParameter("payEndTime");
		String payUser = request.getParameter("payUser");
		String status = request.getParameter("status");
		Date _startTime = null;
		Date _endTime = null;
		Date _checkStartTime = null;
		Date _checkEndTime = null;
		Date _payStartTime = null;
		Date _payEndTime = null;
		if (startTime != null) {
			try {
				_startTime = sdf.parse(startTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (endTime != null) {
			try {
				_endTime = sdf.parse(endTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (checkStartTime != null) {
			try {
				_checkStartTime = sdf.parse(checkStartTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (checkEndTime != null) {
			try {
				_checkEndTime = sdf.parse(checkEndTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (payStartTime != null) {
			try {
				_payStartTime = sdf.parse(payStartTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (payEndTime != null) {
			try {
				_payEndTime = sdf.parse(payEndTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				logger.warn(OAEngineConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		if (OAControllerUtils.isNull(approveUser)) {
			approveUser = null;
		}
		if (OAControllerUtils.isNull(approveRtx)) {
			approveRtx = null;
		}
		if (OAControllerUtils.isNull(approveNo)) {
			approveNo = null;
		}
		if (OAControllerUtils.isNull(checkUser)) {
			checkUser = null;
		}
		if (OAControllerUtils.isNull(payUser)) {
			payUser = null;
		}
		FormInfoList formInfoList = null;
		formInfoList = ioaEngineService.search(approveUser, approveRtx, approveNo, _startTime, _endTime, checkUser, _checkStartTime, _checkEndTime, payUser, _payStartTime, _payEndTime, status, pageNo, pageSize);

		File file = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			HSSFWorkbook wb = new HSSFWorkbook();  
			HSSFSheet sheet = wb.createSheet("sheet1");  
			// 设置excel每列宽度  
		    sheet.setColumnWidth(0, 4000);  
		    sheet.setColumnWidth(1, 3500);  
		  
		    // 创建字体样式  
		    HSSFFont font = wb.createFont();  
		    font.setFontName("Verdana");  
		    font.setBoldweight((short) 100);  
		    font.setFontHeight((short) 300);  
		  
		    // 创建单元格样式  
		    HSSFCellStyle style = wb.createCellStyle();  
		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
		  
		    // 设置边框  
		    //style.setBottomBorderColor(HSSFColor.RED.index);  
		    //style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
		    //style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
		    //style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
		    //style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		  
		    style.setFont(font);// 设置字体 
		    
		    // 创建Excel的sheet的一行  
		    HSSFRow row = sheet.createRow(0);  
		    row.setHeight((short) 500);// 设定行的高度  
		    // 创建一个Excel的单元格  
		    HSSFCell cell = row.createCell(0);  
		  
		    // 合并单元格(startRow，endRow，startColumn，endColumn)  
		    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 27));  
		  
		    // 给Excel的单元格设置样式和赋值  
		    cell.setCellStyle(style);  
		    cell.setCellValue("日常费用报销查询-"+sdf.format(new Date()));  
		  
		    // 设置单元格内容格式  
		    //HSSFCellStyle style1 = wb.createCellStyle(); 
		  
		    row = sheet.createRow(1);  
		    cell = row.createCell(0);  
		    cell.setCellValue("RTX_ID");
		    cell = row.createCell(1);  
		    cell.setCellValue("部门编号");
		    cell = row.createCell(2);  
		    cell.setCellValue("一级部门");
		    cell = row.createCell(3);  
		    cell.setCellValue("二级部门");
		    cell = row.createCell(4);  
		    cell.setCellValue("三级部门");
		    cell = row.createCell(5);  
		    cell.setCellValue("四级部门");
		    cell = row.createCell(6);  
		    cell.setCellValue("五级部门");
		    cell = row.createCell(7);  
		    cell.setCellValue("人员编号");
		    cell = row.createCell(8);  
		    cell.setCellValue("申请人");
		    cell = row.createCell(9);  
		    cell.setCellValue("银行卡号");
		    cell = row.createCell(10);  
		    cell.setCellValue("开户银行");
		    cell = row.createCell(11);  
		    cell.setCellValue("开户所在地");
		    cell = row.createCell(12);  
		    cell.setCellValue("申请日期");
		    cell = row.createCell(13);  
		    cell.setCellValue("借款单流水号");
		    cell = row.createCell(14);  
		    cell.setCellValue("是否有借款");
		    cell = row.createCell(15);  
		    cell.setCellValue("借款金额");
		    cell = row.createCell(16);  
		    cell.setCellValue("流水号");
		    cell = row.createCell(17);  
		    cell.setCellValue("金额总计");
		    cell = row.createCell(18);  
		    cell.setCellValue("通信费金额总计");
		    cell = row.createCell(19);  
		    cell.setCellValue("其他费用金额总计");
		    cell = row.createCell(20);  
		    cell.setCellValue("加班餐费金额总计");
		    cell = row.createCell(21);  
		    cell.setCellValue("招待费金额总计");
		    cell = row.createCell(22);  
		    cell.setCellValue("员工关系费金额总计");
		    cell = row.createCell(23);  
		    cell.setCellValue("出租车费金额总计");
		    cell = row.createCell(24);  
		    cell.setCellValue("财务确认总计");
		    cell = row.createCell(25);  
		    cell.setCellValue("财务审核签字");
		    cell = row.createCell(26);  
		    cell.setCellValue("财务审核签字时间");
		    cell = row.createCell(27);  
		    cell.setCellValue("出纳办理签字");
		    cell = row.createCell(28);  
		    cell.setCellValue("出纳办理签字日期");
		    
		    int no = 2;
		    if(formInfoList != null)for(FormInfo info : formInfoList.getFormInfos()){
		    	row = sheet.createRow(no);  
			    cell = row.createCell(0);  
			    cell.setCellValue(info.getStartMemberId());
			    cell = row.createCell(1);  
			    cell.setCellValue("");
			    cell = row.createCell(2);  
			    cell.setCellValue(info.getFirstDep());
			    cell = row.createCell(3);  
			    cell.setCellValue(info.getSecDep());
			    cell = row.createCell(4);  
			    cell.setCellValue(info.getThridDep());
			    cell = row.createCell(5);  
			    cell.setCellValue(info.getFourthDep());
			    cell = row.createCell(6);  
			    cell.setCellValue(info.getFivethDep());
			    cell = row.createCell(7);  
			    cell.setCellValue(info.getSerialNumber());
			    cell = row.createCell(8);  
			    cell.setCellValue(info.getApplyUser());
			    cell = row.createCell(9);  
			    cell.setCellValue(info.getBankNumber());
			    cell = row.createCell(10);  
			    cell.setCellValue(info.getBankName());
			    cell = row.createCell(11);  
			    cell.setCellValue(info.getBankCity());
			    cell = row.createCell(12);  
			    cell.setCellValue(sdf.format(info.getApplyDate()));
			    String borrowInfo = info.getBorrowSN();
			    String borrowSn = "";
			    double borrowBillBalance = 0;
			    /*if(borrowInfo != null)for(String _info : borrowInfo.split(";")){
			    	 String[] _tmpinfo = _info.split(",");
			    	 borrowSn += _tmpinfo[0];
			    	 String b = _tmpinfo[3];
					if( NumberUtils.isNumber(b)){
						borrowBillBalance += NumberUtils.toDouble(b);
					}
			    }*/
			    cell = row.createCell(13);  
			    cell.setCellValue(borrowSn);
			    cell = row.createCell(14);  
			    cell.setCellValue(borrowSn.length()==0?"否":"有");
			    cell = row.createCell(15);  
			    cell.setCellValue(borrowBillBalance);
			    cell = row.createCell(16);  
			    cell.setCellValue(info.getId());
			    cell = row.createCell(17);  
			    cell.setCellValue(info.getSumFinancialNotify()==null?OAControllerUtils.centMoneyToYuan(info.getMoneyAmount()):OAControllerUtils.centMoneyToYuan(info.getSumFinancialNotify()));
			    cell = row.createCell(18);  
			    cell.setCellValue(info.getCommunicationNotifyAmount()==null?OAControllerUtils.centMoneyToYuan(info.getCommunicationCosts()):OAControllerUtils.centMoneyToYuan(info.getCommunicationNotifyAmount()));
			    cell = row.createCell(19);  
			    cell.setCellValue(info.getOtherNotifyAmount()==null?OAControllerUtils.centMoneyToYuan(info.getSumOtherAmount()):OAControllerUtils.centMoneyToYuan(info.getOtherNotifyAmount()));
			    cell = row.createCell(20);  
			    cell.setCellValue(info.getOvertimeMealsNotifyAmount()==null?OAControllerUtils.centMoneyToYuan(info.getSumOvertimeMealsAmount()):OAControllerUtils.centMoneyToYuan(info.getOvertimeMealsNotifyAmount()));
			    cell = row.createCell(21);  
			    cell.setCellValue(info.getHospitalityNotifyAmount()==null?OAControllerUtils.centMoneyToYuan(info.getSumHospitalityAmount()):OAControllerUtils.centMoneyToYuan(info.getHospitalityNotifyAmount()));
			    cell = row.createCell(22);  
			    cell.setCellValue(info.getEmRelationsFeesNotify()==null?OAControllerUtils.centMoneyToYuan(info.getSumEmployeeRelationsFees()):OAControllerUtils.centMoneyToYuan(info.getEmRelationsFeesNotify()));
			    cell = row.createCell(23);  
			    cell.setCellValue(info.getTaxiFaresNotifyAmount()==null?OAControllerUtils.centMoneyToYuan(info.getSumTaxiFaresAmount()):OAControllerUtils.centMoneyToYuan(info.getTaxiFaresNotifyAmount()));
			    cell = row.createCell(24);  
			    cell.setCellValue(info.getSumFinancialNotify()==null?"":OAControllerUtils.centMoneyToYuan(info.getSumFinancialNotify()));
			    cell = row.createCell(25);
			    cell.setCellValue(info.getField0072());
			    cell = row.createCell(26);  
			    cell.setCellValue(info.getFinancialDate()==null?"":sdf.format(info.getFinancialDate()));
			    cell = row.createCell(27);  
			    cell.setCellValue(info.getCashierSign()==null?"":info.getCashierSign());
			    cell = row.createCell(28);  
			    cell.setCellValue(info.getCashierDate()==null?"":sdf.format(info.getCashierDate()));
			    no++;
		    }
		    
            file = File.createTempFile("日常报销", ".xls");
            FileOutputStream os = new FileOutputStream(file);
            wb.write(os);  
            wb.close();
		    os.close();
            InputStream fis = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));  
            byte[] buffer = new byte[fis.available()];  
            fis.read(buffer);  
            fis.close();  
            response.reset();  
            response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode("日常费用报销查询-"+sdf.format(new Date())+".xls", "UTF-8"));  
            response.addHeader("Content-Length", "" + file.length());  
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
            response.setContentType("application/vnd.ms-excel;charset=utf-8");  
            toClient.write(buffer);  
            toClient.flush();  
            toClient.close();
		  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(file != null){
				file.deleteOnExit();
			}
		}
	}
	
	/**
	 * 获取员工姓名
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/find_employee", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult findEmployee(HttpServletRequest request) {
		String result[] = new String[] { "abc", "aaa", "bbb"};
		return BaseResult.getSuccessResult(result);
	}

	/**
	 * 批量完成任务
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/approve_pass")
	@ResponseBody
	public BaseResult approvePass(HttpServletRequest request,
			@RequestBody CommonRequest commonRequestt) {
		//String userId = (String) request.getSession().getAttribute("USER_ID");
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		String cname = QUtils.getAdname(request);
		Map<String, String> vars = commonRequestt.getVars();
		String formIds = vars.get("formIds");
		String taskIds = vars.get("taskIds");
		List<Long> formIdList = new ArrayList<Long>();
		List<String> taskIdList = new ArrayList<String>();
		String formMsg[] = formIds.split(",");
		for (int i = 0; i < formMsg.length; i++) {
			formIdList.add(Long.valueOf(formMsg[i]));
		}
		String taskMsg[] = taskIds.split(",");
		for (int i = 0; i < taskMsg.length; i++) {
			taskIdList.add(taskMsg[i]);
		}
		// 其实这里就是附言
		String memo = vars.get("memo");
		List<Long> errorFormIds = ioaEngineService.batchPass(processKey,
				userId, cname, formIdList, taskIdList, memo);
		int size = errorFormIds.size();
		if (size == 0) {
			return BaseResult.getSuccessResult("同意操作成功");
		}
		return BaseResult.getSuccessResult(errorFormIds.toArray());
		

	}

	/**
	 * 批量拒绝任务
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/approve_reject")
	@ResponseBody
	public BaseResult approveReject(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		//String userId = (String) request.getSession().getAttribute("USER_ID");
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		String cname = QUtils.getAdname(request);
		Map<String, String> vars = commonRequest.getVars();
		String formIds = vars.get("formIds");
		String taskIds = vars.get("taskIds");
		String formMsg[] = formIds.split(",");
		int len = formMsg.length;
		String taskMsg[] = taskIds.split(",");
		// 其实这里就是附言
		String memo = vars.get("memo");
		// userId = vars.get("userId");
		for (int i = 0; i < len; i++) {
			try {
				ioaEngineService.refuse(processKey, userId, cname, 
						Long.valueOf(formMsg[i]), taskMsg[i], memo);
			} catch (FormNotFoundException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return BaseResult.getErrorResult(
						OAEngineConst.FORM_NOT_FOUND_ERROR,
						OAEngineConst.FORM_NOT_FOUND_ERROR_MSG);
			} catch (ActivitiException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return BaseResult.getErrorResult(OAEngineConst.ACTIVITI_ERROR,
						OAEngineConst.ACTIVITI_ERROR_MSG);
			}
		}
		return BaseResult.getSuccessResult("拒绝审批结束");
	}


	/**
	 * 批量加签任务
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/approve_endorse")
	@ResponseBody
	public BaseResult approveEndorse(HttpServletRequest request, @RequestBody CommonRequest commonRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL, OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		String cname = QUtils.getAdname(request);
		Map<String, String> vars = commonRequest.getVars();
		String formIds = vars.get("formIds");
		String taskIds = vars.get("taskIds");
		String formMsg[] = formIds.split(",");
		int len = formMsg.length;
		String taskMsg[] = taskIds.split(",");
		// 其实这里就是附言
		String memo = vars.get("memo");
		String assignees = vars.get("rtx_id").trim().toLowerCase();
		if(assignees==null || assignees.length() == 0){
			return BaseResult.getErrorResult(-1, "加签人不能为空");
		}
		EmployeeInfo einfo = null;
		if(assignees.indexOf(".") < 0){
			List<EmployeeInfo> infos = this.employeeInfoService.getEmployeeByCname(assignees);
			if(infos.size() == 1){
				einfo = infos.get(0);
				assignees = einfo.getUserId();
			}else if(infos.size() > 1){
				String s = "请选择准确的rtx_id:<br/>";
				for(EmployeeInfo i : infos){
					s += i.getAdName()+"("+i.getUserId()+")<br/>";
				}
				return BaseResult.getErrorResult(-202, s);
			}
		}else{
			try {
				einfo = this.ioaEngineService.getEmployeeInfo(assignees);
				
			} catch (RemoteAccessException e1) {
				e1.printStackTrace();
				return BaseResult.getErrorResult(-1, "被加签人没有找到，或已经离职");
			}
		}
		if(einfo == null || einfo.getEnable() == 0){
			return BaseResult.getErrorResult(-1, "被加签人没有找到，或已经离职");
		}
		if(userId.equals(assignees)){
			return BaseResult.getErrorResult(-1, "加签人对象不能是自己");
		}
		
		for (int i = 0; i < len; i++) {
			try {
				/*FormInfo formInfo = ioaEngineService.getFormInfo(processKey, userId, formMsg[i]);
				String tk = this.workflowManager.getTaskKey(taskMsg[i]);
				if (tk != null 
						&&  tk.equals("direct_manager")
						&& !userId.equals(formInfo.getStartMemberId()) 
						&& !assignees.equals(formInfo.getStartMemberId()) 
						&& !this.groupManager.inGroups(new String[] {tk}, assignees)) {
					return BaseResult.getErrorResult(-1, "加签人必须是同组");
				}*/
				ioaEngineService.endorse(processKey, userId, cname, Long.valueOf(formMsg[i]), taskMsg[i], assignees, memo);
			} catch (FormNotFoundException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return BaseResult.getErrorResult(
						OAEngineConst.FORM_NOT_FOUND_ERROR,
						OAEngineConst.FORM_NOT_FOUND_ERROR_MSG);
			} catch (ActivitiException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return BaseResult.getErrorResult(OAEngineConst.ACTIVITI_ERROR,
						OAEngineConst.ACTIVITI_ERROR_MSG);
			}
		}
		return BaseResult.getSuccessResult("加签结束");
	}
	
	
	/**
	 * 召回
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/approve_recall")
	@ResponseBody
	public BaseResult approveRecall(HttpServletRequest request, @RequestBody CommonRequest commonRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL, OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		String cname = QUtils.getAdname(request);
		Map<String, String> vars = commonRequest.getVars();
		String formId = vars.get("formId");
		
		try {
			ioaEngineService.recall(processKey, userId, cname, Long.parseLong(formId), "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(-1, e.getMessage());
		} 
		return BaseResult.getSuccessResult("召回成功");
	}
	
	/**
	 * 退回
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/approve_back")
	@ResponseBody
	public BaseResult approveBack(HttpServletRequest request, @RequestBody CommonRequest commonRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL, OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		String cname = QUtils.getAdname(request);
		Map<String, String> vars = commonRequest.getVars();
		String formId = vars.get("formIds");
		String taskId = vars.get("taskIds");
		String msg = vars.get("memo");
		String assignees = vars.get("rtx_id");
		
		
		if(assignees==null || assignees.length() == 0){
			return BaseResult.getErrorResult(-1, "退回人不能为空");
		}
		if(assignees.equals("backMemberFlag")){
			List<FormApproveLog> listPass=logManager.formAppreoveLogAllPassByFormId(
					Long.parseLong(request.getParameter("formId").toString()),userId,"pass");
			//保存退回人的rtx_id
			if (listPass==null || listPass.size()<0){
				return BaseResult.getErrorResult(-1, "不可退回");
			}else {
				assignees=listPass.get(0).getApproveUser();
				}
		}else{
			assignees=assignees.substring(0,assignees.lastIndexOf("("));
		}
		EmployeeInfo einfo = null;
		if(assignees.indexOf(".") < 0){
			List<EmployeeInfo> infos = this.employeeInfoService.getEmployeeByCname(assignees);
			if(infos.size() == 1){
				einfo = infos.get(0);
				assignees = einfo.getUserId();
			}else if(infos.size() > 1){
				String s = "请选择准确的rtx_id:<br/>";
				for(EmployeeInfo i : infos){
					s += i.getAdName()+"("+i.getUserId()+")<br/>";
				}
				return BaseResult.getErrorResult(-202, s);
			}
		}else{
			try {
				einfo = this.ioaEngineService.getEmployeeInfo(assignees);
				
			} catch (RemoteAccessException e1) {
				e1.printStackTrace();
				return BaseResult.getErrorResult(-1, "被退回人没有找到，或已经离职");
			}
		}
		if(einfo == null || einfo.getEnable() == 0){
			return BaseResult.getErrorResult(-1, "被退回人没有找到，或已经离职");
		}
		if(userId.equals(assignees)){
			return BaseResult.getErrorResult(-1, "退回人对象不能是自己");
		}
		
		FormInfo formInfo;
		try {
			Boolean passFlag=logManager.formAppreoveLogPass(Long.parseLong(formId),assignees);
			formInfo = ioaEngineService.getFormInfo(processKey, userId, formId);
			String tk = this.workflowManager.getTaskKey(taskId);
			if (tk != null 
					&& (tk.equals("fin_check") || tk.equals("cashier"))
					&& !userId.equals(formInfo.getStartMemberId()) 
					&& !assignees.equals(formInfo.getStartMemberId()) 
					&& !passFlag) {
				return BaseResult.getErrorResult(-1, "退回对象 必须是发起人或审批通过的成员");
			}
			
		} catch (FormNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1.getMessage());
			return BaseResult.getErrorResult(
					OAEngineConst.FORM_NOT_FOUND_ERROR,
					OAEngineConst.FORM_NOT_FOUND_ERROR_MSG);
		}
		try {
				ioaEngineService.back(processKey, userId, cname, taskId, Long.parseLong(formId), msg,assignees);	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(-1, e.getMessage());
		} 
		return BaseResult.getSuccessResult("退回成功");
	}

	/**
	 * 催办动作
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/push_approve")
	@ResponseBody
	public BaseResult pushApprove(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		//String userId = (String) request.getSession().getAttribute("USER_ID");
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		String formId = vars.get("formId");
		ListInfo<ApprovalInfo> approveInfos = ioaEngineService.getApprovalInfo(
				processKey, formId, 1, 20);
		long count = approveInfos.getCount();
		if (count == 0) {
			return BaseResult.getSuccessResult("没有获取到审批候选人");
		}
		List<ApprovalInfo> infos = approveInfos.getInfos();
		ApprovalInfo approvalInfo = infos.get(0);
		Long approveId = approvalInfo.getId();
		String candidates = ioaEngineService.reminder(processKey, userId,
				formId, String.valueOf(approveId), "催办");
		String reMsg = "下个节点审批人列表如下: " + candidates + " 催办成功";
		return BaseResult.getSuccessResult(reMsg);

	}

	/**
	 * 我的正在审批的申请,撤销或者删除
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/back_del")
	@ResponseBody
	public BaseResult applyBackorDel(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		//String userId = (String) request.getSession().getAttribute("USER_ID");
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL,
					OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		String cname = QUtils.getAdname(request);
		Map<String, String> vars = commonRequest.getVars();
		String backDel = vars.get("backDel");
		String formIds = vars.get("formIds");
		String formMsg[] = formIds.split(",");
		int len = formMsg.length;
		// userId = vars.get("userId");
		for (int i = 0; i < len; i++) {
			try {
				if ("delete".equals(backDel)) {
					ioaEngineService.deleteFormInfo(processKey, userId, formMsg[i]);
				} else if ("cancel".equals(backDel)) {
					ioaEngineService.cancelFormInfo(processKey, userId, cname, formMsg[i]);
				}
			} catch (FormNotFoundException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return BaseResult.getErrorResult(
						OAEngineConst.FORM_NOT_FOUND_ERROR, e.getMessage());
			} catch (ManagerFormException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return BaseResult.getErrorResult(
						OAEngineConst.MANAGER_LOCK_ERROR, e.getMessage());
			} catch (ActivitiException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return BaseResult.getErrorResult(OAEngineConst.ACTIVITI_ERROR,
						e.getMessage());
			}
		}
		if ("delete".equals(backDel)) {
			return BaseResult.getSuccessResult("删除成功!");
		} else {
			return BaseResult.getSuccessResult("撤销成功!");
		}
	}

	/**
	 * 获取审批各个节点的审批意见
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/approve_info")
	@ResponseBody
	public BaseResult getApproveInfo(HttpServletRequest request, @RequestBody CommonRequest commonRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return BaseResult.getErrorResult(OAEngineConst.RTX_ID_IS_NULL, OAEngineConst.RTX_ID_IS_NULL_MSG);
		}
		Map<String, String> vars = commonRequest.getVars();
		String formId = vars.get("formId");
		String[] result = this._getApproveInfo(processKey, formId);
		if(result == null) return BaseResult.getSuccessResult("");
		return BaseResult.getSuccessResult(result);
	}
	
	/**
	 * 审批完成API
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "api/info", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult api(HttpServletRequest request) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if(startTime == null || endTime == null){
			return BaseResult.getErrorResult(-100, "日期范围不能为空");
		}
		Date _startTime = null;
		if (startTime != null) {
			try {
				_startTime = sdf.parse(startTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				return BaseResult.getErrorResult(-101, "开始日期格式错误");
			}
		}
		Date _endTime = null;
		if (endTime != null) {
			try {
				_endTime = sdf.parse(endTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				return BaseResult.getErrorResult(-101, "结束日期格式错误");
			}
		}
		try{
			List<Object> info = ioaEngineService.findHistoryForm(_startTime, _endTime);
			return BaseResult.getSuccessResult(info);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return BaseResult.getErrorResult(-500, e.getMessage());
		}
	}
	
	@RequestMapping(value = "oa/get_ems")
	@ResponseBody
	public BaseResult getEmployees(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		String key = vars.get("key");
		List<EmployeeInfo> employeeByCname = this.employeeInfoService.getEmployeeByCname(key);
		List<Map<String, String>> kvs = new ArrayList<Map<String, String>>();
		for(EmployeeInfo info : employeeByCname){
			HashMap<String, String> kv = new HashMap<String, String>();
			kv.put("value", info.getUserId());
			kv.put("label", info.getAdName());
			kvs.add(kv);
		}
		return BaseResult.getSuccessResult(kvs);
	}
	
	/**
	 * 流程任务跟踪图
	 * 
	 * @param processDefinitionId
	 * @param processInstanceId
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "oa/{processKey}/{oid}/trace_pic.png")
	public void readResource(@PathVariable("processKey") String processKey, @PathVariable("oid") String oid, HttpServletResponse response) throws Exception {
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processKey).latestVersion()
				.singleResult();
		BpmnModel bpmnModel = repositoryService.getBpmnModel(pd.getId());
		List<Task> list = taskService.createTaskQuery().active().processInstanceBusinessKey(oid).list();
		List<String> activeActivityIds = new ArrayList<String>();
		for (Task task : list) {
			activeActivityIds.add(task.getTaskDefinitionKey());
		}
		Context.setProcessEngineConfiguration(processEngine
				.getProcessEngineConfiguration());
		ProcessEngineConfiguration processEngineConfiguration = Context
                .getProcessEngineConfiguration();
		InputStream imageStream = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator().generateDiagram(
				bpmnModel, "png", 
				activeActivityIds,
				Collections.<String>emptyList(), 
				processEngineConfiguration.getActivityFontName(),
				processEngine.getProcessEngineConfiguration().getLabelFontName(), 
				null, 1.0);
		byte[] b = new byte[1024];
		int len;
		while ((len = imageStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}
	
	/**
	 * 审批信息
	 * @param processKey
	 * @param formId
	 * @return
	 */
	private String[] _getApproveInfo(String processKey, String formId){
		ListInfo<ApprovalInfo> approveInfos = ioaEngineService.getApprovalInfo(processKey, formId, 1, 100);
		long count = approveInfos.getCount();
		if (count == 0) {
			return null;
		}
		List<ApprovalInfo> infos = approveInfos.getInfos();
		int size = infos.size();
		SimpleDateFormat tdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] result = new String[(size + 1) * 6];
		int k = 0;
		ApprovalInfo approveInfo = null;
		if(size > 0){
			approveInfo = infos.get(0);
			if(approveInfo.getNextTaskId() != null){
				result[k++] = "<b>当前审批节点: " + approveInfo.getNextTaskName() + "</b>";
				//result[k++] = "<b>审批人: " + approveInfo.getNextCandidate() + "</b>";
				StringBuilder str = new StringBuilder();
				str.append("<b>审批人: </br>");
				String[] ApproverRTX = approveInfo.getNextCandidate().split(",");
				for (int i = 0 ; i < ApproverRTX.length ; i++ ) {
					str.append(ApproverRTX[i]+"  ");
					if((i+1)%3==0)str.append("</br>");
				}
				str.append("</b>");
				result[k++] = str.toString();
				result[k++] = "";
			}
		}
		for (int i = 0; i < size; i++) {
			approveInfo = infos.get(i);
			String type = OAControllerUtils.transformApproveEnToCh(approveInfo.getManagerType());
			if ("申请".equals(type)) {
				result[k++] = "申请人: " + approveInfo.getApproveCname();
				result[k++] = "申请时间: " + tdf.format(approveInfo.getTs());
				result[k++] = "";
			} else if ("取消".equals(type)) {
				result[k++] = "用户取消申请: " + approveInfo.getApproveCname();
				result[k++] = "取消时间: " + tdf.format(approveInfo.getTs());
				result[k++] = "";
			} else if ("召回".equals(type)) {
				result[k++] = "召回: " + approveInfo.getApproveCname();
				result[k++] = "取消时间: " + tdf.format(approveInfo.getTs());
				result[k++] = "";
			} else {
				result[k++] = "审批节点: " + approveInfo.getTaskName();
				result[k++] = "审批人: " + approveInfo.getApproveCname();
				result[k++] = "审批时间: " + tdf.format(approveInfo.getTs());
				result[k++] = "审批结果: " + OAControllerUtils.transformApproveEnToCh(approveInfo.getManagerType());
				result[k++] = "附言: " + approveInfo.getMemo();
				result[k++] = "";
			}
		}
		
		return result;
	}

	/**
	 * 获取前端传过来的数据,构造成FormInfo,存入数据库
	 * 
	 * @param formInfo
	 * @param tableMap
	 * @param vars
	 * @return
	 * @throws ParseException
	 */
	private boolean constructFormInfo(FormInfo formInfo,
			Map<String, String[][]> tableMap, Map<String, String> vars,
			boolean ratify) throws ParseException, NumberFormatException {
		if (vars.get("formId") != null) {
			formInfo.setId(Long.parseLong(vars.get("formId")));
		}
		// 存储各个table的数据。
		String table[][] = tableMap.get("table1");
		int len = table.length;
		List<TaxiFaresInfo> list1 = new ArrayList<TaxiFaresInfo>();
		long taxiSum = 0;
		long taxiRatify = 0;
		for (int i = 0; i < len; i++) {
			Date day = null;
			BigDecimal workhour = new BigDecimal(0);
			if (!"".equals(table[i][0])) {
				day = OAControllerUtils.strToDate(table[i][0]);
			}
			if (!"".equals(table[i][6])) {
				workhour = new BigDecimal(table[i][6]);
			}
			TaxiFaresInfo taxiInfo = new TaxiFaresInfo();
			taxiInfo.setFormmain0114id(formInfo.getId());
			taxiInfo.setTaxiFaresDate(day);
			taxiInfo.setTaxiFaresAddr(table[i][1]);
			taxiInfo.setTaxiFaresDest(table[i][2]);
			taxiInfo.setTaxiFaresTime(table[i][3]);
			taxiInfo.setTaxiFaresTimeNew(table[i][3]);
			taxiInfo.setTaxiFaresUse(table[i][4]);
			taxiInfo.setTaxiFaresPeerPeople(table[i][5]);
			taxiInfo.setTaxiFaresWorkhour(workhour);
			taxiInfo.setTaxiFaresAmount(OAControllerUtils.yuanMoneyToCent(table[i][7]));
			if (ratify) {
				long yuanMoneyToCent = OAControllerUtils.yuanMoneyToCent(table[i][8]);
				taxiInfo.setRatify(OAControllerUtils.yuanMoneyToCent(table[i][8]));
			} else {
				//taxiInfo.setRatify(OAControllerUtils.yuanMoneyToCent(table[i][7]));
			}
			taxiInfo.setComment(table[i][9]);
			if (table[i][10].length() > 0) {
				taxiInfo.setId(Long.valueOf(table[i][10]));
			}
			taxiInfo.setSort(i);
			taxiSum += taxiInfo.getTaxiFaresAmount();
			taxiRatify += taxiInfo.getRatify()==null?0:taxiInfo.getRatify();
			list1.add(taxiInfo);
		}
		int size = list1.size();
		TaxiFaresInfo[] taxiInfos = list1.toArray(new TaxiFaresInfo[size]);
		formInfo.setTaxiFaresInfo(taxiInfos);

		table = tableMap.get("table2");
		len = table.length;
		List<OvertimeMealsInfo> list2 = new ArrayList<OvertimeMealsInfo>();
		long overSum = 0;
		long overRatify = 0;
		for (int i = 0; i < len; i++) {
			Date day = null;
			if (!"".equals(table[i][0])) {
				day = OAControllerUtils.strToDate(table[i][0]);
			}
			OvertimeMealsInfo overInfo = new OvertimeMealsInfo();
			overInfo.setFormmain0114id(formInfo.getId());
			overInfo.setOvertimeMealsDate(day);
			overInfo.setMealsAddr(table[i][1]);
			overInfo.setOvertimeMealsPeerPeople(table[i][2]);
			overInfo.setMealsPersonNum(OAControllerUtils.strToLong(table[i][3]));
			overInfo.setOvertimeMealsAmount(OAControllerUtils.yuanMoneyToCent(table[i][4]));
			overInfo.setPerMealsFee(OAControllerUtils.yuanMoneyToCent(table[i][5]));
			overInfo.setInvoiceAmount(table[i][6]);
			overInfo.setOvertimeMealsWorkhours(OAControllerUtils.workHourToDec(table[i][7]));
			if (ratify) {
				overInfo.setRatify(OAControllerUtils.yuanMoneyToCent(table[i][8]));
			} else {
				//overInfo.setRatify(OAControllerUtils.yuanMoneyToCent(table[i][4]));
			}
			overInfo.setOvertimeMealsComment(table[i][9]);
			if (table[i][10].length() > 0) {
				overInfo.setId(Long.valueOf(table[i][10]));
			}
			overInfo.setSort(i);
			overSum += overInfo.getOvertimeMealsAmount();
			overRatify += overInfo.getRatify()==null?0:overInfo.getRatify();
			list2.add(overInfo);
		}
		size = list2.size();
		OvertimeMealsInfo[] overInfos = list2
				.toArray(new OvertimeMealsInfo[size]);
		formInfo.setOvertimeMealsInfo(overInfos);

		table = tableMap.get("table3");
		len = table.length;
		List<HospitalityInfo> list3 = new ArrayList<HospitalityInfo>();
		long hosSum = 0;
		long hosRatify = 0;
		for (int i = 0; i < len; i++) {
			Date day = null;
			if (!"".equals(table[i][0])) {
				day = OAControllerUtils.strToDate(table[i][0]);
			}
			HospitalityInfo hosInfo = new HospitalityInfo();
			hosInfo.setFormmain0114id(formInfo.getId());
			hosInfo.setHospitalityDate(day);
			hosInfo.setHospitalityAddr(table[i][1]);
			hosInfo.setBusinessPurpose(table[i][2]);
			hosInfo.setCustomCompany(table[i][3]);
			hosInfo.setCustomName(table[i][4]);
			hosInfo.setHospitalityNum(table[i][5]);
			hosInfo.setHospitalityAmount(OAControllerUtils.yuanMoneyToCent(table[i][6]));
			if (ratify) {
				hosInfo.setRatify(OAControllerUtils.yuanMoneyToCent(table[i][7]));
			} else {
				//hosInfo.setRatify(OAControllerUtils.yuanMoneyToCent(table[i][6]));
			}
			hosInfo.setMemo(table[i][8]);
			if (table[i][9].length() > 0) {
				hosInfo.setId(Long.valueOf(table[i][9]));
			}
			hosInfo.setSort(i);
			hosSum += hosInfo.getHospitalityAmount();
			hosRatify += hosInfo.getRatify()==null?0:hosInfo.getRatify();
			list3.add(hosInfo);
		}
		size = list3.size();
		HospitalityInfo[] hosInfos = list3.toArray(new HospitalityInfo[size]);
		formInfo.setHospitalityInfo(hosInfos);

		table = tableMap.get("table4");
		len = table.length;
		List<EmployeeRelationsFeesInfo> list4 = new ArrayList<EmployeeRelationsFeesInfo>();
		long employSum = 0;
		long employRatify = 0;
		for (int i = 0; i < len; i++) {
			Date day = null;
			if (!"".equals(table[i][0]) ) {
				day = OAControllerUtils.strToDate(table[i][0]);
			}
			EmployeeRelationsFeesInfo employInfo = new EmployeeRelationsFeesInfo();
			employInfo.setFormmain0114id(formInfo.getId());
			employInfo.setEmRelationsDate(day);
			employInfo.setEmRelationsAddress(table[i][1]);
			employInfo.setEmRelationsPeerPeople(table[i][2]);
			employInfo.setActDest(table[i][3]);
			employInfo.setEmRelationsFees(OAControllerUtils.yuanMoneyToCent(table[i][4]));
			if (ratify) {
				employInfo.setRatify(OAControllerUtils.yuanMoneyToCent(table[i][5]));
			} else {
				//employInfo.setRatify(OAControllerUtils.yuanMoneyToCent(table[i][4]));
			}
			employInfo.setEmRelationsFeesComment(table[i][6]);
			if (table[i][7].length() > 0) {
				employInfo.setId(Long.valueOf(table[i][7]));
			}
			employInfo.setSort(i);
			employSum += employInfo.getEmRelationsFees();
			employRatify += employInfo.getRatify()==null?0:employInfo.getRatify();
			list4.add(employInfo);

		}
		size = list4.size();
		EmployeeRelationsFeesInfo[] employInfos = list4
				.toArray(new EmployeeRelationsFeesInfo[size]);
		formInfo.setEmployeeRelationsFeesInfo(employInfos);

		table = tableMap.get("table5");
		len = table.length;
		List<OtherCostsInfo> list5 = new ArrayList<OtherCostsInfo>();
		long otherSum = 0;
		long otherRatify = 0;
		for (int i = 0; i < len; i++) {
			if ("".equals(table[i][0]) || "".equals(table[i][1])) {
				//continue;
			}
			OtherCostsInfo otherInfo = new OtherCostsInfo();
			otherInfo.setFormmain0114id(formInfo.getId());
			otherInfo.setOtherCostProject(table[i][0]);
			otherInfo.setOtherCostAmount(OAControllerUtils.yuanMoneyToCent(table[i][1]));
			if (ratify) {
				otherInfo.setRatify(OAControllerUtils.yuanMoneyToCent(table[i][2]));
			} else {
				//otherInfo.setRatify(OAControllerUtils.yuanMoneyToCent(table[i][1]));
			}
			otherInfo.setOtherCostComment(table[i][3]);
			if (table[i][4].length() > 0) {
				otherInfo.setId(Long.valueOf(table[i][4]));
			}
			otherInfo.setSort(i);
			otherSum += otherInfo.getOtherCostAmount();
			otherRatify += otherInfo.getRatify()==null?0:otherInfo.getRatify();
			list5.add(otherInfo);
		}
		size = list5.size();
		// 如果全部数据都为空的话，就不存了。
		if (list1.size() == 0 && list2.size() == 0 && list3.size() == 0
				&& list4.size() == 0 && list5.size() == 0
				&& OAControllerUtils.isNull(vars.get("remark")) && (
						OAControllerUtils.isNull(vars.get("sum6"))
						|| "".equals(vars.get("sum6"))
						|| Float.parseFloat(vars.get("sum6")) == 0
					)) {

			return false;
		}

		OtherCostsInfo[] otherInfos = list5.toArray(new OtherCostsInfo[size]);
		// 存储所有数据之和
		formInfo.setOtherCostsInfo(otherInfos);
		formInfo.setSumTaxiFaresAmount(taxiSum);
		formInfo.setSumOvertimeMealsAmount(overSum);
		formInfo.setSumHospitalityAmount(hosSum);
		formInfo.setSumEmployeeRelationsFees(employSum);
		formInfo.setSumOtherAmount(otherSum);
		formInfo.setCommunicationCosts(OAControllerUtils.yuanMoneyToCent(vars.get("sum6")));
		formInfo.setCommuCostsComment(vars.get("remark"));
		
		long amount = taxiSum + overSum + hosSum + employSum + otherSum + OAControllerUtils.yuanMoneyToCent(vars.get("sum6"));
		formInfo.setMoneyAmount(amount);
		
		long commRatify = 0;
		if (ratify) {
			commRatify = OAControllerUtils.yuanMoneyToCent(vars.get("ratify6"));
			formInfo.setCommunicationNotifyAmount(commRatify);
		}else{
			//commRatify = OAControllerUtils.yuanMoneyToCent(vars.get("sum6"));
			//formInfo.setCommunicationNotifyAmount(commRatify);
		}
		if (ratify) {
			formInfo.setTaxiFaresNotifyAmount(taxiRatify);
			formInfo.setOvertimeMealsNotifyAmount(overRatify);
			formInfo.setHospitalityNotifyAmount(hosRatify);
			formInfo.setEmRelationsFeesNotify(employRatify);
			formInfo.setOtherNotifyAmount(otherRatify);
		}
		long ratifyAmount = taxiRatify + overRatify + hosRatify + employRatify + otherRatify + commRatify;
		long payAmount = OAControllerUtils.yuanMoneyToCent(vars.get("payAmount"));
		formInfo.setPayAmount(payAmount);
		if (ratify) {
			formInfo.setSumFinancialNotify(ratifyAmount);
			if(formInfo.getSumFinancialNotify() > formInfo.getMoneyAmount()){
				throw new NumberFormatException("核定金额不能大于报销金额");
			}
			if(formInfo.getPayAmount() > formInfo.getSumFinancialNotify()){
				throw new NumberFormatException("支付金额不能大于核定金额");
			}
		}

		table = tableMap.get("table");
		len = table.length;
		for (int i = 0; i < len; i++) {
			formInfo.setApplyUser(table[i][0]);
			formInfo.setApplyDate((new Date(System.currentTimeMillis())));
			formInfo.setSerialNumber(table[i][1]);
			formInfo.setFirstDep(table[i][3]);
			formInfo.setApplyDep(table[i][4]);
			formInfo.setDepNum(table[i][5]);
			formInfo.setIsDirectVp(table[i][6]);
			formInfo.setBankNumber(table[i][7]);
			formInfo.setBankName(table[i][8]);
		}
		
		table = tableMap.get("table7");
		double borrowBillBalance = 0;
		if(table != null){
			len = table.length;
			String loans = "";
			for (int i = 0; i < table.length; i++) {
				for(int j = 0; j < table[i].length; j++){
					String _loans = table[i][j];
					String[] tmp = _loans.split(",");
					String b = tmp[4];
					if( NumberUtils.isNumber(b)){
						borrowBillBalance += NumberUtils.toDouble(b);
					}
					loans += _loans + ";";
				}
			}
			if(loans.length() > 0){
				loans = loans.substring(0, loans.length() - 1);
			}
			formInfo.setBorrowSN(loans);
		}
		borrowBillBalance = borrowBillBalance * 100;
		formInfo.setBorrowAmount(NumberUtils.toLong(""+(int)borrowBillBalance));
		if (ratify && borrowBillBalance > ratifyAmount) {
			throw new NumberFormatException("冲销借款金额不能大于核定金额");
		}
		if (!ratify && borrowBillBalance > amount) {
			throw new NumberFormatException("冲销借款金额不能大于报销金额");
		}
		return true;
	}

	/**
	 * 获取所有datatable应该展示的内容
	 * 
	 * @param formInfoList
	 * @param userId
	 * @return
	 * @throws RemoteAccessException
	 */
	private DataResult getAllTableInfos(FormInfoList formInfoList,
			String userId, int id) throws RemoteAccessException {
		DataResult dataResult = new DataResult();
		List<FormInfo> formInfos = formInfoList.getFormInfos();
		int size = formInfos.size();
		List<String[]> tableInfos = new ArrayList<String[]>();
		Boolean ratify = null;
		for (int i = 0; i < size; i++) {
			FormInfo formInfo = formInfos.get(i);
			String tk = formInfo.getTaskKey();
			if ("fin_check".equals(tk) || "fin_check_mdd".equals(tk)) {
				if (ratify == null) {
					ratify = this.groupManager.inGroups(new String[] {"fin_check", "fin_check_mdd" }, userId);
				}
				formInfo.setIsRatify(ratify);
			}
			if(formInfo.isEndorse() && userId.equals(formInfo.getStartMemberId())){
				String eu = this.logManager.getLastEndorseUser(formInfo.getId(), formInfo.getTaskKey());
				if(eu != null){
					formInfo.setEndorse(eu);
					formInfo.setIsOwner(true);
				}
			}
			String tableInfo[] = getEveryTableInfo(formInfo, id);
			tableInfos.add(tableInfo);
		}
		dataResult.setCount(formInfoList.getCount());
		dataResult.setTableInfos(tableInfos);
		return dataResult;
	}

	/**
	 * 统一返回中间不同处理
	 * 
	 * @param formInfo
	 * @param depart
	 * @param id
	 * @return
	 */
	private String[] getEveryTableInfo(FormInfo formInfo, int id) {
		String tableInfo[] = {};
		if (id == 1) {
			tableInfo = new String[] { 
					String.valueOf(formInfo.getId()),
					OAControllerUtils.dateToStr(formInfo.getStartDate()),
					String.valueOf(formInfo.getMoneyAmount()) 
					};
		} else if (id == 2) {
			tableInfo = new String[11];
			tableInfo[0] = String.valueOf(formInfo.getId());
			tableInfo[1] = formInfo.getApplyUser();
			tableInfo[2] = formInfo.getApplyDep();
			tableInfo[3] = OAControllerUtils.dateToStr(formInfo.getStartDate());
			tableInfo[4] = String.valueOf(formInfo.getMoneyAmount());
			tableInfo[5] = formInfo.getTaskId();
			tableInfo[6] = formInfo.isRatify() ? "true" : "false";
			tableInfo[7] = (formInfo.isOwner() && formInfo.isEndorse()) ? "true" : "false";
			tableInfo[8] = String.valueOf(formInfo.getEndorseUser());
			tableInfo[9] = formInfo.getTaskKey();
			tableInfo[10] = OAControllerUtils.dateToStr(formInfo.getTaskCreateTime());
		} else if (id == 3) {
			tableInfo = new String[7];
			tableInfo[0] = String.valueOf(formInfo.getId());
			tableInfo[1] = formInfo.getApplyUser();
			tableInfo[2] = formInfo.getApplyDep();
			tableInfo[3] = OAControllerUtils.dateToStr(formInfo.getStartDate());
			tableInfo[5] = String.valueOf(formInfo.getMoneyAmount());
			tableInfo[6] = OAControllerUtils.dateToStr(formInfo.getTaskCreateTime());
			//tableInfo[7] =  this.groupManager.inGroups(new String[] {"fin_check", "fin_check_mdd"}, userId);
		} else if (id == 4) {
			tableInfo = new String[] { 
					String.valueOf(formInfo.getOid()),
					OAControllerUtils.dateToStr(formInfo.getStartDate()),
					String.valueOf(formInfo.getMoneyAmount()) 
					};
		} else if (id == 5) {
			tableInfo = new String[] { 
					String.valueOf(formInfo.getId()),
					OAControllerUtils.dateToStrII(formInfo.getApplyDate()),
					String.valueOf(formInfo.getMoneyAmount()) 
					};
		} else if (id == 6) {
			String stat = "审批中";
			if(formInfo.getFinishedflag() == 1){
				stat = "已完成";
			}else if(formInfo.getFinishedflag() == 3){
				stat = "主动删除";
			}else if(formInfo.getFinishedflag() == 5){
				stat = "草稿";
			}else if(formInfo.getFinishedflag() == 6){
				stat = "被拒绝";
			}else if(formInfo.getFinishedflag() == 7){
				stat = "主动取消";
			}
			tableInfo = new String[] { 
					String.valueOf(formInfo.getId()),
					formInfo.getApplyUser(),
					OAControllerUtils.dateToStrII(formInfo.getApplyDate()),
					formInfo.getFinancialSign(),
					OAControllerUtils.dateToStrII(formInfo.getFinancialDate()),
					formInfo.getCashierSign(),
					OAControllerUtils.dateToStrII(formInfo.getCashierDate()),
					String.valueOf(formInfo.getMoneyAmount()),
					stat
					};
		}
		return tableInfo;
	}

	/**
	 * 构造历史页面需要展示的各种信息
	 * 
	 * @param formInfo
	 * @return FormRequest
	 */
	private FormRequest getApplyDetailInfo(FormInfo formInfo, String[] approveInfo) {
		FormRequest formRequest = new FormRequest();
		Map<String, String[][]> tableMap = new HashMap<String, String[][]>();
		String table[][] = createTableFirstInfo(formInfo);
		tableMap.put("table", table);
		table = createTableLastInfo(formInfo);
		tableMap.put("table7", table);
		TaxiFaresInfo infos1[] = formInfo.getTaxiFaresInfo();
		int len = infos1.length;
		if (len == 0) {
			table = new String[0][0];
		} else {
			table = new String[len][101];
			for (int i = 0; i < len; i++) {
				table[i][100] = String.valueOf(infos1[i].getId());
				table[i][0] = OAControllerUtils.dateToStrII(infos1[i]
						.getTaxiFaresDate());
				table[i][1] = infos1[i].getTaxiFaresAddr();
				table[i][2] = infos1[i].getTaxiFaresDest();
				table[i][3] = infos1[i].getTaxiFaresTime();
				table[i][4] = infos1[i].getTaxiFaresUse();
				table[i][5] = infos1[i].getTaxiFaresPeerPeople();
				table[i][6] = String.valueOf(infos1[i].getTaxiFaresWorkhour());
				String eMoney = OAControllerUtils.centMoneyToYuan(infos1[i].getTaxiFaresAmount());
				table[i][7] = eMoney;
				table[i][8] = infos1[i].getRatify()==null?eMoney:OAControllerUtils.centMoneyToYuan(infos1[i].getRatify());
				table[i][9] = infos1[i].getComment();
			}
		}
		tableMap.put("table1", table);

		OvertimeMealsInfo infos2[] = formInfo.getOvertimeMealsInfo();
		len = infos2.length;
		if (len == 0) {
			table = new String[0][0];
		} else {
			table = new String[len][101];
			for (int i = 0; i < len; i++) {
				table[i][100] = String.valueOf(infos2[i].getId());
				table[i][0] = OAControllerUtils.dateToStrII(infos2[i]
						.getOvertimeMealsDate());
				table[i][1] = infos2[i].getMealsAddr();
				table[i][2] = infos2[i].getOvertimeMealsPeerPeople();
				table[i][3] = String.valueOf(infos2[i].getMealsPersonNum());
				String money1 = OAControllerUtils.centMoneyToYuan(infos2[i]
						.getOvertimeMealsAmount());
				table[i][4] = money1;
				String money2 = OAControllerUtils.centMoneyToYuan(infos2[i]
						.getPerMealsFee());
				table[i][5] = money2;
				table[i][6] = String.valueOf(infos2[i].getInvoiceAmount());
				table[i][7] = String.valueOf(infos2[i]
						.getOvertimeMealsWorkhours());
				table[i][8] = infos2[i].getRatify()==null?money1:OAControllerUtils.centMoneyToYuan(infos2[i].getRatify());
				table[i][9] = infos2[i].getOvertimeMealsComment();
			}
		}
		tableMap.put("table2", table);

		HospitalityInfo infos3[] = formInfo.getHospitalityInfo();
		len = infos3.length;
		if (len == 0) {
			table = new String[0][0];
		} else {
			table = new String[len][101];
			for (int i = 0; i < len; i++) {
				table[i][100] = String.valueOf(infos3[i].getId());
				table[i][0] = OAControllerUtils.dateToStrII(infos3[i]
						.getHospitalityDate());
				table[i][1] = infos3[i].getHospitalityAddr();
				table[i][2] = infos3[i].getBusinessPurpose();
				table[i][3] = infos3[i].getCustomCompany();
				table[i][4] = infos3[i].getCustomName();
				table[i][5] = infos3[i].getHospitalityNum();
				String eMoney = OAControllerUtils.centMoneyToYuan(infos3[i]
						.getHospitalityAmount());
				table[i][6] = eMoney;
				table[i][7] = infos3[i].getRatify()==null?eMoney:OAControllerUtils.centMoneyToYuan(infos3[i].getRatify());
				table[i][8] = infos3[i].getMemo();
			}
		}
		tableMap.put("table3", table);

		EmployeeRelationsFeesInfo infos4[] = formInfo
				.getEmployeeRelationsFeesInfo();
		len = infos4.length;
		if (len == 0) {
			table = new String[0][0];
		} else {
			table = new String[len][101];
			for (int i = 0; i < len; i++) {
				table[i][100] = String.valueOf(infos4[i].getId());
				table[i][0] = OAControllerUtils.dateToStrII(infos4[i]
						.getEmRelationsDate());
				table[i][1] = infos4[i].getEmRelationsAddress();
				table[i][2] = infos4[i].getEmRelationsPeerPeople();
				table[i][3] = infos4[i].getActDest();
				String eMoney = OAControllerUtils.centMoneyToYuan(infos4[i]
						.getEmRelationsFees());
				table[i][4] = eMoney;
				table[i][5] = infos4[i].getRatify()==null?eMoney:OAControllerUtils.centMoneyToYuan(infos4[i].getRatify());
				table[i][6] = infos4[i].getEmRelationsFeesComment();
			}
		}
		tableMap.put("table4", table);

		OtherCostsInfo infos5[] = formInfo.getOtherCostsInfo();
		len = infos5.length;
		if (len == 0) {
			table = new String[0][0];
		} else {
			table = new String[len][101];
			for (int i = 0; i < len; i++) {
				table[i][100] = String.valueOf(infos5[i].getId());
				table[i][0] = infos5[i].getOtherCostProject();
				String eMoney = OAControllerUtils.centMoneyToYuan(infos5[i]
						.getOtherCostAmount());
				table[i][1] = String.valueOf(eMoney);
				table[i][2] = infos5[i].getRatify()==null?eMoney:OAControllerUtils.centMoneyToYuan(infos5[i].getRatify());
				table[i][3] = infos5[i].getOtherCostComment();
			}
		}
		tableMap.put("table5", table);
		
		String borrowSN = formInfo.getBorrowSN();
		len = 0;
		String[][] borrowSNs = new String[0][0];
		if(borrowSN != null && borrowSN.length() > 0){
			String[] tmp = borrowSN.split(";");
			borrowSNs = new String[tmp.length][5];
			len = tmp.length;
			for (int i = 0; i < len; i++) {
				String[] split = tmp[i].split(",");
				borrowSNs[i][0] = split[0];
				borrowSNs[i][1] = split[1];
				borrowSNs[i][2] = split[2];
				borrowSNs[i][3] = split[3];
				borrowSNs[i][4] = split[4];
			}
		}
		tableMap.put("table7", borrowSNs);

		Map<String, String> vars = new HashMap<String, String>();

		String moneySum1 = OAControllerUtils.centMoneyToYuan(formInfo
				.getSumTaxiFaresAmount());
		vars.put("sum1", moneySum1);
		String moneySum2 = OAControllerUtils.centMoneyToYuan(formInfo
				.getSumOvertimeMealsAmount());
		vars.put("sum2", moneySum2);
		String moneySum3 = OAControllerUtils.centMoneyToYuan(formInfo
				.getSumHospitalityAmount());
		vars.put("sum3", moneySum3);
		String moneySum4 = OAControllerUtils.centMoneyToYuan(formInfo
				.getSumEmployeeRelationsFees());
		vars.put("sum4", moneySum4);
		String moneySum5 = OAControllerUtils.centMoneyToYuan(formInfo
				.getSumOtherAmount());
		vars.put("sum5", moneySum5);
		String moneySum = OAControllerUtils.centMoneyToYuan(formInfo
				.getMoneyAmount());
		vars.put("sum", moneySum);
		String moneySum6 = OAControllerUtils.centMoneyToYuan(formInfo
				.getCommunicationCosts());
		vars.put("sum6", moneySum6);
		vars.put("remark", formInfo.getCommuCostsComment()==null?moneySum6:formInfo.getCommuCostsComment());
		
		String m = OAControllerUtils.centMoneyToYuan(formInfo.getTaxiFaresNotifyAmount());
		vars.put("ratify1", formInfo.getTaxiFaresNotifyAmount()==null?moneySum1:m);
		m = OAControllerUtils.centMoneyToYuan(formInfo.getOvertimeMealsNotifyAmount());
		vars.put("ratify2", formInfo.getOvertimeMealsNotifyAmount()==null?moneySum2:m);
		m = OAControllerUtils.centMoneyToYuan(formInfo.getHospitalityNotifyAmount());
		vars.put("ratify3", formInfo.getHospitalityNotifyAmount()==null?moneySum3:m);
		m = OAControllerUtils.centMoneyToYuan(formInfo.getEmRelationsFeesNotify());
		vars.put("ratify4", formInfo.getEmRelationsFeesNotify()==null?moneySum4:m);
		m = OAControllerUtils.centMoneyToYuan(formInfo.getOtherNotifyAmount());
		vars.put("ratify5", formInfo.getOtherNotifyAmount()==null?moneySum5:m);
		m = OAControllerUtils.centMoneyToYuan(formInfo.getCommunicationNotifyAmount());
		vars.put("ratify6", formInfo.getCommunicationNotifyAmount()==null?moneySum6:m);
		
		String ratify = OAControllerUtils.centMoneyToYuan(formInfo.getSumFinancialNotify());
		vars.put("ratify", formInfo.getSumFinancialNotify()==null?moneySum:ratify);
		vars.put("payAmount", formInfo.getPayAmount() == null?moneySum:OAControllerUtils.centMoneyToYuan(formInfo.getPayAmount()));
		vars.put("borrowAmount", formInfo.getBorrowAmount() == null?moneySum:OAControllerUtils.centMoneyToYuan(formInfo.getBorrowAmount()));
		formRequest.setTableMap(tableMap);
		formRequest.setVars(vars);
		formRequest.setFlag(false);
		
		String aInfo = "";
		if(approveInfo != null)for(String info : approveInfo){
			if(info == null) continue;
			aInfo += info + "<br/>";
		}
		vars.put("approveInfo", aInfo);
		
		formRequest.setFiles(this.form0114Manager.getFiles(formInfo.getId()));
		
		return formRequest;
	}

	/**
	 * 历史页面展示信息 第一个table
	 * 
	 * @param formInfo
	 * @return
	 */
	private String[][] createTableFirstInfo(FormInfo formInfo) {
		String table[][] = new String[1][13];
		String acTable[] = new String[13];
		acTable[0] = formInfo.getApplyUser();
		acTable[1] = formInfo.getSerialNumber();
		acTable[2] = OAControllerUtils.dateToStrII(formInfo.getApplyDate());
		acTable[3] = formInfo.getFirstDep();
		acTable[4] = formInfo.getApplyDep();
		acTable[5] = formInfo.getDepNum();
		acTable[6] = formInfo.getIsDirectVp();
		acTable[7] = formInfo.getBankNumber();
		acTable[8] = formInfo.getBankName();
		acTable[9] = formInfo.getIsBorrow();
		acTable[10] = formInfo.getBorrowSN();
		acTable[11] = String.valueOf(formInfo.getBorrowAmount());
		acTable[12] = String.valueOf(formInfo.getPayAmount());
		table[0] = acTable;
		return table;
	}

	/**
	 * 历史页面展示信息,最后一个table
	 * 
	 * @param formInfo
	 * @return
	 */
	private String[][] createTableLastInfo(FormInfo formInfo) {
		String table[][] = new String[8][2];
		table[0][0] = formInfo.getDepLeaderOpinion();
		table[0][1] = OAControllerUtils.dateToStr(formInfo.getDepLeaderDate());
		table[1][0] = formInfo.getDepDirectorOpinion();
		table[1][1] = OAControllerUtils
				.dateToStr(formInfo.getDepDirectorDate());
		table[2][0] = formInfo.getSupDepLeaderOpinion();
		table[2][1] = OAControllerUtils.dateToStr(formInfo
				.getSupDepLeaderDate());
		table[3][0] = formInfo.getFinancialDirectorOption();
		table[3][1] = OAControllerUtils.dateToStr(formInfo
				.getFinancialDirectorDate());
		table[4][0] = formInfo.getCeoOption();
		table[4][1] = OAControllerUtils.dateToStr(formInfo.getCeoDate());
		table[5][0] = formInfo.getCeoOption1();
		table[5][1] = OAControllerUtils.dateToStr(formInfo.getCeoDate1());
		table[6][0] = formInfo.getReimbursementTeamName();
		table[6][1] = OAControllerUtils.dateToStr(formInfo
				.getReimbursementTeamDate());
		table[7][0] = formInfo.getCashierOpinion();
		table[7][1] = OAControllerUtils.dateToStr(formInfo.getCashierDate());
		return table;
	}

	/**
	 * 获取部门信息,拼接成五级部门
	 * 
	 * @param employeeInfo
	 * @return
	 */
	private String getDepartMent(EmployeeInfo employeeInfo) {
		String depart = employeeInfo.getDepartmentI();
		String other = employeeInfo.getDepartmentII();
		if (!OAControllerUtils.isNull(other)) {
			depart += "-" + other;
		}
		other = employeeInfo.getDepartmentIII();
		if (!OAControllerUtils.isNull(other)) {
			depart += "-" + other;
		}
		other = employeeInfo.getDepartmentIV();
		if (!OAControllerUtils.isNull(other)) {
			depart += "-" + other;
		}
		other = employeeInfo.getDepartmentV();
		if (!OAControllerUtils.isNull(other)) {
			depart += "-" + other;
		}
		return depart;
	}
	/**
	 * 测试邮件
	 * @author lee.guo
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/sendTestMail")
	@ResponseBody
	public BaseResult sendTestMail(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		String addressee = vars.get("addressee");
		String ccAddressee = vars.get("ccAddressee");
		String form = "baoxiao@qunar.com";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String now = sdf.format(new Date());
		String content = addressee+"，这是一封测试邮件，请忽略。";
		mailSenderService.sender(form, new String[]{addressee+"@qunar.com"}, new String[]{ccAddressee+"@qunar.com"}, addressee+"，测试邮件，如有打扰尽请见谅",content+"[http://qunar.it]");
		return BaseResult.getSuccessResult("");
	}

	@RequestMapping(value = "oa/payTest")
	@ResponseBody
	public BaseResult test() {
		String a = paymentQscheduleService.execute();
		System.out.println(a);
		return BaseResult.getSuccessResult("");
	}
	
	/**
	 * 出纳自动审批
	 * @param ioaEngineService2 
	 * 
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "oa/auto")
	@ResponseBody
	public  DataResult auto() {
		String userId = "出纳";
		// 默认一页显示50条数据
		int pageSize = 1000;
		int pageNo = 1;
		FormInfoList formInfoList = null;
		try {
			formInfoList = ioaEngineService.todoList(processKey, userId, null, null, null, pageNo, pageSize);
		} catch (FormNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		DataResult dataResult = null;
		try {
			dataResult = getAllTableInfos(formInfoList, userId, 2);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return dataResult;
	}
	
	/**
	 * 出纳批量完成任务
	 * 
	 * @param 
	 */
	@ResponseBody
	public void approvePass(List<Long> formIdList,List<String> taskIdList) {
		List<Long> errorFormIds = ioaEngineService.batchPass(processKey,
				"出纳", "出纳", formIdList, taskIdList, "出纳审批完成");
		//int size = errorFormIds.size();
	}
	
	@RequestMapping("oa/qmonitor.jsp")
	public String qmonitor(HttpServletResponse response)
            throws Exception {
		PrintWriter out = response.getWriter(); 
		for (Entry<String, Long> entry : QMonitor.getValues().entrySet()) {

			String name = entry.getKey();

			Long value = entry.getValue();

			out.print(name + "=" + value + "\n");

			}
		return "qmonitor";
	}
	@RequestMapping("oa/search_rtx")
	public void search_rtx(HttpServletResponse response,HttpServletRequest request)
			throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		String key = request.getParameter("key");
		JSONArray json = this.employeeInfoService.getSearchRtx(key);
		System.out.println(json.toString());
		PrintWriter out = response.getWriter(); 
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 获得待办工作数量
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/getTodoCount")
	@ResponseBody
	public DataResult getTodoCount(HttpServletRequest request,
			@RequestBody CommonRequest commonRequest, HttpServletResponse response) {
		Map<String, String> vars = commonRequest.getVars();
		String userId = vars.get("user");
		int pageSize = 1000;
		int pageNo = 1;
		FormInfoList formInfoList = null;
		try {
			formInfoList = ioaEngineService.todoList(processKey, userId, null, null, null, pageNo, pageSize);
		} catch (FormNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		DataResult dataResult = null;
		try {
			dataResult = getAllTableInfos(formInfoList, userId, 2);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return dataResult;
	}
}
