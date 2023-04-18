package fr.committer.demo.redis.repository;

import fr.committer.demo.redis.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final RedisTemplate<String, User> redisTemplate;

    public void update(User user, String id) {
        redisTemplate.opsForValue().set(id, user);
    }

    public void save(User user) {
        redisTemplate.opsForValue().set(user.getId(), user);
    }

    public User findById(String id) {
        return redisTemplate.opsForValue().get(id);
    }
}
