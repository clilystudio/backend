package com.wistronits.wistlotto.controller;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.wistronits.wistlotto.framework.util.ConverterUtil;
import com.wistronits.wistlotto.model.CommonConst;
import com.wistronits.wistlotto.model.ControlInfoModel;
import com.wistronits.wistlotto.model.tables.TSysInfo;
import com.wistronits.wistlotto.service.SysService;

import lombok.extern.slf4j.Slf4j;

/**
 * 抽奖控制
 * 
 * @author 盛广立
 * @version 1.0 2019年1月7日
 */
@Controller
@Slf4j
public class LottoController {

	@Inject
	private SysService sysService;

	/**
	 * 控制端发送抽奖控制命令
	 * 
	 * @param controlInfo 抽奖控制信息
	 * @return 抽奖状态
	 */
	@MessageMapping("/control")
	@SendTo("/status/control")
	public ControlInfoModel sendControl(ControlInfoModel controlInfo) {
		log.debug("sendControl：" + ConverterUtil.convertFromAppToJSON(controlInfo));
		ControlInfoModel lottoStatus = new ControlInfoModel();
		String command = controlInfo.getCommand();
		lottoStatus.setPrizeId(controlInfo.getPrizeId());
		lottoStatus.setPrizePerson(controlInfo.getPrizePerson());
		lottoStatus.setCommand(command);
		if (StringUtils.equals(command, CommonConst.LottoControl.READY)) {
			// 命令为准备抽奖时，设置状态为准备中
			lottoStatus.setPrizeStatus(CommonConst.PrizeStatus.READYING);
		} else if (StringUtils.equals(command, CommonConst.LottoControl.START)) {
			// 命令为启动抽奖时，设置状态为启动中
			lottoStatus.setPrizeStatus(CommonConst.PrizeStatus.STARTTING);
		} else if (StringUtils.equals(command, CommonConst.LottoControl.STOP)) {
			// 命令为停止抽奖时，设置状态为停止中
			lottoStatus.setPrizeStatus(CommonConst.PrizeStatus.STOPPING);
		}
		saveStatus(lottoStatus);
		return lottoStatus;
	}

	/**
	 * 抽奖端设置抽奖状态
	 * 
	 * @param controlInfo 抽奖控制信息
	 * @return 抽奖状态
	 */
	@MessageMapping("/change")
	@SendTo("/status/change")
	public ControlInfoModel changeStatus(ControlInfoModel controlInfo) {
		log.debug("changeStatus：" + ConverterUtil.convertFromAppToJSON(controlInfo));
		ControlInfoModel lottoStatus = new ControlInfoModel();
		String command = controlInfo.getCommand();
		String prizeId = controlInfo.getPrizeId();
		lottoStatus.setPrizeId(prizeId);
		lottoStatus.setPrizePerson(controlInfo.getPrizePerson());
		lottoStatus.setCommand(command);
		if (StringUtils.equals(command, CommonConst.LottoControl.READY)) {
			// 命令为准备抽奖时，设置状态为准备完成
			lottoStatus.setPrizeStatus(CommonConst.PrizeStatus.READIED);
		} else if (StringUtils.equals(command, CommonConst.LottoControl.START)) {
			// 命令为启动抽奖时，设置状态为已启动
			lottoStatus.setPrizeStatus(CommonConst.PrizeStatus.STARTTED);
		} else if (StringUtils.equals(command, CommonConst.LottoControl.STOP)) {
			// 命令为停止抽奖时，设置状态为已停止
			lottoStatus.setPrizeStatus(CommonConst.PrizeStatus.STOPPED);
		}
		saveStatus(lottoStatus);
		return lottoStatus;
	}

	/**
	 * 保存抽奖控制信息 防止网页关闭的情况，重新运行时可以恢复
	 * 当前版本没有使用，以后考虑
	 * 
	 * @param lottoStatus
	 */
	private void saveStatus(ControlInfoModel lottoStatus) {
		TSysInfo sysInfo = new TSysInfo();
		sysInfo.setSysKey(CommonConst.SysKey.KEY_LOTTO);
		sysInfo.setSysValue(ConverterUtil.convertFromAppToJSON(lottoStatus));
		sysService.setValue(sysInfo);
		log.debug("saveStatus：" + ConverterUtil.convertFromAppToJSON(lottoStatus));
	}
}
