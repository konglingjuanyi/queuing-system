package com.qunar.ops.recruit.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WaitService<T extends Comparable<T>> {

	
	List<T> list = new LinkedList<T>();
	
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
	
}

