package com.springbootmvreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootMvReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvReviewApplication.class, args);
        System.out.println("Start");
    }

}
