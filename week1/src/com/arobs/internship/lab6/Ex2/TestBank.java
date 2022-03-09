package com.arobs.internship.lab6.Ex2;

import java.util.ArrayList;
import java.util.List;

public class TestBank {
    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<>();
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
