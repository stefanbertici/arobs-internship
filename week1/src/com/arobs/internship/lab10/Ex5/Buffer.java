package com.arobs.internship.lab10.Ex5;

import java.util.ArrayList;

class Buffer {
    ArrayList<Double> content = new ArrayList();

    synchronized void push(double d) {
        content.add(d);
        notify();
    }

    synchronized double get() {
        double d = -1;
        try {
            while (content.size() == 0) {
                wait();
            }

            d = content.get(0);
            content.remove(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }
}
