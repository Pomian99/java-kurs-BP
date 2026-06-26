package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa BankingSystem - program główny testujący system bankowy
 */
public class Main {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();

        accounts.add(new Account(new Person("Jan", "Kowalski"), new BigDecimal("1000.00")));
        accounts.add(new StandardAccount(new Person("Maria", "Nowak"), new BigDecimal("5000.00")));
        accounts.add(new StandardAccount(new Person("Piotr", "Wójcik"), new BigDecimal("3000.00")));
        accounts.add(new VIPAccount(
                new Person("Bartosz", "Pomian"),
                new BigDecimal("10000.00"),
                4.5,
                new BigDecimal("-15000.00")
        ));

        accounts.forEach(account -> account.deposit(BigDecimal.valueOf(1000.0)));
        accounts.forEach(account -> account.withdraw(BigDecimal.valueOf(500.0)));
        accounts.forEach(account -> account.withdraw(BigDecimal.valueOf(15000.0)));

        for (int i = 0; i < accounts.size(); i++) {
            accounts.get(i).transfer(accounts.get((i+1)% accounts.size()), BigDecimal.valueOf(500.0));
        }

        StandardAccount.setInterestRate(3.0);

        VIPAccount vipAccount = (VIPAccount) accounts.get(3);
        vipAccount.setIndividualInterestRate(5.0);

        accounts.forEach(Account::update);
        accounts.forEach(System.out::println);

    }
}