package com.qunar.ops.workflow.engine.listener;

import java.io.IOException;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class PhysicalRequestListener implements TaskListener {

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
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String tree = (String) delegateTask.getExecution().getVariable("tree");
		try {
			HttpGet httpget = new HttpGet(this.apiHost + tree);
			httpget.setHeader("Content-Type", "application/json; charset=UTF-8");
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity)
								: null;
					} else {
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}

			};
			String responseBody = httpclient.execute(httpget, responseHandler);
			JSONObject json = JSONObject.parseObject(responseBody);
			int errcode = json.getInteger("errcode");
			if (errcode != 0) {
				throw new ActivitiException(json.getString("msg"));
			} else {

				JSONObject data = json.getJSONObject("data");
				JSONArray owners = data.getJSONArray("owners");
				for(int i=0; i<owners.size(); i++){
					String user = owners.getJSONObject(i).getString("name");
					if(user.equals("david.wu")){
						delegateTask.addCandidateUser("bin.sun");
					}
					delegateTask.addCandidateUser(user);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ActivitiException(e.getMessage());
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new ActivitiException(e.getMessage());
			}
		}
	}

}
