package fr.committer.demo.wiremock.repository;

import fr.committer.demo.wiremock.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository{
    private final RestTemplate restTemplate = new RestTemplate();
    public Optional<User> findById(Integer id){
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8080/users/" + id, User.class));
    }

    public List<User> findAll(){
        return Collections.emptyList();
    }

    public User save(User user){
        return null;
    }

    public User update(User user){
        return null;
    }
}
