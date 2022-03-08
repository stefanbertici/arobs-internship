package com.arobs.internship.lab4;

public class Book {
    private String name;
    private Author[] authors;
    private double price;
    private int qtyInStock = 0;

    public Book(String name, Author[] authors, double price) {
        this.name = name;
        this.authors = authors;
        this.price = price;
    }

    public Book(String name, Author[] authors, double price, int qtyInStock) {
        this.name = name;
        this.authors = authors;
        this.price = price;
        this.qtyInStock = qtyInStock;
    }

    public String getName() {
        return name;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public double getPrice() {
        return price;
    }

    public int getQtyInStock() {
        return qtyInStock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQtyInStock(int qtyInStock) {
        this.qtyInStock = qtyInStock;
    }

    @Override
    public String toString() {
        return name + " by " + authors.length + " authors";
    }

    public void printAuthors() {
        System.out.print("Authors: ");
        for (int i = 0; i < authors.length; i++) {
            if (i < authors.length - 1) {
                System.out.print(authors[i] + ", ");
            } else {
                System.out.print(authors[i] + ".\n");
            }
        }
    }
}
