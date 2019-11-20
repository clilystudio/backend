package com.wistronits.wistlotto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制类
 * 
 * @author 盛广立 2019年1月11日
 */
@Controller
public class PageController {

	/**
	 * 抽奖控制页（手机端显示）
	 * 
	 * @return 页面路径
	 */
	@RequestMapping("/control")
	public String getControl() {
		return "control";
	}

	/**
	 * 抽奖显示页（大屏幕显示）
	 * 
	 * @return 页面路径
	 */
	@RequestMapping("/lotto")
	public String getLotto() {
		return "lotto";
	}

	/**
	 * 后台管理页（PC端显示）
	 * 
	 * @return 页面路径
	 */
	@RequestMapping("/manage")
	public String getManage() {
		return "manage";
	}
}
