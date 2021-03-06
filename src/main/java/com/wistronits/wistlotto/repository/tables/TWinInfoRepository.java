package com.wistronits.wistlotto.repository.tables;

import com.wistronits.wistlotto.model.tables.TWinInfo;
import com.wistronits.wistlotto.model.tables.TWinInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TWinInfoRepository {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_win_info
	 * @mbg.generated
	 */
	long countByExample(TWinInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_win_info
	 * @mbg.generated
	 */
	int deleteByExample(TWinInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_win_info
	 * @mbg.generated
	 */
	int insert(TWinInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_win_info
	 * @mbg.generated
	 */
	int insertSelective(TWinInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_win_info
	 * @mbg.generated
	 */
	List<TWinInfo> selectByExample(TWinInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_win_info
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") TWinInfo record, @Param("example") TWinInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_win_info
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") TWinInfo record, @Param("example") TWinInfoCriteria example);
}