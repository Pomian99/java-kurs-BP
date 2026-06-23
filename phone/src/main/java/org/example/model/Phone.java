package org.example.model;

import org.example.exceptions.WrongNumberFormatException;

public class Phone {
    protected final String communicationInterface;
    protected final Colour colour;

    public Phone(String communicationInterface, Colour colour) {
        this.communicationInterface = communicationInterface;
        this.colour = colour;
    }

    public String getCommunicationInterface() {
        return communicationInterface;
    }

    public Colour getColour() {
        return colour;
    }

    public void makeCall(String phoneNumber) throws WrongNumberFormatException {
        if (phoneNumber == null || !phoneNumber.matches("\\d{3}-\\d{3}-\\d{3}")) {
            throw new WrongNumberFormatException("Given phone number has wrong format.");
        }
        System.out.println("Calling " + phoneNumber);
    }

    public void showCallHistory() {
        System.out.println("No history.");
    }
}
