package com.qunar.ops.oaengine.result;

import java.util.ArrayList;
import java.util.List;

import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;

public class DataResult {
	private List<String[]> aaData = new ArrayList<String[]>();
	private List<FormInfo> formInfos = new ArrayList<FormInfo>();
	private Long count;
	public List<String[]> getAaData() {
		return aaData;
	}
	public void setAaData(List<String[]> aaData) {
		this.aaData = aaData;
	}
	public List<FormInfo> getFormInfos() {
		return formInfos;
	}
	public void setFormInfos(List<FormInfo> formInfos) {
		this.formInfos = formInfos;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
}
