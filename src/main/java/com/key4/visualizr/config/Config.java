package com.key4.visualizr.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

@Configuration
public class Config implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/partslogs")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers", "Access-Control-Allow-Origin")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true);
        registry.addMapping("/api/v1/errorsUpload")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers", "Access-Control-Allow-Origin")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true);
        registry.addMapping("/api/v1/pagerrlogs")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers", "Access-Control-Allow-Origin")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true);
        registry.addMapping("/sse-api")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers", "Access-Control-Allow-Origin")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true);
    }

}