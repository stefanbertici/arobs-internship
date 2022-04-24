package com.arobs.internship.musify.dto;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class BandDTO {
    private Integer id;
    @NotBlank(message = "Band name cannot be blank")
    private String bandName;
    private String location;
    private String activityStartDate;
    private String activityEndDate;
    private List<Integer> bandMembersIds;

    public BandDTO() {
    }

    public BandDTO(Integer id, String bandName, String location, String activityStartDate, String activityEndDate, List<Integer> bandMembersIds) {
        this.id = id;
        this.bandName = bandName;
        this.location = location;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
        this.bandMembersIds = bandMembersIds;
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

    public List<Integer> getBandMembersIds() {
        return bandMembersIds;
    }

    public void setBandMembersIds(List<Integer> bandMembersIds) {
        this.bandMembersIds = bandMembersIds;
    }
}
