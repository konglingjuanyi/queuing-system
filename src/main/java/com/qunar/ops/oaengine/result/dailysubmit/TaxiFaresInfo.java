package com.qunar.ops.oaengine.result.dailysubmit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.qunar.ops.oaengine.model.Formson0119;

public class TaxiFaresInfo extends Formson0119 implements Serializable {
	/**
	 * 出租车费
	 */
	private static final long serialVersionUID = 8933545444370281313L;

	// 加班餐费工时
	private String taxiFaresPeerPeople;
	// 出租车费金额
	private Long taxiFaresAmount;
	// 出租车费用途
	private String taxiFaresUse;
	// 出租车费具体时间
	private String taxiFaresTime;
	// 终出租车费终点
	private String taxiFaresDest;
	// 出租车费地点
	private String taxiFaresAddr;
	// 出租车费日期
	private Date taxiFaresDate;
	// 出租车费具体时间新
	private String taxiFaresTimeNew;
	// 出租车费工时
	private BigDecimal taxiFaresWorkhour;
	// 备注
	private String comment;
	
	private Long ratify;
	public Long getRatify(){
		return super.getRatify();
	}
	public void setRatify(Long v){
		super.setRatify(v);
	}

	public String getTaxiFaresPeerPeople() {
		return super.getField0056();
	}

	public void setTaxiFaresPeerPeople(String taxiFaresPeerPeople) {
		super.setField0056(taxiFaresPeerPeople);
		this.taxiFaresPeerPeople = taxiFaresPeerPeople;
	}

	public Long getTaxiFaresAmount() {
		return super.getField0057()==null?0:super.getField0057();
	}

	public void setTaxiFaresAmount(Long taxiFaresAmount) {
		super.setField0057(taxiFaresAmount);
		this.taxiFaresAmount = taxiFaresAmount;
	}

	public String getTaxiFaresUse() {
		return super.getField0058();
	}

	public void setTaxiFaresUse(String taxiFaresUse) {
		super.setField0058(taxiFaresUse);
		this.taxiFaresUse = taxiFaresUse;
	}

	public String getTaxiFaresTime() {
		return super.getField0059();
	}

	public void setTaxiFaresTime(String taxiFaresTime) {
		super.setField0059(taxiFaresTime);
		this.taxiFaresTime = taxiFaresTime;
	}

	public String getTaxiFaresDest() {
		return super.getField0060();
	}

	public void setTaxiFaresDest(String taxiFaresDest) {
		super.setField0060(taxiFaresDest);
		this.taxiFaresDest = taxiFaresDest;
	}

	public String getTaxiFaresAddr() {
		return super.getField0061();
	}

	public void setTaxiFaresAddr(String taxiFaresAddr) {
		super.setField0061(taxiFaresAddr);
		this.taxiFaresAddr = taxiFaresAddr;
	}

	public Date getTaxiFaresDate() {
		return super.getField0062();
	}

	public void setTaxiFaresDate(Date taxiFaresDate) {
		super.setField0062(taxiFaresDate);
		this.taxiFaresDate = taxiFaresDate;
	}

	public String getTaxiFaresTimeNew() {
		return super.getField0088();
	}

	public void setTaxiFaresTimeNew(String taxiFaresTimeNew) {
		super.setField0088(taxiFaresTimeNew);
		this.taxiFaresTimeNew = taxiFaresTimeNew;
	}

	public BigDecimal getTaxiFaresWorkhour() {
		return super.getField0095();
	}

	public void setTaxiFaresWorkhour(BigDecimal taxiFaresWorkhour) {
		super.setField0095(taxiFaresWorkhour);
		this.taxiFaresWorkhour = taxiFaresWorkhour;
	}

	public String getComment() {
		return super.getField0098();
	}

	public void setComment(String comment) {
		super.setField0098(comment);
		this.comment = comment;
	}
}
