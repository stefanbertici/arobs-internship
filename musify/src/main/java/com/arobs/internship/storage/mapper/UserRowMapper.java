package com.arobs.internship.storage.mapper;

import com.arobs.internship.musify.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        /*return new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
                rs.getString("email"), rs.getString("encrypted_password"), rs.getString("country"),
                rs.getString("role"), rs.getString("status"));*/
        return new User(); // User class is refactored and this is just to get rid of compilation errors
    }
}
