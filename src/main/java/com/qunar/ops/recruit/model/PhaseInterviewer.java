package com.qunar.ops.recruit.model;

public class PhaseInterviewer {
    private Integer id;

    private String year;

    private String phase;

    private String city;

    private String intervierName;

    private String room;

    private String status;

    private Integer oneCount;

    private Integer twoCount;

    private Integer firstRd;

    private Integer firstFe;

    private Integer firstQa;

    private Integer secondRd;

    private Integer secondFe;

    private Integer secondQa;

    private String enable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase == null ? null : phase.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getIntervierName() {
        return intervierName;
    }

    public void setIntervierName(String intervierName) {
        this.intervierName = intervierName == null ? null : intervierName.trim();
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public Integer getFirstRd() {
        return firstRd;
    }

    public void setFirstRd(Integer firstRd) {
        this.firstRd = firstRd;
    }

    public Integer getFirstFe() {
        return firstFe;
    }

    public void setFirstFe(Integer firstFe) {
        this.firstFe = firstFe;
    }

    public Integer getFirstQa() {
        return firstQa;
    }

    public void setFirstQa(Integer firstQa) {
        this.firstQa = firstQa;
    }

    public Integer getSecondRd() {
        return secondRd;
    }

    public void setSecondRd(Integer secondRd) {
        this.secondRd = secondRd;
    }

    public Integer getSecondFe() {
        return secondFe;
    }

    public void setSecondFe(Integer secondFe) {
        this.secondFe = secondFe;
    }

    public Integer getSecondQa() {
        return secondQa;
    }

    public void setSecondQa(Integer secondQa) {
        this.secondQa = secondQa;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }
}