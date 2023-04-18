package fr.committer.demo.graphqlclient.repository;

import fr.committer.demo.graphqlclient.domain.Error;
import fr.committer.demo.graphqlclient.domain.Post;
import fr.committer.demo.graphqlclient.domain.User;
import fr.committer.demo.graphqlclient.exception.MyGraphQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.ResponseError;
import org.springframework.graphql.client.ClientResponseField;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepository {
    private final HttpGraphQlClient httpGraphQlClient;

    public Flux<User> findAll() {
        return httpGraphQlClient
                .documentName("users")
                .retrieve("users")
                .toEntityList(User.class).flatMapMany(Flux::fromIterable);
    }


    public Mono<User> findById(String id) {
        return httpGraphQlClient
                .documentName("getUserById")
                .variable("id", id)
                .execute()
                .map(response -> {
                    if (!response.isValid()) {
                        // Request failure...
                    }

                    ClientResponseField field = response.field("createUser");
                    if (!field.hasValue()) {
                        if (field.getError() != null) {
                            // Field failure...
                        } else {
                            // Optional field set to null...
                        }
                    }

                    return field.toEntity(User.class);
                });
    }

    public Mono<User> save(User user) throws MyGraphQLException {
        return httpGraphQlClient
                .documentName("createUser")
                .variable("user", user)
                .execute()
                .mapNotNull(response -> {
                    log.info("response: {}", response);
                    if (!response.isValid()) {
                        List<ResponseError> errors = response.getErrors();
                        throw new MyGraphQLException(errors.stream()
                                .map(responseError -> new Error(responseError.getMessage(), responseError.getPath()))
                                .toList());
                    }

                    return response.field("createUser").toEntity(User.class);
                });
    }

    public Mono<User> update(User user) {
        return httpGraphQlClient
                .documentName("updateUser")
                .variable("user", user)
                .execute()
                .map(response -> response.toEntity(User.class));
    }

    public Mono<Post> addPost(String id, Post post) {
        return httpGraphQlClient
                .documentName("addPost")
                .variable("id", id)
                .variable("post", post)
                .execute()
                .map(response -> response.toEntity(Post.class));
    }
}
