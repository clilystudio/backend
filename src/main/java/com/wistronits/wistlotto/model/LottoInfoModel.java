package com.wistronits.wistlotto.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wistronits.wistlotto.model.tables.TEmpInfo;

import lombok.Data;

/**
 * 中奖信息
 * 
 * @author 盛广立 2019年1月11日
 */
@Data
@Component
public class LottoInfoModel implements Serializable {

	private static final long serialVersionUID = 8356420659067929244L;

	// 奖项ID
	private String prizeId;

	// 中奖员工
	private List<TEmpInfo> empList;
}
