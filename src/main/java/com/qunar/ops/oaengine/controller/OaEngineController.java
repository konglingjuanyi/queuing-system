package com.qunar.ops.oaengine.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
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
import com.qunar.ops.oaengine.result.DataResult;
import com.qunar.ops.oaengine.result.LaborRequest;
import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.result.TaskResult;
import com.qunar.ops.oaengine.result.WebRequest;
import com.qunar.ops.oaengine.service.IOAEngineService;
import com.qunar.ops.oaengine.service.MailSenderService;
import com.qunar.ops.oaengine.result.BaseResult;
import com.qunar.ops.oaengine.result.EmployeeInfo;
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
     * @param request
     * @return
     */
    @RequestMapping(value = "/oa/login")
    public String login(HttpServletRequest request) {
    	HttpClient client = HttpClientBuilder.create().build() ;
    	String token = request.getParameter("token");
    	HttpGet method = new HttpGet("http://qsso.corp.qunar.com/api/verifytoken.php?token="+token);
    	try {
			HttpResponse response = client.execute(method);
			BufferedReader rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			JSONObject parseObject = JSON.parseObject(result.toString());
			String ret = parseObject.getString("ret");
			if(ret.equals("true")){
				String userId = parseObject.getJSONObject("data").getString("userId");
				request.getSession().setAttribute("USER_ID", userId);
			}else{
				return "redirect:/oa/index.html";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("sso 验证失败", e);
		}
        return "redirect:/oa/my_apply.html";
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
	@RequestMapping(value = "oa/my_apply.html")
	public ModelAndView myApply(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("oa/my_apply");
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
			return BaseResult.getErrorResult(-2, errorMsg);
		}
		String result[] = new String[] { String.valueOf(laborHour) };
		return BaseResult.getSuccessResult(result);
	}

	@RequestMapping(value = "oa/data")
	@ResponseBody
	public BaseResult webPostData(HttpServletRequest request,
			@RequestBody WebRequest webRequest) {
		System.out.println("oa/data");
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
		TaxiFaresInfo[] taxiInfos = (TaxiFaresInfo[]) list1
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
			overInfo = new OvertimeMealsInfo();
			overInfo.setOvertimeMealsDate(sdf.parse(table[i][0]));
			overInfo.setMealsAddr(table[i][1]);
			overInfo.setOvertimeMealsPeerPeople(table[i][2]);
			overInfo.setMealsPersonNum(Long.parseLong(table[i][3]));
			Float money = Float.parseFloat(table[i][4]);
			String sMoney = String.valueOf((int) (money * 100));
			overInfo.setOvertimeMealsAmount(Long.parseLong(sMoney));
			overInfo.setPerMealsFee(Long.parseLong(table[i][5]));
			overInfo.setInvoiceAmount(table[i][6]);
			overInfo.setOvertimeMealsWorkhours(new BigDecimal(table[i][7]));
			list2.add(overInfo);
		}
		size = list2.size();
		OvertimeMealsInfo[] overInfos = (OvertimeMealsInfo[]) list2
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
		HospitalityInfo[] hosInfos = (HospitalityInfo[]) list3
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
		EmployeeRelationsFeesInfo[] employInfos = (EmployeeRelationsFeesInfo[]) list4
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

		OtherCostsInfo[] otherInfos = (OtherCostsInfo[]) list5
				.toArray(new OtherCostsInfo[size]);
		formInfo.setOtherCostsInfo(otherInfos);
		// 存储所有数据之和
		formInfo.setSumTaxiFaresAmount(Long.parseLong(String
				.valueOf((int) Float.parseFloat(vars.get("sum1")) * 100)));
		formInfo.setSumOvertimeMealsAmount(Long.parseLong(String
				.valueOf((int) Float.parseFloat(vars.get("sum2")) * 100)));
		formInfo.setSumHospitalityAmount(Long.parseLong(String
				.valueOf((int) Float.parseFloat(vars.get("sum3")) * 100)));
		formInfo.setSumEmployeeRelationsFees(Long.parseLong(String
				.valueOf((int) Float.parseFloat(vars.get("sum4")) * 100)));
		formInfo.setSumOtherAmount(Long.parseLong(String.valueOf((int) Float
				.parseFloat(vars.get("sum5")) * 100)));

		formInfo.setCommunicationCosts(Long.parseLong(String
				.valueOf((int) Float.parseFloat(vars.get("sum6")) * 100)));
		formInfo.setCommuCostsComment(vars.get("remark"));

		formInfo.setMoneyAmount(Long.parseLong(String.valueOf((int) Float
				.parseFloat(vars.get("sum")) * 100)));

		table = tableMap.get("table");
		len = table.length;
		for (int i = 0; i < len; i++) {
			formInfo.setRtxId(table[i][0]);
			formInfo.setApplyDate(sdf.parse(table[i][2]));
			formInfo.setFirstDep(table[i][3]);
			formInfo.setFourthDep(table[i][4]);
			formInfo.setDepNum(table[i][5]);
			formInfo.setIsDirectVp(table[i][6]);
			formInfo.setBankNumber(table[i][7]);
			formInfo.setBankName(table[i][8]);
			formInfo.setIsBorrow(table[i][9]);
			formInfo.setSerialNumber(table[i][10]);
			if ("".equals(table[i][11])) {
				formInfo.setBorrowAmount(0l);
			} else {
				formInfo.setBorrowAmount(Long.parseLong(table[i][11]));
			}
		}
		return true;
	}

	@RequestMapping(value = "oa/applyinfo")
	@ResponseBody
	public BaseResult getAllMyApplyList(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("USER_ID");
		if (userId == null || userId.length() == 0) {
			logger.warn("登陆用户为空，无法获取员工信息");
			return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
		}
		String processKey = "oa_common";
		// Date startTime, Date endTime,
		int pageNo = 0;
		int pageSize = 20;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		FormInfoList formInfoList = ioaEngineService.getUserApplyList(
				processKey, userId, null, null, pageNo, pageSize);
		DataResult dataResult = new DataResult();
		List<FormInfo> formInfos = formInfoList.getFormInfos();
		int size = formInfos.size();
		List<String[]> tableInfos = new ArrayList<String[]>();
		for (int i = 0; i < size; i++) {
			FormInfo formInfo = formInfos.get(i);
			String tableInfo[] = new String[] {
					formInfo.getUserNumber(),
					formInfo.getFirstDep() + "-" + formInfo.getSecDep() + "-"
							+ formInfo.getThridDep() + "-"
							+ formInfo.getFourthDep(), formInfo.getRtxId(),
					sdf.format(formInfo.getApplyDate()),
					String.valueOf(formInfo.getMoneyAmount()) };
			tableInfos.add(tableInfo);
		}
		dataResult.setCount((long) size);
		dataResult.setFormInfos(formInfos);
		dataResult.setAaData(tableInfos);
		return BaseResult.getSuccessResult(dataResult);
	}
}
