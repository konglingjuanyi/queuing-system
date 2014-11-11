package com.qunar.ops.oaengine.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.result.TaskInfo;
import com.qunar.ops.oaengine.result.TaskResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class WorkflowManagerTest {
	@Autowired
	protected WorkflowManager manager;
	
	/**
	 * 技术部普通员工<5000报销 			[tb_check, direct_manager, director, fin_check, cashier, null]
	 * 技术部普通员工>=5000<10000报销		[tb_check, direct_manager, director, vp, fin_check, fin_director, cashier, null]
	 * 技术部普通员工>=10000报销
	 * 技术部总监<5000报销
	 * 技术部总监>=5000<10000报销
	 * 技术部总监>=10000报销
	 * 
	 * 技术部-研发支持普通员工<5000报销
	 * 技术部-研发支持普通员工>=5000<10000报销
	 * 技术部-研发支持普通员工>=10000报销
	 * 技术部-研发支持总监<5000报销
	 * 技术部-研发支持总监>=5000<10000报销
	 * 技术部-研发支持总监>=10000报销
	 * 
	 * 目的地事业部普通员工<5000报销
	 * 目的地事业部普通员工>=5000<10000报销
	 * 目的地事业部普通员工>=10000报销
	 * 目的地事业部总监<5000报销
	 * 目的地事业部总监>=5000<10000报销
	 * 目的地事业部总监>=10000报销
	 * 
	 * 酒店事业部普通员工<5000报销
	 * 酒店事业部普通员工>=5000<10000报销
	 * 酒店事业部普通员工>=10000报销
	 * 酒店事业部总监<5000报销
	 * 酒店事业部总监>=5000<10000报销
	 * 酒店事业部总监>=10000报销
	 */
	
	
	private String _start(Request req){
		Object[] startWorkflow = this.manager.startWorkflow("oa_common", "nuby.zhang", req);
		List<TaskInfo> infos = (List<TaskInfo>)startWorkflow[1];
		TaskInfo info = infos.get(0);
		List<String> records = new ArrayList<String>();
		records.add(info.getTaskKey());
		this._pass(info.getTaskId(), records);
		return records.toString();
	}
	
	private void _pass(String taskId, List<String> records){
		if(taskId == null) return;
		TaskResult pass = this.manager.pass(taskId, "nuby.zhang");
		List<TaskInfo> nextTasks = pass.getNextTasks();
		if(nextTasks.isEmpty()) return;
		TaskInfo info = nextTasks.get(0);
		records.add(info.getTaskKey());
		this._pass(info.getTaskId(), records);
	}
	
	
	//@Test
	public void workflowITest(){
		Request req = new Request();
		String _start = null;
		req.setDepartment("技术部");
		req.setTbMoney(100l);
		
		req.setAmountMoney(200l);
		req.setReport2vp(false);
		Assert.assertEquals("[tb_check, direct_manager, director, fin_check, cashier, null]", this._start(req));
		
		req.setAmountMoney(500000l);
		req.setReport2vp(false);
		_start = this._start(req);
		Assert.assertEquals("[tb_check, direct_manager, director, vp, fin_check, fin_director, cashier, null]", _start);
		
		req.setAmountMoney(1000000l);
		req.setReport2vp(false);
		_start = this._start(req);
		Assert.assertEquals("[tb_check, direct_manager, director, vp, fin_check, fin_director, cfo, cashier, null]", _start);
		

		req.setAmountMoney(100000l);
		req.setReport2vp(true);
		_start = this._start(req);
		Assert.assertEquals("[tb_check, vp, fin_check, cashier, null]", _start);
		
		req.setAmountMoney(500000l);
		req.setReport2vp(true);
		_start = this._start(req);
		Assert.assertEquals("[tb_check, vp, fin_check, fin_director, cashier, null]", _start);
		
		req.setAmountMoney(1000000l);
		req.setReport2vp(true);
		_start = this._start(req);
		Assert.assertEquals("[tb_check, vp, fin_check, fin_director, cfo, cashier, null]", _start);
	}
	
	//@Test
	public void workflowIITest(){
		Request req = new Request();
		String _start = null;
		req.setDepartment("技术部");
		req.setDepartmentII("研发支持");
		req.setTbMoney(100l);
		
		req.setAmountMoney(200l);
		req.setReport2vp(false);
		Assert.assertEquals("[tb_check, direct_manager, haiyan.bao, fin_check, cashier, null]", this._start(req));
		
		req.setAmountMoney(500000l);
		req.setReport2vp(false);
		_start = this._start(req);
		Assert.assertEquals("[tb_check, direct_manager, haiyan.bao, vp, fin_check, fin_director, cashier, null]", _start);
		
		req.setAmountMoney(1000000l);
		req.setReport2vp(false);
		_start = this._start(req);
		Assert.assertEquals("[tb_check, direct_manager, haiyan.bao, vp, fin_check, fin_director, cfo, cashier, null]", _start);
		

		req.setAmountMoney(100000l);
		req.setReport2vp(true);
		_start = this._start(req);
		Assert.assertEquals("[tb_check, vp, fin_check, cashier, null]", _start);
		
		req.setAmountMoney(500000l);
		req.setReport2vp(true);
		_start = this._start(req);
		Assert.assertEquals("[tb_check, vp, fin_check, fin_director, cashier, null]", _start);
		
		req.setAmountMoney(1000000l);
		req.setReport2vp(true);
		_start = this._start(req);
		Assert.assertEquals("[tb_check, vp, fin_check, fin_director, cfo, cashier, null]", _start);
	}
	
	//@Test
	public void workflowIIITest(){
		Request req = new Request();
		String _start = null;
		req.setDepartment("酒店事业部");
		req.setTbMoney(100l);
		
		req.setAmountMoney(200l);
		req.setReport2vp(false);
		Assert.assertEquals("[tao.feng, direct_manager, director, fin_check, cashier, null]", this._start(req));
		
		req.setAmountMoney(500000l);
		req.setReport2vp(false);
		_start = this._start(req);
		Assert.assertEquals("[tao.feng, direct_manager, director, vp, fin_check, fin_director, cashier, null]", _start);
		
		req.setAmountMoney(1000000l);
		req.setReport2vp(false);
		_start = this._start(req);
		Assert.assertEquals("[tao.feng, direct_manager, director, vp, fin_check, fin_director, cfo, cashier, null]", _start);
		

		req.setAmountMoney(100000l);
		req.setReport2vp(true);
		_start = this._start(req);
		Assert.assertEquals("[tao.feng, zoe.jin, vp, fin_check, cashier, null]", _start);
		
		req.setAmountMoney(500000l);
		req.setReport2vp(true);
		_start = this._start(req);
		Assert.assertEquals("[tao.feng, zoe.jin, vp, fin_check, fin_director, cashier, null]", _start);
		
		req.setAmountMoney(1000000l);
		req.setReport2vp(true);
		_start = this._start(req);
		Assert.assertEquals("[tao.feng, zoe.jin, vp, fin_check, fin_director, cfo, cashier, null]", _start);
	}
	
	
	@Test
	public void workflowIVTest(){
		Request req = new Request();
		String _start = null;
		req.setDepartment("目的地事业部");
		req.setTbMoney(100l);
		
		req.setAmountMoney(200l);
		req.setReport2vp(false);
		Assert.assertEquals("[tao.feng, direct_manager, director, fin_check_mdd, cashier, null]", this._start(req));
		
		req.setAmountMoney(500000l);
		req.setReport2vp(false);
		_start = this._start(req);
		Assert.assertEquals("[tao.feng, direct_manager, director, vp, fin_check_mdd, fin_director, cashier, null]", _start);
		
		req.setAmountMoney(1000000l);
		req.setReport2vp(false);
		_start = this._start(req);
		Assert.assertEquals("[tao.feng, direct_manager, director, vp, fin_check_mdd, fin_director, cfo, cashier, null]", _start);
		

		req.setAmountMoney(100000l);
		req.setReport2vp(true);
		_start = this._start(req);
		Assert.assertEquals("[tao.feng, vp, fin_check_mdd, cashier, null]", _start);
		
		req.setAmountMoney(500000l);
		req.setReport2vp(true);
		_start = this._start(req);
		Assert.assertEquals("[tao.feng, vp, fin_check_mdd, fin_director, cashier, null]", _start);
		
		req.setAmountMoney(1000000l);
		req.setReport2vp(true);
		_start = this._start(req);
		Assert.assertEquals("[tao.feng, vp, fin_check_mdd, fin_director, cfo, cashier, null]", _start);
	}
//	@Test
//	public void endorseTest(){
//		TaskResult endorse = this.manager.endorse("10023", "nuby.zhang", "nuby.zhang");
//		Assert.assertNotNull(endorse);
//		Task info = endorse.getCurrentTask();
//		Assert.assertEquals("tb_check", info.getTaskDefinitionKey());
//	}
	
//	@Test
//	public void passTest(){
//		TaskResult pass = this.manager.pass("7523", "nuby.zhang");
//		Assert.assertNotNull(pass);
//		List<TaskInfo> infos = pass.getNextTasks();
//		Assert.assertEquals(1, infos.size());
//		TaskInfo info = infos.get(0);
//	}
//	
//	@Test
//	public void backTest(){
//		TaskResult back = this.manager.back("nuby.zhang", "20018", "xxx");
//		Assert.assertNotNull(back);
//		List<TaskInfo> infos = back.getNextTasks();
//		Assert.assertEquals(1, infos.size());
//		TaskInfo info = infos.get(0);
//		Assert.assertEquals("tb_check", info.getTaskKey());
//	}
}
