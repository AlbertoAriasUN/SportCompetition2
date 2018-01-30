//Rosanna Kristofferson
//rokr9648

import java.util.Scanner;
import java.util.ArrayList;

public class Program {

    private ArrayList<Participant> participantRegister = new ArrayList<>();
    private ArrayList<SportEvent> eventList = new ArrayList<>();

    private Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        new Program().menu();
    }

    private void menu() {
        while (true) {
            System.out.println("To add an event, enter \"Add event\"\n" +
                "To add a participant, enter \"Add participant\" \n" +
                "To remova a participant, enter \"Remove participant\"\n" +
                "To add a result, enter \"Add result\"\n" +
                "To  search for a participant, enter \"Participant\"\n" +
                "To  search for a event, enter \"Search for event\"\n" +
                "To  print a message, enter \"Message\"\n" +
                "To exit program, enter \"Exit\"\n" +
                "Please enter choice:");
            String userChoice = keyboard.nextLine();
            userChoice = userChoice.trim();

            switch (userChoice.toLowerCase()) {
                case ("add event"):
                case ("1"):
                    addEvent();
                    break;
                case ("add participant"):
                case ("2"):
                    addParticipant();
                    break;
                case ("remove participant"):
                case ("3"):
                    removeParticipant();
                    break;
                case ("add result"):
                case ("4"):
                    addResult();
                    break;
                case ("participant"):
                case ("5"):
                    participant();
                    break;
                case ("exit"):
                case ("6"):
                    exitProgram();
                    break;
                default:
                    if (userChoice.contains("message")) {
                        message();
                        break;
                    }
                    SportEvent event = searchForEvent(userChoice);
                    if (event != null) {
                        event.printEventResults();
                        break;
                    }
                    System.out.println("Error\"" + userChoice + "\"" + "unknown command");
                    break;
            }
        }
    }

    private void addEvent() {

        System.out.println("Add eventname:");
        String eventName = keyboard.nextLine();
        eventName = inputValidation(eventName);

        SportEvent searchEvent = searchForEvent(eventName);
        if (searchEvent != null) {
            System.out.println("Error, event already exist");
            return;
        }

        System.out.println("Add allowed attempts:");
        int allowedAttempts = keyboard.nextInt();
        keyboard.nextLine();

        while (allowedAttempts <= 0) {
            System.out.println("Error, there need to be atleast one allowed attempt, enter attempts:");
            allowedAttempts = keyboard.nextInt();
            keyboard.nextLine();
        }

        eventName = normalizeString(eventName);
        SportEvent event = new SportEvent(eventName, allowedAttempts);
        eventList.add(event);
        System.out.println(event + " has been added");
    }

    private void addParticipant() {

        System.out.println("Firstname: ");
        String firstName = keyboard.nextLine();
        firstName = inputValidation(firstName);

        System.out.println("Lastname: ");
        String lastName = keyboard.nextLine();
        lastName = inputValidation(lastName);

        System.out.println("Team: ");
        String team = keyboard.nextLine();
        team = inputValidation(team);

        firstName = normalizeString(firstName);
        lastName = normalizeString(lastName);
        team = normalizeString(team);
        Participant participant = new Participant(firstName, lastName, team);
        participantRegister.add(participant);
        System.out.println(participant + " has been added");
    }

    private void removeParticipant() {

        Participant p = searchParticipantNumber();
        if (p == null) {
            return;
        }
        System.out.println(p + " has been removed");
        participantRegister.remove(p);

       p.removeResult(p);
    }
    private void addResult() {

        Participant p = searchParticipantNumber();
        if (p == null) {
            return;
        }

        System.out.println("Enter the event: ");
        String event = keyboard.nextLine();
        event = inputValidation(event);
        event = normalizeString(event);

        SportEvent e = searchForEvent(event);
        if (e == null) {
            System.out.println("Error, event doesn't exist");
            return;
        }

        if (e.getAttempts() > p.getAttempts(event)) {
            System.out.println("Enter result: ");
            Double participantResult = keyboard.nextDouble();
            keyboard.nextLine();

            while (participantResult <0){
                System.out.println("Error, result must be greater than zero, try again: ");
                participantResult = keyboard.nextDouble();
                keyboard.nextLine();
            }

            Result result = new Result(participantResult, p, e);

            e.addResult(result);
            p.addResult(result);
            System.out.println("Result for " + p + " in "+ e +": "+ participantResult);
        } else {
            System.out.println("Error, allowed attempts have already been done");
        }
    }

    private void participant() {
        Participant p = searchParticipantNumber();
        if (p == null) {
            return;
        }
        p.printResult();
    }

    private void message() {

        String userMessage = keyboard.nextLine();
        userMessage = userMessage.toUpperCase();

        if (userMessage.length() > 56)
            userMessage = userMessage.substring(0, 56);
    }

    private void exitProgram() {
        System.out.println("Closing program");
        System.exit(0);
    }

    private String normalizeString(String st) {

        st = st.trim();
        st = st.toLowerCase();
        st = st.substring(0, 1).toUpperCase() + st.substring(1);

        return st;
    }

    private String inputValidation(String str) {
        while ((str == null) || str.trim().isEmpty()) {
            System.out.println("Error field can't be empty, please try again:");
            str = keyboard.nextLine();
        }
        return str;
    }

    private Participant searchParticipantNumber() {

        System.out.println("Enter participant number: ");
        int number = keyboard.nextInt();
        keyboard.nextLine();

        for (Participant participant : participantRegister) {
            if (participant.getStartNumber() == number) {
                return participant;
            }
        }
        System.out.println("Error, no participant with that number exist");
        return null;
    }

    private SportEvent searchForEvent(String eventName) {
        for (SportEvent sport : eventList) {
            if (sport.getEventName().equalsIgnoreCase(eventName)) {
                return sport;
            }
        }
        return null;
    }
}

