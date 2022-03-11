package com.arobs.internship.lab10.Ex3;

public class Ex3 {
    public static void main(String[] args) {
        Count count = new Count(0);
        ConnectedCounter c1 = new ConnectedCounter("counter1", count, null);
        ConnectedCounter c2 = new ConnectedCounter("counter2", count, c1);

        c1.start();
        c2.start();
    }
}

