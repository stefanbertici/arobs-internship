package com.arobs.internship.lab7.Ex4;

import java.util.ArrayList;
import java.util.List;

public class TestCar {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        CarManager manager = new CarManager(cars);
        TextUI ui = new TextUI(manager);

        ui.start();
    }
}
