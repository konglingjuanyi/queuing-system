package com.qunar.ops.oaengine.result.dailysubmit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.qunar.ops.oaengine.model.Formson0119;

public class TaxiFaresInfo extends Formson0119 implements Serializable{
	/**
	 * 出租车费
	 */
	private static final long serialVersionUID = 8933545444370281313L;
//	private String taxiFaresPeerPeople;
//    private Long taxiFaresAmount;
//    private String taxiFaresUse;
//    private String taxiFaresTime;
//    private String taxiFaresDest;
//    private String taxiFaresAddr;
//    private Date taxiFaresDate;
//    private String taxiFaresTimeNew;
//    private BigDecimal taxiFaresWorkhour;
//    private String comment;
    
	public String getTaxiFaresPeerPeople() {
		return super.getField0056();
	}
	public void setTaxiFaresPeerPeople(String taxiFaresPeerPeople) {
		super.setField0056(taxiFaresPeerPeople);
	}
	public Long getTaxiFaresAmount() {
		return super.getField0057();
	}
	public void setTaxiFaresAmount(Long taxiFaresAmount) {
		super.setField0057(taxiFaresAmount);
	}
	public String getTaxiFaresUse() {
		return super.getField0058();
	}
	public void setTaxiFaresUse(String taxiFaresUse) {
		super.setField0058(taxiFaresUse);
	}
	public String getTaxiFaresTime() {
		return super.getField0059();
	}
	public void setTaxiFaresTime(String taxiFaresTime) {
		super.setField0059(taxiFaresTime);
	}
	public String getTaxiFaresDest() {
		return super.getField0060();
	}
	public void setTaxiFaresDest(String taxiFaresDest) {
		super.setField0060(taxiFaresDest);
	}
	public String getTaxiFaresAddr() {
		return super.getField0061();
	}
	public void setTaxiFaresAddr(String taxiFaresAddr) {
		super.setField0061(taxiFaresAddr);
	}
	public Date getTaxiFaresDate() {
		return super.getField0062();
	}
	public void setTaxiFaresDate(Date taxiFaresDate) {
		super.setField0062(taxiFaresDate);
	}
	public String getTaxiFaresTimeNew() {
		return super.getField0088();
	}
	public void setTaxiFaresTimeNew(String taxiFaresTimeNew) {
		super.setField0088(taxiFaresTimeNew);
	}
	public BigDecimal getTaxiFaresWorkhour() {
		return super.getField0095();
	}
	public void setTaxiFaresWorkhour(BigDecimal taxiFaresWorkhour) {
		super.setField0095(taxiFaresWorkhour);
	}
	public String getComment() {
		return super.getField0098();
	}
	public void setComment(String comment) {
		super.setField0098(comment);
	}
}
