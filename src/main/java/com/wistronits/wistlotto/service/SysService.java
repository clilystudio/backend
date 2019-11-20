package com.wistronits.wistlotto.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

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
import com.wistronits.wistlotto.model.tables.TEmpInfoCriteria;
import com.wistronits.wistlotto.model.tables.TEmpInfoKey;
import com.wistronits.wistlotto.model.tables.TPrizeInfo;
import com.wistronits.wistlotto.model.tables.TPrizeInfoCriteria;
import com.wistronits.wistlotto.model.tables.TPrizeInfoKey;
import com.wistronits.wistlotto.model.tables.TSysInfo;
import com.wistronits.wistlotto.model.tables.TSysInfoCriteria;
import com.wistronits.wistlotto.model.tables.TSysInfoKey;
import com.wistronits.wistlotto.model.tables.TWinInfo;
import com.wistronits.wistlotto.model.tables.TWinInfoCriteria;
import com.wistronits.wistlotto.repository.LottoRepository;
import com.wistronits.wistlotto.repository.tables.TEmpInfoRepository;
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
	private TWinInfoRepository winInfoRepository;

	@Inject
	private LottoRepository lottoRepository;
	
	@Inject
	private SimpMessagingTemplate webSocket;

	/**
	 * 清除全部系统设置
	 */
	public void clearAll() {
		log.debug("###clearAll");
		sysInfoRepository.deleteByExample(new TSysInfoCriteria());
	}

	/**
	 * 取得指定Key的系统设置
	 * 
	 * @param sysKey Key
	 * @return 系统设置
	 */
	public TSysInfo getValue(String sysKey) {
		log.debug("###getValue");
		TSysInfoKey key = new TSysInfoKey();
		key.setSysKey(sysKey);
		return sysInfoRepository.selectByPrimaryKey(key);
	}

	/**
	 * 更新系统设置
	 * 
	 * @param sysInfo 系统设置
	 * @return 添加结果
	 */
	public CommonResultModel setValue(TSysInfo sysInfo) {
		log.debug("###addSys");
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
		log.debug("###deleteValue");
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
		log.debug("###getWinnerList");
		return lottoRepository.getWinnerInfoList();
	}

	/**
	 * 重置系统
	 */
	public CommonResultModel reset() {
		log.debug("###reset");
		CommonResultModel result = new CommonResultModel();

		// 清除抽奖设置信息
		this.deleteValue(CommonConst.SysKey.KEY_LOTTO);

		// 删除中奖信息
		winInfoRepository.deleteByExample(new TWinInfoCriteria());

		// 重置员工信息
		List<TEmpInfo> empInfoList = empInfoRepository.selectByExample(new TEmpInfoCriteria());
		empInfoList.forEach(e -> {
			e.setPrizeFlag(CommonConst.PrizeFlag.NONE);
			empInfoRepository.updateByPrimaryKey(e);
		});

		// 重置奖项信息
		List<TPrizeInfo> prizeInfoList = prizeInfoRepository.selectByExample(new TPrizeInfoCriteria());
		prizeInfoList.forEach(p -> {
			p.setPrizeStatus(CommonConst.PrizeStatus.READYING);
			p.setPrizeWinner(BigDecimal.ZERO);
			prizeInfoRepository.updateByPrimaryKey(p);
		});

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
		log.debug("###setLotto");
		CommonResultModel result = new CommonResultModel();

		String prizeId = lottoInfo.getPrizeId();
		List<TEmpInfo> empList = lottoInfo.getEmpList();

		// 更新奖项信息
		TPrizeInfoKey key = new TPrizeInfoKey();
		key.setPrizeId(prizeId);
		TPrizeInfo prizeInfo = prizeInfoRepository.selectByPrimaryKey(key);
		if (Objects.nonNull(prizeInfo)) {
			// 更新奖项信息
			prizeInfo.setPrizeWinner(prizeInfo.getPrizeWinner().add(new BigDecimal(empList.size())));
			if (prizeInfo.getPrizeWinner().compareTo(prizeInfo.getPrizeNumber()) == 0) {
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

				// 更新员工信息
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
		log.debug("###removeWinner");

		String prizeId = winnerInfo.getPrizeId();
		String empId = winnerInfo.getEmpId();

		// 更新奖项信息
		TPrizeInfoKey key = new TPrizeInfoKey();
		key.setPrizeId(prizeId);
		TPrizeInfo prizeInfo = prizeInfoRepository.selectByPrimaryKey(key);
		if (Objects.nonNull(prizeInfo)) {
			// 更新奖项信息
			prizeInfo.setPrizeWinner(prizeInfo.getPrizeWinner().subtract(BigDecimal.ONE));
			if (prizeInfo.getPrizeWinner().compareTo(prizeInfo.getPrizeNumber()) == 0) {
				// 奖项全部抽完
				prizeInfo.setPrizeStatus(CommonConst.PrizeStatus.STOPPED);
			} else {
				// 还有剩余奖项
				prizeInfo.setPrizeStatus(CommonConst.PrizeStatus.STARTTED);
			}
			prizeInfoRepository.updateByPrimaryKey(prizeInfo);

			// 更新员工信息
			TEmpInfoKey empKey = new TEmpInfoKey();
			empKey.setEmpId(empId);
			TEmpInfo empInfo = empInfoRepository.selectByPrimaryKey(empKey);
			if (Objects.nonNull(empInfo)) {
				empInfo.setPrizeFlag(CommonConst.PrizeFlag.GIVEUP);
				empInfoRepository.updateByPrimaryKey(empInfo);
			}

			// 删除中奖信息
			TWinInfoCriteria example = new TWinInfoCriteria();
			example.createCriteria().andEmpIdEqualTo(empId).andPrizeIdEqualTo(prizeId);
			winInfoRepository.deleteByExample(example);
		}

		CommonResultModel result = new CommonResultModel();
		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1008).getMessage());
		webSocket.convertAndSend(CommonConst.STATUS_BROADCAST, "");
		return result;
	}

	/**
	 * 取得可抽选奖项
	 * 
	 * @return 奖项信息
	 */
	public TPrizeInfo getLottoPrize() {
		List<TPrizeInfo> prizeList = lottoRepository.getLottoPrize();
		if (prizeList.size() == 0) {
			return new TPrizeInfo();
		} else {
			return prizeList.get(0);
		}
	}

	/**
	 * 刷新前台
	 * 
	 * @return 移除结果
	 */
	public CommonResultModel refreshFront() {
		log.debug("###refreshFront");
		CommonResultModel result = new CommonResultModel();
		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1008).getMessage());
		webSocket.convertAndSend(CommonConst.STATUS_BROADCAST, "");
		return result;
	}
}
