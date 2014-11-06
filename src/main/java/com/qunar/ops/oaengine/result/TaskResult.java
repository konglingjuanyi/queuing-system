package com.qunar.ops.oaengine.result;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.task.Task;

public class TaskResult {
	private Task currentTask;
	private List<TaskInfo> nextTasks = new ArrayList<TaskInfo>();
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
	public TaskResult(Task currentTask, List<TaskInfo> nextTasks) {
		this.currentTask = currentTask;
		this.nextTasks = nextTasks;
	}
}
