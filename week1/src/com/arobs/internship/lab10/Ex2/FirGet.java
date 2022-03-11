package com.arobs.internship.lab10.Ex2;

class FirGet extends Thread {
    Punct p;

    public FirGet(Punct p) {
        this.p = p;
    }

    public void run() {
        int i = 0;
        int a, b;
        while (++i < 15) {
            synchronized (p) {
                a = p.getX();

                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                b = p.getY();
            }
            System.out.println("Am citit: [" + a + "," + b + "]");
        }
    }
}//.class

