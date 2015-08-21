package com.qunar.ops.recruit.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.model.Student;

@Component
public class WaitService<T extends Comparable<T>> {

	
	List<T> list = new LinkedList<T>();
	
	List<T> twoList = new LinkedList<T>();
	
	public synchronized int numberInFrontOf(T t){
		int count = 0;
		for (T tt : list) {
			if(t.equals(tt)){
				break;
			}
			count ++;
		}
		return count;
	}
	
	public synchronized int add2WaitList(T t){
		list.add(t);
		Collections.sort(list);
		return numberInFrontOf(t);
	}
	
	public synchronized T removeHighestPriorityFromList(){
		T ret = null;
		for (T t : list) {
			if(ret == null){
				ret = t;
			}else{
				ret = ret.compareTo(t) < 0 ? t:ret;
			}
		}
		list.remove(ret);
		Collections.sort(list);
		return ret;
	}

	/**
	 * 添加到二面队列中
	 * @param stu
	 */
	public synchronized void addTwoList(Student stu) {
		// TODO Auto-generated method stub
		twoList.add((T) stu);
	}

	/**
	 * 二面面试官获取二面候选人数据
	 * @param city
	 * @param twoView
	 * @param userName
	 * @return
	 */
	public synchronized Student getTwoView(String city, String twoView, String userName) {
		// TODO Auto-generated method stub
		for(int i=0;i<twoList.size();i++){
			
		}
		return null;
	}
	
}

