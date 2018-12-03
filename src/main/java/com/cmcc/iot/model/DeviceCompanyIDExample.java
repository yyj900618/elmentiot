package com.cmcc.iot.model;

import java.util.ArrayList;
import java.util.List;

public class DeviceCompanyIDExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceCompanyIDExample() {
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

        public Criteria andDeviceidIsNull() {
            addCriterion("DEVICEID is null");
            return (Criteria) this;
        }

        public Criteria andDeviceidIsNotNull() {
            addCriterion("DEVICEID is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceidEqualTo(Integer value) {
            addCriterion("DEVICEID =", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotEqualTo(Integer value) {
            addCriterion("DEVICEID <>", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThan(Integer value) {
            addCriterion("DEVICEID >", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThanOrEqualTo(Integer value) {
            addCriterion("DEVICEID >=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThan(Integer value) {
            addCriterion("DEVICEID <", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThanOrEqualTo(Integer value) {
            addCriterion("DEVICEID <=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidIn(List<Integer> values) {
            addCriterion("DEVICEID in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotIn(List<Integer> values) {
            addCriterion("DEVICEID not in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidBetween(Integer value1, Integer value2) {
            addCriterion("DEVICEID between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotBetween(Integer value1, Integer value2) {
            addCriterion("DEVICEID not between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andTitelIsNull() {
            addCriterion("TITEL is null");
            return (Criteria) this;
        }

        public Criteria andTitelIsNotNull() {
            addCriterion("TITEL is not null");
            return (Criteria) this;
        }

        public Criteria andTitelEqualTo(String value) {
            addCriterion("TITEL =", value, "titel");
            return (Criteria) this;
        }

        public Criteria andTitelNotEqualTo(String value) {
            addCriterion("TITEL <>", value, "titel");
            return (Criteria) this;
        }

        public Criteria andTitelGreaterThan(String value) {
            addCriterion("TITEL >", value, "titel");
            return (Criteria) this;
        }

        public Criteria andTitelGreaterThanOrEqualTo(String value) {
            addCriterion("TITEL >=", value, "titel");
            return (Criteria) this;
        }

        public Criteria andTitelLessThan(String value) {
            addCriterion("TITEL <", value, "titel");
            return (Criteria) this;
        }

        public Criteria andTitelLessThanOrEqualTo(String value) {
            addCriterion("TITEL <=", value, "titel");
            return (Criteria) this;
        }

        public Criteria andTitelLike(String value) {
            addCriterion("TITEL like", value, "titel");
            return (Criteria) this;
        }

        public Criteria andTitelNotLike(String value) {
            addCriterion("TITEL not like", value, "titel");
            return (Criteria) this;
        }

        public Criteria andTitelIn(List<String> values) {
            addCriterion("TITEL in", values, "titel");
            return (Criteria) this;
        }

        public Criteria andTitelNotIn(List<String> values) {
            addCriterion("TITEL not in", values, "titel");
            return (Criteria) this;
        }

        public Criteria andTitelBetween(String value1, String value2) {
            addCriterion("TITEL between", value1, value2, "titel");
            return (Criteria) this;
        }

        public Criteria andTitelNotBetween(String value1, String value2) {
            addCriterion("TITEL not between", value1, value2, "titel");
            return (Criteria) this;
        }

        public Criteria andCompanyidIsNull() {
            addCriterion("COMPANYID is null");
            return (Criteria) this;
        }

        public Criteria andCompanyidIsNotNull() {
            addCriterion("COMPANYID is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyidEqualTo(Integer value) {
            addCriterion("COMPANYID =", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotEqualTo(Integer value) {
            addCriterion("COMPANYID <>", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThan(Integer value) {
            addCriterion("COMPANYID >", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThanOrEqualTo(Integer value) {
            addCriterion("COMPANYID >=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThan(Integer value) {
            addCriterion("COMPANYID <", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThanOrEqualTo(Integer value) {
            addCriterion("COMPANYID <=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidIn(List<Integer> values) {
            addCriterion("COMPANYID in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotIn(List<Integer> values) {
            addCriterion("COMPANYID not in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidBetween(Integer value1, Integer value2) {
            addCriterion("COMPANYID between", value1, value2, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotBetween(Integer value1, Integer value2) {
            addCriterion("COMPANYID not between", value1, value2, "companyid");
            return (Criteria) this;
        }

        public Criteria andApikeyIsNull() {
            addCriterion("APIKEY is null");
            return (Criteria) this;
        }

        public Criteria andApikeyIsNotNull() {
            addCriterion("APIKEY is not null");
            return (Criteria) this;
        }

        public Criteria andApikeyEqualTo(String value) {
            addCriterion("APIKEY =", value, "apikey");
            return (Criteria) this;
        }

        public Criteria andApikeyNotEqualTo(String value) {
            addCriterion("APIKEY <>", value, "apikey");
            return (Criteria) this;
        }

        public Criteria andApikeyGreaterThan(String value) {
            addCriterion("APIKEY >", value, "apikey");
            return (Criteria) this;
        }

        public Criteria andApikeyGreaterThanOrEqualTo(String value) {
            addCriterion("APIKEY >=", value, "apikey");
            return (Criteria) this;
        }

        public Criteria andApikeyLessThan(String value) {
            addCriterion("APIKEY <", value, "apikey");
            return (Criteria) this;
        }

        public Criteria andApikeyLessThanOrEqualTo(String value) {
            addCriterion("APIKEY <=", value, "apikey");
            return (Criteria) this;
        }

        public Criteria andApikeyLike(String value) {
            addCriterion("APIKEY like", value, "apikey");
            return (Criteria) this;
        }

        public Criteria andApikeyNotLike(String value) {
            addCriterion("APIKEY not like", value, "apikey");
            return (Criteria) this;
        }

        public Criteria andApikeyIn(List<String> values) {
            addCriterion("APIKEY in", values, "apikey");
            return (Criteria) this;
        }

        public Criteria andApikeyNotIn(List<String> values) {
            addCriterion("APIKEY not in", values, "apikey");
            return (Criteria) this;
        }

        public Criteria andApikeyBetween(String value1, String value2) {
            addCriterion("APIKEY between", value1, value2, "apikey");
            return (Criteria) this;
        }

        public Criteria andApikeyNotBetween(String value1, String value2) {
            addCriterion("APIKEY not between", value1, value2, "apikey");
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