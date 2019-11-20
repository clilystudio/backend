package com.wistronits.wistlotto.framework.exception;

import java.util.ArrayList;
import java.util.List;

import com.wistronits.wistlotto.framework.message.MessageId;
import com.wistronits.wistlotto.framework.message.SystemMessage;

/**
 * 系统异常
 * 
 * @author 盛广立 2019年1月16日
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 2675145706532175756L;

	// 消息一览
	private List<SystemMessage> messages;

	/**
	 * 构造器
	 *
	 * @param cause     异常
	 * @param messageId 消息ID
	 * @param args      消息参数
	 */
	public SystemException(Throwable cause, MessageId messageId, Object... args) {
		super(cause);
		this.messages = new ArrayList<SystemMessage>();
		this.messages.add(new SystemMessage(messageId, args));
	}

	/**
	 * 错误消息取得
	 *
	 * @return 错误消息
	 */
	public List<SystemMessage> getMessages() {
		return messages;
	}
}
