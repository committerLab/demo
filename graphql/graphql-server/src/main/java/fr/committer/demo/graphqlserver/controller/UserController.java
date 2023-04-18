package fr.committer.demo.graphqlserver.controller;

import fr.committer.demo.graphqlserver.domain.Post;
import fr.committer.demo.graphqlserver.domain.User;
import fr.committer.demo.graphqlserver.exception.NotFoundException;
import fr.committer.demo.graphqlserver.repository.UserRepository;
import fr.committer.demo.graphqlserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @QueryMapping
    public List<User> users() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public User user(@Argument String id) {
        return userService.findUserById(id);
    }

    @MutationMapping
    public User createUser(@Argument User user) {
        return userService
                .save(User.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .address(user.getAddress())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .zipCode(user.getZipCode())
                        .build());
    }

    @MutationMapping
    public User updateUser(@Argument User user) {
        return userService
                .createUser(User.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .address(user.getAddress())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .zipCode(user.getZipCode())
                        .build());
    }

    @MutationMapping
    public void deleteUser(@Argument String id) {
        userService.deleteUser(id);
    }
    @MutationMapping
    Post addPost(@Argument Integer id, @Argument Post post) throws NotFoundException {
        return userService.addPost(id, post);
    }
}
