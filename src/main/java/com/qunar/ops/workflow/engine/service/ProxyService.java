package com.qunar.ops.workflow.engine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.task.Task;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.qunar.ops.workflow.engine.domain.CompletMessage;
import com.qunar.ops.workflow.engine.domain.RefuseMessage;
import com.qunar.ops.workflow.engine.domain.TaskMessage;

@Component
public class ProxyService {
	  //@Autowired
	  private RedisTemplate<String, String> template;
	  
	  //@Autowired
	  private RabbitTemplate amqpTemplate;

	  //@Resource(name="redisTemplate")
	  private HashOperations<String, String, TaskMessage> hashOps;
	  
	  public void addTasks(List<Task> tasks, String userId, Map<String, Object> vars, Map<String, String> form) {
		  if(tasks != null)for(Task task : tasks){
			  CompletMessage msg = new CompletMessage(task, userId, vars, form);
			  hashOps.put("tasks", task.getId(), msg);
			  this.amqpTemplate.convertAndSend("approve_center.notification", msg);
		  }
	  }
	  
	  public void addTasks(List<Task> tasks, String userId, String reason) {
		  if(tasks != null)for(Task task : tasks){
			  RefuseMessage msg = new RefuseMessage(task, userId, reason);
			  hashOps.put("tasks", task.getId(), msg);
			  this.amqpTemplate.convertAndSend("approve_center.notification", msg);
		  }
	  }
	  
	  public void removeTask(String taskId){
		  this.hashOps.delete("tasks", taskId);
	  }
	  
	  public Boolean hasKey(String taskId){
		  return this.hashOps.hasKey("tasks", taskId);
	  }
	  
	  public void updateTask(String taskId, TaskMessage taskMessage){
		  this.hashOps.put("tasks", taskId, taskMessage);
	  }
	  
	  public int getStatusByTaskId(String taskId){
		  TaskMessage taskMessage = this.hashOps.get("tasks", taskId);
		  if(taskMessage == null) return 1;
		  return taskMessage.getStatus();
	  }
	  
	  public List<TaskMessage> getAllTasks(){
		  Set<String> keys = this.hashOps.keys("tasks");
		  List<TaskMessage> tasks = this.hashOps.multiGet("tasks", keys);
		  if(tasks == null) tasks = new ArrayList<TaskMessage>();
		  return tasks;
	  }
}
