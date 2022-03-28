package com.arobs.internship.musify.repository;

import com.arobs.internship.musify.mapper.UserRowMapper;
import com.arobs.internship.musify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    public Optional<User> getUserById(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id = " + id, new UserRowMapper());

        if (users.size() == 1) {
            User user = users.get(0);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    public void addUser(User user) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("""
                    INSERT INTO users (first_name, last_name)
                    VALUES (?, ?);
                    """);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            return preparedStatement;
        });
    }
}
