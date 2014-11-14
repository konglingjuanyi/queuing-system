package com.qunar.ops.oaengine.result.dailysubmit;

import java.util.List;

import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;

public class FormInfoList {
	private int pageNo;
	private int pageSize;
	private int count;
	private List<FormInfo> formInfos;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<FormInfo> getFormInfos() {
		return formInfos;
	}
	public void setFormInfos(List<FormInfo> formInfos) {
		this.formInfos = formInfos;
	}
	
	

}
