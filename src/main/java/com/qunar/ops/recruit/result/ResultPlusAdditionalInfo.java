package com.qunar.ops.recruit.result;

import java.util.LinkedList;
import java.util.List;

/**
 * 各个表格信息获取
 */
public class ResultPlusAdditionalInfo {

    Object obj;
    List<String> additionalInfos = new LinkedList<String>();
    
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public List<String> getAdditionalInfos() {
		return additionalInfos;
	}
	public void setAdditionalInfos(List<String> additionalInfos) {
		this.additionalInfos = additionalInfos;
	}
	public void addStringInfo(String str) {
		additionalInfos.add(str);
		
	}
    
    
}
