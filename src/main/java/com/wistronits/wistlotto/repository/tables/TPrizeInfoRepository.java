package com.wistronits.wistlotto.repository.tables;

import com.wistronits.wistlotto.domain.tables.TPrizeInfo;
import com.wistronits.wistlotto.domain.tables.TPrizeInfoCriteria;
import com.wistronits.wistlotto.domain.tables.TPrizeInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TPrizeInfoRepository {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_prize_info
     *
     * @mbg.generated
     */
    long countByExample(TPrizeInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_prize_info
     *
     * @mbg.generated
     */
    int deleteByExample(TPrizeInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_prize_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(TPrizeInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_prize_info
     *
     * @mbg.generated
     */
    int insert(TPrizeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_prize_info
     *
     * @mbg.generated
     */
    int insertSelective(TPrizeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_prize_info
     *
     * @mbg.generated
     */
    List<TPrizeInfo> selectByExample(TPrizeInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_prize_info
     *
     * @mbg.generated
     */
    TPrizeInfo selectByPrimaryKey(TPrizeInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_prize_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TPrizeInfo record, @Param("example") TPrizeInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_prize_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TPrizeInfo record, @Param("example") TPrizeInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_prize_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TPrizeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_prize_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TPrizeInfo record);
}