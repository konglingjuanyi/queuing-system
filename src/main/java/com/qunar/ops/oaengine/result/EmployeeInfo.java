package com.qunar.ops.oaengine.result;

import java.io.Serializable;

public class EmployeeInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	//rtxID
	private String userId;
	//AD姓名
	private String adName;
	//员工编号
	private String sn;
	//一级部门
	private String departmentI;
	private String departmentII;
	private String departmentIII;
	private String departmentIV;
	private String departmentV;
	//银行卡号
	private String bankCardNo;
	//开户行
	private String bankName;
	//直接主管
	private String[] manager;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getDepartmentI() {
		return departmentI;
	}
	public void setDepartmentI(String departmentI) {
		this.departmentI = departmentI;
	}
	public String getDepartmentII() {
		return departmentII;
	}
	public void setDepartmentII(String departmentII) {
		this.departmentII = departmentII;
	}
	public String getDepartmentIII() {
		return departmentIII;
	}
	public void setDepartmentIII(String departmentIII) {
		this.departmentIII = departmentIII;
	}
	public String getDepartmentIV() {
		return departmentIV;
	}
	public void setDepartmentIV(String departmentIV) {
		this.departmentIV = departmentIV;
	}
	public String getDepartmentV() {
		return departmentV;
	}
	public void setDepartmentV(String departmentV) {
		this.departmentV = departmentV;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String[] getManager() {
		return manager;
	}
	public void setManager(String[] manager) {
		this.manager = manager;
	}
	
}
