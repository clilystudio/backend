package com.wistronits.wistlotto.api;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wistronits.wistlotto.framework.message.MessageId;
import com.wistronits.wistlotto.framework.message.SystemMessage;
import com.wistronits.wistlotto.model.CommonResultModel;
import com.wistronits.wistlotto.model.UploadCSVModel;
import com.wistronits.wistlotto.model.tables.TEmpInfo;
import com.wistronits.wistlotto.service.EmpService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/emp")
@Slf4j
public class EmpApiController {
	
    @Inject
	private EmpService empService;
	
	@GetMapping("/list")
	public List<TEmpInfo> getList() {
		// 处理"/emp/list"的GET请求，用来获取员工列表
		return empService.getEmpList();
	}
	
	@GetMapping("/lotto/{prizeId}")
	public List<TEmpInfo> getLotto(@PathVariable String prizeId) {
		// 处理"/emp/lotto/{prizeId}"的GET请求，获取指定奖项ID的待抽奖员工列表
		return empService.getLottoEmp(prizeId);
	}
	
	@PostMapping("/upload")
	public CommonResultModel uploadAll(@ModelAttribute UploadCSVModel model) {
		CommonResultModel result = new CommonResultModel();
		if (model.isClearFlag()) {
			if (!empService.clearAll()) {
				result.setCode(2);
				result.setMessage(new SystemMessage(MessageId.MBE1004).getMessage());
				log.warn("清除员工表失败");
				return result;
			}
		}
		result = empService.uploadAll(model.getFile());
		return result;
	}
}