package com.wistronits.wistlotto.framework.message;

public enum MessageId {
	/** 出现未知系统错误，请查看日志获取更多内容。 */
	MSE0001("MSE0001"),
	/** 是否确定要继续处理？ */
	MSW0001("MSW0001"),
	/** 处理结束。 */
	MSI0001("MSI0001"),
	/** 没有找到可抽选的奖项。 */
	MBE0001("MBE0001"),
	/** 确定{0}是要放弃中奖吗？ */
	MBW0001("MBW0001"),
	/** 奖项{0}共{1}次中奖机会，现在抽选{2}名中奖者。 */
	MBI0001("MBI0001"),
	/** 抽选结束。 */
	MBI0002("MBI0002");


    /** 消息ID。 */
    private String id;

    MessageId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return this.id;
    }
}