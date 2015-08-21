package com.qunar.ops.recruit.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.model.Student;

@Component
public class WaitService {

	
	List<StudentWaiter> list = new LinkedList<StudentWaiter>();
	
	List<StudentWaiter> twoList = new LinkedList<StudentWaiter>();

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
	
	public synchronized StudentWaiter removeHighestPriorityFromList(String city, String twoView, String userName){
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


	/**
	 * 添加到二面队列中
	 * @param stu
	 */
	public synchronized void addTwoList(StudentWaiter stu) {
		// TODO Auto-generated method stub
		twoList.add(stu);
	}

	/**
	 * 二面面试官获取二面候选人数据
	 * @param city
	 * @param twoView
	 * @param userName
	 * @return
	 */
	public synchronized StudentWaiter getTwoView(String city, String twoView, String userName) {
		// TODO Auto-generated method stub
		StudentWaiter stu=null;
		for(int i=0;i<twoList.size();i++){
			String ci=twoList.get(i).getStu().getLocation();
			String view=twoList.get(i).getStu().getJob();
			String one=twoList.get(i).getStu().getFirstTry();
			if(city.equals(ci)&&twoView.contains(view)&&!userName.equals(one)){
				stu=twoList.get(i);
				break;
			}
		}
		return stu;
	}
	
	public boolean contains(Student stu) {
		return list.contains(stu);
	}
	
}

