package com.arobs.internship.lab10.Ex4;

import java.util.Random;

public class Robot extends Thread {
    private Playground playground;
    private int x;
    private int y;
    private int robotId;
    private boolean isAlive;

    public Robot(Playground playground, int x, int y, int robotId) {
        this.playground = playground;
        this.x = x;
        this.y = y;
        this.robotId = robotId;
        this.isAlive = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRobotId() {
        return robotId;
    }

    public synchronized void destroy() {
        isAlive = false;
    }

    @Override
    public void run() {
        Random random = new Random();
        int previousX = x;
        int previousY = y;

        while (isAlive) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (x == previousX && y == previousY) {
                x = random.nextInt(playground.getTableSize());
                y = random.nextInt(playground.getTableSize());
            }

            Robot robotOnNewSpot = playground.whoIsThere(x, y);

            if (isAlive && robotOnNewSpot != null && robotOnNewSpot != this) {
                System.out.println("ROBOT id = " + robotId + " and ROBOT id = " + robotOnNewSpot.getRobotId() + " DESTROY each other on " + "x:" + x + " y:" + y);
                robotOnNewSpot.destroy();
                this.destroy();
                playground.clearSpot(x, y);
                playground.clearSpot(previousX, previousY);
            } else if (isAlive && (x != previousX || y != previousY)){
                playground.moveRobot(previousX, previousY, x, y);
                System.out.println("Robot id = " + robotId + " moves from x:" + previousX + " y:" + previousY + " to " + "x:" + x + " y:" + y);
            }

            previousX = x;
            previousY = y;
        }
    }
}
