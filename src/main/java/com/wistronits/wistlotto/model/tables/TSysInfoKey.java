package com.wistronits.wistlotto.model.tables;

import java.io.Serializable;

public class TSysInfoKey implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_sys_info.sys_key
	 * @mbg.generated
	 */
	private String sysKey;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_sys_info
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_sys_info.sys_key
	 * @return  the value of t_sys_info.sys_key
	 * @mbg.generated
	 */
	public String getSysKey() {
		return sysKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_sys_info.sys_key
	 * @param sysKey  the value for t_sys_info.sys_key
	 * @mbg.generated
	 */
	public void setSysKey(String sysKey) {
		this.sysKey = sysKey == null ? null : sysKey.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_info
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
		TSysInfoKey other = (TSysInfoKey) that;
		return (this.getSysKey() == null ? other.getSysKey() == null : this.getSysKey().equals(other.getSysKey()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_info
	 * @mbg.generated
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getSysKey() == null) ? 0 : getSysKey().hashCode());
		return result;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_info
	 * @mbg.generated
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", sysKey=").append(sysKey);
		sb.append("]");
		return sb.toString();
	}
}