package com.qunar.ops.recruit.model;

import java.util.Date;

public class Interviewer {
    private Integer id;

    private String name;

    private String user_name;

    private String password;

    private String location;

    private String city;

    private String job;

    private String role;

    private Integer one_count;

    private Integer two_count;
    
    private Integer state;

    private Date start_date;

    private Date end_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getOne_count() {
		return one_count;
	}

	public void setOne_count(Integer one_count) {
		this.one_count = one_count;
	}

	public Integer getTwo_count() {
		return two_count;
	}

	public void setTwo_count(Integer two_count) {
		this.two_count = two_count;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

   
}