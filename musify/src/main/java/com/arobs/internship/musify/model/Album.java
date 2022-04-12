package com.arobs.internship.musify.model;

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

    @ManyToMany()
    @JoinTable(name = "albums_songs",
            joinColumns = { @JoinColumn(name = "album_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") })
    private Set<Song> songs = new HashSet<>();

    @ManyToMany(mappedBy = "artistAlbums")
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany(mappedBy = "bandAlbums")
    private Set<Band> bands = new HashSet<>();

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

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<Band> getBands() {
        return bands;
    }

    public void setBands(Set<Band> bands) {
        this.bands = bands;
    }

    public void addSong(Song song) {
        songs.add(song);
        song.getAlbums().add(this);
    }

    public void removeSong(Song song) {
        songs.remove(song);
        song.getAlbums().remove(this);
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
        artist.getArtistAlbums().add(this);
    }

    public void removeArtist(Artist artist) {
        artists.remove(artist);
        artist.getArtistAlbums().remove(this);
    }

    public void addBand(Band band) {
        bands.add(band);
        band.getBandAlbums().add(this);
    }

    public void removeBand(Band band) {
        bands.remove(band);
        band.getBandAlbums().remove(this);
    }
}
