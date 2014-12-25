package com.qunar.ops.oaengine.result.dailysubmit;

import java.io.Serializable;
import java.util.Date;

import com.qunar.ops.oaengine.model.Formson0116;

public class HospitalityInfo extends Formson0116 implements Serializable {
	/**
	 * 招待费
	 */
	private static final long serialVersionUID = 8933545444370281313L;

	// 招待费日期
	private Date hospitalityDate;
	// 招待费地点
	private String hospitalityAddr;
	// 招待费金额
	private Long hospitalityAmount;
	// 客户单位
	private String customCompany;
	// 业务目的
	private String businessPurpose;
	// 招待费参加人数
	private String hospitalityNum;
	// 客户姓名
	private String customName;
	
	private String memo;
	private Long ratify;
	public Long getRatify(){
		return super.getRatify();
	}
	public void setRatify(Long v){
		super.setRatify(v);
	}
	public Date getHospitalityDate() {
		return super.getField0041();
	}

	public void setHospitalityDate(Date hospitalityDate) {
		super.setField0041(hospitalityDate);
		this.hospitalityDate = hospitalityDate;
	}

	public String getHospitalityAddr() {
		return super.getField0042();
	}

	public void setHospitalityAddr(String hospitalityAddr) {
		super.setField0042(hospitalityAddr);
		this.hospitalityAddr = hospitalityAddr;
	}

	public Long getHospitalityAmount() {
		return super.getField0043()==null?0:super.getField0043();
	}

	public void setHospitalityAmount(Long hospitalityAmount) {
		super.setField0043(hospitalityAmount);
		this.hospitalityAmount = hospitalityAmount;
	}

	public String getCustomCompany() {
		return super.getField0044();
	}

	public void setCustomCompany(String customCompany) {
		super.setField0044(customCompany);
		this.customCompany = customCompany;
	}

	public String getBusinessPurpose() {
		return super.getField0045();
	}

	public void setBusinessPurpose(String businessPurpose) {
		super.setField0045(businessPurpose);
		this.businessPurpose = businessPurpose;
	}

	public String getHospitalityNum() {
		return super.getField0046();
	}

	public void setHospitalityNum(String hospitalityNum) {
		super.setField0046(hospitalityNum);
		this.hospitalityNum = hospitalityNum;
	}

	public String getCustomName() {
		return super.getField0047();
	}

	public void setCustomName(String customName) {
		super.setField0047(customName);
		this.customName = customName;
	}
	
	public String getMemo() {
		return super.getMemo();
	}

	public void setMemo(String memo) {
		super.setMemo(memo);
		this.memo = memo;
	}
}
