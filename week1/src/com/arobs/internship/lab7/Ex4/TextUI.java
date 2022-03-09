package com.arobs.internship.lab7.Ex4;

import java.util.Scanner;

public class TextUI {
    private CarManager manager;
    private Scanner scanner;

    public TextUI(CarManager manager) {
        this.manager = manager;
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean inMenu = true;

        while (inMenu) {
            printMenu();
            System.out.print("~ ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> addCar();
                case "2" -> printAllCarsFromMemory();
                case "3" -> saveCarFromMemoryToFile();
                case "4" -> printAllSavedCarFiles();
                case "5" -> printSavedCarInfo();
                case "0" -> {
                    System.out.println("Goodbye!");
                    inMenu = false;
                }
            }
        }
    }

    private void addCar() {
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter price (double): ");
        double price = Double.parseDouble(scanner.nextLine());

        manager.addCar(new Car(model, price));
    }

    private void printAllCarsFromMemory() {
        manager.printCarsInMemory();
    }

    private void saveCarFromMemoryToFile() {
        System.out.print("Enter index: ");
        int index = Integer.parseInt(scanner.nextLine());

        manager.saveCarToFolder(index);
    }

    private void printAllSavedCarFiles() {
        manager.printSavedCarFiles();
    }

    private void printSavedCarInfo() {
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();

        manager.printSavedCarInfo(fileName);
    }

    private void printMenu() {
        System.out.println("""
                ----------------------------------------------
                | Please select one of the following options |
                ----------------------------------------------
                | 1. Add a new car.                          |
                | 2. See all cars currently in memory.       |
                | 3. Transfer a car from memory to file.     |
                | 4. See list of all saved car files.        |
                | 5. Print info of a saved car file.         |
                | 0. Exit program.                           |
                ----------------------------------------------""");
    }
}
