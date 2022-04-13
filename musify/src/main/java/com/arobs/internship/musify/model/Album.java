package com.arobs.internship.musify.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String genre;
    @Column(name = "release_date")
    private Date releaseDate;
    private String label;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    @ManyToMany
    @JoinTable(name = "albums_songs",
            joinColumns = { @JoinColumn(name = "album_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") })
    @JsonBackReference
    private Set<Song> songs = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public void addArtist(Artist artist) {
        this.artist = artist;
        artist.getArtistAlbums().add(this);
    }

    public void removeArtist(Artist artist) {
        this.artist = null;
        artist.getArtistAlbums().remove(this);
    }

    public void addBand(Band band) {
        this.band = band;
        band.getBandAlbums().add(this);
    }

    public void removeBand(Band band) {
        this.band = null;
        band.getBandAlbums().remove(this);
    }

    public void addSong(Song song) {
        songs.add(song);
        song.getAlbums().add(this);
    }

    public void removeSong(Song song) {
        songs.remove(song);
        song.getAlbums().remove(this);
    }
}
