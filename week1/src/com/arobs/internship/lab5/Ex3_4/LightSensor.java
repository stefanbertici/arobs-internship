package com.arobs.internship.lab5.Ex3_4;

import java.util.Random;

public class LightSensor extends Sensor{

    public LightSensor(String location) {
        super(location);
    }

    @Override
    public int readValue() {
        Random random = new Random();
        return random.nextInt(101);
    }

    @Override
    public String toString() {
        return "Temperature reading at " + super.getLocation() + " = " + readValue();
    }
}
