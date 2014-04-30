package example.conference.registrations;

public class RegisterToConference {

    private String orderId;

    private String conferenceId;

    private int numberOfSeats;

    public RegisterToConference(String orderId, String conferenceId, int numberOfSeats) {
        this.orderId = orderId;
        this.conferenceId = conferenceId;
        this.numberOfSeats = numberOfSeats;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getConferenceId() {
        return conferenceId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterToConference that = (RegisterToConference) o;

        if (numberOfSeats != that.numberOfSeats) return false;
        if (!conferenceId.equals(that.conferenceId)) return false;
        if (!orderId.equals(that.orderId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + conferenceId.hashCode();
        result = 31 * result + numberOfSeats;
        return result;
    }

    @Override
    public String toString() {
        return "RegisterToConference{" +
                "orderId='" + orderId + '\'' +
                ", conferenceId='" + conferenceId + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }

    protected RegisterToConference() {
    }
}
