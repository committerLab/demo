package fr.committer.demo.graphqlclient.exception;

import fr.committer.demo.graphqlclient.domain.Error;
import lombok.Getter;

import java.util.List;
@Getter
public class MyGraphQLException extends RuntimeException {
    private List<Error> errors;
    public MyGraphQLException(List<Error> errors) {
        this.errors = errors;
    }
}
