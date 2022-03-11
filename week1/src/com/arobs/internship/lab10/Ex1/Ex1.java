package com.arobs.internship.lab10.Ex1;

public class Ex1 {
    public static void main(String[] args) {
        Counter c1 = new Counter("counter1");
        Counter c2 = new Counter("counter2");
        Counter c3 = new Counter("counter3");

        c1.start();
        c2.start();
        c3.start();
    }
}

