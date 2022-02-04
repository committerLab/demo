package fr.committer.demo.kafkastream.user.controller;

import fr.committer.demo.kafkastream.user.domain.User;
import fr.committer.demo.kafkastream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;
    @GetMapping("/{id}")
    public Mono<User> findUserById(@PathVariable Integer id){
        log.info("ID ---> {}", id);
        return userService.findUserById(id);
    }

    @GetMapping
    public Flux<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public Mono<User> saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public Mono<User> updateUser(@RequestParam Integer id, @RequestBody User user){
        return userService.updateUser(id, user);
    }


}
