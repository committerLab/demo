package fr.committer.demo.redis.controller;

import fr.committer.demo.redis.domain.User;
import fr.committer.demo.redis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    User findUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping
    void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping("/{id}")
    void updateUser(@RequestBody User user, @RequestParam String id) {
        userService.update(user, user.getId());
    }

    @PostMapping("/{userId}/languages/{newLanguage}")
    void addNewLanguage(@PathVariable String userId, @PathVariable String newLanguage) {
        userService.addNewLanguage(userId, newLanguage);
    }
}
