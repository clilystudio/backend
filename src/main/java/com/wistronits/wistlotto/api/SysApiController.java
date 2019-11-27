package com.wistronits.wistlotto.api;

import java.util.List;
import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wistronits.wistlotto.model.CommonResultModel;
import com.wistronits.wistlotto.model.LottoInfoModel;
import com.wistronits.wistlotto.model.PrizeInfoModel;
import com.wistronits.wistlotto.model.WinnerInfoModel;
import com.wistronits.wistlotto.model.tables.TSysInfo;
import com.wistronits.wistlotto.service.SysService;

/**
 * 系统信息相关API
 * 
 * @author 盛广立 2019年1月11日
 */
@RestController
@RequestMapping(value = "/api/sys")
public class SysApiController {

	@Inject
	private SysService sysService;

	/**
	 * 获取指定Key的系统设置
	 * 
	 * @param sysKey 系统Key
	 * @return 系统设置
	 */
	@GetMapping("/get/{sysKey}")
	public TSysInfo getValue(@PathVariable String sysKey) {
		return sysService.getValue(sysKey);
	}

	/**
	 * 保存系统设置
	 * 
	 * @param sysInfo 系统设置
	 * @return 保存结果
	 */
	@PostMapping("/set")
	public CommonResultModel setValue(@RequestBody TSysInfo sysInfo) {
		return sysService.setValue(sysInfo);
	}

	/**
	 * 获取中奖信息一览
	 * 
	 * @return 中奖信息一览
	 */
	@GetMapping("/winner")
	public List<WinnerInfoModel> getWinner() {
		return sysService.getWinnerList();
	}

	/**
	 * 重置系统
	 * 
	 * @return 重置结果
	 */
	@PostMapping("/reset")
	public CommonResultModel resetSys() {
		return sysService.reset();
	}

	/**
	 * 设置中奖信息
	 * 
	 * @param lottoInfo 中奖信息
	 * @return 设置结果
	 */
	@PostMapping("/setlotto")
	public CommonResultModel setLotto(@RequestBody LottoInfoModel lottoInfo) {
		return sysService.setLotto(lottoInfo);
	}

	/**
	 * 移除中奖信息
	 * 
	 * @param winnerInfo 中奖信息
	 * @return 移除结果
	 */
	@PostMapping("/removelotto")
	public CommonResultModel removeLotto(@RequestBody WinnerInfoModel winnerInfo) {
		return sysService.removeWinner(winnerInfo);
	}

	/**
	 * 获取抽选奖项信息
	 */
	@GetMapping("/prize")
	public PrizeInfoModel getLottoPrize() {
		return sysService.getLottoPrize();
	}

	/**
	 * 刷新前台
	 * 
	 * @return 移除结果
	 */
	@PostMapping("/refreshfront")
	public CommonResultModel refreshFront() {
		return sysService.refreshFront();
	}
}
