//Rosanna Kristofferson
//rokr9648

import java.util.ArrayList;

public class Participant {

    private ArrayList<Result> resultList = new ArrayList<>();
    private Result[] resultArray = new Result[resultList.size()];

    //private ArrayList<SportEvent> events = new ArrayList<>();

    private static int nextStartNumber = 100;
    private String firstName;
    private String lastName;
    private String team;
    private int startNumber;

    public Participant(String firstName, String lastName, String team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.startNumber = nextStartNumber++;
    }

    private void insertionSort(Result[] results) {
        int in;
        int out;

        for (out = 1; out < results.length; out++) {
            Result temp = results[out];
            in = out;

            while (in > 0 && results[in - 1].getSportEvent().getEventName().compareTo(temp.getSportEvent().getEventName()) > 0) {
                results[in] = results[in - 1];
                --in;
            }
            results[in] = temp;
        }
    }

    private void sortEventResult() {
        // resultList.sort(new EventComperator());

        resultArray = resultList.toArray(resultArray);
        insertionSort(resultArray);
    }

    public void printResult() {
        sortEventResult();

        for (Result r : resultArray) {
            System.out.println("Results for " + r.getSportEvent().getEventName() + " " + r);
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public String getTeam() {
        return team;
    }

    public void addResult(Result r) {
        resultList.add(r);
    }

    public int getAttempts(String st) {
        int attempt = 0;
        for (Result result : resultList) {
            if (result.getSportEvent().getEventName().equalsIgnoreCase(st)) {
                attempt++;
            }
        }
        return attempt;
    }
    public void removeResult(Participant p){
        for (Result r: resultList){
            if(r.getParticipant()==p){
                resultList.remove(r);
                return;
            }
        }
    }

    public String toString() {
        return getStartNumber() + " " + getFullName() + " " + getTeam();
    }
}



/*            boolean eventFound = false;
            for (SportEvent e : events) {
                if (e.getEventName().equals(result.getSportEvent().getEventName())) {
                    eventFound = true;
                }
            }
            if (!eventFound) {
                events.add(result.getSportEvent());
            }

            for (SportEvent event : events) {
                System.out.print("Result for " + firstName + " " + lastName + " " + team + " " + event);
                for (Result r : resultList) {
                    if (r.getSportEvent().getEventName().equals(event.getEventName())) {
                        System.out.print(r.getResult() + ", ");
                    }
                }
                System.out.println();
            }
        }*/