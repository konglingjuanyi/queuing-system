package com.qunar.ops.workflow.engine.util;

import java.io.IOException;

import org.activiti.engine.impl.persistence.entity.ByteArrayEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HClient {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
	       CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {
	        	HttpPost httpget = new HttpPost("http://localhost:8080/engine/demo/todo");
	            httpget.setHeader("Content-Type", "application/json; charset=UTF-8");
	            String body = "{\"userId\":\"nuby\", \"vars\":{}}";
	            StringEntity entity = new StringEntity(body,"UTF-8");
	            httpget.setEntity(entity);
	            System.out.println("Executing request " + httpget.getRequestLine());
	            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
	                public String handleResponse(
	                        final HttpResponse response) throws ClientProtocolException, IOException {
	                    int status = response.getStatusLine().getStatusCode();
	                    if (status >= 200 && status < 300) {
	                        HttpEntity entity = response.getEntity();
	                        return entity != null ? EntityUtils.toString(entity) : null;
	                    } else {
	                        throw new ClientProtocolException("Unexpected response status: " + status);
	                    }
	                }

	            };
	            String responseBody = httpclient.execute(httpget, responseHandler);
	            JSONObject json = JSONObject.parseObject(responseBody);
	            Boolean boolean1 = json.getBoolean("success");
	            System.out.println("----------------------------------------");
	            System.out.println(json.getIntValue("errorCode"));
	        } finally {
	            httpclient.close();
	        }
			
	}

}
