package com.qunar.ops.oaengine.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qunar.ops.oaengine.model.Formmain0114;


public class ModelUtils {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
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
	
	public static <T> List<Map<String, Object>> compareModel(Class c, T oldObject, T newObject) throws IllegalArgumentException, IllegalAccessException{
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		Map<String, Object> map;
		Field[] fields = c.getDeclaredFields();
		for(int i = 0; i < fields.length; i++){
			fields[i].setAccessible(true);
			if(fields[i].get(oldObject) == null && fields[i].get(newObject) == null){
				continue;
			}else if(fields[i].get(oldObject) != null && fields[i].get(newObject) != null){
				if(!fields[i].get(oldObject).equals(fields[i].get(newObject))){
					map = new HashMap<String, Object>();
					map.put("field", fields[i].getName());
					map.put("oldValue", fields[i].get(oldObject));
					map.put("newValue", fields[i].get(newObject));
					res.add(map);
				}
			}else{
				map = new HashMap<String, Object>();
				map.put("field", fields[i].getName());
				map.put("oldValue", fields[i].get(oldObject));
				map.put("newValue", fields[i].get(newObject));
				res.add(map);
			}
		}
		return res;
	}
}
