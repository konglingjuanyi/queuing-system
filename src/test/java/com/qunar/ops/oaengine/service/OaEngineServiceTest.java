package com.qunar.ops.oaengine.service;

import java.util.Date;

import junit.framework.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qunar.ops.oaengine.exception.CompareModelException;
import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.Form0114Manager;
import com.qunar.ops.oaengine.manager.WorkflowManager;
import com.qunar.ops.oaengine.result.dailysubmit.EmployeeRelationsFeesInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfoList;
import com.qunar.ops.oaengine.result.dailysubmit.HospitalityInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OtherCostsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OvertimeMealsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.TaxiFaresInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OaEngineServiceTest {
	@Autowired(required=true)
	protected IOAEngineService service;
	
	@Autowired
	protected WorkflowManager manager;
	
	@Autowired
	private Form0114Manager form0114manager;
	
	
	//@Test
	public void TestCreateFormAndstart(){
		try {
			long formId = this.service.createFormAndstart("oa_common", "nuby.zhang", "nuby.zhang", this.initFormInfo());
			FormInfoList list = this.service.todoList("oa_common", "nuby.zhang", null, null, null, 0, 10);
			Assert.assertEquals(1, list.getCount());
			FormInfo info = list.getFormInfos().get(0);
			String taskId = info.getTaskId();
			this.service.refuse("oa_common", "nuby.zhang", "nuby.zhang", formId, taskId, "xxx");
			
		} catch (RemoteAccessException e) {
			e.printStackTrace();
		} catch (CompareModelException e) {
			e.printStackTrace();
		} catch (FormNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	//@Test
	public void TestPass(){
		try {
			long formId = this.service.createFormAndstart("oa_common", "nuby.zhang", "nuby.zhang", this.initFormInfo());
			FormInfoList list = this.service.todoList("oa_common", "nuby.zhang", null, null, null, 0, 10);
			Assert.assertEquals(1, list.getCount());
			FormInfo info = list.getFormInfos().get(0);
			String taskId = info.getTaskId();
			this.service.pass("oa_common", "nuby.zhang", "nuby.zhang", formId, taskId, null);
			this.service.refuse("oa_common", "nuby.zhang", "nuby.zhang", formId, null, "xxx");
		} catch (Exception e) {
			// TODO Auto-generatd catch block
			e.printStackTrace();
		} 
	}
	
	
	//@Test
	public void TestEndorse(){
		try {
			long formId = this.service.createFormAndstart("oa_common", "nuby.zhang", "nuby.zhang", this.initFormInfo());
			FormInfoList list = this.service.todoList("oa_common", "nuby.zhang", null, null, null, 0, 10);
			//Assert.assertEquals(1, list.getPageCount());
			FormInfo info = list.getFormInfos().get(0);
			String taskId = info.getTaskId();
			this.service.endorse("oa_common", "nuby.zhang", "nuby.zhang", formId, taskId, "nuby.zhang,yongnina.jiang", null);
			this.service.refuse("oa_common", "nuby.zhang", "nuby.zhang", formId, null, "xxx");
		} catch (Exception e) {
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
