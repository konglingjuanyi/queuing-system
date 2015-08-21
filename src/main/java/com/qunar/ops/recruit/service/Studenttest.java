package com.qunar.ops.recruit.service;

import com.qunar.ops.recruit.util.RecruitConst;

public class Studenttest implements Comparable<Studenttest>{
	long nowtime;
	long shouldComeTime;
	long realComeTime;
	String name;
	
	
	
	public Studenttest(long nowtime, long shouldComeTime,long realComeTime,String name) {
		this.nowtime = nowtime;
		this.realComeTime = realComeTime;
		this.shouldComeTime = shouldComeTime;
		this.name = name;
	}

	@Override
	public int compareTo(Studenttest u) {
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
		if(nowtime >= shouldComeTime && realComeTime <= shouldComeTime){
			return RecruitConst.NORMAL;
		}else{
			return RecruitConst.IN_NORMAL;
		}
	}

	

	@Override
	public boolean equals(Object obj) {
		Studenttest uw = (Studenttest) obj;
		return this.shouldComeTime == uw.shouldComeTime;
	}

	public long getNowtime() {
		return nowtime;
	}

	public void setNowtime(long nowtime) {
		this.nowtime = nowtime;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	

	
}