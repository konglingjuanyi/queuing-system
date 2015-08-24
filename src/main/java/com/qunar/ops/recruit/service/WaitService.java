package com.qunar.ops.recruit.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.model.Student;

@Component
public class WaitService {

	
	List<StudentWaiter> list = new LinkedList<StudentWaiter>();
	
	List<Studenttest> l = new LinkedList<Studenttest>();
	
	List<StudentWaiter> twoList = new LinkedList<StudentWaiter>();

	public synchronized int numberInFrontOf(StudentWaiter t){
		if(list.contains(t)){
			return numberInFrontOfOne(t);
		}
		if(twoList.contains(t)){
			return numberInFrontOfTwo(t);
		}
		return -1;
	}
	
	public synchronized int numberInFrontOfOne(StudentWaiter t) {
		int count = 0;
		for (StudentWaiter stu : list) {
			if(t.equals(stu)){
				break;
			}
//			System.out.println("=======>"+stu.getStu().getLocation()+"\t"+t.getStu().getLocation());
			if(stu.getStu().getLocation().equals(t.getStu().getLocation()) &&
					stu.getStu().getJob().equals(t.getStu().getJob())){
				count ++;
			}
		}
		return count;
	}
	
	
	public synchronized int numberInFrontOfTwo(StudentWaiter t) {
		int count = 0;
		for (StudentWaiter stu : twoList) {
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
	
	public synchronized int addtest(Studenttest t){
		l.add(t);
		Collections.sort(l);
		for(int i=0;i<l.size();i++){
			System.out.println(l.get(i).getName()+"++"+l.get(i).getShouldComeTime()+"==="+l.get(i).getRealComeTime()+"***"+l.get(i).getNowtime());
		}
		return 0;
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
	
	public boolean contains(StudentWaiter stu) {
		return list.contains(stu) || twoList.contains(stu);
	}

	public void recovery(List<Student> oneList, List<Student> twoList) {
		List<StudentWaiter> swList1 = new LinkedList<StudentWaiter>();
		for (Student student : oneList) {
			swList1.add(new StudentWaiter(student,student.getTrueTime().getTime()));
		}
		this.list = swList1;
		Collections.sort(list);
		List<StudentWaiter> swList2 = new LinkedList<StudentWaiter>();
		for (Student student : twoList) {
			swList2.add(new StudentWaiter(student,student.getTrueTime().getTime()));
		}
		this.twoList = swList2;
		Collections.sort(this.twoList, new Comparator<StudentWaiter>() {

			@Override
			public int compare(StudentWaiter o1, StudentWaiter o2) {
				return o1.getRealComeTime() > o2.getRealComeTime() ? 1:-1;
			}
		});
		
	}

	@Override
	public String toString() {
		return list + "\n" + twoList;
	}
	
	

	
}

