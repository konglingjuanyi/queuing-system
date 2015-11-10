package com.qunar.ops.recruit.model;

import java.util.ArrayList;
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

        public Criteria andViewCountIsNull() {
            addCriterion("view_count is null");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNotNull() {
            addCriterion("view_count is not null");
            return (Criteria) this;
        }

        public Criteria andViewCountEqualTo(Integer value) {
            addCriterion("view_count =", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotEqualTo(Integer value) {
            addCriterion("view_count <>", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThan(Integer value) {
            addCriterion("view_count >", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("view_count >=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThan(Integer value) {
            addCriterion("view_count <", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThanOrEqualTo(Integer value) {
            addCriterion("view_count <=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountIn(List<Integer> values) {
            addCriterion("view_count in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotIn(List<Integer> values) {
            addCriterion("view_count not in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountBetween(Integer value1, Integer value2) {
            addCriterion("view_count between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotBetween(Integer value1, Integer value2) {
            addCriterion("view_count not between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andDetermineIsNull() {
            addCriterion("determine is null");
            return (Criteria) this;
        }

        public Criteria andDetermineIsNotNull() {
            addCriterion("determine is not null");
            return (Criteria) this;
        }

        public Criteria andDetermineEqualTo(String value) {
            addCriterion("determine =", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineNotEqualTo(String value) {
            addCriterion("determine <>", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineGreaterThan(String value) {
            addCriterion("determine >", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineGreaterThanOrEqualTo(String value) {
            addCriterion("determine >=", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineLessThan(String value) {
            addCriterion("determine <", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineLessThanOrEqualTo(String value) {
            addCriterion("determine <=", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineLike(String value) {
            addCriterion("determine like", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineNotLike(String value) {
            addCriterion("determine not like", value, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineIn(List<String> values) {
            addCriterion("determine in", values, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineNotIn(List<String> values) {
            addCriterion("determine not in", values, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineBetween(String value1, String value2) {
            addCriterion("determine between", value1, value2, "determine");
            return (Criteria) this;
        }

        public Criteria andDetermineNotBetween(String value1, String value2) {
            addCriterion("determine not between", value1, value2, "determine");
            return (Criteria) this;
        }

        public Criteria andJobIsNull() {
            addCriterion("job is null");
            return (Criteria) this;
        }

        public Criteria andJobIsNotNull() {
            addCriterion("job is not null");
            return (Criteria) this;
        }

        public Criteria andJobEqualTo(String value) {
            addCriterion("job =", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotEqualTo(String value) {
            addCriterion("job <>", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobGreaterThan(String value) {
            addCriterion("job >", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobGreaterThanOrEqualTo(String value) {
            addCriterion("job >=", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobLessThan(String value) {
            addCriterion("job <", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobLessThanOrEqualTo(String value) {
            addCriterion("job <=", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobLike(String value) {
            addCriterion("job like", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotLike(String value) {
            addCriterion("job not like", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobIn(List<String> values) {
            addCriterion("job in", values, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotIn(List<String> values) {
            addCriterion("job not in", values, "job");
            return (Criteria) this;
        }

        public Criteria andJobBetween(String value1, String value2) {
            addCriterion("job between", value1, value2, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotBetween(String value1, String value2) {
            addCriterion("job not between", value1, value2, "job");
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