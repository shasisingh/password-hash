package com.shashi.example.passwordhash.service;

import com.shashi.example.passwordhash.domain.Password;
import com.shashi.example.passwordhash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.shashi.example.passwordhash.service.PasswordHash.getHash;

@Service
public class VerifySignIn {

    private final UserRepository userRepository;

    @Value("${pepper.value:blablabla}")
    private String pepperValue;

    @Autowired
    public VerifySignIn(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean verify(final Password password, final String userInput) {
        String saltHash = getHash(password.getSalt() + userInput);
        String expectedHash = getHash(saltHash + pepperValue);
        return expectedHash.equals(password.getPassword());
    }

}
