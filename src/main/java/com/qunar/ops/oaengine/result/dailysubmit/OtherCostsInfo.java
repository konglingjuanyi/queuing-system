package com.qunar.ops.oaengine.result.dailysubmit;

import java.io.Serializable;

import com.qunar.ops.oaengine.model.Formson0117;

public class OtherCostsInfo extends Formson0117 implements Serializable {
	/**
	 * 其他费用
	 */
	private static final long serialVersionUID = 8933545444370281313L;

	// 其他费用备注
	private String otherCostComment;
	// 其他费用金额
	private Long otherCostAmount;
	// 其他费用项目
	private String otherCostProject;
	private Long ratify;
	public Long getRatify(){
		return super.getRatify();
	}
	public void setRatify(Long v){
		super.setRatify(v);
	}
	public String getOtherCostComment() {
		return super.getField0048();
	}

	public void setOtherCostComment(String otherCostComment) {
		super.setField0048(otherCostComment);
		this.otherCostComment = otherCostComment;
	}

	public Long getOtherCostAmount() {
		return super.getField0049()==null?0:super.getField0049();
	}

	public void setOtherCostAmount(Long otherCostAmount) {
		super.setField0049(otherCostAmount);
		this.otherCostAmount = otherCostAmount;
	}

	public String getOtherCostProject() {
		return super.getField0050();
	}

	public void setOtherCostProject(String otherCostProject) {
		super.setField0050(otherCostProject);
		this.otherCostProject = otherCostProject;
	}

}
