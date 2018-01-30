
import java.util.Comparator;

public class EventComperator implements Comparator<Result> {

    public int compare(Result o1, Result o2) {
        return o1.getSportEvent().getEventName().compareTo(o2.getSportEvent().getEventName());
    }
