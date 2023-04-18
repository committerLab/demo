package fr.committer.demo.graphqlclient.controller;

import fr.committer.demo.graphqlclient.domain.Error;
import fr.committer.demo.graphqlclient.exception.MyGraphQLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { MyGraphQLException.class })
    protected Mono<ResponseEntity<Object>> handleConflict(MyGraphQLException ex, ServerWebExchange request) {
        return handleExceptionInternal(ex, ex.getErrors().stream().map(error-> new Error(error.message(), error.code())).toList(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}