package com.qunar.ops.oaengine.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FormApproveLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limit = -1;

    protected int offset = -1;

    public FormApproveLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(int limit) {
        this.limit=limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setOffset(int offset) {
        this.offset=offset;
    }

    public int getOffset() {
        return offset;
    }

    public void limit(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNull() {
            addCriterion("form_id is null");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNotNull() {
            addCriterion("form_id is not null");
            return (Criteria) this;
        }

        public Criteria andFormIdEqualTo(Long value) {
            addCriterion("form_id =", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotEqualTo(Long value) {
            addCriterion("form_id <>", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThan(Long value) {
            addCriterion("form_id >", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThanOrEqualTo(Long value) {
            addCriterion("form_id >=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThan(Long value) {
            addCriterion("form_id <", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThanOrEqualTo(Long value) {
            addCriterion("form_id <=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdIn(List<Long> values) {
            addCriterion("form_id in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotIn(List<Long> values) {
            addCriterion("form_id not in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdBetween(Long value1, Long value2) {
            addCriterion("form_id between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotBetween(Long value1, Long value2) {
            addCriterion("form_id not between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andApproveUserIsNull() {
            addCriterion("approve_user is null");
            return (Criteria) this;
        }

        public Criteria andApproveUserIsNotNull() {
            addCriterion("approve_user is not null");
            return (Criteria) this;
        }

        public Criteria andApproveUserEqualTo(String value) {
            addCriterion("approve_user =", value, "approveUser");
            return (Criteria) this;
        }

        public Criteria andApproveUserNotEqualTo(String value) {
            addCriterion("approve_user <>", value, "approveUser");
            return (Criteria) this;
        }

        public Criteria andApproveUserGreaterThan(String value) {
            addCriterion("approve_user >", value, "approveUser");
            return (Criteria) this;
        }

        public Criteria andApproveUserGreaterThanOrEqualTo(String value) {
            addCriterion("approve_user >=", value, "approveUser");
            return (Criteria) this;
        }

        public Criteria andApproveUserLessThan(String value) {
            addCriterion("approve_user <", value, "approveUser");
            return (Criteria) this;
        }

        public Criteria andApproveUserLessThanOrEqualTo(String value) {
            addCriterion("approve_user <=", value, "approveUser");
            return (Criteria) this;
        }

        public Criteria andApproveUserLike(String value) {
            addCriterion("approve_user like", value, "approveUser");
            return (Criteria) this;
        }

        public Criteria andApproveUserNotLike(String value) {
            addCriterion("approve_user not like", value, "approveUser");
            return (Criteria) this;
        }

        public Criteria andApproveUserIn(List<String> values) {
            addCriterion("approve_user in", values, "approveUser");
            return (Criteria) this;
        }

        public Criteria andApproveUserNotIn(List<String> values) {
            addCriterion("approve_user not in", values, "approveUser");
            return (Criteria) this;
        }

        public Criteria andApproveUserBetween(String value1, String value2) {
            addCriterion("approve_user between", value1, value2, "approveUser");
            return (Criteria) this;
        }

        public Criteria andApproveUserNotBetween(String value1, String value2) {
            addCriterion("approve_user not between", value1, value2, "approveUser");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("task_id like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("task_id not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNull() {
            addCriterion("task_name is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("task_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("task_name =", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("task_name <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("task_name >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("task_name >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("task_name <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("task_name <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("task_name like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("task_name not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("task_name in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("task_name not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("task_name between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("task_name not between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andManagerTypeIsNull() {
            addCriterion("manager_type is null");
            return (Criteria) this;
        }

        public Criteria andManagerTypeIsNotNull() {
            addCriterion("manager_type is not null");
            return (Criteria) this;
        }

        public Criteria andManagerTypeEqualTo(String value) {
            addCriterion("manager_type =", value, "managerType");
            return (Criteria) this;
        }

        public Criteria andManagerTypeNotEqualTo(String value) {
            addCriterion("manager_type <>", value, "managerType");
            return (Criteria) this;
        }

        public Criteria andManagerTypeGreaterThan(String value) {
            addCriterion("manager_type >", value, "managerType");
            return (Criteria) this;
        }

        public Criteria andManagerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("manager_type >=", value, "managerType");
            return (Criteria) this;
        }

        public Criteria andManagerTypeLessThan(String value) {
            addCriterion("manager_type <", value, "managerType");
            return (Criteria) this;
        }

        public Criteria andManagerTypeLessThanOrEqualTo(String value) {
            addCriterion("manager_type <=", value, "managerType");
            return (Criteria) this;
        }

        public Criteria andManagerTypeLike(String value) {
            addCriterion("manager_type like", value, "managerType");
            return (Criteria) this;
        }

        public Criteria andManagerTypeNotLike(String value) {
            addCriterion("manager_type not like", value, "managerType");
            return (Criteria) this;
        }

        public Criteria andManagerTypeIn(List<String> values) {
            addCriterion("manager_type in", values, "managerType");
            return (Criteria) this;
        }

        public Criteria andManagerTypeNotIn(List<String> values) {
            addCriterion("manager_type not in", values, "managerType");
            return (Criteria) this;
        }

        public Criteria andManagerTypeBetween(String value1, String value2) {
            addCriterion("manager_type between", value1, value2, "managerType");
            return (Criteria) this;
        }

        public Criteria andManagerTypeNotBetween(String value1, String value2) {
            addCriterion("manager_type not between", value1, value2, "managerType");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdIsNull() {
            addCriterion("next_task_id is null");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdIsNotNull() {
            addCriterion("next_task_id is not null");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdEqualTo(String value) {
            addCriterion("next_task_id =", value, "nextTaskId");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdNotEqualTo(String value) {
            addCriterion("next_task_id <>", value, "nextTaskId");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdGreaterThan(String value) {
            addCriterion("next_task_id >", value, "nextTaskId");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("next_task_id >=", value, "nextTaskId");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdLessThan(String value) {
            addCriterion("next_task_id <", value, "nextTaskId");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdLessThanOrEqualTo(String value) {
            addCriterion("next_task_id <=", value, "nextTaskId");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdLike(String value) {
            addCriterion("next_task_id like", value, "nextTaskId");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdNotLike(String value) {
            addCriterion("next_task_id not like", value, "nextTaskId");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdIn(List<String> values) {
            addCriterion("next_task_id in", values, "nextTaskId");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdNotIn(List<String> values) {
            addCriterion("next_task_id not in", values, "nextTaskId");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdBetween(String value1, String value2) {
            addCriterion("next_task_id between", value1, value2, "nextTaskId");
            return (Criteria) this;
        }

        public Criteria andNextTaskIdNotBetween(String value1, String value2) {
            addCriterion("next_task_id not between", value1, value2, "nextTaskId");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameIsNull() {
            addCriterion("next_task_name is null");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameIsNotNull() {
            addCriterion("next_task_name is not null");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameEqualTo(String value) {
            addCriterion("next_task_name =", value, "nextTaskName");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameNotEqualTo(String value) {
            addCriterion("next_task_name <>", value, "nextTaskName");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameGreaterThan(String value) {
            addCriterion("next_task_name >", value, "nextTaskName");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("next_task_name >=", value, "nextTaskName");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameLessThan(String value) {
            addCriterion("next_task_name <", value, "nextTaskName");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameLessThanOrEqualTo(String value) {
            addCriterion("next_task_name <=", value, "nextTaskName");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameLike(String value) {
            addCriterion("next_task_name like", value, "nextTaskName");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameNotLike(String value) {
            addCriterion("next_task_name not like", value, "nextTaskName");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameIn(List<String> values) {
            addCriterion("next_task_name in", values, "nextTaskName");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameNotIn(List<String> values) {
            addCriterion("next_task_name not in", values, "nextTaskName");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameBetween(String value1, String value2) {
            addCriterion("next_task_name between", value1, value2, "nextTaskName");
            return (Criteria) this;
        }

        public Criteria andNextTaskNameNotBetween(String value1, String value2) {
            addCriterion("next_task_name not between", value1, value2, "nextTaskName");
            return (Criteria) this;
        }

        public Criteria andNextCandidateIsNull() {
            addCriterion("next_candidate is null");
            return (Criteria) this;
        }

        public Criteria andNextCandidateIsNotNull() {
            addCriterion("next_candidate is not null");
            return (Criteria) this;
        }

        public Criteria andNextCandidateEqualTo(String value) {
            addCriterion("next_candidate =", value, "nextCandidate");
            return (Criteria) this;
        }

        public Criteria andNextCandidateNotEqualTo(String value) {
            addCriterion("next_candidate <>", value, "nextCandidate");
            return (Criteria) this;
        }

        public Criteria andNextCandidateGreaterThan(String value) {
            addCriterion("next_candidate >", value, "nextCandidate");
            return (Criteria) this;
        }

        public Criteria andNextCandidateGreaterThanOrEqualTo(String value) {
            addCriterion("next_candidate >=", value, "nextCandidate");
            return (Criteria) this;
        }

        public Criteria andNextCandidateLessThan(String value) {
            addCriterion("next_candidate <", value, "nextCandidate");
            return (Criteria) this;
        }

        public Criteria andNextCandidateLessThanOrEqualTo(String value) {
            addCriterion("next_candidate <=", value, "nextCandidate");
            return (Criteria) this;
        }

        public Criteria andNextCandidateLike(String value) {
            addCriterion("next_candidate like", value, "nextCandidate");
            return (Criteria) this;
        }

        public Criteria andNextCandidateNotLike(String value) {
            addCriterion("next_candidate not like", value, "nextCandidate");
            return (Criteria) this;
        }

        public Criteria andNextCandidateIn(List<String> values) {
            addCriterion("next_candidate in", values, "nextCandidate");
            return (Criteria) this;
        }

        public Criteria andNextCandidateNotIn(List<String> values) {
            addCriterion("next_candidate not in", values, "nextCandidate");
            return (Criteria) this;
        }

        public Criteria andNextCandidateBetween(String value1, String value2) {
            addCriterion("next_candidate between", value1, value2, "nextCandidate");
            return (Criteria) this;
        }

        public Criteria andNextCandidateNotBetween(String value1, String value2) {
            addCriterion("next_candidate not between", value1, value2, "nextCandidate");
            return (Criteria) this;
        }

        public Criteria andTsIsNull() {
            addCriterion("ts is null");
            return (Criteria) this;
        }

        public Criteria andTsIsNotNull() {
            addCriterion("ts is not null");
            return (Criteria) this;
        }

        public Criteria andTsEqualTo(Date value) {
            addCriterion("ts =", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotEqualTo(Date value) {
            addCriterion("ts <>", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThan(Date value) {
            addCriterion("ts >", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThanOrEqualTo(Date value) {
            addCriterion("ts >=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThan(Date value) {
            addCriterion("ts <", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThanOrEqualTo(Date value) {
            addCriterion("ts <=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsIn(List<Date> values) {
            addCriterion("ts in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotIn(List<Date> values) {
            addCriterion("ts not in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsBetween(Date value1, Date value2) {
            addCriterion("ts between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotBetween(Date value1, Date value2) {
            addCriterion("ts not between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andDobIsNull() {
            addCriterion("dob is null");
            return (Criteria) this;
        }

        public Criteria andDobIsNotNull() {
            addCriterion("dob is not null");
            return (Criteria) this;
        }

        public Criteria andDobEqualTo(Date value) {
            addCriterionForJDBCDate("dob =", value, "dob");
            return (Criteria) this;
        }

        public Criteria andDobNotEqualTo(Date value) {
            addCriterionForJDBCDate("dob <>", value, "dob");
            return (Criteria) this;
        }

        public Criteria andDobGreaterThan(Date value) {
            addCriterionForJDBCDate("dob >", value, "dob");
            return (Criteria) this;
        }

        public Criteria andDobGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dob >=", value, "dob");
            return (Criteria) this;
        }

        public Criteria andDobLessThan(Date value) {
            addCriterionForJDBCDate("dob <", value, "dob");
            return (Criteria) this;
        }

        public Criteria andDobLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dob <=", value, "dob");
            return (Criteria) this;
        }

        public Criteria andDobIn(List<Date> values) {
            addCriterionForJDBCDate("dob in", values, "dob");
            return (Criteria) this;
        }

        public Criteria andDobNotIn(List<Date> values) {
            addCriterionForJDBCDate("dob not in", values, "dob");
            return (Criteria) this;
        }

        public Criteria andDobBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("dob between", value1, value2, "dob");
            return (Criteria) this;
        }

        public Criteria andDobNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("dob not between", value1, value2, "dob");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}