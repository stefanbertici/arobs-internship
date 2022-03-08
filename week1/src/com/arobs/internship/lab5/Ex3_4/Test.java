package com.arobs.internship.lab5.Ex3_4;

public class Test {
    public static void main(String[] args) {
        TemperatureSensor tempSensor = new TemperatureSensor("indoor-garden");
        LightSensor lightSensor = new LightSensor("indoor-garden-plants");
        Controller indoorController = Controller.getController(tempSensor, lightSensor);

        TemperatureSensor tempSensor2 = new TemperatureSensor("outdoor-garden");
        LightSensor lightSensor2 = new LightSensor("outdoor-garden-plants");
        Controller outdoorController = Controller.getController(tempSensor2, lightSensor2);

        // both controllers will be the same object "indoorController"
        try {
            indoorController.control();
            outdoorController.control();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
