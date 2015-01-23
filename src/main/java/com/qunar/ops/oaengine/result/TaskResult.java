package com.qunar.ops.oaengine.result;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.task.Task;

public class TaskResult {
	private String owner;
	private String cname;
	private Task currentTask;
	private List<TaskInfo> nextTasks = new ArrayList<TaskInfo>();
	
	public TaskResult(String owner, String cname, Task currentTask, List<TaskInfo> nextTasks) {
		this.currentTask = currentTask;
		this.nextTasks = nextTasks;
		this.owner = owner;
		this.cname = cname;
	}
	
	public List<TaskInfo> getNextTasks() {
		return nextTasks;
	}
	
	public void setNextTasks(List<TaskInfo> nextTasks) {
		this.nextTasks = nextTasks;
	}
	
	public Task getCurrentTask() {
		return currentTask;
	}
	
	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}
	
	public boolean isFinished(){
		TaskInfo info = this.nextTasks.get(0);
		return info.getTaskId() == null ? true : false;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
}
