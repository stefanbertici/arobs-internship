package com.arobs.internship.musify.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Time duration;
    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(mappedBy = "song")
    Set<AlternativeSongTitle> alternativeSongTitles = new HashSet<>();

    @ManyToMany(mappedBy = "songs")
    private Set<Album> albums = new HashSet<>();

    @ManyToMany(mappedBy = "artistSongs")
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany(mappedBy = "bandSongs")
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

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<AlternativeSongTitle> getAlternativeSongTitles() {
        return alternativeSongTitles;
    }

    public void setAlternativeSongTitles(Set<AlternativeSongTitle> alternativeSongTitles) {
        this.alternativeSongTitles = alternativeSongTitles;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public void addToAlbum(Album album) {
        albums.add(album);
        album.getSongs().add(this);
    }

    public void removeFromAlbum(Album album) {
        albums.remove(album);
        album.getSongs().remove(this);
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

    public void addAlternativeSongTitle(AlternativeSongTitle alternativeSongTitle) {
        alternativeSongTitles.add(alternativeSongTitle);
        alternativeSongTitle.setSong(this);
    }

    /*public void removeAlternativeSongTitle(AlternativeSongTitle alternativeSongTitle) {
        alternativeSongTitles.remove(alternativeSongTitle);
        alternativeSongTitle.setSong(null);
    }*/

    public void addSongToArtist(Artist artist) {
        artists.add(artist);
        artist.getArtistSongs().add(this);
    }

    public void removeSongFromArtist(Artist artist) {
        artists.remove(artist);
        artist.getArtistSongs().remove(this);
    }

    public void addSongToBand(Band band) {
        bands.add(band);
        band.getBandSongs().add(this);
    }

    public void removeSongFromBand(Band band) {
        bands.remove(band);
        band.getBandSongs().remove(this);
    }
}
