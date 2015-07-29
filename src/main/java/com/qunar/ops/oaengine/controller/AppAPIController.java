package com.qunar.ops.oaengine.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.result.AppNoVarsRequest;
import com.qunar.ops.oaengine.result.AppRequest;
import com.qunar.ops.oaengine.result.AppResult;
import com.qunar.ops.oaengine.result.EmployeeInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfoList;
import com.qunar.ops.oaengine.service.IOAEngineService;
import com.qunar.ops.oaengine.util.OAControllerUtils;
import com.qunar.ops.oaengine.util.OAEngineConst;
import com.qunar.ops.oaengine.util.QUtils;

@Component
@Controller
public class AppAPIController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${api.acl}")
	String acl;
	
	@Autowired
	private IOAEngineService ioaEngineService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private String processKey = "oa_common";
	
	@RequestMapping(value = "oa/app/count")
	@ResponseBody
	public AppResult getCount(HttpServletRequest request, @RequestBody AppNoVarsRequest appRequest) {
		if(!this.checkAcl(request.getRemoteAddr())){
			return AppResult.getErrorResult(-100, "ACL禁止");
		}
		String userId = appRequest.getRtx_id();
		int count = 0;
		try {
			FormInfoList todoList = ioaEngineService.todoList(processKey, userId, null, 0, Integer.MAX_VALUE);
			count = todoList.getFormInfos().size();
		} catch (FormNotFoundException e) {
			logger.warn(e.getMessage(), e);
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("count", count);
		return AppResult.getSuccessResult(data);
	}
	
	@RequestMapping(value = "oa/app/fetch")
	@ResponseBody
	public AppResult getTodo(HttpServletRequest request, @RequestBody AppRequest appRequest) {
		if(!this.checkAcl(request.getRemoteAddr())){
			return AppResult.getErrorResult(-100, "ACL禁止");
		}
		String userId = appRequest.getRtx_id();
		Map<String, String> vars = appRequest.getVars();
		int length = NumberUtils.toInt(vars.get("length"), 50);
		int start = NumberUtils.toInt(vars.get("start"), 0);
		int count = 0;
		List<Map<String, String>> items = new ArrayList<Map<String, String>>();
		try {
			FormInfoList todoList = ioaEngineService.todoList(processKey, userId, null, start, length);
			count = todoList.getFormInfos().size();
			for(FormInfo info : todoList.getFormInfos()){
				Map<String, String> item = new HashMap<String, String>();
				item.put("oid", info.getId() + ":" + info.getTaskId());
				item.put("user", info.getApplyUser());
				item.put("time", sdf.format(info.getStartDate()));
				item.put("sum", "总金额: " + OAControllerUtils.centMoneyToYuan(info.getMoneyAmount()) + "元");
				items.add(item);
			}
		} catch (FormNotFoundException e) {
			logger.warn(e.getMessage(), e);
		}
		AppResult successResult = AppResult.getSuccessResult(items);
		successResult.setSum(count);
		return successResult;
	}
	
	@RequestMapping(value = "oa/app/approve")
	@ResponseBody
	public AppResult pass(HttpServletRequest request, @RequestBody AppRequest appRequest) {
		if(!this.checkAcl(request.getRemoteAddr())){
			return AppResult.getErrorResult(-100, "ACL禁止");
		}
		String userId = appRequest.getRtx_id();
		String cname = this.getAdname(userId);
		Map<String, String> vars = appRequest.getVars();
		String oids = vars.get("taskIds");
		String reason = vars.get("reason");
		String oidArray[] = oids.split(",");
		List<String> taskIdList = new ArrayList<String>();
		List<Long> formIdList = new ArrayList<Long>();
		for (String oid : oidArray) {
			String[] tmp = oid.split(":");
			if(tmp.length != 2) continue;
			formIdList.add(NumberUtils.toLong(tmp[0], 0));
			taskIdList.add(tmp[1]);
		}
		ioaEngineService.batchPass(processKey, userId, cname, formIdList, taskIdList, reason==null?"":reason);
		return AppResult.getSuccessResult(new Object[]{});
	}
	
	@RequestMapping(value = "oa/app/reject")
	@ResponseBody
	public AppResult reject(HttpServletRequest request, @RequestBody AppRequest appRequest) {
		if(!this.checkAcl(request.getRemoteAddr())){
			return AppResult.getErrorResult(-100, "ACL禁止");
		}
		String userId = appRequest.getRtx_id();
		String cname = this.getAdname(userId);
		Map<String, String> vars = appRequest.getVars();
		String oids = vars.get("taskIds");
		String reason = vars.get("reason");
		String oidArray[] = oids.split(",");
		for (String oid : oidArray) {
			try {
				String[] tmp = oid.split(":");
				if(tmp.length != 2) continue;
				long formId = NumberUtils.toLong(tmp[0], 0);
				String taskId = tmp[1];
				ioaEngineService.refuse(processKey, userId, cname, formId, taskId, reason==null?"":reason);
			} catch (FormNotFoundException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		return AppResult.getSuccessResult(new Object[]{});
	}
	
	@RequestMapping(value = "oa/app/details")
	@ResponseBody
	public AppResult details(HttpServletRequest request, @RequestBody AppRequest appRequest) {
		if(!this.checkAcl(request.getRemoteAddr())){
			return AppResult.getErrorResult(-100, "ACL禁止");
		}
		String userId = appRequest.getRtx_id();
		Map<String, String> vars = appRequest.getVars();
		String oid = vars.get("taskIds");
		String oidArray[] = oid.split(":");
		if(oidArray.length != 2) return AppResult.getErrorResult(-100, "报销详情没有找到");
		String formId = oidArray[0];
		final FormInfo info;
		try {
			info = this.ioaEngineService.getFormInfo(null, null, formId);
		} catch (FormNotFoundException e) {
			return AppResult.getErrorResult(-100, e.getMessage());
		}
		if(info == null) return AppResult.getErrorResult(-100, "报销详情没有找到");
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		data.add(new HashMap(){{put("k", "出租车费"); put("v", OAControllerUtils.centMoneyToYuan(info.getSumTaxiFaresAmount())+"元");}});
		data.add(new HashMap(){{put("k", "通信费"); put("v", OAControllerUtils.centMoneyToYuan(info.getCommunicationCosts())+"元");}});
		data.add(new HashMap(){{put("k", "餐费"); put("v", OAControllerUtils.centMoneyToYuan(info.getSumOvertimeMealsAmount())+"元");}});
		data.add(new HashMap(){{put("k", "招待费"); put("v", OAControllerUtils.centMoneyToYuan(info.getSumHospitalityAmount())+"元");}});
		data.add(new HashMap(){{put("k", "员工关系费"); put("v", OAControllerUtils.centMoneyToYuan(info.getSumEmployeeRelationsFees())+"元");}});
		data.add(new HashMap(){{put("k", "其他"); put("v", OAControllerUtils.centMoneyToYuan(info.getSumOtherAmount())+"元");}});
		return AppResult.getSuccessResult(data);
	}
	
	@RequestMapping(value = "oa/app/filter")
	@ResponseBody
	public AppResult getTodoByFilter(HttpServletRequest request, @RequestBody AppRequest appRequest) {
		if(!this.checkAcl(request.getRemoteAddr())){
			return AppResult.getErrorResult(-100, "ACL禁止");
		}
		String userId = appRequest.getRtx_id();
		Map<String, String> vars = appRequest.getVars();
		int length = NumberUtils.toInt(vars.get("length"), 50);
		int start = NumberUtils.toInt(vars.get("start"), 0);
		String user = vars.get("user");
		if(user !=null && user.length() == 0) user = null;
		int count = 0;
		List<Map<String, String>> items = new ArrayList<Map<String, String>>();
		try {
			FormInfoList todoList = ioaEngineService.todoList(processKey, userId, user, start, length);
			count = todoList.getFormInfos().size();
			for(FormInfo info : todoList.getFormInfos()){
				Map<String, String> item = new HashMap<String, String>();
				item.put("oid", info.getId() + ":" + info.getTaskId());
				item.put("user", info.getApplyUser());
				item.put("time", sdf.format(info.getStartDate()));
				item.put("sum", "总金额: " + OAControllerUtils.centMoneyToYuan(info.getMoneyAmount()) + "元");
				items.add(item);
			}
		} catch (FormNotFoundException e) {
			logger.warn(e.getMessage(), e);
		}
		AppResult successResult = AppResult.getSuccessResult(items);
		successResult.setSum(count);
		return successResult;
	}
	
	@RequestMapping(value = "oa/app/history")
	@ResponseBody
	public AppResult getHistory(HttpServletRequest request, @RequestBody AppRequest appRequest) {
		if(!this.checkAcl(request.getRemoteAddr())){
			return AppResult.getErrorResult(-100, "ACL禁止");
		}
		String userId = appRequest.getRtx_id();
		Map<String, String> vars = appRequest.getVars();
		int length = NumberUtils.toInt(vars.get("length"), 50);
		int start = NumberUtils.toInt(vars.get("start"), 0);
		String user = vars.get("user");
		if(user !=null && user.length() == 0) user = null;
		int count = 0;
		List<Map<String, String>> items = new ArrayList<Map<String, String>>();
		FormInfoList list = ioaEngineService.historyListII(userId, null, null, user, start, length);
		count = list.getCount();
		for(FormInfo info : list.getFormInfos()){
			Map<String, String> item = new HashMap<String, String>();
			item.put("oid", info.getId() + ":" + info.getTaskId());
			item.put("user", info.getApplyUser());
			item.put("time", sdf.format(info.getStartDate()));
			item.put("sum", "总金额: " + OAControllerUtils.centMoneyToYuan(info.getMoneyAmount()) + "元");
			items.add(item);
		}
		AppResult successResult = AppResult.getSuccessResult(items);
		successResult.setSum(count);
		return successResult;
	}
	
	@RequestMapping(value = "oa/app/myapply")
	@ResponseBody
	public AppResult getMyApply(HttpServletRequest request, @RequestBody AppRequest appRequest) {
		if(!this.checkAcl(request.getRemoteAddr())){
			//return AppResult.getErrorResult(-100, "ACL禁止");
		}
		String userId = appRequest.getRtx_id();
		Map<String, String> vars = appRequest.getVars();
		int length = NumberUtils.toInt(vars.get("length"), 50);
		int start = NumberUtils.toInt(vars.get("start"), 0);
		String user = vars.get("user");
		if(user !=null && user.length() == 0) user = null;
		int count = 0;
		List<Map<String, String>> items = new ArrayList<Map<String, String>>();
		FormInfoList list = ioaEngineService.getUserApplyList(processKey, userId, start, length);
		count = list.getCount();
		for(FormInfo info : list.getFormInfos()){
			Map<String, String> item = new HashMap<String, String>();
			item.put("oid", info.getId() + ":" + info.getTaskId());
			item.put("user", info.getApplyUser());
			item.put("time", sdf.format(info.getStartDate()));
			item.put("sum", "总金额: " + OAControllerUtils.centMoneyToYuan(info.getMoneyAmount()) + "元");
			items.add(item);
		}
		AppResult successResult = AppResult.getSuccessResult(items);
		successResult.setSum(count);
		return successResult;
	}	
	
	@Cacheable(value = { "employee.adname" }, key="#userId")
	private String getAdname(String userId){
		try {
			EmployeeInfo employeeInfo = this.ioaEngineService.getEmployeeInfo(userId);
			if(employeeInfo != null){
				return employeeInfo.getAdName();
			}
		} catch (RemoteAccessException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private boolean checkAcl(String ip){
		if(this.acl == null || this.acl.length() == 0) return true;
		if("*".equals(this.acl)) return true;
		if(this.acl.indexOf(ip) >= 0) return true;
		return false;
	}

}
