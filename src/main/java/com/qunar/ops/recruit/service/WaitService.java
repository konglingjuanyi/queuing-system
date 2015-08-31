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

	boolean b = false;
	
	public synchronized int numberInFrontOf(StudentWaiter t){
		if(list.contains(t)){
			return numberInFrontOfOne(t)+1;
		}
		if(twoList.contains(t)){
			return numberInFrontOfTwo(t)+1;

		}
		return -1;
	}
	
	public synchronized int numberInFrontOfOne(StudentWaiter t) {
		int count = 0;
//		System.out.println(list);
		for (StudentWaiter stu : list) {
//			System.out.println("=======>"+stu.getStu().getLocation()+"\t"+t.getStu().getLocation());
			Student s1 = stu.getStu();
			Student ss = t.getStu();
			if(isSameCity(s1, ss) && s1.getJob().equals(ss.getJob()) && stu.compareTo(t) > 0){
				count ++;
			}
		}
		return count;
	}
	
	
	private boolean isSameCity(Student s1, Student s2) {
		if(s1 == null || s2 == null)
			return false;
		if(s1.getYear().equals(s2.getYear()) && s1.getPhaseNo().equals(s2.getPhaseNo())
				&& s1.getLocation().equals(s2.getLocation())){
			return true;
		}else{
			return false;
		}
	}

	public synchronized int numberInFrontOfTwo(StudentWaiter t) {
		int count = 0;
		for (StudentWaiter stu : twoList) {
//			System.out.println("=======>"+stu.getStu().getLocation()+"\t"+t.getStu().getLocation());
			Student s1 = stu.getStu();
			Student ss = t.getStu();
			if(isSameCity(s1, ss) && s1.getJob().equals(ss.getJob()) && stu.compareTo(t) > 0){
				count ++;
			}
		}
		return count;
	}
	
	public synchronized int add2WaitList(StudentWaiter t){
		list.add(t);
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

	public void sort() {
		Collections.sort(list);
		Collections.sort(twoList);
		
	}
	
	public synchronized StudentWaiter removeHighestPriorityFromList(String year, String phase, String city, String oneView){
//		System.out.println(year+"====================="+phase+city);
//		System.out.println(list.get(0).getStu().getLocation());
//		System.out.println(list.get(0).getStu().getPhaseNo());
//		System.out.println(list.get(0).getStu().getYear());
//		System.out.println(list.get(0).getStu().getJob());
//		System.out.println(oneView);
		List<StudentWaiter> tmpList = new LinkedList<StudentWaiter>();
		for (StudentWaiter t : list) {
			Student stu = t.getStu();
			if(stu.getYear().equals(year) && stu.getPhaseNo().equals(phase)
					&& stu.getLocation().equals(city)
					&& oneView.contains(stu.getJob())){
				tmpList.add(t);
			}
		}
		StudentWaiter ret = null;
		for (StudentWaiter t : tmpList) {
			if(ret == null){
				ret = t;
			}else{
				ret = ret.compareTo(t) < 0 ? t:ret;
			}
		}
		System.out.println(ret);
		if(ret != null)
			list.remove(ret);
		return ret;
	}
	
	public synchronized StudentWaiter removeHighestPriorityFromTwoList(String year,
			String phase, String city, String twoView, String interviewName) {
		List<StudentWaiter> tmpList = new LinkedList<StudentWaiter>();
		for (StudentWaiter t : twoList) {
			Student stu = t.getStu();
			if(stu.getYear().equals(year) && stu.getPhaseNo().equals(phase)
					&& stu.getLocation().equals(city)
					&& !stu.getFirstTry().equals(interviewName)
					&& twoView.contains(stu.getJob())){
				tmpList.add(t);
			}
		}
		StudentWaiter ret = null;
		for (StudentWaiter t : tmpList) {
			if(ret == null){
				ret = t;
			}else{
				ret = ret.compareTo(t) < 0 ? t:ret;
			}
		}
		if(ret != null)
			twoList.remove(ret);
		return ret;
	}

	public boolean containsOne(StudentWaiter studentWaiter) {
		return list.contains(studentWaiter);
	}
	
	public boolean containsTwo(StudentWaiter studentWaiter) {
		return twoList.contains(studentWaiter);
	}

	
}

