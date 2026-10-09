package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class JavaRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaRedisApplication.class, args);
    }
}

@RestController
class AppController {

    private final StringRedisTemplate redisTemplate;

    AppController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/")
    public String home() {
        return "Java Redis App is running!";
    }

    @GetMapping("/redis")
    public String redis() {
        redisTemplate.opsForValue().set("message", "Hello from Redis!");
        return redisTemplate.opsForValue().get("message");
    }
}
