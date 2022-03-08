package com.arobs.internship.lab4;

public class TestBook {
    public static void main(String[] args) {
        Author a1 = new Author("John Smith", "john.smith@myemail.com", 'm');
        Author a2 = new Author("John Smithy", "john.smithy@myeothermail.com", 'm');
        Author a3 = new Author("Julia Jones", "julia.jones@mymail.com", 'f');
        Author a4 = new Author("Albert Man", "albert.man@somewhere.com", 'm');
        Author[] authors = {a1, a2, a3, a4};

        Book b1 = new Book("Big book", authors, 49.99);
        System.out.println(b1);
        b1.printAuthors();
    }
}
