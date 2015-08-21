package com.qunar.ops.recruit.model;

import java.util.ArrayList;
import java.util.List;

public class StudentAssessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentAssessExample() {
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

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(Long value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(Long value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(Long value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(Long value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(Long value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<Long> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<Long> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(Long value1, Long value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(Long value1, Long value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andOneViewerIsNull() {
            addCriterion("one_viewer is null");
            return (Criteria) this;
        }

        public Criteria andOneViewerIsNotNull() {
            addCriterion("one_viewer is not null");
            return (Criteria) this;
        }

        public Criteria andOneViewerEqualTo(String value) {
            addCriterion("one_viewer =", value, "oneViewer");
            return (Criteria) this;
        }

        public Criteria andOneViewerNotEqualTo(String value) {
            addCriterion("one_viewer <>", value, "oneViewer");
            return (Criteria) this;
        }

        public Criteria andOneViewerGreaterThan(String value) {
            addCriterion("one_viewer >", value, "oneViewer");
            return (Criteria) this;
        }

        public Criteria andOneViewerGreaterThanOrEqualTo(String value) {
            addCriterion("one_viewer >=", value, "oneViewer");
            return (Criteria) this;
        }

        public Criteria andOneViewerLessThan(String value) {
            addCriterion("one_viewer <", value, "oneViewer");
            return (Criteria) this;
        }

        public Criteria andOneViewerLessThanOrEqualTo(String value) {
            addCriterion("one_viewer <=", value, "oneViewer");
            return (Criteria) this;
        }

        public Criteria andOneViewerLike(String value) {
            addCriterion("one_viewer like", value, "oneViewer");
            return (Criteria) this;
        }

        public Criteria andOneViewerNotLike(String value) {
            addCriterion("one_viewer not like", value, "oneViewer");
            return (Criteria) this;
        }

        public Criteria andOneViewerIn(List<String> values) {
            addCriterion("one_viewer in", values, "oneViewer");
            return (Criteria) this;
        }

        public Criteria andOneViewerNotIn(List<String> values) {
            addCriterion("one_viewer not in", values, "oneViewer");
            return (Criteria) this;
        }

        public Criteria andOneViewerBetween(String value1, String value2) {
            addCriterion("one_viewer between", value1, value2, "oneViewer");
            return (Criteria) this;
        }

        public Criteria andOneViewerNotBetween(String value1, String value2) {
            addCriterion("one_viewer not between", value1, value2, "oneViewer");
            return (Criteria) this;
        }

        public Criteria andOneCodeIsNull() {
            addCriterion("one_code is null");
            return (Criteria) this;
        }

        public Criteria andOneCodeIsNotNull() {
            addCriterion("one_code is not null");
            return (Criteria) this;
        }

        public Criteria andOneCodeEqualTo(Long value) {
            addCriterion("one_code =", value, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeNotEqualTo(Long value) {
            addCriterion("one_code <>", value, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeGreaterThan(Long value) {
            addCriterion("one_code >", value, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("one_code >=", value, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeLessThan(Long value) {
            addCriterion("one_code <", value, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeLessThanOrEqualTo(Long value) {
            addCriterion("one_code <=", value, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeIn(List<Long> values) {
            addCriterion("one_code in", values, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeNotIn(List<Long> values) {
            addCriterion("one_code not in", values, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeBetween(Long value1, Long value2) {
            addCriterion("one_code between", value1, value2, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeNotBetween(Long value1, Long value2) {
            addCriterion("one_code not between", value1, value2, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneSuanfaIsNull() {
            addCriterion("one_suanfa is null");
            return (Criteria) this;
        }

        public Criteria andOneSuanfaIsNotNull() {
            addCriterion("one_suanfa is not null");
            return (Criteria) this;
        }

        public Criteria andOneSuanfaEqualTo(Long value) {
            addCriterion("one_suanfa =", value, "oneSuanfa");
            return (Criteria) this;
        }

        public Criteria andOneSuanfaNotEqualTo(Long value) {
            addCriterion("one_suanfa <>", value, "oneSuanfa");
            return (Criteria) this;
        }

        public Criteria andOneSuanfaGreaterThan(Long value) {
            addCriterion("one_suanfa >", value, "oneSuanfa");
            return (Criteria) this;
        }

        public Criteria andOneSuanfaGreaterThanOrEqualTo(Long value) {
            addCriterion("one_suanfa >=", value, "oneSuanfa");
            return (Criteria) this;
        }

        public Criteria andOneSuanfaLessThan(Long value) {
            addCriterion("one_suanfa <", value, "oneSuanfa");
            return (Criteria) this;
        }

        public Criteria andOneSuanfaLessThanOrEqualTo(Long value) {
            addCriterion("one_suanfa <=", value, "oneSuanfa");
            return (Criteria) this;
        }

        public Criteria andOneSuanfaIn(List<Long> values) {
            addCriterion("one_suanfa in", values, "oneSuanfa");
            return (Criteria) this;
        }

        public Criteria andOneSuanfaNotIn(List<Long> values) {
            addCriterion("one_suanfa not in", values, "oneSuanfa");
            return (Criteria) this;
        }

        public Criteria andOneSuanfaBetween(Long value1, Long value2) {
            addCriterion("one_suanfa between", value1, value2, "oneSuanfa");
            return (Criteria) this;
        }

        public Criteria andOneSuanfaNotBetween(Long value1, Long value2) {
            addCriterion("one_suanfa not between", value1, value2, "oneSuanfa");
            return (Criteria) this;
        }

        public Criteria andOneIntenetIsNull() {
            addCriterion("one_intenet is null");
            return (Criteria) this;
        }

        public Criteria andOneIntenetIsNotNull() {
            addCriterion("one_intenet is not null");
            return (Criteria) this;
        }

        public Criteria andOneIntenetEqualTo(Long value) {
            addCriterion("one_intenet =", value, "oneIntenet");
            return (Criteria) this;
        }

        public Criteria andOneIntenetNotEqualTo(Long value) {
            addCriterion("one_intenet <>", value, "oneIntenet");
            return (Criteria) this;
        }

        public Criteria andOneIntenetGreaterThan(Long value) {
            addCriterion("one_intenet >", value, "oneIntenet");
            return (Criteria) this;
        }

        public Criteria andOneIntenetGreaterThanOrEqualTo(Long value) {
            addCriterion("one_intenet >=", value, "oneIntenet");
            return (Criteria) this;
        }

        public Criteria andOneIntenetLessThan(Long value) {
            addCriterion("one_intenet <", value, "oneIntenet");
            return (Criteria) this;
        }

        public Criteria andOneIntenetLessThanOrEqualTo(Long value) {
            addCriterion("one_intenet <=", value, "oneIntenet");
            return (Criteria) this;
        }

        public Criteria andOneIntenetIn(List<Long> values) {
            addCriterion("one_intenet in", values, "oneIntenet");
            return (Criteria) this;
        }

        public Criteria andOneIntenetNotIn(List<Long> values) {
            addCriterion("one_intenet not in", values, "oneIntenet");
            return (Criteria) this;
        }

        public Criteria andOneIntenetBetween(Long value1, Long value2) {
            addCriterion("one_intenet between", value1, value2, "oneIntenet");
            return (Criteria) this;
        }

        public Criteria andOneIntenetNotBetween(Long value1, Long value2) {
            addCriterion("one_intenet not between", value1, value2, "oneIntenet");
            return (Criteria) this;
        }

        public Criteria andOneXiangmuIsNull() {
            addCriterion("one_xiangmu is null");
            return (Criteria) this;
        }

        public Criteria andOneXiangmuIsNotNull() {
            addCriterion("one_xiangmu is not null");
            return (Criteria) this;
        }

        public Criteria andOneXiangmuEqualTo(Long value) {
            addCriterion("one_xiangmu =", value, "oneXiangmu");
            return (Criteria) this;
        }

        public Criteria andOneXiangmuNotEqualTo(Long value) {
            addCriterion("one_xiangmu <>", value, "oneXiangmu");
            return (Criteria) this;
        }

        public Criteria andOneXiangmuGreaterThan(Long value) {
            addCriterion("one_xiangmu >", value, "oneXiangmu");
            return (Criteria) this;
        }

        public Criteria andOneXiangmuGreaterThanOrEqualTo(Long value) {
            addCriterion("one_xiangmu >=", value, "oneXiangmu");
            return (Criteria) this;
        }

        public Criteria andOneXiangmuLessThan(Long value) {
            addCriterion("one_xiangmu <", value, "oneXiangmu");
            return (Criteria) this;
        }

        public Criteria andOneXiangmuLessThanOrEqualTo(Long value) {
            addCriterion("one_xiangmu <=", value, "oneXiangmu");
            return (Criteria) this;
        }

        public Criteria andOneXiangmuIn(List<Long> values) {
            addCriterion("one_xiangmu in", values, "oneXiangmu");
            return (Criteria) this;
        }

        public Criteria andOneXiangmuNotIn(List<Long> values) {
            addCriterion("one_xiangmu not in", values, "oneXiangmu");
            return (Criteria) this;
        }

        public Criteria andOneXiangmuBetween(Long value1, Long value2) {
            addCriterion("one_xiangmu between", value1, value2, "oneXiangmu");
            return (Criteria) this;
        }

        public Criteria andOneXiangmuNotBetween(Long value1, Long value2) {
            addCriterion("one_xiangmu not between", value1, value2, "oneXiangmu");
            return (Criteria) this;
        }

        public Criteria andOneOtherIsNull() {
            addCriterion("one_other is null");
            return (Criteria) this;
        }

        public Criteria andOneOtherIsNotNull() {
            addCriterion("one_other is not null");
            return (Criteria) this;
        }

        public Criteria andOneOtherEqualTo(Long value) {
            addCriterion("one_other =", value, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherNotEqualTo(Long value) {
            addCriterion("one_other <>", value, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherGreaterThan(Long value) {
            addCriterion("one_other >", value, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherGreaterThanOrEqualTo(Long value) {
            addCriterion("one_other >=", value, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherLessThan(Long value) {
            addCriterion("one_other <", value, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherLessThanOrEqualTo(Long value) {
            addCriterion("one_other <=", value, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherIn(List<Long> values) {
            addCriterion("one_other in", values, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherNotIn(List<Long> values) {
            addCriterion("one_other not in", values, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherBetween(Long value1, Long value2) {
            addCriterion("one_other between", value1, value2, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherNotBetween(Long value1, Long value2) {
            addCriterion("one_other not between", value1, value2, "oneOther");
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

        public Criteria andOneCountEqualTo(Long value) {
            addCriterion("one_count =", value, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountNotEqualTo(Long value) {
            addCriterion("one_count <>", value, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountGreaterThan(Long value) {
            addCriterion("one_count >", value, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountGreaterThanOrEqualTo(Long value) {
            addCriterion("one_count >=", value, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountLessThan(Long value) {
            addCriterion("one_count <", value, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountLessThanOrEqualTo(Long value) {
            addCriterion("one_count <=", value, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountIn(List<Long> values) {
            addCriterion("one_count in", values, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountNotIn(List<Long> values) {
            addCriterion("one_count not in", values, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountBetween(Long value1, Long value2) {
            addCriterion("one_count between", value1, value2, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneCountNotBetween(Long value1, Long value2) {
            addCriterion("one_count not between", value1, value2, "oneCount");
            return (Criteria) this;
        }

        public Criteria andOneLuojiIsNull() {
            addCriterion("one_luoji is null");
            return (Criteria) this;
        }

        public Criteria andOneLuojiIsNotNull() {
            addCriterion("one_luoji is not null");
            return (Criteria) this;
        }

        public Criteria andOneLuojiEqualTo(Long value) {
            addCriterion("one_luoji =", value, "oneLuoji");
            return (Criteria) this;
        }

        public Criteria andOneLuojiNotEqualTo(Long value) {
            addCriterion("one_luoji <>", value, "oneLuoji");
            return (Criteria) this;
        }

        public Criteria andOneLuojiGreaterThan(Long value) {
            addCriterion("one_luoji >", value, "oneLuoji");
            return (Criteria) this;
        }

        public Criteria andOneLuojiGreaterThanOrEqualTo(Long value) {
            addCriterion("one_luoji >=", value, "oneLuoji");
            return (Criteria) this;
        }

        public Criteria andOneLuojiLessThan(Long value) {
            addCriterion("one_luoji <", value, "oneLuoji");
            return (Criteria) this;
        }

        public Criteria andOneLuojiLessThanOrEqualTo(Long value) {
            addCriterion("one_luoji <=", value, "oneLuoji");
            return (Criteria) this;
        }

        public Criteria andOneLuojiIn(List<Long> values) {
            addCriterion("one_luoji in", values, "oneLuoji");
            return (Criteria) this;
        }

        public Criteria andOneLuojiNotIn(List<Long> values) {
            addCriterion("one_luoji not in", values, "oneLuoji");
            return (Criteria) this;
        }

        public Criteria andOneLuojiBetween(Long value1, Long value2) {
            addCriterion("one_luoji between", value1, value2, "oneLuoji");
            return (Criteria) this;
        }

        public Criteria andOneLuojiNotBetween(Long value1, Long value2) {
            addCriterion("one_luoji not between", value1, value2, "oneLuoji");
            return (Criteria) this;
        }

        public Criteria andOneChuangxinIsNull() {
            addCriterion("one_chuangxin is null");
            return (Criteria) this;
        }

        public Criteria andOneChuangxinIsNotNull() {
            addCriterion("one_chuangxin is not null");
            return (Criteria) this;
        }

        public Criteria andOneChuangxinEqualTo(Long value) {
            addCriterion("one_chuangxin =", value, "oneChuangxin");
            return (Criteria) this;
        }

        public Criteria andOneChuangxinNotEqualTo(Long value) {
            addCriterion("one_chuangxin <>", value, "oneChuangxin");
            return (Criteria) this;
        }

        public Criteria andOneChuangxinGreaterThan(Long value) {
            addCriterion("one_chuangxin >", value, "oneChuangxin");
            return (Criteria) this;
        }

        public Criteria andOneChuangxinGreaterThanOrEqualTo(Long value) {
            addCriterion("one_chuangxin >=", value, "oneChuangxin");
            return (Criteria) this;
        }

        public Criteria andOneChuangxinLessThan(Long value) {
            addCriterion("one_chuangxin <", value, "oneChuangxin");
            return (Criteria) this;
        }

        public Criteria andOneChuangxinLessThanOrEqualTo(Long value) {
            addCriterion("one_chuangxin <=", value, "oneChuangxin");
            return (Criteria) this;
        }

        public Criteria andOneChuangxinIn(List<Long> values) {
            addCriterion("one_chuangxin in", values, "oneChuangxin");
            return (Criteria) this;
        }

        public Criteria andOneChuangxinNotIn(List<Long> values) {
            addCriterion("one_chuangxin not in", values, "oneChuangxin");
            return (Criteria) this;
        }

        public Criteria andOneChuangxinBetween(Long value1, Long value2) {
            addCriterion("one_chuangxin between", value1, value2, "oneChuangxin");
            return (Criteria) this;
        }

        public Criteria andOneChuangxinNotBetween(Long value1, Long value2) {
            addCriterion("one_chuangxin not between", value1, value2, "oneChuangxin");
            return (Criteria) this;
        }

        public Criteria andOneTeamIsNull() {
            addCriterion("one_team is null");
            return (Criteria) this;
        }

        public Criteria andOneTeamIsNotNull() {
            addCriterion("one_team is not null");
            return (Criteria) this;
        }

        public Criteria andOneTeamEqualTo(Long value) {
            addCriterion("one_team =", value, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamNotEqualTo(Long value) {
            addCriterion("one_team <>", value, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamGreaterThan(Long value) {
            addCriterion("one_team >", value, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamGreaterThanOrEqualTo(Long value) {
            addCriterion("one_team >=", value, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamLessThan(Long value) {
            addCriterion("one_team <", value, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamLessThanOrEqualTo(Long value) {
            addCriterion("one_team <=", value, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamIn(List<Long> values) {
            addCriterion("one_team in", values, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamNotIn(List<Long> values) {
            addCriterion("one_team not in", values, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamBetween(Long value1, Long value2) {
            addCriterion("one_team between", value1, value2, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamNotBetween(Long value1, Long value2) {
            addCriterion("one_team not between", value1, value2, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneStadyIsNull() {
            addCriterion("one_stady is null");
            return (Criteria) this;
        }

        public Criteria andOneStadyIsNotNull() {
            addCriterion("one_stady is not null");
            return (Criteria) this;
        }

        public Criteria andOneStadyEqualTo(Long value) {
            addCriterion("one_stady =", value, "oneStady");
            return (Criteria) this;
        }

        public Criteria andOneStadyNotEqualTo(Long value) {
            addCriterion("one_stady <>", value, "oneStady");
            return (Criteria) this;
        }

        public Criteria andOneStadyGreaterThan(Long value) {
            addCriterion("one_stady >", value, "oneStady");
            return (Criteria) this;
        }

        public Criteria andOneStadyGreaterThanOrEqualTo(Long value) {
            addCriterion("one_stady >=", value, "oneStady");
            return (Criteria) this;
        }

        public Criteria andOneStadyLessThan(Long value) {
            addCriterion("one_stady <", value, "oneStady");
            return (Criteria) this;
        }

        public Criteria andOneStadyLessThanOrEqualTo(Long value) {
            addCriterion("one_stady <=", value, "oneStady");
            return (Criteria) this;
        }

        public Criteria andOneStadyIn(List<Long> values) {
            addCriterion("one_stady in", values, "oneStady");
            return (Criteria) this;
        }

        public Criteria andOneStadyNotIn(List<Long> values) {
            addCriterion("one_stady not in", values, "oneStady");
            return (Criteria) this;
        }

        public Criteria andOneStadyBetween(Long value1, Long value2) {
            addCriterion("one_stady between", value1, value2, "oneStady");
            return (Criteria) this;
        }

        public Criteria andOneStadyNotBetween(Long value1, Long value2) {
            addCriterion("one_stady not between", value1, value2, "oneStady");
            return (Criteria) this;
        }

        public Criteria andOneZhuoyueIsNull() {
            addCriterion("one_zhuoyue is null");
            return (Criteria) this;
        }

        public Criteria andOneZhuoyueIsNotNull() {
            addCriterion("one_zhuoyue is not null");
            return (Criteria) this;
        }

        public Criteria andOneZhuoyueEqualTo(Long value) {
            addCriterion("one_zhuoyue =", value, "oneZhuoyue");
            return (Criteria) this;
        }

        public Criteria andOneZhuoyueNotEqualTo(Long value) {
            addCriterion("one_zhuoyue <>", value, "oneZhuoyue");
            return (Criteria) this;
        }

        public Criteria andOneZhuoyueGreaterThan(Long value) {
            addCriterion("one_zhuoyue >", value, "oneZhuoyue");
            return (Criteria) this;
        }

        public Criteria andOneZhuoyueGreaterThanOrEqualTo(Long value) {
            addCriterion("one_zhuoyue >=", value, "oneZhuoyue");
            return (Criteria) this;
        }

        public Criteria andOneZhuoyueLessThan(Long value) {
            addCriterion("one_zhuoyue <", value, "oneZhuoyue");
            return (Criteria) this;
        }

        public Criteria andOneZhuoyueLessThanOrEqualTo(Long value) {
            addCriterion("one_zhuoyue <=", value, "oneZhuoyue");
            return (Criteria) this;
        }

        public Criteria andOneZhuoyueIn(List<Long> values) {
            addCriterion("one_zhuoyue in", values, "oneZhuoyue");
            return (Criteria) this;
        }

        public Criteria andOneZhuoyueNotIn(List<Long> values) {
            addCriterion("one_zhuoyue not in", values, "oneZhuoyue");
            return (Criteria) this;
        }

        public Criteria andOneZhuoyueBetween(Long value1, Long value2) {
            addCriterion("one_zhuoyue between", value1, value2, "oneZhuoyue");
            return (Criteria) this;
        }

        public Criteria andOneZhuoyueNotBetween(Long value1, Long value2) {
            addCriterion("one_zhuoyue not between", value1, value2, "oneZhuoyue");
            return (Criteria) this;
        }

        public Criteria andOneSumIsNull() {
            addCriterion("one_sum is null");
            return (Criteria) this;
        }

        public Criteria andOneSumIsNotNull() {
            addCriterion("one_sum is not null");
            return (Criteria) this;
        }

        public Criteria andOneSumEqualTo(Long value) {
            addCriterion("one_sum =", value, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumNotEqualTo(Long value) {
            addCriterion("one_sum <>", value, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumGreaterThan(Long value) {
            addCriterion("one_sum >", value, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumGreaterThanOrEqualTo(Long value) {
            addCriterion("one_sum >=", value, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumLessThan(Long value) {
            addCriterion("one_sum <", value, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumLessThanOrEqualTo(Long value) {
            addCriterion("one_sum <=", value, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumIn(List<Long> values) {
            addCriterion("one_sum in", values, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumNotIn(List<Long> values) {
            addCriterion("one_sum not in", values, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumBetween(Long value1, Long value2) {
            addCriterion("one_sum between", value1, value2, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumNotBetween(Long value1, Long value2) {
            addCriterion("one_sum not between", value1, value2, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneResultIsNull() {
            addCriterion("one_result is null");
            return (Criteria) this;
        }

        public Criteria andOneResultIsNotNull() {
            addCriterion("one_result is not null");
            return (Criteria) this;
        }

        public Criteria andOneResultEqualTo(Long value) {
            addCriterion("one_result =", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultNotEqualTo(Long value) {
            addCriterion("one_result <>", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultGreaterThan(Long value) {
            addCriterion("one_result >", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultGreaterThanOrEqualTo(Long value) {
            addCriterion("one_result >=", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultLessThan(Long value) {
            addCriterion("one_result <", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultLessThanOrEqualTo(Long value) {
            addCriterion("one_result <=", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultIn(List<Long> values) {
            addCriterion("one_result in", values, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultNotIn(List<Long> values) {
            addCriterion("one_result not in", values, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultBetween(Long value1, Long value2) {
            addCriterion("one_result between", value1, value2, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultNotBetween(Long value1, Long value2) {
            addCriterion("one_result not between", value1, value2, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneYewuIsNull() {
            addCriterion("one_yewu is null");
            return (Criteria) this;
        }

        public Criteria andOneYewuIsNotNull() {
            addCriterion("one_yewu is not null");
            return (Criteria) this;
        }

        public Criteria andOneYewuEqualTo(String value) {
            addCriterion("one_yewu =", value, "oneYewu");
            return (Criteria) this;
        }

        public Criteria andOneYewuNotEqualTo(String value) {
            addCriterion("one_yewu <>", value, "oneYewu");
            return (Criteria) this;
        }

        public Criteria andOneYewuGreaterThan(String value) {
            addCriterion("one_yewu >", value, "oneYewu");
            return (Criteria) this;
        }

        public Criteria andOneYewuGreaterThanOrEqualTo(String value) {
            addCriterion("one_yewu >=", value, "oneYewu");
            return (Criteria) this;
        }

        public Criteria andOneYewuLessThan(String value) {
            addCriterion("one_yewu <", value, "oneYewu");
            return (Criteria) this;
        }

        public Criteria andOneYewuLessThanOrEqualTo(String value) {
            addCriterion("one_yewu <=", value, "oneYewu");
            return (Criteria) this;
        }

        public Criteria andOneYewuLike(String value) {
            addCriterion("one_yewu like", value, "oneYewu");
            return (Criteria) this;
        }

        public Criteria andOneYewuNotLike(String value) {
            addCriterion("one_yewu not like", value, "oneYewu");
            return (Criteria) this;
        }

        public Criteria andOneYewuIn(List<String> values) {
            addCriterion("one_yewu in", values, "oneYewu");
            return (Criteria) this;
        }

        public Criteria andOneYewuNotIn(List<String> values) {
            addCriterion("one_yewu not in", values, "oneYewu");
            return (Criteria) this;
        }

        public Criteria andOneYewuBetween(String value1, String value2) {
            addCriterion("one_yewu between", value1, value2, "oneYewu");
            return (Criteria) this;
        }

        public Criteria andOneYewuNotBetween(String value1, String value2) {
            addCriterion("one_yewu not between", value1, value2, "oneYewu");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiIsNull() {
            addCriterion("one_suzhi is null");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiIsNotNull() {
            addCriterion("one_suzhi is not null");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiEqualTo(String value) {
            addCriterion("one_suzhi =", value, "oneSuzhi");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiNotEqualTo(String value) {
            addCriterion("one_suzhi <>", value, "oneSuzhi");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiGreaterThan(String value) {
            addCriterion("one_suzhi >", value, "oneSuzhi");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiGreaterThanOrEqualTo(String value) {
            addCriterion("one_suzhi >=", value, "oneSuzhi");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiLessThan(String value) {
            addCriterion("one_suzhi <", value, "oneSuzhi");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiLessThanOrEqualTo(String value) {
            addCriterion("one_suzhi <=", value, "oneSuzhi");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiLike(String value) {
            addCriterion("one_suzhi like", value, "oneSuzhi");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiNotLike(String value) {
            addCriterion("one_suzhi not like", value, "oneSuzhi");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiIn(List<String> values) {
            addCriterion("one_suzhi in", values, "oneSuzhi");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiNotIn(List<String> values) {
            addCriterion("one_suzhi not in", values, "oneSuzhi");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiBetween(String value1, String value2) {
            addCriterion("one_suzhi between", value1, value2, "oneSuzhi");
            return (Criteria) this;
        }

        public Criteria andOneSuzhiNotBetween(String value1, String value2) {
            addCriterion("one_suzhi not between", value1, value2, "oneSuzhi");
            return (Criteria) this;
        }

        public Criteria andOneZongheIsNull() {
            addCriterion("one_zonghe is null");
            return (Criteria) this;
        }

        public Criteria andOneZongheIsNotNull() {
            addCriterion("one_zonghe is not null");
            return (Criteria) this;
        }

        public Criteria andOneZongheEqualTo(String value) {
            addCriterion("one_zonghe =", value, "oneZonghe");
            return (Criteria) this;
        }

        public Criteria andOneZongheNotEqualTo(String value) {
            addCriterion("one_zonghe <>", value, "oneZonghe");
            return (Criteria) this;
        }

        public Criteria andOneZongheGreaterThan(String value) {
            addCriterion("one_zonghe >", value, "oneZonghe");
            return (Criteria) this;
        }

        public Criteria andOneZongheGreaterThanOrEqualTo(String value) {
            addCriterion("one_zonghe >=", value, "oneZonghe");
            return (Criteria) this;
        }

        public Criteria andOneZongheLessThan(String value) {
            addCriterion("one_zonghe <", value, "oneZonghe");
            return (Criteria) this;
        }

        public Criteria andOneZongheLessThanOrEqualTo(String value) {
            addCriterion("one_zonghe <=", value, "oneZonghe");
            return (Criteria) this;
        }

        public Criteria andOneZongheLike(String value) {
            addCriterion("one_zonghe like", value, "oneZonghe");
            return (Criteria) this;
        }

        public Criteria andOneZongheNotLike(String value) {
            addCriterion("one_zonghe not like", value, "oneZonghe");
            return (Criteria) this;
        }

        public Criteria andOneZongheIn(List<String> values) {
            addCriterion("one_zonghe in", values, "oneZonghe");
            return (Criteria) this;
        }

        public Criteria andOneZongheNotIn(List<String> values) {
            addCriterion("one_zonghe not in", values, "oneZonghe");
            return (Criteria) this;
        }

        public Criteria andOneZongheBetween(String value1, String value2) {
            addCriterion("one_zonghe between", value1, value2, "oneZonghe");
            return (Criteria) this;
        }

        public Criteria andOneZongheNotBetween(String value1, String value2) {
            addCriterion("one_zonghe not between", value1, value2, "oneZonghe");
            return (Criteria) this;
        }

        public Criteria andTwoViewerIsNull() {
            addCriterion("two_viewer is null");
            return (Criteria) this;
        }

        public Criteria andTwoViewerIsNotNull() {
            addCriterion("two_viewer is not null");
            return (Criteria) this;
        }

        public Criteria andTwoViewerEqualTo(String value) {
            addCriterion("two_viewer =", value, "twoViewer");
            return (Criteria) this;
        }

        public Criteria andTwoViewerNotEqualTo(String value) {
            addCriterion("two_viewer <>", value, "twoViewer");
            return (Criteria) this;
        }

        public Criteria andTwoViewerGreaterThan(String value) {
            addCriterion("two_viewer >", value, "twoViewer");
            return (Criteria) this;
        }

        public Criteria andTwoViewerGreaterThanOrEqualTo(String value) {
            addCriterion("two_viewer >=", value, "twoViewer");
            return (Criteria) this;
        }

        public Criteria andTwoViewerLessThan(String value) {
            addCriterion("two_viewer <", value, "twoViewer");
            return (Criteria) this;
        }

        public Criteria andTwoViewerLessThanOrEqualTo(String value) {
            addCriterion("two_viewer <=", value, "twoViewer");
            return (Criteria) this;
        }

        public Criteria andTwoViewerLike(String value) {
            addCriterion("two_viewer like", value, "twoViewer");
            return (Criteria) this;
        }

        public Criteria andTwoViewerNotLike(String value) {
            addCriterion("two_viewer not like", value, "twoViewer");
            return (Criteria) this;
        }

        public Criteria andTwoViewerIn(List<String> values) {
            addCriterion("two_viewer in", values, "twoViewer");
            return (Criteria) this;
        }

        public Criteria andTwoViewerNotIn(List<String> values) {
            addCriterion("two_viewer not in", values, "twoViewer");
            return (Criteria) this;
        }

        public Criteria andTwoViewerBetween(String value1, String value2) {
            addCriterion("two_viewer between", value1, value2, "twoViewer");
            return (Criteria) this;
        }

        public Criteria andTwoViewerNotBetween(String value1, String value2) {
            addCriterion("two_viewer not between", value1, value2, "twoViewer");
            return (Criteria) this;
        }

        public Criteria andTwoCodeIsNull() {
            addCriterion("two_code is null");
            return (Criteria) this;
        }

        public Criteria andTwoCodeIsNotNull() {
            addCriterion("two_code is not null");
            return (Criteria) this;
        }

        public Criteria andTwoCodeEqualTo(Long value) {
            addCriterion("two_code =", value, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeNotEqualTo(Long value) {
            addCriterion("two_code <>", value, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeGreaterThan(Long value) {
            addCriterion("two_code >", value, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("two_code >=", value, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeLessThan(Long value) {
            addCriterion("two_code <", value, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeLessThanOrEqualTo(Long value) {
            addCriterion("two_code <=", value, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeIn(List<Long> values) {
            addCriterion("two_code in", values, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeNotIn(List<Long> values) {
            addCriterion("two_code not in", values, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeBetween(Long value1, Long value2) {
            addCriterion("two_code between", value1, value2, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeNotBetween(Long value1, Long value2) {
            addCriterion("two_code not between", value1, value2, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoSuanfaIsNull() {
            addCriterion("two_suanfa is null");
            return (Criteria) this;
        }

        public Criteria andTwoSuanfaIsNotNull() {
            addCriterion("two_suanfa is not null");
            return (Criteria) this;
        }

        public Criteria andTwoSuanfaEqualTo(Long value) {
            addCriterion("two_suanfa =", value, "twoSuanfa");
            return (Criteria) this;
        }

        public Criteria andTwoSuanfaNotEqualTo(Long value) {
            addCriterion("two_suanfa <>", value, "twoSuanfa");
            return (Criteria) this;
        }

        public Criteria andTwoSuanfaGreaterThan(Long value) {
            addCriterion("two_suanfa >", value, "twoSuanfa");
            return (Criteria) this;
        }

        public Criteria andTwoSuanfaGreaterThanOrEqualTo(Long value) {
            addCriterion("two_suanfa >=", value, "twoSuanfa");
            return (Criteria) this;
        }

        public Criteria andTwoSuanfaLessThan(Long value) {
            addCriterion("two_suanfa <", value, "twoSuanfa");
            return (Criteria) this;
        }

        public Criteria andTwoSuanfaLessThanOrEqualTo(Long value) {
            addCriterion("two_suanfa <=", value, "twoSuanfa");
            return (Criteria) this;
        }

        public Criteria andTwoSuanfaIn(List<Long> values) {
            addCriterion("two_suanfa in", values, "twoSuanfa");
            return (Criteria) this;
        }

        public Criteria andTwoSuanfaNotIn(List<Long> values) {
            addCriterion("two_suanfa not in", values, "twoSuanfa");
            return (Criteria) this;
        }

        public Criteria andTwoSuanfaBetween(Long value1, Long value2) {
            addCriterion("two_suanfa between", value1, value2, "twoSuanfa");
            return (Criteria) this;
        }

        public Criteria andTwoSuanfaNotBetween(Long value1, Long value2) {
            addCriterion("two_suanfa not between", value1, value2, "twoSuanfa");
            return (Criteria) this;
        }

        public Criteria andTwoIntenetIsNull() {
            addCriterion("two_intenet is null");
            return (Criteria) this;
        }

        public Criteria andTwoIntenetIsNotNull() {
            addCriterion("two_intenet is not null");
            return (Criteria) this;
        }

        public Criteria andTwoIntenetEqualTo(Long value) {
            addCriterion("two_intenet =", value, "twoIntenet");
            return (Criteria) this;
        }

        public Criteria andTwoIntenetNotEqualTo(Long value) {
            addCriterion("two_intenet <>", value, "twoIntenet");
            return (Criteria) this;
        }

        public Criteria andTwoIntenetGreaterThan(Long value) {
            addCriterion("two_intenet >", value, "twoIntenet");
            return (Criteria) this;
        }

        public Criteria andTwoIntenetGreaterThanOrEqualTo(Long value) {
            addCriterion("two_intenet >=", value, "twoIntenet");
            return (Criteria) this;
        }

        public Criteria andTwoIntenetLessThan(Long value) {
            addCriterion("two_intenet <", value, "twoIntenet");
            return (Criteria) this;
        }

        public Criteria andTwoIntenetLessThanOrEqualTo(Long value) {
            addCriterion("two_intenet <=", value, "twoIntenet");
            return (Criteria) this;
        }

        public Criteria andTwoIntenetIn(List<Long> values) {
            addCriterion("two_intenet in", values, "twoIntenet");
            return (Criteria) this;
        }

        public Criteria andTwoIntenetNotIn(List<Long> values) {
            addCriterion("two_intenet not in", values, "twoIntenet");
            return (Criteria) this;
        }

        public Criteria andTwoIntenetBetween(Long value1, Long value2) {
            addCriterion("two_intenet between", value1, value2, "twoIntenet");
            return (Criteria) this;
        }

        public Criteria andTwoIntenetNotBetween(Long value1, Long value2) {
            addCriterion("two_intenet not between", value1, value2, "twoIntenet");
            return (Criteria) this;
        }

        public Criteria andTwoXiangmuIsNull() {
            addCriterion("two_xiangmu is null");
            return (Criteria) this;
        }

        public Criteria andTwoXiangmuIsNotNull() {
            addCriterion("two_xiangmu is not null");
            return (Criteria) this;
        }

        public Criteria andTwoXiangmuEqualTo(Long value) {
            addCriterion("two_xiangmu =", value, "twoXiangmu");
            return (Criteria) this;
        }

        public Criteria andTwoXiangmuNotEqualTo(Long value) {
            addCriterion("two_xiangmu <>", value, "twoXiangmu");
            return (Criteria) this;
        }

        public Criteria andTwoXiangmuGreaterThan(Long value) {
            addCriterion("two_xiangmu >", value, "twoXiangmu");
            return (Criteria) this;
        }

        public Criteria andTwoXiangmuGreaterThanOrEqualTo(Long value) {
            addCriterion("two_xiangmu >=", value, "twoXiangmu");
            return (Criteria) this;
        }

        public Criteria andTwoXiangmuLessThan(Long value) {
            addCriterion("two_xiangmu <", value, "twoXiangmu");
            return (Criteria) this;
        }

        public Criteria andTwoXiangmuLessThanOrEqualTo(Long value) {
            addCriterion("two_xiangmu <=", value, "twoXiangmu");
            return (Criteria) this;
        }

        public Criteria andTwoXiangmuIn(List<Long> values) {
            addCriterion("two_xiangmu in", values, "twoXiangmu");
            return (Criteria) this;
        }

        public Criteria andTwoXiangmuNotIn(List<Long> values) {
            addCriterion("two_xiangmu not in", values, "twoXiangmu");
            return (Criteria) this;
        }

        public Criteria andTwoXiangmuBetween(Long value1, Long value2) {
            addCriterion("two_xiangmu between", value1, value2, "twoXiangmu");
            return (Criteria) this;
        }

        public Criteria andTwoXiangmuNotBetween(Long value1, Long value2) {
            addCriterion("two_xiangmu not between", value1, value2, "twoXiangmu");
            return (Criteria) this;
        }

        public Criteria andTwoOtherIsNull() {
            addCriterion("two_other is null");
            return (Criteria) this;
        }

        public Criteria andTwoOtherIsNotNull() {
            addCriterion("two_other is not null");
            return (Criteria) this;
        }

        public Criteria andTwoOtherEqualTo(Long value) {
            addCriterion("two_other =", value, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherNotEqualTo(Long value) {
            addCriterion("two_other <>", value, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherGreaterThan(Long value) {
            addCriterion("two_other >", value, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherGreaterThanOrEqualTo(Long value) {
            addCriterion("two_other >=", value, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherLessThan(Long value) {
            addCriterion("two_other <", value, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherLessThanOrEqualTo(Long value) {
            addCriterion("two_other <=", value, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherIn(List<Long> values) {
            addCriterion("two_other in", values, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherNotIn(List<Long> values) {
            addCriterion("two_other not in", values, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherBetween(Long value1, Long value2) {
            addCriterion("two_other between", value1, value2, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherNotBetween(Long value1, Long value2) {
            addCriterion("two_other not between", value1, value2, "twoOther");
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

        public Criteria andTwoCountEqualTo(Long value) {
            addCriterion("two_count =", value, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountNotEqualTo(Long value) {
            addCriterion("two_count <>", value, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountGreaterThan(Long value) {
            addCriterion("two_count >", value, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountGreaterThanOrEqualTo(Long value) {
            addCriterion("two_count >=", value, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountLessThan(Long value) {
            addCriterion("two_count <", value, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountLessThanOrEqualTo(Long value) {
            addCriterion("two_count <=", value, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountIn(List<Long> values) {
            addCriterion("two_count in", values, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountNotIn(List<Long> values) {
            addCriterion("two_count not in", values, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountBetween(Long value1, Long value2) {
            addCriterion("two_count between", value1, value2, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoCountNotBetween(Long value1, Long value2) {
            addCriterion("two_count not between", value1, value2, "twoCount");
            return (Criteria) this;
        }

        public Criteria andTwoLuojiIsNull() {
            addCriterion("two_luoji is null");
            return (Criteria) this;
        }

        public Criteria andTwoLuojiIsNotNull() {
            addCriterion("two_luoji is not null");
            return (Criteria) this;
        }

        public Criteria andTwoLuojiEqualTo(Long value) {
            addCriterion("two_luoji =", value, "twoLuoji");
            return (Criteria) this;
        }

        public Criteria andTwoLuojiNotEqualTo(Long value) {
            addCriterion("two_luoji <>", value, "twoLuoji");
            return (Criteria) this;
        }

        public Criteria andTwoLuojiGreaterThan(Long value) {
            addCriterion("two_luoji >", value, "twoLuoji");
            return (Criteria) this;
        }

        public Criteria andTwoLuojiGreaterThanOrEqualTo(Long value) {
            addCriterion("two_luoji >=", value, "twoLuoji");
            return (Criteria) this;
        }

        public Criteria andTwoLuojiLessThan(Long value) {
            addCriterion("two_luoji <", value, "twoLuoji");
            return (Criteria) this;
        }

        public Criteria andTwoLuojiLessThanOrEqualTo(Long value) {
            addCriterion("two_luoji <=", value, "twoLuoji");
            return (Criteria) this;
        }

        public Criteria andTwoLuojiIn(List<Long> values) {
            addCriterion("two_luoji in", values, "twoLuoji");
            return (Criteria) this;
        }

        public Criteria andTwoLuojiNotIn(List<Long> values) {
            addCriterion("two_luoji not in", values, "twoLuoji");
            return (Criteria) this;
        }

        public Criteria andTwoLuojiBetween(Long value1, Long value2) {
            addCriterion("two_luoji between", value1, value2, "twoLuoji");
            return (Criteria) this;
        }

        public Criteria andTwoLuojiNotBetween(Long value1, Long value2) {
            addCriterion("two_luoji not between", value1, value2, "twoLuoji");
            return (Criteria) this;
        }

        public Criteria andTwoChuangxinIsNull() {
            addCriterion("two_chuangxin is null");
            return (Criteria) this;
        }

        public Criteria andTwoChuangxinIsNotNull() {
            addCriterion("two_chuangxin is not null");
            return (Criteria) this;
        }

        public Criteria andTwoChuangxinEqualTo(Long value) {
            addCriterion("two_chuangxin =", value, "twoChuangxin");
            return (Criteria) this;
        }

        public Criteria andTwoChuangxinNotEqualTo(Long value) {
            addCriterion("two_chuangxin <>", value, "twoChuangxin");
            return (Criteria) this;
        }

        public Criteria andTwoChuangxinGreaterThan(Long value) {
            addCriterion("two_chuangxin >", value, "twoChuangxin");
            return (Criteria) this;
        }

        public Criteria andTwoChuangxinGreaterThanOrEqualTo(Long value) {
            addCriterion("two_chuangxin >=", value, "twoChuangxin");
            return (Criteria) this;
        }

        public Criteria andTwoChuangxinLessThan(Long value) {
            addCriterion("two_chuangxin <", value, "twoChuangxin");
            return (Criteria) this;
        }

        public Criteria andTwoChuangxinLessThanOrEqualTo(Long value) {
            addCriterion("two_chuangxin <=", value, "twoChuangxin");
            return (Criteria) this;
        }

        public Criteria andTwoChuangxinIn(List<Long> values) {
            addCriterion("two_chuangxin in", values, "twoChuangxin");
            return (Criteria) this;
        }

        public Criteria andTwoChuangxinNotIn(List<Long> values) {
            addCriterion("two_chuangxin not in", values, "twoChuangxin");
            return (Criteria) this;
        }

        public Criteria andTwoChuangxinBetween(Long value1, Long value2) {
            addCriterion("two_chuangxin between", value1, value2, "twoChuangxin");
            return (Criteria) this;
        }

        public Criteria andTwoChuangxinNotBetween(Long value1, Long value2) {
            addCriterion("two_chuangxin not between", value1, value2, "twoChuangxin");
            return (Criteria) this;
        }

        public Criteria andTwoTeamIsNull() {
            addCriterion("two_team is null");
            return (Criteria) this;
        }

        public Criteria andTwoTeamIsNotNull() {
            addCriterion("two_team is not null");
            return (Criteria) this;
        }

        public Criteria andTwoTeamEqualTo(Long value) {
            addCriterion("two_team =", value, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamNotEqualTo(Long value) {
            addCriterion("two_team <>", value, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamGreaterThan(Long value) {
            addCriterion("two_team >", value, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamGreaterThanOrEqualTo(Long value) {
            addCriterion("two_team >=", value, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamLessThan(Long value) {
            addCriterion("two_team <", value, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamLessThanOrEqualTo(Long value) {
            addCriterion("two_team <=", value, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamIn(List<Long> values) {
            addCriterion("two_team in", values, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamNotIn(List<Long> values) {
            addCriterion("two_team not in", values, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamBetween(Long value1, Long value2) {
            addCriterion("two_team between", value1, value2, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamNotBetween(Long value1, Long value2) {
            addCriterion("two_team not between", value1, value2, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoStadyIsNull() {
            addCriterion("two_stady is null");
            return (Criteria) this;
        }

        public Criteria andTwoStadyIsNotNull() {
            addCriterion("two_stady is not null");
            return (Criteria) this;
        }

        public Criteria andTwoStadyEqualTo(Long value) {
            addCriterion("two_stady =", value, "twoStady");
            return (Criteria) this;
        }

        public Criteria andTwoStadyNotEqualTo(Long value) {
            addCriterion("two_stady <>", value, "twoStady");
            return (Criteria) this;
        }

        public Criteria andTwoStadyGreaterThan(Long value) {
            addCriterion("two_stady >", value, "twoStady");
            return (Criteria) this;
        }

        public Criteria andTwoStadyGreaterThanOrEqualTo(Long value) {
            addCriterion("two_stady >=", value, "twoStady");
            return (Criteria) this;
        }

        public Criteria andTwoStadyLessThan(Long value) {
            addCriterion("two_stady <", value, "twoStady");
            return (Criteria) this;
        }

        public Criteria andTwoStadyLessThanOrEqualTo(Long value) {
            addCriterion("two_stady <=", value, "twoStady");
            return (Criteria) this;
        }

        public Criteria andTwoStadyIn(List<Long> values) {
            addCriterion("two_stady in", values, "twoStady");
            return (Criteria) this;
        }

        public Criteria andTwoStadyNotIn(List<Long> values) {
            addCriterion("two_stady not in", values, "twoStady");
            return (Criteria) this;
        }

        public Criteria andTwoStadyBetween(Long value1, Long value2) {
            addCriterion("two_stady between", value1, value2, "twoStady");
            return (Criteria) this;
        }

        public Criteria andTwoStadyNotBetween(Long value1, Long value2) {
            addCriterion("two_stady not between", value1, value2, "twoStady");
            return (Criteria) this;
        }

        public Criteria andTwoZhuoyueIsNull() {
            addCriterion("two_zhuoyue is null");
            return (Criteria) this;
        }

        public Criteria andTwoZhuoyueIsNotNull() {
            addCriterion("two_zhuoyue is not null");
            return (Criteria) this;
        }

        public Criteria andTwoZhuoyueEqualTo(Long value) {
            addCriterion("two_zhuoyue =", value, "twoZhuoyue");
            return (Criteria) this;
        }

        public Criteria andTwoZhuoyueNotEqualTo(Long value) {
            addCriterion("two_zhuoyue <>", value, "twoZhuoyue");
            return (Criteria) this;
        }

        public Criteria andTwoZhuoyueGreaterThan(Long value) {
            addCriterion("two_zhuoyue >", value, "twoZhuoyue");
            return (Criteria) this;
        }

        public Criteria andTwoZhuoyueGreaterThanOrEqualTo(Long value) {
            addCriterion("two_zhuoyue >=", value, "twoZhuoyue");
            return (Criteria) this;
        }

        public Criteria andTwoZhuoyueLessThan(Long value) {
            addCriterion("two_zhuoyue <", value, "twoZhuoyue");
            return (Criteria) this;
        }

        public Criteria andTwoZhuoyueLessThanOrEqualTo(Long value) {
            addCriterion("two_zhuoyue <=", value, "twoZhuoyue");
            return (Criteria) this;
        }

        public Criteria andTwoZhuoyueIn(List<Long> values) {
            addCriterion("two_zhuoyue in", values, "twoZhuoyue");
            return (Criteria) this;
        }

        public Criteria andTwoZhuoyueNotIn(List<Long> values) {
            addCriterion("two_zhuoyue not in", values, "twoZhuoyue");
            return (Criteria) this;
        }

        public Criteria andTwoZhuoyueBetween(Long value1, Long value2) {
            addCriterion("two_zhuoyue between", value1, value2, "twoZhuoyue");
            return (Criteria) this;
        }

        public Criteria andTwoZhuoyueNotBetween(Long value1, Long value2) {
            addCriterion("two_zhuoyue not between", value1, value2, "twoZhuoyue");
            return (Criteria) this;
        }

        public Criteria andTwoSumIsNull() {
            addCriterion("two_sum is null");
            return (Criteria) this;
        }

        public Criteria andTwoSumIsNotNull() {
            addCriterion("two_sum is not null");
            return (Criteria) this;
        }

        public Criteria andTwoSumEqualTo(Long value) {
            addCriterion("two_sum =", value, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumNotEqualTo(Long value) {
            addCriterion("two_sum <>", value, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumGreaterThan(Long value) {
            addCriterion("two_sum >", value, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumGreaterThanOrEqualTo(Long value) {
            addCriterion("two_sum >=", value, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumLessThan(Long value) {
            addCriterion("two_sum <", value, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumLessThanOrEqualTo(Long value) {
            addCriterion("two_sum <=", value, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumIn(List<Long> values) {
            addCriterion("two_sum in", values, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumNotIn(List<Long> values) {
            addCriterion("two_sum not in", values, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumBetween(Long value1, Long value2) {
            addCriterion("two_sum between", value1, value2, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumNotBetween(Long value1, Long value2) {
            addCriterion("two_sum not between", value1, value2, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoResultIsNull() {
            addCriterion("two_result is null");
            return (Criteria) this;
        }

        public Criteria andTwoResultIsNotNull() {
            addCriterion("two_result is not null");
            return (Criteria) this;
        }

        public Criteria andTwoResultEqualTo(Long value) {
            addCriterion("two_result =", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultNotEqualTo(Long value) {
            addCriterion("two_result <>", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultGreaterThan(Long value) {
            addCriterion("two_result >", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultGreaterThanOrEqualTo(Long value) {
            addCriterion("two_result >=", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultLessThan(Long value) {
            addCriterion("two_result <", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultLessThanOrEqualTo(Long value) {
            addCriterion("two_result <=", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultIn(List<Long> values) {
            addCriterion("two_result in", values, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultNotIn(List<Long> values) {
            addCriterion("two_result not in", values, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultBetween(Long value1, Long value2) {
            addCriterion("two_result between", value1, value2, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultNotBetween(Long value1, Long value2) {
            addCriterion("two_result not between", value1, value2, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoYewuIsNull() {
            addCriterion("two_yewu is null");
            return (Criteria) this;
        }

        public Criteria andTwoYewuIsNotNull() {
            addCriterion("two_yewu is not null");
            return (Criteria) this;
        }

        public Criteria andTwoYewuEqualTo(String value) {
            addCriterion("two_yewu =", value, "twoYewu");
            return (Criteria) this;
        }

        public Criteria andTwoYewuNotEqualTo(String value) {
            addCriterion("two_yewu <>", value, "twoYewu");
            return (Criteria) this;
        }

        public Criteria andTwoYewuGreaterThan(String value) {
            addCriterion("two_yewu >", value, "twoYewu");
            return (Criteria) this;
        }

        public Criteria andTwoYewuGreaterThanOrEqualTo(String value) {
            addCriterion("two_yewu >=", value, "twoYewu");
            return (Criteria) this;
        }

        public Criteria andTwoYewuLessThan(String value) {
            addCriterion("two_yewu <", value, "twoYewu");
            return (Criteria) this;
        }

        public Criteria andTwoYewuLessThanOrEqualTo(String value) {
            addCriterion("two_yewu <=", value, "twoYewu");
            return (Criteria) this;
        }

        public Criteria andTwoYewuLike(String value) {
            addCriterion("two_yewu like", value, "twoYewu");
            return (Criteria) this;
        }

        public Criteria andTwoYewuNotLike(String value) {
            addCriterion("two_yewu not like", value, "twoYewu");
            return (Criteria) this;
        }

        public Criteria andTwoYewuIn(List<String> values) {
            addCriterion("two_yewu in", values, "twoYewu");
            return (Criteria) this;
        }

        public Criteria andTwoYewuNotIn(List<String> values) {
            addCriterion("two_yewu not in", values, "twoYewu");
            return (Criteria) this;
        }

        public Criteria andTwoYewuBetween(String value1, String value2) {
            addCriterion("two_yewu between", value1, value2, "twoYewu");
            return (Criteria) this;
        }

        public Criteria andTwoYewuNotBetween(String value1, String value2) {
            addCriterion("two_yewu not between", value1, value2, "twoYewu");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiIsNull() {
            addCriterion("two_suzhi is null");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiIsNotNull() {
            addCriterion("two_suzhi is not null");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiEqualTo(String value) {
            addCriterion("two_suzhi =", value, "twoSuzhi");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiNotEqualTo(String value) {
            addCriterion("two_suzhi <>", value, "twoSuzhi");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiGreaterThan(String value) {
            addCriterion("two_suzhi >", value, "twoSuzhi");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiGreaterThanOrEqualTo(String value) {
            addCriterion("two_suzhi >=", value, "twoSuzhi");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiLessThan(String value) {
            addCriterion("two_suzhi <", value, "twoSuzhi");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiLessThanOrEqualTo(String value) {
            addCriterion("two_suzhi <=", value, "twoSuzhi");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiLike(String value) {
            addCriterion("two_suzhi like", value, "twoSuzhi");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiNotLike(String value) {
            addCriterion("two_suzhi not like", value, "twoSuzhi");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiIn(List<String> values) {
            addCriterion("two_suzhi in", values, "twoSuzhi");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiNotIn(List<String> values) {
            addCriterion("two_suzhi not in", values, "twoSuzhi");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiBetween(String value1, String value2) {
            addCriterion("two_suzhi between", value1, value2, "twoSuzhi");
            return (Criteria) this;
        }

        public Criteria andTwoSuzhiNotBetween(String value1, String value2) {
            addCriterion("two_suzhi not between", value1, value2, "twoSuzhi");
            return (Criteria) this;
        }

        public Criteria andTwoZongheIsNull() {
            addCriterion("two_zonghe is null");
            return (Criteria) this;
        }

        public Criteria andTwoZongheIsNotNull() {
            addCriterion("two_zonghe is not null");
            return (Criteria) this;
        }

        public Criteria andTwoZongheEqualTo(String value) {
            addCriterion("two_zonghe =", value, "twoZonghe");
            return (Criteria) this;
        }

        public Criteria andTwoZongheNotEqualTo(String value) {
            addCriterion("two_zonghe <>", value, "twoZonghe");
            return (Criteria) this;
        }

        public Criteria andTwoZongheGreaterThan(String value) {
            addCriterion("two_zonghe >", value, "twoZonghe");
            return (Criteria) this;
        }

        public Criteria andTwoZongheGreaterThanOrEqualTo(String value) {
            addCriterion("two_zonghe >=", value, "twoZonghe");
            return (Criteria) this;
        }

        public Criteria andTwoZongheLessThan(String value) {
            addCriterion("two_zonghe <", value, "twoZonghe");
            return (Criteria) this;
        }

        public Criteria andTwoZongheLessThanOrEqualTo(String value) {
            addCriterion("two_zonghe <=", value, "twoZonghe");
            return (Criteria) this;
        }

        public Criteria andTwoZongheLike(String value) {
            addCriterion("two_zonghe like", value, "twoZonghe");
            return (Criteria) this;
        }

        public Criteria andTwoZongheNotLike(String value) {
            addCriterion("two_zonghe not like", value, "twoZonghe");
            return (Criteria) this;
        }

        public Criteria andTwoZongheIn(List<String> values) {
            addCriterion("two_zonghe in", values, "twoZonghe");
            return (Criteria) this;
        }

        public Criteria andTwoZongheNotIn(List<String> values) {
            addCriterion("two_zonghe not in", values, "twoZonghe");
            return (Criteria) this;
        }

        public Criteria andTwoZongheBetween(String value1, String value2) {
            addCriterion("two_zonghe between", value1, value2, "twoZonghe");
            return (Criteria) this;
        }

        public Criteria andTwoZongheNotBetween(String value1, String value2) {
            addCriterion("two_zonghe not between", value1, value2, "twoZonghe");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiIsNull() {
            addCriterion("one_fenpei is null");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiIsNotNull() {
            addCriterion("one_fenpei is not null");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiEqualTo(String value) {
            addCriterion("one_fenpei =", value, "oneFenpei");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiNotEqualTo(String value) {
            addCriterion("one_fenpei <>", value, "oneFenpei");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiGreaterThan(String value) {
            addCriterion("one_fenpei >", value, "oneFenpei");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiGreaterThanOrEqualTo(String value) {
            addCriterion("one_fenpei >=", value, "oneFenpei");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiLessThan(String value) {
            addCriterion("one_fenpei <", value, "oneFenpei");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiLessThanOrEqualTo(String value) {
            addCriterion("one_fenpei <=", value, "oneFenpei");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiLike(String value) {
            addCriterion("one_fenpei like", value, "oneFenpei");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiNotLike(String value) {
            addCriterion("one_fenpei not like", value, "oneFenpei");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiIn(List<String> values) {
            addCriterion("one_fenpei in", values, "oneFenpei");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiNotIn(List<String> values) {
            addCriterion("one_fenpei not in", values, "oneFenpei");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiBetween(String value1, String value2) {
            addCriterion("one_fenpei between", value1, value2, "oneFenpei");
            return (Criteria) this;
        }

        public Criteria andOneFenpeiNotBetween(String value1, String value2) {
            addCriterion("one_fenpei not between", value1, value2, "oneFenpei");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiIsNull() {
            addCriterion("two_fenpei is null");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiIsNotNull() {
            addCriterion("two_fenpei is not null");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiEqualTo(String value) {
            addCriterion("two_fenpei =", value, "twoFenpei");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiNotEqualTo(String value) {
            addCriterion("two_fenpei <>", value, "twoFenpei");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiGreaterThan(String value) {
            addCriterion("two_fenpei >", value, "twoFenpei");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiGreaterThanOrEqualTo(String value) {
            addCriterion("two_fenpei >=", value, "twoFenpei");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiLessThan(String value) {
            addCriterion("two_fenpei <", value, "twoFenpei");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiLessThanOrEqualTo(String value) {
            addCriterion("two_fenpei <=", value, "twoFenpei");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiLike(String value) {
            addCriterion("two_fenpei like", value, "twoFenpei");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiNotLike(String value) {
            addCriterion("two_fenpei not like", value, "twoFenpei");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiIn(List<String> values) {
            addCriterion("two_fenpei in", values, "twoFenpei");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiNotIn(List<String> values) {
            addCriterion("two_fenpei not in", values, "twoFenpei");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiBetween(String value1, String value2) {
            addCriterion("two_fenpei between", value1, value2, "twoFenpei");
            return (Criteria) this;
        }

        public Criteria andTwoFenpeiNotBetween(String value1, String value2) {
            addCriterion("two_fenpei not between", value1, value2, "twoFenpei");
            return (Criteria) this;
        }

        public Criteria andHrYijianIsNull() {
            addCriterion("hr_yijian is null");
            return (Criteria) this;
        }

        public Criteria andHrYijianIsNotNull() {
            addCriterion("hr_yijian is not null");
            return (Criteria) this;
        }

        public Criteria andHrYijianEqualTo(String value) {
            addCriterion("hr_yijian =", value, "hrYijian");
            return (Criteria) this;
        }

        public Criteria andHrYijianNotEqualTo(String value) {
            addCriterion("hr_yijian <>", value, "hrYijian");
            return (Criteria) this;
        }

        public Criteria andHrYijianGreaterThan(String value) {
            addCriterion("hr_yijian >", value, "hrYijian");
            return (Criteria) this;
        }

        public Criteria andHrYijianGreaterThanOrEqualTo(String value) {
            addCriterion("hr_yijian >=", value, "hrYijian");
            return (Criteria) this;
        }

        public Criteria andHrYijianLessThan(String value) {
            addCriterion("hr_yijian <", value, "hrYijian");
            return (Criteria) this;
        }

        public Criteria andHrYijianLessThanOrEqualTo(String value) {
            addCriterion("hr_yijian <=", value, "hrYijian");
            return (Criteria) this;
        }

        public Criteria andHrYijianLike(String value) {
            addCriterion("hr_yijian like", value, "hrYijian");
            return (Criteria) this;
        }

        public Criteria andHrYijianNotLike(String value) {
            addCriterion("hr_yijian not like", value, "hrYijian");
            return (Criteria) this;
        }

        public Criteria andHrYijianIn(List<String> values) {
            addCriterion("hr_yijian in", values, "hrYijian");
            return (Criteria) this;
        }

        public Criteria andHrYijianNotIn(List<String> values) {
            addCriterion("hr_yijian not in", values, "hrYijian");
            return (Criteria) this;
        }

        public Criteria andHrYijianBetween(String value1, String value2) {
            addCriterion("hr_yijian between", value1, value2, "hrYijian");
            return (Criteria) this;
        }

        public Criteria andHrYijianNotBetween(String value1, String value2) {
            addCriterion("hr_yijian not between", value1, value2, "hrYijian");
            return (Criteria) this;
        }

        public Criteria andOneSlaryIsNull() {
            addCriterion("one_slary is null");
            return (Criteria) this;
        }

        public Criteria andOneSlaryIsNotNull() {
            addCriterion("one_slary is not null");
            return (Criteria) this;
        }

        public Criteria andOneSlaryEqualTo(Long value) {
            addCriterion("one_slary =", value, "oneSlary");
            return (Criteria) this;
        }

        public Criteria andOneSlaryNotEqualTo(Long value) {
            addCriterion("one_slary <>", value, "oneSlary");
            return (Criteria) this;
        }

        public Criteria andOneSlaryGreaterThan(Long value) {
            addCriterion("one_slary >", value, "oneSlary");
            return (Criteria) this;
        }

        public Criteria andOneSlaryGreaterThanOrEqualTo(Long value) {
            addCriterion("one_slary >=", value, "oneSlary");
            return (Criteria) this;
        }

        public Criteria andOneSlaryLessThan(Long value) {
            addCriterion("one_slary <", value, "oneSlary");
            return (Criteria) this;
        }

        public Criteria andOneSlaryLessThanOrEqualTo(Long value) {
            addCriterion("one_slary <=", value, "oneSlary");
            return (Criteria) this;
        }

        public Criteria andOneSlaryIn(List<Long> values) {
            addCriterion("one_slary in", values, "oneSlary");
            return (Criteria) this;
        }

        public Criteria andOneSlaryNotIn(List<Long> values) {
            addCriterion("one_slary not in", values, "oneSlary");
            return (Criteria) this;
        }

        public Criteria andOneSlaryBetween(Long value1, Long value2) {
            addCriterion("one_slary between", value1, value2, "oneSlary");
            return (Criteria) this;
        }

        public Criteria andOneSlaryNotBetween(Long value1, Long value2) {
            addCriterion("one_slary not between", value1, value2, "oneSlary");
            return (Criteria) this;
        }

        public Criteria andTwoSlaryIsNull() {
            addCriterion("two_slary is null");
            return (Criteria) this;
        }

        public Criteria andTwoSlaryIsNotNull() {
            addCriterion("two_slary is not null");
            return (Criteria) this;
        }

        public Criteria andTwoSlaryEqualTo(Long value) {
            addCriterion("two_slary =", value, "twoSlary");
            return (Criteria) this;
        }

        public Criteria andTwoSlaryNotEqualTo(Long value) {
            addCriterion("two_slary <>", value, "twoSlary");
            return (Criteria) this;
        }

        public Criteria andTwoSlaryGreaterThan(Long value) {
            addCriterion("two_slary >", value, "twoSlary");
            return (Criteria) this;
        }

        public Criteria andTwoSlaryGreaterThanOrEqualTo(Long value) {
            addCriterion("two_slary >=", value, "twoSlary");
            return (Criteria) this;
        }

        public Criteria andTwoSlaryLessThan(Long value) {
            addCriterion("two_slary <", value, "twoSlary");
            return (Criteria) this;
        }

        public Criteria andTwoSlaryLessThanOrEqualTo(Long value) {
            addCriterion("two_slary <=", value, "twoSlary");
            return (Criteria) this;
        }

        public Criteria andTwoSlaryIn(List<Long> values) {
            addCriterion("two_slary in", values, "twoSlary");
            return (Criteria) this;
        }

        public Criteria andTwoSlaryNotIn(List<Long> values) {
            addCriterion("two_slary not in", values, "twoSlary");
            return (Criteria) this;
        }

        public Criteria andTwoSlaryBetween(Long value1, Long value2) {
            addCriterion("two_slary between", value1, value2, "twoSlary");
            return (Criteria) this;
        }

        public Criteria andTwoSlaryNotBetween(Long value1, Long value2) {
            addCriterion("two_slary not between", value1, value2, "twoSlary");
            return (Criteria) this;
        }

        public Criteria andHrSlaryIsNull() {
            addCriterion("hr_slary is null");
            return (Criteria) this;
        }

        public Criteria andHrSlaryIsNotNull() {
            addCriterion("hr_slary is not null");
            return (Criteria) this;
        }

        public Criteria andHrSlaryEqualTo(Long value) {
            addCriterion("hr_slary =", value, "hrSlary");
            return (Criteria) this;
        }

        public Criteria andHrSlaryNotEqualTo(Long value) {
            addCriterion("hr_slary <>", value, "hrSlary");
            return (Criteria) this;
        }

        public Criteria andHrSlaryGreaterThan(Long value) {
            addCriterion("hr_slary >", value, "hrSlary");
            return (Criteria) this;
        }

        public Criteria andHrSlaryGreaterThanOrEqualTo(Long value) {
            addCriterion("hr_slary >=", value, "hrSlary");
            return (Criteria) this;
        }

        public Criteria andHrSlaryLessThan(Long value) {
            addCriterion("hr_slary <", value, "hrSlary");
            return (Criteria) this;
        }

        public Criteria andHrSlaryLessThanOrEqualTo(Long value) {
            addCriterion("hr_slary <=", value, "hrSlary");
            return (Criteria) this;
        }

        public Criteria andHrSlaryIn(List<Long> values) {
            addCriterion("hr_slary in", values, "hrSlary");
            return (Criteria) this;
        }

        public Criteria andHrSlaryNotIn(List<Long> values) {
            addCriterion("hr_slary not in", values, "hrSlary");
            return (Criteria) this;
        }

        public Criteria andHrSlaryBetween(Long value1, Long value2) {
            addCriterion("hr_slary between", value1, value2, "hrSlary");
            return (Criteria) this;
        }

        public Criteria andHrSlaryNotBetween(Long value1, Long value2) {
            addCriterion("hr_slary not between", value1, value2, "hrSlary");
            return (Criteria) this;
        }

        public Criteria andHrNameIsNull() {
            addCriterion("hr_name is null");
            return (Criteria) this;
        }

        public Criteria andHrNameIsNotNull() {
            addCriterion("hr_name is not null");
            return (Criteria) this;
        }

        public Criteria andHrNameEqualTo(String value) {
            addCriterion("hr_name =", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameNotEqualTo(String value) {
            addCriterion("hr_name <>", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameGreaterThan(String value) {
            addCriterion("hr_name >", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameGreaterThanOrEqualTo(String value) {
            addCriterion("hr_name >=", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameLessThan(String value) {
            addCriterion("hr_name <", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameLessThanOrEqualTo(String value) {
            addCriterion("hr_name <=", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameLike(String value) {
            addCriterion("hr_name like", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameNotLike(String value) {
            addCriterion("hr_name not like", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameIn(List<String> values) {
            addCriterion("hr_name in", values, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameNotIn(List<String> values) {
            addCriterion("hr_name not in", values, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameBetween(String value1, String value2) {
            addCriterion("hr_name between", value1, value2, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameNotBetween(String value1, String value2) {
            addCriterion("hr_name not between", value1, value2, "hrName");
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