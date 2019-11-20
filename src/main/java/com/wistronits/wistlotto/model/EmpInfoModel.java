package com.wistronits.wistlotto.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.univocity.parsers.annotations.Parsed;

import lombok.Data;

/**
 * 员工信息
 * 
 * @author 盛广立 2019年1月11日
 */
@Data
@Component
public class EmpInfoModel implements Serializable {

	private static final long serialVersionUID = -7044276664169130328L;

	// 员工ID
	@Parsed(index = 0)
	private String empId;

	// 员工姓名
	@Parsed(index = 1)
	private String empName;

	// 员工性别
	@Parsed(index = 2)
	private String empSex;

	// 部门ID
	@Parsed(index = 3)
	private String deptId;

	// 中奖权值
	@Parsed(index = 4)
	private BigDecimal empRate;

	// 中奖标识
	@Parsed(index = 5)
	private String prizeFlag;

	// 部门名称
	private String deptName;

	// 科室ID
	private String branchId;

	// 显示顺序
	private BigDecimal order;
}
