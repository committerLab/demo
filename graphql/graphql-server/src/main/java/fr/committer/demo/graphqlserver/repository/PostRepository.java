package fr.committer.demo.graphqlserver.repository;

import fr.committer.demo.graphqlserver.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
