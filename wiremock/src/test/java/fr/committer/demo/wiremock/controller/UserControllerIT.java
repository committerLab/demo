package fr.committer.demo.wiremock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import fr.committer.demo.wiremock.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockTest
class UserControllerIT {
    @Autowired
    private UserController userController;
    private ObjectMapper objectMapper = new ObjectMapper();
    User user = User.builder()
            .id(1)
            .address("1 rue de la rue")
            .country("France")
            .build();

    @Test
    void contextLoads() throws Exception {
        // Stubbing WireMock
        stubFor(get(urlEqualTo("/users/1")).willReturn(aResponse()
                .withHeader("Content-Type", "application/json").withBody(objectMapper.writeValueAsString(user))));
        // We're asserting if WireMock responded properly
        Assertions.assertEquals(userController.findUserById(1).getId(),2);
    }
}
