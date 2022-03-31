package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.UserDTO;
import com.arobs.internship.musify.dto.UserLoginDTO;
import com.arobs.internship.musify.dto.UserViewDTO;
import com.arobs.internship.musify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserViewDTO>> getAll() {
        List<UserViewDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserViewDTO> getUserById(@PathVariable int id) {
        UserViewDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserViewDTO> addUser(@RequestBody @Valid UserDTO userDTO) {
        UserViewDTO user = userService.registerUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        String token = userService.loginUser(userLoginDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    // placeholder "/user/logout"

    @PutMapping("/user/{id}")
    public ResponseEntity<UserViewDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        UserViewDTO user = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{id}/promote")
    public ResponseEntity<UserViewDTO> promoteUser(@PathVariable int id) {
        UserViewDTO user = userService.updateUserRole(id, "PROMOTE");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{id}/demote")
    public ResponseEntity<UserViewDTO> demoteUser(@PathVariable int id) {
        UserViewDTO user = userService.updateUserRole(id, "DEMOTE");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
