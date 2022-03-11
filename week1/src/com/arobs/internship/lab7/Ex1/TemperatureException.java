package com.arobs.internship.lab7.Ex1;

class TemperatureException extends Exception {
    int t;

    public TemperatureException(int t, String msg) {
        super(msg);
        this.t = t;
    }

    int getTemp() {
        return t;
    }
}
