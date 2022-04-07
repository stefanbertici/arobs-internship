package com.arobs.internship.musify.dto;

public class BandDTO {
    private Integer id;
    private String bandName;
    private String location;
    private String activityStartDate;
    private String activityEndDate;

    public BandDTO() {
    }

    public BandDTO(Integer id, String bandName, String location, String activityStartDate, String activityEndDate) {
        this.id = id;
        this.bandName = bandName;
        this.location = location;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
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

    @Override
    public String toString() {
        return "BandDTO{" +
                "id=" + id +
                ", bandName='" + bandName + '\'' +
                ", location='" + location + '\'' +
                ", activityStartDate='" + activityStartDate + '\'' +
                ", activityEndDate='" + activityEndDate + '\'' +
                '}';
    }
}
