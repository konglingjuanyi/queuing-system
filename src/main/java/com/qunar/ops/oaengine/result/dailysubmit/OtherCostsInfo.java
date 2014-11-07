package com.qunar.ops.oaengine.result.dailysubmit;

import java.io.Serializable;

import com.qunar.ops.oaengine.model.Formson0117;

public class OtherCostsInfo extends Formson0117 implements Serializable{
	/**
	 * 其他费用
	 */
	private static final long serialVersionUID = 8933545444370281313L;
    private String otherCostComment;
    private Long otherCostAmount;
    private String otherCostProject;
    
	public String getOtherCostComment() {
		return super.getField0048();
	}
	public void setOtherCostComment(String otherCostComment) {
		super.setField0048(otherCostComment);
	}
	public Long getOtherCostAmount() {
		return super.getField0049();
	}
	public void setOtherCostAmount(Long otherCostAmount) {
		super.setField0049(otherCostAmount);
	}
	public String getOtherCostProject() {
		return super.getField0050();
	}
	public void setOtherCostProject(String otherCostProject) {
		super.setField0050(otherCostProject);
	}
    
}
