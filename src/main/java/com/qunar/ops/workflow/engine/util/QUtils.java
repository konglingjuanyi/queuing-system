package com.qunar.ops.workflow.engine.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class QUtils {

	public static Map<String, Object> request2Map(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (request == null)
			return map;
		Map<String, Object> vars = request.getParameterMap();
		for (Entry<String, Object> e : vars.entrySet()) {
			String key = e.getKey();
			Object value = e.getValue();
			if (value == null)
				continue;
			if (value.getClass().isArray() && Array.getLength(value) > 0) {
				map.put(key, Array.get(value, 0));
			} else {
				map.put(key, value);
			}
		}
		return map;
	}

	public static List<String> getUsers(Object value) {
		List<String> users = new ArrayList<String>();
		if (value == null)
			return users;
		if (value instanceof ArrayList) {
			for (String v : (ArrayList<String>) value) {
				users.add((String) v);
			}
		} else {
			users.add((String) value);
		}
		return users;
	}

	public static final String full2HalfChange(String QJstr) {
		StringBuffer outStrBuf = new StringBuffer("");
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < QJstr.length(); i++) {
			Tstr = QJstr.substring(i, i + 1);
			if ((" ").equals(Tstr)){
				outStrBuf.append(" ");
				continue;
			}
			try {
				b = Tstr.getBytes("unicode");
				if (b[2] == -1) {
					// 表示全角
					b[3] = (byte) (b[3] + 32);
					b[2] = 0;
					outStrBuf.append(new String(b, "unicode"));
				} else {
					outStrBuf.append(Tstr);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		return outStrBuf.toString();

	}

}
