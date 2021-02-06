package com.shashi.example.passwordhash.controller;

import com.shashi.example.passwordhash.domain.User;
import com.shashi.example.passwordhash.repository.UserRepository;
import com.shashi.example.passwordhash.service.VerifySignIn;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PasswordController {

    private final UserRepository repository;
    private final VerifySignIn verifySignIn;

    public PasswordController(UserRepository repository, VerifySignIn verifySignIn) {
        this.repository = repository;
        this.verifySignIn = verifySignIn;
    }

    @GetMapping("signin")
    public String signIn(@RequestParam("userId") String userId, @RequestParam("password") String password) {
        boolean found = repository.findByUserId(userId).map(User::getPassword).filter(password1 -> verifySignIn.verify(password1, password))
                .isPresent();
        if (found) {
            return "Login is successful, token number is :" + RandomStringUtils.randomAlphanumeric(10);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user is not verified.");
        }
    }
}
