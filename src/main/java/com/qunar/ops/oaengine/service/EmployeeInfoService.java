package com.qunar.ops.oaengine.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.result.EmployeeInfo;

@Component
public class EmployeeInfoService {
	
	/**
	 * 获取员工信息
	 * @param userId
	 * @return
	 * @throws RemoteAccessException
	 */
	public EmployeeInfo getEmployee(String userId) throws RemoteAccessException{
		return null;
	}
	
	/**
	 * 获取员工所在部门VP rtxId
	 * @param userId
	 * @return 没有找到返回空list
	 * @throws RemoteAccessException
	 */
	public List<String> findVP(String userId) throws RemoteAccessException{
		return null;
	}
	
	/**
	 * 获取员工直接主管， 如果没有指定上级主管获取部门VP，如果VP没找到返回空list
	 * @param userId
	 * @return 没有找到返回空list
	 * @throws RemoteAccessException
	 */
	public List<String> findManager(String userId) throws RemoteAccessException{
		return null;
	}
	
	/**
	 * 获取员工上上级主管；
	 * 如果员工上上级主管是VP，返回上级主管；
	 * 如果以上条件均没有获取到，返回VP，如果VP没找到返回空list
	 * @param userId
	 * @return
	 * @throws RemoteAccessException
	 */
	public List<String> findDirector(String userId) throws RemoteAccessException{
		return null;
	}
	
	/**
	 * 获取工时
	 * @param userId
	 * @param day
	 * @return
	 */
	public float getLaborHour(String userId, Date day){
		return 0;
	}
	

}
