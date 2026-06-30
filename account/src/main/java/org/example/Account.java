package org.example;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Account {
    protected final Person owner;
    protected BigDecimal balance;

    public boolean deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Deposit amount must be positive value.");
            return false;
        }
        balance = balance.add(amount);
        return true;
    }

    public boolean withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Withdrawal amount must be positive value.");
            return false;
        }
        if (balance.compareTo(amount) < 0) {
            System.out.println("Balance too low to fulfill withdrawal.");
            return false;
        }

        balance = balance.subtract(amount);
        return true;
    }

    public boolean transfer(Account targetAccount, BigDecimal amount) {
        if (targetAccount == null) {
            System.out.println("Given account does not exist.");
            return false;
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Transfer amount must be positive value.");
            return false;
        }
        if (balance.compareTo(amount) < 0) {
            System.out.println("Balance too low to fulfill transfer.");
            return false;
        }

        balance = balance.subtract(amount);
        targetAccount.balance = targetAccount.balance.add(amount);
        return true;
    }

    public void update() {
        System.out.println("Basic Account");
    }
}
