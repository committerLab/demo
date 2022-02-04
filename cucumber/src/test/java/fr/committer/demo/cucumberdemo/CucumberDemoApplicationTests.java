package fr.committer.demo.cucumberdemo;

import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(classes = CucumberDemoApplicationTests.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@Slf4j
class CucumberDemoApplicationTests {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;


}
