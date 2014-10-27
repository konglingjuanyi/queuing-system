package com.qunar.ops.oaengine.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessInstanceInfo {

	private String processInstanceId;

	private String oid;

	private Date startTime;

	private Date endTime;
	
	private String processKey;
	
	private String isFinished;
	
	public String getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(String isFinished) {
		this.isFinished = isFinished;
	}

	private Map<String, Object> form = new HashMap<String, Object>();
	
	private List<TaskInfo> currentTasks;

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public List<TaskInfo> getCurrentTasks() {
		return currentTasks;
	}

	public void setCurrentTasks(List<TaskInfo> currentTasks) {
		this.currentTasks = currentTasks;
	}

	public void addCurrentTasks(TaskInfo currentTask) {
		if (currentTasks == null)
			currentTasks = new ArrayList<TaskInfo>();
		this.currentTasks.add(currentTask);
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Map<String, Object> getForm() {
		return form;
	}

	public void setForm(Map<String, Object> form) {
		this.form = form;
	}
	
	public void addFormItem(String key, Object value){
		this.form.put(key, value);
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

}
