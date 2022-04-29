package com.onurbcd.eruservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var corsRegistration = registry.addMapping("/**");
        corsRegistration.allowedOrigins("http://localhost:4200");
        corsRegistration.allowedMethods("*");
        corsRegistration.allowedHeaders("*");
    }
}
