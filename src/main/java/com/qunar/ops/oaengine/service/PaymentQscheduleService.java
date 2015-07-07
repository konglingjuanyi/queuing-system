package com.qunar.ops.oaengine.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import qunar.tc.qschedule.config.QSchedule;
import qunar.tc.schedule.TaskHolder;
import qunar.tc.schedule.TaskMonitor;

import com.qunar.ops.oaengine.controller.OaEngineController;
import com.qunar.ops.oaengine.result.DataResult;
@Component
public class PaymentQscheduleService {
	    private TaskMonitor monitor;
	    private static int SIZE = 3;
	    private Logger logger = LoggerFactory.getLogger(this.getClass());
	    
	    
	    @Autowired
		private OaEngineController oaEngineController ;

	    @QSchedule("oaengine.autoPay")
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
