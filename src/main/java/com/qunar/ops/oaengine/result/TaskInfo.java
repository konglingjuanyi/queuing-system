package com.qunar.ops.oaengine.result;

import java.io.Serializable;
import java.util.Date;

public class TaskInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8933545444370281313L;
	private String oid;
	private String taskId;
	private String processInstanceId;
	private boolean endorse;
	private String candidate;
	private String taskKey;
	private String taskName;
	private Date endTime;
	private Date taskCreateTime;
	
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public boolean isEndorse(){
		return this.endorse;
	}
	public void setEndorse(boolean isEndorse){
		this.endorse = isEndorse;
	}
	public String getCandidate() {
		return candidate;
	}
	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}
	public String getTaskKey() {
		return taskKey;
	}
	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getTaskCreateTime() {
		return taskCreateTime;
	}
	public void setTaskCreateTime(Date taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}

}
