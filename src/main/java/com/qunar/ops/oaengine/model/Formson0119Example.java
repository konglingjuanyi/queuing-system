package com.qunar.ops.oaengine.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Formson0119Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limit = -1;

    protected int offset = -1;

    public Formson0119Example() {
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

        public Criteria andField0056IsNull() {
            addCriterion("field0056 is null");
            return (Criteria) this;
        }

        public Criteria andField0056IsNotNull() {
            addCriterion("field0056 is not null");
            return (Criteria) this;
        }

        public Criteria andField0056EqualTo(String value) {
            addCriterion("field0056 =", value, "field0056");
            return (Criteria) this;
        }

        public Criteria andField0056NotEqualTo(String value) {
            addCriterion("field0056 <>", value, "field0056");
            return (Criteria) this;
        }

        public Criteria andField0056GreaterThan(String value) {
            addCriterion("field0056 >", value, "field0056");
            return (Criteria) this;
        }

        public Criteria andField0056GreaterThanOrEqualTo(String value) {
            addCriterion("field0056 >=", value, "field0056");
            return (Criteria) this;
        }

        public Criteria andField0056LessThan(String value) {
            addCriterion("field0056 <", value, "field0056");
            return (Criteria) this;
        }

        public Criteria andField0056LessThanOrEqualTo(String value) {
            addCriterion("field0056 <=", value, "field0056");
            return (Criteria) this;
        }

        public Criteria andField0056Like(String value) {
            addCriterion("field0056 like", value, "field0056");
            return (Criteria) this;
        }

        public Criteria andField0056NotLike(String value) {
            addCriterion("field0056 not like", value, "field0056");
            return (Criteria) this;
        }

        public Criteria andField0056In(List<String> values) {
            addCriterion("field0056 in", values, "field0056");
            return (Criteria) this;
        }

        public Criteria andField0056NotIn(List<String> values) {
            addCriterion("field0056 not in", values, "field0056");
            return (Criteria) this;
        }

        public Criteria andField0056Between(String value1, String value2) {
            addCriterion("field0056 between", value1, value2, "field0056");
            return (Criteria) this;
        }

        public Criteria andField0056NotBetween(String value1, String value2) {
            addCriterion("field0056 not between", value1, value2, "field0056");
            return (Criteria) this;
        }

        public Criteria andField0057IsNull() {
            addCriterion("field0057 is null");
            return (Criteria) this;
        }

        public Criteria andField0057IsNotNull() {
            addCriterion("field0057 is not null");
            return (Criteria) this;
        }

        public Criteria andField0057EqualTo(Long value) {
            addCriterion("field0057 =", value, "field0057");
            return (Criteria) this;
        }

        public Criteria andField0057NotEqualTo(Long value) {
            addCriterion("field0057 <>", value, "field0057");
            return (Criteria) this;
        }

        public Criteria andField0057GreaterThan(Long value) {
            addCriterion("field0057 >", value, "field0057");
            return (Criteria) this;
        }

        public Criteria andField0057GreaterThanOrEqualTo(Long value) {
            addCriterion("field0057 >=", value, "field0057");
            return (Criteria) this;
        }

        public Criteria andField0057LessThan(Long value) {
            addCriterion("field0057 <", value, "field0057");
            return (Criteria) this;
        }

        public Criteria andField0057LessThanOrEqualTo(Long value) {
            addCriterion("field0057 <=", value, "field0057");
            return (Criteria) this;
        }

        public Criteria andField0057In(List<Long> values) {
            addCriterion("field0057 in", values, "field0057");
            return (Criteria) this;
        }

        public Criteria andField0057NotIn(List<Long> values) {
            addCriterion("field0057 not in", values, "field0057");
            return (Criteria) this;
        }

        public Criteria andField0057Between(Long value1, Long value2) {
            addCriterion("field0057 between", value1, value2, "field0057");
            return (Criteria) this;
        }

        public Criteria andField0057NotBetween(Long value1, Long value2) {
            addCriterion("field0057 not between", value1, value2, "field0057");
            return (Criteria) this;
        }

        public Criteria andField0058IsNull() {
            addCriterion("field0058 is null");
            return (Criteria) this;
        }

        public Criteria andField0058IsNotNull() {
            addCriterion("field0058 is not null");
            return (Criteria) this;
        }

        public Criteria andField0058EqualTo(String value) {
            addCriterion("field0058 =", value, "field0058");
            return (Criteria) this;
        }

        public Criteria andField0058NotEqualTo(String value) {
            addCriterion("field0058 <>", value, "field0058");
            return (Criteria) this;
        }

        public Criteria andField0058GreaterThan(String value) {
            addCriterion("field0058 >", value, "field0058");
            return (Criteria) this;
        }

        public Criteria andField0058GreaterThanOrEqualTo(String value) {
            addCriterion("field0058 >=", value, "field0058");
            return (Criteria) this;
        }

        public Criteria andField0058LessThan(String value) {
            addCriterion("field0058 <", value, "field0058");
            return (Criteria) this;
        }

        public Criteria andField0058LessThanOrEqualTo(String value) {
            addCriterion("field0058 <=", value, "field0058");
            return (Criteria) this;
        }

        public Criteria andField0058Like(String value) {
            addCriterion("field0058 like", value, "field0058");
            return (Criteria) this;
        }

        public Criteria andField0058NotLike(String value) {
            addCriterion("field0058 not like", value, "field0058");
            return (Criteria) this;
        }

        public Criteria andField0058In(List<String> values) {
            addCriterion("field0058 in", values, "field0058");
            return (Criteria) this;
        }

        public Criteria andField0058NotIn(List<String> values) {
            addCriterion("field0058 not in", values, "field0058");
            return (Criteria) this;
        }

        public Criteria andField0058Between(String value1, String value2) {
            addCriterion("field0058 between", value1, value2, "field0058");
            return (Criteria) this;
        }

        public Criteria andField0058NotBetween(String value1, String value2) {
            addCriterion("field0058 not between", value1, value2, "field0058");
            return (Criteria) this;
        }

        public Criteria andField0059IsNull() {
            addCriterion("field0059 is null");
            return (Criteria) this;
        }

        public Criteria andField0059IsNotNull() {
            addCriterion("field0059 is not null");
            return (Criteria) this;
        }

        public Criteria andField0059EqualTo(String value) {
            addCriterion("field0059 =", value, "field0059");
            return (Criteria) this;
        }

        public Criteria andField0059NotEqualTo(String value) {
            addCriterion("field0059 <>", value, "field0059");
            return (Criteria) this;
        }

        public Criteria andField0059GreaterThan(String value) {
            addCriterion("field0059 >", value, "field0059");
            return (Criteria) this;
        }

        public Criteria andField0059GreaterThanOrEqualTo(String value) {
            addCriterion("field0059 >=", value, "field0059");
            return (Criteria) this;
        }

        public Criteria andField0059LessThan(String value) {
            addCriterion("field0059 <", value, "field0059");
            return (Criteria) this;
        }

        public Criteria andField0059LessThanOrEqualTo(String value) {
            addCriterion("field0059 <=", value, "field0059");
            return (Criteria) this;
        }

        public Criteria andField0059Like(String value) {
            addCriterion("field0059 like", value, "field0059");
            return (Criteria) this;
        }

        public Criteria andField0059NotLike(String value) {
            addCriterion("field0059 not like", value, "field0059");
            return (Criteria) this;
        }

        public Criteria andField0059In(List<String> values) {
            addCriterion("field0059 in", values, "field0059");
            return (Criteria) this;
        }

        public Criteria andField0059NotIn(List<String> values) {
            addCriterion("field0059 not in", values, "field0059");
            return (Criteria) this;
        }

        public Criteria andField0059Between(String value1, String value2) {
            addCriterion("field0059 between", value1, value2, "field0059");
            return (Criteria) this;
        }

        public Criteria andField0059NotBetween(String value1, String value2) {
            addCriterion("field0059 not between", value1, value2, "field0059");
            return (Criteria) this;
        }

        public Criteria andField0060IsNull() {
            addCriterion("field0060 is null");
            return (Criteria) this;
        }

        public Criteria andField0060IsNotNull() {
            addCriterion("field0060 is not null");
            return (Criteria) this;
        }

        public Criteria andField0060EqualTo(String value) {
            addCriterion("field0060 =", value, "field0060");
            return (Criteria) this;
        }

        public Criteria andField0060NotEqualTo(String value) {
            addCriterion("field0060 <>", value, "field0060");
            return (Criteria) this;
        }

        public Criteria andField0060GreaterThan(String value) {
            addCriterion("field0060 >", value, "field0060");
            return (Criteria) this;
        }

        public Criteria andField0060GreaterThanOrEqualTo(String value) {
            addCriterion("field0060 >=", value, "field0060");
            return (Criteria) this;
        }

        public Criteria andField0060LessThan(String value) {
            addCriterion("field0060 <", value, "field0060");
            return (Criteria) this;
        }

        public Criteria andField0060LessThanOrEqualTo(String value) {
            addCriterion("field0060 <=", value, "field0060");
            return (Criteria) this;
        }

        public Criteria andField0060Like(String value) {
            addCriterion("field0060 like", value, "field0060");
            return (Criteria) this;
        }

        public Criteria andField0060NotLike(String value) {
            addCriterion("field0060 not like", value, "field0060");
            return (Criteria) this;
        }

        public Criteria andField0060In(List<String> values) {
            addCriterion("field0060 in", values, "field0060");
            return (Criteria) this;
        }

        public Criteria andField0060NotIn(List<String> values) {
            addCriterion("field0060 not in", values, "field0060");
            return (Criteria) this;
        }

        public Criteria andField0060Between(String value1, String value2) {
            addCriterion("field0060 between", value1, value2, "field0060");
            return (Criteria) this;
        }

        public Criteria andField0060NotBetween(String value1, String value2) {
            addCriterion("field0060 not between", value1, value2, "field0060");
            return (Criteria) this;
        }

        public Criteria andField0061IsNull() {
            addCriterion("field0061 is null");
            return (Criteria) this;
        }

        public Criteria andField0061IsNotNull() {
            addCriterion("field0061 is not null");
            return (Criteria) this;
        }

        public Criteria andField0061EqualTo(String value) {
            addCriterion("field0061 =", value, "field0061");
            return (Criteria) this;
        }

        public Criteria andField0061NotEqualTo(String value) {
            addCriterion("field0061 <>", value, "field0061");
            return (Criteria) this;
        }

        public Criteria andField0061GreaterThan(String value) {
            addCriterion("field0061 >", value, "field0061");
            return (Criteria) this;
        }

        public Criteria andField0061GreaterThanOrEqualTo(String value) {
            addCriterion("field0061 >=", value, "field0061");
            return (Criteria) this;
        }

        public Criteria andField0061LessThan(String value) {
            addCriterion("field0061 <", value, "field0061");
            return (Criteria) this;
        }

        public Criteria andField0061LessThanOrEqualTo(String value) {
            addCriterion("field0061 <=", value, "field0061");
            return (Criteria) this;
        }

        public Criteria andField0061Like(String value) {
            addCriterion("field0061 like", value, "field0061");
            return (Criteria) this;
        }

        public Criteria andField0061NotLike(String value) {
            addCriterion("field0061 not like", value, "field0061");
            return (Criteria) this;
        }

        public Criteria andField0061In(List<String> values) {
            addCriterion("field0061 in", values, "field0061");
            return (Criteria) this;
        }

        public Criteria andField0061NotIn(List<String> values) {
            addCriterion("field0061 not in", values, "field0061");
            return (Criteria) this;
        }

        public Criteria andField0061Between(String value1, String value2) {
            addCriterion("field0061 between", value1, value2, "field0061");
            return (Criteria) this;
        }

        public Criteria andField0061NotBetween(String value1, String value2) {
            addCriterion("field0061 not between", value1, value2, "field0061");
            return (Criteria) this;
        }

        public Criteria andField0062IsNull() {
            addCriterion("field0062 is null");
            return (Criteria) this;
        }

        public Criteria andField0062IsNotNull() {
            addCriterion("field0062 is not null");
            return (Criteria) this;
        }

        public Criteria andField0062EqualTo(Date value) {
            addCriterionForJDBCDate("field0062 =", value, "field0062");
            return (Criteria) this;
        }

        public Criteria andField0062NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0062 <>", value, "field0062");
            return (Criteria) this;
        }

        public Criteria andField0062GreaterThan(Date value) {
            addCriterionForJDBCDate("field0062 >", value, "field0062");
            return (Criteria) this;
        }

        public Criteria andField0062GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0062 >=", value, "field0062");
            return (Criteria) this;
        }

        public Criteria andField0062LessThan(Date value) {
            addCriterionForJDBCDate("field0062 <", value, "field0062");
            return (Criteria) this;
        }

        public Criteria andField0062LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0062 <=", value, "field0062");
            return (Criteria) this;
        }

        public Criteria andField0062In(List<Date> values) {
            addCriterionForJDBCDate("field0062 in", values, "field0062");
            return (Criteria) this;
        }

        public Criteria andField0062NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0062 not in", values, "field0062");
            return (Criteria) this;
        }

        public Criteria andField0062Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0062 between", value1, value2, "field0062");
            return (Criteria) this;
        }

        public Criteria andField0062NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0062 not between", value1, value2, "field0062");
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

        public Criteria andField0088IsNull() {
            addCriterion("field0088 is null");
            return (Criteria) this;
        }

        public Criteria andField0088IsNotNull() {
            addCriterion("field0088 is not null");
            return (Criteria) this;
        }

        public Criteria andField0088EqualTo(String value) {
            addCriterion("field0088 =", value, "field0088");
            return (Criteria) this;
        }

        public Criteria andField0088NotEqualTo(String value) {
            addCriterion("field0088 <>", value, "field0088");
            return (Criteria) this;
        }

        public Criteria andField0088GreaterThan(String value) {
            addCriterion("field0088 >", value, "field0088");
            return (Criteria) this;
        }

        public Criteria andField0088GreaterThanOrEqualTo(String value) {
            addCriterion("field0088 >=", value, "field0088");
            return (Criteria) this;
        }

        public Criteria andField0088LessThan(String value) {
            addCriterion("field0088 <", value, "field0088");
            return (Criteria) this;
        }

        public Criteria andField0088LessThanOrEqualTo(String value) {
            addCriterion("field0088 <=", value, "field0088");
            return (Criteria) this;
        }

        public Criteria andField0088Like(String value) {
            addCriterion("field0088 like", value, "field0088");
            return (Criteria) this;
        }

        public Criteria andField0088NotLike(String value) {
            addCriterion("field0088 not like", value, "field0088");
            return (Criteria) this;
        }

        public Criteria andField0088In(List<String> values) {
            addCriterion("field0088 in", values, "field0088");
            return (Criteria) this;
        }

        public Criteria andField0088NotIn(List<String> values) {
            addCriterion("field0088 not in", values, "field0088");
            return (Criteria) this;
        }

        public Criteria andField0088Between(String value1, String value2) {
            addCriterion("field0088 between", value1, value2, "field0088");
            return (Criteria) this;
        }

        public Criteria andField0088NotBetween(String value1, String value2) {
            addCriterion("field0088 not between", value1, value2, "field0088");
            return (Criteria) this;
        }

        public Criteria andField0095IsNull() {
            addCriterion("field0095 is null");
            return (Criteria) this;
        }

        public Criteria andField0095IsNotNull() {
            addCriterion("field0095 is not null");
            return (Criteria) this;
        }

        public Criteria andField0095EqualTo(BigDecimal value) {
            addCriterion("field0095 =", value, "field0095");
            return (Criteria) this;
        }

        public Criteria andField0095NotEqualTo(BigDecimal value) {
            addCriterion("field0095 <>", value, "field0095");
            return (Criteria) this;
        }

        public Criteria andField0095GreaterThan(BigDecimal value) {
            addCriterion("field0095 >", value, "field0095");
            return (Criteria) this;
        }

        public Criteria andField0095GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("field0095 >=", value, "field0095");
            return (Criteria) this;
        }

        public Criteria andField0095LessThan(BigDecimal value) {
            addCriterion("field0095 <", value, "field0095");
            return (Criteria) this;
        }

        public Criteria andField0095LessThanOrEqualTo(BigDecimal value) {
            addCriterion("field0095 <=", value, "field0095");
            return (Criteria) this;
        }

        public Criteria andField0095In(List<BigDecimal> values) {
            addCriterion("field0095 in", values, "field0095");
            return (Criteria) this;
        }

        public Criteria andField0095NotIn(List<BigDecimal> values) {
            addCriterion("field0095 not in", values, "field0095");
            return (Criteria) this;
        }

        public Criteria andField0095Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("field0095 between", value1, value2, "field0095");
            return (Criteria) this;
        }

        public Criteria andField0095NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("field0095 not between", value1, value2, "field0095");
            return (Criteria) this;
        }

        public Criteria andField0098IsNull() {
            addCriterion("field0098 is null");
            return (Criteria) this;
        }

        public Criteria andField0098IsNotNull() {
            addCriterion("field0098 is not null");
            return (Criteria) this;
        }

        public Criteria andField0098EqualTo(String value) {
            addCriterion("field0098 =", value, "field0098");
            return (Criteria) this;
        }

        public Criteria andField0098NotEqualTo(String value) {
            addCriterion("field0098 <>", value, "field0098");
            return (Criteria) this;
        }

        public Criteria andField0098GreaterThan(String value) {
            addCriterion("field0098 >", value, "field0098");
            return (Criteria) this;
        }

        public Criteria andField0098GreaterThanOrEqualTo(String value) {
            addCriterion("field0098 >=", value, "field0098");
            return (Criteria) this;
        }

        public Criteria andField0098LessThan(String value) {
            addCriterion("field0098 <", value, "field0098");
            return (Criteria) this;
        }

        public Criteria andField0098LessThanOrEqualTo(String value) {
            addCriterion("field0098 <=", value, "field0098");
            return (Criteria) this;
        }

        public Criteria andField0098Like(String value) {
            addCriterion("field0098 like", value, "field0098");
            return (Criteria) this;
        }

        public Criteria andField0098NotLike(String value) {
            addCriterion("field0098 not like", value, "field0098");
            return (Criteria) this;
        }

        public Criteria andField0098In(List<String> values) {
            addCriterion("field0098 in", values, "field0098");
            return (Criteria) this;
        }

        public Criteria andField0098NotIn(List<String> values) {
            addCriterion("field0098 not in", values, "field0098");
            return (Criteria) this;
        }

        public Criteria andField0098Between(String value1, String value2) {
            addCriterion("field0098 between", value1, value2, "field0098");
            return (Criteria) this;
        }

        public Criteria andField0098NotBetween(String value1, String value2) {
            addCriterion("field0098 not between", value1, value2, "field0098");
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