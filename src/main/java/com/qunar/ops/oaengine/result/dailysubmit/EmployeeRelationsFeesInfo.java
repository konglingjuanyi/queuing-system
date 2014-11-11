package com.qunar.ops.oaengine.result.dailysubmit;

import java.io.Serializable;
import java.util.Date;

import com.qunar.ops.oaengine.model.Formson0118;

public class EmployeeRelationsFeesInfo extends Formson0118 implements Serializable{
	/**
	 * 员工关系费
	 */
	private static final long serialVersionUID = 8933545444370281313L;
    private Long emRelationsFees;
    private String emRelationsAddress;
    private Date emRelationsDate;
    private String actDest;
    private String emRelationsPeerPeople;
    private String emRelationsFeesComment;
    
	public Long getEmRelationsFees() {
		return super.getField0051();
	}
	public void setEmRelationsFees(Long emRelationsFees) {
		super.setField0051(emRelationsFees);
	}
	public String getEmRelationsAddress() {
		return super.getField0052();
	}
	public void setEmRelationsAddress(String emRelationsAddress) {
		super.setField0052(emRelationsAddress);
	}
	public Date getEmRelationsDate() {
		return super.getField0053();
	}
	public void setEmRelationsDate(Date emRelationsDate) {
		super.setField0053(emRelationsDate);
	}
	public String getActDest() {
		return super.getField0054();
	}
	public void setActDest(String actDest) {
		super.setField0054(actDest);
	}
	public String getEmRelationsPeerPeople() {
		return super.getField0055();
	}
	public void setEmRelationsPeerPeople(String emRelationsPeerPeople) {
		super.setField0055(emRelationsPeerPeople);
	}
	public String getEmRelationsFeesComment() {
		return super.getField0089();
	}
	public void setEmRelationsFeesComment(String emRelationsFeesComment) {
		super.setField0089(emRelationsFeesComment);
	}
}