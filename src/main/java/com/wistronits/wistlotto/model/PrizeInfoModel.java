package com.wistronits.wistlotto.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.univocity.parsers.annotations.Parsed;

import lombok.Data;

/**
 * 奖项信息
 * 
 * @author 盛广立 2019年1月11日
 */
@Data
@Component
public class PrizeInfoModel implements Serializable {

	private static final long serialVersionUID = 9105649530988543585L;

	// 奖项ID
	@Parsed(index = 0)
	private String prizeId;

	// 奖项名称
	@Parsed(index = 1)
	private String prizeName;

	// 奖项描述
	@Parsed(index = 2)
	private String prizeDesc;

	// 抽取顺序
	@Parsed(index = 3)
	private BigDecimal prizeOrder;

	// 奖品数量
	@Parsed(index = 4)
	private BigDecimal prizeNumber;

	// 已中奖数量
	@Parsed(index = 5)
	private BigDecimal prizeWinner;

	// 限定部门ID
	@Parsed(index = 6)
	private String deptId;

	// 允许重复中奖
	@Parsed(index = 7)
	private String prizeMulti;

	// 奖项状态
	private String prizeStatus;
}
