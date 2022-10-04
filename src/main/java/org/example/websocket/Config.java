package org.example.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class Config implements WebSocketConfigurer {
    @Autowired
    private Handler handler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(handler,"/channel/{id}")
                .setAllowedOrigins("*");
    }
}