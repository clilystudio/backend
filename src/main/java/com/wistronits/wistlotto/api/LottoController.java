package com.wistronits.wistlotto.api;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.wistronits.wistlotto.framework.util.ConverterUtil;
import com.wistronits.wistlotto.model.CommonConst;
import com.wistronits.wistlotto.model.ControlInfoModel;

@Controller
public class LottoController {
	
    @Inject
	private SimpMessagingTemplate template;
    
    @MessageMapping("/broadcast")
    public void broadcastStatus(String message) {
    	try {
			ControlInfoModel controlInfo = ConverterUtil.convertFromJSONToApp(message, ControlInfoModel.class);
			if (controlInfo.getPrizeCommand() == CommonConst.COMMAND_START) {
				
			}
	    	this.template.convertAndSend("/status", message);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
