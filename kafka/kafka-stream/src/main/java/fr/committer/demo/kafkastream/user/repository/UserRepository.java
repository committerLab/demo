package fr.committer.demo.kafkastream.user.repository;

import fr.committer.demo.kafkastream.user.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, Integer> {
}
