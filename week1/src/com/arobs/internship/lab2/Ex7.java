package com.arobs.internship.lab2;

import java.util.Random;
import java.util.Scanner;

public class Ex7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int randomNumber = random.nextInt(11);

        System.out.println("I have generated a secret number between 0 - 10 for you to guess.");
        int numberOfTries = 3;
        for (int i = 0; i < numberOfTries; i++) {
            System.out.print("You have " + (numberOfTries - i) + " guesses left.\nTake a guess: ");
            int guessedNumber = Integer.parseInt(scanner.nextLine());

            if (guessedNumber < randomNumber) {
                System.out.println("Wrong answer, your number is too low.");
            } else if (guessedNumber > randomNumber) {
                System.out.println("Wrong answer, your number it too high.");
            } else {
                System.out.println("Congratulations! You won!");
                break;
            }

            if (i == numberOfTries - 1) {
                System.out.println("You lost.");
            }
        }
    }
}
