package com.onurbcd.eruservice.config;

import com.onurbcd.eruservice.property.AdminProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AdminProperties config;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var corsRegistration = registry.addMapping(config.getWeb().getCrossOriginPathPattern());
        corsRegistration.allowedOrigins(config.getWeb().getAllowedOrigins().toArray(String[]::new));
        corsRegistration.allowedMethods(CorsConfiguration.ALL);
        corsRegistration.allowedHeaders(CorsConfiguration.ALL);
    }
}
