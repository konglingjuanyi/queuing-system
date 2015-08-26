package com.qunar.ops.recruit.model;

import java.util.ArrayList;
import java.util.List;

public class PhaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PhaseExample() {
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

        public Criteria andPhaseNameIsNull() {
            addCriterion("phase_name is null");
            return (Criteria) this;
        }

        public Criteria andPhaseNameIsNotNull() {
            addCriterion("phase_name is not null");
            return (Criteria) this;
        }

        public Criteria andPhaseNameEqualTo(String value) {
            addCriterion("phase_name =", value, "phaseName");
            return (Criteria) this;
        }

        public Criteria andPhaseNameNotEqualTo(String value) {
            addCriterion("phase_name <>", value, "phaseName");
            return (Criteria) this;
        }

        public Criteria andPhaseNameGreaterThan(String value) {
            addCriterion("phase_name >", value, "phaseName");
            return (Criteria) this;
        }

        public Criteria andPhaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("phase_name >=", value, "phaseName");
            return (Criteria) this;
        }

        public Criteria andPhaseNameLessThan(String value) {
            addCriterion("phase_name <", value, "phaseName");
            return (Criteria) this;
        }

        public Criteria andPhaseNameLessThanOrEqualTo(String value) {
            addCriterion("phase_name <=", value, "phaseName");
            return (Criteria) this;
        }

        public Criteria andPhaseNameLike(String value) {
            addCriterion("phase_name like", value, "phaseName");
            return (Criteria) this;
        }

        public Criteria andPhaseNameNotLike(String value) {
            addCriterion("phase_name not like", value, "phaseName");
            return (Criteria) this;
        }

        public Criteria andPhaseNameIn(List<String> values) {
            addCriterion("phase_name in", values, "phaseName");
            return (Criteria) this;
        }

        public Criteria andPhaseNameNotIn(List<String> values) {
            addCriterion("phase_name not in", values, "phaseName");
            return (Criteria) this;
        }

        public Criteria andPhaseNameBetween(String value1, String value2) {
            addCriterion("phase_name between", value1, value2, "phaseName");
            return (Criteria) this;
        }

        public Criteria andPhaseNameNotBetween(String value1, String value2) {
            addCriterion("phase_name not between", value1, value2, "phaseName");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("city_name is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("city_name is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("city_name <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("city_name >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("city_name >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("city_name <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("city_name <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("city_name like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("city_name not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("city_name in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("city_name not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("city_name between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("city_name not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andYearInfoIsNull() {
            addCriterion("year_info is null");
            return (Criteria) this;
        }

        public Criteria andYearInfoIsNotNull() {
            addCriterion("year_info is not null");
            return (Criteria) this;
        }

        public Criteria andYearInfoEqualTo(String value) {
            addCriterion("year_info =", value, "yearInfo");
            return (Criteria) this;
        }

        public Criteria andYearInfoNotEqualTo(String value) {
            addCriterion("year_info <>", value, "yearInfo");
            return (Criteria) this;
        }

        public Criteria andYearInfoGreaterThan(String value) {
            addCriterion("year_info >", value, "yearInfo");
            return (Criteria) this;
        }

        public Criteria andYearInfoGreaterThanOrEqualTo(String value) {
            addCriterion("year_info >=", value, "yearInfo");
            return (Criteria) this;
        }

        public Criteria andYearInfoLessThan(String value) {
            addCriterion("year_info <", value, "yearInfo");
            return (Criteria) this;
        }

        public Criteria andYearInfoLessThanOrEqualTo(String value) {
            addCriterion("year_info <=", value, "yearInfo");
            return (Criteria) this;
        }

        public Criteria andYearInfoLike(String value) {
            addCriterion("year_info like", value, "yearInfo");
            return (Criteria) this;
        }

        public Criteria andYearInfoNotLike(String value) {
            addCriterion("year_info not like", value, "yearInfo");
            return (Criteria) this;
        }

        public Criteria andYearInfoIn(List<String> values) {
            addCriterion("year_info in", values, "yearInfo");
            return (Criteria) this;
        }

        public Criteria andYearInfoNotIn(List<String> values) {
            addCriterion("year_info not in", values, "yearInfo");
            return (Criteria) this;
        }

        public Criteria andYearInfoBetween(String value1, String value2) {
            addCriterion("year_info between", value1, value2, "yearInfo");
            return (Criteria) this;
        }

        public Criteria andYearInfoNotBetween(String value1, String value2) {
            addCriterion("year_info not between", value1, value2, "yearInfo");
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