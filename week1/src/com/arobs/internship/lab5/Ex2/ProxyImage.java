package com.arobs.internship.lab5.Ex2;

public class ProxyImage implements Image {
    private RealImage realImage;
    private RotatedImage rotatedImage;
    private String fileName;
    private boolean displayRotated;

    public ProxyImage(String fileName, boolean displayRotated) {
        this.fileName = fileName;
        this.displayRotated = displayRotated;
    }

    @Override
    public void display() {
        if (displayRotated) {
            rotatedImage = new RotatedImage(fileName);
            rotatedImage.display();
        } else {
            realImage = new RealImage(fileName);
            realImage.display();
        }
    }
}
