package com.qunar.ops.oaengine.listener;

import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;

public class SelectListener implements TaskListener  {

	private static final long serialVersionUID = -2226505433680697029L;

	@Override
	public void notify(DelegateTask delegateTask) {
		Map<String, Object> vars = delegateTask.getExecution().getVariables();
		if(vars.containsKey("candidates")){
			String candidates = (String)vars.get("candidates");
			if(candidates != null)for(String candidate : candidates.split(",")){
				delegateTask.addCandidateUser(candidate);
			}
		}
		
		//TODO
		//Set<IdentityLink> candidates = delegateTask.getCandidates();
		System.out.println();
		
	}


}
