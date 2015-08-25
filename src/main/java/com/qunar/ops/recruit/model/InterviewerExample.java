package com.qunar.ops.recruit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterviewerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InterviewerExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andOneViewIsNull() {
            addCriterion("one_view is null");
            return (Criteria) this;
        }

        public Criteria andOneViewIsNotNull() {
            addCriterion("one_view is not null");
            return (Criteria) this;
        }

        public Criteria andOneViewEqualTo(String value) {
            addCriterion("one_view =", value, "oneView");
            return (Criteria) this;
        }

        public Criteria andOneViewNotEqualTo(String value) {
            addCriterion("one_view <>", value, "oneView");
            return (Criteria) this;
        }

        public Criteria andOneViewGreaterThan(String value) {
            addCriterion("one_view >", value, "oneView");
            return (Criteria) this;
        }

        public Criteria andOneViewGreaterThanOrEqualTo(String value) {
            addCriterion("one_view >=", value, "oneView");
            return (Criteria) this;
        }

        public Criteria andOneViewLessThan(String value) {
            addCriterion("one_view <", value, "oneView");
            return (Criteria) this;
        }

        public Criteria andOneViewLessThanOrEqualTo(String value) {
            addCriterion("one_view <=", value, "oneView");
            return (Criteria) this;
        }

        public Criteria andOneViewLike(String value) {
            addCriterion("one_view like", value, "oneView");
            return (Criteria) this;
        }

        public Criteria andOneViewNotLike(String value) {
            addCriterion("one_view not like", value, "oneView");
            return (Criteria) this;
        }

        public Criteria andOneViewIn(List<String> values) {
            addCriterion("one_view in", values, "oneView");
            return (Criteria) this;
        }

        public Criteria andOneViewNotIn(List<String> values) {
            addCriterion("one_view not in", values, "oneView");
            return (Criteria) this;
        }

        public Criteria andOneViewBetween(String value1, String value2) {
            addCriterion("one_view between", value1, value2, "oneView");
            return (Criteria) this;
        }

        public Criteria andOneViewNotBetween(String value1, String value2) {
            addCriterion("one_view not between", value1, value2, "oneView");
            return (Criteria) this;
        }

        public Criteria andTwoViewIsNull() {
            addCriterion("two_view is null");
            return (Criteria) this;
        }

        public Criteria andTwoViewIsNotNull() {
            addCriterion("two_view is not null");
            return (Criteria) this;
        }

        public Criteria andTwoViewEqualTo(String value) {
            addCriterion("two_view =", value, "twoView");
            return (Criteria) this;
        }

        public Criteria andTwoViewNotEqualTo(String value) {
            addCriterion("two_view <>", value, "twoView");
            return (Criteria) this;
        }

        public Criteria andTwoViewGreaterThan(String value) {
            addCriterion("two_view >", value, "twoView");
            return (Criteria) this;
        }

        public Criteria andTwoViewGreaterThanOrEqualTo(String value) {
            addCriterion("two_view >=", value, "twoView");
            return (Criteria) this;
        }

        public Criteria andTwoViewLessThan(String value) {
            addCriterion("two_view <", value, "twoView");
            return (Criteria) this;
        }

        public Criteria andTwoViewLessThanOrEqualTo(String value) {
            addCriterion("two_view <=", value, "twoView");
            return (Criteria) this;
        }

        public Criteria andTwoViewLike(String value) {
            addCriterion("two_view like", value, "twoView");
            return (Criteria) this;
        }

        public Criteria andTwoViewNotLike(String value) {
            addCriterion("two_view not like", value, "twoView");
            return (Criteria) this;
        }

        public Criteria andTwoViewIn(List<String> values) {
            addCriterion("two_view in", values, "twoView");
            return (Criteria) this;
        }

        public Criteria andTwoViewNotIn(List<String> values) {
            addCriterion("two_view not in", values, "twoView");
            return (Criteria) this;
        }

        public Criteria andTwoViewBetween(String value1, String value2) {
            addCriterion("two_view between", value1, value2, "twoView");
            return (Criteria) this;
        }

        public Criteria andTwoViewNotBetween(String value1, String value2) {
            addCriterion("two_view not between", value1, value2, "twoView");
            return (Criteria) this;
        }

        public Criteria andOneCountIsNull() {
            addCriterion("one_count is null");
            return (Criteria) this;
        }

        public Criteria andOneCountIsNotNull() {
            addCriterion("one_count is not null");
            return (Criteria) this;
        }

        public Criteria andOneCountEqualTo(Integer value) {
            addCriterion("one_count =", value, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountNotEqualTo(Integer value) {
            addCriterion("one_count <>", value, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountGreaterThan(Integer value) {
            addCriterion("one_count >", value, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_count >=", value, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountLessThan(Integer value) {
            addCriterion("one_count <", value, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountLessThanOrEqualTo(Integer value) {
            addCriterion("one_count <=", value, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountIn(List<Integer> values) {
            addCriterion("one_count in", values, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountNotIn(List<Integer> values) {
            addCriterion("one_count not in", values, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountBetween(Integer value1, Integer value2) {
            addCriterion("one_count between", value1, value2, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountNotBetween(Integer value1, Integer value2) {
            addCriterion("one_count not between", value1, value2, "oneCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountIsNull() {
            addCriterion("two_count is null");
            return (Criteria) this;
        }

        public Criteria andTwoCountIsNotNull() {
            addCriterion("two_count is not null");
            return (Criteria) this;
        }

        public Criteria andTwoCountEqualTo(Integer value) {
            addCriterion("two_count =", value, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountNotEqualTo(Integer value) {
            addCriterion("two_count <>", value, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountGreaterThan(Integer value) {
            addCriterion("two_count >", value, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_count >=", value, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountLessThan(Integer value) {
            addCriterion("two_count <", value, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountLessThanOrEqualTo(Integer value) {
            addCriterion("two_count <=", value, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountIn(List<Integer> values) {
            addCriterion("two_count in", values, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountNotIn(List<Integer> values) {
            addCriterion("two_count not in", values, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountBetween(Integer value1, Integer value2) {
            addCriterion("two_count between", value1, value2, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountNotBetween(Integer value1, Integer value2) {
            addCriterion("two_count not between", value1, value2, "twoCount");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andPhaseIsNull() {
            addCriterion("phase is null");
            return (Criteria) this;
        }

        public Criteria andPhaseIsNotNull() {
            addCriterion("phase is not null");
            return (Criteria) this;
        }

        public Criteria andPhaseEqualTo(String value) {
            addCriterion("phase =", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseNotEqualTo(String value) {
            addCriterion("phase <>", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseGreaterThan(String value) {
            addCriterion("phase >", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseGreaterThanOrEqualTo(String value) {
            addCriterion("phase >=", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseLessThan(String value) {
            addCriterion("phase <", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseLessThanOrEqualTo(String value) {
            addCriterion("phase <=", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseLike(String value) {
            addCriterion("phase like", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseNotLike(String value) {
            addCriterion("phase not like", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseIn(List<String> values) {
            addCriterion("phase in", values, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseNotIn(List<String> values) {
            addCriterion("phase not in", values, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseBetween(String value1, String value2) {
            addCriterion("phase between", value1, value2, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseNotBetween(String value1, String value2) {
            addCriterion("phase not between", value1, value2, "phase");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNull() {
            addCriterion("view_count is null");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNotNull() {
            addCriterion("view_count is not null");
            return (Criteria) this;
        }

        public Criteria andViewCountEqualTo(Long value) {
            addCriterion("view_count =", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotEqualTo(Long value) {
            addCriterion("view_count <>", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThan(Long value) {
            addCriterion("view_count >", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThanOrEqualTo(Long value) {
            addCriterion("view_count >=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThan(Long value) {
            addCriterion("view_count <", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThanOrEqualTo(Long value) {
            addCriterion("view_count <=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountIn(List<Long> values) {
            addCriterion("view_count in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotIn(List<Long> values) {
            addCriterion("view_count not in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountBetween(Long value1, Long value2) {
            addCriterion("view_count between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotBetween(Long value1, Long value2) {
            addCriterion("view_count not between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andRoomIsNull() {
            addCriterion("room is null");
            return (Criteria) this;
        }

        public Criteria andRoomIsNotNull() {
            addCriterion("room is not null");
            return (Criteria) this;
        }

        public Criteria andRoomEqualTo(String value) {
            addCriterion("room =", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotEqualTo(String value) {
            addCriterion("room <>", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomGreaterThan(String value) {
            addCriterion("room >", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomGreaterThanOrEqualTo(String value) {
            addCriterion("room >=", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomLessThan(String value) {
            addCriterion("room <", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomLessThanOrEqualTo(String value) {
            addCriterion("room <=", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomLike(String value) {
            addCriterion("room like", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotLike(String value) {
            addCriterion("room not like", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomIn(List<String> values) {
            addCriterion("room in", values, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotIn(List<String> values) {
            addCriterion("room not in", values, "room");
            return (Criteria) this;
        }

        public Criteria andRoomBetween(String value1, String value2) {
            addCriterion("room between", value1, value2, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotBetween(String value1, String value2) {
            addCriterion("room not between", value1, value2, "room");
            return (Criteria) this;
        }

        public Criteria andEnableIsNull() {
            addCriterion("enable is null");
            return (Criteria) this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("enable is not null");
            return (Criteria) this;
        }

        public Criteria andEnableEqualTo(Long value) {
            addCriterion("enable =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(Long value) {
            addCriterion("enable <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(Long value) {
            addCriterion("enable >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(Long value) {
            addCriterion("enable >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(Long value) {
            addCriterion("enable <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(Long value) {
            addCriterion("enable <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<Long> values) {
            addCriterion("enable in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<Long> values) {
            addCriterion("enable not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(Long value1, Long value2) {
            addCriterion("enable between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(Long value1, Long value2) {
            addCriterion("enable not between", value1, value2, "enable");
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