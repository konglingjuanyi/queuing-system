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
import com.qunar.ops.oaengine.model.FormApproveLogExample.Criteria;
import com.qunar.ops.oaengine.result.ListInfo;
import com.qunar.ops.oaengine.result.TaskInfo;
import com.qunar.ops.oaengine.result.TaskResult;
import com.qunar.ops.oaengine.result.dailysubmit.AlertInfo;
import com.qunar.ops.oaengine.result.dailysubmit.ApprovalInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;

@Component
public class LogManager {
	
	@Autowired(required=true)
	private FormApproveLogMapper formApproveLogMapper;
	
	public void appendApproveLog(String userId, long formId, String type, TaskResult tr, String memo){
		if(userId == null || userId.length() == 0) return;
		if(formId == 0) return;
		if(type == null || type.length() == 0) return;
		FormApproveLog log = new FormApproveLog();
		log.setApproveUser(userId);
		log.setFormId(formId);
		log.setDob(new Date());
		log.setTs(new Date());
		log.setManagerType(type);
		if(memo != null) log.setMemo(memo);
		if(tr != null){
			log.setOwner(tr.getOwner());
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
		}else{
			log.setOwner(userId);
		}
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
	
	public String getLastEndorseUser(long formId, String taskKey){
		FormApproveLogExample e = new FormApproveLogExample();
		Criteria c = e.createCriteria();
		e.setOrderByClause("ts desc");
		c.andFormIdEqualTo(formId);
		c.andTaskIdEqualTo(taskKey);
		c.andManagerTypeEqualTo("endorse");
		List<FormApproveLog> list = formApproveLogMapper.selectByExample(e);
		if(list == null || list.size() == 0) return null;
		return list.get(0).getApproveUser();
	}
	
	public void removeLog(long formId){
		FormApproveLogExample e = new FormApproveLogExample();
		Criteria c = e.createCriteria();
		c.andFormIdEqualTo(formId);
		formApproveLogMapper.deleteByExample(e);
	}
	
}
