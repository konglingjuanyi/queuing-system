package com.qunar.ops.recruit.service;

import java.util.List;

import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.util.RecruitConst;

public class StudentWaiter implements Comparable<StudentWaiter>{
	Student stu;
	long shouldComeTime;
	long realComeTime;
	
	
	
	public StudentWaiter(Student stu, long realComeTime) {
		this.stu = stu;
		this.realComeTime = realComeTime;
		this.shouldComeTime = stu.getInterviewTime().getTime();
	}
	
	public StudentWaiter(Student stu) {
		this.stu = stu;
		this.realComeTime = stu.getTrueTime().getTime();
		this.shouldComeTime = stu.getInterviewTime().getTime();
	}

	@Override
	public int compareTo(StudentWaiter u) {
		if(this.getState().equals(RecruitConst.NORMAL) && u.getState().equals(RecruitConst.NORMAL)){
			return this.realComeTime > u.realComeTime ? -1:1;
		}else if(this.getState().equals(RecruitConst.NORMAL)){
			return -1;
		}else if(u.getState().equals(RecruitConst.NORMAL)){
			return 1;
		}else{
			return this.realComeTime > u.realComeTime ? 1:-1;
		}
	}

	public String getState(){
		if(System.currentTimeMillis() >= shouldComeTime && realComeTime <= shouldComeTime){
			return RecruitConst.NORMAL;
		}else{
			return RecruitConst.IN_NORMAL;
		}
	}

	public long getShouldComeTime() {
		return shouldComeTime;
	}

	public void setShouldComeTime(long shouldComeTime) {
		this.shouldComeTime = shouldComeTime;
	}

	public long getRealComeTime() {
		return realComeTime;
	}

	public void setRealComeTime(long realComeTime) {
		this.realComeTime = realComeTime;
	}

	public Student getStu() {
		return stu;
	}

	public void setStu(Student stu) {
		this.stu = stu;
	}

	@Override
	public boolean equals(Object obj) {
//		System.out.println(this+" "+obj);
		if(obj !=null){
			StudentWaiter uw = (StudentWaiter) obj;
			if(this.stu != null && uw.getStu() != null)
				return this.stu.getId() == uw.getStu().getId();
			else
				return false;
		}else{
			return false;
		}

	}

	public void recovery(List<Student> oneList, List<Student> twoList) {
		// TODO Auto-generated method stub
		
	}

	
	

	
}