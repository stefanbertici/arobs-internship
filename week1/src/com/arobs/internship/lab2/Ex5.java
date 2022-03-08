package com.arobs.internship.lab2;

import java.util.Random;

public class Ex5 {
    public static void main(String[] args) {
        int[] elements = new int[10];
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            elements[i] = random.nextInt(1000);
        }

        System.out.println("Unsorted vector: ");
        printVector(elements);

        sortVector(elements);

        System.out.println("Sorted vector: ");
        printVector(elements);
    }

    private static void sortVector(int[] elements) {
        int size = elements.length;

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (elements[j] > elements[j + 1]) {
                    int temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }

    private static void printVector(int[] elements) {
        for (int i = 0; i < elements.length; i++) {
            if (i < elements.length - 1) {
                System.out.print(elements[i] + ", ");
            } else {
                System.out.print(elements[i] + ".\n");
            }
        }
    }
}
