package fr.committer.demo.wiremock.controller;

import fr.committer.demo.wiremock.domain.User;
import fr.committer.demo.wiremock.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    @GetMapping("/{id}")
    public User findUserById(@PathVariable Integer id){
        log.info("ID ---> {}", id);
        return userService.findUserById(id).get();
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user){
        return userService.updateUser(id, user);
    }


}
