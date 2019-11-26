package com.wistronits.wistlotto.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
import com.wistronits.wistlotto.model.tables.TPrizeGroupInfo;
import com.wistronits.wistlotto.model.tables.TPrizeGroupInfoCriteria;
import com.wistronits.wistlotto.model.tables.TPrizeInfo;
import com.wistronits.wistlotto.model.tables.TPrizeInfoCriteria;
import com.wistronits.wistlotto.model.tables.TPrizeInfoKey;
import com.wistronits.wistlotto.repository.LottoRepository;
import com.wistronits.wistlotto.repository.tables.TPrizeGroupInfoRepository;
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

	@Inject
	private TPrizeGroupInfoRepository prizeGroupInfoRepository;

	@Inject
	private LottoRepository lottoRepository;

	/**
	 * 获取奖项一览
	 * 
	 * @return 奖项一览
	 */
	public List<PrizeInfoModel> getPrizeList() {
		log.debug("###getPrizeList");
		List<PrizeInfoModel> prizeList = new ArrayList<>();
		TPrizeInfoCriteria example = new TPrizeInfoCriteria();
		example.setOrderByClause("prize_order asc, prize_id asc");
		List<TPrizeInfo> prizeInfoList = prizeInfoRepository.selectByExample(example);
		for (TPrizeInfo prizeInfo : prizeInfoList) {
			prizeList.add(getPrizeInfoModel(prizeInfo));
		}
		return prizeList;
	}

	/**
	 * 取得奖项完整信息
	 * 
	 * @param prizeInfo 奖项信息
	 * @return 奖项完整信息
	 */
	private PrizeInfoModel getPrizeInfoModel(TPrizeInfo prizeInfo) {
		PrizeInfoModel prizeInfoModel = ConverterUtil.convertObject(prizeInfo, PrizeInfoModel.class);
		
		TPrizeGroupInfoCriteria example = new TPrizeGroupInfoCriteria();
		example.createCriteria().andPrizeIdEqualTo(prizeInfo.getPrizeId());
		List<TPrizeGroupInfo> prizeGroupInfoList = prizeGroupInfoRepository.selectByExample(example);
		String groupLimit = "";
		BigDecimal prizeSumNumber = BigDecimal.ZERO;
		BigDecimal prizeSumWinner = BigDecimal.ZERO;
		for (TPrizeGroupInfo prizeGroupInfo : prizeGroupInfoList) {
			BigDecimal prizeNumber = prizeGroupInfo.getPrizeNumber();
			BigDecimal prizeWinner = prizeGroupInfo.getPrizeWinner();
			prizeSumNumber = prizeSumNumber.add(prizeNumber);
			prizeSumWinner = prizeSumWinner.add(prizeWinner);
			groupLimit = groupLimit + prizeGroupInfo.getGroupId() + "," + prizeNumber.toPlainString() + "," + prizeWinner.toPlainString() + ";";
		}
		if (StringUtils.isNotEmpty(groupLimit)) {
			groupLimit = StringUtils.left(groupLimit, groupLimit.length() - 1);
		}
		prizeInfoModel.setGroupLimit(groupLimit);
		prizeInfoModel.setPrizeNumber(prizeSumNumber);
		prizeInfoModel.setPrizeWinner(prizeSumWinner);
		return prizeInfoModel;
	}

	/**
	 * 清除全部奖项
	 */
	public void clearAll() {
		log.debug("###clearAll");
		prizeGroupInfoRepository.deleteByExample(new TPrizeGroupInfoCriteria());
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
				addPrize(prizeInfoModel);
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
	 * @param groupId 抽奖组ID
	 * @return 奖项信息
	 */
	public PrizeInfoModel getPrize(String prizeId) {
		log.debug("###getPrize");
		TPrizeInfoKey key = new TPrizeInfoKey();
		key.setPrizeId(prizeId);
		TPrizeInfo prizeInfo = prizeInfoRepository.selectByPrimaryKey(key);
		return getPrizeInfoModel(prizeInfo);
	}

	/**
	 * 取得可抽选奖项
	 * 
	 * @return 奖项信息
	 */
	public PrizeInfoModel getLottoPrize() {
		List<TPrizeInfo> prizeList = lottoRepository.getLottoPrize();
		TPrizeInfo prizeInfo = new TPrizeInfo();
		if (prizeList.size() > 0) {
			prizeInfo = prizeList.get(0);
		}
		return getPrizeInfoModel(prizeInfo);
	}

	/**
	 * 删除指定ID奖项信息
	 * 
	 * @param prizeInfos 奖项ID数组
	 * @return 删除结果
	 */
	public CommonResultModel deletePrizes(String[] prizeIds) {
		log.debug("###deletePrizes");
		CommonResultModel result = new CommonResultModel();
		for (String prizeId : prizeIds) {
			TPrizeGroupInfoCriteria example = new TPrizeGroupInfoCriteria();
			example.createCriteria().andPrizeIdEqualTo(prizeId);
			prizeGroupInfoRepository.deleteByExample(example);
			
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
	public CommonResultModel addPrize(PrizeInfoModel prizeInfoModel) {
		log.debug("###addPrize");
		CommonResultModel result = new CommonResultModel();
		String prizeId = prizeInfoModel.getPrizeId();
		if (getPrize(prizeId) != null) {
			result.setCode(ResultCode.FAILED);
			result.setMessage(new SystemMessage(MessageId.MBE1007).getMessage());
			return result;
		}
		TPrizeInfo prizeInfo = ConverterUtil.convertObject(prizeInfoModel, TPrizeInfo.class);
		prizeInfo.setPrizeStatus(CommonConst.PrizeStatus.READYING);
		prizeInfoRepository.insert(prizeInfo);

		TPrizeGroupInfoCriteria example = new TPrizeGroupInfoCriteria();
		example.createCriteria().andPrizeIdEqualTo(prizeId);
		prizeGroupInfoRepository.deleteByExample(example);
		
		String groupLimit = prizeInfoModel.getGroupLimit();
		String[] prizeGroups = groupLimit.split(";");		
		for (String prizeGroup : prizeGroups) {
			String[] prizeGroupInfos = prizeGroup.split(",");			
			TPrizeGroupInfo prizeGroupInfo = new TPrizeGroupInfo();
			prizeGroupInfo.setPrizeId(prizeId);
			prizeGroupInfo.setGroupId(prizeGroupInfos[0]);
			prizeGroupInfo.setPrizeNumber(new BigDecimal(prizeGroupInfos[1]));
			prizeGroupInfo.setPrizeWinner(BigDecimal.ZERO);
			prizeGroupInfoRepository.insert(prizeGroupInfo);
		}	
		
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
