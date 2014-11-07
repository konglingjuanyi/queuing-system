package com.qunar.ops.oaengine.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DelegationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limit = -1;

    protected int offset = -1;

    public DelegationExample() {
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

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
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

        public Criteria andMasterUserIdIsNull() {
            addCriterion("master_user_id is null");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdIsNotNull() {
            addCriterion("master_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdEqualTo(String value) {
            addCriterion("master_user_id =", value, "masterUserId");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdNotEqualTo(String value) {
            addCriterion("master_user_id <>", value, "masterUserId");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdGreaterThan(String value) {
            addCriterion("master_user_id >", value, "masterUserId");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("master_user_id >=", value, "masterUserId");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdLessThan(String value) {
            addCriterion("master_user_id <", value, "masterUserId");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdLessThanOrEqualTo(String value) {
            addCriterion("master_user_id <=", value, "masterUserId");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdLike(String value) {
            addCriterion("master_user_id like", value, "masterUserId");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdNotLike(String value) {
            addCriterion("master_user_id not like", value, "masterUserId");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdIn(List<String> values) {
            addCriterion("master_user_id in", values, "masterUserId");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdNotIn(List<String> values) {
            addCriterion("master_user_id not in", values, "masterUserId");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdBetween(String value1, String value2) {
            addCriterion("master_user_id between", value1, value2, "masterUserId");
            return (Criteria) this;
        }

        public Criteria andMasterUserIdNotBetween(String value1, String value2) {
            addCriterion("master_user_id not between", value1, value2, "masterUserId");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdIsNull() {
            addCriterion("agent_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdIsNotNull() {
            addCriterion("agent_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdEqualTo(String value) {
            addCriterion("agent_user_id =", value, "agentUserId");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdNotEqualTo(String value) {
            addCriterion("agent_user_id <>", value, "agentUserId");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdGreaterThan(String value) {
            addCriterion("agent_user_id >", value, "agentUserId");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("agent_user_id >=", value, "agentUserId");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdLessThan(String value) {
            addCriterion("agent_user_id <", value, "agentUserId");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdLessThanOrEqualTo(String value) {
            addCriterion("agent_user_id <=", value, "agentUserId");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdLike(String value) {
            addCriterion("agent_user_id like", value, "agentUserId");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdNotLike(String value) {
            addCriterion("agent_user_id not like", value, "agentUserId");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdIn(List<String> values) {
            addCriterion("agent_user_id in", values, "agentUserId");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdNotIn(List<String> values) {
            addCriterion("agent_user_id not in", values, "agentUserId");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdBetween(String value1, String value2) {
            addCriterion("agent_user_id between", value1, value2, "agentUserId");
            return (Criteria) this;
        }

        public Criteria andAgentUserIdNotBetween(String value1, String value2) {
            addCriterion("agent_user_id not between", value1, value2, "agentUserId");
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
            addCriterionForJDBCTime("ts =", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotEqualTo(Date value) {
            addCriterionForJDBCTime("ts <>", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThan(Date value) {
            addCriterionForJDBCTime("ts >", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("ts >=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThan(Date value) {
            addCriterionForJDBCTime("ts <", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("ts <=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsIn(List<Date> values) {
            addCriterionForJDBCTime("ts in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotIn(List<Date> values) {
            addCriterionForJDBCTime("ts not in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("ts between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("ts not between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
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