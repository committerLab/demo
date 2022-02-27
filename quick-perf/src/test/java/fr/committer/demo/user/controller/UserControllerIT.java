package fr.committer.demo.user.controller;

import fr.committer.demo.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quickperf.annotation.MeasureExecutionTime;
import org.quickperf.sql.annotation.ExpectSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class UserControllerIT {


    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @Test
    @MeasureExecutionTime
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
