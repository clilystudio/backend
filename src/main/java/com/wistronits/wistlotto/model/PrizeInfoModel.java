package com.wistronits.wistlotto.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.univocity.parsers.annotations.Parsed;

import lombok.Data;

@Data
@Component
public class PrizeInfoModel implements Serializable {

	private static final long serialVersionUID = 9105649530988543585L;
	
    @Parsed(index = 0)
    private String prizeId;

    @Parsed(index = 1)
	private String prizeName;
	
    @Parsed(index = 2)
	private String prizeDesc;
	
    @Parsed(index = 3)
	private BigDecimal prizeNumber;
	
    @Parsed(index = 4)
	private BigDecimal prizeWinner;
	
    @Parsed(index = 5)
	private String empDeptno;
	
    @Parsed(index = 6)
	private BigDecimal prizeMulti;
}
