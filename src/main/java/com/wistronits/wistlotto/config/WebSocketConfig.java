package com.wistronits.wistlotto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.wistronits.wistlotto.model.CommonConst;

/**
 * WebSocket配置类
 * 
 * @author 盛广立 2019年1月7日
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// addEndpoint 设置 WebSocket 的端点，客户端需要注册这个端点进行链接
		// setAllowedOrigins("*") 设置允许跨域访问
		// withSockJS 允许客户端利用 sockjs 进行浏览器兼容性处理
		registry.addEndpoint(CommonConst.END_POINT).setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// setApplicationDestinationPrefixes 设置客户端订阅消息的基础路径
		// enableSimpleBroker 设置服务器广播消息的基础路径
		registry.setApplicationDestinationPrefixes("/lotto").enableSimpleBroker("/status");
	}
}
