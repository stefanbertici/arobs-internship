package com.arobs.internship.lab10.Ex2;

class FirSet extends Thread {
    Punct p;

    public FirSet(Punct p) {
        this.p = p;
    }

    public void run() {
        int i = 0;
        while (++i < 15) {
            int a = (int) Math.round(10 * Math.random() + 10);
            int b = (int) Math.round(10 * Math.random() + 10);

            synchronized (p) {
                p.setXY(a, b);
            }

            try {
                sleep(10);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println("Am scris: [" + a + "," + b + "]");
        }
    }
}

