package com.qunar.ops.oaengine.manager;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class FormManagerTest {
	
	@Autowired
	private Form0114Manager form0114manager;
	
	private FormInfo initFormInfo(){
		FormInfo formInfo = new FormInfo();
		formInfo.setId(-1l);
		formInfo.setOid("jyn_test");
		formInfo.setProcInstId("jyn_test");
		formInfo.setProcTitle("姜永念测试");
		formInfo.setState(1);
		//需要改表结构成String，存rtx_id
		formInfo.setStartMemberId("");
		formInfo.setStartDate(new Date());
		//需要改表结构成String，存rtx_id
		formInfo.setApproveMemberId("");
		formInfo.setApproveDate(new Date());
		formInfo.setFinishedflag(0);
		formInfo.setRatifyflag(0);
		//需要改表结构成String，存rtx_id
		formInfo.setRatifyMemberId("");
		formInfo.setRatifyDate(new Date());
		formInfo.setFirstDep("技术部");
		formInfo.setSecDep("测试");
		formInfo.setThridDep("");
		//查看表结构长度是否为20
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
		formInfo.setAddress("");
		formInfo.setIsDirectVp("");
		formInfo.setBankName("");
		return formInfo;
	}
	
	@Test
	public void appendMemberTest(){
//		Assert.assertEquals(info.getGroupKey(), "tb_check");
	}

}
