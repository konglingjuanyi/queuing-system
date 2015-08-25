package com.qunar.ops.recruit.model;

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

    private String phase;

    private Long viewCount;

    private String room;

    private Long enable;

    private String determine;

    private String job;

    private Integer firstRd;

    private Integer secondRd;

    private Integer firstFe;

    private Integer secondFe;

    private Integer firstQa;

    private Integer secondQa;

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

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase == null ? null : phase.trim();
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    public Long getEnable() {
        return enable;
    }

    public void setEnable(Long enable) {
        this.enable = enable;
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

    public Integer getFirstRd() {
        return firstRd;
    }

    public void setFirstRd(Integer firstRd) {
        this.firstRd = firstRd;
    }

    public Integer getSecondRd() {
        return secondRd;
    }

    public void setSecondRd(Integer secondRd) {
        this.secondRd = secondRd;
    }

    public Integer getFirstFe() {
        return firstFe;
    }

    public void setFirstFe(Integer firstFe) {
        this.firstFe = firstFe;
    }

    public Integer getSecondFe() {
        return secondFe;
    }

    public void setSecondFe(Integer secondFe) {
        this.secondFe = secondFe;
    }

    public Integer getFirstQa() {
        return firstQa;
    }

    public void setFirstQa(Integer firstQa) {
        this.firstQa = firstQa;
    }

    public Integer getSecondQa() {
        return secondQa;
    }

    public void setSecondQa(Integer secondQa) {
        this.secondQa = secondQa;
    }
}