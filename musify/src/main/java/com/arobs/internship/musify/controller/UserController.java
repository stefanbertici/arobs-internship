package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.UserDTO;
import com.arobs.internship.musify.dto.UserViewDTO;
import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserViewDTO> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public UserViewDTO addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }
}
