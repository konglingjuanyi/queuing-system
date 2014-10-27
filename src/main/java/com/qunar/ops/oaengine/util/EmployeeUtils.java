package com.qunar.ops.oaengine.util;


import java.io.IOException;

import org.activiti.engine.ActivitiException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class EmployeeUtils {
	
	
	public static void main(String[] args) {
//		System.out.println(EmployeeUtils.getDepInfoByUserId("xinxiqi.ji", "http://qunar.it/api/employees/?require=info&rtx_id="));
		System.out.println(EmployeeUtils.getManagerRtx("yongnian.jiang", "http://qunar.it/api/employees/?require=info&rtx_id="));
		System.out.println(EmployeeUtils.getDepInfoByUserId("yongnian.jiang", "http://qunar.it/api/employees/?require=info&rtx_id="));
	}
	
	public static String getManagerRtx(String rtx, String apiHost) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(apiHost + rtx);
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
			Boolean status = json.getBoolean("ret");
			if (!status) {
				throw new ActivitiException(json.getString("msg"));
			} else {
				JSONObject jsonObject = json.getJSONObject("data");
				String manager_email = jsonObject.getString("manager_email");
				String manager_rtx = manager_email.split("@")[0];
				return manager_rtx;
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

	public static String getDepInfoByUserId(String rtx, String apiHost) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(apiHost + rtx);
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
			Boolean status = json.getBoolean("ret");
			if (!status) {
				throw new ActivitiException(json.getString("msg"));
			} else {
				JSONObject jsonObject = json.getJSONObject("data");
				String deptLeave = "null";
				String depInfo = "";
				for(int i = 1; i <= 5; i++){
					deptLeave = jsonObject.getString("dept_level" + i);
					if(deptLeave != null && !deptLeave.equals("null")){
						if(i != 1){
							depInfo += "|";
						}
						depInfo += deptLeave;
					}else{
						break;
					}
				}
				return depInfo;
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
