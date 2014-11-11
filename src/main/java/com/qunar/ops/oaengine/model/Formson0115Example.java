package com.qunar.ops.oaengine.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Formson0115Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limit = -1;

    protected int offset = -1;

    public Formson0115Example() {
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

        public Criteria andField0034IsNull() {
            addCriterion("field0034 is null");
            return (Criteria) this;
        }

        public Criteria andField0034IsNotNull() {
            addCriterion("field0034 is not null");
            return (Criteria) this;
        }

        public Criteria andField0034EqualTo(Date value) {
            addCriterionForJDBCDate("field0034 =", value, "field0034");
            return (Criteria) this;
        }

        public Criteria andField0034NotEqualTo(Date value) {
            addCriterionForJDBCDate("field0034 <>", value, "field0034");
            return (Criteria) this;
        }

        public Criteria andField0034GreaterThan(Date value) {
            addCriterionForJDBCDate("field0034 >", value, "field0034");
            return (Criteria) this;
        }

        public Criteria andField0034GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0034 >=", value, "field0034");
            return (Criteria) this;
        }

        public Criteria andField0034LessThan(Date value) {
            addCriterionForJDBCDate("field0034 <", value, "field0034");
            return (Criteria) this;
        }

        public Criteria andField0034LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("field0034 <=", value, "field0034");
            return (Criteria) this;
        }

        public Criteria andField0034In(List<Date> values) {
            addCriterionForJDBCDate("field0034 in", values, "field0034");
            return (Criteria) this;
        }

        public Criteria andField0034NotIn(List<Date> values) {
            addCriterionForJDBCDate("field0034 not in", values, "field0034");
            return (Criteria) this;
        }

        public Criteria andField0034Between(Date value1, Date value2) {
            addCriterionForJDBCDate("field0034 between", value1, value2, "field0034");
            return (Criteria) this;
        }

        public Criteria andField0034NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("field0034 not between", value1, value2, "field0034");
            return (Criteria) this;
        }

        public Criteria andField0035IsNull() {
            addCriterion("field0035 is null");
            return (Criteria) this;
        }

        public Criteria andField0035IsNotNull() {
            addCriterion("field0035 is not null");
            return (Criteria) this;
        }

        public Criteria andField0035EqualTo(String value) {
            addCriterion("field0035 =", value, "field0035");
            return (Criteria) this;
        }

        public Criteria andField0035NotEqualTo(String value) {
            addCriterion("field0035 <>", value, "field0035");
            return (Criteria) this;
        }

        public Criteria andField0035GreaterThan(String value) {
            addCriterion("field0035 >", value, "field0035");
            return (Criteria) this;
        }

        public Criteria andField0035GreaterThanOrEqualTo(String value) {
            addCriterion("field0035 >=", value, "field0035");
            return (Criteria) this;
        }

        public Criteria andField0035LessThan(String value) {
            addCriterion("field0035 <", value, "field0035");
            return (Criteria) this;
        }

        public Criteria andField0035LessThanOrEqualTo(String value) {
            addCriterion("field0035 <=", value, "field0035");
            return (Criteria) this;
        }

        public Criteria andField0035Like(String value) {
            addCriterion("field0035 like", value, "field0035");
            return (Criteria) this;
        }

        public Criteria andField0035NotLike(String value) {
            addCriterion("field0035 not like", value, "field0035");
            return (Criteria) this;
        }

        public Criteria andField0035In(List<String> values) {
            addCriterion("field0035 in", values, "field0035");
            return (Criteria) this;
        }

        public Criteria andField0035NotIn(List<String> values) {
            addCriterion("field0035 not in", values, "field0035");
            return (Criteria) this;
        }

        public Criteria andField0035Between(String value1, String value2) {
            addCriterion("field0035 between", value1, value2, "field0035");
            return (Criteria) this;
        }

        public Criteria andField0035NotBetween(String value1, String value2) {
            addCriterion("field0035 not between", value1, value2, "field0035");
            return (Criteria) this;
        }

        public Criteria andField0036IsNull() {
            addCriterion("field0036 is null");
            return (Criteria) this;
        }

        public Criteria andField0036IsNotNull() {
            addCriterion("field0036 is not null");
            return (Criteria) this;
        }

        public Criteria andField0036EqualTo(Long value) {
            addCriterion("field0036 =", value, "field0036");
            return (Criteria) this;
        }

        public Criteria andField0036NotEqualTo(Long value) {
            addCriterion("field0036 <>", value, "field0036");
            return (Criteria) this;
        }

        public Criteria andField0036GreaterThan(Long value) {
            addCriterion("field0036 >", value, "field0036");
            return (Criteria) this;
        }

        public Criteria andField0036GreaterThanOrEqualTo(Long value) {
            addCriterion("field0036 >=", value, "field0036");
            return (Criteria) this;
        }

        public Criteria andField0036LessThan(Long value) {
            addCriterion("field0036 <", value, "field0036");
            return (Criteria) this;
        }

        public Criteria andField0036LessThanOrEqualTo(Long value) {
            addCriterion("field0036 <=", value, "field0036");
            return (Criteria) this;
        }

        public Criteria andField0036In(List<Long> values) {
            addCriterion("field0036 in", values, "field0036");
            return (Criteria) this;
        }

        public Criteria andField0036NotIn(List<Long> values) {
            addCriterion("field0036 not in", values, "field0036");
            return (Criteria) this;
        }

        public Criteria andField0036Between(Long value1, Long value2) {
            addCriterion("field0036 between", value1, value2, "field0036");
            return (Criteria) this;
        }

        public Criteria andField0036NotBetween(Long value1, Long value2) {
            addCriterion("field0036 not between", value1, value2, "field0036");
            return (Criteria) this;
        }

        public Criteria andField0037IsNull() {
            addCriterion("field0037 is null");
            return (Criteria) this;
        }

        public Criteria andField0037IsNotNull() {
            addCriterion("field0037 is not null");
            return (Criteria) this;
        }

        public Criteria andField0037EqualTo(Long value) {
            addCriterion("field0037 =", value, "field0037");
            return (Criteria) this;
        }

        public Criteria andField0037NotEqualTo(Long value) {
            addCriterion("field0037 <>", value, "field0037");
            return (Criteria) this;
        }

        public Criteria andField0037GreaterThan(Long value) {
            addCriterion("field0037 >", value, "field0037");
            return (Criteria) this;
        }

        public Criteria andField0037GreaterThanOrEqualTo(Long value) {
            addCriterion("field0037 >=", value, "field0037");
            return (Criteria) this;
        }

        public Criteria andField0037LessThan(Long value) {
            addCriterion("field0037 <", value, "field0037");
            return (Criteria) this;
        }

        public Criteria andField0037LessThanOrEqualTo(Long value) {
            addCriterion("field0037 <=", value, "field0037");
            return (Criteria) this;
        }

        public Criteria andField0037In(List<Long> values) {
            addCriterion("field0037 in", values, "field0037");
            return (Criteria) this;
        }

        public Criteria andField0037NotIn(List<Long> values) {
            addCriterion("field0037 not in", values, "field0037");
            return (Criteria) this;
        }

        public Criteria andField0037Between(Long value1, Long value2) {
            addCriterion("field0037 between", value1, value2, "field0037");
            return (Criteria) this;
        }

        public Criteria andField0037NotBetween(Long value1, Long value2) {
            addCriterion("field0037 not between", value1, value2, "field0037");
            return (Criteria) this;
        }

        public Criteria andField0038IsNull() {
            addCriterion("field0038 is null");
            return (Criteria) this;
        }

        public Criteria andField0038IsNotNull() {
            addCriterion("field0038 is not null");
            return (Criteria) this;
        }

        public Criteria andField0038EqualTo(Long value) {
            addCriterion("field0038 =", value, "field0038");
            return (Criteria) this;
        }

        public Criteria andField0038NotEqualTo(Long value) {
            addCriterion("field0038 <>", value, "field0038");
            return (Criteria) this;
        }

        public Criteria andField0038GreaterThan(Long value) {
            addCriterion("field0038 >", value, "field0038");
            return (Criteria) this;
        }

        public Criteria andField0038GreaterThanOrEqualTo(Long value) {
            addCriterion("field0038 >=", value, "field0038");
            return (Criteria) this;
        }

        public Criteria andField0038LessThan(Long value) {
            addCriterion("field0038 <", value, "field0038");
            return (Criteria) this;
        }

        public Criteria andField0038LessThanOrEqualTo(Long value) {
            addCriterion("field0038 <=", value, "field0038");
            return (Criteria) this;
        }

        public Criteria andField0038In(List<Long> values) {
            addCriterion("field0038 in", values, "field0038");
            return (Criteria) this;
        }

        public Criteria andField0038NotIn(List<Long> values) {
            addCriterion("field0038 not in", values, "field0038");
            return (Criteria) this;
        }

        public Criteria andField0038Between(Long value1, Long value2) {
            addCriterion("field0038 between", value1, value2, "field0038");
            return (Criteria) this;
        }

        public Criteria andField0038NotBetween(Long value1, Long value2) {
            addCriterion("field0038 not between", value1, value2, "field0038");
            return (Criteria) this;
        }

        public Criteria andField0039IsNull() {
            addCriterion("field0039 is null");
            return (Criteria) this;
        }

        public Criteria andField0039IsNotNull() {
            addCriterion("field0039 is not null");
            return (Criteria) this;
        }

        public Criteria andField0039EqualTo(String value) {
            addCriterion("field0039 =", value, "field0039");
            return (Criteria) this;
        }

        public Criteria andField0039NotEqualTo(String value) {
            addCriterion("field0039 <>", value, "field0039");
            return (Criteria) this;
        }

        public Criteria andField0039GreaterThan(String value) {
            addCriterion("field0039 >", value, "field0039");
            return (Criteria) this;
        }

        public Criteria andField0039GreaterThanOrEqualTo(String value) {
            addCriterion("field0039 >=", value, "field0039");
            return (Criteria) this;
        }

        public Criteria andField0039LessThan(String value) {
            addCriterion("field0039 <", value, "field0039");
            return (Criteria) this;
        }

        public Criteria andField0039LessThanOrEqualTo(String value) {
            addCriterion("field0039 <=", value, "field0039");
            return (Criteria) this;
        }

        public Criteria andField0039Like(String value) {
            addCriterion("field0039 like", value, "field0039");
            return (Criteria) this;
        }

        public Criteria andField0039NotLike(String value) {
            addCriterion("field0039 not like", value, "field0039");
            return (Criteria) this;
        }

        public Criteria andField0039In(List<String> values) {
            addCriterion("field0039 in", values, "field0039");
            return (Criteria) this;
        }

        public Criteria andField0039NotIn(List<String> values) {
            addCriterion("field0039 not in", values, "field0039");
            return (Criteria) this;
        }

        public Criteria andField0039Between(String value1, String value2) {
            addCriterion("field0039 between", value1, value2, "field0039");
            return (Criteria) this;
        }

        public Criteria andField0039NotBetween(String value1, String value2) {
            addCriterion("field0039 not between", value1, value2, "field0039");
            return (Criteria) this;
        }

        public Criteria andField0040IsNull() {
            addCriterion("field0040 is null");
            return (Criteria) this;
        }

        public Criteria andField0040IsNotNull() {
            addCriterion("field0040 is not null");
            return (Criteria) this;
        }

        public Criteria andField0040EqualTo(String value) {
            addCriterion("field0040 =", value, "field0040");
            return (Criteria) this;
        }

        public Criteria andField0040NotEqualTo(String value) {
            addCriterion("field0040 <>", value, "field0040");
            return (Criteria) this;
        }

        public Criteria andField0040GreaterThan(String value) {
            addCriterion("field0040 >", value, "field0040");
            return (Criteria) this;
        }

        public Criteria andField0040GreaterThanOrEqualTo(String value) {
            addCriterion("field0040 >=", value, "field0040");
            return (Criteria) this;
        }

        public Criteria andField0040LessThan(String value) {
            addCriterion("field0040 <", value, "field0040");
            return (Criteria) this;
        }

        public Criteria andField0040LessThanOrEqualTo(String value) {
            addCriterion("field0040 <=", value, "field0040");
            return (Criteria) this;
        }

        public Criteria andField0040Like(String value) {
            addCriterion("field0040 like", value, "field0040");
            return (Criteria) this;
        }

        public Criteria andField0040NotLike(String value) {
            addCriterion("field0040 not like", value, "field0040");
            return (Criteria) this;
        }

        public Criteria andField0040In(List<String> values) {
            addCriterion("field0040 in", values, "field0040");
            return (Criteria) this;
        }

        public Criteria andField0040NotIn(List<String> values) {
            addCriterion("field0040 not in", values, "field0040");
            return (Criteria) this;
        }

        public Criteria andField0040Between(String value1, String value2) {
            addCriterion("field0040 between", value1, value2, "field0040");
            return (Criteria) this;
        }

        public Criteria andField0040NotBetween(String value1, String value2) {
            addCriterion("field0040 not between", value1, value2, "field0040");
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

        public Criteria andField0077IsNull() {
            addCriterion("field0077 is null");
            return (Criteria) this;
        }

        public Criteria andField0077IsNotNull() {
            addCriterion("field0077 is not null");
            return (Criteria) this;
        }

        public Criteria andField0077EqualTo(String value) {
            addCriterion("field0077 =", value, "field0077");
            return (Criteria) this;
        }

        public Criteria andField0077NotEqualTo(String value) {
            addCriterion("field0077 <>", value, "field0077");
            return (Criteria) this;
        }

        public Criteria andField0077GreaterThan(String value) {
            addCriterion("field0077 >", value, "field0077");
            return (Criteria) this;
        }

        public Criteria andField0077GreaterThanOrEqualTo(String value) {
            addCriterion("field0077 >=", value, "field0077");
            return (Criteria) this;
        }

        public Criteria andField0077LessThan(String value) {
            addCriterion("field0077 <", value, "field0077");
            return (Criteria) this;
        }

        public Criteria andField0077LessThanOrEqualTo(String value) {
            addCriterion("field0077 <=", value, "field0077");
            return (Criteria) this;
        }

        public Criteria andField0077Like(String value) {
            addCriterion("field0077 like", value, "field0077");
            return (Criteria) this;
        }

        public Criteria andField0077NotLike(String value) {
            addCriterion("field0077 not like", value, "field0077");
            return (Criteria) this;
        }

        public Criteria andField0077In(List<String> values) {
            addCriterion("field0077 in", values, "field0077");
            return (Criteria) this;
        }

        public Criteria andField0077NotIn(List<String> values) {
            addCriterion("field0077 not in", values, "field0077");
            return (Criteria) this;
        }

        public Criteria andField0077Between(String value1, String value2) {
            addCriterion("field0077 between", value1, value2, "field0077");
            return (Criteria) this;
        }

        public Criteria andField0077NotBetween(String value1, String value2) {
            addCriterion("field0077 not between", value1, value2, "field0077");
            return (Criteria) this;
        }

        public Criteria andField0094IsNull() {
            addCriterion("field0094 is null");
            return (Criteria) this;
        }

        public Criteria andField0094IsNotNull() {
            addCriterion("field0094 is not null");
            return (Criteria) this;
        }

        public Criteria andField0094EqualTo(BigDecimal value) {
            addCriterion("field0094 =", value, "field0094");
            return (Criteria) this;
        }

        public Criteria andField0094NotEqualTo(BigDecimal value) {
            addCriterion("field0094 <>", value, "field0094");
            return (Criteria) this;
        }

        public Criteria andField0094GreaterThan(BigDecimal value) {
            addCriterion("field0094 >", value, "field0094");
            return (Criteria) this;
        }

        public Criteria andField0094GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("field0094 >=", value, "field0094");
            return (Criteria) this;
        }

        public Criteria andField0094LessThan(BigDecimal value) {
            addCriterion("field0094 <", value, "field0094");
            return (Criteria) this;
        }

        public Criteria andField0094LessThanOrEqualTo(BigDecimal value) {
            addCriterion("field0094 <=", value, "field0094");
            return (Criteria) this;
        }

        public Criteria andField0094In(List<BigDecimal> values) {
            addCriterion("field0094 in", values, "field0094");
            return (Criteria) this;
        }

        public Criteria andField0094NotIn(List<BigDecimal> values) {
            addCriterion("field0094 not in", values, "field0094");
            return (Criteria) this;
        }

        public Criteria andField0094Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("field0094 between", value1, value2, "field0094");
            return (Criteria) this;
        }

        public Criteria andField0094NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("field0094 not between", value1, value2, "field0094");
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