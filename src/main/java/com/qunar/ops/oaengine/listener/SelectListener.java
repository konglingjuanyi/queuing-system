package com.qunar.ops.oaengine.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.DelegationManager;
import com.qunar.ops.oaengine.manager.GroupManager;
import com.qunar.ops.oaengine.manager.GroupManager.GroupInfo;
import com.qunar.ops.oaengine.model.Delegation;
import com.qunar.ops.oaengine.model.GroupMember;
import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.service.EmployeeInfoService;


public class SelectListener implements TaskListener  {

	private static final long serialVersionUID = -2226505433680697029L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DelegationManager delegationManager;
	@Autowired
	private EmployeeInfoService employeeInfoService;
	@Autowired
	private GroupManager groupManager;

	@Override
	public void notify(DelegateTask delegateTask) throws ActivitiException{
		List<String> candidates = new ArrayList<String>();
		//候选人优先级：手工指定->流程设定->根据规则从骆驼帮获取
		Map<String, Object> vars = delegateTask.getExecution().getVariables();
		String owner = (String)vars.get("owner");
		owner = owner.toLowerCase();
		if(owner == null){
			logger.error("发起人为空: pid= {} taskkey={} taskId={}", delegateTask.getProcessInstanceId(), delegateTask.getTaskDefinitionKey(), delegateTask.getId());
			throw new ActivitiException("发起人为空,可能已经离职");
		}
		if(vars.containsKey("candidates") && vars.get("candidates") != null){//手工指定
			Set<IdentityLink> cs = delegateTask.getCandidates();
			if(cs != null)for(IdentityLink c : delegateTask.getCandidates()){
				if(c.getUserId()!=null){
					delegateTask.deleteCandidateUser(c.getUserId().toLowerCase());
				}else{
					delegateTask.deleteCandidateUser(c.getUserId());
				}
				
			}
			String _candidates = (String)vars.get("candidates");
			_candidates = _candidates.toLowerCase();
			for(String _candidate : _candidates.split(",")){
				delegateTask.addCandidateUser(_candidate.toLowerCase());
				candidates.add(_candidate.toLowerCase());
			}
		}else if(!delegateTask.getCandidates().isEmpty()){//流程指定
			for(IdentityLink _candidate : delegateTask.getCandidates()){
				if(_candidate.getUserId() != null){
					candidates.add(_candidate.getUserId());
				}
				String groupId = _candidate.getGroupId();
				if(groupId != null && groupId.length() > 0){
					if("hrbp_tb".equals(groupId)){
						try {
							Request request = (Request)vars.get("request");
							if(request == null){
								throw new RemoteAccessException("request is null, 请联系ops", this.getClass());
							}
							List<String> hrbpList = employeeInfoService.getEmployeeHRBP(owner,request.getDepartment(),this.groupManager.getGroup(groupId));
							for (String hrbp_rtx : hrbpList) {
								candidates.add(hrbp_rtx.toLowerCase());
								delegateTask.addCandidateUser(hrbp_rtx.toLowerCase());
							}
						} catch (RemoteAccessException e) {
							e.printStackTrace();
							getHRBPGroup(groupId,delegateTask,candidates);
						}
					}else{
						getHRBPGroup(groupId,delegateTask,candidates);
					}
				}
			}
		}else{//从骆驼帮获取
			try {
				List<String> users = null;
				if("direct_manager".equals(delegateTask.getTaskDefinitionKey())){
					users = this.employeeInfoService.findManager(owner);
				}else if("director".equals(delegateTask.getTaskDefinitionKey())){
					Request request = (Request)vars.get("request");
					if(request == null){
						throw new RemoteAccessException("request is null, 请联系ops", this.getClass());
					}
					//换新接口 ， 7.3变更 ，一个月之内没问题可删除注释代码   ----lee.guo
					//users = this.employeeInfoService.findDirector(owner,request.getDepartment(), request.getDepartmentII(), request.getDepartmentIII(), request.getDepartmentIV(), request.getDepartmentV());
					users = this.employeeInfoService.findDirector(owner);
					//处理掉自己审批后 如果没有部门总监审批 则由总监上级审批 --lee.guo
					if(users.size()==0){
						users = this.employeeInfoService.findManager(owner);
					}
				}else if(delegateTask.getTaskDefinitionKey().indexOf("vp") >= 0){
					users = this.employeeInfoService.findVP(owner);
				}
				if(users != null)for(String userId : users){
					delegateTask.addCandidateUser(userId.toLowerCase());
					candidates.add(userId.toLowerCase());
				}
			} catch (RemoteAccessException e) {
				this.logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		if(candidates.isEmpty()){
			logger.error("审批候选人为空: pid= {} taskkey={} taskId={} taskkey={} userId={}", delegateTask.getProcessInstanceId(), delegateTask.getTaskDefinitionKey(), delegateTask.getId(), owner);
			throw new ActivitiException("审批候选人为空");
		}
		for(String _candidate : candidates){
			List<Delegation> agents = delegationManager.findDelegationByMaster(_candidate.toLowerCase());
			if(agents != null)for(Delegation agent : agents){
				delegateTask.addCandidateUser(agent.getAgentUserId().toLowerCase());
			}
		}
	}

	private void getHRBPGroup(String groupId, DelegateTask delegateTask, List<String> candidates) {
		for(GroupInfo info : this.groupManager.getGroup(groupId)){
			delegateTask.deleteCandidateGroup(info.getGroupKey());
			for(GroupMember mem : info.getMembers()){
				if(mem.getMemberUserId()!=null){
					candidates.add(mem.getMemberUserId().toLowerCase());
					delegateTask.addCandidateUser(mem.getMemberUserId().toLowerCase());
				}else{
					candidates.add(mem.getMemberUserId());
					delegateTask.addCandidateUser(mem.getMemberUserId());
				}
				
			}
		}
	}
	
	
}
