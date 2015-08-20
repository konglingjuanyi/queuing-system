package com.qunar.ops.recruit.service;

import com.qunar.ops.recruit.util.RecruitConst;

public class StudentWaiter implements Comparable<StudentWaiter>{
	int userId;
	long shouldComeTime;
	long realComeTime;
	
	@Override
	public int compareTo(StudentWaiter u) {
		if(this.getState().equals(RecruitConst.NORMAL) && u.getState().equals(RecruitConst.NORMAL)){
			return this.realComeTime > u.realComeTime ? -1:1;
		}else if(this.getState().equals(RecruitConst.NORMAL)){
			return 1;
		}else if(u.getState().equals(RecruitConst.NORMAL)){
			return -1;
		}else{
			return this.realComeTime > u.realComeTime ? -1:1;
		}
	}

	public String getState(){
		if(System.currentTimeMillis() >= shouldComeTime && realComeTime <= shouldComeTime){
			return RecruitConst.NORMAL;
		}else{
			return RecruitConst.IN_NORMAL;
		}
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	@Override
	public boolean equals(Object obj) {
		StudentWaiter uw = (StudentWaiter) obj;
		return this.userId == uw.userId;
	}
	
	

	
}