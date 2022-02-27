package fr.committer.demo.mockserverdemo.repository;

import fr.committer.demo.mockserverdemo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository{
    private final RestTemplate restTemplate;
    private static final String uri = "http://localhost:8888/users/";

    /**
     * Get all users
     * @return list of all users
     */
    public List<User> findAll(){
        return Arrays.asList(restTemplate.getForObject(uri, User[].class));
    }
    /**
     * Get user by provided id
     * @param id user's id
     * @return {@link User}
     */
    public Optional<User> findById(Integer id){
        return  Optional.ofNullable(restTemplate.getForObject(uri + id, User.class));
    }

    /**
     * Add new user
     * @param user user's values
     * @return created {@link User}
     */
    public User saveUser(User user){
        return restTemplate.postForObject(uri, user, User.class);
    }

    /**
     * Update an user
     * @param id user's id
     */
    public void updateUser(Integer id, User user){
        restTemplate.put(uri + id , user);
    }
}
