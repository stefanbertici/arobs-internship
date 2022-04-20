package com.arobs.internship.musify.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class SongViewDTO {
    private Integer id;
    private String title;
    private List<String> alternativeTitles;
    private Time duration;
    private Date createdDate;
    private List<String> composers;
    private List<String> albums;

    public SongViewDTO() {
    }

    public SongViewDTO(Integer id, String title, List<String> alternativeTitles, Time duration, Date createdDate, List<String> composers, List<String> albums) {
        this.id = id;
        this.title = title;
        this.alternativeTitles = alternativeTitles;
        this.duration = duration;
        this.createdDate = createdDate;
        this.composers = composers;
        this.albums = albums;
    }

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

    public List<String> getAlternativeTitles() {
        return alternativeTitles;
    }

    public void setAlternativeTitles(List<String> alternativeTitles) {
        this.alternativeTitles = alternativeTitles;
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

    public List<String> getComposers() {
        return composers;
    }

    public void setComposers(List<String> composers) {
        this.composers = composers;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }
}
