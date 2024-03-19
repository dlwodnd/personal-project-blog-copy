package com.project.t_story_copy_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
@EnableJpaRepositories(basePackages = {"com.project.t_story_copy_project"})
@EnableScheduling
public class TStoryCopyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TStoryCopyProjectApplication.class, args);
    }
    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizerPageable(){
        return p -> p.setOneIndexedParameters(true);
    }

}
