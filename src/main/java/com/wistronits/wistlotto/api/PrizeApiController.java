package com.wistronits.wistlotto.api;

import java.util.List;
import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wistronits.wistlotto.model.CommonResultModel;
import com.wistronits.wistlotto.model.PrizeInfoModel;
import com.wistronits.wistlotto.model.UploadCSVModel;
import com.wistronits.wistlotto.model.tables.TPrizeInfo;
import com.wistronits.wistlotto.service.PrizeService;

/**
 * 奖项信息相关API
 * 
 * @author 盛广立 2019年1月11日
 */
@RestController
@RequestMapping(value = "/api/prize")
public class PrizeApiController {

	@Inject
	private PrizeService prizeService;

	/**
	 * 获取奖项一览
	 * 
	 * @return 奖项一览
	 */
	@GetMapping("/list")
	public List<PrizeInfoModel> getPrizeList() {
		// 处理"/prizes/list"的GET请求，用来获取奖项列表
		return prizeService.getPrizeList();
	}

	/**
	 * 获取指定ID的奖项信息
	 * 
	 * @param prizeId 奖项ID
	 * @return 奖项信息
	 */
	@GetMapping("/get/{prizeId}")
	public PrizeInfoModel getPrize(@PathVariable String prizeId) {
		return prizeService.getPrize(prizeId);
	}

	/**
	 * 添加奖项
	 * 
	 * @param prizeInfoModel 奖项信息
	 * @return 添加结果
	 */
	@PostMapping("/add")
	public CommonResultModel addPrize(@RequestBody PrizeInfoModel prizeInfoModel) {
		// 处理"/prize/add"的POST请求，用来添加奖项
		return prizeService.addPrize(prizeInfoModel);
	}

	/**
	 * 删除指定ID的奖项
	 * 
	 * @param prizeIds 奖项ID数组
	 * @return 删除结果
	 */
	@PostMapping("/delete")
	public CommonResultModel deletePrizes(@RequestBody String[] prizeIds) {
		return prizeService.deletePrizes(prizeIds);
	}

	/**
	 * 编辑奖项
	 * 
	 * @param prizeInfo 奖项信息
	 * @return 编辑结果
	 */
	@PostMapping("/edit")
	public CommonResultModel editPrize(@RequestBody TPrizeInfo prizeInfo) {
		return prizeService.editPrize(prizeInfo);
	}

	/**
	 * 批量导入奖项数据
	 * 
	 * @param model 奖项数据
	 * @return 导入结果
	 */
	@PostMapping("/upload")
	public CommonResultModel uploadAll(@ModelAttribute UploadCSVModel model) {
		CommonResultModel result = new CommonResultModel();
		if (model.isClearFlag()) {
			prizeService.clearAll();
		}
		result = prizeService.uploadAll(model.getFile());
		return result;
	}
}
