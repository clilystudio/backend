package com.wistronits.wistlotto.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.wistronits.wistlotto.framework.message.MessageId;
import com.wistronits.wistlotto.framework.message.SystemMessage;
import com.wistronits.wistlotto.model.CommonResultModel;
import com.wistronits.wistlotto.model.LottoInfoModel;
import com.wistronits.wistlotto.model.WinnerInfoModel;
import com.wistronits.wistlotto.model.CommonConst;
import com.wistronits.wistlotto.model.CommonConst.ResultCode;
import com.wistronits.wistlotto.model.tables.TEmpInfo;
import com.wistronits.wistlotto.model.tables.TEmpInfoKey;
import com.wistronits.wistlotto.model.tables.TPrizeGroupInfo;
import com.wistronits.wistlotto.model.tables.TPrizeGroupInfoCriteria;
import com.wistronits.wistlotto.model.tables.TPrizeInfo;
import com.wistronits.wistlotto.model.tables.TPrizeInfoKey;
import com.wistronits.wistlotto.model.tables.TSysInfo;
import com.wistronits.wistlotto.model.tables.TSysInfoCriteria;
import com.wistronits.wistlotto.model.tables.TSysInfoKey;
import com.wistronits.wistlotto.model.tables.TWinInfo;
import com.wistronits.wistlotto.model.tables.TWinInfoCriteria;
import com.wistronits.wistlotto.repository.LottoRepository;
import com.wistronits.wistlotto.repository.tables.TEmpInfoRepository;
import com.wistronits.wistlotto.repository.tables.TPrizeGroupInfoRepository;
import com.wistronits.wistlotto.repository.tables.TPrizeInfoRepository;
import com.wistronits.wistlotto.repository.tables.TSysInfoRepository;
import com.wistronits.wistlotto.repository.tables.TWinInfoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统设置服务
 * 
 * @author 盛广立 2019年1月11日
 */
@Service
@Slf4j
public class SysService {

	@Inject
	private TSysInfoRepository sysInfoRepository;

	@Inject
	private TEmpInfoRepository empInfoRepository;

	@Inject
	private TPrizeInfoRepository prizeInfoRepository;

	@Inject
	private TPrizeGroupInfoRepository prizeGroupInfoRepository;

	@Inject
	private TWinInfoRepository winInfoRepository;

	@Inject
	private LottoRepository lottoRepository;

	@Inject
	private SimpMessagingTemplate webSocket;

	/**
	 * 清除全部系统设置
	 */
	public void clearAll() {
		log.debug("清除全部系统设置");
		sysInfoRepository.deleteByExample(new TSysInfoCriteria());
	}

	/**
	 * 取得指定Key的系统设置
	 * 
	 * @param sysKey Key
	 * @return 系统设置
	 */
	public TSysInfo getValue(String sysKey) {
		log.debug("取得指定Key的系统设置");
		TSysInfoKey key = new TSysInfoKey();
		key.setSysKey(sysKey);
		return sysInfoRepository.selectByPrimaryKey(key);
	}

	/**
	 * 添加系统设置
	 * 
	 * @param sysInfo 系统设置
	 * @return 添加结果
	 */
	public CommonResultModel setValue(TSysInfo sysInfo) {
		log.debug("添加系统设置");
		CommonResultModel result = new CommonResultModel();
		if (sysInfoRepository.selectByPrimaryKey(sysInfo) != null) {
			if (sysInfoRepository.updateByPrimaryKey(sysInfo) == 0) {
				result.setCode(ResultCode.FAILED);
				result.setMessage(new SystemMessage(MessageId.MBE1010).getMessage());
				return result;
			}
		} else {
			sysInfoRepository.insert(sysInfo);
		}
		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1008).getMessage());
		return result;
	}

	/**
	 * 刪除指定Key的系统设置
	 * 
	 * @param sysKey Key
	 */
	public void deleteValue(String sysKey) {
		log.debug("刪除指定Key的系统设置");
		TSysInfoKey key = new TSysInfoKey();
		key.setSysKey(sysKey);
		sysInfoRepository.deleteByPrimaryKey(key);
	}

	public CommonResultModel resetWinner() {
		CommonResultModel result = new CommonResultModel();
		return result;
	}

	/**
	 * 获取中奖信息一览
	 * 
	 * @return 中奖信息一览
	 */
	public List<WinnerInfoModel> getWinnerList() {
		log.debug("获取中奖信息一览");
		return lottoRepository.getWinnerInfoList();
	}

	/**
	 * 重置系统
	 */
	public CommonResultModel reset() {
		log.info("重置系统");
		CommonResultModel result = new CommonResultModel();

		// 清除抽奖设置信息
		this.deleteValue(CommonConst.SysKey.KEY_LOTTO);

		// 删除中奖信息
		winInfoRepository.deleteByExample(new TWinInfoCriteria());

		// 重置员工信息
		lottoRepository.resetEmpInfo();

		// 重置奖项信息
		lottoRepository.resetPrizeInfo();

		// 重置奖项分组信息
		lottoRepository.resetPrizeGroupInfo();

		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1008).getMessage());
		webSocket.convertAndSend(CommonConst.STATUS_BROADCAST, "");
		return result;
	}

	/**
	 * 设置中奖信息
	 * 
	 * @param lottoInfo 中奖信息
	 * @return 设置结果
	 */
	public CommonResultModel setLotto(LottoInfoModel lottoInfo) {
		log.debug("设置中奖信息");
		CommonResultModel result = new CommonResultModel();

		String prizeId = lottoInfo.getPrizeId();
		List<TEmpInfo> empList = lottoInfo.getEmpList();

		// 取得奖项按组分配情况
		TPrizeGroupInfoCriteria example = new TPrizeGroupInfoCriteria();
		example.createCriteria().andPrizeIdEqualTo(prizeId);
		example.setOrderByClause("group_id desc");
		List<TPrizeGroupInfo> prizeGroupInfoList = prizeGroupInfoRepository.selectByExample(example);
		Map<String, TPrizeGroupInfo> prizeGroupInfoMap = new HashMap<>();

		// 更新各组中奖人数
		BigDecimal ungroupNum = BigDecimal.ZERO;
		for (TEmpInfo empInfo : empList) {
			String groupId = empInfo.getGroupId();
			// 组内分配标识
			boolean ungroup = true;
			// 匹配组ID
			for (TPrizeGroupInfo prizeGroupInfo : prizeGroupInfoList) {
				if (StringUtils.equalsIgnoreCase(prizeGroupInfo.getGroupId(), CommonConst.UNLIMIT_GROUP)
						|| StringUtils.equalsIgnoreCase(prizeGroupInfo.getGroupId(), groupId)) {
					if (prizeGroupInfo.getPrizeNumber().compareTo(prizeGroupInfo.getPrizeWinner()) <= 0) {
						// 组内中奖数已等于奖项数时，奖项数加一
						prizeGroupInfo.setPrizeNumber(prizeGroupInfo.getPrizeNumber().add(BigDecimal.ONE));
						// 未能正常组内分配数加一
						ungroupNum = ungroupNum.add(BigDecimal.ONE);
					}
					// 组内中奖数加一
					prizeGroupInfo.setPrizeWinner(prizeGroupInfo.getPrizeWinner().add(BigDecimal.ONE));
					ungroup = false;
					break;
				}
			}
			if (ungroup) {
				// 未能正常组内分配时，记录下分组信息
				ungroupNum = ungroupNum.add(BigDecimal.ONE);
				if (prizeGroupInfoMap.containsKey(groupId)) {
					TPrizeGroupInfo prizeGroupInfo2 = prizeGroupInfoMap.get(groupId);
					prizeGroupInfo2.setPrizeNumber(prizeGroupInfo2.getPrizeNumber().add(BigDecimal.ONE));
					prizeGroupInfo2.setPrizeWinner(prizeGroupInfo2.getPrizeWinner().add(BigDecimal.ONE));
				} else {
					TPrizeGroupInfo prizeGroupInfo2 = new TPrizeGroupInfo();
					prizeGroupInfo2.setPrizeId(prizeId);
					prizeGroupInfo2.setGroupId(groupId);
					prizeGroupInfo2.setPrizeNumber(BigDecimal.ONE);
					prizeGroupInfo2.setPrizeWinner(BigDecimal.ONE);
					prizeGroupInfoMap.put(groupId, prizeGroupInfo2);
				}
			}
			empInfo.setPrizeFlag(CommonConst.PrizeFlag.WIN);
		}

		// 将未能正常组内分配的中奖组，分摊到其它组内，优先顺序是剩余奖项数，奖项数从大到小
		Comparator<TPrizeGroupInfo> comparator = new Comparator<TPrizeGroupInfo>() {
			@Override
			public int compare(TPrizeGroupInfo o1, TPrizeGroupInfo o2) {
				BigDecimal prizeNumber1 = o1.getPrizeNumber();
				BigDecimal prizeWinner1 = o1.getPrizeWinner();
				BigDecimal remain1 = prizeNumber1.subtract(prizeWinner1);
				BigDecimal prizeNumber2 = o2.getPrizeNumber();
				BigDecimal prizeWinner2 = o2.getPrizeWinner();
				BigDecimal remain2 = prizeNumber2.subtract(prizeWinner2);
				int result = remain1.compareTo(remain2);
				if (result == 0) {
					result = prizeNumber1.compareTo(prizeNumber2);
				}
				return result;
			}
		};

		while (ungroupNum.compareTo(BigDecimal.ZERO) > 0) {
			prizeGroupInfoList.sort(comparator);
			// 分摊错误
			boolean isError = true;
			for (TPrizeGroupInfo prizeGroupInfo : prizeGroupInfoList) {
				if (prizeGroupInfo.getPrizeNumber().compareTo(prizeGroupInfo.getPrizeWinner()) > 0) {
					prizeGroupInfo.setPrizeNumber(prizeGroupInfo.getPrizeNumber().subtract(BigDecimal.ONE));
					ungroupNum = ungroupNum.subtract(BigDecimal.ONE);
					isError = false;
					break;
				}
			}
			if (isError) {
				// 分摊错误，中奖数超过了奖项数。这种情况理论上不应该出现
				String message = new SystemMessage(MessageId.MBE1012).getMessage();
				log.error(message);
				result.setCode(ResultCode.FAILED);
				result.setMessage(message);
				return result;
			}
		}

		TPrizeInfoKey key = new TPrizeInfoKey();
		key.setPrizeId(lottoInfo.getPrizeId());
		TPrizeInfo prizeInfo = prizeInfoRepository.selectByPrimaryKey(key);
		if (Objects.nonNull(prizeInfo)) {
			// 更新奖项分组信息
			BigDecimal sumPrizeNumber = BigDecimal.ZERO;
			BigDecimal sumPrizeWinner = BigDecimal.ZERO;
			for (TPrizeGroupInfo prizeGroupInfo : prizeGroupInfoList) {
				sumPrizeNumber = sumPrizeNumber.add(prizeGroupInfo.getPrizeNumber());
				sumPrizeWinner = sumPrizeWinner.add(prizeGroupInfo.getPrizeWinner());
				TPrizeGroupInfoCriteria example2 = new TPrizeGroupInfoCriteria();
				example2.createCriteria().andPrizeIdEqualTo(prizeId).andGroupIdEqualTo(prizeGroupInfo.getGroupId());
				prizeGroupInfoRepository.updateByExample(prizeGroupInfo, example2);
			}
			for (Map.Entry<String, TPrizeGroupInfo> entry : prizeGroupInfoMap.entrySet()) {
				TPrizeGroupInfo prizeGroupInfo = entry.getValue();
				sumPrizeNumber = sumPrizeNumber.add(prizeGroupInfo.getPrizeNumber());
				sumPrizeWinner = sumPrizeWinner.add(prizeGroupInfo.getPrizeWinner());
				prizeGroupInfoRepository.insert(prizeGroupInfo);
			}

			// 更新奖项信息
			if (sumPrizeWinner.compareTo(sumPrizeNumber) >= 0) {
				// 奖项全部抽完
				prizeInfo.setPrizeStatus(CommonConst.PrizeStatus.STOPPED);
			} else {
				// 还有剩余奖项
				prizeInfo.setPrizeStatus(CommonConst.PrizeStatus.STARTTED);
			}
			prizeInfoRepository.updateByPrimaryKey(prizeInfo);

			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String winTime = sdf1.format(new Date(System.currentTimeMillis()));

			for (TEmpInfo emp : empList) {
				// 添加中奖信息
				TWinInfo winInfo = new TWinInfo();
				winInfo.setPrizeId(prizeId);
				winInfo.setEmpId(emp.getEmpId());
				winInfo.setWinTime(winTime);
				winInfoRepository.insert(winInfo);

				// 更新员工信息（允许重复中奖时，不应该有额外加权）
				emp.setEmpRate(BigDecimal.ONE);
				emp.setPrizeFlag(CommonConst.PrizeFlag.WIN);
				empInfoRepository.updateByPrimaryKey(emp);
			}
			result.setCode(ResultCode.SUCCESS);
			result.setMessage(new SystemMessage(MessageId.MBI1008).getMessage());
		} else {
			result.setCode(ResultCode.FAILED);
			result.setMessage(new SystemMessage(MessageId.MBE1010).getMessage());
		}

		return result;
	}

	/**
	 * 放弃中奖
	 * 
	 * @param winnerInfo 中奖信息
	 * @return 放弃结果
	 */
	public CommonResultModel removeWinner(WinnerInfoModel winnerInfo) {
		log.debug("放弃中奖");
		CommonResultModel result = new CommonResultModel();

		String prizeId = winnerInfo.getPrizeId();
		String empId = winnerInfo.getEmpId();

		// 取得员工信息
		TEmpInfoKey empKey = new TEmpInfoKey();
		empKey.setEmpId(empId);
		TEmpInfo empInfo = empInfoRepository.selectByPrimaryKey(empKey);
		if (Objects.isNull(empInfo)) {
			result.setCode(ResultCode.FAILED);
			result.setMessage(new SystemMessage(MessageId.MBE1006).getMessage());
			return result;
		}

		// 取得奖项信息
		TPrizeInfoKey key = new TPrizeInfoKey();
		key.setPrizeId(prizeId);
		TPrizeInfo prizeInfo = prizeInfoRepository.selectByPrimaryKey(key);
		if (Objects.isNull(prizeInfo)) {
			result.setCode(ResultCode.FAILED);
			result.setMessage(new SystemMessage(MessageId.MBE1008).getMessage());
			return result;
		}

		// 取得奖项按组分配情况
		TPrizeGroupInfoCriteria example = new TPrizeGroupInfoCriteria();
		example.createCriteria().andPrizeIdEqualTo(prizeId);
		example.setOrderByClause("group_id desc");
		List<TPrizeGroupInfo> prizeGroupInfoList = prizeGroupInfoRepository.selectByExample(example);

		// 重新计算奖项分组信息
		boolean isError = true;
		String groupId = empInfo.getGroupId();
		for (TPrizeGroupInfo prizeGroupInfo : prizeGroupInfoList) {
			if (StringUtils.equalsIgnoreCase(prizeGroupInfo.getGroupId(), CommonConst.UNLIMIT_GROUP)
					|| StringUtils.equalsIgnoreCase(prizeGroupInfo.getGroupId(), groupId)) {
				prizeGroupInfo.setPrizeWinner(prizeGroupInfo.getPrizeWinner().subtract(BigDecimal.ONE));
				isError = false;
				break;
			}
		}

		if (isError) {
			// 未能找到奖项分组信息。这种情况理论上不应该出现
			String message = new SystemMessage(MessageId.MBE1013).getMessage();
			log.error(message);
			result.setCode(ResultCode.FAILED);
			result.setMessage(message);
			return result;
		}

		// 更新员工信息（弃奖后再也不会参与抽奖）
		empInfo.setEmpRate(BigDecimal.ZERO);
		empInfo.setPrizeFlag(CommonConst.PrizeFlag.GIVEUP);
		empInfoRepository.updateByPrimaryKey(empInfo);

		// 更新奖项分组信息
		BigDecimal sumPrizeNumber = BigDecimal.ZERO;
		BigDecimal sumPrizeWinner = BigDecimal.ZERO;
		for (TPrizeGroupInfo prizeGroupInfo : prizeGroupInfoList) {
			sumPrizeNumber = sumPrizeNumber.add(prizeGroupInfo.getPrizeNumber());
			sumPrizeWinner = sumPrizeWinner.add(prizeGroupInfo.getPrizeWinner());
			TPrizeGroupInfoCriteria example2 = new TPrizeGroupInfoCriteria();
			example2.createCriteria().andPrizeIdEqualTo(prizeId).andGroupIdEqualTo(prizeGroupInfo.getGroupId());
			prizeGroupInfoRepository.updateByExample(prizeGroupInfo, example2);
		}
		// 更新奖项信息
		if (sumPrizeWinner.compareTo(sumPrizeNumber) >= 0) {
			// 奖项全部抽完
			prizeInfo.setPrizeStatus(CommonConst.PrizeStatus.STOPPED);
		} else {
			// 还有剩余奖项
			prizeInfo.setPrizeStatus(CommonConst.PrizeStatus.STARTTED);
		}
		prizeInfoRepository.updateByPrimaryKey(prizeInfo);

		// 删除中奖信息
		TWinInfoCriteria example2 = new TWinInfoCriteria();
		example2.createCriteria().andEmpIdEqualTo(empId).andPrizeIdEqualTo(prizeId);
		winInfoRepository.deleteByExample(example2);

		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1008).getMessage());
		webSocket.convertAndSend(CommonConst.STATUS_BROADCAST, "");
		return result;
	}

	/**
	 * 刷新前台
	 * 
	 * @return 移除结果
	 */
	public CommonResultModel refreshFront() {
		log.debug("刷新前台");
		CommonResultModel result = new CommonResultModel();
		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1008).getMessage());
		webSocket.convertAndSend(CommonConst.STATUS_BROADCAST, "");
		return result;
	}
}
