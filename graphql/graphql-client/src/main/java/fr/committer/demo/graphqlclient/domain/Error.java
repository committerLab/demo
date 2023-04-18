package fr.committer.demo.graphqlclient.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Error(String message, String code) {
}
