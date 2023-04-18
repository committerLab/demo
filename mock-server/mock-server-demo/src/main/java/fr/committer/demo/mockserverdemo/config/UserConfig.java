package fr.committer.demo.mockserverdemo.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class UserConfig {
    /**
     * Créate restTemplate object and add it to spring context
     * @return {@link RestTemplate}
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder().setReadTimeout(Duration.ofSeconds(2)).setConnectTimeout(Duration.ofSeconds(2)).build();
    }
}
