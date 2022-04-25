package com.arobs.internship.musify.service;

import com.arobs.internship.musify.mapper.UserMapper;
import com.arobs.internship.musify.dto.UserDTO;
import com.arobs.internship.musify.dto.UserLoginDTO;
import com.arobs.internship.musify.dto.UserViewDTO;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.model.User;
import com.arobs.internship.musify.repository.UserRepository;
import com.arobs.internship.musify.security.InMemoryTokenBlacklist;
import com.arobs.internship.musify.security.JwtUtils;
import com.arobs.internship.musify.utils.RepositoryChecker;
import com.arobs.internship.musify.utils.UserChecker;
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
    private final RepositoryChecker repositoryChecker;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final InMemoryTokenBlacklist inMemoryTokenBlacklist;

    @Autowired
    public UserService(RepositoryChecker repositoryChecker, UserRepository userRepository, UserMapper userMapper, InMemoryTokenBlacklist inMemoryTokenBlacklist) {
        this.repositoryChecker = repositoryChecker;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.inMemoryTokenBlacklist = inMemoryTokenBlacklist;
    }

    @Transactional
    public List<UserViewDTO> readAllUsers() {
        List<User> users = userRepository.findAll();

        return userMapper.toViewDtos(users);
    }

    @Transactional
    public UserViewDTO readUserById(int id) {
        User user = repositoryChecker.getUserIfExists(id);

        return userMapper.toViewDto(user);
    }

    @Transactional
    public UserViewDTO registerUser(UserDTO userDTO) {
        Optional<User> optional = userRepository.findUserByEmail(userDTO.getEmail());
        if (optional.isPresent()) {
            throw new IllegalArgumentException("Email " + userDTO.getEmail() + " is already registered");
        }

        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);

        String encryptedPassword = getEncryptedPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        user.setRole("user");
        user.setStatus("active");

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
        if (!UserChecker.canLogin(user, encryptedInputPassword)) {
            throw new UnauthorizedException("Incorrect email or password");
        }

        if (!UserChecker.isActive(user)) {
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
        User user = repositoryChecker.getUserIfExists(id);

        if (UserChecker.isCurrentUserNotAdmin() && !UserChecker.isOperationOnSelf(id)) {
            throw new UnauthorizedException("Users can only update their own info");
        }

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        String encryptedPassword = getEncryptedPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        user.setCountry(userDTO.getCountry());

        return userMapper.toViewDto(user);
    }

    @Transactional
    public UserViewDTO updateUserRole(Integer id, String operation) {
        String newRole = "";

        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can modify user roles");
        }

        if (operation.equals("PROMOTE")) {
            newRole = "admin";
        } else if (operation.equals("DEMOTE")) {
            newRole = "user";
        }

        User user = repositoryChecker.getUserIfExists(id);
        user.setRole(newRole);

        return userMapper.toViewDto(user);
    }

    @Transactional
    public UserViewDTO updateUserStatus(Integer id, String operation) {
        String newStatus = "";

        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can modify user statuses");
        }

        if (operation.equals("ACTIVATE")) {
            newStatus = "active";
        } else if (operation.equals("DEACTIVATE")) {
            newStatus = "inactive";
        }

        User user = repositoryChecker.getUserIfExists(id);
        user.setStatus(newStatus);

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
