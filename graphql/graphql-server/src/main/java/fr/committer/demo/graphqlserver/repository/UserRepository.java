package fr.committer.demo.graphqlserver.repository;

import fr.committer.demo.graphqlserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
