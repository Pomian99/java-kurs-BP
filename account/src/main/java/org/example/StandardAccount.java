package org.example;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class StandardAccount extends Account {
    private static double interestRate = 2.1;

    public StandardAccount(Person owner, BigDecimal balance) {
        super(owner, balance);
    }

    public static void setInterestRate(double interestRate) {
        if (interestRate < 0) {
            System.out.println("Can't set negative interest rate.");
            return;
        }
        StandardAccount.interestRate = interestRate;
    }

    @Override
    public void update() {
        balance = balance.add(balance.multiply(BigDecimal.valueOf(interestRate)).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
    }

    @Override
    public String toString() {
        return String.format("StandardAccount{owner=%s, balance=%s, interest rate=%f}", owner, balance, interestRate);
    }
}
