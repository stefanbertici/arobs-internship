package com.arobs.internship.lab7;

import java.io.BufferedReader;
import java.io.FileReader;

public class Ex2 {
    public static void main(String[] args) {
        char searchedForCharacter = args[0].charAt(0);
        System.out.println("We will be searching for \"" + searchedForCharacter + "\" in data.txt");

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                char[] characters = line.toCharArray();
                for (char character : characters) {
                    if (character == searchedForCharacter) {
                        count++;
                    }
                }
            }

            System.out.println("There are " + count + " "+ searchedForCharacter + "\'s" + " in data.txt.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
