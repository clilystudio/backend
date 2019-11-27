package com.wistronits.wistlotto.model;

/**
 * 常量定义类
 * 
 * @author 盛广立 2019年1月11日
 */
public final class CommonConst {

	/** 抽奖控制命令 */
	public static final class LottoControl {
		// 准备抽奖（显示奖项信息）
		public static final String READY = "0";
		// 开始抽奖（员工信息开始转动）
		public static final String START = "1";
		// 停止抽奖（显示中奖名单）
		public static final String STOP = "2";
	}

	/** 员工中奖状态 */
	public static final class PrizeFlag {
		// 没中奖
		public static final String NONE = "0";
		// 已中奖
		public static final String WIN = "1";
		// 已弃奖
		public static final String GIVEUP = "9";
	}

	/** 奖项状态 */
	public static final class PrizeStatus {
		// 准备中
		public static final String READYING = "1";
		// 准备完毕
		public static final String READIED = "2";
		// 抽奖启动中
		public static final String STARTTING = "3";
		// 抽奖启动完毕
		public static final String STARTTED = "4";
		// 抽奖停止中
		public static final String STOPPING = "5";
		// 抽奖停止完毕
		public static final String STOPPED = "6";
	}

	/** 返回结果代码 */
	public static final class ResultCode {
		// 成功
		public static final String SUCCESS = "0";
		// 失败
		public static final String FAILED = "1";
	}

	/** 系统配置KEY */
	public static final class SysKey {
		// 抽奖控制
		public static final String KEY_LOTTO = "lotto";
		// 获奖名单
		public static final String KEY_WINNER = "winner";
	}

	/** 不限定抽奖组 */
	public static final String UNLIMIT_GROUP = "000000";

	/** 分隔符 */
	public static final class Delimiter {
		// 组间分隔符
		public static final String GROUP = ";";
		// 项目间分隔符
		public static final String ITEM = "#";
	}

	/** WebSocket 的端点 */
	public static final String END_POINT = "/lotto-stomp-endpoint";

	/** WebSocket 的发送目标 */
	public static final String STATUS_BROADCAST = "/status/broadcast";
}
