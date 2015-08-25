package com.qunar.ops.recruit.model;

public class Phase {
    private Integer id;

    private String phaseName;

    private String cityName;

    private Object yearInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName == null ? null : phaseName.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Object getYearInfo() {
        return yearInfo;
    }

    public void setYearInfo(Object yearInfo) {
        this.yearInfo = yearInfo;
    }
}