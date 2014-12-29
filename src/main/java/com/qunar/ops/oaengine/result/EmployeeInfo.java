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
	//userMail
	private String userMail;
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
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
	private String manager;
	private String managerMail;
	//VP
	private String vp;
	private String vpMail;
	private int enable;
	private String company;
	
	public String getVp() {
		return vp;
	}
	public void setVp(String vp) {
		this.vp = vp;
	}
	public String getVpMail() {
		return vpMail;
	}
	public void setVpMail(String vpMail) {
		this.vpMail = vpMail;
	}
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
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getManagerMail() {
		return managerMail;
	}
	public void setManagerMail(String managerMail) {
		this.managerMail = managerMail;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
}
