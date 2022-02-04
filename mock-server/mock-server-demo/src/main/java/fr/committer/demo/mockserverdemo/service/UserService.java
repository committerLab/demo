package fr.committer.demo.mockserverdemo.service;

import fr.committer.demo.mockserverdemo.domain.User;
import fr.committer.demo.mockserverdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * Get user by provided id
     * @param id user's id
     * @return {@link User}
     */
    public Optional<User> findUserById(Integer id){
        return userRepository.findById(id);
    }

    /**
     * Get all users
     * @return list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Add new user
     * @param user user's values
     * @return created {@link User}
     */
    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }

    /**
     * Update an user
     * @param id user's id
     */
    public void updateUser(Integer id, User user) {
        userRepository.updateUser(id, user);
    }
}
