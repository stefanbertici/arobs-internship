package com.arobs.internship.musify.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findAllBands", query = "from Band"),
        @NamedQuery(name = "findBandById", query = "from Band where id = :id")
})
@Entity
@Table(name = "bands")
public class Band{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "band_name")
    private String bandName;
    private String location;
    @Column(name = "activity_start_date")
    private String activityStartDate;
    @Column(name = "activity_end_date")
    private String activityEndDate;

    @ManyToMany(mappedBy = "bands")
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "artists_albums",
            joinColumns = { @JoinColumn(name = "band_id") },
            inverseJoinColumns = { @JoinColumn(name = "album_id") })
    private Set<Album> bandAlbums = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "artists_songs",
            joinColumns = { @JoinColumn(name = "band_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") })
    private Set<Song> bandSongs = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getActivityStartDate() {
        return activityStartDate;
    }

    public void setActivityStartDate(String activityStartDate) {
        this.activityStartDate = activityStartDate;
    }

    public String getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(String activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<Album> getBandAlbums() {
        return bandAlbums;
    }

    public void setBandAlbums(Set<Album> bandAlbums) {
        this.bandAlbums = bandAlbums;
    }

    public Set<Song> getBandSongs() {
        return bandSongs;
    }

    public void setBandSongs(Set<Song> bandSongs) {
        this.bandSongs = bandSongs;
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
        artist.getBands().add(this);
    }

    public void removeArtist(Artist artist) {
        artists.remove(artist);
        artist.getBands().remove(this);
    }

    public void addAlbum(Album album) {
        bandAlbums.add(album);
        album.getBands().add(this);
    }

    public void removeAlbum(Album album) {
        bandAlbums.remove(album);
        album.getBands().remove(this);
    }

    public void addSong(Song song) {
        bandSongs.add(song);
        song.getBands().add(this);
    }

    public void removeSong(Song song) {
        bandSongs.remove(song);
        song.getBands().remove(this);
    }
}
