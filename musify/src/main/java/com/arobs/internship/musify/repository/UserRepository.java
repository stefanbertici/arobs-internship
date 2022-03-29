package com.arobs.internship.musify.repository;

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
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    public User getUserById(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id = " + id, new UserRowMapper());

        if (users.size() == 1) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public int addUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = ("INSERT INTO users (first_name, last_name, email, encrypted_password, country_of_origin, role, status) VALUES (?, ?, ?, ?, ?, ?, ?)");

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getEncryptedPassword());
            ps.setString(5, user.getCountryOfOrigin());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getStatus());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
