package com.arobs.internship.musify.repository;

import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());;

        if (users.size() == 0) {
            throw new ResourceNotFoundException("No users exist at this moment");
        } else {
            return users;
        }
    }

    public User getUserById(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id = ?", new UserRowMapper(), id);

        if (users.size() == 1) {
            return users.get(0);
        } else {
            throw new ResourceNotFoundException("User with id = " + id + " does not exist");
        }
    }

    public User getUserByEmail(String email) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email = ?", new UserRowMapper(), email);

        if (users.size() == 1) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public int addUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = ("INSERT INTO users (first_name, last_name, email, encrypted_password, country, role, status) VALUES (?, ?, ?, ?, ?, ?, ?)");

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getEncryptedPassword());
            ps.setString(5, user.getCountry());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getStatus());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public void updateUser(User user) {
        int updatedRows = jdbcTemplate.update("""
                        UPDATE users
                        SET
                            first_name = ?,
                            last_name = ?,
                            email = ?,
                            encrypted_password = ?,
                            country = ?,
                            role = ?,
                            status = ?
                        WHERE id = ?""",
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getEncryptedPassword(),
                user.getCountry(), user.getRole(), user.getStatus(), user.getId());

        if (updatedRows == 0) {
            throw new ResourceNotFoundException("User with id = " + user.getId() + " does not exist");
        }
    }
}
