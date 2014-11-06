package com.qunar.ops.oaengine.result;

import java.io.Serializable;


public class Request implements Serializable{
	private static final long serialVersionUID = 3196624015494364529L;
	private String oid;
	private int amountMoney;
	private int tbMoney;
	private boolean report2vp;
	private String department;
	public int getAmountMoney() {
		return amountMoney;
	}
	public void setAmountMoney(int amountMoney) {
		this.amountMoney = amountMoney;
	}
	public int getTbMoney() {
		return tbMoney;
	}
	public void setTbMoney(int tbMoney) {
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

}
