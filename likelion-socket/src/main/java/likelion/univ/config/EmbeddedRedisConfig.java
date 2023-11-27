package com.websocket.chat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

// 내장 레디스 실행

@Slf4j
@Profile("local")
@Configuration
public class EmbeddedRedisConfig {

    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
//    @PostConstruct
//    public void redisServer() throws IOException {
//        redisServer = new RedisServer(redisPort);
//        redisServer.start();
@PostConstruct
public void start() {
    if (redisServer != null) {
        redisServer.stop();
    }
    redisTemplate.getConnectionFactory().getConnection().flushDb();//redis 데이터 지우기
    log.info("Starting embedded Redis server...");
    redisServer = RedisServer.builder()
            .port(6379)
            .setting("maxmemory 128M")
            .build();
    try {
        redisServer.start();
        log.info("Embedded Redis server started successfully");
    } catch (Exception e) {
        log.error("Failed to start embedded Redis server", e);
    }
}

    @PreDestroy
    public void stopRedis() {
        log.info("Stopping embedded Redis server...");
        if (redisServer != null) {
            redisServer.stop();
            log.info("Embedded Redis server stopped");
        }
    }

    public void destroy() throws Exception {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}