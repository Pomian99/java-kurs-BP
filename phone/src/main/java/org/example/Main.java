package org.example;

import org.example.exceptions.CallHistoryFullException;
import org.example.exceptions.FriendListFullException;
import org.example.exceptions.NumberExistEsception;
import org.example.exceptions.WrongNumberFormatException;
import org.example.model.CellPhone;
import org.example.model.Colour;
import org.example.model.Phone;
import org.example.model.SmartPhone;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private final static String[] testPhoneNumbers = {
            "111-222-333",
            "444-555-666",
            "777-888-999",
            "101-102-103",
            "201-202-203",
            "301-302-303",
            "401-402-403",
            "501-502-503",
            "601-602-603",
            "701-702-703"
    };

    public static void main(String[] args) {

        Phone[] phones = new Phone[3];
        phones[0] = new Phone("GSM", Colour.BLACK);
        phones[1] = new CellPhone("3G/4G", Colour.SILVER);
        SmartPhone smartPhone = new SmartPhone("5G/WiFi", Colour.WHITE);
        try {
            smartPhone.addFriend("Jan", "Kowalski", testPhoneNumbers[0]);
            smartPhone.addFriend("Maria", "Nowak", testPhoneNumbers[1]);
            smartPhone.addFriend("Piotr", "Lewandowski", testPhoneNumbers[2]);
            smartPhone.addFriend("Anna", "Kamińska", testPhoneNumbers[3]);
            smartPhone.addFriend("Krzysztof", "Wójcik", testPhoneNumbers[4]);
        } catch (FriendListFullException | NumberExistEsception e) {
            System.out.println(e.getMessage());
        }
        phones[2] = smartPhone;

        Arrays.stream(phones)
                .forEach(Main::makeTenCalls);
        Arrays.stream(phones)
                .forEach(Phone::showCallHistory);
    }

    private static void makeTenCalls(Phone phone) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String phoneNumber;
            if (i % 2 == 0) {
                phoneNumber = testPhoneNumbers[random.nextInt(5)];
            } else {
                phoneNumber = testPhoneNumbers[random.nextInt(5, testPhoneNumbers.length)];
            }

            try {
                phone.makeCall(phoneNumber);
            } catch (WrongNumberFormatException | CallHistoryFullException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}