package com.arobs.internship.musify.dto;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class SongDTO {
    private Integer id;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Duration cannot be blank")
    private Time duration;
    private Date createdDate;
    private List<String> alternativeTitles;
    private List<Integer> composersIds;

    public SongDTO() {
    }

    public SongDTO(Integer id, String title, Time duration, Date createdDate, List<String> alternativeTitles, List<Integer> composersIds) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.createdDate = createdDate;
        this.alternativeTitles = alternativeTitles;
        this.composersIds = composersIds;
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

    public List<String> getAlternativeTitles() {
        return alternativeTitles;
    }

    public void setAlternativeTitles(List<String> alternativeTitles) {
        this.alternativeTitles = alternativeTitles;
    }

    public List<Integer> getComposersIds() {
        return composersIds;
    }

    public void setComposersIds(List<Integer> composersIds) {
        this.composersIds = composersIds;
    }
}
