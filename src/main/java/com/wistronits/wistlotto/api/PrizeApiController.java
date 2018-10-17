package com.wistronits.wistlotto.api;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wistronits.wistlotto.framework.message.MessageId;
import com.wistronits.wistlotto.framework.message.SystemMessage;
import com.wistronits.wistlotto.model.CommonResultModel;
import com.wistronits.wistlotto.model.UploadCSVModel;
import com.wistronits.wistlotto.model.tables.TPrizeInfo;
import com.wistronits.wistlotto.service.PrizeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/prize")
@Slf4j
public class PrizeApiController {
	
    @Inject
	private PrizeService prizeService;
	
    @GetMapping("/list")
	public List<TPrizeInfo> getPrizeList() {
		// 处理"/prizes/list"的GET请求，用来获取奖项列表
		return prizeService.getPrizeList();
	}
	
    @GetMapping("/lotto")
	public TPrizeInfo getLottoPrize() {
		// 处理"/prizes/lotto"的GET请求，获取待抽奖奖项
		// return prizeService.getLottoPrize();
		TPrizeInfo p = new TPrizeInfo();
		p.setPrizeId("LV1");
		p.setPrizeName("Level 1");
		return p;
	}
	
	@PostMapping("/add")
	public CommonResultModel addPrize(@RequestBody TPrizeInfo prizeInfo) {
		// 处理"/prize/add"的POST请求，用来添加奖项
		return prizeService.addPrize(prizeInfo);
	}
	
	@PostMapping("/delete")
	public CommonResultModel deletePrizes(@RequestBody String[] prizeIds) {
		// 处理"/prize/delete"的POST请求，用来删除指定ID的奖项
		return prizeService.deletePrizes(prizeIds);
	}
	
	@PostMapping("/edit")
	public CommonResultModel editPrize(@RequestBody TPrizeInfo prizeInfo) {
		// 处理"/prize/edit"的POST请求，用来编辑奖项
		return prizeService.editPrize(prizeInfo);
	}
	
	@PostMapping("/upload")
	public CommonResultModel uploadAll(@ModelAttribute UploadCSVModel model) {
		CommonResultModel result = new CommonResultModel();
		if (model.isClearFlag()) {
			if (!prizeService.clearAll()) {
				result.setCode(2);
				result.setMessage(new SystemMessage(MessageId.MBE1004).getMessage());
				log.warn("清除奖项表失败");
				return result;
			}
		}
		result = prizeService.uploadAll(model.getFile());
		return result;
	}
}
