package com.qunar.ops.oaengine.listener;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;

import com.qunar.ops.oaengine.result.TaskInfo;

public class SelectListener implements TaskListener  {

	private static final long serialVersionUID = -2226505433680697029L;

	@Override
	public void notify(DelegateTask delegateTask) {
		Map<String, Object> vars = delegateTask.getExecution().getVariables();
//		String assigner = (String)vars.get("assigner");
//		
//		//TODO 获取候选人
//		
//		if(assigner != null)for(IdentityLink candidate : delegateTask.getCandidates()){
//			if(assigner.equalsIgnoreCase(candidate.getUserId())){
//				TaskInfo taskInfo = new TaskInfo();
//				taskInfo.setCandidate(assigner);
//				taskInfo.setOid(delegateTask.getExecution().getProcessBusinessKey());
//				taskInfo.setProcessInstanceId(delegateTask.getProcessInstanceId());
//				taskInfo.setTaskId(delegateTask.getId());
//				taskInfo.setTaskKey(delegateTask.getTaskDefinitionKey());
//				taskInfo.setTaskName(delegateTask.getName());
//				delegateTask.getExecution().setVariable("skiptask", taskInfo);
//				vars.put("complete", "true");
//				delegateTask.getExecution().getEngineServices().getTaskService().complete(delegateTask.getId(), vars);
//				return;
//			}
//		}
//		delegateTask.getExecution().setVariable("skiptask", null);
		
		if(vars.containsKey("candidates")){
			String candidates = (String)vars.get("candidates");
			if(candidates != null)for(String candidate : candidates.split(",")){
				delegateTask.addCandidateUser(candidate);
			}
		}
	}
}
