package com.wistronits.wistlotto.framework.message;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 系统消息
 * @author 盛广立
 * 2019年1月16日
 */
@Data
@Component
public class SystemMessage {

	private static MessageSource messageSource;

	@Inject
	public void setMessageSource(MessageSource messageSource) {
		SystemMessage.messageSource = messageSource;
	}

	private MessageId id;

	private Object[] args;

	@SuppressWarnings("unused")
	private SystemMessage() {
		// NOP for injection only.
	}

	public SystemMessage(MessageId id, Object... args) {
		this.id = id;
		this.args = args;
	}

	public String getMessage() {
		return messageSource.getMessage(id.toString(), args, Locale.CHINA);
	}

}
