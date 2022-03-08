package com.arobs.internship.lab3;

public class Flower {
    private int petal;
    private static int numberOfFlowersCreated = 0;

    public Flower() {
        petal = 5;
        numberOfFlowersCreated++;
        System.out.println("Flower has been created!");
    }

    public Flower(int petal) {
        this.petal = petal;
        numberOfFlowersCreated++;
        System.out.println("Flower has been created!");
    }

    public static int getNumberOfFlowersCreated() {
        return numberOfFlowersCreated;
    }
}
