public class Event extends Task {
    String startTime;
    String endTime;

    public Event(String description, String start, String end) {
        super(description);
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to "+ endTime + ")";
    }
}
