package com.wistronits.wistlotto.model;

import java.math.BigDecimal;

public class CommonConst {
	public final static BigDecimal STATUS_RUNNING = BigDecimal.ZERO;
	public final static BigDecimal STATUS_READY = BigDecimal.ONE;
	public final static BigDecimal STATUS_FINNISH = new BigDecimal(2);
	
	public final static BigDecimal COMMAND_START = BigDecimal.ONE;
	public final static BigDecimal COMMAND_END = new BigDecimal(2);
}
