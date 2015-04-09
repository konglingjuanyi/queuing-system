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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.result.AppRequest;
import com.qunar.ops.oaengine.result.AppResult;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfoList;
import com.qunar.ops.oaengine.service.IOAEngineService;
import com.qunar.ops.oaengine.util.OAControllerUtils;
import com.qunar.ops.oaengine.util.OAEngineConst;
import com.qunar.ops.oaengine.util.QUtils;

@Controller
public class AppAPIController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IOAEngineService ioaEngineService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private String processKey = "oa_common";
	
	@RequestMapping(value = "oa/app/count")
	@ResponseBody
	public AppResult getCount(HttpServletRequest request, @RequestBody AppRequest appRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return AppResult.getErrorResult(10001);
		}
		int count = 0;
		try {
			FormInfoList todoList = ioaEngineService.todoList(processKey, userId, null, null, null, 0, Integer.MAX_VALUE);
			count = todoList.getCount();
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
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return AppResult.getErrorResult(10001);
		}
		Map<String, String> vars = appRequest.getVars();
		int length = NumberUtils.toInt(vars.get("length"), 50);
		int start = NumberUtils.toInt(vars.get("start"), 0);
		int count = 0;
		List<Map<String, String>> items = new ArrayList<Map<String, String>>();
		try {
			FormInfoList todoList = ioaEngineService.todoList(processKey, userId, start, length);
			count = todoList.getCount();
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
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return AppResult.getErrorResult(10001);
		}
		
		String cname = QUtils.getAdname(request);
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
		ioaEngineService.batchPass(processKey, userId, cname, formIdList, taskIdList, reason);
		return AppResult.getSuccessResult(new Object[]{});
	}
	
	@RequestMapping(value = "oa/app/reject")
	@ResponseBody
	public AppResult reject(HttpServletRequest request, @RequestBody AppRequest appRequest) {
		String userId = QUtils.getUsername(request);
		if (userId == null || userId.length() == 0) {
			logger.warn(OAEngineConst.RTX_ID_IS_NULL_MSG);
			return AppResult.getErrorResult(10001);
		}
		String cname = QUtils.getAdname(request);
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
				ioaEngineService.refuse(processKey, userId, cname, formId, taskId, reason);
			} catch (FormNotFoundException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		return AppResult.getSuccessResult(new Object[]{});
	}

}
