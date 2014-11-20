package com.qunar.ops.oaengine.result;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * 我的申请页面数据
 * @author zhenqing.wang
 *
 */
public class WebRequest implements Serializable{

	/**
	 * list包含每个表格的多条数据
	 * vars主要是页面上唯一的数据
	 */
	private static final long serialVersionUID = 356235207021432802L;
	private Map<String, String[]> tableMap;
	private Map<String, String> vars;
	public Map<String, String[]> getTableMap() {
		return tableMap;
	}
	public void setTableMap(Map<String, String[]> tableMap) {
		this.tableMap = tableMap;
	}
	public Map<String, String> getVars() {
		return vars;
	}
	public void setVars(Map<String, String> vars) {
		this.vars = vars;
	}
	
}
