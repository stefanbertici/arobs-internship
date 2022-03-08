package com.arobs.internship.lab4;

public class Cylinder extends Circle{
    private double height = 1.0;

    public Cylinder() {
        super();
    }

    public Cylinder(double radius) {
        super(radius);
    }

    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return super.getArea() + 2 * Math.PI * super.getRadius() * height;
    }

    public double getVolume() {
        return super.getArea() * height;
    }
}
