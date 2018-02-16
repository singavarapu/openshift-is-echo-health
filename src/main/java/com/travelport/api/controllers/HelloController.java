package com.travelport.api.controllers;

import com.travelport.api.services.GreetingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @ApiOperation(value = "Get a default GreetingService")
  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String sayHello() {
    return "Hello Krupa Singavarapu";
  }

  @ApiOperation(value = "Get a GreetingService Rest response", response = GreetingService.class)
  @RequestMapping(value = "/greetings", method = RequestMethod.GET)
  public GreetingService greeting(
      @RequestParam(value = "greeting", defaultValue = "Hello") String greeting,
      @RequestParam(value = "message", defaultValue = "No Name") String message,
      @RequestParam(value = "happy", defaultValue = "true") boolean happy) {
    return new GreetingService(greeting, message, happy);
  }
}
