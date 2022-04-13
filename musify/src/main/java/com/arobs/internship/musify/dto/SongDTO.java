package com.arobs.internship.musify.dto;

import java.sql.Date;
import java.sql.Time;

public class SongDTO {
    private Integer id;
    private String title;
    private Time duration;
    private Date createdDate;

    public SongDTO() {
    }

    public SongDTO(Integer id, String title, Time duration, Date createdDate) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.createdDate = createdDate;
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
}
