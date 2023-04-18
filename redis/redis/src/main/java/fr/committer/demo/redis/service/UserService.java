package fr.committer.demo.redis.service;

import fr.committer.demo.redis.domain.User;
import fr.committer.demo.redis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addNewLanguage(String userId, String newLanguage) {
        User user = findById(userId);
        user.getLanguages().add(newLanguage);
        save(user);
    }

    public void update(User user, String id) {
        userRepository.update(user, id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(String id) {
        return userRepository.findById(id);
    }
}
