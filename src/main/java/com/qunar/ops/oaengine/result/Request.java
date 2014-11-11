package com.qunar.ops.oaengine.result;

import java.io.Serializable;


public class Request implements Serializable{
	private static final long serialVersionUID = 3196624015494364529L;
	private String oid;
	private Long amountMoney;
	private Long tbMoney;
	private boolean report2vp;
	private String department;
	private String departmentII;
	private String departmentIII;
	private String departmentIV;
	private String departmentV;
	public Long getAmountMoney() {
		return amountMoney;
	}
	public void setAmountMoney(Long amountMoney) {
		this.amountMoney = amountMoney;
	}
	public Long getTbMoney() {
		return tbMoney;
	}
	public void setTbMoney(Long tbMoney) {
		this.tbMoney = tbMoney;
	}
	public boolean isReport2vp() {
		return report2vp;
	}
	public void setReport2vp(boolean report2vp) {
		this.report2vp = report2vp;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
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

}
