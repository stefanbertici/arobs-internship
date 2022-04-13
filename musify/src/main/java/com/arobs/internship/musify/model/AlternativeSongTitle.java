package com.arobs.internship.musify.model;

import javax.persistence.*;

@Entity
@Table(name = "alternative_song_titles")
public class AlternativeSongTitle {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    @Column(name = "alternative_title")
    private String alternativeTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public void addSong(Song song) {
        this.song = song;
        song.getAlternativeSongTitles().add(this);
    }

    public void removeSong(Song song) {
        this.song = null;
        song.getAlternativeSongTitles().remove(this);
    }

    public String getAlternativeTitle() {
        return alternativeTitle;
    }

    public void setAlternativeTitle(String alternativeTitle) {
        this.alternativeTitle = alternativeTitle;
    }
}