package com.arobs.internship.lab5.Ex3_4;

import static java.lang.Thread.sleep;

public class Controller {
    private static Controller controller;
    private TemperatureSensor tempSensor;
    private LightSensor lightSensor;

    // prevent clients from using the constructor
    private Controller(TemperatureSensor tempSensor, LightSensor lightSensor) {
        this.tempSensor = tempSensor;
        this.lightSensor = lightSensor;
    }

    // control the allowed instances (1)
    public synchronized static Controller getController(TemperatureSensor tempSensor, LightSensor lightSensor) {
        if (controller == null) {
            controller = new Controller(tempSensor, lightSensor);
        }

        return controller;
    }

    public void control() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            System.out.println((i + 1) + "s");
            System.out.println(tempSensor);
            System.out.println(lightSensor);
            sleep(1000);
        }
    }
}
