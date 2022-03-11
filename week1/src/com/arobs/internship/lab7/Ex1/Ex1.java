package com.arobs.internship.lab7.Ex1;

public class Ex1 {
    public static void main(String[] args) {
        CofeeMaker mk = new CofeeMaker();
        CofeeDrinker d = new CofeeDrinker();

        for (int i = 0; i < 25; i++) {
            try {
                Cofee c = mk.makeCofee();
                d.drinkCofee(c);
            } catch (TemperatureException e) {
                System.out.println("Exception:" + e.getMessage() + " temp=" + e.getTemp());
            } catch (ConcentrationException e) {
                System.out.println("Exception:" + e.getMessage() + " conc=" + e.getConc());
            } catch (DivisibleByTenException e) {
                System.out.println("Exception:" + e.getMessage() + " coffee #" + e.getNumberOfCoffee());
            } finally {
                System.out.println("Throw the cofee cup.\n");
            }
        }
    }
}

