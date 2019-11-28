package com.wistronits.wistlotto.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 员工信息
 * 
 * @author 盛广立 2019年1月11日
 */
@Data
@Component
public class GroupInfoModel implements Serializable {

	private static final long serialVersionUID = 2271052447809915495L;

	// 抽奖组ID
	private String groupId;

	// 抽奖组员工数
	private BigDecimal groupCount;
}
