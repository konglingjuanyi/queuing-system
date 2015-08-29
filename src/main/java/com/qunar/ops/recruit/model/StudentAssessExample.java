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

        public Criteria andStudenIdIsNull() {
            addCriterion("studen_id is null");
            return (Criteria) this;
        }

        public Criteria andStudenIdIsNotNull() {
            addCriterion("studen_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudenIdEqualTo(Integer value) {
            addCriterion("studen_id =", value, "studenId");
            return (Criteria) this;
        }

        public Criteria andStudenIdNotEqualTo(Integer value) {
            addCriterion("studen_id <>", value, "studenId");
            return (Criteria) this;
        }

        public Criteria andStudenIdGreaterThan(Integer value) {
            addCriterion("studen_id >", value, "studenId");
            return (Criteria) this;
        }

        public Criteria andStudenIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("studen_id >=", value, "studenId");
            return (Criteria) this;
        }

        public Criteria andStudenIdLessThan(Integer value) {
            addCriterion("studen_id <", value, "studenId");
            return (Criteria) this;
        }

        public Criteria andStudenIdLessThanOrEqualTo(Integer value) {
            addCriterion("studen_id <=", value, "studenId");
            return (Criteria) this;
        }

        public Criteria andStudenIdIn(List<Integer> values) {
            addCriterion("studen_id in", values, "studenId");
            return (Criteria) this;
        }

        public Criteria andStudenIdNotIn(List<Integer> values) {
            addCriterion("studen_id not in", values, "studenId");
            return (Criteria) this;
        }

        public Criteria andStudenIdBetween(Integer value1, Integer value2) {
            addCriterion("studen_id between", value1, value2, "studenId");
            return (Criteria) this;
        }

        public Criteria andStudenIdNotBetween(Integer value1, Integer value2) {
            addCriterion("studen_id not between", value1, value2, "studenId");
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

        public Criteria andOneCodeIsNull() {
            addCriterion("one_code is null");
            return (Criteria) this;
        }

        public Criteria andOneCodeIsNotNull() {
            addCriterion("one_code is not null");
            return (Criteria) this;
        }

        public Criteria andOneCodeEqualTo(Integer value) {
            addCriterion("one_code =", value, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeNotEqualTo(Integer value) {
            addCriterion("one_code <>", value, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeGreaterThan(Integer value) {
            addCriterion("one_code >", value, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_code >=", value, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeLessThan(Integer value) {
            addCriterion("one_code <", value, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeLessThanOrEqualTo(Integer value) {
            addCriterion("one_code <=", value, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeIn(List<Integer> values) {
            addCriterion("one_code in", values, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeNotIn(List<Integer> values) {
            addCriterion("one_code not in", values, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeBetween(Integer value1, Integer value2) {
            addCriterion("one_code between", value1, value2, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("one_code not between", value1, value2, "oneCode");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailIsNull() {
            addCriterion("one_code_detail is null");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailIsNotNull() {
            addCriterion("one_code_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailEqualTo(String value) {
            addCriterion("one_code_detail =", value, "oneCodeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailNotEqualTo(String value) {
            addCriterion("one_code_detail <>", value, "oneCodeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailGreaterThan(String value) {
            addCriterion("one_code_detail >", value, "oneCodeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailGreaterThanOrEqualTo(String value) {
            addCriterion("one_code_detail >=", value, "oneCodeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailLessThan(String value) {
            addCriterion("one_code_detail <", value, "oneCodeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailLessThanOrEqualTo(String value) {
            addCriterion("one_code_detail <=", value, "oneCodeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailLike(String value) {
            addCriterion("one_code_detail like", value, "oneCodeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailNotLike(String value) {
            addCriterion("one_code_detail not like", value, "oneCodeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailIn(List<String> values) {
            addCriterion("one_code_detail in", values, "oneCodeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailNotIn(List<String> values) {
            addCriterion("one_code_detail not in", values, "oneCodeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailBetween(String value1, String value2) {
            addCriterion("one_code_detail between", value1, value2, "oneCodeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCodeDetailNotBetween(String value1, String value2) {
            addCriterion("one_code_detail not between", value1, value2, "oneCodeDetail");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmIsNull() {
            addCriterion("one_algorithm is null");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmIsNotNull() {
            addCriterion("one_algorithm is not null");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmEqualTo(Integer value) {
            addCriterion("one_algorithm =", value, "oneAlgorithm");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmNotEqualTo(Integer value) {
            addCriterion("one_algorithm <>", value, "oneAlgorithm");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmGreaterThan(Integer value) {
            addCriterion("one_algorithm >", value, "oneAlgorithm");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_algorithm >=", value, "oneAlgorithm");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmLessThan(Integer value) {
            addCriterion("one_algorithm <", value, "oneAlgorithm");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmLessThanOrEqualTo(Integer value) {
            addCriterion("one_algorithm <=", value, "oneAlgorithm");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmIn(List<Integer> values) {
            addCriterion("one_algorithm in", values, "oneAlgorithm");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmNotIn(List<Integer> values) {
            addCriterion("one_algorithm not in", values, "oneAlgorithm");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmBetween(Integer value1, Integer value2) {
            addCriterion("one_algorithm between", value1, value2, "oneAlgorithm");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmNotBetween(Integer value1, Integer value2) {
            addCriterion("one_algorithm not between", value1, value2, "oneAlgorithm");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailIsNull() {
            addCriterion("one_algorithm_detail is null");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailIsNotNull() {
            addCriterion("one_algorithm_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailEqualTo(String value) {
            addCriterion("one_algorithm_detail =", value, "oneAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailNotEqualTo(String value) {
            addCriterion("one_algorithm_detail <>", value, "oneAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailGreaterThan(String value) {
            addCriterion("one_algorithm_detail >", value, "oneAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailGreaterThanOrEqualTo(String value) {
            addCriterion("one_algorithm_detail >=", value, "oneAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailLessThan(String value) {
            addCriterion("one_algorithm_detail <", value, "oneAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailLessThanOrEqualTo(String value) {
            addCriterion("one_algorithm_detail <=", value, "oneAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailLike(String value) {
            addCriterion("one_algorithm_detail like", value, "oneAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailNotLike(String value) {
            addCriterion("one_algorithm_detail not like", value, "oneAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailIn(List<String> values) {
            addCriterion("one_algorithm_detail in", values, "oneAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailNotIn(List<String> values) {
            addCriterion("one_algorithm_detail not in", values, "oneAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailBetween(String value1, String value2) {
            addCriterion("one_algorithm_detail between", value1, value2, "oneAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andOneAlgorithmDetailNotBetween(String value1, String value2) {
            addCriterion("one_algorithm_detail not between", value1, value2, "oneAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andOneNetworkIsNull() {
            addCriterion("one_network is null");
            return (Criteria) this;
        }

        public Criteria andOneNetworkIsNotNull() {
            addCriterion("one_network is not null");
            return (Criteria) this;
        }

        public Criteria andOneNetworkEqualTo(Integer value) {
            addCriterion("one_network =", value, "oneNetwork");
            return (Criteria) this;
        }

        public Criteria andOneNetworkNotEqualTo(Integer value) {
            addCriterion("one_network <>", value, "oneNetwork");
            return (Criteria) this;
        }

        public Criteria andOneNetworkGreaterThan(Integer value) {
            addCriterion("one_network >", value, "oneNetwork");
            return (Criteria) this;
        }

        public Criteria andOneNetworkGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_network >=", value, "oneNetwork");
            return (Criteria) this;
        }

        public Criteria andOneNetworkLessThan(Integer value) {
            addCriterion("one_network <", value, "oneNetwork");
            return (Criteria) this;
        }

        public Criteria andOneNetworkLessThanOrEqualTo(Integer value) {
            addCriterion("one_network <=", value, "oneNetwork");
            return (Criteria) this;
        }

        public Criteria andOneNetworkIn(List<Integer> values) {
            addCriterion("one_network in", values, "oneNetwork");
            return (Criteria) this;
        }

        public Criteria andOneNetworkNotIn(List<Integer> values) {
            addCriterion("one_network not in", values, "oneNetwork");
            return (Criteria) this;
        }

        public Criteria andOneNetworkBetween(Integer value1, Integer value2) {
            addCriterion("one_network between", value1, value2, "oneNetwork");
            return (Criteria) this;
        }

        public Criteria andOneNetworkNotBetween(Integer value1, Integer value2) {
            addCriterion("one_network not between", value1, value2, "oneNetwork");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailIsNull() {
            addCriterion("one_network_detail is null");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailIsNotNull() {
            addCriterion("one_network_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailEqualTo(String value) {
            addCriterion("one_network_detail =", value, "oneNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailNotEqualTo(String value) {
            addCriterion("one_network_detail <>", value, "oneNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailGreaterThan(String value) {
            addCriterion("one_network_detail >", value, "oneNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailGreaterThanOrEqualTo(String value) {
            addCriterion("one_network_detail >=", value, "oneNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailLessThan(String value) {
            addCriterion("one_network_detail <", value, "oneNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailLessThanOrEqualTo(String value) {
            addCriterion("one_network_detail <=", value, "oneNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailLike(String value) {
            addCriterion("one_network_detail like", value, "oneNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailNotLike(String value) {
            addCriterion("one_network_detail not like", value, "oneNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailIn(List<String> values) {
            addCriterion("one_network_detail in", values, "oneNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailNotIn(List<String> values) {
            addCriterion("one_network_detail not in", values, "oneNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailBetween(String value1, String value2) {
            addCriterion("one_network_detail between", value1, value2, "oneNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andOneNetworkDetailNotBetween(String value1, String value2) {
            addCriterion("one_network_detail not between", value1, value2, "oneNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andOneExperienceIsNull() {
            addCriterion("one_experience is null");
            return (Criteria) this;
        }

        public Criteria andOneExperienceIsNotNull() {
            addCriterion("one_experience is not null");
            return (Criteria) this;
        }

        public Criteria andOneExperienceEqualTo(Integer value) {
            addCriterion("one_experience =", value, "oneExperience");
            return (Criteria) this;
        }

        public Criteria andOneExperienceNotEqualTo(Integer value) {
            addCriterion("one_experience <>", value, "oneExperience");
            return (Criteria) this;
        }

        public Criteria andOneExperienceGreaterThan(Integer value) {
            addCriterion("one_experience >", value, "oneExperience");
            return (Criteria) this;
        }

        public Criteria andOneExperienceGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_experience >=", value, "oneExperience");
            return (Criteria) this;
        }

        public Criteria andOneExperienceLessThan(Integer value) {
            addCriterion("one_experience <", value, "oneExperience");
            return (Criteria) this;
        }

        public Criteria andOneExperienceLessThanOrEqualTo(Integer value) {
            addCriterion("one_experience <=", value, "oneExperience");
            return (Criteria) this;
        }

        public Criteria andOneExperienceIn(List<Integer> values) {
            addCriterion("one_experience in", values, "oneExperience");
            return (Criteria) this;
        }

        public Criteria andOneExperienceNotIn(List<Integer> values) {
            addCriterion("one_experience not in", values, "oneExperience");
            return (Criteria) this;
        }

        public Criteria andOneExperienceBetween(Integer value1, Integer value2) {
            addCriterion("one_experience between", value1, value2, "oneExperience");
            return (Criteria) this;
        }

        public Criteria andOneExperienceNotBetween(Integer value1, Integer value2) {
            addCriterion("one_experience not between", value1, value2, "oneExperience");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailIsNull() {
            addCriterion("one_experience_detail is null");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailIsNotNull() {
            addCriterion("one_experience_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailEqualTo(String value) {
            addCriterion("one_experience_detail =", value, "oneExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailNotEqualTo(String value) {
            addCriterion("one_experience_detail <>", value, "oneExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailGreaterThan(String value) {
            addCriterion("one_experience_detail >", value, "oneExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailGreaterThanOrEqualTo(String value) {
            addCriterion("one_experience_detail >=", value, "oneExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailLessThan(String value) {
            addCriterion("one_experience_detail <", value, "oneExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailLessThanOrEqualTo(String value) {
            addCriterion("one_experience_detail <=", value, "oneExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailLike(String value) {
            addCriterion("one_experience_detail like", value, "oneExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailNotLike(String value) {
            addCriterion("one_experience_detail not like", value, "oneExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailIn(List<String> values) {
            addCriterion("one_experience_detail in", values, "oneExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailNotIn(List<String> values) {
            addCriterion("one_experience_detail not in", values, "oneExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailBetween(String value1, String value2) {
            addCriterion("one_experience_detail between", value1, value2, "oneExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andOneExperienceDetailNotBetween(String value1, String value2) {
            addCriterion("one_experience_detail not between", value1, value2, "oneExperienceDetail");
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

        public Criteria andOneOtherEqualTo(Integer value) {
            addCriterion("one_other =", value, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherNotEqualTo(Integer value) {
            addCriterion("one_other <>", value, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherGreaterThan(Integer value) {
            addCriterion("one_other >", value, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_other >=", value, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherLessThan(Integer value) {
            addCriterion("one_other <", value, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherLessThanOrEqualTo(Integer value) {
            addCriterion("one_other <=", value, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherIn(List<Integer> values) {
            addCriterion("one_other in", values, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherNotIn(List<Integer> values) {
            addCriterion("one_other not in", values, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherBetween(Integer value1, Integer value2) {
            addCriterion("one_other between", value1, value2, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherNotBetween(Integer value1, Integer value2) {
            addCriterion("one_other not between", value1, value2, "oneOther");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailIsNull() {
            addCriterion("one_other_detail is null");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailIsNotNull() {
            addCriterion("one_other_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailEqualTo(String value) {
            addCriterion("one_other_detail =", value, "oneOtherDetail");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailNotEqualTo(String value) {
            addCriterion("one_other_detail <>", value, "oneOtherDetail");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailGreaterThan(String value) {
            addCriterion("one_other_detail >", value, "oneOtherDetail");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailGreaterThanOrEqualTo(String value) {
            addCriterion("one_other_detail >=", value, "oneOtherDetail");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailLessThan(String value) {
            addCriterion("one_other_detail <", value, "oneOtherDetail");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailLessThanOrEqualTo(String value) {
            addCriterion("one_other_detail <=", value, "oneOtherDetail");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailLike(String value) {
            addCriterion("one_other_detail like", value, "oneOtherDetail");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailNotLike(String value) {
            addCriterion("one_other_detail not like", value, "oneOtherDetail");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailIn(List<String> values) {
            addCriterion("one_other_detail in", values, "oneOtherDetail");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailNotIn(List<String> values) {
            addCriterion("one_other_detail not in", values, "oneOtherDetail");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailBetween(String value1, String value2) {
            addCriterion("one_other_detail between", value1, value2, "oneOtherDetail");
            return (Criteria) this;
        }

        public Criteria andOneOtherDetailNotBetween(String value1, String value2) {
            addCriterion("one_other_detail not between", value1, value2, "oneOtherDetail");
            return (Criteria) this;
        }

        public Criteria andOneLogicIsNull() {
            addCriterion("one_logic is null");
            return (Criteria) this;
        }

        public Criteria andOneLogicIsNotNull() {
            addCriterion("one_logic is not null");
            return (Criteria) this;
        }

        public Criteria andOneLogicEqualTo(Integer value) {
            addCriterion("one_logic =", value, "oneLogic");
            return (Criteria) this;
        }

        public Criteria andOneLogicNotEqualTo(Integer value) {
            addCriterion("one_logic <>", value, "oneLogic");
            return (Criteria) this;
        }

        public Criteria andOneLogicGreaterThan(Integer value) {
            addCriterion("one_logic >", value, "oneLogic");
            return (Criteria) this;
        }

        public Criteria andOneLogicGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_logic >=", value, "oneLogic");
            return (Criteria) this;
        }

        public Criteria andOneLogicLessThan(Integer value) {
            addCriterion("one_logic <", value, "oneLogic");
            return (Criteria) this;
        }

        public Criteria andOneLogicLessThanOrEqualTo(Integer value) {
            addCriterion("one_logic <=", value, "oneLogic");
            return (Criteria) this;
        }

        public Criteria andOneLogicIn(List<Integer> values) {
            addCriterion("one_logic in", values, "oneLogic");
            return (Criteria) this;
        }

        public Criteria andOneLogicNotIn(List<Integer> values) {
            addCriterion("one_logic not in", values, "oneLogic");
            return (Criteria) this;
        }

        public Criteria andOneLogicBetween(Integer value1, Integer value2) {
            addCriterion("one_logic between", value1, value2, "oneLogic");
            return (Criteria) this;
        }

        public Criteria andOneLogicNotBetween(Integer value1, Integer value2) {
            addCriterion("one_logic not between", value1, value2, "oneLogic");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailIsNull() {
            addCriterion("one_logic_detail is null");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailIsNotNull() {
            addCriterion("one_logic_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailEqualTo(String value) {
            addCriterion("one_logic_detail =", value, "oneLogicDetail");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailNotEqualTo(String value) {
            addCriterion("one_logic_detail <>", value, "oneLogicDetail");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailGreaterThan(String value) {
            addCriterion("one_logic_detail >", value, "oneLogicDetail");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailGreaterThanOrEqualTo(String value) {
            addCriterion("one_logic_detail >=", value, "oneLogicDetail");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailLessThan(String value) {
            addCriterion("one_logic_detail <", value, "oneLogicDetail");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailLessThanOrEqualTo(String value) {
            addCriterion("one_logic_detail <=", value, "oneLogicDetail");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailLike(String value) {
            addCriterion("one_logic_detail like", value, "oneLogicDetail");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailNotLike(String value) {
            addCriterion("one_logic_detail not like", value, "oneLogicDetail");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailIn(List<String> values) {
            addCriterion("one_logic_detail in", values, "oneLogicDetail");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailNotIn(List<String> values) {
            addCriterion("one_logic_detail not in", values, "oneLogicDetail");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailBetween(String value1, String value2) {
            addCriterion("one_logic_detail between", value1, value2, "oneLogicDetail");
            return (Criteria) this;
        }

        public Criteria andOneLogicDetailNotBetween(String value1, String value2) {
            addCriterion("one_logic_detail not between", value1, value2, "oneLogicDetail");
            return (Criteria) this;
        }

        public Criteria andOneCreativeIsNull() {
            addCriterion("one_creative is null");
            return (Criteria) this;
        }

        public Criteria andOneCreativeIsNotNull() {
            addCriterion("one_creative is not null");
            return (Criteria) this;
        }

        public Criteria andOneCreativeEqualTo(Integer value) {
            addCriterion("one_creative =", value, "oneCreative");
            return (Criteria) this;
        }

        public Criteria andOneCreativeNotEqualTo(Integer value) {
            addCriterion("one_creative <>", value, "oneCreative");
            return (Criteria) this;
        }

        public Criteria andOneCreativeGreaterThan(Integer value) {
            addCriterion("one_creative >", value, "oneCreative");
            return (Criteria) this;
        }

        public Criteria andOneCreativeGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_creative >=", value, "oneCreative");
            return (Criteria) this;
        }

        public Criteria andOneCreativeLessThan(Integer value) {
            addCriterion("one_creative <", value, "oneCreative");
            return (Criteria) this;
        }

        public Criteria andOneCreativeLessThanOrEqualTo(Integer value) {
            addCriterion("one_creative <=", value, "oneCreative");
            return (Criteria) this;
        }

        public Criteria andOneCreativeIn(List<Integer> values) {
            addCriterion("one_creative in", values, "oneCreative");
            return (Criteria) this;
        }

        public Criteria andOneCreativeNotIn(List<Integer> values) {
            addCriterion("one_creative not in", values, "oneCreative");
            return (Criteria) this;
        }

        public Criteria andOneCreativeBetween(Integer value1, Integer value2) {
            addCriterion("one_creative between", value1, value2, "oneCreative");
            return (Criteria) this;
        }

        public Criteria andOneCreativeNotBetween(Integer value1, Integer value2) {
            addCriterion("one_creative not between", value1, value2, "oneCreative");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailIsNull() {
            addCriterion("one_creative_detail is null");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailIsNotNull() {
            addCriterion("one_creative_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailEqualTo(String value) {
            addCriterion("one_creative_detail =", value, "oneCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailNotEqualTo(String value) {
            addCriterion("one_creative_detail <>", value, "oneCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailGreaterThan(String value) {
            addCriterion("one_creative_detail >", value, "oneCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailGreaterThanOrEqualTo(String value) {
            addCriterion("one_creative_detail >=", value, "oneCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailLessThan(String value) {
            addCriterion("one_creative_detail <", value, "oneCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailLessThanOrEqualTo(String value) {
            addCriterion("one_creative_detail <=", value, "oneCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailLike(String value) {
            addCriterion("one_creative_detail like", value, "oneCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailNotLike(String value) {
            addCriterion("one_creative_detail not like", value, "oneCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailIn(List<String> values) {
            addCriterion("one_creative_detail in", values, "oneCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailNotIn(List<String> values) {
            addCriterion("one_creative_detail not in", values, "oneCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailBetween(String value1, String value2) {
            addCriterion("one_creative_detail between", value1, value2, "oneCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andOneCreativeDetailNotBetween(String value1, String value2) {
            addCriterion("one_creative_detail not between", value1, value2, "oneCreativeDetail");
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

        public Criteria andOneTeamEqualTo(Integer value) {
            addCriterion("one_team =", value, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamNotEqualTo(Integer value) {
            addCriterion("one_team <>", value, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamGreaterThan(Integer value) {
            addCriterion("one_team >", value, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_team >=", value, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamLessThan(Integer value) {
            addCriterion("one_team <", value, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamLessThanOrEqualTo(Integer value) {
            addCriterion("one_team <=", value, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamIn(List<Integer> values) {
            addCriterion("one_team in", values, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamNotIn(List<Integer> values) {
            addCriterion("one_team not in", values, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamBetween(Integer value1, Integer value2) {
            addCriterion("one_team between", value1, value2, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamNotBetween(Integer value1, Integer value2) {
            addCriterion("one_team not between", value1, value2, "oneTeam");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailIsNull() {
            addCriterion("one_team_detail is null");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailIsNotNull() {
            addCriterion("one_team_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailEqualTo(String value) {
            addCriterion("one_team_detail =", value, "oneTeamDetail");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailNotEqualTo(String value) {
            addCriterion("one_team_detail <>", value, "oneTeamDetail");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailGreaterThan(String value) {
            addCriterion("one_team_detail >", value, "oneTeamDetail");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailGreaterThanOrEqualTo(String value) {
            addCriterion("one_team_detail >=", value, "oneTeamDetail");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailLessThan(String value) {
            addCriterion("one_team_detail <", value, "oneTeamDetail");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailLessThanOrEqualTo(String value) {
            addCriterion("one_team_detail <=", value, "oneTeamDetail");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailLike(String value) {
            addCriterion("one_team_detail like", value, "oneTeamDetail");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailNotLike(String value) {
            addCriterion("one_team_detail not like", value, "oneTeamDetail");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailIn(List<String> values) {
            addCriterion("one_team_detail in", values, "oneTeamDetail");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailNotIn(List<String> values) {
            addCriterion("one_team_detail not in", values, "oneTeamDetail");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailBetween(String value1, String value2) {
            addCriterion("one_team_detail between", value1, value2, "oneTeamDetail");
            return (Criteria) this;
        }

        public Criteria andOneTeamDetailNotBetween(String value1, String value2) {
            addCriterion("one_team_detail not between", value1, value2, "oneTeamDetail");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningIsNull() {
            addCriterion("one_continuouslearning is null");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningIsNotNull() {
            addCriterion("one_continuouslearning is not null");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningEqualTo(Integer value) {
            addCriterion("one_continuouslearning =", value, "oneContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningNotEqualTo(Integer value) {
            addCriterion("one_continuouslearning <>", value, "oneContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningGreaterThan(Integer value) {
            addCriterion("one_continuouslearning >", value, "oneContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_continuouslearning >=", value, "oneContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningLessThan(Integer value) {
            addCriterion("one_continuouslearning <", value, "oneContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningLessThanOrEqualTo(Integer value) {
            addCriterion("one_continuouslearning <=", value, "oneContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningIn(List<Integer> values) {
            addCriterion("one_continuouslearning in", values, "oneContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningNotIn(List<Integer> values) {
            addCriterion("one_continuouslearning not in", values, "oneContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningBetween(Integer value1, Integer value2) {
            addCriterion("one_continuouslearning between", value1, value2, "oneContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningNotBetween(Integer value1, Integer value2) {
            addCriterion("one_continuouslearning not between", value1, value2, "oneContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailIsNull() {
            addCriterion("one_continuouslearning_detail is null");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailIsNotNull() {
            addCriterion("one_continuouslearning_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailEqualTo(String value) {
            addCriterion("one_continuouslearning_detail =", value, "oneContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailNotEqualTo(String value) {
            addCriterion("one_continuouslearning_detail <>", value, "oneContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailGreaterThan(String value) {
            addCriterion("one_continuouslearning_detail >", value, "oneContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailGreaterThanOrEqualTo(String value) {
            addCriterion("one_continuouslearning_detail >=", value, "oneContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailLessThan(String value) {
            addCriterion("one_continuouslearning_detail <", value, "oneContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailLessThanOrEqualTo(String value) {
            addCriterion("one_continuouslearning_detail <=", value, "oneContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailLike(String value) {
            addCriterion("one_continuouslearning_detail like", value, "oneContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailNotLike(String value) {
            addCriterion("one_continuouslearning_detail not like", value, "oneContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailIn(List<String> values) {
            addCriterion("one_continuouslearning_detail in", values, "oneContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailNotIn(List<String> values) {
            addCriterion("one_continuouslearning_detail not in", values, "oneContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailBetween(String value1, String value2) {
            addCriterion("one_continuouslearning_detail between", value1, value2, "oneContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andOneContinuouslearningDetailNotBetween(String value1, String value2) {
            addCriterion("one_continuouslearning_detail not between", value1, value2, "oneContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingIsNull() {
            addCriterion("one_outstanding is null");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingIsNotNull() {
            addCriterion("one_outstanding is not null");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingEqualTo(Integer value) {
            addCriterion("one_outstanding =", value, "oneOutstanding");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingNotEqualTo(Integer value) {
            addCriterion("one_outstanding <>", value, "oneOutstanding");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingGreaterThan(Integer value) {
            addCriterion("one_outstanding >", value, "oneOutstanding");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_outstanding >=", value, "oneOutstanding");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingLessThan(Integer value) {
            addCriterion("one_outstanding <", value, "oneOutstanding");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingLessThanOrEqualTo(Integer value) {
            addCriterion("one_outstanding <=", value, "oneOutstanding");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingIn(List<Integer> values) {
            addCriterion("one_outstanding in", values, "oneOutstanding");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingNotIn(List<Integer> values) {
            addCriterion("one_outstanding not in", values, "oneOutstanding");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingBetween(Integer value1, Integer value2) {
            addCriterion("one_outstanding between", value1, value2, "oneOutstanding");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingNotBetween(Integer value1, Integer value2) {
            addCriterion("one_outstanding not between", value1, value2, "oneOutstanding");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailIsNull() {
            addCriterion("one_outstanding_detail is null");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailIsNotNull() {
            addCriterion("one_outstanding_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailEqualTo(String value) {
            addCriterion("one_outstanding_detail =", value, "oneOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailNotEqualTo(String value) {
            addCriterion("one_outstanding_detail <>", value, "oneOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailGreaterThan(String value) {
            addCriterion("one_outstanding_detail >", value, "oneOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailGreaterThanOrEqualTo(String value) {
            addCriterion("one_outstanding_detail >=", value, "oneOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailLessThan(String value) {
            addCriterion("one_outstanding_detail <", value, "oneOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailLessThanOrEqualTo(String value) {
            addCriterion("one_outstanding_detail <=", value, "oneOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailLike(String value) {
            addCriterion("one_outstanding_detail like", value, "oneOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailNotLike(String value) {
            addCriterion("one_outstanding_detail not like", value, "oneOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailIn(List<String> values) {
            addCriterion("one_outstanding_detail in", values, "oneOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailNotIn(List<String> values) {
            addCriterion("one_outstanding_detail not in", values, "oneOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailBetween(String value1, String value2) {
            addCriterion("one_outstanding_detail between", value1, value2, "oneOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andOneOutstandingDetailNotBetween(String value1, String value2) {
            addCriterion("one_outstanding_detail not between", value1, value2, "oneOutstandingDetail");
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

        public Criteria andTwoCodeEqualTo(Integer value) {
            addCriterion("two_code =", value, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeNotEqualTo(Integer value) {
            addCriterion("two_code <>", value, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeGreaterThan(Integer value) {
            addCriterion("two_code >", value, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_code >=", value, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeLessThan(Integer value) {
            addCriterion("two_code <", value, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeLessThanOrEqualTo(Integer value) {
            addCriterion("two_code <=", value, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeIn(List<Integer> values) {
            addCriterion("two_code in", values, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeNotIn(List<Integer> values) {
            addCriterion("two_code not in", values, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeBetween(Integer value1, Integer value2) {
            addCriterion("two_code between", value1, value2, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("two_code not between", value1, value2, "twoCode");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailIsNull() {
            addCriterion("two_code_detail is null");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailIsNotNull() {
            addCriterion("two_code_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailEqualTo(String value) {
            addCriterion("two_code_detail =", value, "twoCodeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailNotEqualTo(String value) {
            addCriterion("two_code_detail <>", value, "twoCodeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailGreaterThan(String value) {
            addCriterion("two_code_detail >", value, "twoCodeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailGreaterThanOrEqualTo(String value) {
            addCriterion("two_code_detail >=", value, "twoCodeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailLessThan(String value) {
            addCriterion("two_code_detail <", value, "twoCodeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailLessThanOrEqualTo(String value) {
            addCriterion("two_code_detail <=", value, "twoCodeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailLike(String value) {
            addCriterion("two_code_detail like", value, "twoCodeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailNotLike(String value) {
            addCriterion("two_code_detail not like", value, "twoCodeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailIn(List<String> values) {
            addCriterion("two_code_detail in", values, "twoCodeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailNotIn(List<String> values) {
            addCriterion("two_code_detail not in", values, "twoCodeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailBetween(String value1, String value2) {
            addCriterion("two_code_detail between", value1, value2, "twoCodeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCodeDetailNotBetween(String value1, String value2) {
            addCriterion("two_code_detail not between", value1, value2, "twoCodeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmIsNull() {
            addCriterion("two_algorithm is null");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmIsNotNull() {
            addCriterion("two_algorithm is not null");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmEqualTo(Integer value) {
            addCriterion("two_algorithm =", value, "twoAlgorithm");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmNotEqualTo(Integer value) {
            addCriterion("two_algorithm <>", value, "twoAlgorithm");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmGreaterThan(Integer value) {
            addCriterion("two_algorithm >", value, "twoAlgorithm");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_algorithm >=", value, "twoAlgorithm");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmLessThan(Integer value) {
            addCriterion("two_algorithm <", value, "twoAlgorithm");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmLessThanOrEqualTo(Integer value) {
            addCriterion("two_algorithm <=", value, "twoAlgorithm");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmIn(List<Integer> values) {
            addCriterion("two_algorithm in", values, "twoAlgorithm");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmNotIn(List<Integer> values) {
            addCriterion("two_algorithm not in", values, "twoAlgorithm");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmBetween(Integer value1, Integer value2) {
            addCriterion("two_algorithm between", value1, value2, "twoAlgorithm");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmNotBetween(Integer value1, Integer value2) {
            addCriterion("two_algorithm not between", value1, value2, "twoAlgorithm");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailIsNull() {
            addCriterion("two_algorithm_detail is null");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailIsNotNull() {
            addCriterion("two_algorithm_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailEqualTo(String value) {
            addCriterion("two_algorithm_detail =", value, "twoAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailNotEqualTo(String value) {
            addCriterion("two_algorithm_detail <>", value, "twoAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailGreaterThan(String value) {
            addCriterion("two_algorithm_detail >", value, "twoAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailGreaterThanOrEqualTo(String value) {
            addCriterion("two_algorithm_detail >=", value, "twoAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailLessThan(String value) {
            addCriterion("two_algorithm_detail <", value, "twoAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailLessThanOrEqualTo(String value) {
            addCriterion("two_algorithm_detail <=", value, "twoAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailLike(String value) {
            addCriterion("two_algorithm_detail like", value, "twoAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailNotLike(String value) {
            addCriterion("two_algorithm_detail not like", value, "twoAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailIn(List<String> values) {
            addCriterion("two_algorithm_detail in", values, "twoAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailNotIn(List<String> values) {
            addCriterion("two_algorithm_detail not in", values, "twoAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailBetween(String value1, String value2) {
            addCriterion("two_algorithm_detail between", value1, value2, "twoAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andTwoAlgorithmDetailNotBetween(String value1, String value2) {
            addCriterion("two_algorithm_detail not between", value1, value2, "twoAlgorithmDetail");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkIsNull() {
            addCriterion("two_network is null");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkIsNotNull() {
            addCriterion("two_network is not null");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkEqualTo(Integer value) {
            addCriterion("two_network =", value, "twoNetwork");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkNotEqualTo(Integer value) {
            addCriterion("two_network <>", value, "twoNetwork");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkGreaterThan(Integer value) {
            addCriterion("two_network >", value, "twoNetwork");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_network >=", value, "twoNetwork");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkLessThan(Integer value) {
            addCriterion("two_network <", value, "twoNetwork");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkLessThanOrEqualTo(Integer value) {
            addCriterion("two_network <=", value, "twoNetwork");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkIn(List<Integer> values) {
            addCriterion("two_network in", values, "twoNetwork");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkNotIn(List<Integer> values) {
            addCriterion("two_network not in", values, "twoNetwork");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkBetween(Integer value1, Integer value2) {
            addCriterion("two_network between", value1, value2, "twoNetwork");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkNotBetween(Integer value1, Integer value2) {
            addCriterion("two_network not between", value1, value2, "twoNetwork");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailIsNull() {
            addCriterion("two_network_detail is null");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailIsNotNull() {
            addCriterion("two_network_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailEqualTo(String value) {
            addCriterion("two_network_detail =", value, "twoNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailNotEqualTo(String value) {
            addCriterion("two_network_detail <>", value, "twoNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailGreaterThan(String value) {
            addCriterion("two_network_detail >", value, "twoNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailGreaterThanOrEqualTo(String value) {
            addCriterion("two_network_detail >=", value, "twoNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailLessThan(String value) {
            addCriterion("two_network_detail <", value, "twoNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailLessThanOrEqualTo(String value) {
            addCriterion("two_network_detail <=", value, "twoNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailLike(String value) {
            addCriterion("two_network_detail like", value, "twoNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailNotLike(String value) {
            addCriterion("two_network_detail not like", value, "twoNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailIn(List<String> values) {
            addCriterion("two_network_detail in", values, "twoNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailNotIn(List<String> values) {
            addCriterion("two_network_detail not in", values, "twoNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailBetween(String value1, String value2) {
            addCriterion("two_network_detail between", value1, value2, "twoNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andTwoNetworkDetailNotBetween(String value1, String value2) {
            addCriterion("two_network_detail not between", value1, value2, "twoNetworkDetail");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceIsNull() {
            addCriterion("two_experience is null");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceIsNotNull() {
            addCriterion("two_experience is not null");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceEqualTo(Integer value) {
            addCriterion("two_experience =", value, "twoExperience");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceNotEqualTo(Integer value) {
            addCriterion("two_experience <>", value, "twoExperience");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceGreaterThan(Integer value) {
            addCriterion("two_experience >", value, "twoExperience");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_experience >=", value, "twoExperience");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceLessThan(Integer value) {
            addCriterion("two_experience <", value, "twoExperience");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceLessThanOrEqualTo(Integer value) {
            addCriterion("two_experience <=", value, "twoExperience");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceIn(List<Integer> values) {
            addCriterion("two_experience in", values, "twoExperience");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceNotIn(List<Integer> values) {
            addCriterion("two_experience not in", values, "twoExperience");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceBetween(Integer value1, Integer value2) {
            addCriterion("two_experience between", value1, value2, "twoExperience");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceNotBetween(Integer value1, Integer value2) {
            addCriterion("two_experience not between", value1, value2, "twoExperience");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailIsNull() {
            addCriterion("two_experience_detail is null");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailIsNotNull() {
            addCriterion("two_experience_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailEqualTo(String value) {
            addCriterion("two_experience_detail =", value, "twoExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailNotEqualTo(String value) {
            addCriterion("two_experience_detail <>", value, "twoExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailGreaterThan(String value) {
            addCriterion("two_experience_detail >", value, "twoExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailGreaterThanOrEqualTo(String value) {
            addCriterion("two_experience_detail >=", value, "twoExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailLessThan(String value) {
            addCriterion("two_experience_detail <", value, "twoExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailLessThanOrEqualTo(String value) {
            addCriterion("two_experience_detail <=", value, "twoExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailLike(String value) {
            addCriterion("two_experience_detail like", value, "twoExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailNotLike(String value) {
            addCriterion("two_experience_detail not like", value, "twoExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailIn(List<String> values) {
            addCriterion("two_experience_detail in", values, "twoExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailNotIn(List<String> values) {
            addCriterion("two_experience_detail not in", values, "twoExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailBetween(String value1, String value2) {
            addCriterion("two_experience_detail between", value1, value2, "twoExperienceDetail");
            return (Criteria) this;
        }

        public Criteria andTwoExperienceDetailNotBetween(String value1, String value2) {
            addCriterion("two_experience_detail not between", value1, value2, "twoExperienceDetail");
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

        public Criteria andTwoOtherEqualTo(Integer value) {
            addCriterion("two_other =", value, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherNotEqualTo(Integer value) {
            addCriterion("two_other <>", value, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherGreaterThan(Integer value) {
            addCriterion("two_other >", value, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_other >=", value, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherLessThan(Integer value) {
            addCriterion("two_other <", value, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherLessThanOrEqualTo(Integer value) {
            addCriterion("two_other <=", value, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherIn(List<Integer> values) {
            addCriterion("two_other in", values, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherNotIn(List<Integer> values) {
            addCriterion("two_other not in", values, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherBetween(Integer value1, Integer value2) {
            addCriterion("two_other between", value1, value2, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherNotBetween(Integer value1, Integer value2) {
            addCriterion("two_other not between", value1, value2, "twoOther");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailIsNull() {
            addCriterion("two_other_detail is null");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailIsNotNull() {
            addCriterion("two_other_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailEqualTo(String value) {
            addCriterion("two_other_detail =", value, "twoOtherDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailNotEqualTo(String value) {
            addCriterion("two_other_detail <>", value, "twoOtherDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailGreaterThan(String value) {
            addCriterion("two_other_detail >", value, "twoOtherDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailGreaterThanOrEqualTo(String value) {
            addCriterion("two_other_detail >=", value, "twoOtherDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailLessThan(String value) {
            addCriterion("two_other_detail <", value, "twoOtherDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailLessThanOrEqualTo(String value) {
            addCriterion("two_other_detail <=", value, "twoOtherDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailLike(String value) {
            addCriterion("two_other_detail like", value, "twoOtherDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailNotLike(String value) {
            addCriterion("two_other_detail not like", value, "twoOtherDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailIn(List<String> values) {
            addCriterion("two_other_detail in", values, "twoOtherDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailNotIn(List<String> values) {
            addCriterion("two_other_detail not in", values, "twoOtherDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailBetween(String value1, String value2) {
            addCriterion("two_other_detail between", value1, value2, "twoOtherDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOtherDetailNotBetween(String value1, String value2) {
            addCriterion("two_other_detail not between", value1, value2, "twoOtherDetail");
            return (Criteria) this;
        }

        public Criteria andTwoLogicIsNull() {
            addCriterion("two_logic is null");
            return (Criteria) this;
        }

        public Criteria andTwoLogicIsNotNull() {
            addCriterion("two_logic is not null");
            return (Criteria) this;
        }

        public Criteria andTwoLogicEqualTo(Integer value) {
            addCriterion("two_logic =", value, "twoLogic");
            return (Criteria) this;
        }

        public Criteria andTwoLogicNotEqualTo(Integer value) {
            addCriterion("two_logic <>", value, "twoLogic");
            return (Criteria) this;
        }

        public Criteria andTwoLogicGreaterThan(Integer value) {
            addCriterion("two_logic >", value, "twoLogic");
            return (Criteria) this;
        }

        public Criteria andTwoLogicGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_logic >=", value, "twoLogic");
            return (Criteria) this;
        }

        public Criteria andTwoLogicLessThan(Integer value) {
            addCriterion("two_logic <", value, "twoLogic");
            return (Criteria) this;
        }

        public Criteria andTwoLogicLessThanOrEqualTo(Integer value) {
            addCriterion("two_logic <=", value, "twoLogic");
            return (Criteria) this;
        }

        public Criteria andTwoLogicIn(List<Integer> values) {
            addCriterion("two_logic in", values, "twoLogic");
            return (Criteria) this;
        }

        public Criteria andTwoLogicNotIn(List<Integer> values) {
            addCriterion("two_logic not in", values, "twoLogic");
            return (Criteria) this;
        }

        public Criteria andTwoLogicBetween(Integer value1, Integer value2) {
            addCriterion("two_logic between", value1, value2, "twoLogic");
            return (Criteria) this;
        }

        public Criteria andTwoLogicNotBetween(Integer value1, Integer value2) {
            addCriterion("two_logic not between", value1, value2, "twoLogic");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailIsNull() {
            addCriterion("two_logic_detail is null");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailIsNotNull() {
            addCriterion("two_logic_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailEqualTo(String value) {
            addCriterion("two_logic_detail =", value, "twoLogicDetail");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailNotEqualTo(String value) {
            addCriterion("two_logic_detail <>", value, "twoLogicDetail");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailGreaterThan(String value) {
            addCriterion("two_logic_detail >", value, "twoLogicDetail");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailGreaterThanOrEqualTo(String value) {
            addCriterion("two_logic_detail >=", value, "twoLogicDetail");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailLessThan(String value) {
            addCriterion("two_logic_detail <", value, "twoLogicDetail");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailLessThanOrEqualTo(String value) {
            addCriterion("two_logic_detail <=", value, "twoLogicDetail");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailLike(String value) {
            addCriterion("two_logic_detail like", value, "twoLogicDetail");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailNotLike(String value) {
            addCriterion("two_logic_detail not like", value, "twoLogicDetail");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailIn(List<String> values) {
            addCriterion("two_logic_detail in", values, "twoLogicDetail");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailNotIn(List<String> values) {
            addCriterion("two_logic_detail not in", values, "twoLogicDetail");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailBetween(String value1, String value2) {
            addCriterion("two_logic_detail between", value1, value2, "twoLogicDetail");
            return (Criteria) this;
        }

        public Criteria andTwoLogicDetailNotBetween(String value1, String value2) {
            addCriterion("two_logic_detail not between", value1, value2, "twoLogicDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeIsNull() {
            addCriterion("two_creative is null");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeIsNotNull() {
            addCriterion("two_creative is not null");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeEqualTo(Integer value) {
            addCriterion("two_creative =", value, "twoCreative");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeNotEqualTo(Integer value) {
            addCriterion("two_creative <>", value, "twoCreative");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeGreaterThan(Integer value) {
            addCriterion("two_creative >", value, "twoCreative");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_creative >=", value, "twoCreative");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeLessThan(Integer value) {
            addCriterion("two_creative <", value, "twoCreative");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeLessThanOrEqualTo(Integer value) {
            addCriterion("two_creative <=", value, "twoCreative");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeIn(List<Integer> values) {
            addCriterion("two_creative in", values, "twoCreative");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeNotIn(List<Integer> values) {
            addCriterion("two_creative not in", values, "twoCreative");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeBetween(Integer value1, Integer value2) {
            addCriterion("two_creative between", value1, value2, "twoCreative");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeNotBetween(Integer value1, Integer value2) {
            addCriterion("two_creative not between", value1, value2, "twoCreative");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailIsNull() {
            addCriterion("two_creative_detail is null");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailIsNotNull() {
            addCriterion("two_creative_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailEqualTo(String value) {
            addCriterion("two_creative_detail =", value, "twoCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailNotEqualTo(String value) {
            addCriterion("two_creative_detail <>", value, "twoCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailGreaterThan(String value) {
            addCriterion("two_creative_detail >", value, "twoCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailGreaterThanOrEqualTo(String value) {
            addCriterion("two_creative_detail >=", value, "twoCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailLessThan(String value) {
            addCriterion("two_creative_detail <", value, "twoCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailLessThanOrEqualTo(String value) {
            addCriterion("two_creative_detail <=", value, "twoCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailLike(String value) {
            addCriterion("two_creative_detail like", value, "twoCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailNotLike(String value) {
            addCriterion("two_creative_detail not like", value, "twoCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailIn(List<String> values) {
            addCriterion("two_creative_detail in", values, "twoCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailNotIn(List<String> values) {
            addCriterion("two_creative_detail not in", values, "twoCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailBetween(String value1, String value2) {
            addCriterion("two_creative_detail between", value1, value2, "twoCreativeDetail");
            return (Criteria) this;
        }

        public Criteria andTwoCreativeDetailNotBetween(String value1, String value2) {
            addCriterion("two_creative_detail not between", value1, value2, "twoCreativeDetail");
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

        public Criteria andTwoTeamEqualTo(Integer value) {
            addCriterion("two_team =", value, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamNotEqualTo(Integer value) {
            addCriterion("two_team <>", value, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamGreaterThan(Integer value) {
            addCriterion("two_team >", value, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_team >=", value, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamLessThan(Integer value) {
            addCriterion("two_team <", value, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamLessThanOrEqualTo(Integer value) {
            addCriterion("two_team <=", value, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamIn(List<Integer> values) {
            addCriterion("two_team in", values, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamNotIn(List<Integer> values) {
            addCriterion("two_team not in", values, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamBetween(Integer value1, Integer value2) {
            addCriterion("two_team between", value1, value2, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamNotBetween(Integer value1, Integer value2) {
            addCriterion("two_team not between", value1, value2, "twoTeam");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailIsNull() {
            addCriterion("two_team_detail is null");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailIsNotNull() {
            addCriterion("two_team_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailEqualTo(String value) {
            addCriterion("two_team_detail =", value, "twoTeamDetail");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailNotEqualTo(String value) {
            addCriterion("two_team_detail <>", value, "twoTeamDetail");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailGreaterThan(String value) {
            addCriterion("two_team_detail >", value, "twoTeamDetail");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailGreaterThanOrEqualTo(String value) {
            addCriterion("two_team_detail >=", value, "twoTeamDetail");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailLessThan(String value) {
            addCriterion("two_team_detail <", value, "twoTeamDetail");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailLessThanOrEqualTo(String value) {
            addCriterion("two_team_detail <=", value, "twoTeamDetail");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailLike(String value) {
            addCriterion("two_team_detail like", value, "twoTeamDetail");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailNotLike(String value) {
            addCriterion("two_team_detail not like", value, "twoTeamDetail");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailIn(List<String> values) {
            addCriterion("two_team_detail in", values, "twoTeamDetail");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailNotIn(List<String> values) {
            addCriterion("two_team_detail not in", values, "twoTeamDetail");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailBetween(String value1, String value2) {
            addCriterion("two_team_detail between", value1, value2, "twoTeamDetail");
            return (Criteria) this;
        }

        public Criteria andTwoTeamDetailNotBetween(String value1, String value2) {
            addCriterion("two_team_detail not between", value1, value2, "twoTeamDetail");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningIsNull() {
            addCriterion("two_continuouslearning is null");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningIsNotNull() {
            addCriterion("two_continuouslearning is not null");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningEqualTo(Integer value) {
            addCriterion("two_continuouslearning =", value, "twoContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningNotEqualTo(Integer value) {
            addCriterion("two_continuouslearning <>", value, "twoContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningGreaterThan(Integer value) {
            addCriterion("two_continuouslearning >", value, "twoContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_continuouslearning >=", value, "twoContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningLessThan(Integer value) {
            addCriterion("two_continuouslearning <", value, "twoContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningLessThanOrEqualTo(Integer value) {
            addCriterion("two_continuouslearning <=", value, "twoContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningIn(List<Integer> values) {
            addCriterion("two_continuouslearning in", values, "twoContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningNotIn(List<Integer> values) {
            addCriterion("two_continuouslearning not in", values, "twoContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningBetween(Integer value1, Integer value2) {
            addCriterion("two_continuouslearning between", value1, value2, "twoContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningNotBetween(Integer value1, Integer value2) {
            addCriterion("two_continuouslearning not between", value1, value2, "twoContinuouslearning");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailIsNull() {
            addCriterion("two_continuouslearning_detail is null");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailIsNotNull() {
            addCriterion("two_continuouslearning_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailEqualTo(String value) {
            addCriterion("two_continuouslearning_detail =", value, "twoContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailNotEqualTo(String value) {
            addCriterion("two_continuouslearning_detail <>", value, "twoContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailGreaterThan(String value) {
            addCriterion("two_continuouslearning_detail >", value, "twoContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailGreaterThanOrEqualTo(String value) {
            addCriterion("two_continuouslearning_detail >=", value, "twoContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailLessThan(String value) {
            addCriterion("two_continuouslearning_detail <", value, "twoContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailLessThanOrEqualTo(String value) {
            addCriterion("two_continuouslearning_detail <=", value, "twoContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailLike(String value) {
            addCriterion("two_continuouslearning_detail like", value, "twoContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailNotLike(String value) {
            addCriterion("two_continuouslearning_detail not like", value, "twoContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailIn(List<String> values) {
            addCriterion("two_continuouslearning_detail in", values, "twoContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailNotIn(List<String> values) {
            addCriterion("two_continuouslearning_detail not in", values, "twoContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailBetween(String value1, String value2) {
            addCriterion("two_continuouslearning_detail between", value1, value2, "twoContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andTwoContinuouslearningDetailNotBetween(String value1, String value2) {
            addCriterion("two_continuouslearning_detail not between", value1, value2, "twoContinuouslearningDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingIsNull() {
            addCriterion("two_outstanding is null");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingIsNotNull() {
            addCriterion("two_outstanding is not null");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingEqualTo(Integer value) {
            addCriterion("two_outstanding =", value, "twoOutstanding");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingNotEqualTo(Integer value) {
            addCriterion("two_outstanding <>", value, "twoOutstanding");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingGreaterThan(Integer value) {
            addCriterion("two_outstanding >", value, "twoOutstanding");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_outstanding >=", value, "twoOutstanding");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingLessThan(Integer value) {
            addCriterion("two_outstanding <", value, "twoOutstanding");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingLessThanOrEqualTo(Integer value) {
            addCriterion("two_outstanding <=", value, "twoOutstanding");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingIn(List<Integer> values) {
            addCriterion("two_outstanding in", values, "twoOutstanding");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingNotIn(List<Integer> values) {
            addCriterion("two_outstanding not in", values, "twoOutstanding");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingBetween(Integer value1, Integer value2) {
            addCriterion("two_outstanding between", value1, value2, "twoOutstanding");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingNotBetween(Integer value1, Integer value2) {
            addCriterion("two_outstanding not between", value1, value2, "twoOutstanding");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailIsNull() {
            addCriterion("two_outstanding_detail is null");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailIsNotNull() {
            addCriterion("two_outstanding_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailEqualTo(String value) {
            addCriterion("two_outstanding_detail =", value, "twoOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailNotEqualTo(String value) {
            addCriterion("two_outstanding_detail <>", value, "twoOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailGreaterThan(String value) {
            addCriterion("two_outstanding_detail >", value, "twoOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailGreaterThanOrEqualTo(String value) {
            addCriterion("two_outstanding_detail >=", value, "twoOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailLessThan(String value) {
            addCriterion("two_outstanding_detail <", value, "twoOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailLessThanOrEqualTo(String value) {
            addCriterion("two_outstanding_detail <=", value, "twoOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailLike(String value) {
            addCriterion("two_outstanding_detail like", value, "twoOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailNotLike(String value) {
            addCriterion("two_outstanding_detail not like", value, "twoOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailIn(List<String> values) {
            addCriterion("two_outstanding_detail in", values, "twoOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailNotIn(List<String> values) {
            addCriterion("two_outstanding_detail not in", values, "twoOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailBetween(String value1, String value2) {
            addCriterion("two_outstanding_detail between", value1, value2, "twoOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andTwoOutstandingDetailNotBetween(String value1, String value2) {
            addCriterion("two_outstanding_detail not between", value1, value2, "twoOutstandingDetail");
            return (Criteria) this;
        }

        public Criteria andOneConclusionIsNull() {
            addCriterion("one_conclusion is null");
            return (Criteria) this;
        }

        public Criteria andOneConclusionIsNotNull() {
            addCriterion("one_conclusion is not null");
            return (Criteria) this;
        }

        public Criteria andOneConclusionEqualTo(String value) {
            addCriterion("one_conclusion =", value, "oneConclusion");
            return (Criteria) this;
        }

        public Criteria andOneConclusionNotEqualTo(String value) {
            addCriterion("one_conclusion <>", value, "oneConclusion");
            return (Criteria) this;
        }

        public Criteria andOneConclusionGreaterThan(String value) {
            addCriterion("one_conclusion >", value, "oneConclusion");
            return (Criteria) this;
        }

        public Criteria andOneConclusionGreaterThanOrEqualTo(String value) {
            addCriterion("one_conclusion >=", value, "oneConclusion");
            return (Criteria) this;
        }

        public Criteria andOneConclusionLessThan(String value) {
            addCriterion("one_conclusion <", value, "oneConclusion");
            return (Criteria) this;
        }

        public Criteria andOneConclusionLessThanOrEqualTo(String value) {
            addCriterion("one_conclusion <=", value, "oneConclusion");
            return (Criteria) this;
        }

        public Criteria andOneConclusionLike(String value) {
            addCriterion("one_conclusion like", value, "oneConclusion");
            return (Criteria) this;
        }

        public Criteria andOneConclusionNotLike(String value) {
            addCriterion("one_conclusion not like", value, "oneConclusion");
            return (Criteria) this;
        }

        public Criteria andOneConclusionIn(List<String> values) {
            addCriterion("one_conclusion in", values, "oneConclusion");
            return (Criteria) this;
        }

        public Criteria andOneConclusionNotIn(List<String> values) {
            addCriterion("one_conclusion not in", values, "oneConclusion");
            return (Criteria) this;
        }

        public Criteria andOneConclusionBetween(String value1, String value2) {
            addCriterion("one_conclusion between", value1, value2, "oneConclusion");
            return (Criteria) this;
        }

        public Criteria andOneConclusionNotBetween(String value1, String value2) {
            addCriterion("one_conclusion not between", value1, value2, "oneConclusion");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryIsNull() {
            addCriterion("one_suggest_salary is null");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryIsNotNull() {
            addCriterion("one_suggest_salary is not null");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryEqualTo(Integer value) {
            addCriterion("one_suggest_salary =", value, "oneSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryNotEqualTo(Integer value) {
            addCriterion("one_suggest_salary <>", value, "oneSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryGreaterThan(Integer value) {
            addCriterion("one_suggest_salary >", value, "oneSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_suggest_salary >=", value, "oneSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryLessThan(Integer value) {
            addCriterion("one_suggest_salary <", value, "oneSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryLessThanOrEqualTo(Integer value) {
            addCriterion("one_suggest_salary <=", value, "oneSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryIn(List<Integer> values) {
            addCriterion("one_suggest_salary in", values, "oneSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryNotIn(List<Integer> values) {
            addCriterion("one_suggest_salary not in", values, "oneSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryBetween(Integer value1, Integer value2) {
            addCriterion("one_suggest_salary between", value1, value2, "oneSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("one_suggest_salary not between", value1, value2, "oneSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailIsNull() {
            addCriterion("one_suggest_salary_detail is null");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailIsNotNull() {
            addCriterion("one_suggest_salary_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailEqualTo(String value) {
            addCriterion("one_suggest_salary_detail =", value, "oneSuggestSalaryDetail");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailNotEqualTo(String value) {
            addCriterion("one_suggest_salary_detail <>", value, "oneSuggestSalaryDetail");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailGreaterThan(String value) {
            addCriterion("one_suggest_salary_detail >", value, "oneSuggestSalaryDetail");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailGreaterThanOrEqualTo(String value) {
            addCriterion("one_suggest_salary_detail >=", value, "oneSuggestSalaryDetail");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailLessThan(String value) {
            addCriterion("one_suggest_salary_detail <", value, "oneSuggestSalaryDetail");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailLessThanOrEqualTo(String value) {
            addCriterion("one_suggest_salary_detail <=", value, "oneSuggestSalaryDetail");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailLike(String value) {
            addCriterion("one_suggest_salary_detail like", value, "oneSuggestSalaryDetail");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailNotLike(String value) {
            addCriterion("one_suggest_salary_detail not like", value, "oneSuggestSalaryDetail");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailIn(List<String> values) {
            addCriterion("one_suggest_salary_detail in", values, "oneSuggestSalaryDetail");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailNotIn(List<String> values) {
            addCriterion("one_suggest_salary_detail not in", values, "oneSuggestSalaryDetail");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailBetween(String value1, String value2) {
            addCriterion("one_suggest_salary_detail between", value1, value2, "oneSuggestSalaryDetail");
            return (Criteria) this;
        }

        public Criteria andOneSuggestSalaryDetailNotBetween(String value1, String value2) {
            addCriterion("one_suggest_salary_detail not between", value1, value2, "oneSuggestSalaryDetail");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionIsNull() {
            addCriterion("two_conclusion is null");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionIsNotNull() {
            addCriterion("two_conclusion is not null");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionEqualTo(Integer value) {
            addCriterion("two_conclusion =", value, "twoConclusion");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionNotEqualTo(Integer value) {
            addCriterion("two_conclusion <>", value, "twoConclusion");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionGreaterThan(Integer value) {
            addCriterion("two_conclusion >", value, "twoConclusion");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_conclusion >=", value, "twoConclusion");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionLessThan(Integer value) {
            addCriterion("two_conclusion <", value, "twoConclusion");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionLessThanOrEqualTo(Integer value) {
            addCriterion("two_conclusion <=", value, "twoConclusion");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionIn(List<Integer> values) {
            addCriterion("two_conclusion in", values, "twoConclusion");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionNotIn(List<Integer> values) {
            addCriterion("two_conclusion not in", values, "twoConclusion");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionBetween(Integer value1, Integer value2) {
            addCriterion("two_conclusion between", value1, value2, "twoConclusion");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionNotBetween(Integer value1, Integer value2) {
            addCriterion("two_conclusion not between", value1, value2, "twoConclusion");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailIsNull() {
            addCriterion("two_conclusion_detail is null");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailIsNotNull() {
            addCriterion("two_conclusion_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailEqualTo(String value) {
            addCriterion("two_conclusion_detail =", value, "twoConclusionDetail");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailNotEqualTo(String value) {
            addCriterion("two_conclusion_detail <>", value, "twoConclusionDetail");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailGreaterThan(String value) {
            addCriterion("two_conclusion_detail >", value, "twoConclusionDetail");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailGreaterThanOrEqualTo(String value) {
            addCriterion("two_conclusion_detail >=", value, "twoConclusionDetail");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailLessThan(String value) {
            addCriterion("two_conclusion_detail <", value, "twoConclusionDetail");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailLessThanOrEqualTo(String value) {
            addCriterion("two_conclusion_detail <=", value, "twoConclusionDetail");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailLike(String value) {
            addCriterion("two_conclusion_detail like", value, "twoConclusionDetail");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailNotLike(String value) {
            addCriterion("two_conclusion_detail not like", value, "twoConclusionDetail");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailIn(List<String> values) {
            addCriterion("two_conclusion_detail in", values, "twoConclusionDetail");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailNotIn(List<String> values) {
            addCriterion("two_conclusion_detail not in", values, "twoConclusionDetail");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailBetween(String value1, String value2) {
            addCriterion("two_conclusion_detail between", value1, value2, "twoConclusionDetail");
            return (Criteria) this;
        }

        public Criteria andTwoConclusionDetailNotBetween(String value1, String value2) {
            addCriterion("two_conclusion_detail not between", value1, value2, "twoConclusionDetail");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaIsNull() {
            addCriterion("one_allocation_idea is null");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaIsNotNull() {
            addCriterion("one_allocation_idea is not null");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaEqualTo(String value) {
            addCriterion("one_allocation_idea =", value, "oneAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaNotEqualTo(String value) {
            addCriterion("one_allocation_idea <>", value, "oneAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaGreaterThan(String value) {
            addCriterion("one_allocation_idea >", value, "oneAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaGreaterThanOrEqualTo(String value) {
            addCriterion("one_allocation_idea >=", value, "oneAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaLessThan(String value) {
            addCriterion("one_allocation_idea <", value, "oneAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaLessThanOrEqualTo(String value) {
            addCriterion("one_allocation_idea <=", value, "oneAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaLike(String value) {
            addCriterion("one_allocation_idea like", value, "oneAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaNotLike(String value) {
            addCriterion("one_allocation_idea not like", value, "oneAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaIn(List<String> values) {
            addCriterion("one_allocation_idea in", values, "oneAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaNotIn(List<String> values) {
            addCriterion("one_allocation_idea not in", values, "oneAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaBetween(String value1, String value2) {
            addCriterion("one_allocation_idea between", value1, value2, "oneAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andOneAllocationIdeaNotBetween(String value1, String value2) {
            addCriterion("one_allocation_idea not between", value1, value2, "oneAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaIsNull() {
            addCriterion("two_allocation_idea is null");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaIsNotNull() {
            addCriterion("two_allocation_idea is not null");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaEqualTo(String value) {
            addCriterion("two_allocation_idea =", value, "twoAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaNotEqualTo(String value) {
            addCriterion("two_allocation_idea <>", value, "twoAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaGreaterThan(String value) {
            addCriterion("two_allocation_idea >", value, "twoAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaGreaterThanOrEqualTo(String value) {
            addCriterion("two_allocation_idea >=", value, "twoAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaLessThan(String value) {
            addCriterion("two_allocation_idea <", value, "twoAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaLessThanOrEqualTo(String value) {
            addCriterion("two_allocation_idea <=", value, "twoAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaLike(String value) {
            addCriterion("two_allocation_idea like", value, "twoAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaNotLike(String value) {
            addCriterion("two_allocation_idea not like", value, "twoAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaIn(List<String> values) {
            addCriterion("two_allocation_idea in", values, "twoAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaNotIn(List<String> values) {
            addCriterion("two_allocation_idea not in", values, "twoAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaBetween(String value1, String value2) {
            addCriterion("two_allocation_idea between", value1, value2, "twoAllocationIdea");
            return (Criteria) this;
        }

        public Criteria andTwoAllocationIdeaNotBetween(String value1, String value2) {
            addCriterion("two_allocation_idea not between", value1, value2, "twoAllocationIdea");
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

        public Criteria andHrSuggestSalaryIsNull() {
            addCriterion("hr_suggest_salary is null");
            return (Criteria) this;
        }

        public Criteria andHrSuggestSalaryIsNotNull() {
            addCriterion("hr_suggest_salary is not null");
            return (Criteria) this;
        }

        public Criteria andHrSuggestSalaryEqualTo(Integer value) {
            addCriterion("hr_suggest_salary =", value, "hrSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andHrSuggestSalaryNotEqualTo(Integer value) {
            addCriterion("hr_suggest_salary <>", value, "hrSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andHrSuggestSalaryGreaterThan(Integer value) {
            addCriterion("hr_suggest_salary >", value, "hrSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andHrSuggestSalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("hr_suggest_salary >=", value, "hrSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andHrSuggestSalaryLessThan(Integer value) {
            addCriterion("hr_suggest_salary <", value, "hrSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andHrSuggestSalaryLessThanOrEqualTo(Integer value) {
            addCriterion("hr_suggest_salary <=", value, "hrSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andHrSuggestSalaryIn(List<Integer> values) {
            addCriterion("hr_suggest_salary in", values, "hrSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andHrSuggestSalaryNotIn(List<Integer> values) {
            addCriterion("hr_suggest_salary not in", values, "hrSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andHrSuggestSalaryBetween(Integer value1, Integer value2) {
            addCriterion("hr_suggest_salary between", value1, value2, "hrSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andHrSuggestSalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("hr_suggest_salary not between", value1, value2, "hrSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaIsNull() {
            addCriterion("hr_detail_idea is null");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaIsNotNull() {
            addCriterion("hr_detail_idea is not null");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaEqualTo(String value) {
            addCriterion("hr_detail_idea =", value, "hrDetailIdea");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaNotEqualTo(String value) {
            addCriterion("hr_detail_idea <>", value, "hrDetailIdea");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaGreaterThan(String value) {
            addCriterion("hr_detail_idea >", value, "hrDetailIdea");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaGreaterThanOrEqualTo(String value) {
            addCriterion("hr_detail_idea >=", value, "hrDetailIdea");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaLessThan(String value) {
            addCriterion("hr_detail_idea <", value, "hrDetailIdea");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaLessThanOrEqualTo(String value) {
            addCriterion("hr_detail_idea <=", value, "hrDetailIdea");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaLike(String value) {
            addCriterion("hr_detail_idea like", value, "hrDetailIdea");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaNotLike(String value) {
            addCriterion("hr_detail_idea not like", value, "hrDetailIdea");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaIn(List<String> values) {
            addCriterion("hr_detail_idea in", values, "hrDetailIdea");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaNotIn(List<String> values) {
            addCriterion("hr_detail_idea not in", values, "hrDetailIdea");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaBetween(String value1, String value2) {
            addCriterion("hr_detail_idea between", value1, value2, "hrDetailIdea");
            return (Criteria) this;
        }

        public Criteria andHrDetailIdeaNotBetween(String value1, String value2) {
            addCriterion("hr_detail_idea not between", value1, value2, "hrDetailIdea");
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

        public Criteria andOneSumEqualTo(Integer value) {
            addCriterion("one_sum =", value, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumNotEqualTo(Integer value) {
            addCriterion("one_sum <>", value, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumGreaterThan(Integer value) {
            addCriterion("one_sum >", value, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_sum >=", value, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumLessThan(Integer value) {
            addCriterion("one_sum <", value, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumLessThanOrEqualTo(Integer value) {
            addCriterion("one_sum <=", value, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumIn(List<Integer> values) {
            addCriterion("one_sum in", values, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumNotIn(List<Integer> values) {
            addCriterion("one_sum not in", values, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumBetween(Integer value1, Integer value2) {
            addCriterion("one_sum between", value1, value2, "oneSum");
            return (Criteria) this;
        }

        public Criteria andOneSumNotBetween(Integer value1, Integer value2) {
            addCriterion("one_sum not between", value1, value2, "oneSum");
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

        public Criteria andTwoSumEqualTo(Integer value) {
            addCriterion("two_sum =", value, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumNotEqualTo(Integer value) {
            addCriterion("two_sum <>", value, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumGreaterThan(Integer value) {
            addCriterion("two_sum >", value, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_sum >=", value, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumLessThan(Integer value) {
            addCriterion("two_sum <", value, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumLessThanOrEqualTo(Integer value) {
            addCriterion("two_sum <=", value, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumIn(List<Integer> values) {
            addCriterion("two_sum in", values, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumNotIn(List<Integer> values) {
            addCriterion("two_sum not in", values, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumBetween(Integer value1, Integer value2) {
            addCriterion("two_sum between", value1, value2, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSumNotBetween(Integer value1, Integer value2) {
            addCriterion("two_sum not between", value1, value2, "twoSum");
            return (Criteria) this;
        }

        public Criteria andTwoSuggestSalaryIsNull() {
            addCriterion("two_suggest_salary is null");
            return (Criteria) this;
        }

        public Criteria andTwoSuggestSalaryIsNotNull() {
            addCriterion("two_suggest_salary is not null");
            return (Criteria) this;
        }

        public Criteria andTwoSuggestSalaryEqualTo(Integer value) {
            addCriterion("two_suggest_salary =", value, "twoSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andTwoSuggestSalaryNotEqualTo(Integer value) {
            addCriterion("two_suggest_salary <>", value, "twoSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andTwoSuggestSalaryGreaterThan(Integer value) {
            addCriterion("two_suggest_salary >", value, "twoSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andTwoSuggestSalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_suggest_salary >=", value, "twoSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andTwoSuggestSalaryLessThan(Integer value) {
            addCriterion("two_suggest_salary <", value, "twoSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andTwoSuggestSalaryLessThanOrEqualTo(Integer value) {
            addCriterion("two_suggest_salary <=", value, "twoSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andTwoSuggestSalaryIn(List<Integer> values) {
            addCriterion("two_suggest_salary in", values, "twoSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andTwoSuggestSalaryNotIn(List<Integer> values) {
            addCriterion("two_suggest_salary not in", values, "twoSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andTwoSuggestSalaryBetween(Integer value1, Integer value2) {
            addCriterion("two_suggest_salary between", value1, value2, "twoSuggestSalary");
            return (Criteria) this;
        }

        public Criteria andTwoSuggestSalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("two_suggest_salary not between", value1, value2, "twoSuggestSalary");
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