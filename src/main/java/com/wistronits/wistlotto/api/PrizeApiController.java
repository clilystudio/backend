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
	public List<PrizeInfoModel> getList() {
		return prizeService.getList();
	}

	/**
	 * 获取指定ID的奖项信息
	 * 
	 * @param prizeId 奖项ID
	 * @return 奖项信息
	 */
	@GetMapping("/get/{prizeId}")
	public PrizeInfoModel get(@PathVariable String prizeId) {
		return prizeService.get(prizeId);
	}

	/**
	 * 添加奖项
	 * 
	 * @param prizeInfoModel 奖项信息
	 * @return 添加结果
	 */
	@PostMapping("/add")
	public CommonResultModel add(@RequestBody PrizeInfoModel prizeInfoModel) {
		return prizeService.add(prizeInfoModel);
	}

	/**
	 * 删除指定ID的奖项
	 * 
	 * @param prizeIds 奖项ID数组
	 * @return 删除结果
	 */
	@PostMapping("/delete")
	public CommonResultModel delete(@RequestBody String[] prizeIds) {
		return prizeService.delete(prizeIds);
	}

	/**
	 * 编辑奖项
	 * 
	 * @param prizeInfo 奖项信息
	 * @return 编辑结果
	 */
	@PostMapping("/edit")
	public CommonResultModel edit(@RequestBody PrizeInfoModel prizeInfoModel) {
		return prizeService.edit(prizeInfoModel);
	}

	/**
	 * 批量导入奖项数据
	 * 
	 * @param model 奖项数据
	 * @return 导入结果
	 */
	@PostMapping("/upload")
	public CommonResultModel uploadAll(@ModelAttribute UploadCSVModel model) {
		return prizeService.uploadAll(model.getFile(), model.isClearAll());
	}

	/**
	 * 获取抽选奖项信息
	 */
	@GetMapping("/lotto")
	public PrizeInfoModel getLotto() {
		return prizeService.getLotto();
	}
}
