package fr.committer.demo.graphqlclient.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
@Configuration
public class Config {
    @Value("${graphql.url}")
    private String url;
    @Bean
    HttpGraphQlClient httpGraphQlClient(){
        return HttpGraphQlClient.builder().url(url).build();
    }

}
