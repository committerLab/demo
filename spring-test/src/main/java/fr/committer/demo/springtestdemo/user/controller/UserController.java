package fr.committer.demo.springtestdemo.user.controller;

import fr.committer.demo.springtestdemo.user.domain.User;
import fr.committer.demo.springtestdemo.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
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
