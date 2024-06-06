package com.oaem.Config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class CORS {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
