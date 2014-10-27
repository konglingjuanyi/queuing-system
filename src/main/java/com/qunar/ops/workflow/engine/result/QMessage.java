package com.qunar.ops.workflow.engine.result;

import java.io.Serializable;

public class QMessage implements Serializable{
	private static final long serialVersionUID = -6642080927039164528L;
	private SendToMQInfo info;
	private String sysName;
	private String token;
	public QMessage(String sysName, String token, SendToMQInfo info) {
		this.info = info;
		this.token = token;
		this.sysName = sysName;
	}
	public SendToMQInfo getInfo() {
		return info;
	}
	public void setInfo(SendToMQInfo info) {
		this.info = info;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}

