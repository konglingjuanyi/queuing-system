package com.qunar.ops.oaengine.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Formson0118LogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limit = -1;

    protected int offset = -1;

    public Formson0118LogExample() {
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

        public Criteria andFormmain0114idIsNull() {
            addCriterion("formmain_0114id is null");
            return (Criteria) this;
        }

        public Criteria andFormmain0114idIsNotNull() {
            addCriterion("formmain_0114id is not null");
            return (Criteria) this;
        }

        public Criteria andFormmain0114idEqualTo(Long value) {
            addCriterion("formmain_0114id =", value, "formmain0114id");
            return (Criteria) this;
        }

        public Criteria andFormmain0114idNotEqualTo(Long value) {
            addCriterion("formmain_0114id <>", value, "formmain0114id");
            return (Criteria) this;
        }

        public Criteria andFormmain0114idGreaterThan(Long value) {
            addCriterion("formmain_0114id >", value, "formmain0114id");
            return (Criteria) this;
        }

        public Criteria andFormmain0114idGreaterThanOrEqualTo(Long value) {
            addCriterion("formmain_0114id >=", value, "formmain0114id");
            return (Criteria) this;
        }

        public Criteria andFormmain0114idLessThan(Long value) {
            addCriterion("formmain_0114id <", value, "formmain0114id");
            return (Criteria) this;
        }

        public Criteria andFormmain0114idLessThanOrEqualTo(Long value) {
            addCriterion("formmain_0114id <=", value, "formmain0114id");
            return (Criteria) this;
        }

        public Criteria andFormmain0114idIn(List<Long> values) {
            addCriterion("formmain_0114id in", values, "formmain0114id");
            return (Criteria) this;
        }

        public Criteria andFormmain0114idNotIn(List<Long> values) {
            addCriterion("formmain_0114id not in", values, "formmain0114id");
            return (Criteria) this;
        }

        public Criteria andFormmain0114idBetween(Long value1, Long value2) {
            addCriterion("formmain_0114id between", value1, value2, "formmain0114id");
            return (Criteria) this;
        }

        public Criteria andFormmain0114idNotBetween(Long value1, Long value2) {
            addCriterion("formmain_0114id not between", value1, value2, "formmain0114id");
            return (Criteria) this;
        }

        public Criteria andField0051IsNull() {
            addCriterion("field0051 is null");
            return (Criteria) this;
        }

        public Criteria andField0051IsNotNull() {
            addCriterion("field0051 is not null");
            return (Criteria) this;
        }

        public Criteria andField0051EqualTo(Long value) {
            addCriterion("field0051 =", value, "field0051");
            return (Criteria) this;
        }

        public Criteria andField0051NotEqualTo(Long value) {
            addCriterion("field0051 <>", value, "field0051");
            return (Criteria) this;
        }

        public Criteria andField0051GreaterThan(Long value) {
            addCriterion("field0051 >", value, "field0051");
            return (Criteria) this;
        }

        public Criteria andField0051GreaterThanOrEqualTo(Long value) {
            addCriterion("field0051 >=", value, "field0051");
            return (Criteria) this;
        }

        public Criteria andField0051LessThan(Long value) {
            addCriterion("field0051 <", value, "field0051");
            return (Criteria) this;
        }

        public Criteria andField0051LessThanOrEqualTo(Long value) {
            addCriterion("field0051 <=", value, "field0051");
            return (Criteria) this;
        }

        public Criteria andField0051In(List<Long> values) {
            addCriterion("field0051 in", values, "field0051");
            return (Criteria) this;
        }

        public Criteria andField0051NotIn(List<Long> values) {
            addCriterion("field0051 not in", values, "field0051");
            return (Criteria) this;
        }

        public Criteria andField0051Between(Long value1, Long value2) {
            addCriterion("field0051 between", value1, value2, "field0051");
            return (Criteria) this;
        }

        public Criteria andField0051NotBetween(Long value1, Long value2) {
            addCriterion("field0051 not between", value1, value2, "field0051");
            return (Criteria) this;
        }

        public Criteria andField0052IsNull() {
            addCriterion("field0052 is null");
            return (Criteria) this;
        }

        public Criteria andField0052IsNotNull() {
            addCriterion("field0052 is not null");
            return (Criteria) this;
        }

        public Criteria andField0052EqualTo(String value) {
            addCriterion("field0052 =", value, "field0052");
            return (Criteria) this;
        }

        public Criteria andField0052NotEqualTo(String value) {
            addCriterion("field0052 <>", value, "field0052");
            return (Criteria) this;
        }

        public Criteria andField0052GreaterThan(String value) {
            addCriterion("field0052 >", value, "field0052");
            return (Criteria) this;
        }

        public Criteria andField0052GreaterThanOrEqualTo(String value) {
            addCriterion("field0052 >=", value, "field0052");
            return (Criteria) this;
        }

        public Criteria andField0052LessThan(String value) {
            addCriterion("field0052 <", value, "field0052");
            return (Criteria) this;
        }

        public Criteria andField0052LessThanOrEqualTo(String value) {
            addCriterion("field0052 <=", value, "field0052");
            return (Criteria) this;
        }

        public Criteria andField0052Like(String value) {
            addCriterion("field0052 like", value, "field0052");
            return (Criteria) this;
        }

        public Criteria andField0052NotLike(String value) {
            addCriterion("field0052 not like", value, "field0052");
            return (Criteria) this;
        }

        public Criteria andField0052In(List<String> values) {
            addCriterion("field0052 in", values, "field0052");
            return (Criteria) this;
        }

        public Criteria andField0052NotIn(List<String> values) {
            addCriterion("field0052 not in", values, "field0052");
            return (Criteria) this;
        }

        public Criteria andField0052Between(String value1, String value2) {
            addCriterion("field0052 between", value1, value2, "field0052");
            return (Criteria) this;
        }

        public Criteria andField0052NotBetween(String value1, String value2) {
            addCriterion("field0052 not between", value1, value2, "field0052");
            return (Criteria) this;
        }

        public Criteria andField0053IsNull() {
            addCriterion("field0053 is null");
            return (Criteria) this;
        }

        public Criteria andField0053IsNotNull() {
            addCriterion("field0053 is not null");
            return (Criteria) this;
        }

        public Criteria andField0053EqualTo(Date value) {
            addCriterionForJDBCDate("field0053 =", value, "field0053");
            return (Criteria) this;
        }

        public Criteria andField0053NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0053 <>", value, "field0053");
            return (Criteria) this;
        }

        public Criteria andField0053GreaterThan(Date value) {
            addCriterionForJDBCDate("field0053 >", value, "field0053");
            return (Criteria) this;
        }

        public Criteria andField0053GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0053 >=", value, "field0053");
            return (Criteria) this;
        }

        public Criteria andField0053LessThan(Date value) {
            addCriterionForJDBCDate("field0053 <", value, "field0053");
            return (Criteria) this;
        }

        public Criteria andField0053LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0053 <=", value, "field0053");
            return (Criteria) this;
        }

        public Criteria andField0053In(List<Date> values) {
            addCriterionForJDBCDate("field0053 in", values, "field0053");
            return (Criteria) this;
        }

        public Criteria andField0053NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0053 not in", values, "field0053");
            return (Criteria) this;
        }

        public Criteria andField0053Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0053 between", value1, value2, "field0053");
            return (Criteria) this;
        }

        public Criteria andField0053NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0053 not between", value1, value2, "field0053");
            return (Criteria) this;
        }

        public Criteria andField0054IsNull() {
            addCriterion("field0054 is null");
            return (Criteria) this;
        }

        public Criteria andField0054IsNotNull() {
            addCriterion("field0054 is not null");
            return (Criteria) this;
        }

        public Criteria andField0054EqualTo(String value) {
            addCriterion("field0054 =", value, "field0054");
            return (Criteria) this;
        }

        public Criteria andField0054NotEqualTo(String value) {
            addCriterion("field0054 <>", value, "field0054");
            return (Criteria) this;
        }

        public Criteria andField0054GreaterThan(String value) {
            addCriterion("field0054 >", value, "field0054");
            return (Criteria) this;
        }

        public Criteria andField0054GreaterThanOrEqualTo(String value) {
            addCriterion("field0054 >=", value, "field0054");
            return (Criteria) this;
        }

        public Criteria andField0054LessThan(String value) {
            addCriterion("field0054 <", value, "field0054");
            return (Criteria) this;
        }

        public Criteria andField0054LessThanOrEqualTo(String value) {
            addCriterion("field0054 <=", value, "field0054");
            return (Criteria) this;
        }

        public Criteria andField0054Like(String value) {
            addCriterion("field0054 like", value, "field0054");
            return (Criteria) this;
        }

        public Criteria andField0054NotLike(String value) {
            addCriterion("field0054 not like", value, "field0054");
            return (Criteria) this;
        }

        public Criteria andField0054In(List<String> values) {
            addCriterion("field0054 in", values, "field0054");
            return (Criteria) this;
        }

        public Criteria andField0054NotIn(List<String> values) {
            addCriterion("field0054 not in", values, "field0054");
            return (Criteria) this;
        }

        public Criteria andField0054Between(String value1, String value2) {
            addCriterion("field0054 between", value1, value2, "field0054");
            return (Criteria) this;
        }

        public Criteria andField0054NotBetween(String value1, String value2) {
            addCriterion("field0054 not between", value1, value2, "field0054");
            return (Criteria) this;
        }

        public Criteria andField0055IsNull() {
            addCriterion("field0055 is null");
            return (Criteria) this;
        }

        public Criteria andField0055IsNotNull() {
            addCriterion("field0055 is not null");
            return (Criteria) this;
        }

        public Criteria andField0055EqualTo(String value) {
            addCriterion("field0055 =", value, "field0055");
            return (Criteria) this;
        }

        public Criteria andField0055NotEqualTo(String value) {
            addCriterion("field0055 <>", value, "field0055");
            return (Criteria) this;
        }

        public Criteria andField0055GreaterThan(String value) {
            addCriterion("field0055 >", value, "field0055");
            return (Criteria) this;
        }

        public Criteria andField0055GreaterThanOrEqualTo(String value) {
            addCriterion("field0055 >=", value, "field0055");
            return (Criteria) this;
        }

        public Criteria andField0055LessThan(String value) {
            addCriterion("field0055 <", value, "field0055");
            return (Criteria) this;
        }

        public Criteria andField0055LessThanOrEqualTo(String value) {
            addCriterion("field0055 <=", value, "field0055");
            return (Criteria) this;
        }

        public Criteria andField0055Like(String value) {
            addCriterion("field0055 like", value, "field0055");
            return (Criteria) this;
        }

        public Criteria andField0055NotLike(String value) {
            addCriterion("field0055 not like", value, "field0055");
            return (Criteria) this;
        }

        public Criteria andField0055In(List<String> values) {
            addCriterion("field0055 in", values, "field0055");
            return (Criteria) this;
        }

        public Criteria andField0055NotIn(List<String> values) {
            addCriterion("field0055 not in", values, "field0055");
            return (Criteria) this;
        }

        public Criteria andField0055Between(String value1, String value2) {
            addCriterion("field0055 between", value1, value2, "field0055");
            return (Criteria) this;
        }

        public Criteria andField0055NotBetween(String value1, String value2) {
            addCriterion("field0055 not between", value1, value2, "field0055");
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

        public Criteria andField0089IsNull() {
            addCriterion("field0089 is null");
            return (Criteria) this;
        }

        public Criteria andField0089IsNotNull() {
            addCriterion("field0089 is not null");
            return (Criteria) this;
        }

        public Criteria andField0089EqualTo(String value) {
            addCriterion("field0089 =", value, "field0089");
            return (Criteria) this;
        }

        public Criteria andField0089NotEqualTo(String value) {
            addCriterion("field0089 <>", value, "field0089");
            return (Criteria) this;
        }

        public Criteria andField0089GreaterThan(String value) {
            addCriterion("field0089 >", value, "field0089");
            return (Criteria) this;
        }

        public Criteria andField0089GreaterThanOrEqualTo(String value) {
            addCriterion("field0089 >=", value, "field0089");
            return (Criteria) this;
        }

        public Criteria andField0089LessThan(String value) {
            addCriterion("field0089 <", value, "field0089");
            return (Criteria) this;
        }

        public Criteria andField0089LessThanOrEqualTo(String value) {
            addCriterion("field0089 <=", value, "field0089");
            return (Criteria) this;
        }

        public Criteria andField0089Like(String value) {
            addCriterion("field0089 like", value, "field0089");
            return (Criteria) this;
        }

        public Criteria andField0089NotLike(String value) {
            addCriterion("field0089 not like", value, "field0089");
            return (Criteria) this;
        }

        public Criteria andField0089In(List<String> values) {
            addCriterion("field0089 in", values, "field0089");
            return (Criteria) this;
        }

        public Criteria andField0089NotIn(List<String> values) {
            addCriterion("field0089 not in", values, "field0089");
            return (Criteria) this;
        }

        public Criteria andField0089Between(String value1, String value2) {
            addCriterion("field0089 between", value1, value2, "field0089");
            return (Criteria) this;
        }

        public Criteria andField0089NotBetween(String value1, String value2) {
            addCriterion("field0089 not between", value1, value2, "field0089");
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