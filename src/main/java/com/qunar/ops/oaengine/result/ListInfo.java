package com.qunar.ops.oaengine.result;

import java.util.ArrayList;
import java.util.List;

public class ListInfo<T> {
	private int pageNo;
	private int pageSize;
	private long count;
	private List<T> infos;
	
	public ListInfo() {
		this.infos = new ArrayList<T>();
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
	public List<T> getInfos() {
		return infos;
	}
	public void setInfos(List<T> infos) {
		this.infos = infos;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}

}
