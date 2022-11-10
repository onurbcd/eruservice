package com.onurbcd.eruservice.property;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class WebProperties {

    private String crossOriginPathPattern;

    private Set<String> allowedOrigins;
}
