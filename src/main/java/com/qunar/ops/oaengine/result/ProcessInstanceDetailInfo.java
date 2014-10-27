package com.qunar.ops.oaengine.result;

import java.util.List;

public class ProcessInstanceDetailInfo extends ProcessInstanceInfo{
	
	private List<TaskInfo> taskTrace;

	public List<TaskInfo> getTaskTrace() {
		return taskTrace;
	}

	public void setTaskTrace(List<TaskInfo> taskTrace) {
		this.taskTrace = taskTrace;
	}
	

}
