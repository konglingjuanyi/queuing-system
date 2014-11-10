package com.qunar.ops.oaengine.result.dailysubmit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.qunar.ops.oaengine.model.Formson0115;

public class OvertimeMealsInfo extends Formson0115 implements Serializable{
	/**
	 * 加班餐费
	 */
	private static final long serialVersionUID = 8933545444370281313L;
	private Date overtimeMealsDate;
    private String overtimeMealsComment;
    private Long perMealsFee;
    private Long overtimeMealsAmount;
    private Long mealsPersonNum;
    private String overtimeMealsPeerPeople;
    private String mealsAddr;
    private String invoiceAmount;
    private BigDecimal overtimeMealsWorkhours;
    
	public Date getOvertimeMealsDate() {
		return super.getField0034();
	}
	public void setOvertimeMealsDate(Date overtimeMealsDate) {
		super.setField0034(overtimeMealsDate);
	}
	public String getOvertimeMealsComment() {
		return super.getField0035();
	}
	public void setOvertimeMealsComment(String overtimeMealsComment) {
		super.setField0035(overtimeMealsComment);
	}
	public Long getPerMealsFee() {
		return super.getField0036();
	}
	public void setPerMealsFee(Long perMealsFee) {
		super.setField0036(perMealsFee);
	}
	public Long getOvertimeMealsAmount() {
		return super.getField0037();
	}
	public void setOvertimeMealsAmount(Long overtimeMealsAmount) {
		super.setField0037(overtimeMealsAmount);
	}
	public Long getMealsPersonNum() {
		return super.getField0038();
	}
	public void setMealsPersonNum(Long mealsPersonNum) {
		super.setField0038(mealsPersonNum);
	}
	public String getOvertimeMealsPeerPeople() {
		return super.getField0039();
	}
	public void setOvertimeMealsPeerPeople(String overtimeMealsPeerPeople) {
		super.setField0039(overtimeMealsPeerPeople);
	}
	public String getMealsAddr() {
		return super.getField0040();
	}
	public void setMealsAddr(String mealsAddr) {
		super.setField0040(mealsAddr);
	}
	public String getInvoiceAmount() {
		return super.getField0077();
	}
	public void setInvoiceAmount(String invoiceAmount) {
		super.setField0077(invoiceAmount);
	}
	public BigDecimal getOvertimeMealsWorkhours() {
		return super.getField0094();
	}
	public void setOvertimeMealsWorkhours(BigDecimal overtimeMealsWorkhours) {
		super.setField0094(overtimeMealsWorkhours);
	}
}
