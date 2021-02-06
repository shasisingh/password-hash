package com.shashi.example.passwordhash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestPassword {

    private final UserService userService;

    @Autowired
    public RestPassword(UserService userService) {
        this.userService = userService;
    }

    public boolean restPassword(final String userId, final String newPassword) {
        return userService.reset(userId, newPassword);
    }
}
