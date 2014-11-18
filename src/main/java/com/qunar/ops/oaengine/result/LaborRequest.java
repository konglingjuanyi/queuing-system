package com.qunar.ops.oaengine.result;

import java.io.Serializable;


public class LaborRequest implements Serializable{
	private static final long serialVersionUID = -7524220057917608279L;
	private String whichDay;
	public String getWhichDay() {
		return whichDay;
	}
	public void setWhichDay(String whichDay) {
		this.whichDay = whichDay;
	}
	
}
