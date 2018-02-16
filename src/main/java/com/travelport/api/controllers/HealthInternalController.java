package com.travelport.api.controllers;

import com.travelport.springbootkrupa.ConfigProperties;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthInternalController {

    @Autowired
    private ConfigProperties configProperties;

    @Bean
    public ConfigProperties isHealthTemplate() {
        return new ConfigProperties();
    }

    @ApiOperation(value = "Get the application's latest version")
    @RequestMapping(value = "/appVersion", method = RequestMethod.GET)
    public Object getApplicationVersion() {
        return configProperties.getApplicationVersion();
    }

}
