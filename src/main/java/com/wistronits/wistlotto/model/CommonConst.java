package com.wistronits.wistlotto.model;

/**
 * 常量定义类
 * 
 * @author 盛广立 2019年1月11日
 */
public final class CommonConst {

	/** 抽奖控制命令 */
	public static final class LottoControl {
		public static final String READY = "0";
		public static final String START = "1";
		public static final String STOP = "2";
	}

	/** 员工中奖状态 */
	public static final class PrizeFlag {
		public static final String NONE = "0";
		public static final String WIN = "1";
		public static final String GIVEUP = "9";
	}

	/** 奖项状态 */
	public static final class PrizeStatus {
		public static final String READYING = "1";
		public static final String READIED = "2";
		public static final String STARTTING = "3";
		public static final String STARTTED = "4";
		public static final String STOPPING = "5";
		public static final String STOPPED = "6";
	}

	/** 返回结果代码 */
	public static final class ResultCode {
		public static final String SUCCESS = "0";
		public static final String FAILED = "1";
	}

	/** 系统配置KEY */
	public static final class SysKey {
		public static final String KEY_LOTTO = "lotto";
		public static final String KEY_WINNER = "winner";
	}
	
	/** 不限定抽奖组 */
	public static final String UNLIMIT_GROUP = "000000";
	
	public static final String END_POINT = "/lotto-stomp-endpoint";
	
	public static final String STATUS_BROADCAST = "/status/broadcast";
}
