package com.arobs.internship.musify.model;


import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artistId;
    @Column(name = "activity_start_date")
    private String startDate;
    @Column(name = "activity_end_date")
    private String endDate;
    private String type;

    @OneToOne(mappedBy = "artist")
    private Band band;

    @OneToOne(mappedBy = "artist")
    private Person person;

    public int getId() {
        return artistId;
    }

    public void setId(int artistId) {
        this.artistId = artistId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
