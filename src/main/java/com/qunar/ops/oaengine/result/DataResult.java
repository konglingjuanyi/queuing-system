package com.qunar.ops.oaengine.result;

import java.util.ArrayList;
import java.util.List;

public class DataResult {
	private List<String[]> aaData = new ArrayList<String[]>();
	private long count = 0;

	public List<String[]> getAaData() {
		return aaData;
	}

	public void setAaData(List<String[]> aaData) {
		this.aaData = aaData;
	}

	public void append(String[] info) {
		this.aaData.add(info);
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
