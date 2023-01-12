package com.onurbcd.eruservice.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("admin")
@Configuration("adminProperties")
@Getter
@Setter
public class AdminProperties {

    private CryptoProperties crypto;

    private WebProperties web;

    private Short minYear;

    private String storagePath;
}
