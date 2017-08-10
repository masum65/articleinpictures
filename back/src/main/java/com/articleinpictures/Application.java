package com.articleinpictures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Teemu Hirvonen
 */
@SpringBootApplication
@EnableCaching
public class Application {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
