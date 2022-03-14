package com.arobs.internship.lab10.Ex4;

public class Main {
    public static void main(String[] args) {
        Playground playground = new Playground(15);
        playground.addRobot(new Robot(playground, 0, 0,1));
        playground.addRobot(new Robot(playground, 0, 1,2));
        playground.addRobot(new Robot(playground, 0, 2,3));
        playground.addRobot(new Robot(playground, 0, 3,4));
        playground.addRobot(new Robot(playground, 0, 4,5));
        playground.addRobot(new Robot(playground, 0, 5,6));
        playground.addRobot(new Robot(playground, 0, 6,7));
        playground.addRobot(new Robot(playground, 0, 7,8));
        playground.addRobot(new Robot(playground, 0, 8,9));
        playground.addRobot(new Robot(playground, 0, 9,10));
        playground.startGame();
    }
}
