package com.arobs.internship.lab10.Ex5;

class Producer implements Runnable {
    private Buffer bf;
    private Thread thread;

    Producer(Buffer bf) {
        this.bf = bf;
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void run() {
        while (true) {
            bf.push(Math.random());
            System.out.println("Am scris.");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

