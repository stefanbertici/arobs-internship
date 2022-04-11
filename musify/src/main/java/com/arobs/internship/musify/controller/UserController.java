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
    public ResponseEntity<List<UserViewDTO>> getAllUsers() {
        List<UserViewDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserViewDTO> getUserById(@PathVariable Integer id) {
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
    
    @PostMapping("/user/logout")
    public ResponseEntity<String> logout(@RequestHeader(name = "Authorization") String header) { // 'Authorization: Bearer tokenString'
        String response = userService.logoutUser(header);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserViewDTO> updateUser(@PathVariable Integer id, @RequestBody @Valid UserDTO userDTO) {
        UserViewDTO user = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{id}/promote")
    public ResponseEntity<UserViewDTO> promoteUser(@PathVariable Integer id) {
        UserViewDTO user = userService.updateUserRole(id, "PROMOTE");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{id}/demote")
    public ResponseEntity<UserViewDTO> demoteUser(@PathVariable Integer id) {
        UserViewDTO user = userService.updateUserRole(id, "DEMOTE");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
