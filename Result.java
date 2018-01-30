//Rosanna Kristofferson
//rokr9648

public class Result implements Comparable<Result> {

    private double result;
    private Participant participant;
    private SportEvent sportEvent;


    public Result(double result, Participant participant, SportEvent sportEvent) {
        this.result = result;
        this.participant = participant;
        this.sportEvent = sportEvent;
    }

    public double getResult() {
        return result;
    }

    public SportEvent getSportEvent() {
        return sportEvent;
    }

    public Participant getParticipant() {
        return participant;
    }

    public String toString() {
        return  participant.getFullName() + " " + participant.getTeam() + " " + getResult();

    }

    public int compareTo(Result compareResult) {
        if (result > compareResult.result)
            return -1;

        if (result < compareResult.result)
            return 1;

        return participant.getFullName().compareTo(compareResult.participant.getFullName());
    }
}