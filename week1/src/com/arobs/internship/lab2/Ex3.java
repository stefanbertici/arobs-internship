package com.arobs.internship.lab2;

import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please give the lower bound of the interval: ");
        int lower = Integer.parseInt(scanner.nextLine());
        System.out.print("Please give the upper bound of the interval: ");
        int upper = Integer.parseInt(scanner.nextLine());

        printPrimeNumbers(lower, upper);
    }

    private static void printPrimeNumbers(int lower, int upper) {
        int count = 0;
        for (int i = lower; i <= upper; i++) {
            if (isPrime(i)) {
                System.out.println(i);
                count++;
            }
        }

        System.out.println("There are " + count + " prime numbers between the given interval " + lower + "-" + upper + ".");
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
