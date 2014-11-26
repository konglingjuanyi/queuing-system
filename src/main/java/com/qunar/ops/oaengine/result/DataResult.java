package com.qunar.ops.oaengine.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;

public class DataResult {
	private List<Map<String, String>> varList;
	private List<Map<String, String[][]>> tableMapList;
	private long count;
	public List<Map<String, String>> getVarList() {
		return varList;
	}
	public void setVarList(List<Map<String, String>> varList) {
		this.varList = varList;
	}
	public List<Map<String, String[][]>> getTableMapList() {
		return tableMapList;
	}
	public void setTableMapList(List<Map<String, String[][]>> tableMapList) {
		this.tableMapList = tableMapList;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
}
