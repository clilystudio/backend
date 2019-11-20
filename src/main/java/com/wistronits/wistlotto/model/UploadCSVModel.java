package com.wistronits.wistlotto.model;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 数据文件上传信息
 * 
 * @author 盛广立 2019年1月11日
 */
@Data
@Component
public class UploadCSVModel {

	// 清除标识
	private boolean clearFlag;

	// 数据文件
	private MultipartFile file;
}
