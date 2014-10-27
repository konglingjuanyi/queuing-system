package com.qunar.ops.oaengine.domain;

import java.io.Serializable;
import java.util.Date;

import org.activiti.engine.task.Task;

public class RefuseMessage extends TaskMessage implements Serializable{

	private static final long serialVersionUID = 4284321109659362156L;
	
	private String reason;
	
	public RefuseMessage(Task task, String userId, String reason) {
		this.task = task;
		this.userId = userId;
		this.reason = reason;
		this.operateTime = new Date();
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	
}
