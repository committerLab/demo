package fr.committer.demo.redis.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Set;

@RedisHash("user")
@Getter
@Setter
public class User implements Serializable {

    private String id;
    private String name;
    private Set<String> languages;
}
