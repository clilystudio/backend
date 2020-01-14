package com.wistronits.wistlotto.model;

import java.io.Serializable;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 中奖员工信息
 * 
 * @author 盛广立 2019年1月11日
 */
@Data
@Component
public class PrizeEmpInfoModel implements Serializable {

	private static final long serialVersionUID = 2271052447809915495L;
	
	// 员工ID
	private String empId;

	// 抽奖组ID
	private String groupId;
}
