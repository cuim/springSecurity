package com.andy.spring.springsecurity;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class Application implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("---ApplicationRunner---");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
