package fr.committer.demo.graphqlclient.service;

import fr.committer.demo.graphqlclient.domain.Post;
import fr.committer.demo.graphqlclient.domain.User;
import fr.committer.demo.graphqlclient.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Mono<Post> addPost(String id, Post post) {
        return userRepository.findById(id).map(user -> {
            user.posts().add(post);
            return user;
        }).flatMap(userRepository::save).map(user -> user.posts().get(user.posts().size() - 1));
    }
    
    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> addUser(User user) {
        return userRepository.save(user);
    }
}
