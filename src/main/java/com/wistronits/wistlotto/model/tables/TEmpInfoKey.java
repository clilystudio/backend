package com.wistronits.wistlotto.model.tables;

import java.io.Serializable;

public class TEmpInfoKey implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_info.emp_id
     *
     * @mbg.generated
     */
    private String empId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_emp_info
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_info.emp_id
     *
     * @return the value of t_emp_info.emp_id
     *
     * @mbg.generated
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_info.emp_id
     *
     * @param empId the value for t_emp_info.emp_id
     *
     * @mbg.generated
     */
    public void setEmpId(String empId) {
        this.empId = empId == null ? null : empId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_info
     *
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
        TEmpInfoKey other = (TEmpInfoKey) that;
        return (this.getEmpId() == null ? other.getEmpId() == null : this.getEmpId().equals(other.getEmpId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_info
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmpId() == null) ? 0 : getEmpId().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_info
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", empId=").append(empId);
        sb.append("]");
        return sb.toString();
    }
}