package com.qunar.ops.recruit.model;

public class Interviewer {
    private Integer id;

    private String userName;

    private String password;

    private String oneView;

    private String twoView;

    private Integer viewCount;

    private String determine;

    private String job;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getOneView() {
        return oneView;
    }

    public void setOneView(String oneView) {
        this.oneView = oneView == null ? null : oneView.trim();
    }

    public String getTwoView() {
        return twoView;
    }

    public void setTwoView(String twoView) {
        this.twoView = twoView == null ? null : twoView.trim();
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getDetermine() {
        return determine;
    }

    public void setDetermine(String determine) {
        this.determine = determine == null ? null : determine.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }
}