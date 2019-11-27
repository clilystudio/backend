package com.wistronits.wistlotto.repository.tables;

import com.wistronits.wistlotto.model.tables.TEmpInfo;
import com.wistronits.wistlotto.model.tables.TEmpInfoCriteria;
import com.wistronits.wistlotto.model.tables.TEmpInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TEmpInfoRepository {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_emp_info
	 * 
	 * @mbg.generated
	 */
	long countByExample(TEmpInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_emp_info
	 * 
	 * @mbg.generated
	 */
	int deleteByExample(TEmpInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_emp_info
	 * 
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(TEmpInfoKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_emp_info
	 * 
	 * @mbg.generated
	 */
	int insert(TEmpInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_emp_info
	 * 
	 * @mbg.generated
	 */
	int insertSelective(TEmpInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_emp_info
	 * 
	 * @mbg.generated
	 */
	List<TEmpInfo> selectByExample(TEmpInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_emp_info
	 * 
	 * @mbg.generated
	 */
	TEmpInfo selectByPrimaryKey(TEmpInfoKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_emp_info
	 * 
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") TEmpInfo record, @Param("example") TEmpInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_emp_info
	 * 
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") TEmpInfo record, @Param("example") TEmpInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_emp_info
	 * 
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(TEmpInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_emp_info
	 * 
	 * @mbg.generated
	 */
	int updateByPrimaryKey(TEmpInfo record);
}