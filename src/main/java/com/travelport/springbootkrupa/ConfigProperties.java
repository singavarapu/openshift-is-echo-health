package com.travelport.springbootkrupa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:healthconfig.properties")
public class ConfigProperties {

    @Value("${project.version}")
    public Object appVersion;

    @Bean
    public Object getApplicationVersion() {
        return appVersion;
    }
}
