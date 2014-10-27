package com.qunar.ops.workflow.engine.domain;

import java.io.Serializable;
import java.util.Date;

import org.activiti.engine.task.Task;

public class TaskMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6532073539773198417L;
	protected int status;
	protected String errorMessage;
	protected Task task;
	protected String userId;
	protected Date operateTime;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Task getTask() {
		return task;
	}
	public String getUserId() {
		return userId;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
}
