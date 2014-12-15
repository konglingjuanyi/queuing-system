package com.qunar.ops.oaengine.result;

import java.io.Serializable;
import java.util.Map;

/**
 * 传递一些参数,但不是必须的.
 */
public class CommonRequest implements Serializable{
	private Map<String, String> vars;

	public Map<String, String> getVars() {
		return vars;
	}

	public void setVars(Map<String, String> vars) {
		this.vars = vars;
	}
}
