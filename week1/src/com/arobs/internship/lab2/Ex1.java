package com.arobs.internship.lab2;

import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please give the first number: ");
        int firstNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Please give the second number: ");
        int secondNumber = Integer.parseInt(scanner.nextLine());

        System.out.println(Integer.max(firstNumber, secondNumber) + " is bigger!");
    }
}
