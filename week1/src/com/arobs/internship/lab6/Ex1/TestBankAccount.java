package com.arobs.internship.lab6.Ex1;

public class TestBankAccount {
    public static void main(String[] args) {
        BankAccount accountOne = new BankAccount("John", 0);
        BankAccount accountTwo = new BankAccount("John", 0);
        BankAccount accountThree = new BankAccount("Johnny", 0);

        System.out.println(accountOne.equals(accountOne));
        System.out.println(accountOne.equals(accountTwo));
        System.out.println(accountOne.equals(accountThree));
    }
}
