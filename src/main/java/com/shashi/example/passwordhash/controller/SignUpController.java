package com.shashi.example.passwordhash.controller;

import com.shashi.example.passwordhash.domain.User;
import com.shashi.example.passwordhash.model.SignUpDTO;
import com.shashi.example.passwordhash.model.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SignUpController {

    private final UserFactory factory;

    @Autowired
    public SignUpController(UserFactory factory) {
        this.factory = factory;
    }

    @PostMapping(value = "signup")
    public String signUp(@RequestBody  @NonNull SignUpDTO signUpDetails) {
        User user = factory.create(signUpDetails);
        return "userId ["+user.getUserId()+"] created successfully!";
    }
}
