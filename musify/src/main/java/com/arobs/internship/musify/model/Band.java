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

    public Band() {}

    public Band(Integer id, String bandName, String location, String activityStartDate, String activityEndDate, Set<Artist> artists) {
        this.id = id;
        this.bandName = bandName;
        this.location = location;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
        this.artists = artists;
    }

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

    public void addArtist(Artist artist) {
        artists.add(artist);
        artist.getBands().add(this);
    }

    public void removeArtist(Artist artist) {
        artists.remove(artist);
        artist.getBands().remove(this);
    }
}
