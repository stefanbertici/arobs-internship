package com.arobs.internship.lab7.Ex1;

public class CofeeMaker {
    Cofee makeCofee() throws DivisibleByTenException {
        System.out.println("Make a coffe");
        int t = (int) (Math.random() * 100);
        int c = (int) (Math.random() * 100);
        Cofee cofee = new Cofee(t, c);
        int numberOfCoffeesMade = Cofee.getNumberOfCoffeesMade();

        if (numberOfCoffeesMade % 10 == 0) {
            throw new DivisibleByTenException(numberOfCoffeesMade, "Congratulations! No coffee for you this time!");
        } else {
            return cofee;
        }
    }
}
