package com.travelport.springbootkrupa;

import com.travelport.api.controllers.HelloController;
import com.travelport.api.controllers.HealthInternalController;
import com.travelport.api.services.GreetingService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Here we are starting a full Spring container with default configuration. The TestRestTemplate
 * is configured automatically when you use the @SpringBootTest annotation, and is configured to
 * resolve relative paths to http://localhost:${local.server.port}. When we execute the
 * integrationTest for SpringBootKrupaApplication() test method, we'll send a GET to the web
 * server that will be routed to the HelloController. After that, the controller will call the
 * GreetingService to create the greeting. We are testing our full application stack with a few
 * configurations.
 */

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootKrupaApplicationTest {

    @Autowired
    private HelloController controller;
    @Autowired
    private HealthInternalController healthController;
    @Autowired
    private TestRestTemplate restTemplate;
    private GreetingService service;
    @LocalServerPort
    private int port;
    @Value("${project.version}")
    private String appVersion;

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(controller);
        assertNotNull(healthController);
    }

    // sanity check test

    /**
     * Start the application up and listen for a connection like it would do in production, and then
     * send an HTTPS request and assert the response. HTTPS is enabled via keystore property in
     * application.properties file. Port 8081 is defined at applications.yml
     */
    @Test
    public void testItShouldReturnGreetingMessage() {
        Assertions.assertThat(
                this.restTemplate.getForObject("http://localhost:" + port + "/greetings", String.class))
                .contains("Hello", "No Name", "true");
    }

    @Test
    public void testItShouldReturnTheVersionOfTheApplication() {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/appVersion", String.class))
                .hasSameSizeAs(appVersion);
    }

    @Test
    public void integrationTestForSpringBootKrupaApplication() {
        ResponseEntity<GreetingService> responseEntity =
                restTemplate.getForEntity("/greetings", GreetingService.class);
        GreetingService greeting = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Hello", greeting.getGreetings());
        assertEquals("No Name", greeting.getMessage());
        assertEquals(true, greeting.isHappyToSeeYou());
    }

    // To to: setup a remote url
    //@Test
    public void testItShouldReturnResponseFromRemoteUrl() throws Exception {
        // System.setProperty("javax.net.ssl.trustStore", "src/main/resources/cacerts");
        Assertions.assertThat(
                this.restTemplate.getForObject("http://localhost:" + port + "/health", String.class))
                .contains("diskSpace", "status");

    }

}
