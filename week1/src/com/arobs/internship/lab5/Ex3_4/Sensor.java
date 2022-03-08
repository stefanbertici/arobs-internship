package com.arobs.internship.lab5.Ex3_4;

public abstract class Sensor {
    private String location;

    public Sensor(String location) {
        this.location = location;
    }

    public abstract int readValue();

    public String getLocation() {
        return location;
    }
}
