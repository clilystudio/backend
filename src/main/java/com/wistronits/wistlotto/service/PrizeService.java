package com.wistronits.wistlotto.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.wistronits.wistlotto.framework.exception.SystemException;
import com.wistronits.wistlotto.framework.message.MessageId;
import com.wistronits.wistlotto.framework.message.SystemMessage;
import com.wistronits.wistlotto.framework.util.ConverterUtil;
import com.wistronits.wistlotto.framework.util.CsvUtil;
import com.wistronits.wistlotto.model.CommonResultModel;
import com.wistronits.wistlotto.model.PrizeInfoModel;
import com.wistronits.wistlotto.model.tables.TPrizeInfo;
import com.wistronits.wistlotto.model.tables.TPrizeInfoKey;
import com.wistronits.wistlotto.model.tables.TPrizeInfoCriteria;
import com.wistronits.wistlotto.repository.tables.TPrizeInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrizeService {
	@Inject
	private TPrizeInfoRepository prizeInfoRepository;

	// 获取全部奖项列表
	public List<TPrizeInfo> getPrizeList() {
		TPrizeInfoCriteria example = new TPrizeInfoCriteria();
		example.setOrderByClause("prize_order asc");
		return prizeInfoRepository.selectByExample(example);
	}
	
	public boolean clearAll() {
		log.debug("###Clear All");
		TPrizeInfoCriteria example = new TPrizeInfoCriteria();
		int count = prizeInfoRepository.deleteByExample(example);
		log.debug("delete prize:" + count);
		return true;
	}
	
	public CommonResultModel uploadAll(MultipartFile file) {
		BeanListProcessor<PrizeInfoModel> processor = new BeanListProcessor<PrizeInfoModel>(PrizeInfoModel.class);
		CommonResultModel result = new CommonResultModel();
		try {
			CsvUtil.loadFile(file, processor);
			List<PrizeInfoModel> prizeAllInfo = processor.getBeans();
			for (PrizeInfoModel prizeInfoModel : prizeAllInfo) {
				TPrizeInfo prizeInfo = ConverterUtil.convertObject(prizeInfoModel, TPrizeInfo.class);
				prizeInfoRepository.insert(prizeInfo);
			}
			result.setCode(0);
			result.setMessage(new SystemMessage(MessageId.MBI1001).getMessage());
		} catch (SystemException e) {
			log.error(e.getLocalizedMessage());
			result.setCode(1);
			result.setMessage(e.getMessages().get(0).getMessage());
		}
		return result;
	}
	
	// 获取待抽奖奖项
	public TPrizeInfo getLottoPrize() {
		return null;
	}
	
	public CommonResultModel deletePrizes(String[] prizeIds) {
		CommonResultModel result = new CommonResultModel();
		for (String prizeId : prizeIds) {			
			TPrizeInfoKey key = new TPrizeInfoKey();
			key.setPrizeId(prizeId);
			prizeInfoRepository.deleteByPrimaryKey(key);
		}
		result.setCode(0);
		result.setMessage(new SystemMessage(MessageId.MBI1002).getMessage());
		return result;
	}
	
	public CommonResultModel addPrize(TPrizeInfo prizeInfo) {
		CommonResultModel result = new CommonResultModel();
		TPrizeInfoKey key = new TPrizeInfoKey();
		key.setPrizeId(prizeInfo.getPrizeId());
		if (prizeInfoRepository.selectByPrimaryKey(prizeInfo) != null) {
			result.setCode(1);
			result.setMessage(new SystemMessage(MessageId.MBE1005).getMessage());
			return result;
		}
		prizeInfoRepository.insert(prizeInfo);
		result.setCode(0);
		result.setMessage(new SystemMessage(MessageId.MBI1003).getMessage());
		return result;
	}
	
	public CommonResultModel editPrize(TPrizeInfo prizeInfo) {
		log.debug("editPrize:" + prizeInfo);
		CommonResultModel result = new CommonResultModel();
		if (prizeInfoRepository.updateByPrimaryKey(prizeInfo) == 0) {
			result.setCode(1);
			result.setMessage(new SystemMessage(MessageId.MBE1006).getMessage());
			return result;
		}
		result.setCode(0);
		result.setMessage(new SystemMessage(MessageId.MBI1004).getMessage());
		return result;
	}
}
