package com.arobs.internship.lab3;

public class TestFlower {
    public static void main(String[] args) {
        Flower[] garden = new Flower[5];

        for (int i = 0 ; i < 5 ; i++){
            Flower f = new Flower();
            garden[i] = f;
        }

        System.out.println("We have created " + Flower.getNumberOfFlowersCreated() + " flowers!");
    }
}
