package fr.committer.demo.mockserverdemo.user.controller;

import fr.committer.demo.mockserverdemo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Delay;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import org.mockserver.netty.MockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.ConnectException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class UserController {
    @Autowired
    private RestTemplate restTemplate;
    @LocalServerPort
    private int port;
    private final MockServer mockServer = new MockServer(8888);
    @Test
    void testttt() throws IOException {
        MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
                server.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/users/1"))
                .andRespond((response) -> { throw new ResourceAccessException("ERROR ICI ACCESS MACHIN"); });
        ResponseEntity<User> response = null ;
        try{
            response = restTemplate.getForEntity("http://localhost:8080/users/1", User.class);
        }catch (ResourceAccessException e){
            log.info("ERREUR ERREUR {}", response.getStatusCode());
        }


        log.info("L4erreir ici {}", response.getStatusCode()
        );
    }

}
