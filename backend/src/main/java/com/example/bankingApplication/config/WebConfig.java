package com.example.bankingApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // This will allow all origins and all endpoints
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173")  
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")  
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true);
    }
    
    
}
