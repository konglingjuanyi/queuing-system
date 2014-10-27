package com.qunar.ops.workflow.engine.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.spring.impl.test.SpringActivitiTestCase;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author yongnian.jiang
 * 
 * 2011-12-20  下午09:29:23
 */
@ContextConfiguration("classpath:spring.xml")
public class ServiceTaskExpressionTest extends SpringActivitiTestCase {
  
  @Deployment(resources={"com/test/service_demo.bpmn"})
  public void testDelegateExpression() {
	  
    ProcessInstance procInst = runtimeService.startProcessInstanceByKey("service_demo");
    List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInst.getId()).list();
    Map<String, Object> vars = new HashMap<String, Object>();
    for (Task task : tasks) {
    	String taskId = task.getId();
    	System.out.println("Following task is: taskID -" +taskId+" taskName -"+ task.getName() + "taskKey" + task.getTaskDefinitionKey());
    	// 认领任务  
    	taskService.claim(task.getId(), "yongnian.jiang");
    	
    	vars.put("var1", "test1");
    	vars.put("var2", "test2");
    	taskService.setVariablesLocal(taskId, vars);
    	taskService.complete(taskId, vars);
    }
    assertEquals(true,procInst.isEnded());
  }

}
