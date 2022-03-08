package com.arobs.internship.lab5.Ex2;

public class Test {
    public static void main(String[] args) {
        ProxyImage imageOne = new ProxyImage("image1", false);
        ProxyImage imageTwo = new ProxyImage("image2", true);

        imageOne.display();
        imageTwo.display();
    }
}
