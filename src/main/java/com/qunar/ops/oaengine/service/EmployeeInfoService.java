package com.qunar.ops.oaengine.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.result.EmployeeInfo;

@Component
public class EmployeeInfoService {
	
	@Value("${backyard.apihost}")
	String backyardUrl;
	
	/**
	 * 获取员工信息
	 * @param userId
	 * @return
	 * @throws RemoteAccessException
	 */
	public EmployeeInfo getEmployee(String userId) throws RemoteAccessException{
		EmployeeInfo eInfo = new EmployeeInfo();
		String apiUrl = backyardUrl + "?require=info&rtx_id=" + userId;
		JSONObject json = invokeGetApi(apiUrl);
		if (json.containsKey("err_id")) {
			throw new RemoteAccessException(json.getString("msg"), EmployeeInfoService.class);
		} else {
			JSONObject data = json.getJSONObject("data");
//			EmployeeInfo eInfo = JSONObject.toJavaObject(data, EmployeeInfo.class);
			eInfo.setUserId((String)data.get("username"));
			eInfo.setManager(((String)data.get("manager")).split(","));
			eInfo.setSn((String)data.get("sn"));
			eInfo.setAdName((String)data.get("ad_cn"));
			eInfo.setDepartmentI((String)data.get("dept_level1"));
			eInfo.setDepartmentII((String)data.get("dept_level2"));
			eInfo.setDepartmentIII((String)data.get("dept_level3"));
			eInfo.setDepartmentIV((String)data.get("dept_level4"));
			eInfo.setDepartmentV((String)data.get("dept_level5"));
			
			//银行卡号qunar.it暂时没有，看是否直接调用coreHr的API。
			eInfo.setBankCardNo((String)data.get("dept_level5"));
			eInfo.setBankName((String)data.get("dept_level5"));
		}
		return eInfo;
	}
	
	/**
	 * 获取员工所在部门VP rtxId
	 * @param userId
	 * @return 没有找到返回空list
	 * @throws RemoteAccessException
	 */
	public List<String> findVP(String userId) throws RemoteAccessException{
		
		List<String> users = new ArrayList<String>();
		users.add("nuby.zhang");
		return users;
	}
	
	/**
	 * 获取员工直接主管， 如果没有指定上级主管获取部门VP，如果VP没找到返回空list
	 * @param userId
	 * @return 没有找到返回空list
	 * @throws RemoteAccessException
	 */
	public List<String> findManager(String userId) throws RemoteAccessException{
		List<String> users = new ArrayList<String>();
		users.add("nuby.zhang");
		return users;
	}
	
	/**
	 * 获取员工上上级主管；
	 * 如果员工上上级主管是VP，返回上级主管；
	 * 如果以上条件均没有获取到，返回VP，如果VP没找到返回空list
	 * @param userId
	 * @return
	 * @throws RemoteAccessException
	 */
	public List<String> findDirector(String userId) throws RemoteAccessException{
		List<String> users = new ArrayList<String>();
		users.add("nuby.zhang");
		return users;
	}
	
	/**
	 * 获取工时
	 * @param userId
	 * @param day
	 * @return
	 */
	public float getLaborHour(String userId, Date day){
		String apiUrl = backyardUrl + "?require=workhours&rtx_id=" + userId + "&date=" + new SimpleDateFormat().format(day);
		return 0;
	}
	
	/**
	 * 
	 * @param apiUrl
	 * @return	JSONObject对象，内容为返回结果
	 * @throws RemoteAccessException
	 */
	private JSONObject invokeGetApi(String apiUrl) throws RemoteAccessException{
		System.out.println(apiUrl);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(apiUrl);
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
			if(json == null){
				throw new RemoteAccessException("responseBody is null", EmployeeInfoService.class);
			}
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RemoteAccessException(e.getMessage(), EmployeeInfoService.class);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RemoteAccessException(e.getMessage(), EmployeeInfoService.class);
			}
		}
	}

	public static void main(String[] args) throws RemoteAccessException {
		EmployeeInfoService s = new ClassPathXmlApplicationContext(new String[]{"spring.xml"}).getBean(EmployeeInfoService.class);
		System.out.println(s.getEmployee("yongnian.jiang").getAdName());
	}
}
