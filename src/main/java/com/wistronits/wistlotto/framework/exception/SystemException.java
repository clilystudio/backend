package com.wistronits.wistlotto.framework.exception;

import java.util.ArrayList;
import java.util.List;

import com.wistronits.wistlotto.framework.message.MessageId;
import com.wistronits.wistlotto.framework.message.SystemMessage;

public class SystemException extends RuntimeException {

    /**
     * シリアルバージョン。
     */
    private static final long serialVersionUID = 2675145706532175756L;

    /**
     * メッセージのリスト。
     */
    private List<SystemMessage> messages;

    /**
     * 本来起きてはいけない例外をキャッチして、エラーにするときのコンストラクタ。
     *
     * @param cause スローされた例外
     * @param messageId メッセージID
     * @param args 動的にメッセージに表示する値。なければ null を指定。
     */
    public SystemException(Throwable cause, MessageId messageId, Object... args) {
        super(cause);
        this.messages = new ArrayList<SystemMessage>();
        this.messages.add(new SystemMessage(messageId, args));
    }

    /**
     * メッセージを返す。
     *
     * @return メッセージ
     */
    public List<SystemMessage> getMessages() {
        return messages;
    }
}
