package com.arobs.internship.lab7.Ex4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarManager {
    List<Car> carRepository = new ArrayList<>();
    String savedCarsPath = "C:\\Users\\Stefan\\Documents\\School\\arobs-internship\\week1\\saved-cars\\";

    public CarManager(List<Car> carRepository) {
        this.carRepository = carRepository;
    }

    public void printCarsInMemory() {
        System.out.println("Cars currently loaded in memory:");
        for (int i = 0; i < carRepository.size(); i++) {
            System.out.println(i + ": " + carRepository.get(i));
        }
    }

    public void printSavedCarFiles() {
        File file = new File(savedCarsPath);
        String[] fileNames = file.list();

        System.out.println("Saved cars:");
        for (int i = 0; i < fileNames.length; i++) {
            System.out.println(fileNames[i]);
        }
    }

    public void addCar(Car car) {
        carRepository.add(car);
    }

    public void printSavedCarInfo(String fileName) {
        String filePathAndName = savedCarsPath + fileName;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePathAndName))) {
            Car car = (Car) in.readObject();
            System.out.println(car);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveCarToFolder(int index) {
        Car car = carRepository.get(index);
        String filePathAndName = savedCarsPath + car.getModel() + car.hashCode() + ".dat";

        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filePathAndName))) {
            o.writeObject(car);
            carRepository.remove(index);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
