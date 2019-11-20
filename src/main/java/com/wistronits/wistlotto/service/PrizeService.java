package com.wistronits.wistlotto.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.wistronits.wistlotto.framework.exception.SystemException;
import com.wistronits.wistlotto.framework.message.MessageId;
import com.wistronits.wistlotto.framework.message.SystemMessage;
import com.wistronits.wistlotto.framework.util.ConverterUtil;
import com.wistronits.wistlotto.framework.util.CsvUtil;
import com.wistronits.wistlotto.model.CommonConst;
import com.wistronits.wistlotto.model.CommonConst.ResultCode;
import com.wistronits.wistlotto.model.CommonResultModel;
import com.wistronits.wistlotto.model.PrizeInfoModel;
import com.wistronits.wistlotto.model.tables.TPrizeInfo;
import com.wistronits.wistlotto.model.tables.TPrizeInfoKey;
import com.wistronits.wistlotto.model.tables.TPrizeInfoCriteria;
import com.wistronits.wistlotto.repository.tables.TPrizeInfoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 奖项信息服务
 * 
 * @author 盛广立 2019年1月11日
 */
@Service
@Slf4j
public class PrizeService {

	@Inject
	private TPrizeInfoRepository prizeInfoRepository;

	/**
	 * 获取奖项一览
	 * 
	 * @return 奖项一览
	 */
	public List<TPrizeInfo> getPrizeList() {
		log.debug("###getPrizeList");
		TPrizeInfoCriteria example = new TPrizeInfoCriteria();
		example.setOrderByClause("prize_order asc, prize_id asc");
		return prizeInfoRepository.selectByExample(example);
	}

	/**
	 * 清除全部奖项
	 */
	public void clearAll() {
		log.debug("###clearAll");
		prizeInfoRepository.deleteByExample(new TPrizeInfoCriteria());
	}

	/**
	 * 批量导入奖项数据
	 * 
	 * @param file 奖项数据
	 * @return 导入结果
	 */
	public CommonResultModel uploadAll(MultipartFile file) {
		log.debug("###uploadAll");
		BeanListProcessor<PrizeInfoModel> processor = new BeanListProcessor<PrizeInfoModel>(PrizeInfoModel.class);
		CommonResultModel result = new CommonResultModel();
		try {
			CsvUtil.loadFile(file, processor);
			List<PrizeInfoModel> prizeAllInfo = processor.getBeans();
			for (PrizeInfoModel prizeInfoModel : prizeAllInfo) {
				TPrizeInfo prizeInfo = ConverterUtil.convertObject(prizeInfoModel, TPrizeInfo.class);
				prizeInfo.setPrizeStatus(CommonConst.PrizeStatus.READYING);
				prizeInfoRepository.insert(prizeInfo);
			}
			result.setCode(ResultCode.SUCCESS);
			result.setMessage(new SystemMessage(MessageId.MBI1001).getMessage());
		} catch (SystemException e) {
			log.error(e.getLocalizedMessage());
			result.setCode(ResultCode.FAILED);
			result.setMessage(e.getMessages().get(0).getMessage());
		}
		return result;
	}

	/**
	 * 取得指定ID奖项信息
	 * 
	 * @param prizeId 奖项ID
	 * @return 奖项信息
	 */
	public TPrizeInfo getPrize(String prizeId) {
		log.debug("###getPrize");
		TPrizeInfoKey key = new TPrizeInfoKey();
		key.setPrizeId(prizeId);
		return prizeInfoRepository.selectByPrimaryKey(key);
	}

	/**
	 * 删除指定ID奖项信息
	 * 
	 * @param prizeIds 奖项ID数组
	 * @return 删除结果
	 */
	public CommonResultModel deletePrizes(String[] prizeIds) {
		log.debug("###deletePrizes");
		CommonResultModel result = new CommonResultModel();
		for (String prizeId : prizeIds) {
			TPrizeInfoKey key = new TPrizeInfoKey();
			key.setPrizeId(prizeId);
			prizeInfoRepository.deleteByPrimaryKey(key);
		}
		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1005).getMessage());
		return result;
	}

	/**
	 * 添加奖项
	 * 
	 * @param prizeInfo 奖项信息
	 * @return 添加结果
	 */
	public CommonResultModel addPrize(TPrizeInfo prizeInfo) {
		log.debug("###addPrize");
		CommonResultModel result = new CommonResultModel();
		TPrizeInfoKey key = new TPrizeInfoKey();
		String prizeId = prizeInfo.getPrizeId();
		key.setPrizeId(prizeId);
		if (prizeInfoRepository.selectByPrimaryKey(key) != null) {
			result.setCode(ResultCode.FAILED);
			result.setMessage(new SystemMessage(MessageId.MBE1007).getMessage());
			return result;
		}
		if (StringUtils.startsWith(prizeId, "CS")) {
			// 现金奖
			String prizeDesc = prizeInfo.getPrizeDesc();
			String deptId = RegExUtils.replaceAll(prizeDesc, "[^0-9]*([0-9]+)[^0-9]*", "$1");
			prizeInfo.setDeptId(deptId);
		}
		prizeInfoRepository.insert(prizeInfo);
		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1006).getMessage());
		return result;
	}

	/**
	 * 编辑奖项
	 * 
	 * @param prizeInfo 奖项信息
	 * @return 编辑结果
	 */
	public CommonResultModel editPrize(TPrizeInfo prizeInfo) {
		log.debug("###editPrize");
		CommonResultModel result = new CommonResultModel();
		if (StringUtils.startsWith(prizeInfo.getPrizeId(), "CS")) {
			// 现金奖
			String prizeDesc = prizeInfo.getPrizeDesc();
			String deptId = RegExUtils.replaceAll(prizeDesc, "[^0-9]*([0-9]+)[^0-9]*", "$1");
			prizeInfo.setDeptId(deptId);
		}
		if (prizeInfoRepository.updateByPrimaryKey(prizeInfo) == 0) {
			result.setCode(ResultCode.FAILED);
			result.setMessage(new SystemMessage(MessageId.MBE1008).getMessage());
			return result;
		}
		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1007).getMessage());
		return result;
	}
}
