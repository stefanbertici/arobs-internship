package com.arobs.internship.musify.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

// for base hibernate operations
@NamedQueries({
        @NamedQuery(name = "findAllArtists", query = "from Artist"),
        @NamedQuery(name = "findArtistById", query = "from Artist where id = :id")
})
@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "stage_name")
    private String stageName;
    private Date birthday;
    @Column(name = "activity_start_date")
    private String activityStartDate;
    @Column(name = "activity_end_date")
    private String activityEndDate;

    @ManyToMany()
    @JoinTable(name = "bands_artists",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "band_id") })
    private Set<Band> bands = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "artists_albums",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "album_id") })
    private Set<Album> artistAlbums = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "artists_songs",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") })
    private Set<Song> artistSongs = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public Set<Band> getBands() {
        return bands;
    }

    public void setBands(Set<Band> bands) {
        this.bands = bands;
    }

    public Set<Album> getArtistAlbums() {
        return artistAlbums;
    }

    public void setArtistAlbums(Set<Album> artistAlbums) {
        this.artistAlbums = artistAlbums;
    }

    public Set<Song> getArtistSongs() {
        return artistSongs;
    }

    public void setArtistSongs(Set<Song> artistSongs) {
        this.artistSongs = artistSongs;
    }

    public void addBand(Band band) {
        bands.add(band);
        band.getArtists().add(this);
    }

    public void removeBand(Band band) {
        bands.remove(band);
        band.getArtists().remove(this);
    }

    public void addAlbum(Album album) {
        artistAlbums.add(album);
        album.getArtists().add(this);
    }

    public void removeAlbum(Album album) {
        artistAlbums.remove(album);
        album.getArtists().remove(this);
    }

    public void addSong(Song song) {
        artistSongs.add(song);
        song.getArtists().add(this);
    }

    public void removeSong(Song song) {
        artistSongs.remove(song);
        song.getArtists().remove(this);
    }
}
