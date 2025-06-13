package me.example.demo.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi(){
        String [] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("Open API ver 0.1")
                .pathsToMatch(paths)
                .build();
    }
}
