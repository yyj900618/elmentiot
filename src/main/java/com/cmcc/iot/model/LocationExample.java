package com.cmcc.iot.model;

import java.util.ArrayList;
import java.util.List;

public class LocationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LocationExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Double value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Double value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Double value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Double value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Double value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Double value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Double> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Double> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Double value1, Double value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Double value1, Double value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLocationnameIsNull() {
            addCriterion("LOCATIONNAME is null");
            return (Criteria) this;
        }

        public Criteria andLocationnameIsNotNull() {
            addCriterion("LOCATIONNAME is not null");
            return (Criteria) this;
        }

        public Criteria andLocationnameEqualTo(String value) {
            addCriterion("LOCATIONNAME =", value, "locationname");
            return (Criteria) this;
        }

        public Criteria andLocationnameNotEqualTo(String value) {
            addCriterion("LOCATIONNAME <>", value, "locationname");
            return (Criteria) this;
        }

        public Criteria andLocationnameGreaterThan(String value) {
            addCriterion("LOCATIONNAME >", value, "locationname");
            return (Criteria) this;
        }

        public Criteria andLocationnameGreaterThanOrEqualTo(String value) {
            addCriterion("LOCATIONNAME >=", value, "locationname");
            return (Criteria) this;
        }

        public Criteria andLocationnameLessThan(String value) {
            addCriterion("LOCATIONNAME <", value, "locationname");
            return (Criteria) this;
        }

        public Criteria andLocationnameLessThanOrEqualTo(String value) {
            addCriterion("LOCATIONNAME <=", value, "locationname");
            return (Criteria) this;
        }

        public Criteria andLocationnameLike(String value) {
            addCriterion("LOCATIONNAME like", value, "locationname");
            return (Criteria) this;
        }

        public Criteria andLocationnameNotLike(String value) {
            addCriterion("LOCATIONNAME not like", value, "locationname");
            return (Criteria) this;
        }

        public Criteria andLocationnameIn(List<String> values) {
            addCriterion("LOCATIONNAME in", values, "locationname");
            return (Criteria) this;
        }

        public Criteria andLocationnameNotIn(List<String> values) {
            addCriterion("LOCATIONNAME not in", values, "locationname");
            return (Criteria) this;
        }

        public Criteria andLocationnameBetween(String value1, String value2) {
            addCriterion("LOCATIONNAME between", value1, value2, "locationname");
            return (Criteria) this;
        }

        public Criteria andLocationnameNotBetween(String value1, String value2) {
            addCriterion("LOCATIONNAME not between", value1, value2, "locationname");
            return (Criteria) this;
        }

        public Criteria andLocationcodeIsNull() {
            addCriterion("LOCATIONCODE is null");
            return (Criteria) this;
        }

        public Criteria andLocationcodeIsNotNull() {
            addCriterion("LOCATIONCODE is not null");
            return (Criteria) this;
        }

        public Criteria andLocationcodeEqualTo(String value) {
            addCriterion("LOCATIONCODE =", value, "locationcode");
            return (Criteria) this;
        }

        public Criteria andLocationcodeNotEqualTo(String value) {
            addCriterion("LOCATIONCODE <>", value, "locationcode");
            return (Criteria) this;
        }

        public Criteria andLocationcodeGreaterThan(String value) {
            addCriterion("LOCATIONCODE >", value, "locationcode");
            return (Criteria) this;
        }

        public Criteria andLocationcodeGreaterThanOrEqualTo(String value) {
            addCriterion("LOCATIONCODE >=", value, "locationcode");
            return (Criteria) this;
        }

        public Criteria andLocationcodeLessThan(String value) {
            addCriterion("LOCATIONCODE <", value, "locationcode");
            return (Criteria) this;
        }

        public Criteria andLocationcodeLessThanOrEqualTo(String value) {
            addCriterion("LOCATIONCODE <=", value, "locationcode");
            return (Criteria) this;
        }

        public Criteria andLocationcodeLike(String value) {
            addCriterion("LOCATIONCODE like", value, "locationcode");
            return (Criteria) this;
        }

        public Criteria andLocationcodeNotLike(String value) {
            addCriterion("LOCATIONCODE not like", value, "locationcode");
            return (Criteria) this;
        }

        public Criteria andLocationcodeIn(List<String> values) {
            addCriterion("LOCATIONCODE in", values, "locationcode");
            return (Criteria) this;
        }

        public Criteria andLocationcodeNotIn(List<String> values) {
            addCriterion("LOCATIONCODE not in", values, "locationcode");
            return (Criteria) this;
        }

        public Criteria andLocationcodeBetween(String value1, String value2) {
            addCriterion("LOCATIONCODE between", value1, value2, "locationcode");
            return (Criteria) this;
        }

        public Criteria andLocationcodeNotBetween(String value1, String value2) {
            addCriterion("LOCATIONCODE not between", value1, value2, "locationcode");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeIsNull() {
            addCriterion("LASTLEVELCODE is null");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeIsNotNull() {
            addCriterion("LASTLEVELCODE is not null");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeEqualTo(String value) {
            addCriterion("LASTLEVELCODE =", value, "lastlevelcode");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeNotEqualTo(String value) {
            addCriterion("LASTLEVELCODE <>", value, "lastlevelcode");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeGreaterThan(String value) {
            addCriterion("LASTLEVELCODE >", value, "lastlevelcode");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeGreaterThanOrEqualTo(String value) {
            addCriterion("LASTLEVELCODE >=", value, "lastlevelcode");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeLessThan(String value) {
            addCriterion("LASTLEVELCODE <", value, "lastlevelcode");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeLessThanOrEqualTo(String value) {
            addCriterion("LASTLEVELCODE <=", value, "lastlevelcode");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeLike(String value) {
            addCriterion("LASTLEVELCODE like", value, "lastlevelcode");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeNotLike(String value) {
            addCriterion("LASTLEVELCODE not like", value, "lastlevelcode");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeIn(List<String> values) {
            addCriterion("LASTLEVELCODE in", values, "lastlevelcode");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeNotIn(List<String> values) {
            addCriterion("LASTLEVELCODE not in", values, "lastlevelcode");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeBetween(String value1, String value2) {
            addCriterion("LASTLEVELCODE between", value1, value2, "lastlevelcode");
            return (Criteria) this;
        }

        public Criteria andLastlevelcodeNotBetween(String value1, String value2) {
            addCriterion("LASTLEVELCODE not between", value1, value2, "lastlevelcode");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("PARENTID is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("PARENTID is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(Double value) {
            addCriterion("PARENTID =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(Double value) {
            addCriterion("PARENTID <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(Double value) {
            addCriterion("PARENTID >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(Double value) {
            addCriterion("PARENTID >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(Double value) {
            addCriterion("PARENTID <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(Double value) {
            addCriterion("PARENTID <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<Double> values) {
            addCriterion("PARENTID in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<Double> values) {
            addCriterion("PARENTID not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(Double value1, Double value2) {
            addCriterion("PARENTID between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(Double value1, Double value2) {
            addCriterion("PARENTID not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andLocationlevelIsNull() {
            addCriterion("LOCATIONLEVEL is null");
            return (Criteria) this;
        }

        public Criteria andLocationlevelIsNotNull() {
            addCriterion("LOCATIONLEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andLocationlevelEqualTo(Double value) {
            addCriterion("LOCATIONLEVEL =", value, "locationlevel");
            return (Criteria) this;
        }

        public Criteria andLocationlevelNotEqualTo(Double value) {
            addCriterion("LOCATIONLEVEL <>", value, "locationlevel");
            return (Criteria) this;
        }

        public Criteria andLocationlevelGreaterThan(Double value) {
            addCriterion("LOCATIONLEVEL >", value, "locationlevel");
            return (Criteria) this;
        }

        public Criteria andLocationlevelGreaterThanOrEqualTo(Double value) {
            addCriterion("LOCATIONLEVEL >=", value, "locationlevel");
            return (Criteria) this;
        }

        public Criteria andLocationlevelLessThan(Double value) {
            addCriterion("LOCATIONLEVEL <", value, "locationlevel");
            return (Criteria) this;
        }

        public Criteria andLocationlevelLessThanOrEqualTo(Double value) {
            addCriterion("LOCATIONLEVEL <=", value, "locationlevel");
            return (Criteria) this;
        }

        public Criteria andLocationlevelIn(List<Double> values) {
            addCriterion("LOCATIONLEVEL in", values, "locationlevel");
            return (Criteria) this;
        }

        public Criteria andLocationlevelNotIn(List<Double> values) {
            addCriterion("LOCATIONLEVEL not in", values, "locationlevel");
            return (Criteria) this;
        }

        public Criteria andLocationlevelBetween(Double value1, Double value2) {
            addCriterion("LOCATIONLEVEL between", value1, value2, "locationlevel");
            return (Criteria) this;
        }

        public Criteria andLocationlevelNotBetween(Double value1, Double value2) {
            addCriterion("LOCATIONLEVEL not between", value1, value2, "locationlevel");
            return (Criteria) this;
        }

        public Criteria andOrderindexIsNull() {
            addCriterion("ORDERINDEX is null");
            return (Criteria) this;
        }

        public Criteria andOrderindexIsNotNull() {
            addCriterion("ORDERINDEX is not null");
            return (Criteria) this;
        }

        public Criteria andOrderindexEqualTo(String value) {
            addCriterion("ORDERINDEX =", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexNotEqualTo(String value) {
            addCriterion("ORDERINDEX <>", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexGreaterThan(String value) {
            addCriterion("ORDERINDEX >", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexGreaterThanOrEqualTo(String value) {
            addCriterion("ORDERINDEX >=", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexLessThan(String value) {
            addCriterion("ORDERINDEX <", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexLessThanOrEqualTo(String value) {
            addCriterion("ORDERINDEX <=", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexLike(String value) {
            addCriterion("ORDERINDEX like", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexNotLike(String value) {
            addCriterion("ORDERINDEX not like", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexIn(List<String> values) {
            addCriterion("ORDERINDEX in", values, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexNotIn(List<String> values) {
            addCriterion("ORDERINDEX not in", values, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexBetween(String value1, String value2) {
            addCriterion("ORDERINDEX between", value1, value2, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexNotBetween(String value1, String value2) {
            addCriterion("ORDERINDEX not between", value1, value2, "orderindex");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
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