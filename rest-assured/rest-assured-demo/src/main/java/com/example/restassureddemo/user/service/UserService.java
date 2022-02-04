package com.example.restassureddemo.user.service;

import com.example.restassureddemo.user.domain.User;
import com.example.restassureddemo.user.exception.NotFoundException;
import com.example.restassureddemo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findUserById(Integer id) {

                Optional<User> user = userRepository.findById(id);
                log.info("EXISTE -> {}", user.isPresent());
                log.info("id -> {}", user.get().getId());
                return user;
    }

    public List<User> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        return userRepository.save(user);
    }
}
