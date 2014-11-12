package com.qunar.ops.oaengine.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.dao.FormApproveLogMapper;
import com.qunar.ops.oaengine.model.FormApproveLog;
import com.qunar.ops.oaengine.model.FormApproveLogExample;
import com.qunar.ops.oaengine.result.ListInfo;
import com.qunar.ops.oaengine.result.TaskInfo;
import com.qunar.ops.oaengine.result.TaskResult;
import com.qunar.ops.oaengine.result.dailysubmit.AlertInfo;
import com.qunar.ops.oaengine.result.dailysubmit.ApprovalInfo;

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
		if(currentTask != null){
			log.setTaskId(currentTask.getTaskDefinitionKey());
			log.setTaskName(currentTask.getName());
		}
		List<TaskInfo> nextTasks = tr.getNextTasks();
		if(nextTasks != null && nextTasks.size() > 0){
			TaskInfo info = nextTasks.get(0);
			log.setNextTaskId(info.getTaskKey());
			log.setNextTaskName(info.getTaskName());
			log.setNextCandidate(info.getCandidate());
		}
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
	
	public ListInfo<ApprovalInfo> getApproveLogs(long formId, int pageNo, int pageSize){
		ListInfo<ApprovalInfo> approvalInfos = new ListInfo<ApprovalInfo>();
		List<ApprovalInfo> _approvalInfos = new ArrayList<ApprovalInfo>();
		FormApproveLogExample example = new FormApproveLogExample();
		example.createCriteria().andFormIdEqualTo(formId);
		int count = formApproveLogMapper.countByExample(example);
		example.setOrderByClause("ts desc");
		example.setOffset((pageNo - 1) * pageSize);
		example.setLimit(pageSize);
		approvalInfos.setCount(count);
		approvalInfos.setPageNo(pageNo);
		approvalInfos.setPageSize(pageSize);
		
		List<FormApproveLog> logs = formApproveLogMapper.selectByExample(example);
		
		// 数据库Model转换为代码中的model
		ApprovalInfo approvalInfo;
		for (int i = 0; i < logs.size(); i++) {
			approvalInfo = new ApprovalInfo();
			BeanUtils.copyProperties(logs.get(i), approvalInfo);
			_approvalInfos.add(approvalInfo);
		}
		
		approvalInfos.setInfos(_approvalInfos);
		
		return approvalInfos;
	}
	
	public ApprovalInfo getApprovalInfo(long approveId){
		ApprovalInfo info = new ApprovalInfo();
		BeanUtils.copyProperties(formApproveLogMapper.selectByPrimaryKey(approveId), info);
		return info;
	}
	
}
