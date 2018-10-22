package com.wistronits.wistlotto.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.univocity.parsers.annotations.Parsed;

import lombok.Data;

@Data
@Component
public class LottoInfoModel implements Serializable {

	private static final long serialVersionUID = 11512276583142114L;

    private String prizeId;

	private String prizeName;

	private BigDecimal prizeNumber;

	private BigDecimal prizeWinner;
	
	private BigDecimal lottoPerson;
	
	private BigDecimal lottoMax;
}
