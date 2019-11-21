package com.wistronits.wistlotto.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 控制信息
 * 
 * @author 盛广立 2019年1月11日
 */
@Data
@Component
public class ControlInfoModel implements Serializable {

	private static final long serialVersionUID = 8356420659067929244L;

	// 奖项ID
	private String prizeId;
	
	// 抽奖组ID
	private String groupId;

	// 奖项状态
	private String prizeStatus;

	// 抽选人数
	private BigDecimal prizePerson;

	// 控制指令
	private String command;
}
