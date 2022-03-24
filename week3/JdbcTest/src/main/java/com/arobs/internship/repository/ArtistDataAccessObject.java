package com.arobs.internship.repository;

import com.arobs.internship.domain.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ArtistDataAccessObject implements Dao<Artist> {
    ConnectionPool connectionPool;

    public ArtistDataAccessObject(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Optional<Artist> get(int id) {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM artists WHERE id = " + id;
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                id = rs.getInt("id");
                String stageName = rs.getString("stage_name");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                Date birthday = rs.getDate("birthday");
                Timestamp updatedAt = rs.getTimestamp("updatedAt");

                Artist artist = new Artist(id, stageName, firstName, lastName, email, birthday, updatedAt);
                return Optional.of(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<Artist> getAll() {
        Connection connection = null;
        Collection<Artist> artists = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM artists";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String stageName = rs.getString("stage_name");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                Date birthday = rs.getDate("birthday");
                Timestamp updatedAt = rs.getTimestamp("updatedAt");

                Artist artist = new Artist(id, stageName, firstName, lastName, email, birthday, updatedAt);
                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return artists;
    }

    @Override
    public int save(Artist artist) {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("""
                    INSERT INTO artists (stage_name, first_name, last_name, email, birthday, updatedAt)
                    VALUES (?, ?, ?, ?, ?, ?);
                    """);

            preparedStatement.setString(1, artist.getStageName());
            preparedStatement.setString(2, artist.getFirstName());
            preparedStatement.setString(3, artist.getLastName());
            preparedStatement.setString(4, artist.getEmail());
            preparedStatement.setDate(5, artist.getBirthday());
            preparedStatement.setTimestamp(6, artist.getUpdatedAt());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    @Override
    public void update(Artist artist) {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("""
                    UPDATE artists
                    SET
                        stage_name = ?,
                        first_name = ?,
                        last_name = ?,
                        email = ?,
                        birthday = ?
                    WHERE id = ?;
                    """);

            preparedStatement.setString(1, artist.getStageName());
            preparedStatement.setString(2, artist.getFirstName());
            preparedStatement.setString(3, artist.getLastName());
            preparedStatement.setString(4, artist.getEmail());
            preparedStatement.setDate(5, artist.getBirthday());
            preparedStatement.setInt(6, artist.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Artist artist) {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("""
                    DELETE FROM artists WHERE id = ?;
                    """);

            preparedStatement.setInt(1, artist.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
