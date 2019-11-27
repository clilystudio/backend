package com.wistronits.wistlotto.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
		log.debug("获取奖项一览");
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
		if (Objects.isNull(prizeInfo)) {
			return new PrizeInfoModel();
		}
		PrizeInfoModel prizeInfoModel = ConverterUtil.convertObject(prizeInfo, PrizeInfoModel.class);

		TPrizeGroupInfoCriteria example = new TPrizeGroupInfoCriteria();
		example.createCriteria().andPrizeIdEqualTo(prizeInfo.getPrizeId());
		List<TPrizeGroupInfo> prizeGroupInfoList = prizeGroupInfoRepository.selectByExample(example);
		StringBuilder sb = new StringBuilder();
		BigDecimal prizeSumNumber = BigDecimal.ZERO;
		BigDecimal prizeSumWinner = BigDecimal.ZERO;
		for (TPrizeGroupInfo prizeGroupInfo : prizeGroupInfoList) {
			BigDecimal prizeNumber = prizeGroupInfo.getPrizeNumber();
			BigDecimal prizeWinner = prizeGroupInfo.getPrizeWinner();
			prizeSumNumber = prizeSumNumber.add(prizeNumber);
			prizeSumWinner = prizeSumWinner.add(prizeWinner);
			sb.append(prizeGroupInfo.getGroupId()).append(CommonConst.Delimiter.ITEM)
					.append(prizeNumber.toPlainString()).append(CommonConst.Delimiter.ITEM)
					.append(prizeWinner.toPlainString()).append(CommonConst.Delimiter.GROUP);
		}
		String groupLimit = sb.toString();
		if (StringUtils.isNotEmpty(groupLimit)) {
			groupLimit = StringUtils.left(groupLimit, groupLimit.length() - 1);
		}
		prizeInfoModel.setGroupLimit(groupLimit);
		prizeInfoModel.setPrizeNumber(prizeSumNumber);
		prizeInfoModel.setPrizeWinner(prizeSumWinner);
		return prizeInfoModel;
	}

	/**
	 * 批量导入奖项数据
	 * 
	 * @param file 奖项数据
	 * @return 导入结果
	 */
	public CommonResultModel uploadAll(MultipartFile file, boolean isClearAll) {
		log.debug("批量导入奖项数据");
		if (isClearAll) {
			// 清除全部奖项
			prizeGroupInfoRepository.deleteByExample(new TPrizeGroupInfoCriteria());
			prizeInfoRepository.deleteByExample(new TPrizeInfoCriteria());
		}
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
		log.debug("取得指定ID奖项信息");
		TPrizeInfoKey key = new TPrizeInfoKey();
		key.setPrizeId(prizeId);
		TPrizeInfo prizeInfo = prizeInfoRepository.selectByPrimaryKey(key);
		if (Objects.isNull(prizeInfo)) {
			return null;
		}
		return getPrizeInfoModel(prizeInfo);
	}

	/**
	 * 取得可抽选奖项
	 * 
	 * @return 奖项信息
	 */
	public PrizeInfoModel getLottoPrize() {
		List<TPrizeInfo> prizeList = lottoRepository.getLottoPrize();
		TPrizeInfo prizeInfo = null;
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
		log.debug("删除指定ID奖项信息");
		CommonResultModel result = new CommonResultModel();
		for (String prizeId : prizeIds) {
			// 删除奖项分组信息
			TPrizeGroupInfoCriteria example = new TPrizeGroupInfoCriteria();
			example.createCriteria().andPrizeIdEqualTo(prizeId);
			prizeGroupInfoRepository.deleteByExample(example);

			// 删除奖项信息
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
		log.debug("添加奖项");
		CommonResultModel result = new CommonResultModel();
		String prizeId = prizeInfoModel.getPrizeId();
		if (Objects.nonNull(getPrize(prizeId))) {
			result.setCode(ResultCode.FAILED);
			result.setMessage(new SystemMessage(MessageId.MBE1007).getMessage());
			return result;
		}
		// 添加奖项
		TPrizeInfo prizeInfo = ConverterUtil.convertObject(prizeInfoModel, TPrizeInfo.class);
		prizeInfo.setPrizeStatus(CommonConst.PrizeStatus.READYING);
		prizeInfoRepository.insert(prizeInfo);

		// 添加奖项分组信息
		TPrizeGroupInfoCriteria example = new TPrizeGroupInfoCriteria();
		example.createCriteria().andPrizeIdEqualTo(prizeId);
		prizeGroupInfoRepository.deleteByExample(example);

		String groupLimit = prizeInfoModel.getGroupLimit();
		String[] prizeGroups = groupLimit.split(CommonConst.Delimiter.GROUP);
		for (String prizeGroup : prizeGroups) {
			String[] prizeGroupInfos = prizeGroup.split(CommonConst.Delimiter.ITEM);
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
	public CommonResultModel editPrize(PrizeInfoModel prizeInfoModel) {
		log.debug("编辑奖项");
		CommonResultModel result = new CommonResultModel();
		
		// 编辑奖项
		TPrizeInfo prizeInfo = ConverterUtil.convertObject(prizeInfoModel, TPrizeInfo.class);
		prizeInfo.setPrizeStatus(CommonConst.PrizeStatus.READYING);
		if (prizeInfoRepository.updateByPrimaryKey(prizeInfo) == 0) {
			result.setCode(ResultCode.FAILED);
			result.setMessage(new SystemMessage(MessageId.MBE1008).getMessage());
			return result;
		}

		// 重新设置奖项分组信息
		TPrizeGroupInfoCriteria example = new TPrizeGroupInfoCriteria();
		String prizeId = prizeInfoModel.getPrizeId();
		example.createCriteria().andPrizeIdEqualTo(prizeId);
		prizeGroupInfoRepository.deleteByExample(example);

		String groupLimit = prizeInfoModel.getGroupLimit();
		String[] prizeGroups = groupLimit.split(CommonConst.Delimiter.GROUP);
		for (String prizeGroup : prizeGroups) {
			String[] prizeGroupInfos = prizeGroup.split(CommonConst.Delimiter.ITEM);
			TPrizeGroupInfo prizeGroupInfo = new TPrizeGroupInfo();
			prizeGroupInfo.setPrizeId(prizeId);
			prizeGroupInfo.setGroupId(prizeGroupInfos[0]);
			prizeGroupInfo.setPrizeNumber(new BigDecimal(prizeGroupInfos[1]));
			prizeGroupInfo.setPrizeWinner(BigDecimal.ZERO);
			prizeGroupInfoRepository.insert(prizeGroupInfo);
		}
		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1007).getMessage());
		return result;
	}
}
