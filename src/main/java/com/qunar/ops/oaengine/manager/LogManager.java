package com.qunar.ops.oaengine.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.dao.FormApproveLogMapper;
import com.qunar.ops.oaengine.datasource.Read;
import com.qunar.ops.oaengine.model.FormApproveLog;
import com.qunar.ops.oaengine.model.FormApproveLogExample;
import com.qunar.ops.oaengine.model.GroupMember;
import com.qunar.ops.oaengine.model.GroupMemberExample;
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
	
	public void appendApproveLog(String userId, String approveCname, long formId, String type, TaskResult tr, String memo){
		if(userId == null || userId.length() == 0) return;
		if(formId == 0) return;
		if(type == null || type.length() == 0) return;
		FormApproveLog log = new FormApproveLog();
		log.setApproveUser(userId);
		log.setApproveCname(approveCname);
		log.setFormId(formId);
		log.setDob(new Date());
		log.setTs(new Date());
		log.setManagerType(type);
		if(memo != null) log.setMemo(memo);
		if(tr != null){
			log.setOwner(tr.getOwner());
			log.setOwnerCname(tr.getCname());
			Task currentTask = tr.getCurrentTask();
			//获取前一条日志信息，判断上一条是否加签过来的消息，如果是加签，记录便是加签操作，否则记录正常节点 --lee.guo
			if (currentTask != null) {
				List<FormApproveLog> logList = this.formApproveLogMapper.selectLogByFormId(log.getFormId());
				String lastMemo = logList.get(0).getMemo();
				int lastMemoLength = lastMemo.split("\\[加签给：").length;
				if (lastMemoLength == 2) {
					log.setTaskId(currentTask.getTaskDefinitionKey());
					log.setTaskName("加签操作");
				} else {
					log.setTaskId(currentTask.getTaskDefinitionKey());
					log.setTaskName(currentTask.getName());
				}
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
	
	@Read
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
	
	@Read
	public FormApproveLog getLastApproveLog(long formId, String userId){
		FormApproveLogExample example = new FormApproveLogExample();
		example.createCriteria().andFormIdEqualTo(formId).andApproveUserEqualTo(userId);
		example.setOrderByClause("ts desc");
		example.setOffset(0);
		example.setLimit(1);
		List<FormApproveLog> logs = formApproveLogMapper.selectByExample(example);
		if(logs.size() == 0) return null;
		return logs.get(0);
	}
	
	@Read
	public FormApproveLog getLastApproveLog(long formId){
		FormApproveLogExample example = new FormApproveLogExample();
		example.createCriteria().andFormIdEqualTo(formId);
		example.setOrderByClause("ts desc");
		List<FormApproveLog> logs = formApproveLogMapper.selectByExample(example);
		if(logs!=null && logs.size() >0) return logs.get(0);
		return null;
		
	}
	
	@Read
	public FormApproveLog getLastPassApproveLog(long formId, String userId){
		FormApproveLogExample example = new FormApproveLogExample();
		example.createCriteria().andFormIdEqualTo(formId);
		example.setOrderByClause("ts desc");
		example.setOffset(0);
		example.setLimit(1);
		List<FormApproveLog> logs = formApproveLogMapper.selectByExample(example);
		if(logs.size() == 0) return null;
		FormApproveLog log = logs.get(0);
		
		if("pass".equals(log.getManagerType())){
			return log;
		}else{
			FormApproveLogExample e = new FormApproveLogExample();
			e.createCriteria().andFormIdEqualTo(formId).andManagerTypeEqualTo("pass").andNextTaskIdEqualTo(log.getTaskId());
			example.setOrderByClause("ts desc");
			example.setOffset(0);
			example.setLimit(1);
			logs = formApproveLogMapper.selectByExample(e);
			if(logs.size() == 0) return null;
			return logs.get(0);
		}
	}
	
	@Read
	public ApprovalInfo getApprovalInfo(long approveId){
		ApprovalInfo info = new ApprovalInfo();
		BeanUtils.copyProperties(formApproveLogMapper.selectByPrimaryKey(approveId), info);
		return info;
	}
	
	@Read
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
	
	@Read
	public boolean formAppreoveLogPass(long formId,String assignees){
		FormApproveLogExample ge = new FormApproveLogExample();
		Criteria c = ge.createCriteria();
		c.andFormIdEqualTo(formId);
		c.andManagerTypeEqualTo("pass");
		c.andApproveUserEqualTo(assignees);
		List<FormApproveLog> list = this.formApproveLogMapper.selectByExample(ge);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}
	
	@Read
	public List<FormApproveLog> formAppreoveLogPassList(long formId,String assignees){
		FormApproveLogExample ge = new FormApproveLogExample();
		Criteria c = ge.createCriteria();
		c.andFormIdEqualTo(formId);
		c.andManagerTypeEqualTo("pass");
		c.andApproveUserEqualTo(assignees);
		List<FormApproveLog> list = this.formApproveLogMapper.selectByExample(ge);
		if(list!=null && list.size()>0){
			list.get(0);
		}
		return null;
	}
	
	@Read
	public List<FormApproveLog> formAppreoveLogAllPassByFormId(long formId,String tk,String managertTpe){
		FormApproveLogExample ge = new FormApproveLogExample();
		Criteria c = ge.createCriteria();
		c.andFormIdEqualTo(formId);
		c.andManagerTypeEqualTo(managertTpe);
		c.andNextTaskIdEqualTo(tk);
		List<FormApproveLog> list = this.formApproveLogMapper.selectByExample(ge);
		if(list !=null && list.size()>0){
			 return list;
		}
		return null;
		
	}
	
	@Read
	public FormApproveLog getFirstApproveLog(long formId, String userId){
		FormApproveLogExample example = new FormApproveLogExample();
		example.createCriteria().andFormIdEqualTo(formId).andApproveUserEqualTo(userId);
		example.setOrderByClause("ts ASC");
		example.setOffset(0);
		example.setLimit(1);
		List<FormApproveLog> logs = formApproveLogMapper.selectByExample(example);
		if(logs.size() == 0) return null;
		return logs.get(0);
	}
	@Read
	public String getFinCheckedUser(long formId, String taskName){
		FormApproveLogExample example = new FormApproveLogExample();
		//增加新条件 不是召回操作  --lee.guo
		example.createCriteria().andFormIdEqualTo(formId).andTaskNameEqualTo(taskName).andManagerTypeNotEqualTo("recall");
		example.setOrderByClause("ts ASC");
		example.setOffset(0);
		example.setLimit(1);
		List<FormApproveLog> logs = formApproveLogMapper.selectByExample(example);
		if(null!=logs && logs.size() > 0){
			return logs.get(0).getApproveUser();
		}
		return "";
		
	}
	
}
