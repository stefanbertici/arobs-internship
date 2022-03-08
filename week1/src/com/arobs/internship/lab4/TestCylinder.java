package com.arobs.internship.lab4;

public class TestCylinder {
    public static void main(String[] args) {
        Cylinder c1 = new Cylinder();
        System.out.println("C1 radius: " + c1.getRadius() + ",  height: " + c1.getHeight() + ", area: " + c1.getArea()+ ", volume: " + c1.getVolume());

        Cylinder c2 = new Cylinder(13, 4);
        System.out.println("C2 radius: " + c2.getRadius() + ",  height: " + c2.getHeight() + ", area: " + c2.getArea()+ ", volume: " + c2.getVolume());
    }
}
