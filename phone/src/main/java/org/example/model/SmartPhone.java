package org.example.model;

import org.example.exceptions.FriendListFullException;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SmartPhone extends CellPhone {
    private final Person[] friends = new Person[10];
    private int friendCounter = 0;

    public SmartPhone(String communicationInterface, Colour colour) {
        super(communicationInterface, colour);
    }

    public void addFriend(String name, String surname, String phoneNumber) {
        if (friendCounter >= friends.length) {
            throw new FriendListFullException("Friend list is full. Can't add more friends.");
        }
        friends[friendCounter++] = new Person(name, surname, phoneNumber);
    }

    @Override
    public void showCallHistory() {
        System.out.println(historyCounter == HISTORY_SIZE-1 ? "No calls in history" : "Last calls:");
        Map<String, String> friendsMap = Arrays.stream(friends)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        Person::phoneNumber,
                        Person::toString,
                        (a, b) -> a
                ));
        Arrays.stream(history)
                .filter(Objects::nonNull)
                .map(number -> friendsMap.getOrDefault(number, number))
                .forEach(System.out::println);
    }
}
