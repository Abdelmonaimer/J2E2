package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Adjust the mapping based on your API paths
                .allowedOrigins("http://localhost:4200")  // Replace with the actual origin of your Angular app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}

