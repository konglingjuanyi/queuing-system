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

import com.qunar.ops.oaengine.dao.DelegationMapper;
import com.qunar.ops.oaengine.manager.DelegationManager;
import com.qunar.ops.oaengine.model.Delegation;
import com.qunar.ops.oaengine.result.TaskInfo;

public class SelectListener implements TaskListener  {

	private static final long serialVersionUID = -2226505433680697029L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DelegationManager delegationManager;

	@Override
	public void notify(DelegateTask delegateTask) {
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
				candidates.add(_candidate.getUserId());
			}
		}else{//从骆驼帮获取
			
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
