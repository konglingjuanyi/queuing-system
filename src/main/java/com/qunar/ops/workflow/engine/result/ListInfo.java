package com.qunar.ops.workflow.engine.result;

import java.util.List;

public class ListInfo {
	
	private int pageNo;
	private int pageSize;
	private long count;
	private List<?> list;
	
	public ListInfo(int pageNo, int pageSize, long count, List<?>list) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.list = list;
		this.count = count;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<ProcessInstanceInfo> list) {
		this.list = list;
	}
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
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
}
