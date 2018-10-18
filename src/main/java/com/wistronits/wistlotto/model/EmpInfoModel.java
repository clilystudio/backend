package com.wistronits.wistlotto.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.univocity.parsers.annotations.Parsed;

import lombok.Data;

@Data
@Component
public class EmpInfoModel implements Serializable {

	private static final long serialVersionUID = -7044276664169130328L;
	
    @Parsed(index = 0)
    private String empId;
    
    @Parsed(index = 1)
    private String empName;
    
    @Parsed(index = 2)
    private String deptId;
    
    @Parsed(index = 3)
    private BigDecimal empRate;
    
    @Parsed(index = 4)
    private BigDecimal prizeFlag;
    
    private String deptName;
}
