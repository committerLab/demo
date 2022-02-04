package fr.committer.demo.springtestdemo.user.repository;

import fr.committer.demo.springtestdemo.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
