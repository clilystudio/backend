package com.wistronits.wistlotto.model.tables;

import java.util.ArrayList;
import java.util.List;

public class TSysInfoCriteria {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	public TSysInfoCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_sys_info
	 * 
	 * @mbg.generated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the
	 * database table t_sys_info
	 * 
	 * @mbg.generated
	 */
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

		public Criteria andSysKeyIsNull() {
			addCriterion("sys_key is null");
			return (Criteria) this;
		}

		public Criteria andSysKeyIsNotNull() {
			addCriterion("sys_key is not null");
			return (Criteria) this;
		}

		public Criteria andSysKeyEqualTo(String value) {
			addCriterion("sys_key =", value, "sysKey");
			return (Criteria) this;
		}

		public Criteria andSysKeyNotEqualTo(String value) {
			addCriterion("sys_key <>", value, "sysKey");
			return (Criteria) this;
		}

		public Criteria andSysKeyGreaterThan(String value) {
			addCriterion("sys_key >", value, "sysKey");
			return (Criteria) this;
		}

		public Criteria andSysKeyGreaterThanOrEqualTo(String value) {
			addCriterion("sys_key >=", value, "sysKey");
			return (Criteria) this;
		}

		public Criteria andSysKeyLessThan(String value) {
			addCriterion("sys_key <", value, "sysKey");
			return (Criteria) this;
		}

		public Criteria andSysKeyLessThanOrEqualTo(String value) {
			addCriterion("sys_key <=", value, "sysKey");
			return (Criteria) this;
		}

		public Criteria andSysKeyLike(String value) {
			addCriterion("sys_key like", value, "sysKey");
			return (Criteria) this;
		}

		public Criteria andSysKeyNotLike(String value) {
			addCriterion("sys_key not like", value, "sysKey");
			return (Criteria) this;
		}

		public Criteria andSysKeyIn(List<String> values) {
			addCriterion("sys_key in", values, "sysKey");
			return (Criteria) this;
		}

		public Criteria andSysKeyNotIn(List<String> values) {
			addCriterion("sys_key not in", values, "sysKey");
			return (Criteria) this;
		}

		public Criteria andSysKeyBetween(String value1, String value2) {
			addCriterion("sys_key between", value1, value2, "sysKey");
			return (Criteria) this;
		}

		public Criteria andSysKeyNotBetween(String value1, String value2) {
			addCriterion("sys_key not between", value1, value2, "sysKey");
			return (Criteria) this;
		}

		public Criteria andSysValueIsNull() {
			addCriterion("sys_value is null");
			return (Criteria) this;
		}

		public Criteria andSysValueIsNotNull() {
			addCriterion("sys_value is not null");
			return (Criteria) this;
		}

		public Criteria andSysValueEqualTo(String value) {
			addCriterion("sys_value =", value, "sysValue");
			return (Criteria) this;
		}

		public Criteria andSysValueNotEqualTo(String value) {
			addCriterion("sys_value <>", value, "sysValue");
			return (Criteria) this;
		}

		public Criteria andSysValueGreaterThan(String value) {
			addCriterion("sys_value >", value, "sysValue");
			return (Criteria) this;
		}

		public Criteria andSysValueGreaterThanOrEqualTo(String value) {
			addCriterion("sys_value >=", value, "sysValue");
			return (Criteria) this;
		}

		public Criteria andSysValueLessThan(String value) {
			addCriterion("sys_value <", value, "sysValue");
			return (Criteria) this;
		}

		public Criteria andSysValueLessThanOrEqualTo(String value) {
			addCriterion("sys_value <=", value, "sysValue");
			return (Criteria) this;
		}

		public Criteria andSysValueLike(String value) {
			addCriterion("sys_value like", value, "sysValue");
			return (Criteria) this;
		}

		public Criteria andSysValueNotLike(String value) {
			addCriterion("sys_value not like", value, "sysValue");
			return (Criteria) this;
		}

		public Criteria andSysValueIn(List<String> values) {
			addCriterion("sys_value in", values, "sysValue");
			return (Criteria) this;
		}

		public Criteria andSysValueNotIn(List<String> values) {
			addCriterion("sys_value not in", values, "sysValue");
			return (Criteria) this;
		}

		public Criteria andSysValueBetween(String value1, String value2) {
			addCriterion("sys_value between", value1, value2, "sysValue");
			return (Criteria) this;
		}

		public Criteria andSysValueNotBetween(String value1, String value2) {
			addCriterion("sys_value not between", value1, value2, "sysValue");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the
	 * database table t_sys_info
	 * 
	 * @mbg.generated
	 */
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

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the
	 * database table t_sys_info
	 *
	 * @mbg.generated do_not_delete_during_merge
	 */
	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}
}