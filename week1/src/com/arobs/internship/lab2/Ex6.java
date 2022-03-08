package com.arobs.internship.lab2;

import java.util.Scanner;

public class Ex6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please give a number for factorial calculation: ");
        int number = Integer.parseInt(scanner.nextLine());

        System.out.println(number + "! = " + nFactorialRecursive(number));
        System.out.println(number + "! = " + nFactorialNonRecursive(number));
    }

    private static int nFactorialRecursive(int number) {
        if (number == 1) {
            return 1;
        } else {
            return number * nFactorialRecursive(number - 1);
        }
    }

    private static int nFactorialNonRecursive(int number) {
        int result = 1;

        for (int i = 1; i <= number ; i++) {
            result *= i;
        }

        return result;
    }
}
