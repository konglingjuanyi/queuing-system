package com.qunar.ops.recruit.model;

import java.util.ArrayList;
import java.util.List;

public class PhaseInterviewerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PhaseInterviewerExample() {
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

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(String value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLike(String value) {
            addCriterion("year like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotLike(String value) {
            addCriterion("year not like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<String> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<String> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(String value1, String value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(String value1, String value2) {
            addCriterion("year not between", value1, value2, "year");
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

        public Criteria andIntervierNameIsNull() {
            addCriterion("intervier_name is null");
            return (Criteria) this;
        }

        public Criteria andIntervierNameIsNotNull() {
            addCriterion("intervier_name is not null");
            return (Criteria) this;
        }

        public Criteria andIntervierNameEqualTo(String value) {
            addCriterion("intervier_name =", value, "intervierName");
            return (Criteria) this;
        }

        public Criteria andIntervierNameNotEqualTo(String value) {
            addCriterion("intervier_name <>", value, "intervierName");
            return (Criteria) this;
        }

        public Criteria andIntervierNameGreaterThan(String value) {
            addCriterion("intervier_name >", value, "intervierName");
            return (Criteria) this;
        }

        public Criteria andIntervierNameGreaterThanOrEqualTo(String value) {
            addCriterion("intervier_name >=", value, "intervierName");
            return (Criteria) this;
        }

        public Criteria andIntervierNameLessThan(String value) {
            addCriterion("intervier_name <", value, "intervierName");
            return (Criteria) this;
        }

        public Criteria andIntervierNameLessThanOrEqualTo(String value) {
            addCriterion("intervier_name <=", value, "intervierName");
            return (Criteria) this;
        }

        public Criteria andIntervierNameLike(String value) {
            addCriterion("intervier_name like", value, "intervierName");
            return (Criteria) this;
        }

        public Criteria andIntervierNameNotLike(String value) {
            addCriterion("intervier_name not like", value, "intervierName");
            return (Criteria) this;
        }

        public Criteria andIntervierNameIn(List<String> values) {
            addCriterion("intervier_name in", values, "intervierName");
            return (Criteria) this;
        }

        public Criteria andIntervierNameNotIn(List<String> values) {
            addCriterion("intervier_name not in", values, "intervierName");
            return (Criteria) this;
        }

        public Criteria andIntervierNameBetween(String value1, String value2) {
            addCriterion("intervier_name between", value1, value2, "intervierName");
            return (Criteria) this;
        }

        public Criteria andIntervierNameNotBetween(String value1, String value2) {
            addCriterion("intervier_name not between", value1, value2, "intervierName");
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

        public Criteria andFirstRdIsNull() {
            addCriterion("first_rd is null");
            return (Criteria) this;
        }

        public Criteria andFirstRdIsNotNull() {
            addCriterion("first_rd is not null");
            return (Criteria) this;
        }

        public Criteria andFirstRdEqualTo(Integer value) {
            addCriterion("first_rd =", value, "firstRd");
            return (Criteria) this;
        }

        public Criteria andFirstRdNotEqualTo(Integer value) {
            addCriterion("first_rd <>", value, "firstRd");
            return (Criteria) this;
        }

        public Criteria andFirstRdGreaterThan(Integer value) {
            addCriterion("first_rd >", value, "firstRd");
            return (Criteria) this;
        }

        public Criteria andFirstRdGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_rd >=", value, "firstRd");
            return (Criteria) this;
        }

        public Criteria andFirstRdLessThan(Integer value) {
            addCriterion("first_rd <", value, "firstRd");
            return (Criteria) this;
        }

        public Criteria andFirstRdLessThanOrEqualTo(Integer value) {
            addCriterion("first_rd <=", value, "firstRd");
            return (Criteria) this;
        }

        public Criteria andFirstRdIn(List<Integer> values) {
            addCriterion("first_rd in", values, "firstRd");
            return (Criteria) this;
        }

        public Criteria andFirstRdNotIn(List<Integer> values) {
            addCriterion("first_rd not in", values, "firstRd");
            return (Criteria) this;
        }

        public Criteria andFirstRdBetween(Integer value1, Integer value2) {
            addCriterion("first_rd between", value1, value2, "firstRd");
            return (Criteria) this;
        }

        public Criteria andFirstRdNotBetween(Integer value1, Integer value2) {
            addCriterion("first_rd not between", value1, value2, "firstRd");
            return (Criteria) this;
        }

        public Criteria andFirstFeIsNull() {
            addCriterion("first_fe is null");
            return (Criteria) this;
        }

        public Criteria andFirstFeIsNotNull() {
            addCriterion("first_fe is not null");
            return (Criteria) this;
        }

        public Criteria andFirstFeEqualTo(Integer value) {
            addCriterion("first_fe =", value, "firstFe");
            return (Criteria) this;
        }

        public Criteria andFirstFeNotEqualTo(Integer value) {
            addCriterion("first_fe <>", value, "firstFe");
            return (Criteria) this;
        }

        public Criteria andFirstFeGreaterThan(Integer value) {
            addCriterion("first_fe >", value, "firstFe");
            return (Criteria) this;
        }

        public Criteria andFirstFeGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_fe >=", value, "firstFe");
            return (Criteria) this;
        }

        public Criteria andFirstFeLessThan(Integer value) {
            addCriterion("first_fe <", value, "firstFe");
            return (Criteria) this;
        }

        public Criteria andFirstFeLessThanOrEqualTo(Integer value) {
            addCriterion("first_fe <=", value, "firstFe");
            return (Criteria) this;
        }

        public Criteria andFirstFeIn(List<Integer> values) {
            addCriterion("first_fe in", values, "firstFe");
            return (Criteria) this;
        }

        public Criteria andFirstFeNotIn(List<Integer> values) {
            addCriterion("first_fe not in", values, "firstFe");
            return (Criteria) this;
        }

        public Criteria andFirstFeBetween(Integer value1, Integer value2) {
            addCriterion("first_fe between", value1, value2, "firstFe");
            return (Criteria) this;
        }

        public Criteria andFirstFeNotBetween(Integer value1, Integer value2) {
            addCriterion("first_fe not between", value1, value2, "firstFe");
            return (Criteria) this;
        }

        public Criteria andFirstQaIsNull() {
            addCriterion("first_qa is null");
            return (Criteria) this;
        }

        public Criteria andFirstQaIsNotNull() {
            addCriterion("first_qa is not null");
            return (Criteria) this;
        }

        public Criteria andFirstQaEqualTo(Integer value) {
            addCriterion("first_qa =", value, "firstQa");
            return (Criteria) this;
        }

        public Criteria andFirstQaNotEqualTo(Integer value) {
            addCriterion("first_qa <>", value, "firstQa");
            return (Criteria) this;
        }

        public Criteria andFirstQaGreaterThan(Integer value) {
            addCriterion("first_qa >", value, "firstQa");
            return (Criteria) this;
        }

        public Criteria andFirstQaGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_qa >=", value, "firstQa");
            return (Criteria) this;
        }

        public Criteria andFirstQaLessThan(Integer value) {
            addCriterion("first_qa <", value, "firstQa");
            return (Criteria) this;
        }

        public Criteria andFirstQaLessThanOrEqualTo(Integer value) {
            addCriterion("first_qa <=", value, "firstQa");
            return (Criteria) this;
        }

        public Criteria andFirstQaIn(List<Integer> values) {
            addCriterion("first_qa in", values, "firstQa");
            return (Criteria) this;
        }

        public Criteria andFirstQaNotIn(List<Integer> values) {
            addCriterion("first_qa not in", values, "firstQa");
            return (Criteria) this;
        }

        public Criteria andFirstQaBetween(Integer value1, Integer value2) {
            addCriterion("first_qa between", value1, value2, "firstQa");
            return (Criteria) this;
        }

        public Criteria andFirstQaNotBetween(Integer value1, Integer value2) {
            addCriterion("first_qa not between", value1, value2, "firstQa");
            return (Criteria) this;
        }

        public Criteria andSecondRdIsNull() {
            addCriterion("second_rd is null");
            return (Criteria) this;
        }

        public Criteria andSecondRdIsNotNull() {
            addCriterion("second_rd is not null");
            return (Criteria) this;
        }

        public Criteria andSecondRdEqualTo(Integer value) {
            addCriterion("second_rd =", value, "secondRd");
            return (Criteria) this;
        }

        public Criteria andSecondRdNotEqualTo(Integer value) {
            addCriterion("second_rd <>", value, "secondRd");
            return (Criteria) this;
        }

        public Criteria andSecondRdGreaterThan(Integer value) {
            addCriterion("second_rd >", value, "secondRd");
            return (Criteria) this;
        }

        public Criteria andSecondRdGreaterThanOrEqualTo(Integer value) {
            addCriterion("second_rd >=", value, "secondRd");
            return (Criteria) this;
        }

        public Criteria andSecondRdLessThan(Integer value) {
            addCriterion("second_rd <", value, "secondRd");
            return (Criteria) this;
        }

        public Criteria andSecondRdLessThanOrEqualTo(Integer value) {
            addCriterion("second_rd <=", value, "secondRd");
            return (Criteria) this;
        }

        public Criteria andSecondRdIn(List<Integer> values) {
            addCriterion("second_rd in", values, "secondRd");
            return (Criteria) this;
        }

        public Criteria andSecondRdNotIn(List<Integer> values) {
            addCriterion("second_rd not in", values, "secondRd");
            return (Criteria) this;
        }

        public Criteria andSecondRdBetween(Integer value1, Integer value2) {
            addCriterion("second_rd between", value1, value2, "secondRd");
            return (Criteria) this;
        }

        public Criteria andSecondRdNotBetween(Integer value1, Integer value2) {
            addCriterion("second_rd not between", value1, value2, "secondRd");
            return (Criteria) this;
        }

        public Criteria andSecondFeIsNull() {
            addCriterion("second_fe is null");
            return (Criteria) this;
        }

        public Criteria andSecondFeIsNotNull() {
            addCriterion("second_fe is not null");
            return (Criteria) this;
        }

        public Criteria andSecondFeEqualTo(Integer value) {
            addCriterion("second_fe =", value, "secondFe");
            return (Criteria) this;
        }

        public Criteria andSecondFeNotEqualTo(Integer value) {
            addCriterion("second_fe <>", value, "secondFe");
            return (Criteria) this;
        }

        public Criteria andSecondFeGreaterThan(Integer value) {
            addCriterion("second_fe >", value, "secondFe");
            return (Criteria) this;
        }

        public Criteria andSecondFeGreaterThanOrEqualTo(Integer value) {
            addCriterion("second_fe >=", value, "secondFe");
            return (Criteria) this;
        }

        public Criteria andSecondFeLessThan(Integer value) {
            addCriterion("second_fe <", value, "secondFe");
            return (Criteria) this;
        }

        public Criteria andSecondFeLessThanOrEqualTo(Integer value) {
            addCriterion("second_fe <=", value, "secondFe");
            return (Criteria) this;
        }

        public Criteria andSecondFeIn(List<Integer> values) {
            addCriterion("second_fe in", values, "secondFe");
            return (Criteria) this;
        }

        public Criteria andSecondFeNotIn(List<Integer> values) {
            addCriterion("second_fe not in", values, "secondFe");
            return (Criteria) this;
        }

        public Criteria andSecondFeBetween(Integer value1, Integer value2) {
            addCriterion("second_fe between", value1, value2, "secondFe");
            return (Criteria) this;
        }

        public Criteria andSecondFeNotBetween(Integer value1, Integer value2) {
            addCriterion("second_fe not between", value1, value2, "secondFe");
            return (Criteria) this;
        }

        public Criteria andSecondQaIsNull() {
            addCriterion("second_qa is null");
            return (Criteria) this;
        }

        public Criteria andSecondQaIsNotNull() {
            addCriterion("second_qa is not null");
            return (Criteria) this;
        }

        public Criteria andSecondQaEqualTo(Integer value) {
            addCriterion("second_qa =", value, "secondQa");
            return (Criteria) this;
        }

        public Criteria andSecondQaNotEqualTo(Integer value) {
            addCriterion("second_qa <>", value, "secondQa");
            return (Criteria) this;
        }

        public Criteria andSecondQaGreaterThan(Integer value) {
            addCriterion("second_qa >", value, "secondQa");
            return (Criteria) this;
        }

        public Criteria andSecondQaGreaterThanOrEqualTo(Integer value) {
            addCriterion("second_qa >=", value, "secondQa");
            return (Criteria) this;
        }

        public Criteria andSecondQaLessThan(Integer value) {
            addCriterion("second_qa <", value, "secondQa");
            return (Criteria) this;
        }

        public Criteria andSecondQaLessThanOrEqualTo(Integer value) {
            addCriterion("second_qa <=", value, "secondQa");
            return (Criteria) this;
        }

        public Criteria andSecondQaIn(List<Integer> values) {
            addCriterion("second_qa in", values, "secondQa");
            return (Criteria) this;
        }

        public Criteria andSecondQaNotIn(List<Integer> values) {
            addCriterion("second_qa not in", values, "secondQa");
            return (Criteria) this;
        }

        public Criteria andSecondQaBetween(Integer value1, Integer value2) {
            addCriterion("second_qa between", value1, value2, "secondQa");
            return (Criteria) this;
        }

        public Criteria andSecondQaNotBetween(Integer value1, Integer value2) {
            addCriterion("second_qa not between", value1, value2, "secondQa");
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

        public Criteria andEnableEqualTo(String value) {
            addCriterion("enable =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(String value) {
            addCriterion("enable <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(String value) {
            addCriterion("enable >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(String value) {
            addCriterion("enable >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(String value) {
            addCriterion("enable <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(String value) {
            addCriterion("enable <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLike(String value) {
            addCriterion("enable like", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotLike(String value) {
            addCriterion("enable not like", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<String> values) {
            addCriterion("enable in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<String> values) {
            addCriterion("enable not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(String value1, String value2) {
            addCriterion("enable between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(String value1, String value2) {
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