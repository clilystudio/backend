package com.wistronits.wistlotto.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 返回结果
 * 
 * @author 盛广立 2019年1月11日
 */
@Data
@Component
public class CommonResultModel implements Serializable {

	private static final long serialVersionUID = 477647208344054370L;

	// 返回代码
	private String code;

	// 返回消息
	private String message;
}
