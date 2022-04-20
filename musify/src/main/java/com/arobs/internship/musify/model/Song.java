package com.arobs.internship.musify.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ManyToMany(mappedBy = "composedSongs")
    private Set<Artist> composers = new HashSet<>();

    @ManyToMany(mappedBy = "songsInPlaylist")
    private Set<Playlist> playlists = new HashSet<>();

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

    public List<String> getAlternativeSongTitlesList() {
        return alternativeSongTitles
                .stream()
                .map(AlternativeSongTitle::getAlternativeTitle)
                .collect(Collectors.toList());
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Artist> getComposers() {
        return composers;
    }

    public void setComposers(Set<Artist> artists) {
        this.composers = artists;
    }

    public List<Integer> getComposersIds() {
        List<Integer> ids = new ArrayList<>();
        for (Artist composer : composers) {
            ids.add(composer.getId());
        }

        return ids;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void addAlternativeSongTitle(AlternativeSongTitle alternativeSongTitle) {
        alternativeSongTitles.add(alternativeSongTitle);
        alternativeSongTitle.setSong(this);
    }

    public void removeAlternativeSongTitle(AlternativeSongTitle alternativeSongTitle) {
        alternativeSongTitles.remove(alternativeSongTitle);
        alternativeSongTitle.setSong(null);
    }

    public void addToAlbum(Album album) {
        albums.add(album);
        album.getSongs().add(this);
    }

    public void removeFromAlbum(Album album) {
        albums.remove(album);
        album.getSongs().remove(this);
    }

    public void addComposer(Artist artist) {
        composers.add(artist);
        artist.getComposedSongs().add(this);
    }

    public void removeComposer(Artist artist) {
        composers.remove(artist);
        artist.getComposedSongs().remove(this);
    }

    public void addToPlaylist(Playlist playlist) {
        playlists.add(playlist);
        playlist.getSongsInPlaylist().add(this);
    }

    public void removeFromPlaylist(Playlist playlist) {
        playlists.remove(playlist);
        playlist.getSongsInPlaylist().remove(this);
    }
}
