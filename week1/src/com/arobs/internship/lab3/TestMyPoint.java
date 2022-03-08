package com.arobs.internship.lab3;

public class TestMyPoint {
    public static void main(String[] args) {
        MyPoint p1 = new MyPoint();
        System.out.println(p1);

        MyPoint p2 = new MyPoint(9,9);
        System.out.println(p2);

        p1.setXY(3,3);
        System.out.println(p1);

        System.out.println(p1.distance(3,3));
        System.out.println(p1.distance(p2));
    }
}
