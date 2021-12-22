package com.saurabh.spring.top_score;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.saurabh.spring.top_score"})
public class TopScoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopScoreApplication.class, args);
    }

}
