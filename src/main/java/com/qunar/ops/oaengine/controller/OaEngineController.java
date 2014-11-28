package com.qunar.ops.oaengine.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qunar.ops.oaengine.result.*;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.juel.ObjectValueExpression;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qunar.ops.oaengine.exception.CompareModelException;
import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.WorkflowManager;
import com.qunar.ops.oaengine.service.IOAEngineService;
import com.qunar.ops.oaengine.service.MailSenderService;
import com.qunar.ops.oaengine.result.dailysubmit.EmployeeRelationsFeesInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfoList;
import com.qunar.ops.oaengine.result.dailysubmit.HospitalityInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OtherCostsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OvertimeMealsInfo;
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

	/**
	 * login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/oa/login")
	public String login(HttpServletRequest request) {
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
				String userId = parseObject.getJSONObject("data").getString(
						"userId");
				request.getSession().setAttribute("USER_ID", userId);
			} else {
				return "redirect:/oa/index.html";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("sso 验证失败", e);
		}
		return "redirect:/oa/my_apply_todo.html";
	}

	/**
	 * index
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/oa/index.html")
	public ModelAndView welcom(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("USER_ID");
		if (userId != null) {
			return addApply(request);
		}
		ModelAndView mav = new ModelAndView("/oa/index");
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
		return mav;
	}

	/**
	 * 我的申请和已在审批中的报销页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/my_apply_todo.html")
	public ModelAndView myApplyTodo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("oa/my_apply_todo");
		return mav;
	}

	/**
	 * 我的申请和已在审批中的报销页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/my_apply_history.html")
	public ModelAndView myApplyHistory(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("oa/my_apply_history");
		return mav;
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
		String result[] = new String[] { employeeInfo.getAdName(),
				employeeInfo.getSn(),
				sdf.format(new Date(System.currentTimeMillis())),
				employeeInfo.getDepartmentI(), employeeInfo.getDepartmentIV() };
		return BaseResult.getSuccessResult(result);
	}

	@RequestMapping(value = "oa/laborhour")
	@ResponseBody
	public BaseResult webLaborHour(HttpServletRequest request,
			@RequestBody LaborRequest laborRequest) {
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
			laborHour = 0;
		}
		String result[] = new String[] { String.valueOf(laborHour) };
		return BaseResult.getSuccessResult(result);
	}

	@RequestMapping(value = "oa/data")
	@ResponseBody
	public BaseResult webPostData(HttpServletRequest request,
			@RequestBody WebRequest webRequest) {
		String userId = (String) request.getSession().getAttribute("USER_ID");
		if (userId == null || userId.length() == 0) {
			logger.warn("登陆用户为空，无法获取员工信息");
			return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
		}
		Map<String, String> vars = webRequest.getVars();
		Map<String, String[][]> tableMap = webRequest.getTableMap();
		boolean createFlag = true;
		FormInfo formInfo = new FormInfo();
		try {
			createFlag = constructFormInfo(formInfo, tableMap, vars);
		} catch (ParseException e) {
			e.printStackTrace();
			String errorMsg = "日期字段填写错误，请检查";
			logger.warn(errorMsg);
			return BaseResult.getErrorResult(-2, errorMsg);
		}
		if (!createFlag) {
			String errorMsg = "没有任何报销内容，请检查";
			logger.warn(errorMsg);
			return BaseResult.getErrorResult(-1, errorMsg);
		}
		String processKey = "oa_common";
		boolean flag = webRequest.isFlag();

		long id = 0;
		if (flag) {
			try {
				ioaEngineService.createFormAndstart(processKey, userId,
						formInfo);
			} catch (RemoteAccessException e) {
				e.printStackTrace();
				String errorMsg = "ACL限制，获取员工信息失败";
				logger.warn(errorMsg);
				return BaseResult.getErrorResult(-1, errorMsg);
			} catch (CompareModelException e) {
				e.printStackTrace();
				String errorMsg = "日期字段填写错误，请检查";
				logger.warn(errorMsg);
				return BaseResult.getErrorResult(-1, errorMsg);
			} catch (FormNotFoundException e) {
				e.printStackTrace();
				String errorMsg = "表单未找到，请检查！";
				logger.warn(errorMsg);
				return BaseResult.getErrorResult(-1, errorMsg);
			}
		} else {
			ioaEngineService.createForm(processKey, userId, formInfo);
		}
		return BaseResult.getSuccessResult(String.valueOf(id));
	}

	private boolean constructFormInfo(FormInfo formInfo,
			Map<String, String[][]> tableMap, Map<String, String> vars)
			throws ParseException {
		// 存储各个table的数据。
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String table[][] = tableMap.get("table1");
		int len = table.length;
		List<TaxiFaresInfo> list1 = new ArrayList<TaxiFaresInfo>();
		for (int i = 0; i < len; i++) {
			if ("".equals(table[i][0]) || "".equals(table[i][7])) {
				continue;
			}
			TaxiFaresInfo taxiInfo = new TaxiFaresInfo();
			taxiInfo.setTaxiFaresDate(sdf.parse(table[i][0]));
			taxiInfo.setTaxiFaresAddr(table[i][1]);
			taxiInfo.setTaxiFaresDest(table[i][2]);
			taxiInfo.setTaxiFaresTime(table[i][3]);
			taxiInfo.setTaxiFaresTimeNew(table[i][3]);
			taxiInfo.setTaxiFaresUse(table[i][4]);
			taxiInfo.setTaxiFaresPeerPeople(table[i][5]);
			taxiInfo.setTaxiFaresWorkhour(new BigDecimal(table[i][6]));
			Float money = Float.parseFloat(table[i][7]);
			String sMoney = String.valueOf((int) (money * 100));
			taxiInfo.setTaxiFaresAmount(Long.parseLong(sMoney));
			taxiInfo.setComment(table[i][8]);
			list1.add(taxiInfo);
		}
		int size = list1.size();
		TaxiFaresInfo[] taxiInfos = list1
				.toArray(new TaxiFaresInfo[size]);
		formInfo.setTaxiFaresInfo(taxiInfos);

		table = tableMap.get("table2");
		len = table.length;
		List<OvertimeMealsInfo> list2 = new ArrayList<OvertimeMealsInfo>();
		for (int i = 0; i < len; i++) {
			if ("".equals(table[i][0]) || "".equals(table[i][5])) {
				continue;
			}
			OvertimeMealsInfo overInfo = new OvertimeMealsInfo();
			overInfo.setOvertimeMealsDate(sdf.parse(table[i][0]));
			overInfo.setMealsAddr(table[i][1]);
			overInfo.setOvertimeMealsPeerPeople(table[i][2]);
			overInfo.setMealsPersonNum(Long.parseLong(table[i][3]));
			Float money = Float.parseFloat(table[i][4]);
			String sMoney = String.valueOf((int) (money * 100));
			overInfo.setOvertimeMealsAmount(Long.parseLong(sMoney));
			overInfo.setPerMealsFee((long) (Float.parseFloat(table[i][5]) * 100));
			overInfo.setInvoiceAmount(table[i][6]);
			overInfo.setOvertimeMealsWorkhours(new BigDecimal(table[i][7]));
			overInfo.setOvertimeMealsComment(table[i][8]);
			list2.add(overInfo);
		}
		size = list2.size();
		OvertimeMealsInfo[] overInfos = list2
				.toArray(new OvertimeMealsInfo[size]);
		formInfo.setOvertimeMealsInfo(overInfos);

		table = tableMap.get("table3");
		len = table.length;
		List<HospitalityInfo> list3 = new ArrayList<HospitalityInfo>();
		for (int i = 0; i < len; i++) {
			if ("".equals(table[i][0]) || "".equals(table[i][6])) {
				continue;
			}
			HospitalityInfo hosInfo = new HospitalityInfo();
			hosInfo.setHospitalityDate(sdf.parse(table[i][0]));
			hosInfo.setHospitalityAddr(table[i][1]);
			hosInfo.setBusinessPurpose(table[i][2]);
			hosInfo.setCustomCompany(table[i][3]);
			hosInfo.setCustomName(table[i][4]);
			hosInfo.setHospitalityNum(table[i][5]);
			Float money = Float.parseFloat(table[i][6]);
			String sMoney = String.valueOf((int) (money * 100));
			hosInfo.setHospitalityAmount(Long.parseLong(sMoney));
			list3.add(hosInfo);
		}
		size = list3.size();
		HospitalityInfo[] hosInfos = list3
				.toArray(new HospitalityInfo[size]);
		formInfo.setHospitalityInfo(hosInfos);

		table = tableMap.get("table4");
		len = table.length;
		List<EmployeeRelationsFeesInfo> list4 = new ArrayList<EmployeeRelationsFeesInfo>();
		for (int i = 0; i < len; i++) {
			if ("".equals(table[i][0]) || "".equals(table[i][4])) {
				continue;
			}
			EmployeeRelationsFeesInfo employInfo = new EmployeeRelationsFeesInfo();
			employInfo.setEmRelationsDate(sdf.parse(table[i][0]));
			employInfo.setEmRelationsAddress(table[i][1]);
			employInfo.setEmRelationsPeerPeople(table[i][2]);
			employInfo.setActDest(table[i][3]);
			Float money = Float.parseFloat(table[i][4]);
			String sMoney = String.valueOf((int) (money * 100));
			employInfo.setEmRelationsFees(Long.parseLong(sMoney));
			employInfo.setEmRelationsFeesComment(table[i][5]);
			list4.add(employInfo);

		}
		size = list4.size();
		EmployeeRelationsFeesInfo[] employInfos = list4
				.toArray(new EmployeeRelationsFeesInfo[size]);
		formInfo.setEmployeeRelationsFeesInfo(employInfos);

		table = tableMap.get("table5");
		len = table.length;
		List<OtherCostsInfo> list5 = new ArrayList<OtherCostsInfo>();
		for (int i = 0; i < len; i++) {
			if ("".equals(table[i][0]) || "".equals(table[i][2])) {
				continue;
			}
			OtherCostsInfo otherInfo = new OtherCostsInfo();
			otherInfo.setOtherCostProject(table[i][0]);
			Float money = Float.parseFloat(table[i][1]);
			String sMoney = String.valueOf((int) (money * 100));
			otherInfo.setOtherCostAmount(Long.parseLong(sMoney));
			otherInfo.setOtherCostComment(table[i][2]);
			list5.add(otherInfo);
		}
		size = list5.size();
		// 如果全部数据都为空的话，就不存了。

		if (list1.size() == 0 && list2.size() == 0 && list3.size() == 0
				&& list4.size() == 0 && list5.size() == 0) {
			return false;
		}

		OtherCostsInfo[] otherInfos = list5
				.toArray(new OtherCostsInfo[size]);
		formInfo.setOtherCostsInfo(otherInfos);
		// 存储所有数据之和
		formInfo.setSumTaxiFaresAmount((long)(Float.parseFloat(vars.get("sum1")) * 100));
		formInfo.setSumOvertimeMealsAmount((long)(Float.parseFloat(vars.get("sum2")) * 100));
		formInfo.setSumHospitalityAmount((long)(Float.parseFloat(vars.get("sum3")) * 100));
		formInfo.setSumEmployeeRelationsFees((long)(Float.parseFloat(vars.get("sum4")) * 100));
		formInfo.setSumOtherAmount((long)(Float.parseFloat(vars.get("sum5")) * 100));

		formInfo.setCommunicationCosts((long)(Float.parseFloat(vars.get("sum6")) * 100));
		formInfo.setCommuCostsComment(vars.get("remark"));

		formInfo.setMoneyAmount((long)(Float.parseFloat(vars.get("sum")) * 100));
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
			formInfo.setIsBorrow(table[i][9]);
			formInfo.setSerialNumber(table[i][10]);
			if (isNull(table[i][11])) {
				formInfo.setBorrowAmount(0l);
			} else {
				formInfo.setBorrowAmount(Long.parseLong(table[i][11]));
			}
		}
		return true;
	}

	@RequestMapping(value = "oa/todo")
	@ResponseBody
	public BaseResult getAllMyApplyTodoList(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("USER_ID");
		if (userId == null || userId.length() == 0) {
			logger.warn("登陆用户为空，无法获取员工信息");
			return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
		}
		int iDisplayStart = Integer.parseInt(request
				.getParameter("iDisplayStart"));
		int iDisplayLength = Integer.parseInt(request
				.getParameter("iDisplayLength"));
		System.out.println(iDisplayStart);
		System.out.println(iDisplayLength);
		String processKey = "oa_common";
		// Date startTime, Date endTime,
		// 默认一页显示50条数据
		int pageSize = 50;
		int pageNo = iDisplayStart / 50 + 1;
		FormInfoList formInfoList = ioaEngineService.getUserApplyList(
				processKey, userId, null, null, pageNo, pageSize);
		DataResult dataResult;
		try {
			dataResult = getTableInfos(formInfoList, userId);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			String errorMsg = "ACL限制，获取员工信息失败";
			logger.warn(errorMsg);
			return BaseResult.getErrorResult(-2, errorMsg);
		}
		return BaseResult.getSuccessResult(dataResult);
	}

	@RequestMapping(value = "oa/history")
	@ResponseBody
	public BaseResult getAllMyApplyHistoryList(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("USER_ID");
		if (userId == null || userId.length() == 0) {
			logger.warn("登陆用户为空，无法获取员工信息");
			return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
		}
		int iDisplayStart = Integer.parseInt(request
				.getParameter("iDisplayStart"));
		int iDisplayLength = Integer.parseInt(request
				.getParameter("iDisplayLength"));
		String processKey = "oa_common";
		// Date startTime, Date endTime,
		// 默认一页显示50条数据
		int pageSize = 50;
		int pageNo = iDisplayStart / 50 + 1;
		FormInfoList formInfoList = ioaEngineService.getUserApplyHisList(
				processKey, userId, null, null, pageNo, pageSize);
		DataResult dataResult;
		try {
			dataResult = getTableInfos(formInfoList, userId);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			String errorMsg = "ACL限制，获取员工信息失败";
			logger.warn(errorMsg);
			return BaseResult.getErrorResult(-2, errorMsg);
		}
		return BaseResult.getSuccessResult(dataResult);
	}

	@RequestMapping(value = "oa/approve_todo")
	@ResponseBody
	public BaseResult getAllApproveTodoList(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("USER_ID");
		if (userId == null || userId.length() == 0) {
			logger.warn("登陆用户为空，无法获取员工信息");
			return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
		}
		int iDisplayStart = Integer.parseInt(request
				.getParameter("iDisplayStart"));
		int iDisplayLength = Integer.parseInt(request
				.getParameter("iDisplayLength"));
		String processKey = "oa_common";
		// Date startTime, Date endTime,
		// 默认一页显示50条数据
		int pageSize = 50;
		int pageNo = iDisplayStart / 50 + 1;
		FormInfoList formInfoList = null;
		try {
			formInfoList = ioaEngineService.todoList(
					processKey, userId, null, null, null, pageNo, pageSize);
		} catch (FormNotFoundException e) {
			e.printStackTrace();
			String errorMsg = "ACL限制，获取员工信息失败";
			logger.warn(errorMsg);
			ApproveResult approveResult = new ApproveResult();
			return BaseResult.getSuccessResult(approveResult);
		}
		ApproveResult approveResult = new ApproveResult();
//		try {
//			approveResult = getTableInfos(formInfoList, userId);
//		} catch (RemoteAccessException e) {
//			e.printStackTrace();
//			String errorMsg = "ACL限制，获取员工信息失败";
//			logger.warn(errorMsg);
//			return BaseResult.getErrorResult(-2, errorMsg);
//		}
		return BaseResult.getSuccessResult(approveResult);
	}

	private DataResult getTableInfos(FormInfoList formInfoList, String userId) throws RemoteAccessException{
		DataResult dataResult = new DataResult();
		List<FormInfo> formInfos = formInfoList.getFormInfos();
		int size = formInfos.size();
		List<String[]> tableInfos = new ArrayList<String[]>();
		EmployeeInfo employeeInfo = ioaEngineService.getEmployeeInfo(userId);
		String depart = getDepartMent(employeeInfo);
		String sn = employeeInfo.getSn();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, String>> varsList = new ArrayList<Map<String,String>>();
		List<Map<String, String[][]>> tableMapList = new ArrayList<Map<String,String[][]>>();
		for (int i = 0; i < size; i++) {
			FormInfo formInfo = formInfos.get(i);
			String tableInfo[] = new String[] { sn, depart,
					formInfo.getApplyUser(), sdf.format(formInfo.getApplyDate()),
					String.valueOf(formInfo.getMoneyAmount()) };
			tableInfos.add(tableInfo);
			constructTableMap(formInfo, varsList, tableMapList);
		}
		dataResult.setCount((long) size);
		dataResult.setTableMapList(tableMapList);
		dataResult.setVarsList(varsList);
		dataResult.setTableInfos(tableInfos);
		return dataResult;
	}

	private void constructTableMap(FormInfo formInfo, List<Map<String, String>> varsList,
			List<Map<String, String[][]>> tableMapList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Map<String, String[][]> tableMap = new HashMap<String, String[][]>();
		String table[][] = new String[1][12];
		table[0][0] = formInfo.getApplyUser();
		table[0][1] = formInfo.getSerialNumber();
		table[0][2] = sdf.format(formInfo.getApplyDate());
		table[0][3] = formInfo.getFirstDep();
		table[0][4] = formInfo.getApplyDep();
		table[0][5] = formInfo.getDepNum();
		table[0][6] = formInfo.getIsDirectVp();
		table[0][7] = formInfo.getBankNumber();
		table[0][8] = formInfo.getBankName();
		table[0][9] = formInfo.getIsBorrow();
		table[0][10] = formInfo.getBorrowSN();
		table[0][11] = String.valueOf(formInfo.getBorrowAmount());
		tableMap.put("table", table);
		TaxiFaresInfo infos1[] = formInfo.getTaxiFaresInfo();
		int len = infos1.length;
		if (len == 0) {
			table = new String[0][0];
		}else{
			table = new String[len][9];
			for (int i = 0; i < len; i++) {
				table[i][0] = sdf.format(infos1[i].getTaxiFaresDate());
				table[i][1] = infos1[i].getTaxiFaresAddr();
				table[i][2] = infos1[i].getTaxiFaresDest();
				table[i][3] = infos1[i].getTaxiFaresTime();
				table[i][4] = infos1[i].getTaxiFaresUse();
				table[i][5] = infos1[i].getTaxiFaresPeerPeople();
				table[i][6] = String.valueOf(infos1[i].getTaxiFaresWorkhour());
				String eMoney = transformMoney(infos1[i].getTaxiFaresAmount());
				table[i][7] = eMoney;
				table[i][8] = infos1[i].getComment();
			}
		}
		tableMap.put("table1", table);
		
		OvertimeMealsInfo infos2[] = formInfo.getOvertimeMealsInfo();
		len = infos2.length;
		if (len == 0) {
			table = new String[0][0]; 
		}else{
			table = new String[len][9];
			for (int i = 0; i < len; i++) {
				table[i][0] = sdf.format(infos2[i].getOvertimeMealsDate());
				table[i][1] = infos2[i].getMealsAddr();
				table[i][2] = infos2[i].getOvertimeMealsPeerPeople();
				table[i][3] = String.valueOf(infos2[i].getMealsPersonNum());
				String money1 = transformMoney(infos2[i].getOvertimeMealsAmount());
				table[i][4] = money1;
				String money2 = transformMoney(infos2[i].getPerMealsFee());
				table[i][5] = money2;
				table[i][6] = String.valueOf(infos2[i].getInvoiceAmount());
				table[i][7] = infos2[i].getOvertimeMealsWorkhours().toString();
				table[i][8] = infos2[i].getOvertimeMealsComment();
			}
		}
		tableMap.put("table2", table);
		
		HospitalityInfo infos3[] = formInfo.getHospitalityInfo();
		len = infos3.length;
		if (len == 0) {
			table = new String[0][0]; 
		}else{
			table = new String[len][7];
			for (int i = 0; i < len; i++) {
				table[i][0] = sdf.format(infos3[i].getHospitalityDate());
				table[i][1] = infos3[i].getHospitalityAddr();
				table[i][2] = infos3[i].getBusinessPurpose();
				table[i][3] = infos3[i].getCustomCompany();
				table[i][4] = infos3[i].getCustomName();
				table[i][5] = infos3[i].getHospitalityNum();
				String eMoney = transformMoney(infos3[i].getHospitalityAmount());
				table[i][6] = eMoney;
			}
		}
		tableMap.put("table3", table);
		
		EmployeeRelationsFeesInfo infos4[] = formInfo.getEmployeeRelationsFeesInfo();
		len = infos4.length;
		if (len == 0) {
			table = new String[0][0]; 
		}else{
			table = new String[len][6];
			for (int i = 0; i < len; i++) {
				table[i][0] = sdf.format(infos4[i].getEmRelationsDate());
				table[i][1] = infos4[i].getEmRelationsAddress();
				table[i][2] = infos4[i].getEmRelationsPeerPeople();
				table[i][3] = infos4[i].getActDest();
				String eMoney = transformMoney(infos4[i].getEmRelationsFees());
				table[i][4] = eMoney;
				table[i][5] = infos4[i].getEmRelationsFeesComment();
			}
		}
		tableMap.put("table4", table);
		
		OtherCostsInfo infos5[] = formInfo.getOtherCostsInfo();
		len = infos5.length;
		if (len == 0) {
			table = new String[0][0]; 
		}else{
			table = new String[len][3];
			for (int i = 0; i < len; i++) {
				table[i][0] = infos5[i].getOtherCostProject();
				String eMoney = transformMoney(infos5[i].getOtherCostAmount());
				table[i][1] = String.valueOf(eMoney);
				table[i][2] = infos5[i].getOtherCostComment();
			}
		}
		tableMap.put("table5", table);
		
		Map<String, String> vars = new HashMap<String, String>();

		String moneySum1 = transformMoney(formInfo.getSumTaxiFaresAmount());
		vars.put("sum1", moneySum1);
		String moneySum2 = transformMoney(formInfo.getSumOvertimeMealsAmount());
		vars.put("sum2", moneySum2);
		String moneySum3 = transformMoney(formInfo.getSumHospitalityAmount());
		vars.put("sum3", moneySum3);

		String moneySum4 = transformMoney(formInfo.getSumEmployeeRelationsFees());
		vars.put("sum4", moneySum4);
		String moneySum5 = transformMoney(formInfo.getSumOtherAmount());
		vars.put("sum5", moneySum5);
		String moneySum = transformMoney(formInfo.getMoneyAmount());
		vars.put("sum", moneySum);

		String moneySum6 = transformMoney(formInfo.getCommunicationCosts());
		vars.put("sum6", moneySum6);
		vars.put("remark", formInfo.getCommuCostsComment());

		varsList.add(vars);
		tableMapList.add(tableMap);
	}

	private String getDepartMent(EmployeeInfo employeeInfo) {
		String depart = employeeInfo.getDepartmentI();
		String other = employeeInfo.getDepartmentII();
		if (!isNull(other)) {
			depart += "-" + other;
		}
		other = employeeInfo.getDepartmentIII();
		if (!isNull(other)) {
			depart += "-" + other;
		}
		other = employeeInfo.getDepartmentIV();
		if (!isNull(other)) {
			depart += "-" + other;
		}
		other = employeeInfo.getDepartmentV();
		if (!isNull(other)) {
			depart += "-" + other;
		}
		return depart;
	}
	
	private boolean isNull(String value){
		if (value == null || "".equals(value)) {
			return true;
		}
		return false;
	}

	private String transformMoney(long money){
		DecimalFormat ndf = new DecimalFormat("##0.00");
		double sMoney = (double)money / 100;
		String eMoney = ndf.format(sMoney);
		return eMoney;
	}
}
