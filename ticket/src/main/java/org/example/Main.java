package org.example;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Ticket ticket = Ticket.SENIOR;
        BigDecimal ticketPrice = BigDecimal.valueOf(4.5);

        System.out.printf("%s pays for ticket %.2f.", ticket.getName(), ticket.calculatePrice(ticketPrice));
    }
}
