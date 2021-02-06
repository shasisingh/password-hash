package com.shashi.example.passwordhash.service;

import com.shashi.example.passwordhash.domain.Password;
import com.shashi.example.passwordhash.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.shashi.example.passwordhash.service.PasswordHash.getHash;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Value("${pepper.value:blablabla}")
    private String pepperValue;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean reset(final String userId, final String newPassword) {
        return userRepository.findByUserId(userId).map(user -> {
            Password existingPassword = user.getPassword();
            String newSalt = RandomStringUtils.randomAlphabetic(10);
            String saltHash = getHash(newSalt + newPassword);
            existingPassword.setPassword(getHash(saltHash + pepperValue));
            existingPassword.setSalt(newSalt);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }

}
