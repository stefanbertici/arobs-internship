package com.arobs.internship.lab7.Ex1;

class ConcentrationException extends Exception {
    int c;

    public ConcentrationException(int c, String msg) {
        super(msg);
        this.c = c;
    }

    int getConc() {
        return c;
    }
}
