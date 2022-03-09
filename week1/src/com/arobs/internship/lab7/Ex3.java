package com.arobs.internship.lab7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Ex3 {
    public static void main(String[] args) {
        String operation = args[0];
        String file = args[1];

        System.out.println("We will be " + operation + "ing " + file);

        if (operation.equals("encript")) {
            encript(file);
        } else if (operation.equals("decript")) {
            decript(file);
        } else {
            System.out.println("Wrong arguments given! encript/decript,filename.extension expected.");
        }
    }

    private static void decript(String file) {
        String inputFile = file;
        String[] parts = inputFile.split("\\.");
        String outputFile = parts[0] + ".dec";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bwr = new BufferedWriter(new FileWriter(outputFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                char[] characters = line.toCharArray();
                for (int i = 0; i < characters.length; i++) {
                    characters[i] = (char) (characters[i] + '1');
                }

                bwr.write(characters);
                System.out.println("Decripted successfully!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void encript(String file) {
        String inputFile = file;
        String[] parts = inputFile.split("\\.");
        String outputFile = parts[0] + ".enc";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bwr = new BufferedWriter(new FileWriter(outputFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                char[] characters = line.toCharArray();
                for (int i = 0; i < characters.length; i++) {
                    characters[i] = (char) (characters[i] - '1');
                }

                bwr.write(characters);
                System.out.println("Encripted successfully!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
