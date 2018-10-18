package com.wistronits.wistlotto.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.wistronits.wistlotto.model.CommonResultModel;
import com.wistronits.wistlotto.model.EmpInfoModel;
import com.wistronits.wistlotto.model.tables.TEmpInfo;
import com.wistronits.wistlotto.model.tables.TEmpInfoCriteria;
import com.wistronits.wistlotto.model.tables.TEmpInfoKey;
import com.wistronits.wistlotto.framework.exception.SystemException;
import com.wistronits.wistlotto.framework.message.MessageId;
import com.wistronits.wistlotto.framework.message.SystemMessage;
import com.wistronits.wistlotto.framework.util.ConverterUtil;
import com.wistronits.wistlotto.framework.util.CsvUtil;
import com.wistronits.wistlotto.repository.LottoRepository;
import com.wistronits.wistlotto.repository.tables.TEmpInfoRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class EmpService {
	@Inject
	private TEmpInfoRepository empInfoRepository;
	
	@Inject
	private LottoRepository lottoRepository;
	
	
	// 获取全部奖项列表
	public List<EmpInfoModel> getEmpList() {
		return lottoRepository.getEmpList();
	}
	
	// 获取全部奖项列表
	public List<TEmpInfo> getLottoEmp(final String prizeId) {
		return null;
	}
	
	public boolean clearAll() {
		log.debug("###Clear All");
		TEmpInfoCriteria example = new TEmpInfoCriteria();
		int count = empInfoRepository.deleteByExample(example);
		log.debug("delete emp:" + count);
		return true;
	}
	
	public CommonResultModel uploadAll(MultipartFile file) {
		BeanListProcessor<EmpInfoModel> processor = new BeanListProcessor<EmpInfoModel>(EmpInfoModel.class);
		CommonResultModel result = new CommonResultModel();
		try {
			CsvUtil.loadFile(file, processor);
			List<EmpInfoModel> empAllInfo = processor.getBeans();
			for (EmpInfoModel empInfoModel : empAllInfo) {
				TEmpInfo empInfo = ConverterUtil.convertObject(empInfoModel, TEmpInfo.class);
				empInfoRepository.insert(empInfo);
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
	
	public CommonResultModel deleteEmps(String[] empIds) {
		CommonResultModel result = new CommonResultModel();
		for (String empId : empIds) {			
			TEmpInfoKey key = new TEmpInfoKey();
			key.setEmpId(empId);
			empInfoRepository.deleteByPrimaryKey(key);
		}
		result.setCode(0);
		result.setMessage(new SystemMessage(MessageId.MBI1002).getMessage());
		return result;
	}
	
	public CommonResultModel addEmp(TEmpInfo empInfo) {
		CommonResultModel result = new CommonResultModel();
		TEmpInfoKey key = new TEmpInfoKey();
		key.setEmpId(empInfo.getEmpId());
		if (empInfoRepository.selectByPrimaryKey(empInfo) != null) {
			result.setCode(1);
			result.setMessage(new SystemMessage(MessageId.MBE1005).getMessage());
			return result;
		}
		empInfoRepository.insert(empInfo);
		result.setCode(0);
		result.setMessage(new SystemMessage(MessageId.MBI1003).getMessage());
		return result;
	}
	
	public CommonResultModel editEmp(TEmpInfo empInfo) {
		CommonResultModel result = new CommonResultModel();
		if (empInfoRepository.updateByPrimaryKey(empInfo) == 0) {
			result.setCode(1);
			result.setMessage(new SystemMessage(MessageId.MBE1006).getMessage());
			return result;
		}
		result.setCode(0);
		result.setMessage(new SystemMessage(MessageId.MBI1004).getMessage());
		return result;
	}
}
