package com.arobs.internship.lab7.Ex1;

public class CofeeDrinker {
    void drinkCofee(Cofee c) throws TemperatureException, ConcentrationException {
        if (c.getTemp() > 60)
            throw new TemperatureException(c.getTemp(), "Cofee is to hot!");
        if (c.getConc() > 50)
            throw new ConcentrationException(c.getConc(), "Cofee concentration to high!");
        System.out.println("Drink cofee:" + c);
    }
}
