package com.qunar.ops.recruit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentExample() {
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

        public Criteria andNoteNoIsNull() {
            addCriterion("note_no is null");
            return (Criteria) this;
        }

        public Criteria andNoteNoIsNotNull() {
            addCriterion("note_no is not null");
            return (Criteria) this;
        }

        public Criteria andNoteNoEqualTo(String value) {
            addCriterion("note_no =", value, "noteNo");
            return (Criteria) this;
        }

        public Criteria andNoteNoNotEqualTo(String value) {
            addCriterion("note_no <>", value, "noteNo");
            return (Criteria) this;
        }

        public Criteria andNoteNoGreaterThan(String value) {
            addCriterion("note_no >", value, "noteNo");
            return (Criteria) this;
        }

        public Criteria andNoteNoGreaterThanOrEqualTo(String value) {
            addCriterion("note_no >=", value, "noteNo");
            return (Criteria) this;
        }

        public Criteria andNoteNoLessThan(String value) {
            addCriterion("note_no <", value, "noteNo");
            return (Criteria) this;
        }

        public Criteria andNoteNoLessThanOrEqualTo(String value) {
            addCriterion("note_no <=", value, "noteNo");
            return (Criteria) this;
        }

        public Criteria andNoteNoLike(String value) {
            addCriterion("note_no like", value, "noteNo");
            return (Criteria) this;
        }

        public Criteria andNoteNoNotLike(String value) {
            addCriterion("note_no not like", value, "noteNo");
            return (Criteria) this;
        }

        public Criteria andNoteNoIn(List<String> values) {
            addCriterion("note_no in", values, "noteNo");
            return (Criteria) this;
        }

        public Criteria andNoteNoNotIn(List<String> values) {
            addCriterion("note_no not in", values, "noteNo");
            return (Criteria) this;
        }

        public Criteria andNoteNoBetween(String value1, String value2) {
            addCriterion("note_no between", value1, value2, "noteNo");
            return (Criteria) this;
        }

        public Criteria andNoteNoNotBetween(String value1, String value2) {
            addCriterion("note_no not between", value1, value2, "noteNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoIsNull() {
            addCriterion("phase_no is null");
            return (Criteria) this;
        }

        public Criteria andPhaseNoIsNotNull() {
            addCriterion("phase_no is not null");
            return (Criteria) this;
        }

        public Criteria andPhaseNoEqualTo(String value) {
            addCriterion("phase_no =", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoNotEqualTo(String value) {
            addCriterion("phase_no <>", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoGreaterThan(String value) {
            addCriterion("phase_no >", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoGreaterThanOrEqualTo(String value) {
            addCriterion("phase_no >=", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoLessThan(String value) {
            addCriterion("phase_no <", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoLessThanOrEqualTo(String value) {
            addCriterion("phase_no <=", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoLike(String value) {
            addCriterion("phase_no like", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoNotLike(String value) {
            addCriterion("phase_no not like", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoIn(List<String> values) {
            addCriterion("phase_no in", values, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoNotIn(List<String> values) {
            addCriterion("phase_no not in", values, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoBetween(String value1, String value2) {
            addCriterion("phase_no between", value1, value2, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoNotBetween(String value1, String value2) {
            addCriterion("phase_no not between", value1, value2, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeIsNull() {
            addCriterion("interview_time is null");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeIsNotNull() {
            addCriterion("interview_time is not null");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeEqualTo(Date value) {
            addCriterion("interview_time =", value, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeNotEqualTo(Date value) {
            addCriterion("interview_time <>", value, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeGreaterThan(Date value) {
            addCriterion("interview_time >", value, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("interview_time >=", value, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeLessThan(Date value) {
            addCriterion("interview_time <", value, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeLessThanOrEqualTo(Date value) {
            addCriterion("interview_time <=", value, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeIn(List<Date> values) {
            addCriterion("interview_time in", values, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeNotIn(List<Date> values) {
            addCriterion("interview_time not in", values, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeBetween(Date value1, Date value2) {
            addCriterion("interview_time between", value1, value2, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeNotBetween(Date value1, Date value2) {
            addCriterion("interview_time not between", value1, value2, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSchoolIsNull() {
            addCriterion("school is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIsNotNull() {
            addCriterion("school is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolEqualTo(String value) {
            addCriterion("school =", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotEqualTo(String value) {
            addCriterion("school <>", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolGreaterThan(String value) {
            addCriterion("school >", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("school >=", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLessThan(String value) {
            addCriterion("school <", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLessThanOrEqualTo(String value) {
            addCriterion("school <=", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLike(String value) {
            addCriterion("school like", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotLike(String value) {
            addCriterion("school not like", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolIn(List<String> values) {
            addCriterion("school in", values, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotIn(List<String> values) {
            addCriterion("school not in", values, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolBetween(String value1, String value2) {
            addCriterion("school between", value1, value2, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotBetween(String value1, String value2) {
            addCriterion("school not between", value1, value2, "school");
            return (Criteria) this;
        }

        public Criteria andProfessionIsNull() {
            addCriterion("profession is null");
            return (Criteria) this;
        }

        public Criteria andProfessionIsNotNull() {
            addCriterion("profession is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionEqualTo(String value) {
            addCriterion("profession =", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotEqualTo(String value) {
            addCriterion("profession <>", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionGreaterThan(String value) {
            addCriterion("profession >", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionGreaterThanOrEqualTo(String value) {
            addCriterion("profession >=", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLessThan(String value) {
            addCriterion("profession <", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLessThanOrEqualTo(String value) {
            addCriterion("profession <=", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLike(String value) {
            addCriterion("profession like", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotLike(String value) {
            addCriterion("profession not like", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionIn(List<String> values) {
            addCriterion("profession in", values, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotIn(List<String> values) {
            addCriterion("profession not in", values, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionBetween(String value1, String value2) {
            addCriterion("profession between", value1, value2, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotBetween(String value1, String value2) {
            addCriterion("profession not between", value1, value2, "profession");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNull() {
            addCriterion("card_no is null");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNotNull() {
            addCriterion("card_no is not null");
            return (Criteria) this;
        }

        public Criteria andCardNoEqualTo(String value) {
            addCriterion("card_no =", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotEqualTo(String value) {
            addCriterion("card_no <>", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThan(String value) {
            addCriterion("card_no >", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("card_no >=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThan(String value) {
            addCriterion("card_no <", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThanOrEqualTo(String value) {
            addCriterion("card_no <=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLike(String value) {
            addCriterion("card_no like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotLike(String value) {
            addCriterion("card_no not like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoIn(List<String> values) {
            addCriterion("card_no in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotIn(List<String> values) {
            addCriterion("card_no not in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoBetween(String value1, String value2) {
            addCriterion("card_no between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotBetween(String value1, String value2) {
            addCriterion("card_no not between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andQqNoIsNull() {
            addCriterion("qq_no is null");
            return (Criteria) this;
        }

        public Criteria andQqNoIsNotNull() {
            addCriterion("qq_no is not null");
            return (Criteria) this;
        }

        public Criteria andQqNoEqualTo(String value) {
            addCriterion("qq_no =", value, "qqNo");
            return (Criteria) this;
        }

        public Criteria andQqNoNotEqualTo(String value) {
            addCriterion("qq_no <>", value, "qqNo");
            return (Criteria) this;
        }

        public Criteria andQqNoGreaterThan(String value) {
            addCriterion("qq_no >", value, "qqNo");
            return (Criteria) this;
        }

        public Criteria andQqNoGreaterThanOrEqualTo(String value) {
            addCriterion("qq_no >=", value, "qqNo");
            return (Criteria) this;
        }

        public Criteria andQqNoLessThan(String value) {
            addCriterion("qq_no <", value, "qqNo");
            return (Criteria) this;
        }

        public Criteria andQqNoLessThanOrEqualTo(String value) {
            addCriterion("qq_no <=", value, "qqNo");
            return (Criteria) this;
        }

        public Criteria andQqNoLike(String value) {
            addCriterion("qq_no like", value, "qqNo");
            return (Criteria) this;
        }

        public Criteria andQqNoNotLike(String value) {
            addCriterion("qq_no not like", value, "qqNo");
            return (Criteria) this;
        }

        public Criteria andQqNoIn(List<String> values) {
            addCriterion("qq_no in", values, "qqNo");
            return (Criteria) this;
        }

        public Criteria andQqNoNotIn(List<String> values) {
            addCriterion("qq_no not in", values, "qqNo");
            return (Criteria) this;
        }

        public Criteria andQqNoBetween(String value1, String value2) {
            addCriterion("qq_no between", value1, value2, "qqNo");
            return (Criteria) this;
        }

        public Criteria andQqNoNotBetween(String value1, String value2) {
            addCriterion("qq_no not between", value1, value2, "qqNo");
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

        public Criteria andWorkStartIsNull() {
            addCriterion("work_start is null");
            return (Criteria) this;
        }

        public Criteria andWorkStartIsNotNull() {
            addCriterion("work_start is not null");
            return (Criteria) this;
        }

        public Criteria andWorkStartEqualTo(Date value) {
            addCriterion("work_start =", value, "workStart");
            return (Criteria) this;
        }

        public Criteria andWorkStartNotEqualTo(Date value) {
            addCriterion("work_start <>", value, "workStart");
            return (Criteria) this;
        }

        public Criteria andWorkStartGreaterThan(Date value) {
            addCriterion("work_start >", value, "workStart");
            return (Criteria) this;
        }

        public Criteria andWorkStartGreaterThanOrEqualTo(Date value) {
            addCriterion("work_start >=", value, "workStart");
            return (Criteria) this;
        }

        public Criteria andWorkStartLessThan(Date value) {
            addCriterion("work_start <", value, "workStart");
            return (Criteria) this;
        }

        public Criteria andWorkStartLessThanOrEqualTo(Date value) {
            addCriterion("work_start <=", value, "workStart");
            return (Criteria) this;
        }

        public Criteria andWorkStartIn(List<Date> values) {
            addCriterion("work_start in", values, "workStart");
            return (Criteria) this;
        }

        public Criteria andWorkStartNotIn(List<Date> values) {
            addCriterion("work_start not in", values, "workStart");
            return (Criteria) this;
        }

        public Criteria andWorkStartBetween(Date value1, Date value2) {
            addCriterion("work_start between", value1, value2, "workStart");
            return (Criteria) this;
        }

        public Criteria andWorkStartNotBetween(Date value1, Date value2) {
            addCriterion("work_start not between", value1, value2, "workStart");
            return (Criteria) this;
        }

        public Criteria andWorkEndIsNull() {
            addCriterion("work_end is null");
            return (Criteria) this;
        }

        public Criteria andWorkEndIsNotNull() {
            addCriterion("work_end is not null");
            return (Criteria) this;
        }

        public Criteria andWorkEndEqualTo(Date value) {
            addCriterion("work_end =", value, "workEnd");
            return (Criteria) this;
        }

        public Criteria andWorkEndNotEqualTo(Date value) {
            addCriterion("work_end <>", value, "workEnd");
            return (Criteria) this;
        }

        public Criteria andWorkEndGreaterThan(Date value) {
            addCriterion("work_end >", value, "workEnd");
            return (Criteria) this;
        }

        public Criteria andWorkEndGreaterThanOrEqualTo(Date value) {
            addCriterion("work_end >=", value, "workEnd");
            return (Criteria) this;
        }

        public Criteria andWorkEndLessThan(Date value) {
            addCriterion("work_end <", value, "workEnd");
            return (Criteria) this;
        }

        public Criteria andWorkEndLessThanOrEqualTo(Date value) {
            addCriterion("work_end <=", value, "workEnd");
            return (Criteria) this;
        }

        public Criteria andWorkEndIn(List<Date> values) {
            addCriterion("work_end in", values, "workEnd");
            return (Criteria) this;
        }

        public Criteria andWorkEndNotIn(List<Date> values) {
            addCriterion("work_end not in", values, "workEnd");
            return (Criteria) this;
        }

        public Criteria andWorkEndBetween(Date value1, Date value2) {
            addCriterion("work_end between", value1, value2, "workEnd");
            return (Criteria) this;
        }

        public Criteria andWorkEndNotBetween(Date value1, Date value2) {
            addCriterion("work_end not between", value1, value2, "workEnd");
            return (Criteria) this;
        }

        public Criteria andGraduateDateIsNull() {
            addCriterion("graduate_date is null");
            return (Criteria) this;
        }

        public Criteria andGraduateDateIsNotNull() {
            addCriterion("graduate_date is not null");
            return (Criteria) this;
        }

        public Criteria andGraduateDateEqualTo(Date value) {
            addCriterion("graduate_date =", value, "graduateDate");
            return (Criteria) this;
        }

        public Criteria andGraduateDateNotEqualTo(Date value) {
            addCriterion("graduate_date <>", value, "graduateDate");
            return (Criteria) this;
        }

        public Criteria andGraduateDateGreaterThan(Date value) {
            addCriterion("graduate_date >", value, "graduateDate");
            return (Criteria) this;
        }

        public Criteria andGraduateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("graduate_date >=", value, "graduateDate");
            return (Criteria) this;
        }

        public Criteria andGraduateDateLessThan(Date value) {
            addCriterion("graduate_date <", value, "graduateDate");
            return (Criteria) this;
        }

        public Criteria andGraduateDateLessThanOrEqualTo(Date value) {
            addCriterion("graduate_date <=", value, "graduateDate");
            return (Criteria) this;
        }

        public Criteria andGraduateDateIn(List<Date> values) {
            addCriterion("graduate_date in", values, "graduateDate");
            return (Criteria) this;
        }

        public Criteria andGraduateDateNotIn(List<Date> values) {
            addCriterion("graduate_date not in", values, "graduateDate");
            return (Criteria) this;
        }

        public Criteria andGraduateDateBetween(Date value1, Date value2) {
            addCriterion("graduate_date between", value1, value2, "graduateDate");
            return (Criteria) this;
        }

        public Criteria andGraduateDateNotBetween(Date value1, Date value2) {
            addCriterion("graduate_date not between", value1, value2, "graduateDate");
            return (Criteria) this;
        }

        public Criteria andSalaryIsNull() {
            addCriterion("salary is null");
            return (Criteria) this;
        }

        public Criteria andSalaryIsNotNull() {
            addCriterion("salary is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryEqualTo(Double value) {
            addCriterion("salary =", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotEqualTo(Double value) {
            addCriterion("salary <>", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryGreaterThan(Double value) {
            addCriterion("salary >", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryGreaterThanOrEqualTo(Double value) {
            addCriterion("salary >=", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLessThan(Double value) {
            addCriterion("salary <", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLessThanOrEqualTo(Double value) {
            addCriterion("salary <=", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryIn(List<Double> values) {
            addCriterion("salary in", values, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotIn(List<Double> values) {
            addCriterion("salary not in", values, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryBetween(Double value1, Double value2) {
            addCriterion("salary between", value1, value2, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotBetween(Double value1, Double value2) {
            addCriterion("salary not between", value1, value2, "salary");
            return (Criteria) this;
        }

        public Criteria andAssessIsNull() {
            addCriterion("assess is null");
            return (Criteria) this;
        }

        public Criteria andAssessIsNotNull() {
            addCriterion("assess is not null");
            return (Criteria) this;
        }

        public Criteria andAssessEqualTo(String value) {
            addCriterion("assess =", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessNotEqualTo(String value) {
            addCriterion("assess <>", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessGreaterThan(String value) {
            addCriterion("assess >", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessGreaterThanOrEqualTo(String value) {
            addCriterion("assess >=", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessLessThan(String value) {
            addCriterion("assess <", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessLessThanOrEqualTo(String value) {
            addCriterion("assess <=", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessLike(String value) {
            addCriterion("assess like", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessNotLike(String value) {
            addCriterion("assess not like", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessIn(List<String> values) {
            addCriterion("assess in", values, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessNotIn(List<String> values) {
            addCriterion("assess not in", values, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessBetween(String value1, String value2) {
            addCriterion("assess between", value1, value2, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessNotBetween(String value1, String value2) {
            addCriterion("assess not between", value1, value2, "assess");
            return (Criteria) this;
        }

        public Criteria andFirstTryIsNull() {
            addCriterion("first_try is null");
            return (Criteria) this;
        }

        public Criteria andFirstTryIsNotNull() {
            addCriterion("first_try is not null");
            return (Criteria) this;
        }

        public Criteria andFirstTryEqualTo(String value) {
            addCriterion("first_try =", value, "firstTry");
            return (Criteria) this;
        }

        public Criteria andFirstTryNotEqualTo(String value) {
            addCriterion("first_try <>", value, "firstTry");
            return (Criteria) this;
        }

        public Criteria andFirstTryGreaterThan(String value) {
            addCriterion("first_try >", value, "firstTry");
            return (Criteria) this;
        }

        public Criteria andFirstTryGreaterThanOrEqualTo(String value) {
            addCriterion("first_try >=", value, "firstTry");
            return (Criteria) this;
        }

        public Criteria andFirstTryLessThan(String value) {
            addCriterion("first_try <", value, "firstTry");
            return (Criteria) this;
        }

        public Criteria andFirstTryLessThanOrEqualTo(String value) {
            addCriterion("first_try <=", value, "firstTry");
            return (Criteria) this;
        }

        public Criteria andFirstTryLike(String value) {
            addCriterion("first_try like", value, "firstTry");
            return (Criteria) this;
        }

        public Criteria andFirstTryNotLike(String value) {
            addCriterion("first_try not like", value, "firstTry");
            return (Criteria) this;
        }

        public Criteria andFirstTryIn(List<String> values) {
            addCriterion("first_try in", values, "firstTry");
            return (Criteria) this;
        }

        public Criteria andFirstTryNotIn(List<String> values) {
            addCriterion("first_try not in", values, "firstTry");
            return (Criteria) this;
        }

        public Criteria andFirstTryBetween(String value1, String value2) {
            addCriterion("first_try between", value1, value2, "firstTry");
            return (Criteria) this;
        }

        public Criteria andFirstTryNotBetween(String value1, String value2) {
            addCriterion("first_try not between", value1, value2, "firstTry");
            return (Criteria) this;
        }

        public Criteria andSecondTryIsNull() {
            addCriterion("second_try is null");
            return (Criteria) this;
        }

        public Criteria andSecondTryIsNotNull() {
            addCriterion("second_try is not null");
            return (Criteria) this;
        }

        public Criteria andSecondTryEqualTo(String value) {
            addCriterion("second_try =", value, "secondTry");
            return (Criteria) this;
        }

        public Criteria andSecondTryNotEqualTo(String value) {
            addCriterion("second_try <>", value, "secondTry");
            return (Criteria) this;
        }

        public Criteria andSecondTryGreaterThan(String value) {
            addCriterion("second_try >", value, "secondTry");
            return (Criteria) this;
        }

        public Criteria andSecondTryGreaterThanOrEqualTo(String value) {
            addCriterion("second_try >=", value, "secondTry");
            return (Criteria) this;
        }

        public Criteria andSecondTryLessThan(String value) {
            addCriterion("second_try <", value, "secondTry");
            return (Criteria) this;
        }

        public Criteria andSecondTryLessThanOrEqualTo(String value) {
            addCriterion("second_try <=", value, "secondTry");
            return (Criteria) this;
        }

        public Criteria andSecondTryLike(String value) {
            addCriterion("second_try like", value, "secondTry");
            return (Criteria) this;
        }

        public Criteria andSecondTryNotLike(String value) {
            addCriterion("second_try not like", value, "secondTry");
            return (Criteria) this;
        }

        public Criteria andSecondTryIn(List<String> values) {
            addCriterion("second_try in", values, "secondTry");
            return (Criteria) this;
        }

        public Criteria andSecondTryNotIn(List<String> values) {
            addCriterion("second_try not in", values, "secondTry");
            return (Criteria) this;
        }

        public Criteria andSecondTryBetween(String value1, String value2) {
            addCriterion("second_try between", value1, value2, "secondTry");
            return (Criteria) this;
        }

        public Criteria andSecondTryNotBetween(String value1, String value2) {
            addCriterion("second_try not between", value1, value2, "secondTry");
            return (Criteria) this;
        }

        public Criteria andViewRemarkIsNull() {
            addCriterion("view_remark is null");
            return (Criteria) this;
        }

        public Criteria andViewRemarkIsNotNull() {
            addCriterion("view_remark is not null");
            return (Criteria) this;
        }

        public Criteria andViewRemarkEqualTo(String value) {
            addCriterion("view_remark =", value, "viewRemark");
            return (Criteria) this;
        }

        public Criteria andViewRemarkNotEqualTo(String value) {
            addCriterion("view_remark <>", value, "viewRemark");
            return (Criteria) this;
        }

        public Criteria andViewRemarkGreaterThan(String value) {
            addCriterion("view_remark >", value, "viewRemark");
            return (Criteria) this;
        }

        public Criteria andViewRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("view_remark >=", value, "viewRemark");
            return (Criteria) this;
        }

        public Criteria andViewRemarkLessThan(String value) {
            addCriterion("view_remark <", value, "viewRemark");
            return (Criteria) this;
        }

        public Criteria andViewRemarkLessThanOrEqualTo(String value) {
            addCriterion("view_remark <=", value, "viewRemark");
            return (Criteria) this;
        }

        public Criteria andViewRemarkLike(String value) {
            addCriterion("view_remark like", value, "viewRemark");
            return (Criteria) this;
        }

        public Criteria andViewRemarkNotLike(String value) {
            addCriterion("view_remark not like", value, "viewRemark");
            return (Criteria) this;
        }

        public Criteria andViewRemarkIn(List<String> values) {
            addCriterion("view_remark in", values, "viewRemark");
            return (Criteria) this;
        }

        public Criteria andViewRemarkNotIn(List<String> values) {
            addCriterion("view_remark not in", values, "viewRemark");
            return (Criteria) this;
        }

        public Criteria andViewRemarkBetween(String value1, String value2) {
            addCriterion("view_remark between", value1, value2, "viewRemark");
            return (Criteria) this;
        }

        public Criteria andViewRemarkNotBetween(String value1, String value2) {
            addCriterion("view_remark not between", value1, value2, "viewRemark");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andOfferStateIsNull() {
            addCriterion("offer_state is null");
            return (Criteria) this;
        }

        public Criteria andOfferStateIsNotNull() {
            addCriterion("offer_state is not null");
            return (Criteria) this;
        }

        public Criteria andOfferStateEqualTo(String value) {
            addCriterion("offer_state =", value, "offerState");
            return (Criteria) this;
        }

        public Criteria andOfferStateNotEqualTo(String value) {
            addCriterion("offer_state <>", value, "offerState");
            return (Criteria) this;
        }

        public Criteria andOfferStateGreaterThan(String value) {
            addCriterion("offer_state >", value, "offerState");
            return (Criteria) this;
        }

        public Criteria andOfferStateGreaterThanOrEqualTo(String value) {
            addCriterion("offer_state >=", value, "offerState");
            return (Criteria) this;
        }

        public Criteria andOfferStateLessThan(String value) {
            addCriterion("offer_state <", value, "offerState");
            return (Criteria) this;
        }

        public Criteria andOfferStateLessThanOrEqualTo(String value) {
            addCriterion("offer_state <=", value, "offerState");
            return (Criteria) this;
        }

        public Criteria andOfferStateLike(String value) {
            addCriterion("offer_state like", value, "offerState");
            return (Criteria) this;
        }

        public Criteria andOfferStateNotLike(String value) {
            addCriterion("offer_state not like", value, "offerState");
            return (Criteria) this;
        }

        public Criteria andOfferStateIn(List<String> values) {
            addCriterion("offer_state in", values, "offerState");
            return (Criteria) this;
        }

        public Criteria andOfferStateNotIn(List<String> values) {
            addCriterion("offer_state not in", values, "offerState");
            return (Criteria) this;
        }

        public Criteria andOfferStateBetween(String value1, String value2) {
            addCriterion("offer_state between", value1, value2, "offerState");
            return (Criteria) this;
        }

        public Criteria andOfferStateNotBetween(String value1, String value2) {
            addCriterion("offer_state not between", value1, value2, "offerState");
            return (Criteria) this;
        }

        public Criteria andThreeSideIsNull() {
            addCriterion("three_side is null");
            return (Criteria) this;
        }

        public Criteria andThreeSideIsNotNull() {
            addCriterion("three_side is not null");
            return (Criteria) this;
        }

        public Criteria andThreeSideEqualTo(String value) {
            addCriterion("three_side =", value, "threeSide");
            return (Criteria) this;
        }

        public Criteria andThreeSideNotEqualTo(String value) {
            addCriterion("three_side <>", value, "threeSide");
            return (Criteria) this;
        }

        public Criteria andThreeSideGreaterThan(String value) {
            addCriterion("three_side >", value, "threeSide");
            return (Criteria) this;
        }

        public Criteria andThreeSideGreaterThanOrEqualTo(String value) {
            addCriterion("three_side >=", value, "threeSide");
            return (Criteria) this;
        }

        public Criteria andThreeSideLessThan(String value) {
            addCriterion("three_side <", value, "threeSide");
            return (Criteria) this;
        }

        public Criteria andThreeSideLessThanOrEqualTo(String value) {
            addCriterion("three_side <=", value, "threeSide");
            return (Criteria) this;
        }

        public Criteria andThreeSideLike(String value) {
            addCriterion("three_side like", value, "threeSide");
            return (Criteria) this;
        }

        public Criteria andThreeSideNotLike(String value) {
            addCriterion("three_side not like", value, "threeSide");
            return (Criteria) this;
        }

        public Criteria andThreeSideIn(List<String> values) {
            addCriterion("three_side in", values, "threeSide");
            return (Criteria) this;
        }

        public Criteria andThreeSideNotIn(List<String> values) {
            addCriterion("three_side not in", values, "threeSide");
            return (Criteria) this;
        }

        public Criteria andThreeSideBetween(String value1, String value2) {
            addCriterion("three_side between", value1, value2, "threeSide");
            return (Criteria) this;
        }

        public Criteria andThreeSideNotBetween(String value1, String value2) {
            addCriterion("three_side not between", value1, value2, "threeSide");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andThreeStateIsNull() {
            addCriterion("three_state is null");
            return (Criteria) this;
        }

        public Criteria andThreeStateIsNotNull() {
            addCriterion("three_state is not null");
            return (Criteria) this;
        }

        public Criteria andThreeStateEqualTo(String value) {
            addCriterion("three_state =", value, "threeState");
            return (Criteria) this;
        }

        public Criteria andThreeStateNotEqualTo(String value) {
            addCriterion("three_state <>", value, "threeState");
            return (Criteria) this;
        }

        public Criteria andThreeStateGreaterThan(String value) {
            addCriterion("three_state >", value, "threeState");
            return (Criteria) this;
        }

        public Criteria andThreeStateGreaterThanOrEqualTo(String value) {
            addCriterion("three_state >=", value, "threeState");
            return (Criteria) this;
        }

        public Criteria andThreeStateLessThan(String value) {
            addCriterion("three_state <", value, "threeState");
            return (Criteria) this;
        }

        public Criteria andThreeStateLessThanOrEqualTo(String value) {
            addCriterion("three_state <=", value, "threeState");
            return (Criteria) this;
        }

        public Criteria andThreeStateLike(String value) {
            addCriterion("three_state like", value, "threeState");
            return (Criteria) this;
        }

        public Criteria andThreeStateNotLike(String value) {
            addCriterion("three_state not like", value, "threeState");
            return (Criteria) this;
        }

        public Criteria andThreeStateIn(List<String> values) {
            addCriterion("three_state in", values, "threeState");
            return (Criteria) this;
        }

        public Criteria andThreeStateNotIn(List<String> values) {
            addCriterion("three_state not in", values, "threeState");
            return (Criteria) this;
        }

        public Criteria andThreeStateBetween(String value1, String value2) {
            addCriterion("three_state between", value1, value2, "threeState");
            return (Criteria) this;
        }

        public Criteria andThreeStateNotBetween(String value1, String value2) {
            addCriterion("three_state not between", value1, value2, "threeState");
            return (Criteria) this;
        }

        public Criteria andRefuseIsNull() {
            addCriterion("refuse is null");
            return (Criteria) this;
        }

        public Criteria andRefuseIsNotNull() {
            addCriterion("refuse is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseEqualTo(String value) {
            addCriterion("refuse =", value, "refuse");
            return (Criteria) this;
        }

        public Criteria andRefuseNotEqualTo(String value) {
            addCriterion("refuse <>", value, "refuse");
            return (Criteria) this;
        }

        public Criteria andRefuseGreaterThan(String value) {
            addCriterion("refuse >", value, "refuse");
            return (Criteria) this;
        }

        public Criteria andRefuseGreaterThanOrEqualTo(String value) {
            addCriterion("refuse >=", value, "refuse");
            return (Criteria) this;
        }

        public Criteria andRefuseLessThan(String value) {
            addCriterion("refuse <", value, "refuse");
            return (Criteria) this;
        }

        public Criteria andRefuseLessThanOrEqualTo(String value) {
            addCriterion("refuse <=", value, "refuse");
            return (Criteria) this;
        }

        public Criteria andRefuseLike(String value) {
            addCriterion("refuse like", value, "refuse");
            return (Criteria) this;
        }

        public Criteria andRefuseNotLike(String value) {
            addCriterion("refuse not like", value, "refuse");
            return (Criteria) this;
        }

        public Criteria andRefuseIn(List<String> values) {
            addCriterion("refuse in", values, "refuse");
            return (Criteria) this;
        }

        public Criteria andRefuseNotIn(List<String> values) {
            addCriterion("refuse not in", values, "refuse");
            return (Criteria) this;
        }

        public Criteria andRefuseBetween(String value1, String value2) {
            addCriterion("refuse between", value1, value2, "refuse");
            return (Criteria) this;
        }

        public Criteria andRefuseNotBetween(String value1, String value2) {
            addCriterion("refuse not between", value1, value2, "refuse");
            return (Criteria) this;
        }

        public Criteria andRefuseResonIsNull() {
            addCriterion("refuse_reson is null");
            return (Criteria) this;
        }

        public Criteria andRefuseResonIsNotNull() {
            addCriterion("refuse_reson is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseResonEqualTo(String value) {
            addCriterion("refuse_reson =", value, "refuseReson");
            return (Criteria) this;
        }

        public Criteria andRefuseResonNotEqualTo(String value) {
            addCriterion("refuse_reson <>", value, "refuseReson");
            return (Criteria) this;
        }

        public Criteria andRefuseResonGreaterThan(String value) {
            addCriterion("refuse_reson >", value, "refuseReson");
            return (Criteria) this;
        }

        public Criteria andRefuseResonGreaterThanOrEqualTo(String value) {
            addCriterion("refuse_reson >=", value, "refuseReson");
            return (Criteria) this;
        }

        public Criteria andRefuseResonLessThan(String value) {
            addCriterion("refuse_reson <", value, "refuseReson");
            return (Criteria) this;
        }

        public Criteria andRefuseResonLessThanOrEqualTo(String value) {
            addCriterion("refuse_reson <=", value, "refuseReson");
            return (Criteria) this;
        }

        public Criteria andRefuseResonLike(String value) {
            addCriterion("refuse_reson like", value, "refuseReson");
            return (Criteria) this;
        }

        public Criteria andRefuseResonNotLike(String value) {
            addCriterion("refuse_reson not like", value, "refuseReson");
            return (Criteria) this;
        }

        public Criteria andRefuseResonIn(List<String> values) {
            addCriterion("refuse_reson in", values, "refuseReson");
            return (Criteria) this;
        }

        public Criteria andRefuseResonNotIn(List<String> values) {
            addCriterion("refuse_reson not in", values, "refuseReson");
            return (Criteria) this;
        }

        public Criteria andRefuseResonBetween(String value1, String value2) {
            addCriterion("refuse_reson between", value1, value2, "refuseReson");
            return (Criteria) this;
        }

        public Criteria andRefuseResonNotBetween(String value1, String value2) {
            addCriterion("refuse_reson not between", value1, value2, "refuseReson");
            return (Criteria) this;
        }

        public Criteria andRefuseDateIsNull() {
            addCriterion("refuse_date is null");
            return (Criteria) this;
        }

        public Criteria andRefuseDateIsNotNull() {
            addCriterion("refuse_date is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseDateEqualTo(Date value) {
            addCriterion("refuse_date =", value, "refuseDate");
            return (Criteria) this;
        }

        public Criteria andRefuseDateNotEqualTo(Date value) {
            addCriterion("refuse_date <>", value, "refuseDate");
            return (Criteria) this;
        }

        public Criteria andRefuseDateGreaterThan(Date value) {
            addCriterion("refuse_date >", value, "refuseDate");
            return (Criteria) this;
        }

        public Criteria andRefuseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("refuse_date >=", value, "refuseDate");
            return (Criteria) this;
        }

        public Criteria andRefuseDateLessThan(Date value) {
            addCriterion("refuse_date <", value, "refuseDate");
            return (Criteria) this;
        }

        public Criteria andRefuseDateLessThanOrEqualTo(Date value) {
            addCriterion("refuse_date <=", value, "refuseDate");
            return (Criteria) this;
        }

        public Criteria andRefuseDateIn(List<Date> values) {
            addCriterion("refuse_date in", values, "refuseDate");
            return (Criteria) this;
        }

        public Criteria andRefuseDateNotIn(List<Date> values) {
            addCriterion("refuse_date not in", values, "refuseDate");
            return (Criteria) this;
        }

        public Criteria andRefuseDateBetween(Date value1, Date value2) {
            addCriterion("refuse_date between", value1, value2, "refuseDate");
            return (Criteria) this;
        }

        public Criteria andRefuseDateNotBetween(Date value1, Date value2) {
            addCriterion("refuse_date not between", value1, value2, "refuseDate");
            return (Criteria) this;
        }

        public Criteria andBreakerIsNull() {
            addCriterion("breaker is null");
            return (Criteria) this;
        }

        public Criteria andBreakerIsNotNull() {
            addCriterion("breaker is not null");
            return (Criteria) this;
        }

        public Criteria andBreakerEqualTo(String value) {
            addCriterion("breaker =", value, "breaker");
            return (Criteria) this;
        }

        public Criteria andBreakerNotEqualTo(String value) {
            addCriterion("breaker <>", value, "breaker");
            return (Criteria) this;
        }

        public Criteria andBreakerGreaterThan(String value) {
            addCriterion("breaker >", value, "breaker");
            return (Criteria) this;
        }

        public Criteria andBreakerGreaterThanOrEqualTo(String value) {
            addCriterion("breaker >=", value, "breaker");
            return (Criteria) this;
        }

        public Criteria andBreakerLessThan(String value) {
            addCriterion("breaker <", value, "breaker");
            return (Criteria) this;
        }

        public Criteria andBreakerLessThanOrEqualTo(String value) {
            addCriterion("breaker <=", value, "breaker");
            return (Criteria) this;
        }

        public Criteria andBreakerLike(String value) {
            addCriterion("breaker like", value, "breaker");
            return (Criteria) this;
        }

        public Criteria andBreakerNotLike(String value) {
            addCriterion("breaker not like", value, "breaker");
            return (Criteria) this;
        }

        public Criteria andBreakerIn(List<String> values) {
            addCriterion("breaker in", values, "breaker");
            return (Criteria) this;
        }

        public Criteria andBreakerNotIn(List<String> values) {
            addCriterion("breaker not in", values, "breaker");
            return (Criteria) this;
        }

        public Criteria andBreakerBetween(String value1, String value2) {
            addCriterion("breaker between", value1, value2, "breaker");
            return (Criteria) this;
        }

        public Criteria andBreakerNotBetween(String value1, String value2) {
            addCriterion("breaker not between", value1, value2, "breaker");
            return (Criteria) this;
        }

        public Criteria andTrainIsNull() {
            addCriterion("train is null");
            return (Criteria) this;
        }

        public Criteria andTrainIsNotNull() {
            addCriterion("train is not null");
            return (Criteria) this;
        }

        public Criteria andTrainEqualTo(String value) {
            addCriterion("train =", value, "train");
            return (Criteria) this;
        }

        public Criteria andTrainNotEqualTo(String value) {
            addCriterion("train <>", value, "train");
            return (Criteria) this;
        }

        public Criteria andTrainGreaterThan(String value) {
            addCriterion("train >", value, "train");
            return (Criteria) this;
        }

        public Criteria andTrainGreaterThanOrEqualTo(String value) {
            addCriterion("train >=", value, "train");
            return (Criteria) this;
        }

        public Criteria andTrainLessThan(String value) {
            addCriterion("train <", value, "train");
            return (Criteria) this;
        }

        public Criteria andTrainLessThanOrEqualTo(String value) {
            addCriterion("train <=", value, "train");
            return (Criteria) this;
        }

        public Criteria andTrainLike(String value) {
            addCriterion("train like", value, "train");
            return (Criteria) this;
        }

        public Criteria andTrainNotLike(String value) {
            addCriterion("train not like", value, "train");
            return (Criteria) this;
        }

        public Criteria andTrainIn(List<String> values) {
            addCriterion("train in", values, "train");
            return (Criteria) this;
        }

        public Criteria andTrainNotIn(List<String> values) {
            addCriterion("train not in", values, "train");
            return (Criteria) this;
        }

        public Criteria andTrainBetween(String value1, String value2) {
            addCriterion("train between", value1, value2, "train");
            return (Criteria) this;
        }

        public Criteria andTrainNotBetween(String value1, String value2) {
            addCriterion("train not between", value1, value2, "train");
            return (Criteria) this;
        }

        public Criteria andStaffNoIsNull() {
            addCriterion("staff_no is null");
            return (Criteria) this;
        }

        public Criteria andStaffNoIsNotNull() {
            addCriterion("staff_no is not null");
            return (Criteria) this;
        }

        public Criteria andStaffNoEqualTo(String value) {
            addCriterion("staff_no =", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoNotEqualTo(String value) {
            addCriterion("staff_no <>", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoGreaterThan(String value) {
            addCriterion("staff_no >", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoGreaterThanOrEqualTo(String value) {
            addCriterion("staff_no >=", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoLessThan(String value) {
            addCriterion("staff_no <", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoLessThanOrEqualTo(String value) {
            addCriterion("staff_no <=", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoLike(String value) {
            addCriterion("staff_no like", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoNotLike(String value) {
            addCriterion("staff_no not like", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoIn(List<String> values) {
            addCriterion("staff_no in", values, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoNotIn(List<String> values) {
            addCriterion("staff_no not in", values, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoBetween(String value1, String value2) {
            addCriterion("staff_no between", value1, value2, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoNotBetween(String value1, String value2) {
            addCriterion("staff_no not between", value1, value2, "staffNo");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptIsNull() {
            addCriterion("interview_dept is null");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptIsNotNull() {
            addCriterion("interview_dept is not null");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptEqualTo(String value) {
            addCriterion("interview_dept =", value, "interviewDept");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptNotEqualTo(String value) {
            addCriterion("interview_dept <>", value, "interviewDept");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptGreaterThan(String value) {
            addCriterion("interview_dept >", value, "interviewDept");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptGreaterThanOrEqualTo(String value) {
            addCriterion("interview_dept >=", value, "interviewDept");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptLessThan(String value) {
            addCriterion("interview_dept <", value, "interviewDept");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptLessThanOrEqualTo(String value) {
            addCriterion("interview_dept <=", value, "interviewDept");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptLike(String value) {
            addCriterion("interview_dept like", value, "interviewDept");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptNotLike(String value) {
            addCriterion("interview_dept not like", value, "interviewDept");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptIn(List<String> values) {
            addCriterion("interview_dept in", values, "interviewDept");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptNotIn(List<String> values) {
            addCriterion("interview_dept not in", values, "interviewDept");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptBetween(String value1, String value2) {
            addCriterion("interview_dept between", value1, value2, "interviewDept");
            return (Criteria) this;
        }

        public Criteria andInterviewDeptNotBetween(String value1, String value2) {
            addCriterion("interview_dept not between", value1, value2, "interviewDept");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptIsNull() {
            addCriterion("fenpei_dept is null");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptIsNotNull() {
            addCriterion("fenpei_dept is not null");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptEqualTo(String value) {
            addCriterion("fenpei_dept =", value, "fenpeiDept");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptNotEqualTo(String value) {
            addCriterion("fenpei_dept <>", value, "fenpeiDept");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptGreaterThan(String value) {
            addCriterion("fenpei_dept >", value, "fenpeiDept");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptGreaterThanOrEqualTo(String value) {
            addCriterion("fenpei_dept >=", value, "fenpeiDept");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptLessThan(String value) {
            addCriterion("fenpei_dept <", value, "fenpeiDept");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptLessThanOrEqualTo(String value) {
            addCriterion("fenpei_dept <=", value, "fenpeiDept");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptLike(String value) {
            addCriterion("fenpei_dept like", value, "fenpeiDept");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptNotLike(String value) {
            addCriterion("fenpei_dept not like", value, "fenpeiDept");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptIn(List<String> values) {
            addCriterion("fenpei_dept in", values, "fenpeiDept");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptNotIn(List<String> values) {
            addCriterion("fenpei_dept not in", values, "fenpeiDept");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptBetween(String value1, String value2) {
            addCriterion("fenpei_dept between", value1, value2, "fenpeiDept");
            return (Criteria) this;
        }

        public Criteria andFenpeiDeptNotBetween(String value1, String value2) {
            addCriterion("fenpei_dept not between", value1, value2, "fenpeiDept");
            return (Criteria) this;
        }

        public Criteria andYuanDeptIsNull() {
            addCriterion("yuan_dept is null");
            return (Criteria) this;
        }

        public Criteria andYuanDeptIsNotNull() {
            addCriterion("yuan_dept is not null");
            return (Criteria) this;
        }

        public Criteria andYuanDeptEqualTo(String value) {
            addCriterion("yuan_dept =", value, "yuanDept");
            return (Criteria) this;
        }

        public Criteria andYuanDeptNotEqualTo(String value) {
            addCriterion("yuan_dept <>", value, "yuanDept");
            return (Criteria) this;
        }

        public Criteria andYuanDeptGreaterThan(String value) {
            addCriterion("yuan_dept >", value, "yuanDept");
            return (Criteria) this;
        }

        public Criteria andYuanDeptGreaterThanOrEqualTo(String value) {
            addCriterion("yuan_dept >=", value, "yuanDept");
            return (Criteria) this;
        }

        public Criteria andYuanDeptLessThan(String value) {
            addCriterion("yuan_dept <", value, "yuanDept");
            return (Criteria) this;
        }

        public Criteria andYuanDeptLessThanOrEqualTo(String value) {
            addCriterion("yuan_dept <=", value, "yuanDept");
            return (Criteria) this;
        }

        public Criteria andYuanDeptLike(String value) {
            addCriterion("yuan_dept like", value, "yuanDept");
            return (Criteria) this;
        }

        public Criteria andYuanDeptNotLike(String value) {
            addCriterion("yuan_dept not like", value, "yuanDept");
            return (Criteria) this;
        }

        public Criteria andYuanDeptIn(List<String> values) {
            addCriterion("yuan_dept in", values, "yuanDept");
            return (Criteria) this;
        }

        public Criteria andYuanDeptNotIn(List<String> values) {
            addCriterion("yuan_dept not in", values, "yuanDept");
            return (Criteria) this;
        }

        public Criteria andYuanDeptBetween(String value1, String value2) {
            addCriterion("yuan_dept between", value1, value2, "yuanDept");
            return (Criteria) this;
        }

        public Criteria andYuanDeptNotBetween(String value1, String value2) {
            addCriterion("yuan_dept not between", value1, value2, "yuanDept");
            return (Criteria) this;
        }

        public Criteria andOrganizeIsNull() {
            addCriterion("organize is null");
            return (Criteria) this;
        }

        public Criteria andOrganizeIsNotNull() {
            addCriterion("organize is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizeEqualTo(String value) {
            addCriterion("organize =", value, "organize");
            return (Criteria) this;
        }

        public Criteria andOrganizeNotEqualTo(String value) {
            addCriterion("organize <>", value, "organize");
            return (Criteria) this;
        }

        public Criteria andOrganizeGreaterThan(String value) {
            addCriterion("organize >", value, "organize");
            return (Criteria) this;
        }

        public Criteria andOrganizeGreaterThanOrEqualTo(String value) {
            addCriterion("organize >=", value, "organize");
            return (Criteria) this;
        }

        public Criteria andOrganizeLessThan(String value) {
            addCriterion("organize <", value, "organize");
            return (Criteria) this;
        }

        public Criteria andOrganizeLessThanOrEqualTo(String value) {
            addCriterion("organize <=", value, "organize");
            return (Criteria) this;
        }

        public Criteria andOrganizeLike(String value) {
            addCriterion("organize like", value, "organize");
            return (Criteria) this;
        }

        public Criteria andOrganizeNotLike(String value) {
            addCriterion("organize not like", value, "organize");
            return (Criteria) this;
        }

        public Criteria andOrganizeIn(List<String> values) {
            addCriterion("organize in", values, "organize");
            return (Criteria) this;
        }

        public Criteria andOrganizeNotIn(List<String> values) {
            addCriterion("organize not in", values, "organize");
            return (Criteria) this;
        }

        public Criteria andOrganizeBetween(String value1, String value2) {
            addCriterion("organize between", value1, value2, "organize");
            return (Criteria) this;
        }

        public Criteria andOrganizeNotBetween(String value1, String value2) {
            addCriterion("organize not between", value1, value2, "organize");
            return (Criteria) this;
        }

        public Criteria andLeaderIsNull() {
            addCriterion("leader is null");
            return (Criteria) this;
        }

        public Criteria andLeaderIsNotNull() {
            addCriterion("leader is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderEqualTo(String value) {
            addCriterion("leader =", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderNotEqualTo(String value) {
            addCriterion("leader <>", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderGreaterThan(String value) {
            addCriterion("leader >", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderGreaterThanOrEqualTo(String value) {
            addCriterion("leader >=", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderLessThan(String value) {
            addCriterion("leader <", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderLessThanOrEqualTo(String value) {
            addCriterion("leader <=", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderLike(String value) {
            addCriterion("leader like", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderNotLike(String value) {
            addCriterion("leader not like", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderIn(List<String> values) {
            addCriterion("leader in", values, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderNotIn(List<String> values) {
            addCriterion("leader not in", values, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderBetween(String value1, String value2) {
            addCriterion("leader between", value1, value2, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderNotBetween(String value1, String value2) {
            addCriterion("leader not between", value1, value2, "leader");
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

        public Criteria andOneResultEqualTo(String value) {
            addCriterion("one_result =", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultNotEqualTo(String value) {
            addCriterion("one_result <>", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultGreaterThan(String value) {
            addCriterion("one_result >", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultGreaterThanOrEqualTo(String value) {
            addCriterion("one_result >=", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultLessThan(String value) {
            addCriterion("one_result <", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultLessThanOrEqualTo(String value) {
            addCriterion("one_result <=", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultLike(String value) {
            addCriterion("one_result like", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultNotLike(String value) {
            addCriterion("one_result not like", value, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultIn(List<String> values) {
            addCriterion("one_result in", values, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultNotIn(List<String> values) {
            addCriterion("one_result not in", values, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultBetween(String value1, String value2) {
            addCriterion("one_result between", value1, value2, "oneResult");
            return (Criteria) this;
        }

        public Criteria andOneResultNotBetween(String value1, String value2) {
            addCriterion("one_result not between", value1, value2, "oneResult");
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

        public Criteria andTwoResultEqualTo(String value) {
            addCriterion("two_result =", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultNotEqualTo(String value) {
            addCriterion("two_result <>", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultGreaterThan(String value) {
            addCriterion("two_result >", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultGreaterThanOrEqualTo(String value) {
            addCriterion("two_result >=", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultLessThan(String value) {
            addCriterion("two_result <", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultLessThanOrEqualTo(String value) {
            addCriterion("two_result <=", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultLike(String value) {
            addCriterion("two_result like", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultNotLike(String value) {
            addCriterion("two_result not like", value, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultIn(List<String> values) {
            addCriterion("two_result in", values, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultNotIn(List<String> values) {
            addCriterion("two_result not in", values, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultBetween(String value1, String value2) {
            addCriterion("two_result between", value1, value2, "twoResult");
            return (Criteria) this;
        }

        public Criteria andTwoResultNotBetween(String value1, String value2) {
            addCriterion("two_result not between", value1, value2, "twoResult");
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