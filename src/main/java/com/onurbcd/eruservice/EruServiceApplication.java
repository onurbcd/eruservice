package com.onurbcd.eruservice;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = "com.onurbcd.eruservice")
@EnableJpaRepositories(basePackages = "com.onurbcd.eruservice.persistency.repository")
@EntityScan(basePackages = "com.onurbcd.eruservice.persistency.entity")
public class EruServiceApplication {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(EruServiceApplication.class, args);
    }
}
