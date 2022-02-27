package fr.committer.demo.ehcache.user.service;

import fr.committer.demo.ehcache.user.domain.User;
import fr.committer.demo.ehcache.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Cacheable(
            value = "userCache",
            key = "#id")
    public User findUserById(Integer id){
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        if(!id.equals(user.getId()) || !userRepository.existsById(id)){
            throw  new RuntimeException();
        }

        return userRepository.save(user);
    }
}
