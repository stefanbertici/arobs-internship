package com.arobs.internship.lab4.Ex6;

public class Test {
    public static void main(String[] args) {
        Shape shape = new Shape("violet", false);
        System.out.println(shape);

        Circle circle = new Circle("blue", true, 15);
        System.out.println(circle);

        Rectangle rectangle = new Rectangle("red", false, 3, 8);
        System.out.println(rectangle);

        Square square = new Square("yellow", true, 20);
        System.out.println(square);
    }
}
