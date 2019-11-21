package com.wistronits.wistlotto.model;

import java.io.Serializable;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 中奖信息
 * 
 * @author 盛广立 2019年1月11日
 */
@Data
@Component
public class WinnerInfoModel implements Serializable {

	private static final long serialVersionUID = 8356420659067929244L;

	// 奖项ID
	private String prizeId;

	// 奖项名称
	private String prizeName;

	// 奖项描述
	private String prizeDesc;

	// 员工ID
	private String empId;
	
	// 抽奖组ID
	private String groupId;

	// 员工姓名
	private String empName;

	// 中奖时间
	private String winTime;
}
