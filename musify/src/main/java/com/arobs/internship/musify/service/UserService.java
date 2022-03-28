package com.arobs.internship.musify.service;

import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.model.User;
import com.arobs.internship.musify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(int id) {
         Optional<User> opt = userRepository.getUserById(id);

         if (opt.isPresent()) {
             return opt.get();
         } else {
             throw new ResourceNotFoundException("User with id #" + id + " not found.");
         }
    }

    public void addUser(String firstName, String lastName) {
        userRepository.addUser(new User(firstName, lastName));
    }
}
