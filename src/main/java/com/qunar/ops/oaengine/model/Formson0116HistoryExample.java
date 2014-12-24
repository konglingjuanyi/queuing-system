package com.qunar.ops.oaengine.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Formson0116HistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limit = -1;

    protected int offset = -1;

    public Formson0116HistoryExample() {
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

        public Criteria andField0041IsNull() {
            addCriterion("field0041 is null");
            return (Criteria) this;
        }

        public Criteria andField0041IsNotNull() {
            addCriterion("field0041 is not null");
            return (Criteria) this;
        }

        public Criteria andField0041EqualTo(Date value) {
            addCriterionForJDBCDate("field0041 =", value, "field0041");
            return (Criteria) this;
        }

        public Criteria andField0041NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0041 <>", value, "field0041");
            return (Criteria) this;
        }

        public Criteria andField0041GreaterThan(Date value) {
            addCriterionForJDBCDate("field0041 >", value, "field0041");
            return (Criteria) this;
        }

        public Criteria andField0041GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0041 >=", value, "field0041");
            return (Criteria) this;
        }

        public Criteria andField0041LessThan(Date value) {
            addCriterionForJDBCDate("field0041 <", value, "field0041");
            return (Criteria) this;
        }

        public Criteria andField0041LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0041 <=", value, "field0041");
            return (Criteria) this;
        }

        public Criteria andField0041In(List<Date> values) {
            addCriterionForJDBCDate("field0041 in", values, "field0041");
            return (Criteria) this;
        }

        public Criteria andField0041NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0041 not in", values, "field0041");
            return (Criteria) this;
        }

        public Criteria andField0041Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0041 between", value1, value2, "field0041");
            return (Criteria) this;
        }

        public Criteria andField0041NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0041 not between", value1, value2, "field0041");
            return (Criteria) this;
        }

        public Criteria andField0042IsNull() {
            addCriterion("field0042 is null");
            return (Criteria) this;
        }

        public Criteria andField0042IsNotNull() {
            addCriterion("field0042 is not null");
            return (Criteria) this;
        }

        public Criteria andField0042EqualTo(String value) {
            addCriterion("field0042 =", value, "field0042");
            return (Criteria) this;
        }

        public Criteria andField0042NotEqualTo(String value) {
            addCriterion("field0042 <>", value, "field0042");
            return (Criteria) this;
        }

        public Criteria andField0042GreaterThan(String value) {
            addCriterion("field0042 >", value, "field0042");
            return (Criteria) this;
        }

        public Criteria andField0042GreaterThanOrEqualTo(String value) {
            addCriterion("field0042 >=", value, "field0042");
            return (Criteria) this;
        }

        public Criteria andField0042LessThan(String value) {
            addCriterion("field0042 <", value, "field0042");
            return (Criteria) this;
        }

        public Criteria andField0042LessThanOrEqualTo(String value) {
            addCriterion("field0042 <=", value, "field0042");
            return (Criteria) this;
        }

        public Criteria andField0042Like(String value) {
            addCriterion("field0042 like", value, "field0042");
            return (Criteria) this;
        }

        public Criteria andField0042NotLike(String value) {
            addCriterion("field0042 not like", value, "field0042");
            return (Criteria) this;
        }

        public Criteria andField0042In(List<String> values) {
            addCriterion("field0042 in", values, "field0042");
            return (Criteria) this;
        }

        public Criteria andField0042NotIn(List<String> values) {
            addCriterion("field0042 not in", values, "field0042");
            return (Criteria) this;
        }

        public Criteria andField0042Between(String value1, String value2) {
            addCriterion("field0042 between", value1, value2, "field0042");
            return (Criteria) this;
        }

        public Criteria andField0042NotBetween(String value1, String value2) {
            addCriterion("field0042 not between", value1, value2, "field0042");
            return (Criteria) this;
        }

        public Criteria andField0043IsNull() {
            addCriterion("field0043 is null");
            return (Criteria) this;
        }

        public Criteria andField0043IsNotNull() {
            addCriterion("field0043 is not null");
            return (Criteria) this;
        }

        public Criteria andField0043EqualTo(Long value) {
            addCriterion("field0043 =", value, "field0043");
            return (Criteria) this;
        }

        public Criteria andField0043NotEqualTo(Long value) {
            addCriterion("field0043 <>", value, "field0043");
            return (Criteria) this;
        }

        public Criteria andField0043GreaterThan(Long value) {
            addCriterion("field0043 >", value, "field0043");
            return (Criteria) this;
        }

        public Criteria andField0043GreaterThanOrEqualTo(Long value) {
            addCriterion("field0043 >=", value, "field0043");
            return (Criteria) this;
        }

        public Criteria andField0043LessThan(Long value) {
            addCriterion("field0043 <", value, "field0043");
            return (Criteria) this;
        }

        public Criteria andField0043LessThanOrEqualTo(Long value) {
            addCriterion("field0043 <=", value, "field0043");
            return (Criteria) this;
        }

        public Criteria andField0043In(List<Long> values) {
            addCriterion("field0043 in", values, "field0043");
            return (Criteria) this;
        }

        public Criteria andField0043NotIn(List<Long> values) {
            addCriterion("field0043 not in", values, "field0043");
            return (Criteria) this;
        }

        public Criteria andField0043Between(Long value1, Long value2) {
            addCriterion("field0043 between", value1, value2, "field0043");
            return (Criteria) this;
        }

        public Criteria andField0043NotBetween(Long value1, Long value2) {
            addCriterion("field0043 not between", value1, value2, "field0043");
            return (Criteria) this;
        }

        public Criteria andField0044IsNull() {
            addCriterion("field0044 is null");
            return (Criteria) this;
        }

        public Criteria andField0044IsNotNull() {
            addCriterion("field0044 is not null");
            return (Criteria) this;
        }

        public Criteria andField0044EqualTo(String value) {
            addCriterion("field0044 =", value, "field0044");
            return (Criteria) this;
        }

        public Criteria andField0044NotEqualTo(String value) {
            addCriterion("field0044 <>", value, "field0044");
            return (Criteria) this;
        }

        public Criteria andField0044GreaterThan(String value) {
            addCriterion("field0044 >", value, "field0044");
            return (Criteria) this;
        }

        public Criteria andField0044GreaterThanOrEqualTo(String value) {
            addCriterion("field0044 >=", value, "field0044");
            return (Criteria) this;
        }

        public Criteria andField0044LessThan(String value) {
            addCriterion("field0044 <", value, "field0044");
            return (Criteria) this;
        }

        public Criteria andField0044LessThanOrEqualTo(String value) {
            addCriterion("field0044 <=", value, "field0044");
            return (Criteria) this;
        }

        public Criteria andField0044Like(String value) {
            addCriterion("field0044 like", value, "field0044");
            return (Criteria) this;
        }

        public Criteria andField0044NotLike(String value) {
            addCriterion("field0044 not like", value, "field0044");
            return (Criteria) this;
        }

        public Criteria andField0044In(List<String> values) {
            addCriterion("field0044 in", values, "field0044");
            return (Criteria) this;
        }

        public Criteria andField0044NotIn(List<String> values) {
            addCriterion("field0044 not in", values, "field0044");
            return (Criteria) this;
        }

        public Criteria andField0044Between(String value1, String value2) {
            addCriterion("field0044 between", value1, value2, "field0044");
            return (Criteria) this;
        }

        public Criteria andField0044NotBetween(String value1, String value2) {
            addCriterion("field0044 not between", value1, value2, "field0044");
            return (Criteria) this;
        }

        public Criteria andField0045IsNull() {
            addCriterion("field0045 is null");
            return (Criteria) this;
        }

        public Criteria andField0045IsNotNull() {
            addCriterion("field0045 is not null");
            return (Criteria) this;
        }

        public Criteria andField0045EqualTo(String value) {
            addCriterion("field0045 =", value, "field0045");
            return (Criteria) this;
        }

        public Criteria andField0045NotEqualTo(String value) {
            addCriterion("field0045 <>", value, "field0045");
            return (Criteria) this;
        }

        public Criteria andField0045GreaterThan(String value) {
            addCriterion("field0045 >", value, "field0045");
            return (Criteria) this;
        }

        public Criteria andField0045GreaterThanOrEqualTo(String value) {
            addCriterion("field0045 >=", value, "field0045");
            return (Criteria) this;
        }

        public Criteria andField0045LessThan(String value) {
            addCriterion("field0045 <", value, "field0045");
            return (Criteria) this;
        }

        public Criteria andField0045LessThanOrEqualTo(String value) {
            addCriterion("field0045 <=", value, "field0045");
            return (Criteria) this;
        }

        public Criteria andField0045Like(String value) {
            addCriterion("field0045 like", value, "field0045");
            return (Criteria) this;
        }

        public Criteria andField0045NotLike(String value) {
            addCriterion("field0045 not like", value, "field0045");
            return (Criteria) this;
        }

        public Criteria andField0045In(List<String> values) {
            addCriterion("field0045 in", values, "field0045");
            return (Criteria) this;
        }

        public Criteria andField0045NotIn(List<String> values) {
            addCriterion("field0045 not in", values, "field0045");
            return (Criteria) this;
        }

        public Criteria andField0045Between(String value1, String value2) {
            addCriterion("field0045 between", value1, value2, "field0045");
            return (Criteria) this;
        }

        public Criteria andField0045NotBetween(String value1, String value2) {
            addCriterion("field0045 not between", value1, value2, "field0045");
            return (Criteria) this;
        }

        public Criteria andField0046IsNull() {
            addCriterion("field0046 is null");
            return (Criteria) this;
        }

        public Criteria andField0046IsNotNull() {
            addCriterion("field0046 is not null");
            return (Criteria) this;
        }

        public Criteria andField0046EqualTo(String value) {
            addCriterion("field0046 =", value, "field0046");
            return (Criteria) this;
        }

        public Criteria andField0046NotEqualTo(String value) {
            addCriterion("field0046 <>", value, "field0046");
            return (Criteria) this;
        }

        public Criteria andField0046GreaterThan(String value) {
            addCriterion("field0046 >", value, "field0046");
            return (Criteria) this;
        }

        public Criteria andField0046GreaterThanOrEqualTo(String value) {
            addCriterion("field0046 >=", value, "field0046");
            return (Criteria) this;
        }

        public Criteria andField0046LessThan(String value) {
            addCriterion("field0046 <", value, "field0046");
            return (Criteria) this;
        }

        public Criteria andField0046LessThanOrEqualTo(String value) {
            addCriterion("field0046 <=", value, "field0046");
            return (Criteria) this;
        }

        public Criteria andField0046Like(String value) {
            addCriterion("field0046 like", value, "field0046");
            return (Criteria) this;
        }

        public Criteria andField0046NotLike(String value) {
            addCriterion("field0046 not like", value, "field0046");
            return (Criteria) this;
        }

        public Criteria andField0046In(List<String> values) {
            addCriterion("field0046 in", values, "field0046");
            return (Criteria) this;
        }

        public Criteria andField0046NotIn(List<String> values) {
            addCriterion("field0046 not in", values, "field0046");
            return (Criteria) this;
        }

        public Criteria andField0046Between(String value1, String value2) {
            addCriterion("field0046 between", value1, value2, "field0046");
            return (Criteria) this;
        }

        public Criteria andField0046NotBetween(String value1, String value2) {
            addCriterion("field0046 not between", value1, value2, "field0046");
            return (Criteria) this;
        }

        public Criteria andField0047IsNull() {
            addCriterion("field0047 is null");
            return (Criteria) this;
        }

        public Criteria andField0047IsNotNull() {
            addCriterion("field0047 is not null");
            return (Criteria) this;
        }

        public Criteria andField0047EqualTo(String value) {
            addCriterion("field0047 =", value, "field0047");
            return (Criteria) this;
        }

        public Criteria andField0047NotEqualTo(String value) {
            addCriterion("field0047 <>", value, "field0047");
            return (Criteria) this;
        }

        public Criteria andField0047GreaterThan(String value) {
            addCriterion("field0047 >", value, "field0047");
            return (Criteria) this;
        }

        public Criteria andField0047GreaterThanOrEqualTo(String value) {
            addCriterion("field0047 >=", value, "field0047");
            return (Criteria) this;
        }

        public Criteria andField0047LessThan(String value) {
            addCriterion("field0047 <", value, "field0047");
            return (Criteria) this;
        }

        public Criteria andField0047LessThanOrEqualTo(String value) {
            addCriterion("field0047 <=", value, "field0047");
            return (Criteria) this;
        }

        public Criteria andField0047Like(String value) {
            addCriterion("field0047 like", value, "field0047");
            return (Criteria) this;
        }

        public Criteria andField0047NotLike(String value) {
            addCriterion("field0047 not like", value, "field0047");
            return (Criteria) this;
        }

        public Criteria andField0047In(List<String> values) {
            addCriterion("field0047 in", values, "field0047");
            return (Criteria) this;
        }

        public Criteria andField0047NotIn(List<String> values) {
            addCriterion("field0047 not in", values, "field0047");
            return (Criteria) this;
        }

        public Criteria andField0047Between(String value1, String value2) {
            addCriterion("field0047 between", value1, value2, "field0047");
            return (Criteria) this;
        }

        public Criteria andField0047NotBetween(String value1, String value2) {
            addCriterion("field0047 not between", value1, value2, "field0047");
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

        public Criteria andRatifyIsNull() {
            addCriterion("ratify is null");
            return (Criteria) this;
        }

        public Criteria andRatifyIsNotNull() {
            addCriterion("ratify is not null");
            return (Criteria) this;
        }

        public Criteria andRatifyEqualTo(Long value) {
            addCriterion("ratify =", value, "ratify");
            return (Criteria) this;
        }

        public Criteria andRatifyNotEqualTo(Long value) {
            addCriterion("ratify <>", value, "ratify");
            return (Criteria) this;
        }

        public Criteria andRatifyGreaterThan(Long value) {
            addCriterion("ratify >", value, "ratify");
            return (Criteria) this;
        }

        public Criteria andRatifyGreaterThanOrEqualTo(Long value) {
            addCriterion("ratify >=", value, "ratify");
            return (Criteria) this;
        }

        public Criteria andRatifyLessThan(Long value) {
            addCriterion("ratify <", value, "ratify");
            return (Criteria) this;
        }

        public Criteria andRatifyLessThanOrEqualTo(Long value) {
            addCriterion("ratify <=", value, "ratify");
            return (Criteria) this;
        }

        public Criteria andRatifyIn(List<Long> values) {
            addCriterion("ratify in", values, "ratify");
            return (Criteria) this;
        }

        public Criteria andRatifyNotIn(List<Long> values) {
            addCriterion("ratify not in", values, "ratify");
            return (Criteria) this;
        }

        public Criteria andRatifyBetween(Long value1, Long value2) {
            addCriterion("ratify between", value1, value2, "ratify");
            return (Criteria) this;
        }

        public Criteria andRatifyNotBetween(Long value1, Long value2) {
            addCriterion("ratify not between", value1, value2, "ratify");
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