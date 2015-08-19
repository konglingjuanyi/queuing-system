package com.qunar.ops.recruit.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qunar.flight.qmonitor.QMonitor;
import com.qunar.ops.recruit.dao.InterviewerMapper;
import com.qunar.ops.recruit.model.Interviewer;

@Component
public class InterviewerService {

	@Value("${backyard.apihost}")
	String backyardUrl;
	
	@Value("${backyard.apihost.bank}")
	String backyardBankUrl;
	
	@Value("${backyard.drt_vp}")
	String backyardDrt_vpUrl;
	
	@Value("${oa.apihost}")
	String oadUrl;
	
	@Autowired
	InterviewerMapper interMapper;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<Interviewer> getAllInterviewer() {
		return interMapper.getAll();
	}

	public List<Interviewer> getInterviewers(int offset, int limit) {
		// TODO Auto-generated method stub
		return interMapper.getInterviewers(offset, limit);
	}

}
