package org.example.model;

import org.example.exceptions.CallHistoryFullException;
import org.example.exceptions.WrongNumberFormatException;

import java.util.Arrays;
import java.util.Objects;

public class CellPhone extends Phone {
    protected final static int HISTORY_SIZE = 10;
    protected final String[] history = new String[HISTORY_SIZE];
    protected int historyCounter = HISTORY_SIZE-1;

    public CellPhone(String communicationInterface, Colour colour) {
        super(communicationInterface, colour);
    }

    @Override
    public void makeCall(String phoneNumber) throws WrongNumberFormatException {
        super.makeCall(phoneNumber);
        if (historyCounter < 0) {
            throw new CallHistoryFullException("Call history is full");
        }
        history[historyCounter--] = phoneNumber;
    }

    @Override
    public void showCallHistory() {
        System.out.println(historyCounter == HISTORY_SIZE-1 ? "No calls in history" : "Last calls:");
        Arrays.stream(history)
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }
}
