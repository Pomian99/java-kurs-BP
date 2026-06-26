package org.example;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter @Setter
public class VIPAccount extends StandardAccount{
    private double individualInterestRate;
    private BigDecimal overdraftLimit;

    public VIPAccount(Person owner, BigDecimal balance, double interestRate, BigDecimal overdraftLimit) {
        super(owner, balance);
        this.individualInterestRate = interestRate;
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Withdrawal amount must be positive value.");
            return false;
        }
        BigDecimal balanceAfter = this.balance.subtract(amount);
        if (balanceAfter.compareTo(overdraftLimit) < 0) {
            System.out.println("Balance too low. Withdrawal will exceed limit.");
            return false;
        }

        this.balance = balanceAfter;
        return true;
    }

    @Override
    public boolean transfer(Account targetAccount, BigDecimal amount) {
        if (targetAccount == null) {
            System.out.println("Given account does not exist.");
            return false;
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Transfer amount must be positive value.");
            return false;
        }

        BigDecimal balanceAfter = this.balance.subtract(amount);
        if (balanceAfter.compareTo(overdraftLimit) < 0) {
            System.out.println("Balance too low. Transfer will exceed limit.");
            return false;
        }

        this.balance = balanceAfter;
        targetAccount.setBalance(targetAccount.getBalance().add(amount));
        return true;
    }

    @Override
    public void update() {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Account is in debt. Interest rate not processed.");
            return;
        }
        balance = balance.add(balance.multiply(BigDecimal.valueOf(individualInterestRate)).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
    }

}
