package com.qunar.ops.recruit.model;

import java.util.Date;

public class Interviewer {
    private Integer id;

    private String userName;

    private String password;

    private String city;

    private String oneView;

    private String twoView;

    private Integer oneCount;

    private Integer twoCount;

    private Integer status;

    private Date startDate;

    private Date endDate;

    private Date createTime;

    private Long pahseId;

    private Long viewCount;

    private Object room;

    private Long enable;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
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

    public Integer getOneCount() {
        return oneCount;
    }

    public void setOneCount(Integer oneCount) {
        this.oneCount = oneCount;
    }

    public Integer getTwoCount() {
        return twoCount;
    }

    public void setTwoCount(Integer twoCount) {
        this.twoCount = twoCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getPahseId() {
        return pahseId;
    }

    public void setPahseId(Long pahseId) {
        this.pahseId = pahseId;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Object getRoom() {
        return room;
    }

    public void setRoom(Object room) {
        this.room = room;
    }

    public Long getEnable() {
        return enable;
    }

    public void setEnable(Long enable) {
        this.enable = enable;
    }
}