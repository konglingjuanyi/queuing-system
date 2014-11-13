package com.qunar.ops.oaengine.util;

public interface Constants {
	//processKey
	public static final String DAILYSUBMIT = "日常报销";
	
	//finishedFlag
	public static final int PROCESSING = 0;
	public static final int PROC_END = 1;
	public static final int TERMINATION = 3;
	public static final int PROC_GRIFT = 5;
	
	
	//return result
	public static final int SUCCESS = 0;
	public static final int FIELD = 1;
	public static final int EXCEPTION = 2;
}
