package com.arobs.internship.lab3;

public class TestAuthor {
    public static void main(String[] args) {
        Author a1 = new Author("John Smith", "john.smith@myemail.com", 'm');
        System.out.println(a1);
        System.out.println(a1.getName() + ", " + a1.getEmail() + ", " + a1.getGender());
        a1.setEmail("johns-new-email@myemail.com");
        System.out.println(a1);
    }
}
