package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.UserDTO;
import com.arobs.internship.musify.dto.UserLoginDTO;
import com.arobs.internship.musify.dto.UserViewDTO;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.service.UserService;
import com.arobs.internship.musify.utils.UserChecker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserViewDTO>> readAllUsers() {
        List<UserViewDTO> users = userService.readAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserViewDTO> readUserById(@PathVariable Integer id) {
        UserViewDTO user = userService.readUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserViewDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        UserViewDTO user = userService.registerUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        String token = userService.loginUser(userLoginDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
    
    @PostMapping("/user/logout")
    public ResponseEntity<String> logoutUser(@RequestHeader(name = "Authorization") String header) { // 'Authorization: Bearer tokenString'
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
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can modify user roles");
        }

        UserViewDTO user = userService.updateUserRole(id, "PROMOTE");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{id}/demote")
    public ResponseEntity<UserViewDTO> demoteUser(@PathVariable Integer id) {
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can modify user roles");
        }

        UserViewDTO user = userService.updateUserRole(id, "DEMOTE");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{id}/activate")
    public ResponseEntity<UserViewDTO> activateUser(@PathVariable Integer id) {
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can modify user statuses");
        }

        UserViewDTO user = userService.updateUserStatus(id, "ACTIVATE");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{id}/deactivate")
    public ResponseEntity<UserViewDTO> deactivateUser(@PathVariable Integer id) {
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can modify user statuses");
        }

        UserViewDTO user = userService.updateUserStatus(id, "DEACTIVATE");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
