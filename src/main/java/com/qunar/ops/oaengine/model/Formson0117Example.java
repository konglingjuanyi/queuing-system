package com.qunar.ops.oaengine.model;

import java.util.ArrayList;
import java.util.List;

public class Formson0117Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limit = -1;

    protected int offset = -1;

    public Formson0117Example() {
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

        public Criteria andField0048IsNull() {
            addCriterion("field0048 is null");
            return (Criteria) this;
        }

        public Criteria andField0048IsNotNull() {
            addCriterion("field0048 is not null");
            return (Criteria) this;
        }

        public Criteria andField0048EqualTo(String value) {
            addCriterion("field0048 =", value, "field0048");
            return (Criteria) this;
        }

        public Criteria andField0048NotEqualTo(String value) {
            addCriterion("field0048 <>", value, "field0048");
            return (Criteria) this;
        }

        public Criteria andField0048GreaterThan(String value) {
            addCriterion("field0048 >", value, "field0048");
            return (Criteria) this;
        }

        public Criteria andField0048GreaterThanOrEqualTo(String value) {
            addCriterion("field0048 >=", value, "field0048");
            return (Criteria) this;
        }

        public Criteria andField0048LessThan(String value) {
            addCriterion("field0048 <", value, "field0048");
            return (Criteria) this;
        }

        public Criteria andField0048LessThanOrEqualTo(String value) {
            addCriterion("field0048 <=", value, "field0048");
            return (Criteria) this;
        }

        public Criteria andField0048Like(String value) {
            addCriterion("field0048 like", value, "field0048");
            return (Criteria) this;
        }

        public Criteria andField0048NotLike(String value) {
            addCriterion("field0048 not like", value, "field0048");
            return (Criteria) this;
        }

        public Criteria andField0048In(List<String> values) {
            addCriterion("field0048 in", values, "field0048");
            return (Criteria) this;
        }

        public Criteria andField0048NotIn(List<String> values) {
            addCriterion("field0048 not in", values, "field0048");
            return (Criteria) this;
        }

        public Criteria andField0048Between(String value1, String value2) {
            addCriterion("field0048 between", value1, value2, "field0048");
            return (Criteria) this;
        }

        public Criteria andField0048NotBetween(String value1, String value2) {
            addCriterion("field0048 not between", value1, value2, "field0048");
            return (Criteria) this;
        }

        public Criteria andField0049IsNull() {
            addCriterion("field0049 is null");
            return (Criteria) this;
        }

        public Criteria andField0049IsNotNull() {
            addCriterion("field0049 is not null");
            return (Criteria) this;
        }

        public Criteria andField0049EqualTo(Long value) {
            addCriterion("field0049 =", value, "field0049");
            return (Criteria) this;
        }

        public Criteria andField0049NotEqualTo(Long value) {
            addCriterion("field0049 <>", value, "field0049");
            return (Criteria) this;
        }

        public Criteria andField0049GreaterThan(Long value) {
            addCriterion("field0049 >", value, "field0049");
            return (Criteria) this;
        }

        public Criteria andField0049GreaterThanOrEqualTo(Long value) {
            addCriterion("field0049 >=", value, "field0049");
            return (Criteria) this;
        }

        public Criteria andField0049LessThan(Long value) {
            addCriterion("field0049 <", value, "field0049");
            return (Criteria) this;
        }

        public Criteria andField0049LessThanOrEqualTo(Long value) {
            addCriterion("field0049 <=", value, "field0049");
            return (Criteria) this;
        }

        public Criteria andField0049In(List<Long> values) {
            addCriterion("field0049 in", values, "field0049");
            return (Criteria) this;
        }

        public Criteria andField0049NotIn(List<Long> values) {
            addCriterion("field0049 not in", values, "field0049");
            return (Criteria) this;
        }

        public Criteria andField0049Between(Long value1, Long value2) {
            addCriterion("field0049 between", value1, value2, "field0049");
            return (Criteria) this;
        }

        public Criteria andField0049NotBetween(Long value1, Long value2) {
            addCriterion("field0049 not between", value1, value2, "field0049");
            return (Criteria) this;
        }

        public Criteria andField0050IsNull() {
            addCriterion("field0050 is null");
            return (Criteria) this;
        }

        public Criteria andField0050IsNotNull() {
            addCriterion("field0050 is not null");
            return (Criteria) this;
        }

        public Criteria andField0050EqualTo(String value) {
            addCriterion("field0050 =", value, "field0050");
            return (Criteria) this;
        }

        public Criteria andField0050NotEqualTo(String value) {
            addCriterion("field0050 <>", value, "field0050");
            return (Criteria) this;
        }

        public Criteria andField0050GreaterThan(String value) {
            addCriterion("field0050 >", value, "field0050");
            return (Criteria) this;
        }

        public Criteria andField0050GreaterThanOrEqualTo(String value) {
            addCriterion("field0050 >=", value, "field0050");
            return (Criteria) this;
        }

        public Criteria andField0050LessThan(String value) {
            addCriterion("field0050 <", value, "field0050");
            return (Criteria) this;
        }

        public Criteria andField0050LessThanOrEqualTo(String value) {
            addCriterion("field0050 <=", value, "field0050");
            return (Criteria) this;
        }

        public Criteria andField0050Like(String value) {
            addCriterion("field0050 like", value, "field0050");
            return (Criteria) this;
        }

        public Criteria andField0050NotLike(String value) {
            addCriterion("field0050 not like", value, "field0050");
            return (Criteria) this;
        }

        public Criteria andField0050In(List<String> values) {
            addCriterion("field0050 in", values, "field0050");
            return (Criteria) this;
        }

        public Criteria andField0050NotIn(List<String> values) {
            addCriterion("field0050 not in", values, "field0050");
            return (Criteria) this;
        }

        public Criteria andField0050Between(String value1, String value2) {
            addCriterion("field0050 between", value1, value2, "field0050");
            return (Criteria) this;
        }

        public Criteria andField0050NotBetween(String value1, String value2) {
            addCriterion("field0050 not between", value1, value2, "field0050");
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