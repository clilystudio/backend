package com.wistronits.wistlotto.api;

import javax.inject.Inject;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LottoController {
	
    @Inject
	private SimpMessagingTemplate template;
    
    @MessageMapping("/broadcast")
    public void broadcastStatus(String message) {
    	this.template.convertAndSend("/status", message);
    }
}
