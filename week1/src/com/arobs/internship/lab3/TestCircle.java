package com.arobs.internship.lab3;

public class TestCircle {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        Circle c2 = new Circle(13, "green");

        System.out.println("C1 radius = " + c1.getRadius() + ", " + "area = " + c1.getArea());
        System.out.println("C2 radius = " + c2.getRadius() + ", " + "area = " + c2.getArea());
    }
}
