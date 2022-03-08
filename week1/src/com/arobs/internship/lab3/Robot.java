package com.arobs.internship.lab3;

public class Robot {
    private int x;

    public Robot() {
        x = 1;
    }

    public void change(int k) {
        if (k >= 1) {
            x += k;
        }
    }

    @Override
    public String toString() {
        return "Robot{" +
                "x=" + x +
                '}';
    }
}
