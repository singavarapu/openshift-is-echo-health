package com.travelport.api.controllers;

import com.travelport.api.services.GreetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
public class HelloControllerTest {

  private HelloController helloTemplate = new HelloController();

  @Test
  public void testHelloPathTest() {
    String body = this.helloTemplate.sayHello();
    assertNotNull(body);
    assertEquals(("Hello Krupa Singavarapu"), body);
  }

  @Test
  public void testGreetingsPathTest() {
    GreetingService body = this.helloTemplate.greeting("Howdy", "my friend", false);
    assertNotNull(body);
    assertEquals(" greeting is incorrect", ("Howdy"), body.getGreetings());
    assertEquals(" message is incorrect", ("my friend"), body.getMessage());
    assertEquals("returned happyToSeeYou is incorrect", false, body.isHappyToSeeYou());
  }
}
