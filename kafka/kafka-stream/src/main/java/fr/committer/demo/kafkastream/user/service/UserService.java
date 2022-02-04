package fr.committer.demo.kafkastream.user.service;

import fr.committer.demo.kafkastream.user.domain.User;
import fr.committer.demo.kafkastream.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Mono<User> findUserById(Integer id){
        return userRepository.findById(id);
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> saveUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> updateUser(Integer id, User user) {
        return userRepository.save(user);
    }
}
