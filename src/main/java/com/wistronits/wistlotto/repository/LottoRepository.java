package com.wistronits.wistlotto.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.wistronits.wistlotto.model.EmpInfoModel;
import com.wistronits.wistlotto.model.WinnerInfoModel;
import com.wistronits.wistlotto.model.tables.TPrizeInfo;

/**
 * 抽奖相关DAO
 * 
 * @author 盛广立 2019年1月16日
 */
@Mapper
public interface LottoRepository {

	/**
	 * 取得员工一览
	 * 
	 * @return 员工一览
	 */
	List<EmpInfoModel> getEmpList();

	/**
	 * 取得中奖信息一览
	 * 
	 * @return 中奖信息一览
	 */
	List<WinnerInfoModel> getWinnerInfoList();

	/**
	 * 取得可抽选奖项
	 * 
	 * @return 奖项信息
	 */
	List<TPrizeInfo> getLottoPrize();

	/**
	 * 重置员工中奖标识
	 * 
	 * @return 更新件数
	 */
	int resetEmpInfo();

	/**
	 * 重置员工中奖标识
	 * 
	 * @return 更新件数
	 */
	int resetPrizeInfo();

	/**
	 * 重置员工中奖标识
	 * 
	 * @return 更新件数
	 */
	int resetPrizeGroupInfo();
}