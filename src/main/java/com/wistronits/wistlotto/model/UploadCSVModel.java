package com.wistronits.wistlotto.model;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Component
public class UploadCSVModel {
	private boolean clearFlag;

    private MultipartFile file;
}
