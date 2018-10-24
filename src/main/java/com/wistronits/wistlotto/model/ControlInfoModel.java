package com.wistronits.wistlotto.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ControlInfoModel implements Serializable {

	private static final long serialVersionUID = 8356420659067929244L;
	
    private String prizeId;
    
	private BigDecimal prizeStatus;
	
	private BigDecimal prizePerson;

	private BigDecimal prizeCommand;
}
