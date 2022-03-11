package com.arobs.internship.lab10.Ex5;

public class Ex5 {
    public static void main(String[] args) {
        Buffer b = new Buffer();
        Producer pro = new Producer(b);
        Consumer c = new Consumer(b);
        Consumer c2 = new Consumer(b);

        pro.start();
        c.start();
        c2.start();
    }
}

