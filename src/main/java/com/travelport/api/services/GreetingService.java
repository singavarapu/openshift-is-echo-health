package com.travelport.api.services;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

/**
 * Has implementation for greeting endpoint and other services.
 */
@Component
public class GreetingService implements Endpoint<GreetingService> {
  private String message;
  private String greetings;
  private boolean happyToSeeYou;

  // default constructor is a must, otherwise @Component would throw exception
  // Parameter 0 of constructor in com.travelport.api.services.GreetingService required a bean of
  // type 'java.lang.String' that could not be found.
  public GreetingService() {}

  public GreetingService(String greetings, String message, boolean happyToSeeYou) {
    this.greetings = greetings;
    this.message = message;
    this.happyToSeeYou = happyToSeeYou;
  }

  @Override
  public GreetingService invoke() {
    // the values below would be seen in the response
    return new GreetingService("Hi there", "friend", true);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getGreetings() {
    return greetings;
  }

  public void setGreetings(String greetings) {
    this.greetings = greetings;
  }

  public boolean isHappyToSeeYou() {
    return happyToSeeYou;
  }

  public void setHappyToSeeYou(boolean happyToSeeYou) {
    this.happyToSeeYou = happyToSeeYou;
  }

  // The three methods below help to define and endPoint
  @Override
  public String getId() {
    // the id becomes the endpoint path in the actuator page (/management)
    return "greetingEndpoint";
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean isSensitive() {
    return true;
  }
}
