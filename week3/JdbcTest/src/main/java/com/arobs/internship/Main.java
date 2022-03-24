package com.arobs.internship;

import com.arobs.internship.domain.Artist;
import com.arobs.internship.repository.ArtistDataAccessObject;
import com.arobs.internship.repository.ConnectionPool;
import com.arobs.internship.repository.HikariConnection;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ConnectionPool connectionPool = HikariConnection.getInstance();
        ArtistDataAccessObject artistDataAccessObject = new ArtistDataAccessObject(connectionPool);

        System.out.println("Artist id = 1 -->");
        Optional<Artist> artist = artistDataAccessObject.get(1);
        artist.ifPresent(System.out::println);

        System.out.println("All artists -->");
        Collection<Artist> artists = artistDataAccessObject.getAll();
        artists.forEach(System.out::println);
    }
}
