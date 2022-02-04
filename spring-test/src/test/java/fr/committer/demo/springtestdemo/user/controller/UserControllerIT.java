package fr.committer.demo.springtestdemo.user.controller;

import fr.committer.demo.springtestdemo.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class UserControllerIT {

    /**
     * Teste avec un objet controller
     */
    @Autowired
    private UserController  userController;
    @Test
    void testWithControllerBean(){
        List<User> users = userController.getAllUsers();
        assertNotNull(users);
        users.forEach(user -> assertNotNull(user.getId()));
        assertEquals(5, users.size());
    }

    /**
     * Test avec TestREstTemplate
     * Spring fournit un objet TestRestTemplate uniquement quand on utilise
     * webEnvironment de SpringBootTest
     * ex: @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
     */
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @Test
    public void testWithTestRestTremplate(){
        ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:" + port + "/users/1", User.class);

        User user = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(user);
        assertEquals(user.getId(), 1);
        assertEquals(user.getFirstName(), "Peters");
        assertEquals(user.getLastName(), "Oleg");
        assertEquals(user.getAddress(), "176-6378 Laoreet Road");
        assertEquals(user.getEmail(), "olegpeters@icloud.ca");
        assertEquals(user.getCountry(), "Australia");
        assertEquals(user.getPhone(), "06 42 79 03 28");
        assertEquals(user.getZipCode(), "62636");
        assertEquals(user.getBirthDate(), LocalDate.of(2003,12,21));
    }
}
