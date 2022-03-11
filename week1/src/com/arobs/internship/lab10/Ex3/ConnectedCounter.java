package com.arobs.internship.lab10.Ex3;

class ConnectedCounter extends Thread {
    private Count count;
    private ConnectedCounter predecessor;

    ConnectedCounter(String name, Count count, ConnectedCounter predecessor){
        super(name);
        this.count = count;
        this.predecessor = predecessor;
    }

    public void run(){
        if (predecessor != null && predecessor.isAlive()) {
            try {
                predecessor.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        int startCount = count.getCount();
        int endCount = startCount + 100;

        for(int i = startCount; i <= endCount; i++) {
            System.out.println(getName() + " count = " + count.getCount());

            if (i < endCount) {
                count.increment();
            }

            try {
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(getName() + " job finalised.");
    }
}

