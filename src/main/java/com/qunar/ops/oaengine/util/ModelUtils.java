package com.qunar.ops.oaengine.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qunar.ops.oaengine.exception.CompareModelException;
import com.qunar.ops.oaengine.model.Formmain0114;


public class ModelUtils {
	public static void main(String[] args) throws ClassNotFoundException, CompareModelException {
		Formmain0114 formmain0114New = new Formmain0114();
		formmain0114New.setId(123l);
		formmain0114New.setField0001("1234");
		formmain0114New.setApproveDate(new Date(123));
		Formmain0114 formmain0114Old = new Formmain0114();
		formmain0114Old.setId(1234l);
		formmain0114Old.setApproveDate(new Date());
		formmain0114Old.setField0001("1234");
		List<Map<String, Object>> res = ModelUtils.compareModel(Formmain0114.class, formmain0114New, formmain0114Old);
		for(int i = 0; i < res.size(); i++){
			System.out.println(res.get(i).get("field") + "===" + res.get(i).get("oldValue") + "===" + res.get(i).get("newValue"));
		}
	}
	
	public static <T> List<Map<String, Object>> compareModel(Class<?> c, T newObject, T oldObject) throws CompareModelException {
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		try{
			Map<String, Object> map;
			
			
			Field[] fields = c.getDeclaredFields();
			for(int i = 0; i < fields.length; i++){
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method getMethod = null;
				Method setMethod = null;
				try {
					getMethod = c.getDeclaredMethod(getMethodName);//.getMethod(getMethodName);
					setMethod = c.getDeclaredMethod(setMethodName, new Class[]{field.getType()});//.getMethod(setMethodName);
				} catch (NoSuchMethodException e) {
				} catch (SecurityException e) {
				}
				if(getMethod == null || setMethod == null) continue;
				Object oldVal = getMethod.invoke(oldObject, new Object[]{});//field.get(oldObject);
				Object newVal = getMethod.invoke(newObject, new Object[]{});//field.get(newObject);
				if(oldVal == null && newVal == null){
					continue;
				}else if(oldVal != null && newVal == null){
					setMethod.invoke(newObject, new Object[]{oldVal});
				}else if(oldVal != null && newVal != null){
					if(!oldVal.equals(newVal)){
						map = new HashMap<String, Object>();
						map.put("field", fields[i].getName());
						map.put("oldValue", oldVal);
						map.put("newValue", newVal);
						res.add(map);
						setMethod.invoke(oldObject, new Object[]{newVal});
					}
				}else{
					map = new HashMap<String, Object>();
					map.put("field", fields[i].getName());
					map.put("oldValue", oldVal);
					map.put("newValue", newVal);
					res.add(map);
					setMethod.invoke(oldObject, new Object[]{newVal});
				}
			}
		}catch(Exception ex){
			throw new CompareModelException("CompareModel Reflect Exception", ModelUtils.class);
		}
		return res;
	}
}
