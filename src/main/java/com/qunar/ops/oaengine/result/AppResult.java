package com.qunar.ops.oaengine.result;

public class AppResult {
	private int errorcode;
	private String msg;
	private Object data;
	private int sum;
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public static AppResult getErrorResult(int errorCode){
		AppResult res = new AppResult();
		res.setErrorcode(errorCode);
		res.setMsg("");
		return res;
	}
	
	public static AppResult getErrorResult(int errorCode, String errorMsg){
		AppResult res = new AppResult();
		res.setErrorcode(errorCode);
		res.setMsg(errorMsg);
		return res;
	}
	
	public static AppResult getSuccessResult(Object data){
		AppResult res = new AppResult();
		res.setErrorcode(0);
		res.setMsg("");
		res.setData(data);
		return res;
	}

}
