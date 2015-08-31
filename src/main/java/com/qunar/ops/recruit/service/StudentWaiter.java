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
		if(stu.getTrueTime()==null || "".equals(stu.getTrueTime())){
			this.realComeTime =0;
		}else{
			this.realComeTime = stu.getTrueTime().getTime();
		}
		this.shouldComeTime = stu.getInterviewTime().getTime();
	}

	@Override
	public int compareTo(StudentWaiter u) {
		if(this.getState().equals(RecruitConst.NORMAL) && u.getState().equals(RecruitConst.NORMAL)){
			if(this.realComeTime > u.realComeTime){
				return -1;
			}else if(this.realComeTime < u.realComeTime){
				return 1;
			}else{
				return 0;
			}
		}else if(this.getState().equals(RecruitConst.NORMAL)){
			return 1;
		}else if(u.getState().equals(RecruitConst.NORMAL)){
			return -1;
		}else{
			if(this.realComeTime > u.realComeTime){
				return -1;
			}else if(this.realComeTime < u.realComeTime){
				return 1;
			}else{
				return 0;
			}
		}
	}

	public String getState(){
		if(System.currentTimeMillis() >= shouldComeTime && realComeTime <= shouldComeTime + RecruitConst.LATE_TIME){
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
		if(obj !=null){
			StudentWaiter uw = (StudentWaiter) obj;
//			System.out.println(this.stu);
//			System.out.println(this.stu.getId().equals(uw.getStu().getId()));
			if(this.stu != null && uw.getStu() != null)
				return this.stu.getId().equals(uw.getStu().getId());
			else
				return false;
		}else{
			return false;
		}

	}
//	
//	@Override
//	public int hashCode() {
//		return this.getStu().getId();
//	}

	public void recovery(List<Student> oneList, List<Student> twoList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return this.getStu().toString();
	}
	

	
}