package com.arobs.internship.musify.model;

public class User {

    private long id;
    private final String firstName;
    private final String lastName;

    public User(String firstName, String lastName) {
        this.id = -1;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
