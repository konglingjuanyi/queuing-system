package com.qunar.ops.workflow.engine.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;

public class CompletMessage extends TaskMessage implements Serializable{

	private static final long serialVersionUID = -6979237728528243073L;
	
	private Map<String, Object> vars;
	private Map<String, String> form;
	
	public CompletMessage(Task task, String userId, Map<String, Object> vars, Map<String, String> form) {
		this.task = task;
		this.userId = userId;
		this.vars = vars;
		this.form = form;
		this.operateTime = new Date();
	}

	public Map<String, Object> getVars() {
		return vars;
	}

	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}

	public Map<String, String> getForm() {
		return form;
	}

	public void setForm(Map<String, String> form) {
		this.form = form;
	}
	
}
