package com.shashi.example.passwordhash.repository;

import com.shashi.example.passwordhash.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserId(String userId);

    List<User> findAll();
}
