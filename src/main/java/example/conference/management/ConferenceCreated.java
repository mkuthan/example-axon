package example.conference.management;

public class ConferenceCreated {

    private String conferenceId;

    private int availableNumberOfSeats;

    public ConferenceCreated(String conferenceId, int availableNumberOfSeats) {
        this.conferenceId = conferenceId;
        this.availableNumberOfSeats = availableNumberOfSeats;
    }

    public String getConferenceId() {
        return conferenceId;
    }

    public int getAvailableNumberOfSeats() {
        return availableNumberOfSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferenceCreated that = (ConferenceCreated) o;

        if (availableNumberOfSeats != that.availableNumberOfSeats) return false;
        if (!conferenceId.equals(that.conferenceId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = conferenceId.hashCode();
        result = 31 * result + availableNumberOfSeats;
        return result;
    }

    @Override
    public String toString() {
        return "ConferenceCreated{" +
                "conferenceId='" + conferenceId + '\'' +
                ", availableNumberOfSeats=" + availableNumberOfSeats +
                '}';
    }

    protected ConferenceCreated() {
    }
}
