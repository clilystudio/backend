package com.wistronits.wistlotto.model.tables;

import java.io.Serializable;
import java.math.BigDecimal;

public class TEmpInfo extends TEmpInfoKey implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_emp_info.group_id
	 * @mbg.generated
	 */
	private String groupId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_emp_info.emp_name
	 * @mbg.generated
	 */
	private String empName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_emp_info.emp_sex
	 * @mbg.generated
	 */
	private String empSex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_emp_info.emp_date
	 * @mbg.generated
	 */
	private String empDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_emp_info.dept_id
	 * @mbg.generated
	 */
	private String deptId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_emp_info.emp_rate
	 * @mbg.generated
	 */
	private BigDecimal empRate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_emp_info.prize_flag
	 * @mbg.generated
	 */
	private String prizeFlag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_emp_info
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_emp_info.group_id
	 * @return  the value of t_emp_info.group_id
	 * @mbg.generated
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_emp_info.group_id
	 * @param groupId  the value for t_emp_info.group_id
	 * @mbg.generated
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId == null ? null : groupId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_emp_info.emp_name
	 * @return  the value of t_emp_info.emp_name
	 * @mbg.generated
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_emp_info.emp_name
	 * @param empName  the value for t_emp_info.emp_name
	 * @mbg.generated
	 */
	public void setEmpName(String empName) {
		this.empName = empName == null ? null : empName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_emp_info.emp_sex
	 * @return  the value of t_emp_info.emp_sex
	 * @mbg.generated
	 */
	public String getEmpSex() {
		return empSex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_emp_info.emp_sex
	 * @param empSex  the value for t_emp_info.emp_sex
	 * @mbg.generated
	 */
	public void setEmpSex(String empSex) {
		this.empSex = empSex == null ? null : empSex.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_emp_info.emp_date
	 * @return  the value of t_emp_info.emp_date
	 * @mbg.generated
	 */
	public String getEmpDate() {
		return empDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_emp_info.emp_date
	 * @param empDate  the value for t_emp_info.emp_date
	 * @mbg.generated
	 */
	public void setEmpDate(String empDate) {
		this.empDate = empDate == null ? null : empDate.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_emp_info.dept_id
	 * @return  the value of t_emp_info.dept_id
	 * @mbg.generated
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_emp_info.dept_id
	 * @param deptId  the value for t_emp_info.dept_id
	 * @mbg.generated
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId == null ? null : deptId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_emp_info.emp_rate
	 * @return  the value of t_emp_info.emp_rate
	 * @mbg.generated
	 */
	public BigDecimal getEmpRate() {
		return empRate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_emp_info.emp_rate
	 * @param empRate  the value for t_emp_info.emp_rate
	 * @mbg.generated
	 */
	public void setEmpRate(BigDecimal empRate) {
		this.empRate = empRate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_emp_info.prize_flag
	 * @return  the value of t_emp_info.prize_flag
	 * @mbg.generated
	 */
	public String getPrizeFlag() {
		return prizeFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_emp_info.prize_flag
	 * @param prizeFlag  the value for t_emp_info.prize_flag
	 * @mbg.generated
	 */
	public void setPrizeFlag(String prizeFlag) {
		this.prizeFlag = prizeFlag == null ? null : prizeFlag.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_emp_info
	 * @mbg.generated
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		TEmpInfo other = (TEmpInfo) that;
		return (this.getEmpId() == null ? other.getEmpId() == null : this.getEmpId().equals(other.getEmpId()))
				&& (this.getGroupId() == null ? other.getGroupId() == null
						: this.getGroupId().equals(other.getGroupId()))
				&& (this.getEmpName() == null ? other.getEmpName() == null
						: this.getEmpName().equals(other.getEmpName()))
				&& (this.getEmpSex() == null ? other.getEmpSex() == null : this.getEmpSex().equals(other.getEmpSex()))
				&& (this.getEmpDate() == null ? other.getEmpDate() == null
						: this.getEmpDate().equals(other.getEmpDate()))
				&& (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
				&& (this.getEmpRate() == null ? other.getEmpRate() == null
						: this.getEmpRate().equals(other.getEmpRate()))
				&& (this.getPrizeFlag() == null ? other.getPrizeFlag() == null
						: this.getPrizeFlag().equals(other.getPrizeFlag()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_emp_info
	 * @mbg.generated
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getEmpId() == null) ? 0 : getEmpId().hashCode());
		result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
		result = prime * result + ((getEmpName() == null) ? 0 : getEmpName().hashCode());
		result = prime * result + ((getEmpSex() == null) ? 0 : getEmpSex().hashCode());
		result = prime * result + ((getEmpDate() == null) ? 0 : getEmpDate().hashCode());
		result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
		result = prime * result + ((getEmpRate() == null) ? 0 : getEmpRate().hashCode());
		result = prime * result + ((getPrizeFlag() == null) ? 0 : getPrizeFlag().hashCode());
		return result;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_emp_info
	 * @mbg.generated
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", groupId=").append(groupId);
		sb.append(", empName=").append(empName);
		sb.append(", empSex=").append(empSex);
		sb.append(", empDate=").append(empDate);
		sb.append(", deptId=").append(deptId);
		sb.append(", empRate=").append(empRate);
		sb.append(", prizeFlag=").append(prizeFlag);
		sb.append("]");
		return sb.toString();
	}
}