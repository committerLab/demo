package fr.committer.demo.mockserverdemo.controller;

import fr.committer.demo.mockserverdemo.domain.User;
import fr.committer.demo.mockserverdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public User findUserById(@PathVariable Integer id){
        return userService.findUserById(id).get();
    }

    /**
     *
     * @return List of users {@link  User}
     */
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    /**
     *
     * @param user user to save
     * @return saved user {@link User}
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
