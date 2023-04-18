package fr.committer.demo.graphqlclient.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record User(
        Integer id,
        String firstName,
        String lastName,
        String phone,
        String email,
        String address,
        String country,
        String zipCode,
        List<Post> posts
) {
}
