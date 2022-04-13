package com.arobs.internship.musify.service;

import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.mapper.UserMapper;
import com.arobs.internship.musify.dto.UserDTO;
import com.arobs.internship.musify.dto.UserLoginDTO;
import com.arobs.internship.musify.dto.UserViewDTO;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.mapper.UserMapperImpl;
import com.arobs.internship.musify.model.User;
import com.arobs.internship.musify.repository.UserRepository;
import com.arobs.internship.musify.security.InMemoryTokenBlacklist;
import com.arobs.internship.musify.security.JwtUtils;
import com.arobs.internship.musify.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public List<UserViewDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toViewDtos(users);
    }

    @Transactional
    public UserViewDTO getUserById(int id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no user with id = " + id);
        }

        return userMapper.toViewDto(optional.get());
    }

    @Transactional
    public UserViewDTO registerUser(UserDTO userDTO) {
        Optional<User> optional = userRepository.findUserByEmail(userDTO.getEmail());
        if (optional.isPresent()) {
            throw new IllegalArgumentException("Email " + userDTO.getEmail() + " is already registered");
        }

        User user = userMapper.toEntity(userDTO);
        String encryptedPassword = getEncryptedPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        user.setRole("user");
        user.setStatus("active");
        user = userRepository.save(user);

        return userMapper.toViewDto(user);
    }

    @Transactional
    public String loginUser(UserLoginDTO userLoginDTO) {
        Optional<User> optional = userRepository.findUserByEmail(userLoginDTO.getEmail());
        String encryptedInputPassword = getEncryptedPassword(userLoginDTO.getPassword());
        if (optional.isEmpty()) {
            throw new UnauthorizedException("Incorrect email or password");
        }

        User user = optional.get();
        if (!UserUtils.canLogin(user, encryptedInputPassword)) {
            throw new UnauthorizedException("Incorrect email or password");
        }

        if (!UserUtils.isActive(user)) {
            throw new UnauthorizedException("It looks like your account has been deactivated :(\n Please contact our customer support department");
        }

        return JwtUtils.generateToken(user.getId(), user.getEmail(), user.getRole());
    }

    public String logoutUser(String header) {
        String token = JwtUtils.extractTokenFromHeader(header);
        inMemoryTokenBlacklist.blacklist(token);
        return "Logout successful";
    }

    @Transactional
    public UserViewDTO updateUser(Integer id, UserDTO userDTO) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no user with id = " + id);
        }

        if (!UserUtils.isCurrentAdmin() && !UserUtils.isOperationOnSelf(id)) {
            throw new UnauthorizedException("Users can only update their own info");
        }

        User user = optional.get();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        String encryptedPassword = getEncryptedPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        user.setCountry(userDTO.getCountry());
        user = userRepository.save(user);

        return userMapper.toViewDto(user);
    }

    @Transactional
    public UserViewDTO updateUserRole(Integer id, String operation) {
        String newRole = "";

        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can modify user roles");
        }

        if (operation.equals("PROMOTE")) {
            newRole = "admin";
        } else if (operation.equals("DEMOTE")) {
            newRole = "user";
        }

        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no user with id = " + id);
        }

        User user = optional.get();
        user.setRole(newRole);
        userRepository.save(user);

        return userMapper.toViewDto(user);
    }

    @Transactional
    public UserViewDTO updateUserStatus(Integer id, String operation) {
        String newStatus = "";

        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can modify user statuses");
        }

        if (operation.equals("ACTIVATE")) {
            newStatus = "active";
        } else if (operation.equals("DEACTIVATE")) {
            newStatus = "inactive";
        }

        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no user with id = " + id);
        }

        User user = optional.get();
        user.setStatus(newStatus);
        userRepository.save(user);

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
