package com.qunar.ops.oaengine.result;

public class ApproveRequest {
	private String key;	//process key
	private String type; //s-单条审批, m-允许批量审批
	private String url;	//应用url
	private String name; //应用名称
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
