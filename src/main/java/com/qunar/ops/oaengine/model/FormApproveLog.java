package com.qunar.ops.oaengine.model;

import java.util.Date;

public class FormApproveLog {
    private Integer id;

    private Long formId;

    private String approveUser;

    private String taskId;

    private String taskName;

    private String managerType;

    private String nextTaskId;

    private String nextTaskName;

    private String nextCandidate;

    private Date ts;

    private Date dob;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser == null ? null : approveUser.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getManagerType() {
        return managerType;
    }

    public void setManagerType(String managerType) {
        this.managerType = managerType == null ? null : managerType.trim();
    }

    public String getNextTaskId() {
        return nextTaskId;
    }

    public void setNextTaskId(String nextTaskId) {
        this.nextTaskId = nextTaskId == null ? null : nextTaskId.trim();
    }

    public String getNextTaskName() {
        return nextTaskName;
    }

    public void setNextTaskName(String nextTaskName) {
        this.nextTaskName = nextTaskName == null ? null : nextTaskName.trim();
    }

    public String getNextCandidate() {
        return nextCandidate;
    }

    public void setNextCandidate(String nextCandidate) {
        this.nextCandidate = nextCandidate == null ? null : nextCandidate.trim();
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}