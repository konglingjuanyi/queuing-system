package com.qunar.ops.oaengine.stragety;

import java.util.HashMap;
import java.util.Map;

public class HistoryFactory {
	
	private final static String REQUEST = "vm_request";
	private final static String OTHER = "other";
	private final static String ALL = "all";
	private final static Map<String, HistoryInterface> hisMap;
	
	static{
		hisMap = new HashMap<String, HistoryInterface>();
		hisMap.put(REQUEST, new VmRequestHistoryImpl());
		hisMap.put(OTHER, new DefaultHistoryImpl());
		hisMap.put(ALL, new VmOtherRequestHistoryImpl());
	}
	
	public static HistoryInterface baseResult(String processKeys){
		if (processKeys.equals(REQUEST)) {
			return hisMap.get(REQUEST);
		}
		if (!processKeys.contains(REQUEST)) {
//			String keys = "";
//			for (String key : processKeys.split(",")) {
//				keys += "'" + key + "',";
//			}
			return hisMap.get(OTHER);
		}
		return hisMap.get(ALL);
	}
}
