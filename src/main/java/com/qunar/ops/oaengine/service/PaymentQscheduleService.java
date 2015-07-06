package com.qunar.ops.oaengine.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.qunar.flight.qmonitor.QMonitor;
import com.qunar.ops.oaengine.controller.OaEngineController;
import com.qunar.ops.oaengine.dao.Formmain0114HistoryMapper;
import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.Form0114Manager;
import com.qunar.ops.oaengine.model.Formmain0114History;
import com.qunar.ops.oaengine.model.Formmain0114HistoryExample;
import com.qunar.ops.oaengine.result.BaseResult;
import com.qunar.ops.oaengine.result.DataResult;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfoList;
import com.qunar.ops.oaengine.util.OAControllerUtils;
import com.qunar.ops.oaengine.util.OAEngineConst;

import qunar.tc.qschedule.config.QSchedule;
import qunar.tc.schedule.TaskHolder;
import qunar.tc.schedule.TaskMonitor;
@Component
public class PaymentQscheduleService {
	    private TaskMonitor monitor;
	    private static int SIZE = 3;
	    private Logger logger = LoggerFactory.getLogger(this.getClass());
	    
	    @Resource
		private Formmain0114HistoryMapper formmain0114HistoryMapper;
	    
	    
	    @Autowired
		private OaEngineController oaEngineController ;

	    @QSchedule("oaengine.payment")
	    private void process() {
	        // 此为QSchedule提供的API
	        monitor = TaskHolder.getKeeper();
	        // 通过该Logger可以将任务执行过程中的日志发送给调度中心，在调度中心监控页面可以浏览任务执行日志
	        logger = monitor.getLogger();
	        logger.info("出纳自动审批 开始运行（定时推送报销单）");
	        // 该方法可以将任务要执行的总量发送给调度中心
	        monitor.setRateCapacity(SIZE);
	        execute();
	        monitor.setRate(SIZE);
	        logger.info("出纳自动审批 运行结束");
	    }

	    public String execute() {
	    	
	    	DataResult result = oaEngineController.auto();
	    	List<String[]> tableInfos =  result.getTableInfos();
	    	List<Long> formIdList = new ArrayList<Long>();
			List<String> taskIdList = new ArrayList<String>();
	    	for (String[] infos : tableInfos) {
	    		formIdList.add(Long.valueOf(infos[0]));
	    		taskIdList.add(infos[5]);
			}
	    	oaEngineController.approvePass(formIdList, taskIdList);
	    	return "成功";
	    }
	

}
