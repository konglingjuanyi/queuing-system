package com.qunar.ops.workflow.engine.listener;

import java.io.IOException;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AccountApplyCandateListener implements TaskListener {

	private static final long serialVersionUID = -2226505433680697029L;
	private String apiHost;

	public String getApiHost() {
		return apiHost;
	}

	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		String name = delegateTask.getTaskDefinitionKey();
		String host = this.apiHost;
		if ("bu_leader".equals(name)) {
			host += "/ops/account/app_owner/approvers";
		} else if ("dba".equals(name)) {
			host += "/ops/account/dba/approvers";
		} else if ("sec".equals(name)) {
			host += "/ops/account/sec/approvers";
		} else if ("ops".equals(name)) {
			host += "/ops/account/ops/approvers";
		}
		String hostname = (String) delegateTask.getExecution().getVariable("hostname");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpget = new HttpPost(host);
			StringEntity se = new StringEntity("{\"hostname\":\""+hostname+"\"}");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpget.setEntity(se);
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
			Boolean success = json.getBoolean("success");
			if (!success) {
				throw new ActivitiException(json.getString("msg"));
			} else {

				JSONArray jsonArray = json.getJSONArray("data");
				for(int i=0; i<jsonArray.size(); i++){
					delegateTask.addCandidateUser(jsonArray.getString(i));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ActivitiException(e.getMessage());
		} catch (Exception e){
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
	
	public static void main(String[] args) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpget = new HttpPost("http://l-dongliang.ops.dev.cn6.qunar.com:8888/ops/account/app_owner/approvers");
			StringEntity se = new StringEntity("{\"hostname\":\"l-dongliang.ops.dev.cn6\"}");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpget.setEntity(se);
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
			Boolean success = json.getBoolean("success");
			if (!success) {
				throw new ActivitiException(json.getString("msg"));
			} else {

				JSONArray jsonArray = json.getJSONArray("data");
				for(int i=0; i<jsonArray.size(); i++){
					String user = (String)jsonArray.get(i);
					System.out.println(user);
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
