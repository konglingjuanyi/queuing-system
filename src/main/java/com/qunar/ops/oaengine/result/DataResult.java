package com.qunar.ops.oaengine.result;

import java.util.List;
import java.util.Map;

public class DataResult {
	private List<Map<String, String>> varsList;
	private List<Map<String, String[][]>> tableMapList;
	private List<String[]> tableInfos;
	private long count;

	public List<Map<String, String>> getVarsList() {
		return varsList;
	}

	public void setVarsList(List<Map<String, String>> varsList) {
		this.varsList = varsList;
	}

	public List<Map<String, String[][]>> getTableMapList() {
		return tableMapList;
	}

	public void setTableMapList(List<Map<String, String[][]>> tableMapList) {
		this.tableMapList = tableMapList;
	}

	public List<String[]> getTableInfos() {
		return tableInfos;
	}

	public void setTableInfos(List<String[]> tableInfos) {
		this.tableInfos = tableInfos;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
