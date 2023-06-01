package com.example.dolbomi;

import com.example.dolbomi.repository.JdbcTemplateMessageRepository;
import com.example.dolbomi.repository.JdbcTemplateParentAccountRespository;
import com.example.dolbomi.repository.MessageRepository;
import com.example.dolbomi.repository.ParentAccountRespository;
import com.example.dolbomi.service.MessageService;
import com.example.dolbomi.service.TimelineService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.sql.DataSource;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final DataSource dataSource;


    public WebSocketConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(messageSocketHandler(), "/room")
                .setAllowedOrigins("*");
    }

    @Bean
    public org.springframework.web.socket.WebSocketHandler messageSocketHandler() {
        return new WebSocketHandler(messageService());
    }
    @Bean
    public MessageService messageService(){
        return new MessageService(messageRepository());
    }
    @Bean
    public MessageRepository messageRepository() {
        return new JdbcTemplateMessageRepository(dataSource);
    }
}
