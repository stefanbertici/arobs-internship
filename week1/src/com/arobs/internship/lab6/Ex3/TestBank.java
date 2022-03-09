package com.arobs.internship.lab6.Ex3;

import java.util.Set;
import java.util.TreeSet;

public class TestBank {
    public static void main(String[] args) {
        Set<BankAccount> accounts = new TreeSet<>();
        Bank bank = new Bank(accounts);

        bank.addAccount("John", 9999);
        bank.addAccount("John", 0); // testing if contains() method works and addAccount doesn't add a new account with the same owner
        bank.addAccount("Albert", 100);
        bank.addAccount("May", 2833);

        System.out.println("Accounts ordered by balance:");
        bank.printAccountsByBalance();
        System.out.println();

        System.out.println("Accounts with balance between 1000-3000:");
        bank.printAccounts(1000, 3000);
        System.out.println();

        System.out.println("Accounts ordered by owner:");
        for (BankAccount account : bank.getAllAccounts()) {
            System.out.println(account);
        }
    }
}
