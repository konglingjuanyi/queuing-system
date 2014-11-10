package com.qunar.ops.oaengine.result.dailysubmit;

import java.io.Serializable;
import java.util.Date;

import com.qunar.ops.oaengine.model.Formson0116;

public class HospitalityInfo extends Formson0116 implements Serializable{
	/**
	 * 招待费
	 */
	private static final long serialVersionUID = 8933545444370281313L;
    private Date hospitalityDate;
    private String hospitalityAddr;
    private Long hospitalityAmount;
    private String customCompany;
    private String businessPurpose;
    private String hospitalityNum;
    private String customName;
    
	public Date getHospitalityDate() {
		return super.getField0041();
	}
	public void setHospitalityDate(Date hospitalityDate) {
		super.setField0041(hospitalityDate);
	}
	public String getHospitalityAddr() {
		return super.getField0042();
	}
	public void setHospitalityAddr(String hospitalityAddr) {
		super.setField0042(hospitalityAddr);
	}
	public Long getHospitalityAmount() {
		return super.getField0043();
	}
	public void setHospitalityAmount(Long hospitalityAmount) {
		super.setField0043(hospitalityAmount);
	}
	public String getCustomCompany() {
		return super.getField0044();
	}
	public void setCustomCompany(String customCompany) {
		super.setField0044(customCompany);
	}
	public String getBusinessPurpose() {
		return super.getField0045();
	}
	public void setBusinessPurpose(String businessPurpose) {
		super.setField0045(businessPurpose);
	}
	public String getHospitalityNum() {
		return super.getField0046();
	}
	public void setHospitalityNum(String hospitalityNum) {
		super.setField0046(hospitalityNum);
	}
	public String getCustomName() {
		return super.getField0047();
	}
	public void setCustomName(String customName) {
		super.setField0047(customName);
	}
}
