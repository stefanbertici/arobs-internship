package com.arobs.internship.lab10.Ex2;

public class Ex2 {
    public static void main(String[] args) {
        Punct p = new Punct();
        FirSet fs1 = new FirSet(p);
        FirGet fg1 = new FirGet(p);

        fs1.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fg1.start();
    }
}

