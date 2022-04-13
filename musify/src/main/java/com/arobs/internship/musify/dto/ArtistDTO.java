package com.arobs.internship.musify.dto;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

public class ArtistDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String stageName;
    private Date birthday;
    private String activityStartDate;
    private String activityEndDate;

    public ArtistDTO() {
    }

    public ArtistDTO(Integer id, String firstName, String lastName, String stageName, Date birthday, String activityStartDate, String activityEndDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.birthday = birthday;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
}
