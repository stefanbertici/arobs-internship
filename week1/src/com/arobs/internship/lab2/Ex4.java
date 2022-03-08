package com.arobs.internship.lab2;

import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many elements do you wish to enter? ");
        int numberOfElements = Integer.parseInt(scanner.nextLine());
        int[] elements = new int[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            System.out.print("Please give element #" + (i + 1) + ": ");
            elements[i] = Integer.parseInt(scanner.nextLine());
        }

        printMax(elements);
    }

    private static void printMax(int[] elements) {
        int max = elements[0];

        for (int element : elements) {
            if (element > max) {
                max = element;
            }
        }

        System.out.println("Maximum of the given elements is: " + max);
    }
}
