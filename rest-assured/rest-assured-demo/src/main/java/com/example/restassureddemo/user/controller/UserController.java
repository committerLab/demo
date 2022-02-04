package com.example.restassureddemo.user.controller;

import com.example.restassureddemo.user.domain.User;
import com.example.restassureddemo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    /**
     *
     * @param id user's id
     * @return {@link User}
     */
    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable Integer id){
        return userService.findUserById(id);
    }

    /**
     *
     * @return List of {@link User}
     */
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    /**
     *
     * @param user
     * @ saved {@link User}
     */
    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    /**
     *
     * @param id user's id
     * @param user user to update
     */
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User user){
        userService.updateUser(id, user);
    }

}
