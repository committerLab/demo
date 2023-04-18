package fr.committer.demo.redis.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "redis")
@Getter
@Setter
public class Config {
    private String hostName;
    private int port;
    private String userName;
    private String password;
}
