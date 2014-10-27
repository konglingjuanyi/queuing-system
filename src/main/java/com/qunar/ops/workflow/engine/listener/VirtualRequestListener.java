package com.qunar.ops.workflow.engine.listener;


import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qunar.ops.workflow.engine.util.EmployeeUtils;

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
