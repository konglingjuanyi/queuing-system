package com.qunar.ops.oaengine.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Formmain0114HistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limit = -1;

    protected int offset = -1;

    public Formmain0114HistoryExample() {
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

        public Criteria andOidIsNull() {
            addCriterion("oid is null");
            return (Criteria) this;
        }

        public Criteria andOidIsNotNull() {
            addCriterion("oid is not null");
            return (Criteria) this;
        }

        public Criteria andOidEqualTo(String value) {
            addCriterion("oid =", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotEqualTo(String value) {
            addCriterion("oid <>", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThan(String value) {
            addCriterion("oid >", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThanOrEqualTo(String value) {
            addCriterion("oid >=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThan(String value) {
            addCriterion("oid <", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThanOrEqualTo(String value) {
            addCriterion("oid <=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLike(String value) {
            addCriterion("oid like", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotLike(String value) {
            addCriterion("oid not like", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidIn(List<String> values) {
            addCriterion("oid in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotIn(List<String> values) {
            addCriterion("oid not in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidBetween(String value1, String value2) {
            addCriterion("oid between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotBetween(String value1, String value2) {
            addCriterion("oid not between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andProcInstIdIsNull() {
            addCriterion("proc_inst_id is null");
            return (Criteria) this;
        }

        public Criteria andProcInstIdIsNotNull() {
            addCriterion("proc_inst_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcInstIdEqualTo(String value) {
            addCriterion("proc_inst_id =", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdNotEqualTo(String value) {
            addCriterion("proc_inst_id <>", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdGreaterThan(String value) {
            addCriterion("proc_inst_id >", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdGreaterThanOrEqualTo(String value) {
            addCriterion("proc_inst_id >=", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdLessThan(String value) {
            addCriterion("proc_inst_id <", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdLessThanOrEqualTo(String value) {
            addCriterion("proc_inst_id <=", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdLike(String value) {
            addCriterion("proc_inst_id like", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdNotLike(String value) {
            addCriterion("proc_inst_id not like", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdIn(List<String> values) {
            addCriterion("proc_inst_id in", values, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdNotIn(List<String> values) {
            addCriterion("proc_inst_id not in", values, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdBetween(String value1, String value2) {
            addCriterion("proc_inst_id between", value1, value2, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdNotBetween(String value1, String value2) {
            addCriterion("proc_inst_id not between", value1, value2, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcTitleIsNull() {
            addCriterion("proc_title is null");
            return (Criteria) this;
        }

        public Criteria andProcTitleIsNotNull() {
            addCriterion("proc_title is not null");
            return (Criteria) this;
        }

        public Criteria andProcTitleEqualTo(String value) {
            addCriterion("proc_title =", value, "procTitle");
            return (Criteria) this;
        }

        public Criteria andProcTitleNotEqualTo(String value) {
            addCriterion("proc_title <>", value, "procTitle");
            return (Criteria) this;
        }

        public Criteria andProcTitleGreaterThan(String value) {
            addCriterion("proc_title >", value, "procTitle");
            return (Criteria) this;
        }

        public Criteria andProcTitleGreaterThanOrEqualTo(String value) {
            addCriterion("proc_title >=", value, "procTitle");
            return (Criteria) this;
        }

        public Criteria andProcTitleLessThan(String value) {
            addCriterion("proc_title <", value, "procTitle");
            return (Criteria) this;
        }

        public Criteria andProcTitleLessThanOrEqualTo(String value) {
            addCriterion("proc_title <=", value, "procTitle");
            return (Criteria) this;
        }

        public Criteria andProcTitleLike(String value) {
            addCriterion("proc_title like", value, "procTitle");
            return (Criteria) this;
        }

        public Criteria andProcTitleNotLike(String value) {
            addCriterion("proc_title not like", value, "procTitle");
            return (Criteria) this;
        }

        public Criteria andProcTitleIn(List<String> values) {
            addCriterion("proc_title in", values, "procTitle");
            return (Criteria) this;
        }

        public Criteria andProcTitleNotIn(List<String> values) {
            addCriterion("proc_title not in", values, "procTitle");
            return (Criteria) this;
        }

        public Criteria andProcTitleBetween(String value1, String value2) {
            addCriterion("proc_title between", value1, value2, "procTitle");
            return (Criteria) this;
        }

        public Criteria andProcTitleNotBetween(String value1, String value2) {
            addCriterion("proc_title not between", value1, value2, "procTitle");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdIsNull() {
            addCriterion("start_member_id is null");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdIsNotNull() {
            addCriterion("start_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdEqualTo(String value) {
            addCriterion("start_member_id =", value, "startMemberId");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdNotEqualTo(String value) {
            addCriterion("start_member_id <>", value, "startMemberId");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdGreaterThan(String value) {
            addCriterion("start_member_id >", value, "startMemberId");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("start_member_id >=", value, "startMemberId");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdLessThan(String value) {
            addCriterion("start_member_id <", value, "startMemberId");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdLessThanOrEqualTo(String value) {
            addCriterion("start_member_id <=", value, "startMemberId");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdLike(String value) {
            addCriterion("start_member_id like", value, "startMemberId");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdNotLike(String value) {
            addCriterion("start_member_id not like", value, "startMemberId");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdIn(List<String> values) {
            addCriterion("start_member_id in", values, "startMemberId");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdNotIn(List<String> values) {
            addCriterion("start_member_id not in", values, "startMemberId");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdBetween(String value1, String value2) {
            addCriterion("start_member_id between", value1, value2, "startMemberId");
            return (Criteria) this;
        }

        public Criteria andStartMemberIdNotBetween(String value1, String value2) {
            addCriterion("start_member_id not between", value1, value2, "startMemberId");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdIsNull() {
            addCriterion("approve_member_id is null");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdIsNotNull() {
            addCriterion("approve_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdEqualTo(String value) {
            addCriterion("approve_member_id =", value, "approveMemberId");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdNotEqualTo(String value) {
            addCriterion("approve_member_id <>", value, "approveMemberId");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdGreaterThan(String value) {
            addCriterion("approve_member_id >", value, "approveMemberId");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("approve_member_id >=", value, "approveMemberId");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdLessThan(String value) {
            addCriterion("approve_member_id <", value, "approveMemberId");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdLessThanOrEqualTo(String value) {
            addCriterion("approve_member_id <=", value, "approveMemberId");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdLike(String value) {
            addCriterion("approve_member_id like", value, "approveMemberId");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdNotLike(String value) {
            addCriterion("approve_member_id not like", value, "approveMemberId");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdIn(List<String> values) {
            addCriterion("approve_member_id in", values, "approveMemberId");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdNotIn(List<String> values) {
            addCriterion("approve_member_id not in", values, "approveMemberId");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdBetween(String value1, String value2) {
            addCriterion("approve_member_id between", value1, value2, "approveMemberId");
            return (Criteria) this;
        }

        public Criteria andApproveMemberIdNotBetween(String value1, String value2) {
            addCriterion("approve_member_id not between", value1, value2, "approveMemberId");
            return (Criteria) this;
        }

        public Criteria andApproveDateIsNull() {
            addCriterion("approve_date is null");
            return (Criteria) this;
        }

        public Criteria andApproveDateIsNotNull() {
            addCriterion("approve_date is not null");
            return (Criteria) this;
        }

        public Criteria andApproveDateEqualTo(Date value) {
            addCriterion("approve_date =", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateNotEqualTo(Date value) {
            addCriterion("approve_date <>", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateGreaterThan(Date value) {
            addCriterion("approve_date >", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateGreaterThanOrEqualTo(Date value) {
            addCriterion("approve_date >=", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateLessThan(Date value) {
            addCriterion("approve_date <", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateLessThanOrEqualTo(Date value) {
            addCriterion("approve_date <=", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateIn(List<Date> values) {
            addCriterion("approve_date in", values, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateNotIn(List<Date> values) {
            addCriterion("approve_date not in", values, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateBetween(Date value1, Date value2) {
            addCriterion("approve_date between", value1, value2, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateNotBetween(Date value1, Date value2) {
            addCriterion("approve_date not between", value1, value2, "approveDate");
            return (Criteria) this;
        }

        public Criteria andFinishedflagIsNull() {
            addCriterion("finishedflag is null");
            return (Criteria) this;
        }

        public Criteria andFinishedflagIsNotNull() {
            addCriterion("finishedflag is not null");
            return (Criteria) this;
        }

        public Criteria andFinishedflagEqualTo(Integer value) {
            addCriterion("finishedflag =", value, "finishedflag");
            return (Criteria) this;
        }

        public Criteria andFinishedflagNotEqualTo(Integer value) {
            addCriterion("finishedflag <>", value, "finishedflag");
            return (Criteria) this;
        }

        public Criteria andFinishedflagGreaterThan(Integer value) {
            addCriterion("finishedflag >", value, "finishedflag");
            return (Criteria) this;
        }

        public Criteria andFinishedflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("finishedflag >=", value, "finishedflag");
            return (Criteria) this;
        }

        public Criteria andFinishedflagLessThan(Integer value) {
            addCriterion("finishedflag <", value, "finishedflag");
            return (Criteria) this;
        }

        public Criteria andFinishedflagLessThanOrEqualTo(Integer value) {
            addCriterion("finishedflag <=", value, "finishedflag");
            return (Criteria) this;
        }

        public Criteria andFinishedflagIn(List<Integer> values) {
            addCriterion("finishedflag in", values, "finishedflag");
            return (Criteria) this;
        }

        public Criteria andFinishedflagNotIn(List<Integer> values) {
            addCriterion("finishedflag not in", values, "finishedflag");
            return (Criteria) this;
        }

        public Criteria andFinishedflagBetween(Integer value1, Integer value2) {
            addCriterion("finishedflag between", value1, value2, "finishedflag");
            return (Criteria) this;
        }

        public Criteria andFinishedflagNotBetween(Integer value1, Integer value2) {
            addCriterion("finishedflag not between", value1, value2, "finishedflag");
            return (Criteria) this;
        }

        public Criteria andRatifyflagIsNull() {
            addCriterion("ratifyflag is null");
            return (Criteria) this;
        }

        public Criteria andRatifyflagIsNotNull() {
            addCriterion("ratifyflag is not null");
            return (Criteria) this;
        }

        public Criteria andRatifyflagEqualTo(Integer value) {
            addCriterion("ratifyflag =", value, "ratifyflag");
            return (Criteria) this;
        }

        public Criteria andRatifyflagNotEqualTo(Integer value) {
            addCriterion("ratifyflag <>", value, "ratifyflag");
            return (Criteria) this;
        }

        public Criteria andRatifyflagGreaterThan(Integer value) {
            addCriterion("ratifyflag >", value, "ratifyflag");
            return (Criteria) this;
        }

        public Criteria andRatifyflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("ratifyflag >=", value, "ratifyflag");
            return (Criteria) this;
        }

        public Criteria andRatifyflagLessThan(Integer value) {
            addCriterion("ratifyflag <", value, "ratifyflag");
            return (Criteria) this;
        }

        public Criteria andRatifyflagLessThanOrEqualTo(Integer value) {
            addCriterion("ratifyflag <=", value, "ratifyflag");
            return (Criteria) this;
        }

        public Criteria andRatifyflagIn(List<Integer> values) {
            addCriterion("ratifyflag in", values, "ratifyflag");
            return (Criteria) this;
        }

        public Criteria andRatifyflagNotIn(List<Integer> values) {
            addCriterion("ratifyflag not in", values, "ratifyflag");
            return (Criteria) this;
        }

        public Criteria andRatifyflagBetween(Integer value1, Integer value2) {
            addCriterion("ratifyflag between", value1, value2, "ratifyflag");
            return (Criteria) this;
        }

        public Criteria andRatifyflagNotBetween(Integer value1, Integer value2) {
            addCriterion("ratifyflag not between", value1, value2, "ratifyflag");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdIsNull() {
            addCriterion("ratify_member_id is null");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdIsNotNull() {
            addCriterion("ratify_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdEqualTo(String value) {
            addCriterion("ratify_member_id =", value, "ratifyMemberId");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdNotEqualTo(String value) {
            addCriterion("ratify_member_id <>", value, "ratifyMemberId");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdGreaterThan(String value) {
            addCriterion("ratify_member_id >", value, "ratifyMemberId");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("ratify_member_id >=", value, "ratifyMemberId");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdLessThan(String value) {
            addCriterion("ratify_member_id <", value, "ratifyMemberId");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdLessThanOrEqualTo(String value) {
            addCriterion("ratify_member_id <=", value, "ratifyMemberId");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdLike(String value) {
            addCriterion("ratify_member_id like", value, "ratifyMemberId");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdNotLike(String value) {
            addCriterion("ratify_member_id not like", value, "ratifyMemberId");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdIn(List<String> values) {
            addCriterion("ratify_member_id in", values, "ratifyMemberId");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdNotIn(List<String> values) {
            addCriterion("ratify_member_id not in", values, "ratifyMemberId");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdBetween(String value1, String value2) {
            addCriterion("ratify_member_id between", value1, value2, "ratifyMemberId");
            return (Criteria) this;
        }

        public Criteria andRatifyMemberIdNotBetween(String value1, String value2) {
            addCriterion("ratify_member_id not between", value1, value2, "ratifyMemberId");
            return (Criteria) this;
        }

        public Criteria andRatifyDateIsNull() {
            addCriterion("ratify_date is null");
            return (Criteria) this;
        }

        public Criteria andRatifyDateIsNotNull() {
            addCriterion("ratify_date is not null");
            return (Criteria) this;
        }

        public Criteria andRatifyDateEqualTo(Date value) {
            addCriterion("ratify_date =", value, "ratifyDate");
            return (Criteria) this;
        }

        public Criteria andRatifyDateNotEqualTo(Date value) {
            addCriterion("ratify_date <>", value, "ratifyDate");
            return (Criteria) this;
        }

        public Criteria andRatifyDateGreaterThan(Date value) {
            addCriterion("ratify_date >", value, "ratifyDate");
            return (Criteria) this;
        }

        public Criteria andRatifyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("ratify_date >=", value, "ratifyDate");
            return (Criteria) this;
        }

        public Criteria andRatifyDateLessThan(Date value) {
            addCriterion("ratify_date <", value, "ratifyDate");
            return (Criteria) this;
        }

        public Criteria andRatifyDateLessThanOrEqualTo(Date value) {
            addCriterion("ratify_date <=", value, "ratifyDate");
            return (Criteria) this;
        }

        public Criteria andRatifyDateIn(List<Date> values) {
            addCriterion("ratify_date in", values, "ratifyDate");
            return (Criteria) this;
        }

        public Criteria andRatifyDateNotIn(List<Date> values) {
            addCriterion("ratify_date not in", values, "ratifyDate");
            return (Criteria) this;
        }

        public Criteria andRatifyDateBetween(Date value1, Date value2) {
            addCriterion("ratify_date between", value1, value2, "ratifyDate");
            return (Criteria) this;
        }

        public Criteria andRatifyDateNotBetween(Date value1, Date value2) {
            addCriterion("ratify_date not between", value1, value2, "ratifyDate");
            return (Criteria) this;
        }

        public Criteria andField0001IsNull() {
            addCriterion("field0001 is null");
            return (Criteria) this;
        }

        public Criteria andField0001IsNotNull() {
            addCriterion("field0001 is not null");
            return (Criteria) this;
        }

        public Criteria andField0001EqualTo(String value) {
            addCriterion("field0001 =", value, "field0001");
            return (Criteria) this;
        }

        public Criteria andField0001NotEqualTo(String value) {
            addCriterion("field0001 <>", value, "field0001");
            return (Criteria) this;
        }

        public Criteria andField0001GreaterThan(String value) {
            addCriterion("field0001 >", value, "field0001");
            return (Criteria) this;
        }

        public Criteria andField0001GreaterThanOrEqualTo(String value) {
            addCriterion("field0001 >=", value, "field0001");
            return (Criteria) this;
        }

        public Criteria andField0001LessThan(String value) {
            addCriterion("field0001 <", value, "field0001");
            return (Criteria) this;
        }

        public Criteria andField0001LessThanOrEqualTo(String value) {
            addCriterion("field0001 <=", value, "field0001");
            return (Criteria) this;
        }

        public Criteria andField0001Like(String value) {
            addCriterion("field0001 like", value, "field0001");
            return (Criteria) this;
        }

        public Criteria andField0001NotLike(String value) {
            addCriterion("field0001 not like", value, "field0001");
            return (Criteria) this;
        }

        public Criteria andField0001In(List<String> values) {
            addCriterion("field0001 in", values, "field0001");
            return (Criteria) this;
        }

        public Criteria andField0001NotIn(List<String> values) {
            addCriterion("field0001 not in", values, "field0001");
            return (Criteria) this;
        }

        public Criteria andField0001Between(String value1, String value2) {
            addCriterion("field0001 between", value1, value2, "field0001");
            return (Criteria) this;
        }

        public Criteria andField0001NotBetween(String value1, String value2) {
            addCriterion("field0001 not between", value1, value2, "field0001");
            return (Criteria) this;
        }

        public Criteria andField0002IsNull() {
            addCriterion("field0002 is null");
            return (Criteria) this;
        }

        public Criteria andField0002IsNotNull() {
            addCriterion("field0002 is not null");
            return (Criteria) this;
        }

        public Criteria andField0002EqualTo(String value) {
            addCriterion("field0002 =", value, "field0002");
            return (Criteria) this;
        }

        public Criteria andField0002NotEqualTo(String value) {
            addCriterion("field0002 <>", value, "field0002");
            return (Criteria) this;
        }

        public Criteria andField0002GreaterThan(String value) {
            addCriterion("field0002 >", value, "field0002");
            return (Criteria) this;
        }

        public Criteria andField0002GreaterThanOrEqualTo(String value) {
            addCriterion("field0002 >=", value, "field0002");
            return (Criteria) this;
        }

        public Criteria andField0002LessThan(String value) {
            addCriterion("field0002 <", value, "field0002");
            return (Criteria) this;
        }

        public Criteria andField0002LessThanOrEqualTo(String value) {
            addCriterion("field0002 <=", value, "field0002");
            return (Criteria) this;
        }

        public Criteria andField0002Like(String value) {
            addCriterion("field0002 like", value, "field0002");
            return (Criteria) this;
        }

        public Criteria andField0002NotLike(String value) {
            addCriterion("field0002 not like", value, "field0002");
            return (Criteria) this;
        }

        public Criteria andField0002In(List<String> values) {
            addCriterion("field0002 in", values, "field0002");
            return (Criteria) this;
        }

        public Criteria andField0002NotIn(List<String> values) {
            addCriterion("field0002 not in", values, "field0002");
            return (Criteria) this;
        }

        public Criteria andField0002Between(String value1, String value2) {
            addCriterion("field0002 between", value1, value2, "field0002");
            return (Criteria) this;
        }

        public Criteria andField0002NotBetween(String value1, String value2) {
            addCriterion("field0002 not between", value1, value2, "field0002");
            return (Criteria) this;
        }

        public Criteria andField0003IsNull() {
            addCriterion("field0003 is null");
            return (Criteria) this;
        }

        public Criteria andField0003IsNotNull() {
            addCriterion("field0003 is not null");
            return (Criteria) this;
        }

        public Criteria andField0003EqualTo(String value) {
            addCriterion("field0003 =", value, "field0003");
            return (Criteria) this;
        }

        public Criteria andField0003NotEqualTo(String value) {
            addCriterion("field0003 <>", value, "field0003");
            return (Criteria) this;
        }

        public Criteria andField0003GreaterThan(String value) {
            addCriterion("field0003 >", value, "field0003");
            return (Criteria) this;
        }

        public Criteria andField0003GreaterThanOrEqualTo(String value) {
            addCriterion("field0003 >=", value, "field0003");
            return (Criteria) this;
        }

        public Criteria andField0003LessThan(String value) {
            addCriterion("field0003 <", value, "field0003");
            return (Criteria) this;
        }

        public Criteria andField0003LessThanOrEqualTo(String value) {
            addCriterion("field0003 <=", value, "field0003");
            return (Criteria) this;
        }

        public Criteria andField0003Like(String value) {
            addCriterion("field0003 like", value, "field0003");
            return (Criteria) this;
        }

        public Criteria andField0003NotLike(String value) {
            addCriterion("field0003 not like", value, "field0003");
            return (Criteria) this;
        }

        public Criteria andField0003In(List<String> values) {
            addCriterion("field0003 in", values, "field0003");
            return (Criteria) this;
        }

        public Criteria andField0003NotIn(List<String> values) {
            addCriterion("field0003 not in", values, "field0003");
            return (Criteria) this;
        }

        public Criteria andField0003Between(String value1, String value2) {
            addCriterion("field0003 between", value1, value2, "field0003");
            return (Criteria) this;
        }

        public Criteria andField0003NotBetween(String value1, String value2) {
            addCriterion("field0003 not between", value1, value2, "field0003");
            return (Criteria) this;
        }

        public Criteria andField0004IsNull() {
            addCriterion("field0004 is null");
            return (Criteria) this;
        }

        public Criteria andField0004IsNotNull() {
            addCriterion("field0004 is not null");
            return (Criteria) this;
        }

        public Criteria andField0004EqualTo(String value) {
            addCriterion("field0004 =", value, "field0004");
            return (Criteria) this;
        }

        public Criteria andField0004NotEqualTo(String value) {
            addCriterion("field0004 <>", value, "field0004");
            return (Criteria) this;
        }

        public Criteria andField0004GreaterThan(String value) {
            addCriterion("field0004 >", value, "field0004");
            return (Criteria) this;
        }

        public Criteria andField0004GreaterThanOrEqualTo(String value) {
            addCriterion("field0004 >=", value, "field0004");
            return (Criteria) this;
        }

        public Criteria andField0004LessThan(String value) {
            addCriterion("field0004 <", value, "field0004");
            return (Criteria) this;
        }

        public Criteria andField0004LessThanOrEqualTo(String value) {
            addCriterion("field0004 <=", value, "field0004");
            return (Criteria) this;
        }

        public Criteria andField0004Like(String value) {
            addCriterion("field0004 like", value, "field0004");
            return (Criteria) this;
        }

        public Criteria andField0004NotLike(String value) {
            addCriterion("field0004 not like", value, "field0004");
            return (Criteria) this;
        }

        public Criteria andField0004In(List<String> values) {
            addCriterion("field0004 in", values, "field0004");
            return (Criteria) this;
        }

        public Criteria andField0004NotIn(List<String> values) {
            addCriterion("field0004 not in", values, "field0004");
            return (Criteria) this;
        }

        public Criteria andField0004Between(String value1, String value2) {
            addCriterion("field0004 between", value1, value2, "field0004");
            return (Criteria) this;
        }

        public Criteria andField0004NotBetween(String value1, String value2) {
            addCriterion("field0004 not between", value1, value2, "field0004");
            return (Criteria) this;
        }

        public Criteria andField0005IsNull() {
            addCriterion("field0005 is null");
            return (Criteria) this;
        }

        public Criteria andField0005IsNotNull() {
            addCriterion("field0005 is not null");
            return (Criteria) this;
        }

        public Criteria andField0005EqualTo(Date value) {
            addCriterionForJDBCDate("field0005 =", value, "field0005");
            return (Criteria) this;
        }

        public Criteria andField0005NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0005 <>", value, "field0005");
            return (Criteria) this;
        }

        public Criteria andField0005GreaterThan(Date value) {
            addCriterionForJDBCDate("field0005 >", value, "field0005");
            return (Criteria) this;
        }

        public Criteria andField0005GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0005 >=", value, "field0005");
            return (Criteria) this;
        }

        public Criteria andField0005LessThan(Date value) {
            addCriterionForJDBCDate("field0005 <", value, "field0005");
            return (Criteria) this;
        }

        public Criteria andField0005LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0005 <=", value, "field0005");
            return (Criteria) this;
        }

        public Criteria andField0005In(List<Date> values) {
            addCriterionForJDBCDate("field0005 in", values, "field0005");
            return (Criteria) this;
        }

        public Criteria andField0005NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0005 not in", values, "field0005");
            return (Criteria) this;
        }

        public Criteria andField0005Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0005 between", value1, value2, "field0005");
            return (Criteria) this;
        }

        public Criteria andField0005NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0005 not between", value1, value2, "field0005");
            return (Criteria) this;
        }

        public Criteria andField0006IsNull() {
            addCriterion("field0006 is null");
            return (Criteria) this;
        }

        public Criteria andField0006IsNotNull() {
            addCriterion("field0006 is not null");
            return (Criteria) this;
        }

        public Criteria andField0006EqualTo(String value) {
            addCriterion("field0006 =", value, "field0006");
            return (Criteria) this;
        }

        public Criteria andField0006NotEqualTo(String value) {
            addCriterion("field0006 <>", value, "field0006");
            return (Criteria) this;
        }

        public Criteria andField0006GreaterThan(String value) {
            addCriterion("field0006 >", value, "field0006");
            return (Criteria) this;
        }

        public Criteria andField0006GreaterThanOrEqualTo(String value) {
            addCriterion("field0006 >=", value, "field0006");
            return (Criteria) this;
        }

        public Criteria andField0006LessThan(String value) {
            addCriterion("field0006 <", value, "field0006");
            return (Criteria) this;
        }

        public Criteria andField0006LessThanOrEqualTo(String value) {
            addCriterion("field0006 <=", value, "field0006");
            return (Criteria) this;
        }

        public Criteria andField0006Like(String value) {
            addCriterion("field0006 like", value, "field0006");
            return (Criteria) this;
        }

        public Criteria andField0006NotLike(String value) {
            addCriterion("field0006 not like", value, "field0006");
            return (Criteria) this;
        }

        public Criteria andField0006In(List<String> values) {
            addCriterion("field0006 in", values, "field0006");
            return (Criteria) this;
        }

        public Criteria andField0006NotIn(List<String> values) {
            addCriterion("field0006 not in", values, "field0006");
            return (Criteria) this;
        }

        public Criteria andField0006Between(String value1, String value2) {
            addCriterion("field0006 between", value1, value2, "field0006");
            return (Criteria) this;
        }

        public Criteria andField0006NotBetween(String value1, String value2) {
            addCriterion("field0006 not between", value1, value2, "field0006");
            return (Criteria) this;
        }

        public Criteria andField0007IsNull() {
            addCriterion("field0007 is null");
            return (Criteria) this;
        }

        public Criteria andField0007IsNotNull() {
            addCriterion("field0007 is not null");
            return (Criteria) this;
        }

        public Criteria andField0007EqualTo(String value) {
            addCriterion("field0007 =", value, "field0007");
            return (Criteria) this;
        }

        public Criteria andField0007NotEqualTo(String value) {
            addCriterion("field0007 <>", value, "field0007");
            return (Criteria) this;
        }

        public Criteria andField0007GreaterThan(String value) {
            addCriterion("field0007 >", value, "field0007");
            return (Criteria) this;
        }

        public Criteria andField0007GreaterThanOrEqualTo(String value) {
            addCriterion("field0007 >=", value, "field0007");
            return (Criteria) this;
        }

        public Criteria andField0007LessThan(String value) {
            addCriterion("field0007 <", value, "field0007");
            return (Criteria) this;
        }

        public Criteria andField0007LessThanOrEqualTo(String value) {
            addCriterion("field0007 <=", value, "field0007");
            return (Criteria) this;
        }

        public Criteria andField0007Like(String value) {
            addCriterion("field0007 like", value, "field0007");
            return (Criteria) this;
        }

        public Criteria andField0007NotLike(String value) {
            addCriterion("field0007 not like", value, "field0007");
            return (Criteria) this;
        }

        public Criteria andField0007In(List<String> values) {
            addCriterion("field0007 in", values, "field0007");
            return (Criteria) this;
        }

        public Criteria andField0007NotIn(List<String> values) {
            addCriterion("field0007 not in", values, "field0007");
            return (Criteria) this;
        }

        public Criteria andField0007Between(String value1, String value2) {
            addCriterion("field0007 between", value1, value2, "field0007");
            return (Criteria) this;
        }

        public Criteria andField0007NotBetween(String value1, String value2) {
            addCriterion("field0007 not between", value1, value2, "field0007");
            return (Criteria) this;
        }

        public Criteria andField0008IsNull() {
            addCriterion("field0008 is null");
            return (Criteria) this;
        }

        public Criteria andField0008IsNotNull() {
            addCriterion("field0008 is not null");
            return (Criteria) this;
        }

        public Criteria andField0008EqualTo(String value) {
            addCriterion("field0008 =", value, "field0008");
            return (Criteria) this;
        }

        public Criteria andField0008NotEqualTo(String value) {
            addCriterion("field0008 <>", value, "field0008");
            return (Criteria) this;
        }

        public Criteria andField0008GreaterThan(String value) {
            addCriterion("field0008 >", value, "field0008");
            return (Criteria) this;
        }

        public Criteria andField0008GreaterThanOrEqualTo(String value) {
            addCriterion("field0008 >=", value, "field0008");
            return (Criteria) this;
        }

        public Criteria andField0008LessThan(String value) {
            addCriterion("field0008 <", value, "field0008");
            return (Criteria) this;
        }

        public Criteria andField0008LessThanOrEqualTo(String value) {
            addCriterion("field0008 <=", value, "field0008");
            return (Criteria) this;
        }

        public Criteria andField0008Like(String value) {
            addCriterion("field0008 like", value, "field0008");
            return (Criteria) this;
        }

        public Criteria andField0008NotLike(String value) {
            addCriterion("field0008 not like", value, "field0008");
            return (Criteria) this;
        }

        public Criteria andField0008In(List<String> values) {
            addCriterion("field0008 in", values, "field0008");
            return (Criteria) this;
        }

        public Criteria andField0008NotIn(List<String> values) {
            addCriterion("field0008 not in", values, "field0008");
            return (Criteria) this;
        }

        public Criteria andField0008Between(String value1, String value2) {
            addCriterion("field0008 between", value1, value2, "field0008");
            return (Criteria) this;
        }

        public Criteria andField0008NotBetween(String value1, String value2) {
            addCriterion("field0008 not between", value1, value2, "field0008");
            return (Criteria) this;
        }

        public Criteria andField0009IsNull() {
            addCriterion("field0009 is null");
            return (Criteria) this;
        }

        public Criteria andField0009IsNotNull() {
            addCriterion("field0009 is not null");
            return (Criteria) this;
        }

        public Criteria andField0009EqualTo(String value) {
            addCriterion("field0009 =", value, "field0009");
            return (Criteria) this;
        }

        public Criteria andField0009NotEqualTo(String value) {
            addCriterion("field0009 <>", value, "field0009");
            return (Criteria) this;
        }

        public Criteria andField0009GreaterThan(String value) {
            addCriterion("field0009 >", value, "field0009");
            return (Criteria) this;
        }

        public Criteria andField0009GreaterThanOrEqualTo(String value) {
            addCriterion("field0009 >=", value, "field0009");
            return (Criteria) this;
        }

        public Criteria andField0009LessThan(String value) {
            addCriterion("field0009 <", value, "field0009");
            return (Criteria) this;
        }

        public Criteria andField0009LessThanOrEqualTo(String value) {
            addCriterion("field0009 <=", value, "field0009");
            return (Criteria) this;
        }

        public Criteria andField0009Like(String value) {
            addCriterion("field0009 like", value, "field0009");
            return (Criteria) this;
        }

        public Criteria andField0009NotLike(String value) {
            addCriterion("field0009 not like", value, "field0009");
            return (Criteria) this;
        }

        public Criteria andField0009In(List<String> values) {
            addCriterion("field0009 in", values, "field0009");
            return (Criteria) this;
        }

        public Criteria andField0009NotIn(List<String> values) {
            addCriterion("field0009 not in", values, "field0009");
            return (Criteria) this;
        }

        public Criteria andField0009Between(String value1, String value2) {
            addCriterion("field0009 between", value1, value2, "field0009");
            return (Criteria) this;
        }

        public Criteria andField0009NotBetween(String value1, String value2) {
            addCriterion("field0009 not between", value1, value2, "field0009");
            return (Criteria) this;
        }

        public Criteria andField0010IsNull() {
            addCriterion("field0010 is null");
            return (Criteria) this;
        }

        public Criteria andField0010IsNotNull() {
            addCriterion("field0010 is not null");
            return (Criteria) this;
        }

        public Criteria andField0010EqualTo(Long value) {
            addCriterion("field0010 =", value, "field0010");
            return (Criteria) this;
        }

        public Criteria andField0010NotEqualTo(Long value) {
            addCriterion("field0010 <>", value, "field0010");
            return (Criteria) this;
        }

        public Criteria andField0010GreaterThan(Long value) {
            addCriterion("field0010 >", value, "field0010");
            return (Criteria) this;
        }

        public Criteria andField0010GreaterThanOrEqualTo(Long value) {
            addCriterion("field0010 >=", value, "field0010");
            return (Criteria) this;
        }

        public Criteria andField0010LessThan(Long value) {
            addCriterion("field0010 <", value, "field0010");
            return (Criteria) this;
        }

        public Criteria andField0010LessThanOrEqualTo(Long value) {
            addCriterion("field0010 <=", value, "field0010");
            return (Criteria) this;
        }

        public Criteria andField0010In(List<Long> values) {
            addCriterion("field0010 in", values, "field0010");
            return (Criteria) this;
        }

        public Criteria andField0010NotIn(List<Long> values) {
            addCriterion("field0010 not in", values, "field0010");
            return (Criteria) this;
        }

        public Criteria andField0010Between(Long value1, Long value2) {
            addCriterion("field0010 between", value1, value2, "field0010");
            return (Criteria) this;
        }

        public Criteria andField0010NotBetween(Long value1, Long value2) {
            addCriterion("field0010 not between", value1, value2, "field0010");
            return (Criteria) this;
        }

        public Criteria andField0011IsNull() {
            addCriterion("field0011 is null");
            return (Criteria) this;
        }

        public Criteria andField0011IsNotNull() {
            addCriterion("field0011 is not null");
            return (Criteria) this;
        }

        public Criteria andField0011EqualTo(Long value) {
            addCriterion("field0011 =", value, "field0011");
            return (Criteria) this;
        }

        public Criteria andField0011NotEqualTo(Long value) {
            addCriterion("field0011 <>", value, "field0011");
            return (Criteria) this;
        }

        public Criteria andField0011GreaterThan(Long value) {
            addCriterion("field0011 >", value, "field0011");
            return (Criteria) this;
        }

        public Criteria andField0011GreaterThanOrEqualTo(Long value) {
            addCriterion("field0011 >=", value, "field0011");
            return (Criteria) this;
        }

        public Criteria andField0011LessThan(Long value) {
            addCriterion("field0011 <", value, "field0011");
            return (Criteria) this;
        }

        public Criteria andField0011LessThanOrEqualTo(Long value) {
            addCriterion("field0011 <=", value, "field0011");
            return (Criteria) this;
        }

        public Criteria andField0011In(List<Long> values) {
            addCriterion("field0011 in", values, "field0011");
            return (Criteria) this;
        }

        public Criteria andField0011NotIn(List<Long> values) {
            addCriterion("field0011 not in", values, "field0011");
            return (Criteria) this;
        }

        public Criteria andField0011Between(Long value1, Long value2) {
            addCriterion("field0011 between", value1, value2, "field0011");
            return (Criteria) this;
        }

        public Criteria andField0011NotBetween(Long value1, Long value2) {
            addCriterion("field0011 not between", value1, value2, "field0011");
            return (Criteria) this;
        }

        public Criteria andField0012IsNull() {
            addCriterion("field0012 is null");
            return (Criteria) this;
        }

        public Criteria andField0012IsNotNull() {
            addCriterion("field0012 is not null");
            return (Criteria) this;
        }

        public Criteria andField0012EqualTo(String value) {
            addCriterion("field0012 =", value, "field0012");
            return (Criteria) this;
        }

        public Criteria andField0012NotEqualTo(String value) {
            addCriterion("field0012 <>", value, "field0012");
            return (Criteria) this;
        }

        public Criteria andField0012GreaterThan(String value) {
            addCriterion("field0012 >", value, "field0012");
            return (Criteria) this;
        }

        public Criteria andField0012GreaterThanOrEqualTo(String value) {
            addCriterion("field0012 >=", value, "field0012");
            return (Criteria) this;
        }

        public Criteria andField0012LessThan(String value) {
            addCriterion("field0012 <", value, "field0012");
            return (Criteria) this;
        }

        public Criteria andField0012LessThanOrEqualTo(String value) {
            addCriterion("field0012 <=", value, "field0012");
            return (Criteria) this;
        }

        public Criteria andField0012Like(String value) {
            addCriterion("field0012 like", value, "field0012");
            return (Criteria) this;
        }

        public Criteria andField0012NotLike(String value) {
            addCriterion("field0012 not like", value, "field0012");
            return (Criteria) this;
        }

        public Criteria andField0012In(List<String> values) {
            addCriterion("field0012 in", values, "field0012");
            return (Criteria) this;
        }

        public Criteria andField0012NotIn(List<String> values) {
            addCriterion("field0012 not in", values, "field0012");
            return (Criteria) this;
        }

        public Criteria andField0012Between(String value1, String value2) {
            addCriterion("field0012 between", value1, value2, "field0012");
            return (Criteria) this;
        }

        public Criteria andField0012NotBetween(String value1, String value2) {
            addCriterion("field0012 not between", value1, value2, "field0012");
            return (Criteria) this;
        }

        public Criteria andField0013IsNull() {
            addCriterion("field0013 is null");
            return (Criteria) this;
        }

        public Criteria andField0013IsNotNull() {
            addCriterion("field0013 is not null");
            return (Criteria) this;
        }

        public Criteria andField0013EqualTo(String value) {
            addCriterion("field0013 =", value, "field0013");
            return (Criteria) this;
        }

        public Criteria andField0013NotEqualTo(String value) {
            addCriterion("field0013 <>", value, "field0013");
            return (Criteria) this;
        }

        public Criteria andField0013GreaterThan(String value) {
            addCriterion("field0013 >", value, "field0013");
            return (Criteria) this;
        }

        public Criteria andField0013GreaterThanOrEqualTo(String value) {
            addCriterion("field0013 >=", value, "field0013");
            return (Criteria) this;
        }

        public Criteria andField0013LessThan(String value) {
            addCriterion("field0013 <", value, "field0013");
            return (Criteria) this;
        }

        public Criteria andField0013LessThanOrEqualTo(String value) {
            addCriterion("field0013 <=", value, "field0013");
            return (Criteria) this;
        }

        public Criteria andField0013Like(String value) {
            addCriterion("field0013 like", value, "field0013");
            return (Criteria) this;
        }

        public Criteria andField0013NotLike(String value) {
            addCriterion("field0013 not like", value, "field0013");
            return (Criteria) this;
        }

        public Criteria andField0013In(List<String> values) {
            addCriterion("field0013 in", values, "field0013");
            return (Criteria) this;
        }

        public Criteria andField0013NotIn(List<String> values) {
            addCriterion("field0013 not in", values, "field0013");
            return (Criteria) this;
        }

        public Criteria andField0013Between(String value1, String value2) {
            addCriterion("field0013 between", value1, value2, "field0013");
            return (Criteria) this;
        }

        public Criteria andField0013NotBetween(String value1, String value2) {
            addCriterion("field0013 not between", value1, value2, "field0013");
            return (Criteria) this;
        }

        public Criteria andField0014IsNull() {
            addCriterion("field0014 is null");
            return (Criteria) this;
        }

        public Criteria andField0014IsNotNull() {
            addCriterion("field0014 is not null");
            return (Criteria) this;
        }

        public Criteria andField0014EqualTo(Date value) {
            addCriterionForJDBCDate("field0014 =", value, "field0014");
            return (Criteria) this;
        }

        public Criteria andField0014NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0014 <>", value, "field0014");
            return (Criteria) this;
        }

        public Criteria andField0014GreaterThan(Date value) {
            addCriterionForJDBCDate("field0014 >", value, "field0014");
            return (Criteria) this;
        }

        public Criteria andField0014GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0014 >=", value, "field0014");
            return (Criteria) this;
        }

        public Criteria andField0014LessThan(Date value) {
            addCriterionForJDBCDate("field0014 <", value, "field0014");
            return (Criteria) this;
        }

        public Criteria andField0014LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0014 <=", value, "field0014");
            return (Criteria) this;
        }

        public Criteria andField0014In(List<Date> values) {
            addCriterionForJDBCDate("field0014 in", values, "field0014");
            return (Criteria) this;
        }

        public Criteria andField0014NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0014 not in", values, "field0014");
            return (Criteria) this;
        }

        public Criteria andField0014Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0014 between", value1, value2, "field0014");
            return (Criteria) this;
        }

        public Criteria andField0014NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0014 not between", value1, value2, "field0014");
            return (Criteria) this;
        }

        public Criteria andField0015IsNull() {
            addCriterion("field0015 is null");
            return (Criteria) this;
        }

        public Criteria andField0015IsNotNull() {
            addCriterion("field0015 is not null");
            return (Criteria) this;
        }

        public Criteria andField0015EqualTo(String value) {
            addCriterion("field0015 =", value, "field0015");
            return (Criteria) this;
        }

        public Criteria andField0015NotEqualTo(String value) {
            addCriterion("field0015 <>", value, "field0015");
            return (Criteria) this;
        }

        public Criteria andField0015GreaterThan(String value) {
            addCriterion("field0015 >", value, "field0015");
            return (Criteria) this;
        }

        public Criteria andField0015GreaterThanOrEqualTo(String value) {
            addCriterion("field0015 >=", value, "field0015");
            return (Criteria) this;
        }

        public Criteria andField0015LessThan(String value) {
            addCriterion("field0015 <", value, "field0015");
            return (Criteria) this;
        }

        public Criteria andField0015LessThanOrEqualTo(String value) {
            addCriterion("field0015 <=", value, "field0015");
            return (Criteria) this;
        }

        public Criteria andField0015Like(String value) {
            addCriterion("field0015 like", value, "field0015");
            return (Criteria) this;
        }

        public Criteria andField0015NotLike(String value) {
            addCriterion("field0015 not like", value, "field0015");
            return (Criteria) this;
        }

        public Criteria andField0015In(List<String> values) {
            addCriterion("field0015 in", values, "field0015");
            return (Criteria) this;
        }

        public Criteria andField0015NotIn(List<String> values) {
            addCriterion("field0015 not in", values, "field0015");
            return (Criteria) this;
        }

        public Criteria andField0015Between(String value1, String value2) {
            addCriterion("field0015 between", value1, value2, "field0015");
            return (Criteria) this;
        }

        public Criteria andField0015NotBetween(String value1, String value2) {
            addCriterion("field0015 not between", value1, value2, "field0015");
            return (Criteria) this;
        }

        public Criteria andField0016IsNull() {
            addCriterion("field0016 is null");
            return (Criteria) this;
        }

        public Criteria andField0016IsNotNull() {
            addCriterion("field0016 is not null");
            return (Criteria) this;
        }

        public Criteria andField0016EqualTo(String value) {
            addCriterion("field0016 =", value, "field0016");
            return (Criteria) this;
        }

        public Criteria andField0016NotEqualTo(String value) {
            addCriterion("field0016 <>", value, "field0016");
            return (Criteria) this;
        }

        public Criteria andField0016GreaterThan(String value) {
            addCriterion("field0016 >", value, "field0016");
            return (Criteria) this;
        }

        public Criteria andField0016GreaterThanOrEqualTo(String value) {
            addCriterion("field0016 >=", value, "field0016");
            return (Criteria) this;
        }

        public Criteria andField0016LessThan(String value) {
            addCriterion("field0016 <", value, "field0016");
            return (Criteria) this;
        }

        public Criteria andField0016LessThanOrEqualTo(String value) {
            addCriterion("field0016 <=", value, "field0016");
            return (Criteria) this;
        }

        public Criteria andField0016Like(String value) {
            addCriterion("field0016 like", value, "field0016");
            return (Criteria) this;
        }

        public Criteria andField0016NotLike(String value) {
            addCriterion("field0016 not like", value, "field0016");
            return (Criteria) this;
        }

        public Criteria andField0016In(List<String> values) {
            addCriterion("field0016 in", values, "field0016");
            return (Criteria) this;
        }

        public Criteria andField0016NotIn(List<String> values) {
            addCriterion("field0016 not in", values, "field0016");
            return (Criteria) this;
        }

        public Criteria andField0016Between(String value1, String value2) {
            addCriterion("field0016 between", value1, value2, "field0016");
            return (Criteria) this;
        }

        public Criteria andField0016NotBetween(String value1, String value2) {
            addCriterion("field0016 not between", value1, value2, "field0016");
            return (Criteria) this;
        }

        public Criteria andField0017IsNull() {
            addCriterion("field0017 is null");
            return (Criteria) this;
        }

        public Criteria andField0017IsNotNull() {
            addCriterion("field0017 is not null");
            return (Criteria) this;
        }

        public Criteria andField0017EqualTo(Date value) {
            addCriterionForJDBCDate("field0017 =", value, "field0017");
            return (Criteria) this;
        }

        public Criteria andField0017NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0017 <>", value, "field0017");
            return (Criteria) this;
        }

        public Criteria andField0017GreaterThan(Date value) {
            addCriterionForJDBCDate("field0017 >", value, "field0017");
            return (Criteria) this;
        }

        public Criteria andField0017GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0017 >=", value, "field0017");
            return (Criteria) this;
        }

        public Criteria andField0017LessThan(Date value) {
            addCriterionForJDBCDate("field0017 <", value, "field0017");
            return (Criteria) this;
        }

        public Criteria andField0017LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0017 <=", value, "field0017");
            return (Criteria) this;
        }

        public Criteria andField0017In(List<Date> values) {
            addCriterionForJDBCDate("field0017 in", values, "field0017");
            return (Criteria) this;
        }

        public Criteria andField0017NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0017 not in", values, "field0017");
            return (Criteria) this;
        }

        public Criteria andField0017Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0017 between", value1, value2, "field0017");
            return (Criteria) this;
        }

        public Criteria andField0017NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0017 not between", value1, value2, "field0017");
            return (Criteria) this;
        }

        public Criteria andField0018IsNull() {
            addCriterion("field0018 is null");
            return (Criteria) this;
        }

        public Criteria andField0018IsNotNull() {
            addCriterion("field0018 is not null");
            return (Criteria) this;
        }

        public Criteria andField0018EqualTo(String value) {
            addCriterion("field0018 =", value, "field0018");
            return (Criteria) this;
        }

        public Criteria andField0018NotEqualTo(String value) {
            addCriterion("field0018 <>", value, "field0018");
            return (Criteria) this;
        }

        public Criteria andField0018GreaterThan(String value) {
            addCriterion("field0018 >", value, "field0018");
            return (Criteria) this;
        }

        public Criteria andField0018GreaterThanOrEqualTo(String value) {
            addCriterion("field0018 >=", value, "field0018");
            return (Criteria) this;
        }

        public Criteria andField0018LessThan(String value) {
            addCriterion("field0018 <", value, "field0018");
            return (Criteria) this;
        }

        public Criteria andField0018LessThanOrEqualTo(String value) {
            addCriterion("field0018 <=", value, "field0018");
            return (Criteria) this;
        }

        public Criteria andField0018Like(String value) {
            addCriterion("field0018 like", value, "field0018");
            return (Criteria) this;
        }

        public Criteria andField0018NotLike(String value) {
            addCriterion("field0018 not like", value, "field0018");
            return (Criteria) this;
        }

        public Criteria andField0018In(List<String> values) {
            addCriterion("field0018 in", values, "field0018");
            return (Criteria) this;
        }

        public Criteria andField0018NotIn(List<String> values) {
            addCriterion("field0018 not in", values, "field0018");
            return (Criteria) this;
        }

        public Criteria andField0018Between(String value1, String value2) {
            addCriterion("field0018 between", value1, value2, "field0018");
            return (Criteria) this;
        }

        public Criteria andField0018NotBetween(String value1, String value2) {
            addCriterion("field0018 not between", value1, value2, "field0018");
            return (Criteria) this;
        }

        public Criteria andField0019IsNull() {
            addCriterion("field0019 is null");
            return (Criteria) this;
        }

        public Criteria andField0019IsNotNull() {
            addCriterion("field0019 is not null");
            return (Criteria) this;
        }

        public Criteria andField0019EqualTo(String value) {
            addCriterion("field0019 =", value, "field0019");
            return (Criteria) this;
        }

        public Criteria andField0019NotEqualTo(String value) {
            addCriterion("field0019 <>", value, "field0019");
            return (Criteria) this;
        }

        public Criteria andField0019GreaterThan(String value) {
            addCriterion("field0019 >", value, "field0019");
            return (Criteria) this;
        }

        public Criteria andField0019GreaterThanOrEqualTo(String value) {
            addCriterion("field0019 >=", value, "field0019");
            return (Criteria) this;
        }

        public Criteria andField0019LessThan(String value) {
            addCriterion("field0019 <", value, "field0019");
            return (Criteria) this;
        }

        public Criteria andField0019LessThanOrEqualTo(String value) {
            addCriterion("field0019 <=", value, "field0019");
            return (Criteria) this;
        }

        public Criteria andField0019Like(String value) {
            addCriterion("field0019 like", value, "field0019");
            return (Criteria) this;
        }

        public Criteria andField0019NotLike(String value) {
            addCriterion("field0019 not like", value, "field0019");
            return (Criteria) this;
        }

        public Criteria andField0019In(List<String> values) {
            addCriterion("field0019 in", values, "field0019");
            return (Criteria) this;
        }

        public Criteria andField0019NotIn(List<String> values) {
            addCriterion("field0019 not in", values, "field0019");
            return (Criteria) this;
        }

        public Criteria andField0019Between(String value1, String value2) {
            addCriterion("field0019 between", value1, value2, "field0019");
            return (Criteria) this;
        }

        public Criteria andField0019NotBetween(String value1, String value2) {
            addCriterion("field0019 not between", value1, value2, "field0019");
            return (Criteria) this;
        }

        public Criteria andField0020IsNull() {
            addCriterion("field0020 is null");
            return (Criteria) this;
        }

        public Criteria andField0020IsNotNull() {
            addCriterion("field0020 is not null");
            return (Criteria) this;
        }

        public Criteria andField0020EqualTo(Date value) {
            addCriterionForJDBCDate("field0020 =", value, "field0020");
            return (Criteria) this;
        }

        public Criteria andField0020NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0020 <>", value, "field0020");
            return (Criteria) this;
        }

        public Criteria andField0020GreaterThan(Date value) {
            addCriterionForJDBCDate("field0020 >", value, "field0020");
            return (Criteria) this;
        }

        public Criteria andField0020GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0020 >=", value, "field0020");
            return (Criteria) this;
        }

        public Criteria andField0020LessThan(Date value) {
            addCriterionForJDBCDate("field0020 <", value, "field0020");
            return (Criteria) this;
        }

        public Criteria andField0020LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0020 <=", value, "field0020");
            return (Criteria) this;
        }

        public Criteria andField0020In(List<Date> values) {
            addCriterionForJDBCDate("field0020 in", values, "field0020");
            return (Criteria) this;
        }

        public Criteria andField0020NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0020 not in", values, "field0020");
            return (Criteria) this;
        }

        public Criteria andField0020Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0020 between", value1, value2, "field0020");
            return (Criteria) this;
        }

        public Criteria andField0020NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0020 not between", value1, value2, "field0020");
            return (Criteria) this;
        }

        public Criteria andField0021IsNull() {
            addCriterion("field0021 is null");
            return (Criteria) this;
        }

        public Criteria andField0021IsNotNull() {
            addCriterion("field0021 is not null");
            return (Criteria) this;
        }

        public Criteria andField0021EqualTo(String value) {
            addCriterion("field0021 =", value, "field0021");
            return (Criteria) this;
        }

        public Criteria andField0021NotEqualTo(String value) {
            addCriterion("field0021 <>", value, "field0021");
            return (Criteria) this;
        }

        public Criteria andField0021GreaterThan(String value) {
            addCriterion("field0021 >", value, "field0021");
            return (Criteria) this;
        }

        public Criteria andField0021GreaterThanOrEqualTo(String value) {
            addCriterion("field0021 >=", value, "field0021");
            return (Criteria) this;
        }

        public Criteria andField0021LessThan(String value) {
            addCriterion("field0021 <", value, "field0021");
            return (Criteria) this;
        }

        public Criteria andField0021LessThanOrEqualTo(String value) {
            addCriterion("field0021 <=", value, "field0021");
            return (Criteria) this;
        }

        public Criteria andField0021Like(String value) {
            addCriterion("field0021 like", value, "field0021");
            return (Criteria) this;
        }

        public Criteria andField0021NotLike(String value) {
            addCriterion("field0021 not like", value, "field0021");
            return (Criteria) this;
        }

        public Criteria andField0021In(List<String> values) {
            addCriterion("field0021 in", values, "field0021");
            return (Criteria) this;
        }

        public Criteria andField0021NotIn(List<String> values) {
            addCriterion("field0021 not in", values, "field0021");
            return (Criteria) this;
        }

        public Criteria andField0021Between(String value1, String value2) {
            addCriterion("field0021 between", value1, value2, "field0021");
            return (Criteria) this;
        }

        public Criteria andField0021NotBetween(String value1, String value2) {
            addCriterion("field0021 not between", value1, value2, "field0021");
            return (Criteria) this;
        }

        public Criteria andField0022IsNull() {
            addCriterion("field0022 is null");
            return (Criteria) this;
        }

        public Criteria andField0022IsNotNull() {
            addCriterion("field0022 is not null");
            return (Criteria) this;
        }

        public Criteria andField0022EqualTo(String value) {
            addCriterion("field0022 =", value, "field0022");
            return (Criteria) this;
        }

        public Criteria andField0022NotEqualTo(String value) {
            addCriterion("field0022 <>", value, "field0022");
            return (Criteria) this;
        }

        public Criteria andField0022GreaterThan(String value) {
            addCriterion("field0022 >", value, "field0022");
            return (Criteria) this;
        }

        public Criteria andField0022GreaterThanOrEqualTo(String value) {
            addCriterion("field0022 >=", value, "field0022");
            return (Criteria) this;
        }

        public Criteria andField0022LessThan(String value) {
            addCriterion("field0022 <", value, "field0022");
            return (Criteria) this;
        }

        public Criteria andField0022LessThanOrEqualTo(String value) {
            addCriterion("field0022 <=", value, "field0022");
            return (Criteria) this;
        }

        public Criteria andField0022Like(String value) {
            addCriterion("field0022 like", value, "field0022");
            return (Criteria) this;
        }

        public Criteria andField0022NotLike(String value) {
            addCriterion("field0022 not like", value, "field0022");
            return (Criteria) this;
        }

        public Criteria andField0022In(List<String> values) {
            addCriterion("field0022 in", values, "field0022");
            return (Criteria) this;
        }

        public Criteria andField0022NotIn(List<String> values) {
            addCriterion("field0022 not in", values, "field0022");
            return (Criteria) this;
        }

        public Criteria andField0022Between(String value1, String value2) {
            addCriterion("field0022 between", value1, value2, "field0022");
            return (Criteria) this;
        }

        public Criteria andField0022NotBetween(String value1, String value2) {
            addCriterion("field0022 not between", value1, value2, "field0022");
            return (Criteria) this;
        }

        public Criteria andField0023IsNull() {
            addCriterion("field0023 is null");
            return (Criteria) this;
        }

        public Criteria andField0023IsNotNull() {
            addCriterion("field0023 is not null");
            return (Criteria) this;
        }

        public Criteria andField0023EqualTo(Date value) {
            addCriterionForJDBCDate("field0023 =", value, "field0023");
            return (Criteria) this;
        }

        public Criteria andField0023NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0023 <>", value, "field0023");
            return (Criteria) this;
        }

        public Criteria andField0023GreaterThan(Date value) {
            addCriterionForJDBCDate("field0023 >", value, "field0023");
            return (Criteria) this;
        }

        public Criteria andField0023GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0023 >=", value, "field0023");
            return (Criteria) this;
        }

        public Criteria andField0023LessThan(Date value) {
            addCriterionForJDBCDate("field0023 <", value, "field0023");
            return (Criteria) this;
        }

        public Criteria andField0023LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0023 <=", value, "field0023");
            return (Criteria) this;
        }

        public Criteria andField0023In(List<Date> values) {
            addCriterionForJDBCDate("field0023 in", values, "field0023");
            return (Criteria) this;
        }

        public Criteria andField0023NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0023 not in", values, "field0023");
            return (Criteria) this;
        }

        public Criteria andField0023Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0023 between", value1, value2, "field0023");
            return (Criteria) this;
        }

        public Criteria andField0023NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0023 not between", value1, value2, "field0023");
            return (Criteria) this;
        }

        public Criteria andField0024IsNull() {
            addCriterion("field0024 is null");
            return (Criteria) this;
        }

        public Criteria andField0024IsNotNull() {
            addCriterion("field0024 is not null");
            return (Criteria) this;
        }

        public Criteria andField0024EqualTo(String value) {
            addCriterion("field0024 =", value, "field0024");
            return (Criteria) this;
        }

        public Criteria andField0024NotEqualTo(String value) {
            addCriterion("field0024 <>", value, "field0024");
            return (Criteria) this;
        }

        public Criteria andField0024GreaterThan(String value) {
            addCriterion("field0024 >", value, "field0024");
            return (Criteria) this;
        }

        public Criteria andField0024GreaterThanOrEqualTo(String value) {
            addCriterion("field0024 >=", value, "field0024");
            return (Criteria) this;
        }

        public Criteria andField0024LessThan(String value) {
            addCriterion("field0024 <", value, "field0024");
            return (Criteria) this;
        }

        public Criteria andField0024LessThanOrEqualTo(String value) {
            addCriterion("field0024 <=", value, "field0024");
            return (Criteria) this;
        }

        public Criteria andField0024Like(String value) {
            addCriterion("field0024 like", value, "field0024");
            return (Criteria) this;
        }

        public Criteria andField0024NotLike(String value) {
            addCriterion("field0024 not like", value, "field0024");
            return (Criteria) this;
        }

        public Criteria andField0024In(List<String> values) {
            addCriterion("field0024 in", values, "field0024");
            return (Criteria) this;
        }

        public Criteria andField0024NotIn(List<String> values) {
            addCriterion("field0024 not in", values, "field0024");
            return (Criteria) this;
        }

        public Criteria andField0024Between(String value1, String value2) {
            addCriterion("field0024 between", value1, value2, "field0024");
            return (Criteria) this;
        }

        public Criteria andField0024NotBetween(String value1, String value2) {
            addCriterion("field0024 not between", value1, value2, "field0024");
            return (Criteria) this;
        }

        public Criteria andField0025IsNull() {
            addCriterion("field0025 is null");
            return (Criteria) this;
        }

        public Criteria andField0025IsNotNull() {
            addCriterion("field0025 is not null");
            return (Criteria) this;
        }

        public Criteria andField0025EqualTo(String value) {
            addCriterion("field0025 =", value, "field0025");
            return (Criteria) this;
        }

        public Criteria andField0025NotEqualTo(String value) {
            addCriterion("field0025 <>", value, "field0025");
            return (Criteria) this;
        }

        public Criteria andField0025GreaterThan(String value) {
            addCriterion("field0025 >", value, "field0025");
            return (Criteria) this;
        }

        public Criteria andField0025GreaterThanOrEqualTo(String value) {
            addCriterion("field0025 >=", value, "field0025");
            return (Criteria) this;
        }

        public Criteria andField0025LessThan(String value) {
            addCriterion("field0025 <", value, "field0025");
            return (Criteria) this;
        }

        public Criteria andField0025LessThanOrEqualTo(String value) {
            addCriterion("field0025 <=", value, "field0025");
            return (Criteria) this;
        }

        public Criteria andField0025Like(String value) {
            addCriterion("field0025 like", value, "field0025");
            return (Criteria) this;
        }

        public Criteria andField0025NotLike(String value) {
            addCriterion("field0025 not like", value, "field0025");
            return (Criteria) this;
        }

        public Criteria andField0025In(List<String> values) {
            addCriterion("field0025 in", values, "field0025");
            return (Criteria) this;
        }

        public Criteria andField0025NotIn(List<String> values) {
            addCriterion("field0025 not in", values, "field0025");
            return (Criteria) this;
        }

        public Criteria andField0025Between(String value1, String value2) {
            addCriterion("field0025 between", value1, value2, "field0025");
            return (Criteria) this;
        }

        public Criteria andField0025NotBetween(String value1, String value2) {
            addCriterion("field0025 not between", value1, value2, "field0025");
            return (Criteria) this;
        }

        public Criteria andField0026IsNull() {
            addCriterion("field0026 is null");
            return (Criteria) this;
        }

        public Criteria andField0026IsNotNull() {
            addCriterion("field0026 is not null");
            return (Criteria) this;
        }

        public Criteria andField0026EqualTo(Date value) {
            addCriterionForJDBCDate("field0026 =", value, "field0026");
            return (Criteria) this;
        }

        public Criteria andField0026NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0026 <>", value, "field0026");
            return (Criteria) this;
        }

        public Criteria andField0026GreaterThan(Date value) {
            addCriterionForJDBCDate("field0026 >", value, "field0026");
            return (Criteria) this;
        }

        public Criteria andField0026GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0026 >=", value, "field0026");
            return (Criteria) this;
        }

        public Criteria andField0026LessThan(Date value) {
            addCriterionForJDBCDate("field0026 <", value, "field0026");
            return (Criteria) this;
        }

        public Criteria andField0026LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0026 <=", value, "field0026");
            return (Criteria) this;
        }

        public Criteria andField0026In(List<Date> values) {
            addCriterionForJDBCDate("field0026 in", values, "field0026");
            return (Criteria) this;
        }

        public Criteria andField0026NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0026 not in", values, "field0026");
            return (Criteria) this;
        }

        public Criteria andField0026Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0026 between", value1, value2, "field0026");
            return (Criteria) this;
        }

        public Criteria andField0026NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0026 not between", value1, value2, "field0026");
            return (Criteria) this;
        }

        public Criteria andField0027IsNull() {
            addCriterion("field0027 is null");
            return (Criteria) this;
        }

        public Criteria andField0027IsNotNull() {
            addCriterion("field0027 is not null");
            return (Criteria) this;
        }

        public Criteria andField0027EqualTo(String value) {
            addCriterion("field0027 =", value, "field0027");
            return (Criteria) this;
        }

        public Criteria andField0027NotEqualTo(String value) {
            addCriterion("field0027 <>", value, "field0027");
            return (Criteria) this;
        }

        public Criteria andField0027GreaterThan(String value) {
            addCriterion("field0027 >", value, "field0027");
            return (Criteria) this;
        }

        public Criteria andField0027GreaterThanOrEqualTo(String value) {
            addCriterion("field0027 >=", value, "field0027");
            return (Criteria) this;
        }

        public Criteria andField0027LessThan(String value) {
            addCriterion("field0027 <", value, "field0027");
            return (Criteria) this;
        }

        public Criteria andField0027LessThanOrEqualTo(String value) {
            addCriterion("field0027 <=", value, "field0027");
            return (Criteria) this;
        }

        public Criteria andField0027Like(String value) {
            addCriterion("field0027 like", value, "field0027");
            return (Criteria) this;
        }

        public Criteria andField0027NotLike(String value) {
            addCriterion("field0027 not like", value, "field0027");
            return (Criteria) this;
        }

        public Criteria andField0027In(List<String> values) {
            addCriterion("field0027 in", values, "field0027");
            return (Criteria) this;
        }

        public Criteria andField0027NotIn(List<String> values) {
            addCriterion("field0027 not in", values, "field0027");
            return (Criteria) this;
        }

        public Criteria andField0027Between(String value1, String value2) {
            addCriterion("field0027 between", value1, value2, "field0027");
            return (Criteria) this;
        }

        public Criteria andField0027NotBetween(String value1, String value2) {
            addCriterion("field0027 not between", value1, value2, "field0027");
            return (Criteria) this;
        }

        public Criteria andField0028IsNull() {
            addCriterion("field0028 is null");
            return (Criteria) this;
        }

        public Criteria andField0028IsNotNull() {
            addCriterion("field0028 is not null");
            return (Criteria) this;
        }

        public Criteria andField0028EqualTo(String value) {
            addCriterion("field0028 =", value, "field0028");
            return (Criteria) this;
        }

        public Criteria andField0028NotEqualTo(String value) {
            addCriterion("field0028 <>", value, "field0028");
            return (Criteria) this;
        }

        public Criteria andField0028GreaterThan(String value) {
            addCriterion("field0028 >", value, "field0028");
            return (Criteria) this;
        }

        public Criteria andField0028GreaterThanOrEqualTo(String value) {
            addCriterion("field0028 >=", value, "field0028");
            return (Criteria) this;
        }

        public Criteria andField0028LessThan(String value) {
            addCriterion("field0028 <", value, "field0028");
            return (Criteria) this;
        }

        public Criteria andField0028LessThanOrEqualTo(String value) {
            addCriterion("field0028 <=", value, "field0028");
            return (Criteria) this;
        }

        public Criteria andField0028Like(String value) {
            addCriterion("field0028 like", value, "field0028");
            return (Criteria) this;
        }

        public Criteria andField0028NotLike(String value) {
            addCriterion("field0028 not like", value, "field0028");
            return (Criteria) this;
        }

        public Criteria andField0028In(List<String> values) {
            addCriterion("field0028 in", values, "field0028");
            return (Criteria) this;
        }

        public Criteria andField0028NotIn(List<String> values) {
            addCriterion("field0028 not in", values, "field0028");
            return (Criteria) this;
        }

        public Criteria andField0028Between(String value1, String value2) {
            addCriterion("field0028 between", value1, value2, "field0028");
            return (Criteria) this;
        }

        public Criteria andField0028NotBetween(String value1, String value2) {
            addCriterion("field0028 not between", value1, value2, "field0028");
            return (Criteria) this;
        }

        public Criteria andField0029IsNull() {
            addCriterion("field0029 is null");
            return (Criteria) this;
        }

        public Criteria andField0029IsNotNull() {
            addCriterion("field0029 is not null");
            return (Criteria) this;
        }

        public Criteria andField0029EqualTo(Date value) {
            addCriterionForJDBCDate("field0029 =", value, "field0029");
            return (Criteria) this;
        }

        public Criteria andField0029NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0029 <>", value, "field0029");
            return (Criteria) this;
        }

        public Criteria andField0029GreaterThan(Date value) {
            addCriterionForJDBCDate("field0029 >", value, "field0029");
            return (Criteria) this;
        }

        public Criteria andField0029GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0029 >=", value, "field0029");
            return (Criteria) this;
        }

        public Criteria andField0029LessThan(Date value) {
            addCriterionForJDBCDate("field0029 <", value, "field0029");
            return (Criteria) this;
        }

        public Criteria andField0029LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0029 <=", value, "field0029");
            return (Criteria) this;
        }

        public Criteria andField0029In(List<Date> values) {
            addCriterionForJDBCDate("field0029 in", values, "field0029");
            return (Criteria) this;
        }

        public Criteria andField0029NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0029 not in", values, "field0029");
            return (Criteria) this;
        }

        public Criteria andField0029Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0029 between", value1, value2, "field0029");
            return (Criteria) this;
        }

        public Criteria andField0029NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0029 not between", value1, value2, "field0029");
            return (Criteria) this;
        }

        public Criteria andField0030IsNull() {
            addCriterion("field0030 is null");
            return (Criteria) this;
        }

        public Criteria andField0030IsNotNull() {
            addCriterion("field0030 is not null");
            return (Criteria) this;
        }

        public Criteria andField0030EqualTo(Long value) {
            addCriterion("field0030 =", value, "field0030");
            return (Criteria) this;
        }

        public Criteria andField0030NotEqualTo(Long value) {
            addCriterion("field0030 <>", value, "field0030");
            return (Criteria) this;
        }

        public Criteria andField0030GreaterThan(Long value) {
            addCriterion("field0030 >", value, "field0030");
            return (Criteria) this;
        }

        public Criteria andField0030GreaterThanOrEqualTo(Long value) {
            addCriterion("field0030 >=", value, "field0030");
            return (Criteria) this;
        }

        public Criteria andField0030LessThan(Long value) {
            addCriterion("field0030 <", value, "field0030");
            return (Criteria) this;
        }

        public Criteria andField0030LessThanOrEqualTo(Long value) {
            addCriterion("field0030 <=", value, "field0030");
            return (Criteria) this;
        }

        public Criteria andField0030In(List<Long> values) {
            addCriterion("field0030 in", values, "field0030");
            return (Criteria) this;
        }

        public Criteria andField0030NotIn(List<Long> values) {
            addCriterion("field0030 not in", values, "field0030");
            return (Criteria) this;
        }

        public Criteria andField0030Between(Long value1, Long value2) {
            addCriterion("field0030 between", value1, value2, "field0030");
            return (Criteria) this;
        }

        public Criteria andField0030NotBetween(Long value1, Long value2) {
            addCriterion("field0030 not between", value1, value2, "field0030");
            return (Criteria) this;
        }

        public Criteria andField0031IsNull() {
            addCriterion("field0031 is null");
            return (Criteria) this;
        }

        public Criteria andField0031IsNotNull() {
            addCriterion("field0031 is not null");
            return (Criteria) this;
        }

        public Criteria andField0031EqualTo(Long value) {
            addCriterion("field0031 =", value, "field0031");
            return (Criteria) this;
        }

        public Criteria andField0031NotEqualTo(Long value) {
            addCriterion("field0031 <>", value, "field0031");
            return (Criteria) this;
        }

        public Criteria andField0031GreaterThan(Long value) {
            addCriterion("field0031 >", value, "field0031");
            return (Criteria) this;
        }

        public Criteria andField0031GreaterThanOrEqualTo(Long value) {
            addCriterion("field0031 >=", value, "field0031");
            return (Criteria) this;
        }

        public Criteria andField0031LessThan(Long value) {
            addCriterion("field0031 <", value, "field0031");
            return (Criteria) this;
        }

        public Criteria andField0031LessThanOrEqualTo(Long value) {
            addCriterion("field0031 <=", value, "field0031");
            return (Criteria) this;
        }

        public Criteria andField0031In(List<Long> values) {
            addCriterion("field0031 in", values, "field0031");
            return (Criteria) this;
        }

        public Criteria andField0031NotIn(List<Long> values) {
            addCriterion("field0031 not in", values, "field0031");
            return (Criteria) this;
        }

        public Criteria andField0031Between(Long value1, Long value2) {
            addCriterion("field0031 between", value1, value2, "field0031");
            return (Criteria) this;
        }

        public Criteria andField0031NotBetween(Long value1, Long value2) {
            addCriterion("field0031 not between", value1, value2, "field0031");
            return (Criteria) this;
        }

        public Criteria andField0032IsNull() {
            addCriterion("field0032 is null");
            return (Criteria) this;
        }

        public Criteria andField0032IsNotNull() {
            addCriterion("field0032 is not null");
            return (Criteria) this;
        }

        public Criteria andField0032EqualTo(Long value) {
            addCriterion("field0032 =", value, "field0032");
            return (Criteria) this;
        }

        public Criteria andField0032NotEqualTo(Long value) {
            addCriterion("field0032 <>", value, "field0032");
            return (Criteria) this;
        }

        public Criteria andField0032GreaterThan(Long value) {
            addCriterion("field0032 >", value, "field0032");
            return (Criteria) this;
        }

        public Criteria andField0032GreaterThanOrEqualTo(Long value) {
            addCriterion("field0032 >=", value, "field0032");
            return (Criteria) this;
        }

        public Criteria andField0032LessThan(Long value) {
            addCriterion("field0032 <", value, "field0032");
            return (Criteria) this;
        }

        public Criteria andField0032LessThanOrEqualTo(Long value) {
            addCriterion("field0032 <=", value, "field0032");
            return (Criteria) this;
        }

        public Criteria andField0032In(List<Long> values) {
            addCriterion("field0032 in", values, "field0032");
            return (Criteria) this;
        }

        public Criteria andField0032NotIn(List<Long> values) {
            addCriterion("field0032 not in", values, "field0032");
            return (Criteria) this;
        }

        public Criteria andField0032Between(Long value1, Long value2) {
            addCriterion("field0032 between", value1, value2, "field0032");
            return (Criteria) this;
        }

        public Criteria andField0032NotBetween(Long value1, Long value2) {
            addCriterion("field0032 not between", value1, value2, "field0032");
            return (Criteria) this;
        }

        public Criteria andField0033IsNull() {
            addCriterion("field0033 is null");
            return (Criteria) this;
        }

        public Criteria andField0033IsNotNull() {
            addCriterion("field0033 is not null");
            return (Criteria) this;
        }

        public Criteria andField0033EqualTo(Long value) {
            addCriterion("field0033 =", value, "field0033");
            return (Criteria) this;
        }

        public Criteria andField0033NotEqualTo(Long value) {
            addCriterion("field0033 <>", value, "field0033");
            return (Criteria) this;
        }

        public Criteria andField0033GreaterThan(Long value) {
            addCriterion("field0033 >", value, "field0033");
            return (Criteria) this;
        }

        public Criteria andField0033GreaterThanOrEqualTo(Long value) {
            addCriterion("field0033 >=", value, "field0033");
            return (Criteria) this;
        }

        public Criteria andField0033LessThan(Long value) {
            addCriterion("field0033 <", value, "field0033");
            return (Criteria) this;
        }

        public Criteria andField0033LessThanOrEqualTo(Long value) {
            addCriterion("field0033 <=", value, "field0033");
            return (Criteria) this;
        }

        public Criteria andField0033In(List<Long> values) {
            addCriterion("field0033 in", values, "field0033");
            return (Criteria) this;
        }

        public Criteria andField0033NotIn(List<Long> values) {
            addCriterion("field0033 not in", values, "field0033");
            return (Criteria) this;
        }

        public Criteria andField0033Between(Long value1, Long value2) {
            addCriterion("field0033 between", value1, value2, "field0033");
            return (Criteria) this;
        }

        public Criteria andField0033NotBetween(Long value1, Long value2) {
            addCriterion("field0033 not between", value1, value2, "field0033");
            return (Criteria) this;
        }

        public Criteria andField0063IsNull() {
            addCriterion("field0063 is null");
            return (Criteria) this;
        }

        public Criteria andField0063IsNotNull() {
            addCriterion("field0063 is not null");
            return (Criteria) this;
        }

        public Criteria andField0063EqualTo(String value) {
            addCriterion("field0063 =", value, "field0063");
            return (Criteria) this;
        }

        public Criteria andField0063NotEqualTo(String value) {
            addCriterion("field0063 <>", value, "field0063");
            return (Criteria) this;
        }

        public Criteria andField0063GreaterThan(String value) {
            addCriterion("field0063 >", value, "field0063");
            return (Criteria) this;
        }

        public Criteria andField0063GreaterThanOrEqualTo(String value) {
            addCriterion("field0063 >=", value, "field0063");
            return (Criteria) this;
        }

        public Criteria andField0063LessThan(String value) {
            addCriterion("field0063 <", value, "field0063");
            return (Criteria) this;
        }

        public Criteria andField0063LessThanOrEqualTo(String value) {
            addCriterion("field0063 <=", value, "field0063");
            return (Criteria) this;
        }

        public Criteria andField0063Like(String value) {
            addCriterion("field0063 like", value, "field0063");
            return (Criteria) this;
        }

        public Criteria andField0063NotLike(String value) {
            addCriterion("field0063 not like", value, "field0063");
            return (Criteria) this;
        }

        public Criteria andField0063In(List<String> values) {
            addCriterion("field0063 in", values, "field0063");
            return (Criteria) this;
        }

        public Criteria andField0063NotIn(List<String> values) {
            addCriterion("field0063 not in", values, "field0063");
            return (Criteria) this;
        }

        public Criteria andField0063Between(String value1, String value2) {
            addCriterion("field0063 between", value1, value2, "field0063");
            return (Criteria) this;
        }

        public Criteria andField0063NotBetween(String value1, String value2) {
            addCriterion("field0063 not between", value1, value2, "field0063");
            return (Criteria) this;
        }

        public Criteria andField0064IsNull() {
            addCriterion("field0064 is null");
            return (Criteria) this;
        }

        public Criteria andField0064IsNotNull() {
            addCriterion("field0064 is not null");
            return (Criteria) this;
        }

        public Criteria andField0064EqualTo(String value) {
            addCriterion("field0064 =", value, "field0064");
            return (Criteria) this;
        }

        public Criteria andField0064NotEqualTo(String value) {
            addCriterion("field0064 <>", value, "field0064");
            return (Criteria) this;
        }

        public Criteria andField0064GreaterThan(String value) {
            addCriterion("field0064 >", value, "field0064");
            return (Criteria) this;
        }

        public Criteria andField0064GreaterThanOrEqualTo(String value) {
            addCriterion("field0064 >=", value, "field0064");
            return (Criteria) this;
        }

        public Criteria andField0064LessThan(String value) {
            addCriterion("field0064 <", value, "field0064");
            return (Criteria) this;
        }

        public Criteria andField0064LessThanOrEqualTo(String value) {
            addCriterion("field0064 <=", value, "field0064");
            return (Criteria) this;
        }

        public Criteria andField0064Like(String value) {
            addCriterion("field0064 like", value, "field0064");
            return (Criteria) this;
        }

        public Criteria andField0064NotLike(String value) {
            addCriterion("field0064 not like", value, "field0064");
            return (Criteria) this;
        }

        public Criteria andField0064In(List<String> values) {
            addCriterion("field0064 in", values, "field0064");
            return (Criteria) this;
        }

        public Criteria andField0064NotIn(List<String> values) {
            addCriterion("field0064 not in", values, "field0064");
            return (Criteria) this;
        }

        public Criteria andField0064Between(String value1, String value2) {
            addCriterion("field0064 between", value1, value2, "field0064");
            return (Criteria) this;
        }

        public Criteria andField0064NotBetween(String value1, String value2) {
            addCriterion("field0064 not between", value1, value2, "field0064");
            return (Criteria) this;
        }

        public Criteria andField0065IsNull() {
            addCriterion("field0065 is null");
            return (Criteria) this;
        }

        public Criteria andField0065IsNotNull() {
            addCriterion("field0065 is not null");
            return (Criteria) this;
        }

        public Criteria andField0065EqualTo(Long value) {
            addCriterion("field0065 =", value, "field0065");
            return (Criteria) this;
        }

        public Criteria andField0065NotEqualTo(Long value) {
            addCriterion("field0065 <>", value, "field0065");
            return (Criteria) this;
        }

        public Criteria andField0065GreaterThan(Long value) {
            addCriterion("field0065 >", value, "field0065");
            return (Criteria) this;
        }

        public Criteria andField0065GreaterThanOrEqualTo(Long value) {
            addCriterion("field0065 >=", value, "field0065");
            return (Criteria) this;
        }

        public Criteria andField0065LessThan(Long value) {
            addCriterion("field0065 <", value, "field0065");
            return (Criteria) this;
        }

        public Criteria andField0065LessThanOrEqualTo(Long value) {
            addCriterion("field0065 <=", value, "field0065");
            return (Criteria) this;
        }

        public Criteria andField0065In(List<Long> values) {
            addCriterion("field0065 in", values, "field0065");
            return (Criteria) this;
        }

        public Criteria andField0065NotIn(List<Long> values) {
            addCriterion("field0065 not in", values, "field0065");
            return (Criteria) this;
        }

        public Criteria andField0065Between(Long value1, Long value2) {
            addCriterion("field0065 between", value1, value2, "field0065");
            return (Criteria) this;
        }

        public Criteria andField0065NotBetween(Long value1, Long value2) {
            addCriterion("field0065 not between", value1, value2, "field0065");
            return (Criteria) this;
        }

        public Criteria andField0066IsNull() {
            addCriterion("field0066 is null");
            return (Criteria) this;
        }

        public Criteria andField0066IsNotNull() {
            addCriterion("field0066 is not null");
            return (Criteria) this;
        }

        public Criteria andField0066EqualTo(Long value) {
            addCriterion("field0066 =", value, "field0066");
            return (Criteria) this;
        }

        public Criteria andField0066NotEqualTo(Long value) {
            addCriterion("field0066 <>", value, "field0066");
            return (Criteria) this;
        }

        public Criteria andField0066GreaterThan(Long value) {
            addCriterion("field0066 >", value, "field0066");
            return (Criteria) this;
        }

        public Criteria andField0066GreaterThanOrEqualTo(Long value) {
            addCriterion("field0066 >=", value, "field0066");
            return (Criteria) this;
        }

        public Criteria andField0066LessThan(Long value) {
            addCriterion("field0066 <", value, "field0066");
            return (Criteria) this;
        }

        public Criteria andField0066LessThanOrEqualTo(Long value) {
            addCriterion("field0066 <=", value, "field0066");
            return (Criteria) this;
        }

        public Criteria andField0066In(List<Long> values) {
            addCriterion("field0066 in", values, "field0066");
            return (Criteria) this;
        }

        public Criteria andField0066NotIn(List<Long> values) {
            addCriterion("field0066 not in", values, "field0066");
            return (Criteria) this;
        }

        public Criteria andField0066Between(Long value1, Long value2) {
            addCriterion("field0066 between", value1, value2, "field0066");
            return (Criteria) this;
        }

        public Criteria andField0066NotBetween(Long value1, Long value2) {
            addCriterion("field0066 not between", value1, value2, "field0066");
            return (Criteria) this;
        }

        public Criteria andField0067IsNull() {
            addCriterion("field0067 is null");
            return (Criteria) this;
        }

        public Criteria andField0067IsNotNull() {
            addCriterion("field0067 is not null");
            return (Criteria) this;
        }

        public Criteria andField0067EqualTo(Long value) {
            addCriterion("field0067 =", value, "field0067");
            return (Criteria) this;
        }

        public Criteria andField0067NotEqualTo(Long value) {
            addCriterion("field0067 <>", value, "field0067");
            return (Criteria) this;
        }

        public Criteria andField0067GreaterThan(Long value) {
            addCriterion("field0067 >", value, "field0067");
            return (Criteria) this;
        }

        public Criteria andField0067GreaterThanOrEqualTo(Long value) {
            addCriterion("field0067 >=", value, "field0067");
            return (Criteria) this;
        }

        public Criteria andField0067LessThan(Long value) {
            addCriterion("field0067 <", value, "field0067");
            return (Criteria) this;
        }

        public Criteria andField0067LessThanOrEqualTo(Long value) {
            addCriterion("field0067 <=", value, "field0067");
            return (Criteria) this;
        }

        public Criteria andField0067In(List<Long> values) {
            addCriterion("field0067 in", values, "field0067");
            return (Criteria) this;
        }

        public Criteria andField0067NotIn(List<Long> values) {
            addCriterion("field0067 not in", values, "field0067");
            return (Criteria) this;
        }

        public Criteria andField0067Between(Long value1, Long value2) {
            addCriterion("field0067 between", value1, value2, "field0067");
            return (Criteria) this;
        }

        public Criteria andField0067NotBetween(Long value1, Long value2) {
            addCriterion("field0067 not between", value1, value2, "field0067");
            return (Criteria) this;
        }

        public Criteria andField0068IsNull() {
            addCriterion("field0068 is null");
            return (Criteria) this;
        }

        public Criteria andField0068IsNotNull() {
            addCriterion("field0068 is not null");
            return (Criteria) this;
        }

        public Criteria andField0068EqualTo(Long value) {
            addCriterion("field0068 =", value, "field0068");
            return (Criteria) this;
        }

        public Criteria andField0068NotEqualTo(Long value) {
            addCriterion("field0068 <>", value, "field0068");
            return (Criteria) this;
        }

        public Criteria andField0068GreaterThan(Long value) {
            addCriterion("field0068 >", value, "field0068");
            return (Criteria) this;
        }

        public Criteria andField0068GreaterThanOrEqualTo(Long value) {
            addCriterion("field0068 >=", value, "field0068");
            return (Criteria) this;
        }

        public Criteria andField0068LessThan(Long value) {
            addCriterion("field0068 <", value, "field0068");
            return (Criteria) this;
        }

        public Criteria andField0068LessThanOrEqualTo(Long value) {
            addCriterion("field0068 <=", value, "field0068");
            return (Criteria) this;
        }

        public Criteria andField0068In(List<Long> values) {
            addCriterion("field0068 in", values, "field0068");
            return (Criteria) this;
        }

        public Criteria andField0068NotIn(List<Long> values) {
            addCriterion("field0068 not in", values, "field0068");
            return (Criteria) this;
        }

        public Criteria andField0068Between(Long value1, Long value2) {
            addCriterion("field0068 between", value1, value2, "field0068");
            return (Criteria) this;
        }

        public Criteria andField0068NotBetween(Long value1, Long value2) {
            addCriterion("field0068 not between", value1, value2, "field0068");
            return (Criteria) this;
        }

        public Criteria andField0069IsNull() {
            addCriterion("field0069 is null");
            return (Criteria) this;
        }

        public Criteria andField0069IsNotNull() {
            addCriterion("field0069 is not null");
            return (Criteria) this;
        }

        public Criteria andField0069EqualTo(Long value) {
            addCriterion("field0069 =", value, "field0069");
            return (Criteria) this;
        }

        public Criteria andField0069NotEqualTo(Long value) {
            addCriterion("field0069 <>", value, "field0069");
            return (Criteria) this;
        }

        public Criteria andField0069GreaterThan(Long value) {
            addCriterion("field0069 >", value, "field0069");
            return (Criteria) this;
        }

        public Criteria andField0069GreaterThanOrEqualTo(Long value) {
            addCriterion("field0069 >=", value, "field0069");
            return (Criteria) this;
        }

        public Criteria andField0069LessThan(Long value) {
            addCriterion("field0069 <", value, "field0069");
            return (Criteria) this;
        }

        public Criteria andField0069LessThanOrEqualTo(Long value) {
            addCriterion("field0069 <=", value, "field0069");
            return (Criteria) this;
        }

        public Criteria andField0069In(List<Long> values) {
            addCriterion("field0069 in", values, "field0069");
            return (Criteria) this;
        }

        public Criteria andField0069NotIn(List<Long> values) {
            addCriterion("field0069 not in", values, "field0069");
            return (Criteria) this;
        }

        public Criteria andField0069Between(Long value1, Long value2) {
            addCriterion("field0069 between", value1, value2, "field0069");
            return (Criteria) this;
        }

        public Criteria andField0069NotBetween(Long value1, Long value2) {
            addCriterion("field0069 not between", value1, value2, "field0069");
            return (Criteria) this;
        }

        public Criteria andField0070IsNull() {
            addCriterion("field0070 is null");
            return (Criteria) this;
        }

        public Criteria andField0070IsNotNull() {
            addCriterion("field0070 is not null");
            return (Criteria) this;
        }

        public Criteria andField0070EqualTo(Long value) {
            addCriterion("field0070 =", value, "field0070");
            return (Criteria) this;
        }

        public Criteria andField0070NotEqualTo(Long value) {
            addCriterion("field0070 <>", value, "field0070");
            return (Criteria) this;
        }

        public Criteria andField0070GreaterThan(Long value) {
            addCriterion("field0070 >", value, "field0070");
            return (Criteria) this;
        }

        public Criteria andField0070GreaterThanOrEqualTo(Long value) {
            addCriterion("field0070 >=", value, "field0070");
            return (Criteria) this;
        }

        public Criteria andField0070LessThan(Long value) {
            addCriterion("field0070 <", value, "field0070");
            return (Criteria) this;
        }

        public Criteria andField0070LessThanOrEqualTo(Long value) {
            addCriterion("field0070 <=", value, "field0070");
            return (Criteria) this;
        }

        public Criteria andField0070In(List<Long> values) {
            addCriterion("field0070 in", values, "field0070");
            return (Criteria) this;
        }

        public Criteria andField0070NotIn(List<Long> values) {
            addCriterion("field0070 not in", values, "field0070");
            return (Criteria) this;
        }

        public Criteria andField0070Between(Long value1, Long value2) {
            addCriterion("field0070 between", value1, value2, "field0070");
            return (Criteria) this;
        }

        public Criteria andField0070NotBetween(Long value1, Long value2) {
            addCriterion("field0070 not between", value1, value2, "field0070");
            return (Criteria) this;
        }

        public Criteria andField0071IsNull() {
            addCriterion("field0071 is null");
            return (Criteria) this;
        }

        public Criteria andField0071IsNotNull() {
            addCriterion("field0071 is not null");
            return (Criteria) this;
        }

        public Criteria andField0071EqualTo(String value) {
            addCriterion("field0071 =", value, "field0071");
            return (Criteria) this;
        }

        public Criteria andField0071NotEqualTo(String value) {
            addCriterion("field0071 <>", value, "field0071");
            return (Criteria) this;
        }

        public Criteria andField0071GreaterThan(String value) {
            addCriterion("field0071 >", value, "field0071");
            return (Criteria) this;
        }

        public Criteria andField0071GreaterThanOrEqualTo(String value) {
            addCriterion("field0071 >=", value, "field0071");
            return (Criteria) this;
        }

        public Criteria andField0071LessThan(String value) {
            addCriterion("field0071 <", value, "field0071");
            return (Criteria) this;
        }

        public Criteria andField0071LessThanOrEqualTo(String value) {
            addCriterion("field0071 <=", value, "field0071");
            return (Criteria) this;
        }

        public Criteria andField0071Like(String value) {
            addCriterion("field0071 like", value, "field0071");
            return (Criteria) this;
        }

        public Criteria andField0071NotLike(String value) {
            addCriterion("field0071 not like", value, "field0071");
            return (Criteria) this;
        }

        public Criteria andField0071In(List<String> values) {
            addCriterion("field0071 in", values, "field0071");
            return (Criteria) this;
        }

        public Criteria andField0071NotIn(List<String> values) {
            addCriterion("field0071 not in", values, "field0071");
            return (Criteria) this;
        }

        public Criteria andField0071Between(String value1, String value2) {
            addCriterion("field0071 between", value1, value2, "field0071");
            return (Criteria) this;
        }

        public Criteria andField0071NotBetween(String value1, String value2) {
            addCriterion("field0071 not between", value1, value2, "field0071");
            return (Criteria) this;
        }

        public Criteria andField0072IsNull() {
            addCriterion("field0072 is null");
            return (Criteria) this;
        }

        public Criteria andField0072IsNotNull() {
            addCriterion("field0072 is not null");
            return (Criteria) this;
        }

        public Criteria andField0072EqualTo(String value) {
            addCriterion("field0072 =", value, "field0072");
            return (Criteria) this;
        }

        public Criteria andField0072NotEqualTo(String value) {
            addCriterion("field0072 <>", value, "field0072");
            return (Criteria) this;
        }

        public Criteria andField0072GreaterThan(String value) {
            addCriterion("field0072 >", value, "field0072");
            return (Criteria) this;
        }

        public Criteria andField0072GreaterThanOrEqualTo(String value) {
            addCriterion("field0072 >=", value, "field0072");
            return (Criteria) this;
        }

        public Criteria andField0072LessThan(String value) {
            addCriterion("field0072 <", value, "field0072");
            return (Criteria) this;
        }

        public Criteria andField0072LessThanOrEqualTo(String value) {
            addCriterion("field0072 <=", value, "field0072");
            return (Criteria) this;
        }

        public Criteria andField0072Like(String value) {
            addCriterion("field0072 like", value, "field0072");
            return (Criteria) this;
        }

        public Criteria andField0072NotLike(String value) {
            addCriterion("field0072 not like", value, "field0072");
            return (Criteria) this;
        }

        public Criteria andField0072In(List<String> values) {
            addCriterion("field0072 in", values, "field0072");
            return (Criteria) this;
        }

        public Criteria andField0072NotIn(List<String> values) {
            addCriterion("field0072 not in", values, "field0072");
            return (Criteria) this;
        }

        public Criteria andField0072Between(String value1, String value2) {
            addCriterion("field0072 between", value1, value2, "field0072");
            return (Criteria) this;
        }

        public Criteria andField0072NotBetween(String value1, String value2) {
            addCriterion("field0072 not between", value1, value2, "field0072");
            return (Criteria) this;
        }

        public Criteria andField0073IsNull() {
            addCriterion("field0073 is null");
            return (Criteria) this;
        }

        public Criteria andField0073IsNotNull() {
            addCriterion("field0073 is not null");
            return (Criteria) this;
        }

        public Criteria andField0073EqualTo(Date value) {
            addCriterionForJDBCDate("field0073 =", value, "field0073");
            return (Criteria) this;
        }

        public Criteria andField0073NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0073 <>", value, "field0073");
            return (Criteria) this;
        }

        public Criteria andField0073GreaterThan(Date value) {
            addCriterionForJDBCDate("field0073 >", value, "field0073");
            return (Criteria) this;
        }

        public Criteria andField0073GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0073 >=", value, "field0073");
            return (Criteria) this;
        }

        public Criteria andField0073LessThan(Date value) {
            addCriterionForJDBCDate("field0073 <", value, "field0073");
            return (Criteria) this;
        }

        public Criteria andField0073LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0073 <=", value, "field0073");
            return (Criteria) this;
        }

        public Criteria andField0073In(List<Date> values) {
            addCriterionForJDBCDate("field0073 in", values, "field0073");
            return (Criteria) this;
        }

        public Criteria andField0073NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0073 not in", values, "field0073");
            return (Criteria) this;
        }

        public Criteria andField0073Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0073 between", value1, value2, "field0073");
            return (Criteria) this;
        }

        public Criteria andField0073NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0073 not between", value1, value2, "field0073");
            return (Criteria) this;
        }

        public Criteria andField0074IsNull() {
            addCriterion("field0074 is null");
            return (Criteria) this;
        }

        public Criteria andField0074IsNotNull() {
            addCriterion("field0074 is not null");
            return (Criteria) this;
        }

        public Criteria andField0074EqualTo(String value) {
            addCriterion("field0074 =", value, "field0074");
            return (Criteria) this;
        }

        public Criteria andField0074NotEqualTo(String value) {
            addCriterion("field0074 <>", value, "field0074");
            return (Criteria) this;
        }

        public Criteria andField0074GreaterThan(String value) {
            addCriterion("field0074 >", value, "field0074");
            return (Criteria) this;
        }

        public Criteria andField0074GreaterThanOrEqualTo(String value) {
            addCriterion("field0074 >=", value, "field0074");
            return (Criteria) this;
        }

        public Criteria andField0074LessThan(String value) {
            addCriterion("field0074 <", value, "field0074");
            return (Criteria) this;
        }

        public Criteria andField0074LessThanOrEqualTo(String value) {
            addCriterion("field0074 <=", value, "field0074");
            return (Criteria) this;
        }

        public Criteria andField0074Like(String value) {
            addCriterion("field0074 like", value, "field0074");
            return (Criteria) this;
        }

        public Criteria andField0074NotLike(String value) {
            addCriterion("field0074 not like", value, "field0074");
            return (Criteria) this;
        }

        public Criteria andField0074In(List<String> values) {
            addCriterion("field0074 in", values, "field0074");
            return (Criteria) this;
        }

        public Criteria andField0074NotIn(List<String> values) {
            addCriterion("field0074 not in", values, "field0074");
            return (Criteria) this;
        }

        public Criteria andField0074Between(String value1, String value2) {
            addCriterion("field0074 between", value1, value2, "field0074");
            return (Criteria) this;
        }

        public Criteria andField0074NotBetween(String value1, String value2) {
            addCriterion("field0074 not between", value1, value2, "field0074");
            return (Criteria) this;
        }

        public Criteria andField0075IsNull() {
            addCriterion("field0075 is null");
            return (Criteria) this;
        }

        public Criteria andField0075IsNotNull() {
            addCriterion("field0075 is not null");
            return (Criteria) this;
        }

        public Criteria andField0075EqualTo(String value) {
            addCriterion("field0075 =", value, "field0075");
            return (Criteria) this;
        }

        public Criteria andField0075NotEqualTo(String value) {
            addCriterion("field0075 <>", value, "field0075");
            return (Criteria) this;
        }

        public Criteria andField0075GreaterThan(String value) {
            addCriterion("field0075 >", value, "field0075");
            return (Criteria) this;
        }

        public Criteria andField0075GreaterThanOrEqualTo(String value) {
            addCriterion("field0075 >=", value, "field0075");
            return (Criteria) this;
        }

        public Criteria andField0075LessThan(String value) {
            addCriterion("field0075 <", value, "field0075");
            return (Criteria) this;
        }

        public Criteria andField0075LessThanOrEqualTo(String value) {
            addCriterion("field0075 <=", value, "field0075");
            return (Criteria) this;
        }

        public Criteria andField0075Like(String value) {
            addCriterion("field0075 like", value, "field0075");
            return (Criteria) this;
        }

        public Criteria andField0075NotLike(String value) {
            addCriterion("field0075 not like", value, "field0075");
            return (Criteria) this;
        }

        public Criteria andField0075In(List<String> values) {
            addCriterion("field0075 in", values, "field0075");
            return (Criteria) this;
        }

        public Criteria andField0075NotIn(List<String> values) {
            addCriterion("field0075 not in", values, "field0075");
            return (Criteria) this;
        }

        public Criteria andField0075Between(String value1, String value2) {
            addCriterion("field0075 between", value1, value2, "field0075");
            return (Criteria) this;
        }

        public Criteria andField0075NotBetween(String value1, String value2) {
            addCriterion("field0075 not between", value1, value2, "field0075");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andField0076IsNull() {
            addCriterion("field0076 is null");
            return (Criteria) this;
        }

        public Criteria andField0076IsNotNull() {
            addCriterion("field0076 is not null");
            return (Criteria) this;
        }

        public Criteria andField0076EqualTo(String value) {
            addCriterion("field0076 =", value, "field0076");
            return (Criteria) this;
        }

        public Criteria andField0076NotEqualTo(String value) {
            addCriterion("field0076 <>", value, "field0076");
            return (Criteria) this;
        }

        public Criteria andField0076GreaterThan(String value) {
            addCriterion("field0076 >", value, "field0076");
            return (Criteria) this;
        }

        public Criteria andField0076GreaterThanOrEqualTo(String value) {
            addCriterion("field0076 >=", value, "field0076");
            return (Criteria) this;
        }

        public Criteria andField0076LessThan(String value) {
            addCriterion("field0076 <", value, "field0076");
            return (Criteria) this;
        }

        public Criteria andField0076LessThanOrEqualTo(String value) {
            addCriterion("field0076 <=", value, "field0076");
            return (Criteria) this;
        }

        public Criteria andField0076Like(String value) {
            addCriterion("field0076 like", value, "field0076");
            return (Criteria) this;
        }

        public Criteria andField0076NotLike(String value) {
            addCriterion("field0076 not like", value, "field0076");
            return (Criteria) this;
        }

        public Criteria andField0076In(List<String> values) {
            addCriterion("field0076 in", values, "field0076");
            return (Criteria) this;
        }

        public Criteria andField0076NotIn(List<String> values) {
            addCriterion("field0076 not in", values, "field0076");
            return (Criteria) this;
        }

        public Criteria andField0076Between(String value1, String value2) {
            addCriterion("field0076 between", value1, value2, "field0076");
            return (Criteria) this;
        }

        public Criteria andField0076NotBetween(String value1, String value2) {
            addCriterion("field0076 not between", value1, value2, "field0076");
            return (Criteria) this;
        }

        public Criteria andField0078IsNull() {
            addCriterion("field0078 is null");
            return (Criteria) this;
        }

        public Criteria andField0078IsNotNull() {
            addCriterion("field0078 is not null");
            return (Criteria) this;
        }

        public Criteria andField0078EqualTo(String value) {
            addCriterion("field0078 =", value, "field0078");
            return (Criteria) this;
        }

        public Criteria andField0078NotEqualTo(String value) {
            addCriterion("field0078 <>", value, "field0078");
            return (Criteria) this;
        }

        public Criteria andField0078GreaterThan(String value) {
            addCriterion("field0078 >", value, "field0078");
            return (Criteria) this;
        }

        public Criteria andField0078GreaterThanOrEqualTo(String value) {
            addCriterion("field0078 >=", value, "field0078");
            return (Criteria) this;
        }

        public Criteria andField0078LessThan(String value) {
            addCriterion("field0078 <", value, "field0078");
            return (Criteria) this;
        }

        public Criteria andField0078LessThanOrEqualTo(String value) {
            addCriterion("field0078 <=", value, "field0078");
            return (Criteria) this;
        }

        public Criteria andField0078Like(String value) {
            addCriterion("field0078 like", value, "field0078");
            return (Criteria) this;
        }

        public Criteria andField0078NotLike(String value) {
            addCriterion("field0078 not like", value, "field0078");
            return (Criteria) this;
        }

        public Criteria andField0078In(List<String> values) {
            addCriterion("field0078 in", values, "field0078");
            return (Criteria) this;
        }

        public Criteria andField0078NotIn(List<String> values) {
            addCriterion("field0078 not in", values, "field0078");
            return (Criteria) this;
        }

        public Criteria andField0078Between(String value1, String value2) {
            addCriterion("field0078 between", value1, value2, "field0078");
            return (Criteria) this;
        }

        public Criteria andField0078NotBetween(String value1, String value2) {
            addCriterion("field0078 not between", value1, value2, "field0078");
            return (Criteria) this;
        }

        public Criteria andField0079IsNull() {
            addCriterion("field0079 is null");
            return (Criteria) this;
        }

        public Criteria andField0079IsNotNull() {
            addCriterion("field0079 is not null");
            return (Criteria) this;
        }

        public Criteria andField0079EqualTo(String value) {
            addCriterion("field0079 =", value, "field0079");
            return (Criteria) this;
        }

        public Criteria andField0079NotEqualTo(String value) {
            addCriterion("field0079 <>", value, "field0079");
            return (Criteria) this;
        }

        public Criteria andField0079GreaterThan(String value) {
            addCriterion("field0079 >", value, "field0079");
            return (Criteria) this;
        }

        public Criteria andField0079GreaterThanOrEqualTo(String value) {
            addCriterion("field0079 >=", value, "field0079");
            return (Criteria) this;
        }

        public Criteria andField0079LessThan(String value) {
            addCriterion("field0079 <", value, "field0079");
            return (Criteria) this;
        }

        public Criteria andField0079LessThanOrEqualTo(String value) {
            addCriterion("field0079 <=", value, "field0079");
            return (Criteria) this;
        }

        public Criteria andField0079Like(String value) {
            addCriterion("field0079 like", value, "field0079");
            return (Criteria) this;
        }

        public Criteria andField0079NotLike(String value) {
            addCriterion("field0079 not like", value, "field0079");
            return (Criteria) this;
        }

        public Criteria andField0079In(List<String> values) {
            addCriterion("field0079 in", values, "field0079");
            return (Criteria) this;
        }

        public Criteria andField0079NotIn(List<String> values) {
            addCriterion("field0079 not in", values, "field0079");
            return (Criteria) this;
        }

        public Criteria andField0079Between(String value1, String value2) {
            addCriterion("field0079 between", value1, value2, "field0079");
            return (Criteria) this;
        }

        public Criteria andField0079NotBetween(String value1, String value2) {
            addCriterion("field0079 not between", value1, value2, "field0079");
            return (Criteria) this;
        }

        public Criteria andField0080IsNull() {
            addCriterion("field0080 is null");
            return (Criteria) this;
        }

        public Criteria andField0080IsNotNull() {
            addCriterion("field0080 is not null");
            return (Criteria) this;
        }

        public Criteria andField0080EqualTo(String value) {
            addCriterion("field0080 =", value, "field0080");
            return (Criteria) this;
        }

        public Criteria andField0080NotEqualTo(String value) {
            addCriterion("field0080 <>", value, "field0080");
            return (Criteria) this;
        }

        public Criteria andField0080GreaterThan(String value) {
            addCriterion("field0080 >", value, "field0080");
            return (Criteria) this;
        }

        public Criteria andField0080GreaterThanOrEqualTo(String value) {
            addCriterion("field0080 >=", value, "field0080");
            return (Criteria) this;
        }

        public Criteria andField0080LessThan(String value) {
            addCriterion("field0080 <", value, "field0080");
            return (Criteria) this;
        }

        public Criteria andField0080LessThanOrEqualTo(String value) {
            addCriterion("field0080 <=", value, "field0080");
            return (Criteria) this;
        }

        public Criteria andField0080Like(String value) {
            addCriterion("field0080 like", value, "field0080");
            return (Criteria) this;
        }

        public Criteria andField0080NotLike(String value) {
            addCriterion("field0080 not like", value, "field0080");
            return (Criteria) this;
        }

        public Criteria andField0080In(List<String> values) {
            addCriterion("field0080 in", values, "field0080");
            return (Criteria) this;
        }

        public Criteria andField0080NotIn(List<String> values) {
            addCriterion("field0080 not in", values, "field0080");
            return (Criteria) this;
        }

        public Criteria andField0080Between(String value1, String value2) {
            addCriterion("field0080 between", value1, value2, "field0080");
            return (Criteria) this;
        }

        public Criteria andField0080NotBetween(String value1, String value2) {
            addCriterion("field0080 not between", value1, value2, "field0080");
            return (Criteria) this;
        }

        public Criteria andField0081IsNull() {
            addCriterion("field0081 is null");
            return (Criteria) this;
        }

        public Criteria andField0081IsNotNull() {
            addCriterion("field0081 is not null");
            return (Criteria) this;
        }

        public Criteria andField0081EqualTo(Date value) {
            addCriterionForJDBCDate("field0081 =", value, "field0081");
            return (Criteria) this;
        }

        public Criteria andField0081NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0081 <>", value, "field0081");
            return (Criteria) this;
        }

        public Criteria andField0081GreaterThan(Date value) {
            addCriterionForJDBCDate("field0081 >", value, "field0081");
            return (Criteria) this;
        }

        public Criteria andField0081GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0081 >=", value, "field0081");
            return (Criteria) this;
        }

        public Criteria andField0081LessThan(Date value) {
            addCriterionForJDBCDate("field0081 <", value, "field0081");
            return (Criteria) this;
        }

        public Criteria andField0081LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0081 <=", value, "field0081");
            return (Criteria) this;
        }

        public Criteria andField0081In(List<Date> values) {
            addCriterionForJDBCDate("field0081 in", values, "field0081");
            return (Criteria) this;
        }

        public Criteria andField0081NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0081 not in", values, "field0081");
            return (Criteria) this;
        }

        public Criteria andField0081Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0081 between", value1, value2, "field0081");
            return (Criteria) this;
        }

        public Criteria andField0081NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0081 not between", value1, value2, "field0081");
            return (Criteria) this;
        }

        public Criteria andField0082IsNull() {
            addCriterion("field0082 is null");
            return (Criteria) this;
        }

        public Criteria andField0082IsNotNull() {
            addCriterion("field0082 is not null");
            return (Criteria) this;
        }

        public Criteria andField0082EqualTo(String value) {
            addCriterion("field0082 =", value, "field0082");
            return (Criteria) this;
        }

        public Criteria andField0082NotEqualTo(String value) {
            addCriterion("field0082 <>", value, "field0082");
            return (Criteria) this;
        }

        public Criteria andField0082GreaterThan(String value) {
            addCriterion("field0082 >", value, "field0082");
            return (Criteria) this;
        }

        public Criteria andField0082GreaterThanOrEqualTo(String value) {
            addCriterion("field0082 >=", value, "field0082");
            return (Criteria) this;
        }

        public Criteria andField0082LessThan(String value) {
            addCriterion("field0082 <", value, "field0082");
            return (Criteria) this;
        }

        public Criteria andField0082LessThanOrEqualTo(String value) {
            addCriterion("field0082 <=", value, "field0082");
            return (Criteria) this;
        }

        public Criteria andField0082Like(String value) {
            addCriterion("field0082 like", value, "field0082");
            return (Criteria) this;
        }

        public Criteria andField0082NotLike(String value) {
            addCriterion("field0082 not like", value, "field0082");
            return (Criteria) this;
        }

        public Criteria andField0082In(List<String> values) {
            addCriterion("field0082 in", values, "field0082");
            return (Criteria) this;
        }

        public Criteria andField0082NotIn(List<String> values) {
            addCriterion("field0082 not in", values, "field0082");
            return (Criteria) this;
        }

        public Criteria andField0082Between(String value1, String value2) {
            addCriterion("field0082 between", value1, value2, "field0082");
            return (Criteria) this;
        }

        public Criteria andField0082NotBetween(String value1, String value2) {
            addCriterion("field0082 not between", value1, value2, "field0082");
            return (Criteria) this;
        }

        public Criteria andField0083IsNull() {
            addCriterion("field0083 is null");
            return (Criteria) this;
        }

        public Criteria andField0083IsNotNull() {
            addCriterion("field0083 is not null");
            return (Criteria) this;
        }

        public Criteria andField0083EqualTo(String value) {
            addCriterion("field0083 =", value, "field0083");
            return (Criteria) this;
        }

        public Criteria andField0083NotEqualTo(String value) {
            addCriterion("field0083 <>", value, "field0083");
            return (Criteria) this;
        }

        public Criteria andField0083GreaterThan(String value) {
            addCriterion("field0083 >", value, "field0083");
            return (Criteria) this;
        }

        public Criteria andField0083GreaterThanOrEqualTo(String value) {
            addCriterion("field0083 >=", value, "field0083");
            return (Criteria) this;
        }

        public Criteria andField0083LessThan(String value) {
            addCriterion("field0083 <", value, "field0083");
            return (Criteria) this;
        }

        public Criteria andField0083LessThanOrEqualTo(String value) {
            addCriterion("field0083 <=", value, "field0083");
            return (Criteria) this;
        }

        public Criteria andField0083Like(String value) {
            addCriterion("field0083 like", value, "field0083");
            return (Criteria) this;
        }

        public Criteria andField0083NotLike(String value) {
            addCriterion("field0083 not like", value, "field0083");
            return (Criteria) this;
        }

        public Criteria andField0083In(List<String> values) {
            addCriterion("field0083 in", values, "field0083");
            return (Criteria) this;
        }

        public Criteria andField0083NotIn(List<String> values) {
            addCriterion("field0083 not in", values, "field0083");
            return (Criteria) this;
        }

        public Criteria andField0083Between(String value1, String value2) {
            addCriterion("field0083 between", value1, value2, "field0083");
            return (Criteria) this;
        }

        public Criteria andField0083NotBetween(String value1, String value2) {
            addCriterion("field0083 not between", value1, value2, "field0083");
            return (Criteria) this;
        }

        public Criteria andField0084IsNull() {
            addCriterion("field0084 is null");
            return (Criteria) this;
        }

        public Criteria andField0084IsNotNull() {
            addCriterion("field0084 is not null");
            return (Criteria) this;
        }

        public Criteria andField0084EqualTo(Date value) {
            addCriterionForJDBCDate("field0084 =", value, "field0084");
            return (Criteria) this;
        }

        public Criteria andField0084NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0084 <>", value, "field0084");
            return (Criteria) this;
        }

        public Criteria andField0084GreaterThan(Date value) {
            addCriterionForJDBCDate("field0084 >", value, "field0084");
            return (Criteria) this;
        }

        public Criteria andField0084GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0084 >=", value, "field0084");
            return (Criteria) this;
        }

        public Criteria andField0084LessThan(Date value) {
            addCriterionForJDBCDate("field0084 <", value, "field0084");
            return (Criteria) this;
        }

        public Criteria andField0084LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0084 <=", value, "field0084");
            return (Criteria) this;
        }

        public Criteria andField0084In(List<Date> values) {
            addCriterionForJDBCDate("field0084 in", values, "field0084");
            return (Criteria) this;
        }

        public Criteria andField0084NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0084 not in", values, "field0084");
            return (Criteria) this;
        }

        public Criteria andField0084Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0084 between", value1, value2, "field0084");
            return (Criteria) this;
        }

        public Criteria andField0084NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0084 not between", value1, value2, "field0084");
            return (Criteria) this;
        }

        public Criteria andField0085IsNull() {
            addCriterion("field0085 is null");
            return (Criteria) this;
        }

        public Criteria andField0085IsNotNull() {
            addCriterion("field0085 is not null");
            return (Criteria) this;
        }

        public Criteria andField0085EqualTo(Long value) {
            addCriterion("field0085 =", value, "field0085");
            return (Criteria) this;
        }

        public Criteria andField0085NotEqualTo(Long value) {
            addCriterion("field0085 <>", value, "field0085");
            return (Criteria) this;
        }

        public Criteria andField0085GreaterThan(Long value) {
            addCriterion("field0085 >", value, "field0085");
            return (Criteria) this;
        }

        public Criteria andField0085GreaterThanOrEqualTo(Long value) {
            addCriterion("field0085 >=", value, "field0085");
            return (Criteria) this;
        }

        public Criteria andField0085LessThan(Long value) {
            addCriterion("field0085 <", value, "field0085");
            return (Criteria) this;
        }

        public Criteria andField0085LessThanOrEqualTo(Long value) {
            addCriterion("field0085 <=", value, "field0085");
            return (Criteria) this;
        }

        public Criteria andField0085In(List<Long> values) {
            addCriterion("field0085 in", values, "field0085");
            return (Criteria) this;
        }

        public Criteria andField0085NotIn(List<Long> values) {
            addCriterion("field0085 not in", values, "field0085");
            return (Criteria) this;
        }

        public Criteria andField0085Between(Long value1, Long value2) {
            addCriterion("field0085 between", value1, value2, "field0085");
            return (Criteria) this;
        }

        public Criteria andField0085NotBetween(Long value1, Long value2) {
            addCriterion("field0085 not between", value1, value2, "field0085");
            return (Criteria) this;
        }

        public Criteria andField0086IsNull() {
            addCriterion("field0086 is null");
            return (Criteria) this;
        }

        public Criteria andField0086IsNotNull() {
            addCriterion("field0086 is not null");
            return (Criteria) this;
        }

        public Criteria andField0086EqualTo(Long value) {
            addCriterion("field0086 =", value, "field0086");
            return (Criteria) this;
        }

        public Criteria andField0086NotEqualTo(Long value) {
            addCriterion("field0086 <>", value, "field0086");
            return (Criteria) this;
        }

        public Criteria andField0086GreaterThan(Long value) {
            addCriterion("field0086 >", value, "field0086");
            return (Criteria) this;
        }

        public Criteria andField0086GreaterThanOrEqualTo(Long value) {
            addCriterion("field0086 >=", value, "field0086");
            return (Criteria) this;
        }

        public Criteria andField0086LessThan(Long value) {
            addCriterion("field0086 <", value, "field0086");
            return (Criteria) this;
        }

        public Criteria andField0086LessThanOrEqualTo(Long value) {
            addCriterion("field0086 <=", value, "field0086");
            return (Criteria) this;
        }

        public Criteria andField0086In(List<Long> values) {
            addCriterion("field0086 in", values, "field0086");
            return (Criteria) this;
        }

        public Criteria andField0086NotIn(List<Long> values) {
            addCriterion("field0086 not in", values, "field0086");
            return (Criteria) this;
        }

        public Criteria andField0086Between(Long value1, Long value2) {
            addCriterion("field0086 between", value1, value2, "field0086");
            return (Criteria) this;
        }

        public Criteria andField0086NotBetween(Long value1, Long value2) {
            addCriterion("field0086 not between", value1, value2, "field0086");
            return (Criteria) this;
        }

        public Criteria andField0087IsNull() {
            addCriterion("field0087 is null");
            return (Criteria) this;
        }

        public Criteria andField0087IsNotNull() {
            addCriterion("field0087 is not null");
            return (Criteria) this;
        }

        public Criteria andField0087EqualTo(String value) {
            addCriterion("field0087 =", value, "field0087");
            return (Criteria) this;
        }

        public Criteria andField0087NotEqualTo(String value) {
            addCriterion("field0087 <>", value, "field0087");
            return (Criteria) this;
        }

        public Criteria andField0087GreaterThan(String value) {
            addCriterion("field0087 >", value, "field0087");
            return (Criteria) this;
        }

        public Criteria andField0087GreaterThanOrEqualTo(String value) {
            addCriterion("field0087 >=", value, "field0087");
            return (Criteria) this;
        }

        public Criteria andField0087LessThan(String value) {
            addCriterion("field0087 <", value, "field0087");
            return (Criteria) this;
        }

        public Criteria andField0087LessThanOrEqualTo(String value) {
            addCriterion("field0087 <=", value, "field0087");
            return (Criteria) this;
        }

        public Criteria andField0087Like(String value) {
            addCriterion("field0087 like", value, "field0087");
            return (Criteria) this;
        }

        public Criteria andField0087NotLike(String value) {
            addCriterion("field0087 not like", value, "field0087");
            return (Criteria) this;
        }

        public Criteria andField0087In(List<String> values) {
            addCriterion("field0087 in", values, "field0087");
            return (Criteria) this;
        }

        public Criteria andField0087NotIn(List<String> values) {
            addCriterion("field0087 not in", values, "field0087");
            return (Criteria) this;
        }

        public Criteria andField0087Between(String value1, String value2) {
            addCriterion("field0087 between", value1, value2, "field0087");
            return (Criteria) this;
        }

        public Criteria andField0087NotBetween(String value1, String value2) {
            addCriterion("field0087 not between", value1, value2, "field0087");
            return (Criteria) this;
        }

        public Criteria andField0090IsNull() {
            addCriterion("field0090 is null");
            return (Criteria) this;
        }

        public Criteria andField0090IsNotNull() {
            addCriterion("field0090 is not null");
            return (Criteria) this;
        }

        public Criteria andField0090EqualTo(String value) {
            addCriterion("field0090 =", value, "field0090");
            return (Criteria) this;
        }

        public Criteria andField0090NotEqualTo(String value) {
            addCriterion("field0090 <>", value, "field0090");
            return (Criteria) this;
        }

        public Criteria andField0090GreaterThan(String value) {
            addCriterion("field0090 >", value, "field0090");
            return (Criteria) this;
        }

        public Criteria andField0090GreaterThanOrEqualTo(String value) {
            addCriterion("field0090 >=", value, "field0090");
            return (Criteria) this;
        }

        public Criteria andField0090LessThan(String value) {
            addCriterion("field0090 <", value, "field0090");
            return (Criteria) this;
        }

        public Criteria andField0090LessThanOrEqualTo(String value) {
            addCriterion("field0090 <=", value, "field0090");
            return (Criteria) this;
        }

        public Criteria andField0090Like(String value) {
            addCriterion("field0090 like", value, "field0090");
            return (Criteria) this;
        }

        public Criteria andField0090NotLike(String value) {
            addCriterion("field0090 not like", value, "field0090");
            return (Criteria) this;
        }

        public Criteria andField0090In(List<String> values) {
            addCriterion("field0090 in", values, "field0090");
            return (Criteria) this;
        }

        public Criteria andField0090NotIn(List<String> values) {
            addCriterion("field0090 not in", values, "field0090");
            return (Criteria) this;
        }

        public Criteria andField0090Between(String value1, String value2) {
            addCriterion("field0090 between", value1, value2, "field0090");
            return (Criteria) this;
        }

        public Criteria andField0090NotBetween(String value1, String value2) {
            addCriterion("field0090 not between", value1, value2, "field0090");
            return (Criteria) this;
        }

        public Criteria andField0091IsNull() {
            addCriterion("field0091 is null");
            return (Criteria) this;
        }

        public Criteria andField0091IsNotNull() {
            addCriterion("field0091 is not null");
            return (Criteria) this;
        }

        public Criteria andField0091EqualTo(String value) {
            addCriterion("field0091 =", value, "field0091");
            return (Criteria) this;
        }

        public Criteria andField0091NotEqualTo(String value) {
            addCriterion("field0091 <>", value, "field0091");
            return (Criteria) this;
        }

        public Criteria andField0091GreaterThan(String value) {
            addCriterion("field0091 >", value, "field0091");
            return (Criteria) this;
        }

        public Criteria andField0091GreaterThanOrEqualTo(String value) {
            addCriterion("field0091 >=", value, "field0091");
            return (Criteria) this;
        }

        public Criteria andField0091LessThan(String value) {
            addCriterion("field0091 <", value, "field0091");
            return (Criteria) this;
        }

        public Criteria andField0091LessThanOrEqualTo(String value) {
            addCriterion("field0091 <=", value, "field0091");
            return (Criteria) this;
        }

        public Criteria andField0091Like(String value) {
            addCriterion("field0091 like", value, "field0091");
            return (Criteria) this;
        }

        public Criteria andField0091NotLike(String value) {
            addCriterion("field0091 not like", value, "field0091");
            return (Criteria) this;
        }

        public Criteria andField0091In(List<String> values) {
            addCriterion("field0091 in", values, "field0091");
            return (Criteria) this;
        }

        public Criteria andField0091NotIn(List<String> values) {
            addCriterion("field0091 not in", values, "field0091");
            return (Criteria) this;
        }

        public Criteria andField0091Between(String value1, String value2) {
            addCriterion("field0091 between", value1, value2, "field0091");
            return (Criteria) this;
        }

        public Criteria andField0091NotBetween(String value1, String value2) {
            addCriterion("field0091 not between", value1, value2, "field0091");
            return (Criteria) this;
        }

        public Criteria andField0092IsNull() {
            addCriterion("field0092 is null");
            return (Criteria) this;
        }

        public Criteria andField0092IsNotNull() {
            addCriterion("field0092 is not null");
            return (Criteria) this;
        }

        public Criteria andField0092EqualTo(Date value) {
            addCriterionForJDBCDate("field0092 =", value, "field0092");
            return (Criteria) this;
        }

        public Criteria andField0092NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0092 <>", value, "field0092");
            return (Criteria) this;
        }

        public Criteria andField0092GreaterThan(Date value) {
            addCriterionForJDBCDate("field0092 >", value, "field0092");
            return (Criteria) this;
        }

        public Criteria andField0092GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0092 >=", value, "field0092");
            return (Criteria) this;
        }

        public Criteria andField0092LessThan(Date value) {
            addCriterionForJDBCDate("field0092 <", value, "field0092");
            return (Criteria) this;
        }

        public Criteria andField0092LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0092 <=", value, "field0092");
            return (Criteria) this;
        }

        public Criteria andField0092In(List<Date> values) {
            addCriterionForJDBCDate("field0092 in", values, "field0092");
            return (Criteria) this;
        }

        public Criteria andField0092NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0092 not in", values, "field0092");
            return (Criteria) this;
        }

        public Criteria andField0092Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0092 between", value1, value2, "field0092");
            return (Criteria) this;
        }

        public Criteria andField0092NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0092 not between", value1, value2, "field0092");
            return (Criteria) this;
        }

        public Criteria andField0093IsNull() {
            addCriterion("field0093 is null");
            return (Criteria) this;
        }

        public Criteria andField0093IsNotNull() {
            addCriterion("field0093 is not null");
            return (Criteria) this;
        }

        public Criteria andField0093EqualTo(String value) {
            addCriterion("field0093 =", value, "field0093");
            return (Criteria) this;
        }

        public Criteria andField0093NotEqualTo(String value) {
            addCriterion("field0093 <>", value, "field0093");
            return (Criteria) this;
        }

        public Criteria andField0093GreaterThan(String value) {
            addCriterion("field0093 >", value, "field0093");
            return (Criteria) this;
        }

        public Criteria andField0093GreaterThanOrEqualTo(String value) {
            addCriterion("field0093 >=", value, "field0093");
            return (Criteria) this;
        }

        public Criteria andField0093LessThan(String value) {
            addCriterion("field0093 <", value, "field0093");
            return (Criteria) this;
        }

        public Criteria andField0093LessThanOrEqualTo(String value) {
            addCriterion("field0093 <=", value, "field0093");
            return (Criteria) this;
        }

        public Criteria andField0093Like(String value) {
            addCriterion("field0093 like", value, "field0093");
            return (Criteria) this;
        }

        public Criteria andField0093NotLike(String value) {
            addCriterion("field0093 not like", value, "field0093");
            return (Criteria) this;
        }

        public Criteria andField0093In(List<String> values) {
            addCriterion("field0093 in", values, "field0093");
            return (Criteria) this;
        }

        public Criteria andField0093NotIn(List<String> values) {
            addCriterion("field0093 not in", values, "field0093");
            return (Criteria) this;
        }

        public Criteria andField0093Between(String value1, String value2) {
            addCriterion("field0093 between", value1, value2, "field0093");
            return (Criteria) this;
        }

        public Criteria andField0093NotBetween(String value1, String value2) {
            addCriterion("field0093 not between", value1, value2, "field0093");
            return (Criteria) this;
        }

        public Criteria andField0096IsNull() {
            addCriterion("field0096 is null");
            return (Criteria) this;
        }

        public Criteria andField0096IsNotNull() {
            addCriterion("field0096 is not null");
            return (Criteria) this;
        }

        public Criteria andField0096EqualTo(String value) {
            addCriterion("field0096 =", value, "field0096");
            return (Criteria) this;
        }

        public Criteria andField0096NotEqualTo(String value) {
            addCriterion("field0096 <>", value, "field0096");
            return (Criteria) this;
        }

        public Criteria andField0096GreaterThan(String value) {
            addCriterion("field0096 >", value, "field0096");
            return (Criteria) this;
        }

        public Criteria andField0096GreaterThanOrEqualTo(String value) {
            addCriterion("field0096 >=", value, "field0096");
            return (Criteria) this;
        }

        public Criteria andField0096LessThan(String value) {
            addCriterion("field0096 <", value, "field0096");
            return (Criteria) this;
        }

        public Criteria andField0096LessThanOrEqualTo(String value) {
            addCriterion("field0096 <=", value, "field0096");
            return (Criteria) this;
        }

        public Criteria andField0096Like(String value) {
            addCriterion("field0096 like", value, "field0096");
            return (Criteria) this;
        }

        public Criteria andField0096NotLike(String value) {
            addCriterion("field0096 not like", value, "field0096");
            return (Criteria) this;
        }

        public Criteria andField0096In(List<String> values) {
            addCriterion("field0096 in", values, "field0096");
            return (Criteria) this;
        }

        public Criteria andField0096NotIn(List<String> values) {
            addCriterion("field0096 not in", values, "field0096");
            return (Criteria) this;
        }

        public Criteria andField0096Between(String value1, String value2) {
            addCriterion("field0096 between", value1, value2, "field0096");
            return (Criteria) this;
        }

        public Criteria andField0096NotBetween(String value1, String value2) {
            addCriterion("field0096 not between", value1, value2, "field0096");
            return (Criteria) this;
        }

        public Criteria andField0097IsNull() {
            addCriterion("field0097 is null");
            return (Criteria) this;
        }

        public Criteria andField0097IsNotNull() {
            addCriterion("field0097 is not null");
            return (Criteria) this;
        }

        public Criteria andField0097EqualTo(String value) {
            addCriterion("field0097 =", value, "field0097");
            return (Criteria) this;
        }

        public Criteria andField0097NotEqualTo(String value) {
            addCriterion("field0097 <>", value, "field0097");
            return (Criteria) this;
        }

        public Criteria andField0097GreaterThan(String value) {
            addCriterion("field0097 >", value, "field0097");
            return (Criteria) this;
        }

        public Criteria andField0097GreaterThanOrEqualTo(String value) {
            addCriterion("field0097 >=", value, "field0097");
            return (Criteria) this;
        }

        public Criteria andField0097LessThan(String value) {
            addCriterion("field0097 <", value, "field0097");
            return (Criteria) this;
        }

        public Criteria andField0097LessThanOrEqualTo(String value) {
            addCriterion("field0097 <=", value, "field0097");
            return (Criteria) this;
        }

        public Criteria andField0097Like(String value) {
            addCriterion("field0097 like", value, "field0097");
            return (Criteria) this;
        }

        public Criteria andField0097NotLike(String value) {
            addCriterion("field0097 not like", value, "field0097");
            return (Criteria) this;
        }

        public Criteria andField0097In(List<String> values) {
            addCriterion("field0097 in", values, "field0097");
            return (Criteria) this;
        }

        public Criteria andField0097NotIn(List<String> values) {
            addCriterion("field0097 not in", values, "field0097");
            return (Criteria) this;
        }

        public Criteria andField0097Between(String value1, String value2) {
            addCriterion("field0097 between", value1, value2, "field0097");
            return (Criteria) this;
        }

        public Criteria andField0097NotBetween(String value1, String value2) {
            addCriterion("field0097 not between", value1, value2, "field0097");
            return (Criteria) this;
        }

        public Criteria andField0099IsNull() {
            addCriterion("field0099 is null");
            return (Criteria) this;
        }

        public Criteria andField0099IsNotNull() {
            addCriterion("field0099 is not null");
            return (Criteria) this;
        }

        public Criteria andField0099EqualTo(Long value) {
            addCriterion("field0099 =", value, "field0099");
            return (Criteria) this;
        }

        public Criteria andField0099NotEqualTo(Long value) {
            addCriterion("field0099 <>", value, "field0099");
            return (Criteria) this;
        }

        public Criteria andField0099GreaterThan(Long value) {
            addCriterion("field0099 >", value, "field0099");
            return (Criteria) this;
        }

        public Criteria andField0099GreaterThanOrEqualTo(Long value) {
            addCriterion("field0099 >=", value, "field0099");
            return (Criteria) this;
        }

        public Criteria andField0099LessThan(Long value) {
            addCriterion("field0099 <", value, "field0099");
            return (Criteria) this;
        }

        public Criteria andField0099LessThanOrEqualTo(Long value) {
            addCriterion("field0099 <=", value, "field0099");
            return (Criteria) this;
        }

        public Criteria andField0099In(List<Long> values) {
            addCriterion("field0099 in", values, "field0099");
            return (Criteria) this;
        }

        public Criteria andField0099NotIn(List<Long> values) {
            addCriterion("field0099 not in", values, "field0099");
            return (Criteria) this;
        }

        public Criteria andField0099Between(Long value1, Long value2) {
            addCriterion("field0099 between", value1, value2, "field0099");
            return (Criteria) this;
        }

        public Criteria andField0099NotBetween(Long value1, Long value2) {
            addCriterion("field0099 not between", value1, value2, "field0099");
            return (Criteria) this;
        }

        public Criteria andField0100IsNull() {
            addCriterion("field0100 is null");
            return (Criteria) this;
        }

        public Criteria andField0100IsNotNull() {
            addCriterion("field0100 is not null");
            return (Criteria) this;
        }

        public Criteria andField0100EqualTo(String value) {
            addCriterion("field0100 =", value, "field0100");
            return (Criteria) this;
        }

        public Criteria andField0100NotEqualTo(String value) {
            addCriterion("field0100 <>", value, "field0100");
            return (Criteria) this;
        }

        public Criteria andField0100GreaterThan(String value) {
            addCriterion("field0100 >", value, "field0100");
            return (Criteria) this;
        }

        public Criteria andField0100GreaterThanOrEqualTo(String value) {
            addCriterion("field0100 >=", value, "field0100");
            return (Criteria) this;
        }

        public Criteria andField0100LessThan(String value) {
            addCriterion("field0100 <", value, "field0100");
            return (Criteria) this;
        }

        public Criteria andField0100LessThanOrEqualTo(String value) {
            addCriterion("field0100 <=", value, "field0100");
            return (Criteria) this;
        }

        public Criteria andField0100Like(String value) {
            addCriterion("field0100 like", value, "field0100");
            return (Criteria) this;
        }

        public Criteria andField0100NotLike(String value) {
            addCriterion("field0100 not like", value, "field0100");
            return (Criteria) this;
        }

        public Criteria andField0100In(List<String> values) {
            addCriterion("field0100 in", values, "field0100");
            return (Criteria) this;
        }

        public Criteria andField0100NotIn(List<String> values) {
            addCriterion("field0100 not in", values, "field0100");
            return (Criteria) this;
        }

        public Criteria andField0100Between(String value1, String value2) {
            addCriterion("field0100 between", value1, value2, "field0100");
            return (Criteria) this;
        }

        public Criteria andField0100NotBetween(String value1, String value2) {
            addCriterion("field0100 not between", value1, value2, "field0100");
            return (Criteria) this;
        }

        public Criteria andField0101IsNull() {
            addCriterion("field0101 is null");
            return (Criteria) this;
        }

        public Criteria andField0101IsNotNull() {
            addCriterion("field0101 is not null");
            return (Criteria) this;
        }

        public Criteria andField0101EqualTo(Long value) {
            addCriterion("field0101 =", value, "field0101");
            return (Criteria) this;
        }

        public Criteria andField0101NotEqualTo(Long value) {
            addCriterion("field0101 <>", value, "field0101");
            return (Criteria) this;
        }

        public Criteria andField0101GreaterThan(Long value) {
            addCriterion("field0101 >", value, "field0101");
            return (Criteria) this;
        }

        public Criteria andField0101GreaterThanOrEqualTo(Long value) {
            addCriterion("field0101 >=", value, "field0101");
            return (Criteria) this;
        }

        public Criteria andField0101LessThan(Long value) {
            addCriterion("field0101 <", value, "field0101");
            return (Criteria) this;
        }

        public Criteria andField0101LessThanOrEqualTo(Long value) {
            addCriterion("field0101 <=", value, "field0101");
            return (Criteria) this;
        }

        public Criteria andField0101In(List<Long> values) {
            addCriterion("field0101 in", values, "field0101");
            return (Criteria) this;
        }

        public Criteria andField0101NotIn(List<Long> values) {
            addCriterion("field0101 not in", values, "field0101");
            return (Criteria) this;
        }

        public Criteria andField0101Between(Long value1, Long value2) {
            addCriterion("field0101 between", value1, value2, "field0101");
            return (Criteria) this;
        }

        public Criteria andField0101NotBetween(Long value1, Long value2) {
            addCriterion("field0101 not between", value1, value2, "field0101");
            return (Criteria) this;
        }

        public Criteria andField0102IsNull() {
            addCriterion("field0102 is null");
            return (Criteria) this;
        }

        public Criteria andField0102IsNotNull() {
            addCriterion("field0102 is not null");
            return (Criteria) this;
        }

        public Criteria andField0102EqualTo(String value) {
            addCriterion("field0102 =", value, "field0102");
            return (Criteria) this;
        }

        public Criteria andField0102NotEqualTo(String value) {
            addCriterion("field0102 <>", value, "field0102");
            return (Criteria) this;
        }

        public Criteria andField0102GreaterThan(String value) {
            addCriterion("field0102 >", value, "field0102");
            return (Criteria) this;
        }

        public Criteria andField0102GreaterThanOrEqualTo(String value) {
            addCriterion("field0102 >=", value, "field0102");
            return (Criteria) this;
        }

        public Criteria andField0102LessThan(String value) {
            addCriterion("field0102 <", value, "field0102");
            return (Criteria) this;
        }

        public Criteria andField0102LessThanOrEqualTo(String value) {
            addCriterion("field0102 <=", value, "field0102");
            return (Criteria) this;
        }

        public Criteria andField0102Like(String value) {
            addCriterion("field0102 like", value, "field0102");
            return (Criteria) this;
        }

        public Criteria andField0102NotLike(String value) {
            addCriterion("field0102 not like", value, "field0102");
            return (Criteria) this;
        }

        public Criteria andField0102In(List<String> values) {
            addCriterion("field0102 in", values, "field0102");
            return (Criteria) this;
        }

        public Criteria andField0102NotIn(List<String> values) {
            addCriterion("field0102 not in", values, "field0102");
            return (Criteria) this;
        }

        public Criteria andField0102Between(String value1, String value2) {
            addCriterion("field0102 between", value1, value2, "field0102");
            return (Criteria) this;
        }

        public Criteria andField0102NotBetween(String value1, String value2) {
            addCriterion("field0102 not between", value1, value2, "field0102");
            return (Criteria) this;
        }

        public Criteria andField0103IsNull() {
            addCriterion("field0103 is null");
            return (Criteria) this;
        }

        public Criteria andField0103IsNotNull() {
            addCriterion("field0103 is not null");
            return (Criteria) this;
        }

        public Criteria andField0103EqualTo(String value) {
            addCriterion("field0103 =", value, "field0103");
            return (Criteria) this;
        }

        public Criteria andField0103NotEqualTo(String value) {
            addCriterion("field0103 <>", value, "field0103");
            return (Criteria) this;
        }

        public Criteria andField0103GreaterThan(String value) {
            addCriterion("field0103 >", value, "field0103");
            return (Criteria) this;
        }

        public Criteria andField0103GreaterThanOrEqualTo(String value) {
            addCriterion("field0103 >=", value, "field0103");
            return (Criteria) this;
        }

        public Criteria andField0103LessThan(String value) {
            addCriterion("field0103 <", value, "field0103");
            return (Criteria) this;
        }

        public Criteria andField0103LessThanOrEqualTo(String value) {
            addCriterion("field0103 <=", value, "field0103");
            return (Criteria) this;
        }

        public Criteria andField0103Like(String value) {
            addCriterion("field0103 like", value, "field0103");
            return (Criteria) this;
        }

        public Criteria andField0103NotLike(String value) {
            addCriterion("field0103 not like", value, "field0103");
            return (Criteria) this;
        }

        public Criteria andField0103In(List<String> values) {
            addCriterion("field0103 in", values, "field0103");
            return (Criteria) this;
        }

        public Criteria andField0103NotIn(List<String> values) {
            addCriterion("field0103 not in", values, "field0103");
            return (Criteria) this;
        }

        public Criteria andField0103Between(String value1, String value2) {
            addCriterion("field0103 between", value1, value2, "field0103");
            return (Criteria) this;
        }

        public Criteria andField0103NotBetween(String value1, String value2) {
            addCriterion("field0103 not between", value1, value2, "field0103");
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