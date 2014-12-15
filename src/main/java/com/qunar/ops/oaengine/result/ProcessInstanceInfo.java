package com.qunar.ops.oaengine.result;

import java.io.Serializable;
import java.util.List;

public class ProcessInstanceInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8933545444370281313L;
	private String processInstanceId;
	private List<TaskInfo> taskInfos;
	
	public List<TaskInfo> getTaskInfos() {
		return taskInfos;
	}
	public void setTaskInfos(List<TaskInfo> taskInfos) {
		this.taskInfos = taskInfos;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
}
