package com.qunar.ops.oaengine.manager;

import java.util.Date;
import java.util.List;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.dao.FormApproveLogMapper;
import com.qunar.ops.oaengine.model.FormApproveLog;
import com.qunar.ops.oaengine.result.TaskInfo;
import com.qunar.ops.oaengine.result.TaskResult;

@Component
public class LogManager {
	
	@Autowired
	private FormApproveLogMapper formApproveLogMapper;
	
	public void appendApproveLog(String userId, long formId, String type, TaskResult tr, String memo){
		if(userId == null || userId.length() == 0) return;
		if(formId == 0) return;
		if(type == null || type.length() == 0) return;
		if(tr == null) return;
		FormApproveLog log = new FormApproveLog();
		log.setApproveUser(userId);
		log.setFormId(formId);
		log.setDob(new Date());
		log.setTs(new Date());
		log.setManagerType(type);
		if(memo != null) log.setMemo(memo);
		Task currentTask = tr.getCurrentTask();
		log.setTaskId(currentTask.getTaskDefinitionKey());
		log.setTaskName(currentTask.getName());
		List<TaskInfo> nextTasks = tr.getNextTasks();
		TaskInfo info = nextTasks.get(0);
		log.setNextTaskId(info.getTaskKey());
		log.setNextTaskName(info.getTaskName());
		log.setNextCandidate(info.getCandidate());
		this.formApproveLogMapper.insert(log);
	}
	
	public void refuseLog(String userId, long formId, String reason){
		FormApproveLog log = new FormApproveLog();
		log.setApproveUser(userId);
		log.setFormId(formId);
		log.setDob(new Date());
		log.setTs(new Date());
		log.setManagerType("refuse");
		log.setMemo(reason);
		this.formApproveLogMapper.insert(log);
	}
	
}
