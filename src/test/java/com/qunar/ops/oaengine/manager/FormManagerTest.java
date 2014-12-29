package com.qunar.ops.oaengine.manager;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qunar.ops.oaengine.exception.CompareModelException;
import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.result.dailysubmit.EmployeeRelationsFeesInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.HospitalityInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OtherCostsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OvertimeMealsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.TaxiFaresInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class FormManagerTest {
	
	@Autowired
	private Form0114Manager form0114manager;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private Long formId;
	
	@Before
	public void init_data() throws FormNotFoundException{
		FormInfo info = initFormInfo();
		Long i = form0114manager.createFormInfo("yongnian.jiang", info);
		
		FormInfo formInfo = form0114manager.getFormInfo(1l);
		formId = formInfo.getId();
	}
	
	@After
	public void destory_data() throws FormNotFoundException{
		form0114manager.deleteFormInfo("yongnian.jiang", formId, 0);
	}
	
	@Test
	public void form_search_test() throws FormNotFoundException{
		FormInfo formInfo = form0114manager.getFormInfo(1l);
		Assert.assertEquals("姜永念测试", formInfo.getProcTitle());
		Assert.assertEquals(formId, formInfo.getId());
		
		exception.expect(FormNotFoundException.class);
		formInfo = form0114manager.getFormInfo(1l);
	}
	
	@Test
	public void form_update_test() throws FormNotFoundException, CompareModelException{
		FormInfo formInfo = form0114manager.getFormInfo(1l);
		Assert.assertEquals("source", formInfo.getAddress());
		
		formInfo.setAddress("update_address");
		form0114manager.updateFormInfo("yongnian.jiang", formId, formInfo);
		
		formInfo = form0114manager.getFormInfo(1l);
		Assert.assertEquals("update_address", formInfo.getAddress());
		
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
