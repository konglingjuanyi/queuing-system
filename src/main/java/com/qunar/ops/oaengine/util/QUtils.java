package com.qunar.ops.oaengine.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class QUtils {

	public static final int MAX_AGE_DEFAULT = 30 ;
	
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
	
	public static String getUsername(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies != null)for(Cookie cookie : cookies){
			if("un".equals(cookie.getName())){
				String un = cookie.getValue();
				if(un == null || un.length() == 0) return null;
				un = decrypt(un, "qunar-opsdev-1qaz2wsx-123456");
				return un;
			}
		}
		return null;
	}
	
	public static String getAdname(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies != null)for(Cookie cookie : cookies){
			if("name".equals(cookie.getName())){
				String name = cookie.getValue();
				if(name == null || name.length() == 0) return null;
				name = decrypt(name, "qunar-opsdev-1qaz2wsx-123456");
				try {
					return URLDecoder.decode(name, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static String getAdmin(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies != null)for(Cookie cookie : cookies){
			if("ADMIN_ID".equals(cookie.getName())){
				String name = cookie.getValue();
				if(name == null || name.length() == 0) return null;
				name = decrypt(name, "qunar-opsdev-1qaz2wsx-123456");
				try {
					return URLDecoder.decode(name, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	public static String setUsername(HttpServletResponse response, String name, String value, boolean encrypt){
		if(value != null){
			try {
				value = URLEncoder.encode(value, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(encrypt){
				value = encrypt(value, "qunar-opsdev-1qaz2wsx-123456");
			}
			Cookie cookie = new Cookie(name, value);
			cookie.setMaxAge(MAX_AGE_DEFAULT);
			response.addCookie(cookie);
		}else{
			Cookie cookie = new Cookie(name, null);
			cookie.setMaxAge(0);
			cookie.setPath("/"); 
			response.addCookie(cookie);
		}
		return value;
	}
	
	public static String encrypt(String src, String key)   {  
		//return src;
		try{
		    DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));  
		    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");  
		    SecretKey securekey = keyFactory.generateSecret(dks);  
		    Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");  
		    cipher.init(Cipher.ENCRYPT_MODE, securekey);  
		    byte[] b=cipher.doFinal(src.getBytes("UTF-8"));  
		    BASE64Encoder encoder = new BASE64Encoder();  
		    return encoder.encode(b).replaceAll("\r", "").replaceAll("\n", "");  
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static String decrypt(String src,String key) {  
		//return src;
		try{
		    //--通过base64,将字符串转成byte数组  
		    BASE64Decoder decoder = new BASE64Decoder();  
		    byte[] bytesrc = decoder.decodeBuffer(src);  
		    //--解密的key  
		    DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));  
		    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");  
		    SecretKey securekey = keyFactory.generateSecret(dks);  
		      
		    //--Chipher对象解密  
		    Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");  
		    cipher.init(Cipher.DECRYPT_MODE, securekey);  
		    byte[] retByte = cipher.doFinal(bytesrc);  
		      
		    return new String(retByte);  
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}
}
