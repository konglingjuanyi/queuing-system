package com.qunar.ops.oaengine.result.dailysubmit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.qunar.ops.oaengine.model.Formson0115;

public class OvertimeMealsInfo extends Formson0115 implements Serializable {
	/**
	 * 加班餐费
	 */
	private static final long serialVersionUID = 8933545444370281313L;

	// 加班餐费日期
	private Date overtimeMealsDate;
	// 加班餐费备注
	private String overtimeMealsComment;
	// 人均餐标
	private Long perMealsFee;
	// 加班餐费金额
	private Long overtimeMealsAmount;
	// 就餐人数
	private Long mealsPersonNum;
	// 加班餐费同行人
	private String overtimeMealsPeerPeople;
	// 就餐地点
	private String mealsAddr;
	// 发票金额
	private String invoiceAmount;
	// 加班餐费工时
	private BigDecimal overtimeMealsWorkhours;
	private Long ratify;
	public Long getRatify(){
		return super.getRatify();
	}
	public void setRatify(Long v){
		super.setRatify(v);
	}
	public Date getOvertimeMealsDate() {
		return super.getField0034();
	}

	public void setOvertimeMealsDate(Date overtimeMealsDate) {
		super.setField0034(overtimeMealsDate);
		this.overtimeMealsDate = overtimeMealsDate;
	}

	public String getOvertimeMealsComment() {
		return super.getField0035();
	}

	public void setOvertimeMealsComment(String overtimeMealsComment) {
		super.setField0035(overtimeMealsComment);
		this.overtimeMealsComment = overtimeMealsComment;
	}

	public Long getPerMealsFee() {
		return super.getField0036();
	}

	public void setPerMealsFee(Long perMealsFee) {
		super.setField0036(perMealsFee);
		this.perMealsFee = perMealsFee;
	}

	public Long getOvertimeMealsAmount() {
		return super.getField0037()==null?0:super.getField0037();
	}

	public void setOvertimeMealsAmount(Long overtimeMealsAmount) {
		super.setField0037(overtimeMealsAmount);
		this.overtimeMealsAmount = overtimeMealsAmount;
	}

	public Long getMealsPersonNum() {
		return super.getField0038();
	}

	public void setMealsPersonNum(Long mealsPersonNum) {
		super.setField0038(mealsPersonNum);
		this.mealsPersonNum = mealsPersonNum;
	}

	public String getOvertimeMealsPeerPeople() {
		return super.getField0039();
	}

	public void setOvertimeMealsPeerPeople(String overtimeMealsPeerPeople) {
		super.setField0039(overtimeMealsPeerPeople);
		this.overtimeMealsPeerPeople = overtimeMealsPeerPeople;
	}

	public String getMealsAddr() {
		return super.getField0040();
	}

	public void setMealsAddr(String mealsAddr) {
		super.setField0040(mealsAddr);
		this.mealsAddr = mealsAddr;
	}

	public String getInvoiceAmount() {
		return super.getField0077();
	}

	public void setInvoiceAmount(String invoiceAmount) {
		super.setField0077(invoiceAmount);
		this.invoiceAmount = invoiceAmount;
	}

	public BigDecimal getOvertimeMealsWorkhours() {
		return super.getField0094();
	}

	public void setOvertimeMealsWorkhours(BigDecimal overtimeMealsWorkhours) {
		super.setField0094(overtimeMealsWorkhours);
		this.overtimeMealsWorkhours = overtimeMealsWorkhours;
	}
	private Integer sort;
	public Integer getSort() {
		return super.getSort();
	}
	public void setSort(Integer sort) {
		super.setSort(sort);
		this.sort = sort;
	}
}
