package com.arobs.internship.lab6.Ex4;

import java.util.Scanner;

public class TextUI {
    private Dictionary dictionary;
    private Scanner scanner;

    public TextUI(Dictionary dictionary) {
        this.dictionary = dictionary;
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean inMenu = true;

        while (inMenu) {
            printMenu();
            System.out.print("~ ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> addWord();
                case "2" -> getDefinition();
                case "3" -> getAllWords();
                case "4" -> getAllDefinitions();
                case "0" -> {
                    System.out.println("Goodbye!");
                    inMenu = false;
                }

            }
        }
    }

    private void getAllDefinitions() {
        for (Definition definition : dictionary.getAllDefinitions()) {
            System.out.println(definition);
        }
    }

    private void getAllWords() {
        for (Word word : dictionary.getAllWords()) {
            System.out.println(word);
        }
    }

    private void getDefinition() {
        System.out.print("Enter word: ");
        String word = scanner.nextLine();

        System.out.println(dictionary.getDefinition(new Word(word)));
    }

    private void addWord() {
        System.out.print("Enter word: ");
        String word = scanner.nextLine();
        System.out.print("Enter definition: ");
        String definition = scanner.nextLine();

        dictionary.addWord(new Word(word), new Definition(definition));
    }

    private void printMenu() {
        System.out.println("""
                ----------------------------------------------
                | Please select one of the following options |
                ----------------------------------------------
                | 1. Add a new word.                         |
                | 2. Get the definition of a word.           |
                | 3. Get all of the words.                   |
                | 4. Get all of the definitions.             |
                | 0. Exit program.                           |
                ----------------------------------------------""");
    }
}
