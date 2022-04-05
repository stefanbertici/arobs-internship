package com.arobs.internship.musify.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "bands_artists",
            joinColumns = { @JoinColumn(name = "band_id") },
            inverseJoinColumns = { @JoinColumn(name = "artist_id") })
    private final Set<Artist> artists = new HashSet<>();

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

    public void addArtist(Artist artist) {
        this.artists.add(artist);
        artist.getBands().add(this);
    }

    public void removeArtist(Artist artist) {
        this.artists.remove(artist);
        artist.getBands().remove(this);
    }
}
