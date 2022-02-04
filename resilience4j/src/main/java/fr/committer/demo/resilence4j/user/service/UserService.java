package fr.committer.demo.resilence4j.user.service;

import fr.committer.demo.resilence4j.user.domain.User;
import fr.committer.demo.resilence4j.user.exception.NotFoundException;
import fr.committer.demo.resilence4j.user.repository.UserRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @CircuitBreaker(name = "userService")
    @Retry(name = "userService")
    //@Bulkhead(name = "userService")
    public User findUserById(Integer id) {
        log.info("test=======");
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<User> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        if (!id.equals(user.getId()) || !userRepository.existsById(id)) {
            throw new RuntimeException();
        }

        return userRepository.save(user);
    }
}
