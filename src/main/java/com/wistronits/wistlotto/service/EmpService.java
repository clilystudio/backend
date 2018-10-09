package com.wistronits.wistlotto.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.wistronits.wistlotto.model.EmpInfoModel;
import com.wistronits.wistlotto.model.tables.TEmpInfo;
import com.wistronits.wistlotto.framework.util.ConverterUtil;
import com.wistronits.wistlotto.framework.util.CsvUtil;
import com.wistronits.wistlotto.repository.tables.TEmpInfoRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class EmpService {
	@Inject
	private TEmpInfoRepository empInfoRespository;
	
	
	// 获取全部奖项列表
	public List<TEmpInfo> getEmpList() {
		return null;
	}
	// 获取全部奖项列表
	public List<TEmpInfo> getLottoEmp(final String prizeId) {
		return null;
	}
	
	public boolean clearAll() {
		return true;
	}
	
	public int uploadAll(MultipartFile file) {
		BeanListProcessor<EmpInfoModel> processor = new BeanListProcessor<EmpInfoModel>(EmpInfoModel.class);
		try {
			CsvUtil.loadFile(file, processor);
			List<EmpInfoModel> empAllInfo = processor.getBeans();
			for (EmpInfoModel empInfoModel : empAllInfo) {
				TEmpInfo empInfo = ConverterUtil.convertObject(empInfoModel, TEmpInfo.class);
				empInfoRespository.insert(empInfo);
			}
			return empAllInfo.size();
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
			return 0;
		}
	}
}
