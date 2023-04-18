package fr.committer.demo.graphqlserver.service;

import fr.committer.demo.graphqlserver.domain.Post;
import fr.committer.demo.graphqlserver.domain.User;
import fr.committer.demo.graphqlserver.exception.NotFoundException;
import fr.committer.demo.graphqlserver.repository.PostRepository;
import fr.committer.demo.graphqlserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser( String id) {
        userRepository.deleteById(id);
    }
    Post addPost(String id, Post post) throws NotFoundException {
        return postRepository.save(post);
    }

}
