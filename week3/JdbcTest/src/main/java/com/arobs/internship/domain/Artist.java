package com.arobs.internship.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Artist {
    private int id;
    private String stageName;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthday;
    private Timestamp updatedAt;

    public Artist(int id, String stageName, String firstName, String lastName, String email, Date birthday, Timestamp updatedAt) {
        this.id = id;
        this.stageName = stageName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getStageName() {
        return stageName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", stageName='" + stageName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
