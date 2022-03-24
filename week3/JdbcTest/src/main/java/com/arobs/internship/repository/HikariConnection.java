package com.arobs.internship.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class HikariConnection implements ConnectionPool {

    private static HikariConnection instance = null;
    HikariConfig hikariConfig = new HikariConfig();
    HikariDataSource hikariDataSource;

    private HikariConnection() {
        Map<String, String> configs = loadJdbcConfig();
        hikariConfig.setJdbcUrl(configs.get("jdbc_url"));
        hikariConfig.setUsername(configs.get("username"));
        hikariConfig.setPassword(configs.get("password"));
        hikariConfig.addDataSourceProperty ("maximumPoolSize", configs.get("maximumPoolSize"));
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public static HikariConnection getInstance() {
        if (instance == null) {
            instance = new HikariConnection();
        }

        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    @Override
    public void releaseConnection(Connection connection) throws SQLException {
        connection.close();
    }

    private Map<String, String> loadJdbcConfig() {
        Map<String, String> configs = new HashMap<>();
        String line = null;

        try (BufferedReader br = new BufferedReader(new FileReader("jdbc_config.txt"))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                configs.put(parts[0], parts[1]);
            }
        } catch (IOException fnfex) {
            System.out.println(fnfex.getMessage());
        }

        return configs;
    }
}
