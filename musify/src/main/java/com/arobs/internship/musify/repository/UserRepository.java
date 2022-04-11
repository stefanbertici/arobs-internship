package com.arobs.internship.musify.repository;

import com.arobs.internship.musify.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    List<User> findAll();

    Optional<User> findUserByEmail(String email);
}
