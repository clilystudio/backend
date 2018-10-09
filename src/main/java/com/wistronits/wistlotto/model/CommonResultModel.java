package com.wistronits.wistlotto.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CommonResultModel {
	// 返回代码
	private int code;
	
	// 返回消息
	private String message;
}
