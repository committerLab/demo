package fr.committer.demo.mockserverdemo.user.controller;

import fr.committer.demo.mockserverdemo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.HttpHostConnectException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Delay;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Slf4j
@MockServerTest
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class UserControllerTest {

    private final ClientAndServer mockServer = new ClientAndServer(8888);

    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Testdocker run -it --rm \
            -p 8080:8080 \
            --name wiremock \
    wiremock/wiremock:2.32.0
    void test() throws IOException {
        mockServer.when(
                HttpRequest
                        .request()
                        .withMethod(HttpMethod.GET.name())
                        .withPath("/users/1")
                )
                .respond(
                    HttpResponse.response()
                        .withStatusCode(200)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(getContentFile("user/one-user.json"))
                );
            ResponseEntity<User> response = testRestTemplate.getForEntity("http://localhost:"+ port + "/users/1", User.class);

            User user = response.getBody();
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(user);
            log.info("USER.getID ---> {}", user.getId());
            assertEquals(1, user.getId());
            assertEquals("Peters", user.getFirstName());
            assertEquals("Oleg", user.getLastName());
            assertEquals("176-6378 Laoreet Road", user.getAddress());
            assertEquals("olegpeters@icloud.ca", user.getEmail());
            assertEquals("Australia", user.getCountry());
            assertEquals("06 42 79 03 28", user.getPhone());
            assertEquals("62636", user.getZipCode());
            assertEquals(LocalDate.of(2003,12,21), user.getBirthDate());
    }

    @Test
    void testThrowError(){
        mockServer.when(
                        HttpRequest
                                .request()
                                .withMethod("GET")
                                .withPath("/users/1")
                )
                .respond(
                        HttpResponse.notFoundResponse()
                );
        ResponseEntity<User> response = testRestTemplate.getForEntity("http://localhost:"+ port + "/users/1", User.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testautre(){
        mockServer.when(
                        HttpRequest
                                .request()
                                .withMethod("GET")
                                .withPath("/users/1")
                )
                .respond(
                        HttpResponse.response()
                                .
                );
        ResponseEntity<User> response = testRestTemplate.getForEntity("http://localhost:"+ port + "/users/1", User.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    public String getContentFile(String path) throws IOException {
        return new String(Files.readAllBytes(new ClassPathResource(path).getFile().toPath()));
    }

}
