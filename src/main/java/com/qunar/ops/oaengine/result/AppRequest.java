package com.qunar.ops.oaengine.result;


/**
 * 传递一些参数,但不是必须的.
 */
public class AppRequest extends CommonRequest{
	private static final long serialVersionUID = -1740742078764095210L;
	private String system;

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

}
