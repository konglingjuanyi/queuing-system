package com.qunar.ops.oaengine.listener;


import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.qunar.ops.oaengine.util.EmployeeUtils;

public class VirtualRequestListener implements TaskListener {

	private static final long serialVersionUID = 1L;

	private String apiHost;

	public String getApiHost() {
		return apiHost;
	}

	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		if("bu_leader".equals(delegateTask.getTaskDefinitionKey())){
			delegateTask.getExecution().setVariable("tree", delegateTask.getExecution().getVariableLocal("tree"));
		}
		String rtxId = (String)delegateTask.getExecution().getVariableLocal("userId");
		String managerRtx = EmployeeUtils.getManagerRtx(rtxId, this.apiHost);
		if(managerRtx.equals("david.wu")){
			delegateTask.addCandidateUser("bin.sun");
		}
		delegateTask.addCandidateUser(managerRtx);
	}
	
}
