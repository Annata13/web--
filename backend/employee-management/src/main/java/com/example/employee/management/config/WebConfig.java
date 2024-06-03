package com.example.employee.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Аннотация указывает, что это класс конфигурации Spring
public class WebConfig {

    @Bean // Аннотация указывает, что метод возвращает бин, который будет управляться контейнером Spring
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            // Метод для настройки правил CORS
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Указываем, для каких URL-путей будет применяться правило CORS (все URL, начинающиеся с /api)
                        .allowedOrigins("http://localhost:5173") // Указываем, какие источники разрешены (фронтенд-приложение, работающее на localhost:5173)
                        .allowedMethods("GET", "POST", "PUT", "DELETE"); // Указываем, какие HTTP методы разрешены
            }
        };
    }
}