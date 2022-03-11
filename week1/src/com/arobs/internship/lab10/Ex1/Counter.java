package com.arobs.internship.lab10.Ex1;

class Counter extends Thread {

    Counter(String name){
        super(name);
    }

    public void run(){
        for(int i = 0; i < 20; i++){
            System.out.println(getName() + " i = "+ i);

            try {
                Thread.sleep((int)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + " job finalised.");
    }
}
