package com.arobs.internship.lab5.Ex1;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Circle circle = new Circle("blue", true, 15);
        Rectangle rectangle = new Rectangle("red", false, 3, 8);
        Square square = new Square("yellow", true, 20);

        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(circle);
        shapes.add(rectangle);
        shapes.add(square);

        for (Shape shape : shapes) {
            System.out.println(shape);
            System.out.println("Area = " + shape.getArea());
            System.out.println("Perimeter = " + shape.getPerimeter());
        }
    }
}
