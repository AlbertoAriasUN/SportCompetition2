//Rosanna Kristofferson
//rokr9648

import java.util.ArrayList;
import java.util.Collections;

public class SportEvent {

    private ArrayList<Result> resultList = new ArrayList<>();
    private ArrayList<Result> bestResults = new ArrayList<>();

    private String eventName;
    private int allowedAttempts;

    public SportEvent(String eventName, int allowedAttempts) {
        this.eventName = eventName;
        this.allowedAttempts = allowedAttempts;
    }

    private void sortEventResults() {
        Collections.sort(resultList);

        bestResults.clear();
        ArrayList<Participant> uniqueParticipants = new ArrayList<>();

        for (Result result : resultList) {
            if (!uniqueParticipants.contains(result.getParticipant())) {
                uniqueParticipants.add(result.getParticipant());
                bestResults.add(result);//Need to remove participants from resultlist if the participant has been deleted
            }
        }
    }

    public void printEventResults() {
        sortEventResults();
        System.out.println("Results for " + eventName);

        double previousResult = -1;
        int previousPosition = 1;

        for (int i = 0; i < bestResults.size(); i++) {
            if (bestResults.get(i).getResult() == previousResult) {
                System.out.println(previousPosition + " " + bestResults.get(i).toString());
            } else {
                previousResult = bestResults.get(i).getResult();
                previousPosition = i + 1;
                System.out.println(previousPosition + " " + bestResults.get(i).toString());
            }
        }
    }



    public String getEventName() {
        return eventName;
    }

    public int getAttempts() {
        return allowedAttempts;
    }

    public void addResult(Result r) {
        resultList.add(r);
    }

    public String toString() {
        return getEventName();
    }
}

