package com.shashi.example.passwordhash.controller;

import com.shashi.example.passwordhash.domain.User;
import com.shashi.example.passwordhash.repository.UserRepository;
import com.shashi.example.passwordhash.service.RestPassword;
import com.shashi.example.passwordhash.service.VerifySignIn;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@ControllerAdvice
public class PasswordController {

    private final UserRepository repository;
    private final VerifySignIn verifySignIn;
    private final RestPassword restPassword;

    @Autowired
    public PasswordController(UserRepository repository, VerifySignIn verifySignIn, RestPassword restPassword) {
        this.repository = repository;
        this.verifySignIn = verifySignIn;
        this.restPassword = restPassword;
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

    @GetMapping("reset")
    public String reset(@RequestParam("userId") String userId, @RequestParam("password") String password,
            @RequestParam("newPassword") String newPassword) {
        boolean isPasswordMatch = repository.findByUserId(userId).map(User::getPassword).filter(var1 -> verifySignIn.verify(var1, password))
                .isPresent();
        if (isPasswordMatch && restPassword.restPassword(userId, newPassword)) {
            return "Password is successful change, token number is :" + RandomStringUtils.randomAlphanumeric(10);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide correct old password.");
        }
    }
}
