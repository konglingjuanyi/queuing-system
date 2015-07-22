package com.qunar.ops.oaengine.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctc.wstx.util.StringUtil;
import com.qunar.flight.qmonitor.QMonitor;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.GroupManager.GroupInfo;
import com.qunar.ops.oaengine.model.GroupMember;
import com.qunar.ops.oaengine.result.EmployeeInfo;
import com.qunar.ops.oaengine.util.OAControllerUtils;

@Component
public class EmployeeInfoService {

	@Value("${backyard.apihost}")
	String backyardUrl;
	
	@Value("${backyard.apihost.bank}")
	String backyardBankUrl;
	
	@Value("${backyard.drt_vp}")
	String backyardDrt_vpUrl;
	
	@Value("${oa.apihost}")
	String oadUrl;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 获取员工信息
	 * 
	 * @param userId
	 * @return
	 * @throws RemoteAccessException
	 */
	public List<EmployeeInfo> getEmployeeByCname(String cname) {
		if(cname == null) cname = "";
		cname = cname.trim();
		List<EmployeeInfo> eInfos = new ArrayList<EmployeeInfo>();
		String apiUrl = backyardUrl + "?require=search_em";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("full_name", cname));
		JSONObject json;
		try {
			json = invokePostApi(apiUrl, params);
		} catch (RemoteAccessException e) {
			return eInfos;
		}
		if (json.containsKey("error_id") && json.getInteger("err_id") > 0) {
			return eInfos;
		} else {
			JSONArray datas = json.getJSONArray("data");
			if(datas.size() == 0){				return eInfos;
			}
			for(int i=0; i<datas.size(); i++){
				EmployeeInfo eInfo = new EmployeeInfo();
				JSONObject data = datas.getJSONObject(i);
				eInfo.setUserId((String) data.get("rtx_id"));
				eInfo.setUserMail((String) data.get("email"));
				
				String manager_email = data.getString("manager_email");
				eInfo.setManagerMail(manager_email);
				String manager = "";
				if(manager_email != null){
					manager = manager_email.substring(0, manager_email.indexOf("@"));
				}
				eInfo.setManager(manager);
				eInfo.setSn((String) data.get("sn"));
				eInfo.setAdName((String) data.get("cn"));
				eInfo.setDepartmentI((String) data.get("dep1"));
				eInfo.setDepartmentII((String) data.get("dep2"));
				eInfo.setDepartmentIII((String) data.get("dep3"));
				eInfo.setDepartmentIV((String) data.get("dep4"));
				eInfo.setDepartmentV((String) data.get("dep5"));
	
				//eInfo.setVp((String) data.get("vp"));
				//eInfo.setVpMail((String) data.get("vp_mail"));
				
				eInfo.setEnable(1);
				eInfo.setCompany(data.getString("company"));
				eInfos.add(eInfo);
			}

			QMonitor.recordOne("call_qunarit_success");
		}
		return eInfos;
	}

	/**
	 * 获取员工信息
	 * 
	 * @param userId
	 * @return
	 * @throws RemoteAccessException
	 */
	public EmployeeInfo getEmployee(String userId) throws RemoteAccessException {
		EmployeeInfo eInfo = new EmployeeInfo();
		String apiUrl = backyardUrl + "?require=info&rtx_id=" + userId;
		JSONObject json = invokeGetApi(apiUrl);
		if (json.containsKey("err_id")) {
			
			throw new RemoteAccessException(json.getString("msg"),
					EmployeeInfoService.class);
		} else {
			JSONObject data = json.getJSONObject("data");
			// EmployeeInfo eInfo = JSONObject.toJavaObject(data,
			// EmployeeInfo.class);
			eInfo.setUserId((String) data.get("username"));
			eInfo.setUserMail((String) data.get("email"));
			eInfo.setManager(((String) data.get("manager")));
			eInfo.setManagerMail(((String) data.get("manager_email")));
			eInfo.setSn((String) data.get("sn"));
			eInfo.setAdName((String) data.get("ad_cn"));
			eInfo.setDepartmentI((String) data.get("dept_level1"));
			eInfo.setDepartmentII((String) data.get("dept_level2"));
			eInfo.setDepartmentIII((String) data.get("dept_level3"));
			eInfo.setDepartmentIV((String) data.get("dept_level4"));
			eInfo.setDepartmentV((String) data.get("dept_level5"));

			eInfo.setVp((String) data.get("vp"));
			eInfo.setVpMail((String) data.get("vp_mail"));
			
			eInfo.setEnable(data.getIntValue("enable"));
			eInfo.setCompany(data.getString("company"));

			QMonitor.recordOne("call_qunarit_success");
		}
		return eInfo;
	}
	
	/**
	 * 获取员工信息
	 * 
	 * @param userId
	 * @return
	 * @throws RemoteAccessException
	 */
	public EmployeeInfo getEmployeeBankInfo(String userId) throws RemoteAccessException {
		EmployeeInfo eInfo = new EmployeeInfo();
		//return eInfo;
		String apiUrl = backyardBankUrl + "?require=bank_info&rtx_id=" + userId;
		JSONObject json = invokeGetApi(apiUrl);
		if (json.containsKey("err_id")) {
			throw new RemoteAccessException(json.getString("msg"),
					EmployeeInfoService.class);
		} else {
			JSONObject data = json.getJSONObject("data");
			eInfo.setBankCardNo((String)data.get("bank_card"));
			eInfo.setBankName((String)data.get("bank_class"));
			eInfo.setBankCity((String)data.get("bank_city"));
			QMonitor.recordOne("call_qunarit_success");
		}
		return eInfo;
	}
	
	/**
	 * 获取员工借款
	 * 
	 * @param userId
	 * @return
	 * @throws RemoteAccessException
	 */
	public List<String[]> getLoans(String userId) throws RemoteAccessException {
		List<String[]> infos = new ArrayList<String[]>();
		/*
		String apiUrl = oadUrl + userId + "@qunar.com/1";
		JSONObject json = invokeGetApi(apiUrl);
		Boolean ret = json.getBoolean("ret");
		if (!ret) {
			throw new RemoteAccessException(json.getString("errmsg"), EmployeeInfoService.class);
		} else {
			JSONArray array = json.getJSONArray("data");
			for(int i=0; i < array.size(); i++){
				JSONObject info = array.getJSONObject(i);
				String billNo = (String) info.get("billNo");
				long invoiceDate = info.getLongValue("invoiceDate");
				String date = sdf.format(new Date(invoiceDate));
				String amount = (String) info.get("amount");
				String borrowBillBalance = (String) info.get("borrowBillBalance");
				infos.add(new String[]{billNo, date, amount, borrowBillBalance});
			}
		}
		infos.add(new String[]{"xxxxx", "2000-01-01", "1000.00", "500.0"});
		infos.add(new String[]{"yyyyy", "2000-01-01", "2000.00", "300.0"});
		 */
		return infos;
	}

	/**
	 * 获取员工所在部门VP rtxId
	 * 
	 * @param userId
	 * @return 没有找到返回空list
	 * @throws RemoteAccessException
	 */
	public List<String> findVP(String userId) throws RemoteAccessException {

		List<String> users = new ArrayList<String>();
		String apiUrl = backyardUrl + "?require=info&rtx_id=" + userId;
		JSONObject json = invokeGetApi(apiUrl);
		System.out.println(json);
		if (json.containsKey("err_id")) {
			throw new RemoteAccessException(json.getString("msg"),
					EmployeeInfoService.class);
		} else {
			JSONObject data = json.getJSONObject("data");
			Object vp = data.get("vp_mail");
			if (vp != null) {
				String user = (String) vp;
				users.add(user.split("@")[0].toLowerCase());
			}
			QMonitor.recordOne("call_qunarit_success");
		}
		return users;
	}

	/**
	 * 获取员工直接主管， 如果没有指定上级主管获取部门VP，如果VP没找到返回空list
	 * 
	 * @param userId
	 * @return 没有找到返回空list
	 * @throws RemoteAccessException
	 */
	public List<String> findManager(String userId) throws RemoteAccessException {
		List<String> users = new ArrayList<String>();
		String apiUrl = backyardUrl + "?require=info&rtx_id=" + userId;
		JSONObject json = invokeGetApi(apiUrl);
		System.out.println(json);
		if (json.containsKey("err_id")) {
			throw new RemoteAccessException(json.getString("msg"),
					EmployeeInfoService.class);
		} else {
			JSONObject data = json.getJSONObject("data");
			Object u = data.get("manager_email");
			if (u == null) {
				u = data.get("vp_email");
			}
			if (u != null) {
				String user = (String) u;
				users.add(user.split("@")[0].toLowerCase());
			}
			QMonitor.recordOne("call_qunarit_success");
		}
		return users;
	}

	/**
	 * 获取员工上上级主管； 如果员工上上级主管是VP，返回上级主管； 如果以上条件均没有获取到，返回VP，如果VP没找到返回空list
	 * @param owner 
	 * 
	 * @param userId
	 * @return
	 * @throws RemoteAccessException
	public List<String> findDirector(String userId) throws RemoteAccessException {
		List<String> users = new ArrayList<String>();
		String apiUrl = backyardUrl + "?require=info&rtx_id=" + userId;
		JSONObject json = invokeGetApi(apiUrl);
		if (json.containsKey("err_id")) {
			throw new RemoteAccessException(json.getString("msg"),
					EmployeeInfoService.class);
		}
		JSONObject data = json.getJSONObject("data");
		Object u = data.get("manager_email");
		if (u == null) {
			return users;
		}
		String user = (String) u;
		userId = user.split("@")[0];
		apiUrl = backyardUrl + "?require=info&rtx_id=" + userId;
		json = invokeGetApi(apiUrl);
		if (json.containsKey("err_id")) {
			throw new RemoteAccessException(json.getString("msg"), EmployeeInfoService.class);
		}
		data = json.getJSONObject("data");
		Object u2 = (String)data.get("manager_email");
		Object vp = (String)data.get("vp_mail");
		user = null;
		if(u2 == null && vp == null){
			user = (String) u;
		}else if(u2 == null && vp != null){
			user = (String) u;
		}else if(u2.equals(vp)){
			user = (String) u;
		}else{
			user =(String) u2;
		}
		if(user != null){
			users.add(user.split("@")[0]);
		}
		return users;
	}
	 */
	public List<String> findDirector(String owner,String dep1, String dep2, String dep3, String dep4, String dep5) throws RemoteAccessException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("dep1", dep1==null?"":dep1));
		params.add(new BasicNameValuePair("dep2", dep2==null?"":dep2));
		params.add(new BasicNameValuePair("dep3", dep3==null?"":dep3));
		params.add(new BasicNameValuePair("dep4", dep4==null?"":dep4));
		params.add(new BasicNameValuePair("dep5", dep5==null?"":dep5));
		List<String> users = new ArrayList<String>();
		String apiUrl = backyardUrl + "?require=get_tree_dep";
		//String apiUrl = "http://l-backyard1.ops.dev.cn6.qunar.com:8899/api/employees/" + "?require=get_tree_dep";
		JSONObject json = invokePostApi(apiUrl, params);
		if ("false".equals(json.getString("ret"))) {
			throw new RemoteAccessException(json.getString("msg"), EmployeeInfoService.class);
		}
		JSONObject data = json.getJSONObject("data");
		String leaders = data.getString("leaders");
		if (leaders == null || leaders.length() == 0) {
			leaders = data.getString("hrbps");
		}
		if (leaders.length() == 0) {
			throw new RemoteAccessException("没有找到leaders", EmployeeInfoService.class);
		}
		for(String leader : leaders.split(",")){
			if(leader.length() == 0) continue;
			if(!owner.equals(leader))users.add(leader.toLowerCase());//如果组内有自己审批人是自己的情况 不添加 --lee.guo
		}
		QMonitor.recordOne("call_qunarit_success");
		return users;
	}
	/***
	 * 
	 * @param owner
	 * @param dep1
	 * @param dep2
	 * @param dep3
	 * @param dep4
	 * @param dep5
	 * @param dep6
	 * @return
	 * @throws RemoteAccessException
	 */
	public List<String> findDirector(String owner) throws RemoteAccessException {
		List<String> users = new ArrayList<String>();
		String apiUrl = backyardDrt_vpUrl + "?require=drt_vp&rtx_id=" + owner;
		//String apiUrl = "http://l-backyard1.ops.dev.cn6.qunar.com:8899/api/employees/" + "?require=get_tree_dep";
		JSONObject json = invokeGetApi(apiUrl);
		if ("false".equals(json.getString("ret"))) {
			throw new RemoteAccessException(json.getString("msg"), EmployeeInfoService.class);
		}
		JSONArray datas = json.getJSONArray("data");
		List<String> drt_rtxList = new ArrayList<String>();
		for(int i=0; i<datas.size(); i++){
			JSONObject data = datas.getJSONObject(i);
			drt_rtxList .add(data.getString("drt_rtx").trim());
		}
		for (String drt_rtxs : drt_rtxList) {
			for(String leader : drt_rtxs.split(",")){
				if(leader.length() == 0) continue;
				if(!owner.equals(leader))users.add(leader.toLowerCase().trim());//如果组内有自己审批人是自己的情况 不添加 --lee.guo
			}
		}
		
		QMonitor.recordOne("call_qunarit_success");
		return users;
	}

	/**
	 * 获取工时
	 * 
	 * @param userId
	 * @param day
	 * @return
	 * @throws RemoteAccessException
	 */
	public float getLaborHour(String userId, Date day)
			throws RemoteAccessException {
		float hours = 0.00f;
		String apiUrl = backyardUrl + "?require=workhours&rtx_id=" + userId
				+ "&date=" + new SimpleDateFormat("yyyy-MM-dd").format(day);
		JSONObject json = invokeGetApi(apiUrl);
		System.out.println(json);
		if (json.containsKey("err_id") && !json.getInteger("err_id").equals(0)) {
			throw new RemoteAccessException(json.getString("msg"),
					EmployeeInfoService.class);
		} else {
			JSONObject data = json.getJSONObject("data");
			hours = data.getFloatValue("hours");
			QMonitor.recordOne("call_qunarit_success");
		}
		return hours;
	}

	/**
	 * 
	 * @param apiUrl
	 * @return JSONObject对象，内容为返回结果
	 * @throws RemoteAccessException
	 */
	private JSONObject invokeGetApi(String apiUrl) throws RemoteAccessException {
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
						QMonitor.recordOne("call_qunarit_fail");
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}

			};
			String responseBody = httpclient.execute(httpget, responseHandler);
			JSONObject json = JSONObject.parseObject(responseBody);
			if (json == null) {
				throw new RemoteAccessException("responseBody is null",
						EmployeeInfoService.class);
			}
			return json;
		} catch (IOException e) {
			QMonitor.recordOne("call_qunarit_fail");
			e.printStackTrace();
			throw new RemoteAccessException(e.getMessage(),
					EmployeeInfoService.class);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RemoteAccessException(e.getMessage(),
						EmployeeInfoService.class);
			}
		}
	}
	
	/**
	 * 
	 * @param apiUrl
	 * @return JSONObject对象，内容为返回结果
	 * @throws RemoteAccessException
	 */
	private JSONObject invokePostApi(String apiUrl, List<NameValuePair> params) throws RemoteAccessException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(apiUrl);
			//httppost.setHeader("Content-Type", "application/json; charset=UTF-8");
			httppost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity)
								: null;
					} else {
						QMonitor.recordOne("call_qunarit_fail");
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}

			};
			String responseBody = httpclient.execute(httppost, responseHandler);
			JSONObject json = JSONObject.parseObject(responseBody);
			if (json == null) {
				throw new RemoteAccessException("responseBody is null",
						EmployeeInfoService.class);
			}
			return json;
		} catch (IOException e) {
			QMonitor.recordOne("call_qunarit_fail");
			e.printStackTrace();
			throw new RemoteAccessException(e.getMessage(),
					EmployeeInfoService.class);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RemoteAccessException(e.getMessage(),
						EmployeeInfoService.class);
			}
		}
	}

	public static void main(String[] args) throws RemoteAccessException {
//		EmployeeInfoService s = new ClassPathXmlApplicationContext(
//				new String[] { "spring.xml" })
//				.getBean(EmployeeInfoService.class);
//		System.out.println(s.getEmployee("yongnian.jiang").getAdName());
//		System.out.println(s.getLaborHour("yongnian.jiang", new Date()));
		
		EmployeeInfoService employeeInfoService = new EmployeeInfoService();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("dep1", "技术部"));
		params.add(new BasicNameValuePair("dep2", "OPS-IT-CCops"));
		params.add(new BasicNameValuePair("dep3", "Dev"));
		params.add(new BasicNameValuePair("dep4", "HrDev"));
		params.add(new BasicNameValuePair("dep5", ""));
		JSONObject json = employeeInfoService.invokePostApi("http://l-backyard1.ops.dev.cn6.qunar.com:8899/api/employees/?require=get_tree_dep", params);
		System.out.println(json.toJSONString());
	}
	
	
	
	/**
	 * 获取员工信息HRBP
	 * 
	 * @param userId
	 * @param hrbpList 
	 * @param string 
	 * @return
	 * @throws RemoteAccessException
	 */
	public List<String> getEmployeeHRBP(String userId, String dep1, List<GroupInfo> hrbpGroupList) throws RemoteAccessException {
		List<String> hrbpList = new ArrayList<String>();
		for (GroupInfo groupInfo : hrbpGroupList) {
			for(GroupMember mem : groupInfo.getMembers()){
				if(dep1.equals(getEmployee(mem.getMemberUserId().toLowerCase()).getDepartmentI())){
					hrbpList.add(mem.getMemberUserId().toLowerCase());
				};
			}
		}
		if(hrbpList.size()>0)return hrbpList;
		
		String apiUrl = backyardUrl + "?require=info_mask&userId=" + userId;
		JSONObject json = invokeGetApi(apiUrl);
		String hrbp_rtxs;
		if (json.containsKey("err_id")) {
			
			throw new RemoteAccessException(json.getString("msg"),
					EmployeeInfoService.class);
		} else {
			JSONObject data = json.getJSONObject("data");
			// EmployeeInfo eInfo = JSONObject.toJavaObject(data,
			// EmployeeInfo.class);
			hrbp_rtxs = (String) data.get("hrbp");
			if(hrbp_rtxs!=null&&"".equals(hrbp_rtxs)){
				for(String htbp : hrbp_rtxs.split(",")){
					hrbpList.add(htbp);
				}
			}
			
			QMonitor.recordOne("call_qunarit_success");
		}
		
		if(hrbpList.size()==0)hrbpList.add(hrbp_rtxs);
		return hrbpList;
	}
/**
 *自动补全
 * @param key
 * @return 
 */
	public JSONArray getSearchRtx(String key) {
		EmployeeInfo eInfo = new EmployeeInfo();
		String apiUrl = "http://qunar.it/s/?kw=" + key;
		Object object = null;
		try {
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
							QMonitor.recordOne("call_qunarit_fail");
							throw new ClientProtocolException(
									"Unexpected response status: " + status);
						}
					}

				};
				String responseBody = httpclient.execute(httpget, responseHandler);
				 JSONArray json = JSONArray.parseArray(responseBody);
				if (json == null) {
					throw new RemoteAccessException("responseBody is null",
							EmployeeInfoService.class);
				}
				return json;
				/*int size = json.size();
				JSONObject jo = json.getJSONObject(0);
				for(int i=0;i<size;i++){
		            JSONObject jo1 = json.getJSONObject(i);
		            System.out.println(jo1.getString("label")); //循环返回网址
		        }*/
			} catch (IOException e) {
				QMonitor.recordOne("call_qunarit_fail");
				e.printStackTrace();
				throw new RemoteAccessException(e.getMessage(),
						EmployeeInfoService.class);
			} finally {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RemoteAccessException(e.getMessage(),
							EmployeeInfoService.class);
				}
			}
		} catch (RemoteAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
