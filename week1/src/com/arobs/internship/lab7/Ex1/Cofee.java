package com.arobs.internship.lab7.Ex1;

public class Cofee {
    private static int numberOfCoffeesMade = 0;
    private int temp;
    private int conc;

    Cofee(int t, int c) {
        temp = t;
        conc = c;
        numberOfCoffeesMade++;
    }

    int getTemp() {
        return temp;
    }

    int getConc() {
        return conc;
    }

    static int getNumberOfCoffeesMade() {
        return numberOfCoffeesMade;
    }

    public String toString() {
        return "[cofee temperature=" + temp + ":concentration=" + conc + "]";
    }
}
