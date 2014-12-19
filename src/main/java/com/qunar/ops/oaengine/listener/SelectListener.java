package com.qunar.ops.oaengine.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		if(owner == null){
			logger.error("发起人为空: pid= {} taskkey={} taskId={}", delegateTask.getProcessInstanceId(), delegateTask.getTaskDefinitionKey(), delegateTask.getId());
			throw new ActivitiException("审批候选人为空");
		}
		if(vars.containsKey("candidates")){//手工指定
			String _candidates = (String)vars.get("candidates");
			if(_candidates != null)for(String _candidate : _candidates.split(",")){
				delegateTask.addCandidateUser(_candidate);
				candidates.add(_candidate);
			}
		}else if(!delegateTask.getCandidates().isEmpty()){//流程指定
			for(IdentityLink _candidate : delegateTask.getCandidates()){
				if(_candidate.getUserId() != null){
					candidates.add(_candidate.getUserId());
				}
				String groupId = _candidate.getGroupId();
				if(groupId != null && groupId.length() > 0){
					for(GroupInfo info : this.groupManager.getGroup(groupId)){
						delegateTask.deleteCandidateGroup(info.getGroupKey());
						for(GroupMember mem : info.getMembers()){
							candidates.add(mem.getMemberUserId());
							delegateTask.addCandidateUser(mem.getMemberUserId());
						}
					}
				}
			}
		}else{//从骆驼帮获取
			try {
				List<String> users = null;
				if("direct_manager".equals(delegateTask.getTaskDefinitionKey())){
					users = this.employeeInfoService.findManager(owner);
				}else if("director".equals(delegateTask.getTaskDefinitionKey())){
					users = this.employeeInfoService.findDirector(owner);
				}else if(delegateTask.getTaskDefinitionKey().indexOf("vp") >= 0){
					users = this.employeeInfoService.findVP(owner);
				}
				if(users != null)for(String userId : users){
					delegateTask.addCandidateUser(userId);
					candidates.add(userId);
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
			List<Delegation> agents = delegationManager.findDelegationByMaster(_candidate);
			if(agents != null)for(Delegation agent : agents){
				delegateTask.addCandidateUser(agent.getAgentUserId());
			}
		}
	}
}
