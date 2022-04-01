package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.UserDTO;
import com.arobs.internship.musify.dto.UserLoginDTO;
import com.arobs.internship.musify.dto.UserViewDTO;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.model.User;
import com.arobs.internship.musify.repository.UserRepository;
import com.arobs.internship.musify.security.InMemoryTokenBlacklist;
import com.arobs.internship.musify.security.JwtUtils;
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
    private final InMemoryTokenBlacklist inMemoryTokenBlacklist;

    @Autowired
    public UserService(UserRepository userRepository, InMemoryTokenBlacklist inMemoryTokenBlacklist) {
        this.userRepository = userRepository;
        this.inMemoryTokenBlacklist = inMemoryTokenBlacklist;
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
        return userMapper.toViewDto(user);
    }

    public UserViewDTO registerUser(UserDTO userDTO) {
        User userWithGivenEmailInRepository = userRepository.getUserByEmail(userDTO.getEmail());
        if (userWithGivenEmailInRepository != null) {
            throw new IllegalArgumentException("Email " + userDTO.getEmail() + " is already registered");
        }

        User user = userMapper.toEntity(userDTO);
        String encryptedPassword = getEncryptedPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        user.setRole("user");
        user.setStatus("active");
        int id = userRepository.addUser(user);
        user.setId(id);

        return userMapper.toViewDto(user);
    }

    public String loginUser(UserLoginDTO userLoginDTO) {
        User user = userRepository.getUserByEmail(userLoginDTO.getEmail());
        String encryptedInputPassword = getEncryptedPassword(userLoginDTO.getPassword());

        if (user != null && user.getEncryptedPassword().equals(encryptedInputPassword)) {
            //return userMapper.toViewDto(user);
            return JwtUtils.generateToken(user.getId(), user.getEmail(), user.getRole());
        } else {
            throw new UnauthorizedException("Incorrect email or password");
        }
    }

    public String logoutUser(String header) {
        String token = JwtUtils.extractTokenFromHeader(header);
        inMemoryTokenBlacklist.blacklist(token);
        return "Logout successful";
    }

    public UserViewDTO updateUser(int id, UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        String encryptedPassword = getEncryptedPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        user.setId(id);
        userRepository.updateUser(user);

        return userMapper.toViewDto(user);
    }

    public UserViewDTO updateUserRole(int id, String operation) {
        String currentUserRole = JwtUtils.getCurrentUserRole();
        Integer currentUserId = JwtUtils.getCurrentUserId();
        String newRole = "";

        if (!currentUserRole.equals("admin")) {
            throw new UnauthorizedException("Only admins can modify user roles");
        } else if (currentUserId == id) {
            throw new UnauthorizedException("You cannot modify your own role");
        }

        if (operation.equals("PROMOTE")) {
            newRole = "admin";
        } else if (operation.equals("DEMOTE")) {
            newRole = "user";
        }

        User user = userRepository.getUserById(id);
        user.setRole(newRole);
        userRepository.updateUser(user);

        return userMapper.toViewDto(user);
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
