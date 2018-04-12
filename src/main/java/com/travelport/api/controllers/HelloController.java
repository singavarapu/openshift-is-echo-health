package com.travelport.api.controllers;
 
import com.travelport.api.services.GreetingService;
import com.travelport.api.services.HealthUtil;
import com.travelport.soa.lib.common.technical.logging.client.EnterpriseLoggingLogManager;
import com.travelport.soa.lib.common.technical.logging.client.EnterpriseLoggingLogger;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class HelloController {
 
    private static final EnterpriseLoggingLogger elogger =
            EnterpriseLoggingLogManager.getEnterpriseLoggingLogger(HelloController.class);
 
    @ApiOperation(value = "Get a default GreetingService")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        elogger.log(HealthUtil.sendToELog("returning from sayHello()"), EnterpriseLoggingLogger.Format.JSON);
        return "Hello Friend!";
    }
 
    @ApiOperation(value = "Get a GreetingService Rest response", response = GreetingService.class)
    @RequestMapping(value = "/greetings", method = RequestMethod.GET)
    public GreetingService greeting(
            @RequestParam(value = "greeting", defaultValue = "Hello") String greeting,
            @RequestParam(value = "message", defaultValue = "No Name") String message,
            @RequestParam(value = "happy", defaultValue = "true") boolean happy) {
        elogger.log(HealthUtil.sendToELog("returning a new GreetingService"), EnterpriseLoggingLogger.Format.JSON);
        return new GreetingService(greeting, message, happy);
    }
}
