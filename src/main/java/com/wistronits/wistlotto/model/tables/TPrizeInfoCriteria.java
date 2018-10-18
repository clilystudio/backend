package com.wistronits.wistlotto.model.tables;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TPrizeInfoCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	public TPrizeInfoCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_prize_info
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_prize_info
	 * @mbg.generated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_prize_info
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

		public Criteria andPrizeIdIsNull() {
			addCriterion("prize_id is null");
			return (Criteria) this;
		}

		public Criteria andPrizeIdIsNotNull() {
			addCriterion("prize_id is not null");
			return (Criteria) this;
		}

		public Criteria andPrizeIdEqualTo(String value) {
			addCriterion("prize_id =", value, "prizeId");
			return (Criteria) this;
		}

		public Criteria andPrizeIdNotEqualTo(String value) {
			addCriterion("prize_id <>", value, "prizeId");
			return (Criteria) this;
		}

		public Criteria andPrizeIdGreaterThan(String value) {
			addCriterion("prize_id >", value, "prizeId");
			return (Criteria) this;
		}

		public Criteria andPrizeIdGreaterThanOrEqualTo(String value) {
			addCriterion("prize_id >=", value, "prizeId");
			return (Criteria) this;
		}

		public Criteria andPrizeIdLessThan(String value) {
			addCriterion("prize_id <", value, "prizeId");
			return (Criteria) this;
		}

		public Criteria andPrizeIdLessThanOrEqualTo(String value) {
			addCriterion("prize_id <=", value, "prizeId");
			return (Criteria) this;
		}

		public Criteria andPrizeIdLike(String value) {
			addCriterion("prize_id like", value, "prizeId");
			return (Criteria) this;
		}

		public Criteria andPrizeIdNotLike(String value) {
			addCriterion("prize_id not like", value, "prizeId");
			return (Criteria) this;
		}

		public Criteria andPrizeIdIn(List<String> values) {
			addCriterion("prize_id in", values, "prizeId");
			return (Criteria) this;
		}

		public Criteria andPrizeIdNotIn(List<String> values) {
			addCriterion("prize_id not in", values, "prizeId");
			return (Criteria) this;
		}

		public Criteria andPrizeIdBetween(String value1, String value2) {
			addCriterion("prize_id between", value1, value2, "prizeId");
			return (Criteria) this;
		}

		public Criteria andPrizeIdNotBetween(String value1, String value2) {
			addCriterion("prize_id not between", value1, value2, "prizeId");
			return (Criteria) this;
		}

		public Criteria andPrizeNameIsNull() {
			addCriterion("prize_name is null");
			return (Criteria) this;
		}

		public Criteria andPrizeNameIsNotNull() {
			addCriterion("prize_name is not null");
			return (Criteria) this;
		}

		public Criteria andPrizeNameEqualTo(String value) {
			addCriterion("prize_name =", value, "prizeName");
			return (Criteria) this;
		}

		public Criteria andPrizeNameNotEqualTo(String value) {
			addCriterion("prize_name <>", value, "prizeName");
			return (Criteria) this;
		}

		public Criteria andPrizeNameGreaterThan(String value) {
			addCriterion("prize_name >", value, "prizeName");
			return (Criteria) this;
		}

		public Criteria andPrizeNameGreaterThanOrEqualTo(String value) {
			addCriterion("prize_name >=", value, "prizeName");
			return (Criteria) this;
		}

		public Criteria andPrizeNameLessThan(String value) {
			addCriterion("prize_name <", value, "prizeName");
			return (Criteria) this;
		}

		public Criteria andPrizeNameLessThanOrEqualTo(String value) {
			addCriterion("prize_name <=", value, "prizeName");
			return (Criteria) this;
		}

		public Criteria andPrizeNameLike(String value) {
			addCriterion("prize_name like", value, "prizeName");
			return (Criteria) this;
		}

		public Criteria andPrizeNameNotLike(String value) {
			addCriterion("prize_name not like", value, "prizeName");
			return (Criteria) this;
		}

		public Criteria andPrizeNameIn(List<String> values) {
			addCriterion("prize_name in", values, "prizeName");
			return (Criteria) this;
		}

		public Criteria andPrizeNameNotIn(List<String> values) {
			addCriterion("prize_name not in", values, "prizeName");
			return (Criteria) this;
		}

		public Criteria andPrizeNameBetween(String value1, String value2) {
			addCriterion("prize_name between", value1, value2, "prizeName");
			return (Criteria) this;
		}

		public Criteria andPrizeNameNotBetween(String value1, String value2) {
			addCriterion("prize_name not between", value1, value2, "prizeName");
			return (Criteria) this;
		}

		public Criteria andPrizeDescIsNull() {
			addCriterion("prize_desc is null");
			return (Criteria) this;
		}

		public Criteria andPrizeDescIsNotNull() {
			addCriterion("prize_desc is not null");
			return (Criteria) this;
		}

		public Criteria andPrizeDescEqualTo(String value) {
			addCriterion("prize_desc =", value, "prizeDesc");
			return (Criteria) this;
		}

		public Criteria andPrizeDescNotEqualTo(String value) {
			addCriterion("prize_desc <>", value, "prizeDesc");
			return (Criteria) this;
		}

		public Criteria andPrizeDescGreaterThan(String value) {
			addCriterion("prize_desc >", value, "prizeDesc");
			return (Criteria) this;
		}

		public Criteria andPrizeDescGreaterThanOrEqualTo(String value) {
			addCriterion("prize_desc >=", value, "prizeDesc");
			return (Criteria) this;
		}

		public Criteria andPrizeDescLessThan(String value) {
			addCriterion("prize_desc <", value, "prizeDesc");
			return (Criteria) this;
		}

		public Criteria andPrizeDescLessThanOrEqualTo(String value) {
			addCriterion("prize_desc <=", value, "prizeDesc");
			return (Criteria) this;
		}

		public Criteria andPrizeDescLike(String value) {
			addCriterion("prize_desc like", value, "prizeDesc");
			return (Criteria) this;
		}

		public Criteria andPrizeDescNotLike(String value) {
			addCriterion("prize_desc not like", value, "prizeDesc");
			return (Criteria) this;
		}

		public Criteria andPrizeDescIn(List<String> values) {
			addCriterion("prize_desc in", values, "prizeDesc");
			return (Criteria) this;
		}

		public Criteria andPrizeDescNotIn(List<String> values) {
			addCriterion("prize_desc not in", values, "prizeDesc");
			return (Criteria) this;
		}

		public Criteria andPrizeDescBetween(String value1, String value2) {
			addCriterion("prize_desc between", value1, value2, "prizeDesc");
			return (Criteria) this;
		}

		public Criteria andPrizeDescNotBetween(String value1, String value2) {
			addCriterion("prize_desc not between", value1, value2, "prizeDesc");
			return (Criteria) this;
		}

		public Criteria andPrizeOrderIsNull() {
			addCriterion("prize_order is null");
			return (Criteria) this;
		}

		public Criteria andPrizeOrderIsNotNull() {
			addCriterion("prize_order is not null");
			return (Criteria) this;
		}

		public Criteria andPrizeOrderEqualTo(BigDecimal value) {
			addCriterion("prize_order =", value, "prizeOrder");
			return (Criteria) this;
		}

		public Criteria andPrizeOrderNotEqualTo(BigDecimal value) {
			addCriterion("prize_order <>", value, "prizeOrder");
			return (Criteria) this;
		}

		public Criteria andPrizeOrderGreaterThan(BigDecimal value) {
			addCriterion("prize_order >", value, "prizeOrder");
			return (Criteria) this;
		}

		public Criteria andPrizeOrderGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("prize_order >=", value, "prizeOrder");
			return (Criteria) this;
		}

		public Criteria andPrizeOrderLessThan(BigDecimal value) {
			addCriterion("prize_order <", value, "prizeOrder");
			return (Criteria) this;
		}

		public Criteria andPrizeOrderLessThanOrEqualTo(BigDecimal value) {
			addCriterion("prize_order <=", value, "prizeOrder");
			return (Criteria) this;
		}

		public Criteria andPrizeOrderIn(List<BigDecimal> values) {
			addCriterion("prize_order in", values, "prizeOrder");
			return (Criteria) this;
		}

		public Criteria andPrizeOrderNotIn(List<BigDecimal> values) {
			addCriterion("prize_order not in", values, "prizeOrder");
			return (Criteria) this;
		}

		public Criteria andPrizeOrderBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("prize_order between", value1, value2, "prizeOrder");
			return (Criteria) this;
		}

		public Criteria andPrizeOrderNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("prize_order not between", value1, value2, "prizeOrder");
			return (Criteria) this;
		}

		public Criteria andPrizeNumberIsNull() {
			addCriterion("prize_number is null");
			return (Criteria) this;
		}

		public Criteria andPrizeNumberIsNotNull() {
			addCriterion("prize_number is not null");
			return (Criteria) this;
		}

		public Criteria andPrizeNumberEqualTo(BigDecimal value) {
			addCriterion("prize_number =", value, "prizeNumber");
			return (Criteria) this;
		}

		public Criteria andPrizeNumberNotEqualTo(BigDecimal value) {
			addCriterion("prize_number <>", value, "prizeNumber");
			return (Criteria) this;
		}

		public Criteria andPrizeNumberGreaterThan(BigDecimal value) {
			addCriterion("prize_number >", value, "prizeNumber");
			return (Criteria) this;
		}

		public Criteria andPrizeNumberGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("prize_number >=", value, "prizeNumber");
			return (Criteria) this;
		}

		public Criteria andPrizeNumberLessThan(BigDecimal value) {
			addCriterion("prize_number <", value, "prizeNumber");
			return (Criteria) this;
		}

		public Criteria andPrizeNumberLessThanOrEqualTo(BigDecimal value) {
			addCriterion("prize_number <=", value, "prizeNumber");
			return (Criteria) this;
		}

		public Criteria andPrizeNumberIn(List<BigDecimal> values) {
			addCriterion("prize_number in", values, "prizeNumber");
			return (Criteria) this;
		}

		public Criteria andPrizeNumberNotIn(List<BigDecimal> values) {
			addCriterion("prize_number not in", values, "prizeNumber");
			return (Criteria) this;
		}

		public Criteria andPrizeNumberBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("prize_number between", value1, value2, "prizeNumber");
			return (Criteria) this;
		}

		public Criteria andPrizeNumberNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("prize_number not between", value1, value2, "prizeNumber");
			return (Criteria) this;
		}

		public Criteria andPrizeWinnerIsNull() {
			addCriterion("prize_winner is null");
			return (Criteria) this;
		}

		public Criteria andPrizeWinnerIsNotNull() {
			addCriterion("prize_winner is not null");
			return (Criteria) this;
		}

		public Criteria andPrizeWinnerEqualTo(BigDecimal value) {
			addCriterion("prize_winner =", value, "prizeWinner");
			return (Criteria) this;
		}

		public Criteria andPrizeWinnerNotEqualTo(BigDecimal value) {
			addCriterion("prize_winner <>", value, "prizeWinner");
			return (Criteria) this;
		}

		public Criteria andPrizeWinnerGreaterThan(BigDecimal value) {
			addCriterion("prize_winner >", value, "prizeWinner");
			return (Criteria) this;
		}

		public Criteria andPrizeWinnerGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("prize_winner >=", value, "prizeWinner");
			return (Criteria) this;
		}

		public Criteria andPrizeWinnerLessThan(BigDecimal value) {
			addCriterion("prize_winner <", value, "prizeWinner");
			return (Criteria) this;
		}

		public Criteria andPrizeWinnerLessThanOrEqualTo(BigDecimal value) {
			addCriterion("prize_winner <=", value, "prizeWinner");
			return (Criteria) this;
		}

		public Criteria andPrizeWinnerIn(List<BigDecimal> values) {
			addCriterion("prize_winner in", values, "prizeWinner");
			return (Criteria) this;
		}

		public Criteria andPrizeWinnerNotIn(List<BigDecimal> values) {
			addCriterion("prize_winner not in", values, "prizeWinner");
			return (Criteria) this;
		}

		public Criteria andPrizeWinnerBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("prize_winner between", value1, value2, "prizeWinner");
			return (Criteria) this;
		}

		public Criteria andPrizeWinnerNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("prize_winner not between", value1, value2, "prizeWinner");
			return (Criteria) this;
		}

		public Criteria andDeptIdIsNull() {
			addCriterion("dept_id is null");
			return (Criteria) this;
		}

		public Criteria andDeptIdIsNotNull() {
			addCriterion("dept_id is not null");
			return (Criteria) this;
		}

		public Criteria andDeptIdEqualTo(String value) {
			addCriterion("dept_id =", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdNotEqualTo(String value) {
			addCriterion("dept_id <>", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdGreaterThan(String value) {
			addCriterion("dept_id >", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdGreaterThanOrEqualTo(String value) {
			addCriterion("dept_id >=", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdLessThan(String value) {
			addCriterion("dept_id <", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdLessThanOrEqualTo(String value) {
			addCriterion("dept_id <=", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdLike(String value) {
			addCriterion("dept_id like", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdNotLike(String value) {
			addCriterion("dept_id not like", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdIn(List<String> values) {
			addCriterion("dept_id in", values, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdNotIn(List<String> values) {
			addCriterion("dept_id not in", values, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdBetween(String value1, String value2) {
			addCriterion("dept_id between", value1, value2, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdNotBetween(String value1, String value2) {
			addCriterion("dept_id not between", value1, value2, "deptId");
			return (Criteria) this;
		}

		public Criteria andPrizeMultiIsNull() {
			addCriterion("prize_multi is null");
			return (Criteria) this;
		}

		public Criteria andPrizeMultiIsNotNull() {
			addCriterion("prize_multi is not null");
			return (Criteria) this;
		}

		public Criteria andPrizeMultiEqualTo(BigDecimal value) {
			addCriterion("prize_multi =", value, "prizeMulti");
			return (Criteria) this;
		}

		public Criteria andPrizeMultiNotEqualTo(BigDecimal value) {
			addCriterion("prize_multi <>", value, "prizeMulti");
			return (Criteria) this;
		}

		public Criteria andPrizeMultiGreaterThan(BigDecimal value) {
			addCriterion("prize_multi >", value, "prizeMulti");
			return (Criteria) this;
		}

		public Criteria andPrizeMultiGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("prize_multi >=", value, "prizeMulti");
			return (Criteria) this;
		}

		public Criteria andPrizeMultiLessThan(BigDecimal value) {
			addCriterion("prize_multi <", value, "prizeMulti");
			return (Criteria) this;
		}

		public Criteria andPrizeMultiLessThanOrEqualTo(BigDecimal value) {
			addCriterion("prize_multi <=", value, "prizeMulti");
			return (Criteria) this;
		}

		public Criteria andPrizeMultiIn(List<BigDecimal> values) {
			addCriterion("prize_multi in", values, "prizeMulti");
			return (Criteria) this;
		}

		public Criteria andPrizeMultiNotIn(List<BigDecimal> values) {
			addCriterion("prize_multi not in", values, "prizeMulti");
			return (Criteria) this;
		}

		public Criteria andPrizeMultiBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("prize_multi between", value1, value2, "prizeMulti");
			return (Criteria) this;
		}

		public Criteria andPrizeMultiNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("prize_multi not between", value1, value2, "prizeMulti");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_prize_info
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
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_prize_info
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}