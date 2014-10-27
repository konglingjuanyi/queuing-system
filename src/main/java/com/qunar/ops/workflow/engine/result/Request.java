package com.qunar.ops.workflow.engine.result;

import java.util.HashMap;
import java.util.Map;

public class Request {
	private String oid;
	private String userId;
	private Map<String, Object> vars;
	private Map<String, String> form;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Map<String, Object> getVars() {
		if(vars == null) return new HashMap<String, Object>();
		return vars;
	}
	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}
	public Map<String, String> getForm() {
		if(form == null) return new HashMap<String, String>();
		return form;
	}
	public void setForm(Map<String, String> form) {
		this.form = form;
	}

}
