package com.wistronits.wistlotto.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wistronits.wistlotto.model.tables.TPrizeInfo;
import com.wistronits.wistlotto.service.PrizeService;

@RestController
@RequestMapping(value = "/prizes")
public class PrizeController {
	
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
}
