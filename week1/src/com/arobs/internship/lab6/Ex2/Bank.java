package com.arobs.internship.lab6.Ex2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bank {
    private List<BankAccount> accounts;

    public Bank(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    // adding a new account
    public void addAccount(String owner, double balance) {
        BankAccount account = new BankAccount(owner, balance);

        if (!accounts.contains(account)) {
            accounts.add(account);
        }
    }

    // printing all accounts ordered by balance
    public void printAccountsByBalance() {
        List<BankAccount> accountsByBalance = new ArrayList<>(accounts);

        accountsByBalance.sort(new Comparator<>() {
            @Override
            public int compare(BankAccount o1, BankAccount o2) {
                return Double.compare(o1.getBalance(), o2.getBalance());
            }
        });

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
    public List<BankAccount> getAllAccounts() {
        List<BankAccount> accountsByOwner = new ArrayList<>(accounts);

        accountsByOwner.sort(new Comparator<>() {
            @Override
            public int compare(BankAccount o1, BankAccount o2) {
                return o1.getOwner().compareTo(o2.getOwner());
            }
        });

        return accountsByOwner;
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
