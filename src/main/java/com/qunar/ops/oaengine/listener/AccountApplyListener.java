package com.qunar.ops.oaengine.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class AccountApplyListener implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariable("account_type", execution.getVariableLocal("account_type"));
		execution.setVariable("hostname", execution.getVariableLocal("hostname"));
	}

}
