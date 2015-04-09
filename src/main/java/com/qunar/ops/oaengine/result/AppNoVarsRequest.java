package com.qunar.ops.oaengine.result;


/**
 * 传递一些参数,但不是必须的.
 */
public class AppNoVarsRequest{
	private String system;
	private String rtx_id;

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getRtx_id() {
		return rtx_id;
	}

	public void setRtx_id(String rtx_id) {
		this.rtx_id = rtx_id;
	}

}
