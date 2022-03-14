package com.arobs.internship.lab10.Ex4;

import java.util.ArrayList;
import java.util.List;

public class Playground {
    List<Robot> robots;
    private Robot[][] field;
    private int size;

    public Playground(int size) {
        robots = new ArrayList<>();
        field = new Robot[size][size];
        this.size = size;
    }

    public int getTableSize() {
        return size;
    }

    public void startGame() {
        for (Robot robot : robots) {
            robot.start();
        }

        // we wait until every robot finished execution of run() method
        try {
            for (Robot robot : robots) {
                robot.join();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("The game has finished!");
    }

    public void addRobot(Robot robot) {
        robots.add(robot);
        field[robot.getX()][robot.getY()] = robot;
    }

    public synchronized Robot whoIsThere(int x, int y) {
        return field[x][y];
    }

    public synchronized void clearSpot(int x, int y) {
        field[x][y] = null;
    }

    public synchronized void moveRobot(int fromX, int fromY, int toX, int toY) {
        Robot robotToMove = field[fromX][fromY];

        field[toX][toY] = robotToMove;
        field[fromX][fromY] = null;
    }
}
