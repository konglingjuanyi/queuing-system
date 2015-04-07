package com.qunar.ops.oaengine.result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.qunar.ops.oaengine.model.Files;

/**
 * 获取每个表单的具体信息.
 * vars包含每项费用,key-value形式.
 * tableMap key为每个table的id,value为table二维数组.
 *
 */
public class FormRequest implements Serializable{

	private static final long serialVersionUID = 356235207021432802L;
	private Map<String, String[][]> tableMap;
	private Map<String, String> vars;
	private List<Files> files;
	private boolean flag;
	public Map<String, String[][]> getTableMap() {
		return tableMap;
	}
	public void setTableMap(Map<String, String[][]> tableMap) {
		this.tableMap = tableMap;
	}
	public Map<String, String> getVars() {
		return vars;
	}
	public void setVars(Map<String, String> vars) {
		this.vars = vars;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public List<Files> getFiles() {
		return files;
	}
	public void setFiles(List<Files> files) {
		this.files = files;
	}
	
}
