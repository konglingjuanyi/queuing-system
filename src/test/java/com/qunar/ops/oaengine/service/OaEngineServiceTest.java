package com.qunar.ops.oaengine.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.activiti.engine.ActivitiException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qunar.ops.oaengine.exception.CompareModelException;
import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.Form0114Manager;
import com.qunar.ops.oaengine.manager.WorkflowManager;
import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.result.TaskInfo;
import com.qunar.ops.oaengine.result.TaskResult;
import com.qunar.ops.oaengine.result.dailysubmit.EmployeeRelationsFeesInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfoList;
import com.qunar.ops.oaengine.result.dailysubmit.HospitalityInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OtherCostsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OvertimeMealsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.TaxiFaresInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class OaEngineServiceTest {
	@Autowired(required=true)
	protected IOAEngineService service;
	
	@Autowired
	protected WorkflowManager manager;
	
	@Autowired
	private Form0114Manager form0114manager;
	
	private Long formId;
	private String taskId;
	
	@After
	public void destory_data() throws FormNotFoundException{
		this.manager.cancel("oa_common", ""+formId, "nuby.zhang", "xxx");
		form0114manager.deleteFormInfo("yongnian.jiang", formId);
	}
	
	@Test
	public void createFormAndstartTest(){
		try {
			this.formId = this.service.createFormAndstart("oa_common", "nuby.zhang", this.initFormInfo());
			FormInfoList list = this.service.todoList("oa_common", "nuby.zhang", null, null, null, 0, 10);
			Assert.assertEquals(1, list.getPageCount());
			FormInfo info = list.getFormInfos().get(0);
			this.taskId = info.getTaskId();
			
		} catch (RemoteAccessException e) {
			e.printStackTrace();
		} catch (CompareModelException e) {
			e.printStackTrace();
		} catch (FormNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	//@Test
	public void passTest(){
		try {
			this.service.pass("oa_common", "nuby.zhang", this.formId, this.taskId, null);
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
		try {
			this.service.back("oa_common", "nuby.zhang", 2L, "380013", "xxxx");
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
		try {
			this.service.endorse("oa_common", "nuby.zhang", 2L, "380013", "nuby.zhang", "xxxx");
		} catch (ActivitiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test
	public void refuseTest(){
		try {
			this.service.refuse("oa_common", "nuby.zhang", 4L, "387522", "xxx");
		} catch (ActivitiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private FormInfo initFormInfo(){
		FormInfo formInfo = new FormInfo();
		formInfo.setOtherCostsInfo(new OtherCostsInfo[]{});
		formInfo.setHospitalityInfo(new HospitalityInfo[]{});
		formInfo.setOvertimeMealsInfo(new OvertimeMealsInfo[]{});
		formInfo.setEmployeeRelationsFeesInfo(new EmployeeRelationsFeesInfo[]{});
		formInfo.setTaxiFaresInfo(new TaxiFaresInfo[]{});
		//这里set无效
		formInfo.setId(-1l);
		formInfo.setOid("jyn_test");
		formInfo.setProcInstId("jyn_test");
		formInfo.setProcTitle("姜永念测试");
		formInfo.setState(1);
		formInfo.setStartMemberId("");
		formInfo.setStartDate(new Date());
		formInfo.setApproveMemberId("");
		formInfo.setApproveDate(new Date());
		formInfo.setFinishedflag(0);
		formInfo.setRatifyflag(0);
		formInfo.setRatifyMemberId("");
		formInfo.setRatifyDate(new Date());
		formInfo.setFirstDep("技术部");
		formInfo.setSecDep("测试");
		formInfo.setThridDep("");
		formInfo.setApplyUser("yongnian.jiang");
		formInfo.setApplyDate(new Date());
		formInfo.setBorrowSN("1");
		formInfo.setIsBorrow("1");
		formInfo.setSerialNumber("123");
		formInfo.setFourthDep("");
		formInfo.setSumOtherAmount(123l);
		formInfo.setMoneyAmount(123l);
		formInfo.setDepLeaderOpinion("测试");
		formInfo.setDepLeaderDate(new Date());
		formInfo.setDepLeaderSign("测试");
		formInfo.setSupDepLeaderDate(new Date());
		formInfo.setSupDepLeaderOpinion("o");
		formInfo.setSupDepLeaderSign("s");
		formInfo.setDepDirectorDate(new Date());
		formInfo.setDepDirectorOpinion("o");
		formInfo.setDepDirectorSign("s");
		formInfo.setVpDate(new Date());
		formInfo.setVpOption("o");
		formInfo.setVpSign("s");
		formInfo.setCeoDate(new Date());
		formInfo.setCeoOption("o");
		formInfo.setCeoSign("s");
		formInfo.setCashierDate(new Date());
		formInfo.setCashierOpinion("o");
		formInfo.setCashierSign("s");
		formInfo.setSumOvertimeMealsAmount(100l);
		formInfo.setSumHospitalityAmount(100l);
		formInfo.setSumEmployeeRelationsFees(100l);
		formInfo.setSumTaxiFaresAmount(100l);
		formInfo.setDepLevel("1");
		formInfo.setUserNumber("123");
		formInfo.setOvertimeMealsNotifyAmount(100l);
		formInfo.setHospitalityNotifyAmount(100l);
		formInfo.setEmRelationsFeesNotify(100l);
		formInfo.setTaxiFaresNotifyAmount(100l);
		formInfo.setSumFinancialNotify(100l);
		formInfo.setOtherNotifyAmount(100l);
		formInfo.setFinancialOption("1");
		formInfo.setFinancialDate(new Date());
		formInfo.setFinancialSign("s");
		formInfo.setApplyDep("abc");
		formInfo.setDepNum("1");
		formInfo.setBankNumber("123");
		formInfo.setOnlyFirstDep("");
		formInfo.setFinancialDirectorDate(new Date());
		formInfo.setFinancialDirectorOption("");
		formInfo.setFinancialDirectorSign("");
		formInfo.setCeoDate1(new Date());
		formInfo.setCeoOption1("");
		formInfo.setCeoSign1("");
		formInfo.setBorrowAmount(100l);
		formInfo.setCommunicationCosts(100l);
		formInfo.setCommuCostsComment("");
		formInfo.setRtxId("100");
		formInfo.setReimbursementTeamName("");
		formInfo.setReimbursementTeamDate(new Date());
		formInfo.setAddress("source");
		formInfo.setIsDirectVp("");
		formInfo.setBankName("");
		return formInfo;
	}
}
