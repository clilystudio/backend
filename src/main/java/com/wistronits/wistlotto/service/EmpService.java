package com.wistronits.wistlotto.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.wistronits.wistlotto.model.CommonConst.ResultCode;
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

/**
 * 员工信息服务
 * 
 * @author 盛广立 2019年1月11日
 */
@Service
@Slf4j
public class EmpService {

	@Inject
	private TEmpInfoRepository empInfoRepository;

	@Inject
	private LottoRepository lottoRepository;

	/**
	 * 获取员工一览
	 * 
	 * @return 员工一览
	 */
	public List<EmpInfoModel> getEmpList() {
		log.debug("###getEmpList");
		return lottoRepository.getEmpList();
	}

	/**
	 * 清除全部员工数据
	 */
	public void clearAll() {
		log.debug("###clearAll");
		empInfoRepository.deleteByExample(new TEmpInfoCriteria());
	}

	/**
	 * 批量导入员工数据
	 */
	public CommonResultModel uploadAll(MultipartFile file) {
		log.debug("###uploadAll");
		BeanListProcessor<EmpInfoModel> processor = new BeanListProcessor<EmpInfoModel>(EmpInfoModel.class);
		CommonResultModel result = new CommonResultModel();
		try {
			CsvUtil.loadFile(file, processor);
			List<EmpInfoModel> empAllInfo = processor.getBeans();
			for (EmpInfoModel empInfoModel : empAllInfo) {
				TEmpInfo empInfo = ConverterUtil.convertObject(empInfoModel, TEmpInfo.class);
				empInfoRepository.insert(empInfo);
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
	 * 删除指定ID的员工数据
	 * 
	 * @param empIds 员工ID数组
	 * @return 删除结果
	 */
	public CommonResultModel deleteEmps(String[] empIds) {
		CommonResultModel result = new CommonResultModel();
		for (String empId : empIds) {
			TEmpInfoKey key = new TEmpInfoKey();
			key.setEmpId(empId);
			empInfoRepository.deleteByPrimaryKey(key);
		}
		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1002).getMessage());
		return result;
	}

	/**
	 * 添加员工数据
	 * 
	 * @param empInfo 员工信息
	 * @return 添加结果
	 */
	public CommonResultModel addEmp(TEmpInfo empInfo) {
		CommonResultModel result = new CommonResultModel();
		TEmpInfoKey key = new TEmpInfoKey();
		key.setEmpId(empInfo.getEmpId());
		if (empInfoRepository.selectByPrimaryKey(empInfo) != null) {
			result.setCode(ResultCode.FAILED);
			result.setMessage(new SystemMessage(MessageId.MBE1005).getMessage());
			return result;
		}
		empInfoRepository.insert(empInfo);
		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1003).getMessage());
		return result;
	}

	/**
	 * 编辑员工数据
	 * 
	 * @param empInfo 员工数据
	 * @return 编辑结果
	 */
	public CommonResultModel editEmp(TEmpInfo empInfo) {
		CommonResultModel result = new CommonResultModel();
		if (empInfoRepository.updateByPrimaryKey(empInfo) == 0) {
			result.setCode(ResultCode.FAILED);
			result.setMessage(new SystemMessage(MessageId.MBE1006).getMessage());
			return result;
		}
		result.setCode(ResultCode.SUCCESS);
		result.setMessage(new SystemMessage(MessageId.MBI1004).getMessage());
		return result;
	}
}
