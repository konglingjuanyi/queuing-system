package com.qunar.ops.recruit.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.model.Student;

@Component
public class WaitService {

	
	List<StudentWaiter> list = new LinkedList<StudentWaiter>();
	
	public synchronized int numberInFrontOf(StudentWaiter t){
		int count = 0;
		for (StudentWaiter stu : list) {
			if(t.equals(stu)){
				break;
			}
			if(stu.getStu().getLocation().equals(t.getStu().getLocation()) &&
					stu.getStu().getJob().equals(t.getStu().getJob())){
				count ++;
			}
		}
		return count;
	}
	
	public synchronized int add2WaitList(StudentWaiter t){
		list.add(t);
		Collections.sort(list);
		return numberInFrontOf(t);
	}
	
	public synchronized StudentWaiter removeHighestPriorityFromList(){
		StudentWaiter ret = null;
		for (StudentWaiter t : list) {
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

	public boolean contains(Student stu) {
		return list.contains(stu);
	}
	
}

