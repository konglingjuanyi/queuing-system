package com.qunar.ops.oaengine.result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SendToMQInfo implements Serializable{

	private String startedUser;

	private List<Map<String, Object>> tasks;

	public String getStartedUser() {
		return startedUser;
	}

	public void setStartedUser(String startedUser) {
		this.startedUser = startedUser;
	}

	public List<Map<String, Object>> getTasks() {
		return tasks;
	}

	public void setTasks(List<Map<String, Object>> tasks) {
		this.tasks = tasks;
	}

}
