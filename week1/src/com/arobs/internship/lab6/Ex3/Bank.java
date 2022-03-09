package com.arobs.internship.lab6.Ex3;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Bank {
    private Set<BankAccount> accounts;

    public Bank(Set<BankAccount> accounts) {
        this.accounts = accounts;
    }

    // adding a new account
    public void addAccount(String owner, double balance) {
        BankAccount account = new BankAccount(owner, balance);
        accounts.add(account);
    }

    // printing all accounts ordered by balance
    public void printAccountsByBalance() {
        Set<BankAccount> accountsByBalance = new TreeSet<>(new Comparator<>() {
            @Override
            public int compare(BankAccount o1, BankAccount o2) {
                return Double.compare(o1.getBalance(), o2.getBalance());
            }
        });

        for (BankAccount account : accounts) {
            accountsByBalance.add(account);
        }

        for (BankAccount account : accountsByBalance) {
            System.out.println(account);
        }
    }

    // printing all accounts with balance between two given bounds
    public void printAccounts(double minBalance, double maxBalance) {
        for (BankAccount account : accounts) {
            if (account.getBalance() >= minBalance && account.getBalance() <= maxBalance) {
                System.out.println(account);
            }
        }
    }

    // returning all accounts ordered alphabetically by owner
    public Set<BankAccount> getAllAccounts() {
        return accounts;
    }

    // returning one account
    public BankAccount getAccount(String owner) {
        for (BankAccount account : accounts) {
            if (account.getOwner().equals(owner)) {
                return account;
            }
        }

        return null;
    }
}
