package fr.committer.demo.graphqlclient.controller;

import fr.committer.demo.graphqlclient.domain.Post;
import fr.committer.demo.graphqlclient.domain.User;
import fr.committer.demo.graphqlclient.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    Mono<User> getUserById(@RequestParam(required = false) List<String> fields, @PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping
    Flux<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping
    Mono<User> createUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    @PostMapping("/{id}/posts")
    Mono<Post> addPost(@PathVariable String id, @RequestBody Post post) {
        return userService.addPost(id, post);
    }

}
