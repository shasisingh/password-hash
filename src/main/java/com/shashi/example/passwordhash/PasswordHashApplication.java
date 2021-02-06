package com.shashi.example.passwordhash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PasswordHashApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasswordHashApplication.class, args);
    }

}
