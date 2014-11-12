package com.qunar.ops.oaengine.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.manager.WorkflowManager;
import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.result.TaskInfo;
import com.qunar.ops.oaengine.result.TaskResult;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class OaEngineServiceTest {
	@Autowired(required=true)
	protected IOAEngineService service;
	
	@Autowired
	protected WorkflowManager manager;
	
	
	//@Test
	public void passTest(){
		Request req = new Request();
		req.setDepartment("技术部");
		req.setTbMoney(100L);
		req.setAmountMoney(200L);
		req.setReport2vp(false);
		Object[] startWorkflow = this.manager.startWorkflow("oa_common", "nuby.zhang", req);
		List<TaskInfo> infos = (List<TaskInfo>)startWorkflow[1];
		TaskInfo info = infos.get(0);
		try {
			this.service.pass("oa_common", "nuby.zhang", 1000, info.getTaskId(), "xxxx");
		} catch (ActivitiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test
	public void backTest(){
		Request req = new Request();
		req.setDepartment("技术部");
		req.setTbMoney(100L);
		req.setAmountMoney(200L);
		req.setReport2vp(false);
		Object[] startWorkflow = this.manager.startWorkflow("oa_common", "nuby.zhang", req);
		List<TaskInfo> infos = (List<TaskInfo>)startWorkflow[1];
		TaskInfo info = infos.get(0);
		try {
			TaskResult pass = this.manager.pass(info.getTaskId(), "nuby.zhang");
			String taskId = pass.getNextTasks().get(0).getTaskId();
			this.service.back("oa_common", "nuby.zhang", 1000, taskId, "xxxx");
		} catch (ActivitiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test
	public void endorseTest(){
		Request req = new Request();
		req.setDepartment("技术部");
		req.setTbMoney(100L);
		req.setAmountMoney(200L);
		req.setReport2vp(false);
		Object[] startWorkflow = this.manager.startWorkflow("oa_common", "nuby.zhang", req);
		List<TaskInfo> infos = (List<TaskInfo>)startWorkflow[1];
		TaskInfo info = infos.get(0);
		try {
			this.service.endorse("oa_common", "nuby.zhang", 1000, info.getTaskId(), "nuby.zhang, xxx0", "x");
		} catch (ActivitiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
