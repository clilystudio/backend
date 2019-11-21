package com.wistronits.wistlotto.api;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wistronits.wistlotto.framework.util.ConverterUtil;
import com.wistronits.wistlotto.model.CommonConst;
import com.wistronits.wistlotto.model.CommonResultModel;
import com.wistronits.wistlotto.model.ControlInfoModel;
import com.wistronits.wistlotto.model.UploadCSVModel;
import com.wistronits.wistlotto.model.tables.TPrizeInfo;
import com.wistronits.wistlotto.model.tables.TSysInfo;
import com.wistronits.wistlotto.service.PrizeService;
import com.wistronits.wistlotto.service.SysService;

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

	@Inject
	private SysService sysService;

	/**
	 * 获取奖项一览
	 * 
	 * @return 奖项一览
	 */
	@GetMapping("/list")
	public List<TPrizeInfo> getPrizeList() {
		// 处理"/prizes/list"的GET请求，用来获取奖项列表
		return prizeService.getPrizeList();
	}

	/**
	 * 获取指定ID的奖项信息
	 * 
	 * @param prizeId 奖项ID
	 * @return 奖项信息
	 */
	@GetMapping("/get/{prizeId}/{groupId}")
	public TPrizeInfo getPrize(@PathVariable String prizeId, @PathVariable String groupId) {
		return prizeService.getPrize(prizeId, groupId);
	}

	/**
	 * 添加奖项
	 * 
	 * @param prizeInfo 奖项信息
	 * @return 添加结果
	 */
	@PostMapping("/add")
	public CommonResultModel addPrize(@RequestBody TPrizeInfo prizeInfo) {
		// 处理"/prize/add"的POST请求，用来添加奖项
		return prizeService.addPrize(prizeInfo);
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

	/**
	 * 获取抽选奖项信息
	 */
	@GetMapping("/lotto")
	public TPrizeInfo getLottoPrize() {
		TPrizeInfo prizeInfo = new TPrizeInfo();
		// 读取系统配置，查找抽选控制信息
		TSysInfo sysInfo = sysService.getValue(CommonConst.SysKey.KEY_LOTTO);
		if (Objects.nonNull(sysInfo) && StringUtils.isNoneEmpty(sysInfo.getSysValue())) {
			ControlInfoModel controlInfo = ConverterUtil.convertFromJSONToApp(sysInfo.getSysValue(),
					ControlInfoModel.class);
			if (Objects.isNull(controlInfo)) {
				sysService.deleteValue(CommonConst.SysKey.KEY_LOTTO);
			} else {
				// 根据控制信息查找抽选奖项
				TPrizeInfo controlPrizeInfo = prizeService.getPrize(controlInfo.getPrizeId(), controlInfo.getGroupId());
				if (Objects.nonNull(controlPrizeInfo)) {
					// TODO 奖项状态检查待完善
					return controlPrizeInfo;
				}
			}
		}
		return prizeInfo;
	}
}
