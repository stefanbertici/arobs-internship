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
}
