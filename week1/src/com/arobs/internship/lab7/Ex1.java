package com.arobs.internship.lab7;

public class Ex1 {
    public static void main(String[] args) {
        CofeeMaker mk = new CofeeMaker();
        CofeeDrinker d = new CofeeDrinker();

        for(int i = 0;i<25;i++){
            try {
                Cofee c = mk.makeCofee();
                d.drinkCofee(c);
            } catch (TemperatureException e) {
                System.out.println("Exception:"+e.getMessage()+" temp="+e.getTemp());
            } catch (ConcentrationException e) {
                System.out.println("Exception:"+e.getMessage()+" conc="+e.getConc());
            } catch (DivisibleByTenException e) {
                System.out.println("Exception:"+e.getMessage()+" coffee #"+e.getNumberOfCoffee());
            }
            finally {
                System.out.println("Throw the cofee cup.\n");
            }
        }
    }
}//.class

class CofeeMaker {
    Cofee makeCofee() throws DivisibleByTenException{
        System.out.println("Make a coffe");
        int t = (int)(Math.random()*100);
        int c = (int)(Math.random()*100);
        Cofee cofee = new Cofee(t,c);
        int numberOfCoffeesMade = Cofee.getNumberOfCoffeesMade();

        if (numberOfCoffeesMade % 10 == 0) {
            throw new DivisibleByTenException(numberOfCoffeesMade, "Congratulations! No coffee for you this time!");
        } else {
            return cofee;
        }
    }

}//.class

class Cofee{
    private static int numberOfCoffeesMade = 0;
    private int temp;
    private int conc;

    Cofee(int t,int c) {
        temp = t;
        conc = c;
        numberOfCoffeesMade++;
    }

    int getTemp(){return temp;}
    int getConc(){return conc;}
    static int getNumberOfCoffeesMade() {
        return numberOfCoffeesMade;
    }
    public String toString(){return "[cofee temperature="+temp+":concentration="+conc+"]";}
}//.class

class CofeeDrinker{
    void drinkCofee(Cofee c) throws TemperatureException, ConcentrationException{
        if(c.getTemp()>60)
            throw new TemperatureException(c.getTemp(),"Cofee is to hot!");
        if(c.getConc()>50)
            throw new ConcentrationException(c.getConc(),"Cofee concentration to high!");
        System.out.println("Drink cofee:"+c);
    }
}//.class

class DivisibleByTenException extends Exception{
    int numberOfCoffee;
    public DivisibleByTenException(int numberOfCoffee,String msg) {
        super(msg);
        this.numberOfCoffee = numberOfCoffee;
    }

    int getNumberOfCoffee() {
        return numberOfCoffee;
    }
}//.class

class TemperatureException extends Exception{
    int t;
    public TemperatureException(int t,String msg) {
        super(msg);
        this.t = t;
    }

    int getTemp(){
        return t;
    }
}//.class

class ConcentrationException extends Exception{
    int c;
    public ConcentrationException(int c,String msg) {
        super(msg);
        this.c = c;
    }

    int getConc(){
        return c;
    }
}//.class
