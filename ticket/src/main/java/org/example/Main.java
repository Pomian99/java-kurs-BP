package org.example;

public class Main {
    public static void main(String[] args) {
        Ticket ticket = Ticket.CHILD;
        double ticketPrice = 4.5;

        System.out.printf("%s pays for ticket %.2f.", ticket.getName(), ticket.calculatePrice(ticketPrice));
    }
}
