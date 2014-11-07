package com.qunar.ops.oaengine.result;

import java.util.List;

public class FormInfoList {
	private int pageNo;
	private int pageSize;
	private int pageCount;
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
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<FormInfo> getFormInfos() {
		return formInfos;
	}
	public void setFormInfos(List<FormInfo> formInfos) {
		this.formInfos = formInfos;
	}
	
	

}
