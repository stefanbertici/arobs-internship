package com.arobs.internship.lab7.Ex1;

class DivisibleByTenException extends Exception {
    int numberOfCoffee;

    public DivisibleByTenException(int numberOfCoffee, String msg) {
        super(msg);
        this.numberOfCoffee = numberOfCoffee;
    }

    int getNumberOfCoffee() {
        return numberOfCoffee;
    }
}

