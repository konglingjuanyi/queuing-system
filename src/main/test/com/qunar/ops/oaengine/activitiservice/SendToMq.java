package com.qunar.ops.oaengine.activitiservice;

import java.util.Iterator;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * 从配置文件注入多个属性类型必须为Expression
 * 
 * @author yongnian.jiang
 * 
 * 2014-10-09 上午10:08:01
 */
public class SendToMq implements JavaDelegate {
	
	private Expression text1;
	private Expression text2;
	
	public void execute(DelegateExecution execution) {
		Map<String, Object> vars = execution.getVariables();
		Iterator iter = execution.getVariableNames().iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		String value1 = (String)vars.get("var1");

		String value2 = (String)vars.get("var2");
		System.out.println(value1 + "===" + value2);
	}
}