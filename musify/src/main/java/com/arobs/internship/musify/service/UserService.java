package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.UserDTO;
import com.arobs.internship.musify.dto.UserViewDTO;
import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.model.User;
import com.arobs.internship.musify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = new UserMapperImpl();
    }

    public List<UserViewDTO> getAllUsers() {
        List<User> users = userRepository.getAllUsers();

        return users
                .stream()
                .map(userMapper::toViewDto)
                .collect(Collectors.toList());
    }

    public UserViewDTO getUserById(int id) {
         User user = userRepository.getUserById(id);

         if (user != null) {
             return userMapper.toViewDto(user);
         } else {
             throw new ResourceNotFoundException("User with id " + id + " not found.");
         }
    }

    public UserViewDTO addUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        String encryptedPassword = getEncryptedPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        int id = userRepository.addUser(user);
        user.setId(id);

        return userMapper.toViewDto(user);
    }

    public UserViewDTO updateUser(int id, UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        String encryptedPassword = getEncryptedPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        user.setId(id);

        int updatedRows = userRepository.updateUser(user);
        if (updatedRows == 1) {
            return userMapper.toViewDto(user);
        } else {
            throw new ResourceNotFoundException("User with id " + id + " not found.");
        }
    }

    private String getEncryptedPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashedPassword = md.digest(password.getBytes());
            BigInteger bigInteger = new BigInteger(1, hashedPassword);
            return bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
