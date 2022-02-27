package fr.committer.demo.ehcache.user.repository;

import fr.committer.demo.ehcache.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
