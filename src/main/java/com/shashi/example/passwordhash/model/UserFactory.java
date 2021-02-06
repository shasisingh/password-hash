package com.shashi.example.passwordhash.model;

import com.shashi.example.passwordhash.domain.Password;
import com.shashi.example.passwordhash.domain.User;
import com.shashi.example.passwordhash.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.shashi.example.passwordhash.service.PasswordHash.getHash;

@Service
public class UserFactory {

    private final UserRepository userRepository;
    @Value("${pepper.value:blablabla}")
    private String pepperValue;

    public UserFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(SignUpDTO sign) {
        User user = new User();
        user.setUserId(RandomStringUtils.randomAlphanumeric(10));
        user.setEmail(sign.getEmail());
        user.setFirstName(sign.getFirstName());
        user.setLastName(sign.getLastName());
        Password password = new Password();
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        String saltHash = getHash(generatedString + sign.getPassword());
        password.setPassword(getHash(saltHash + pepperValue));
        password.setSalt(generatedString);
        password.setUser(user);
        user.setPassword(password);
        return userRepository.save(user);
    }
}
