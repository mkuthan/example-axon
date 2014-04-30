package example.conference.registrations;

public class CancelSeatsReservation {

    private String conferenceId;
    private String orderId;
    private int numberOfSeats;

    public CancelSeatsReservation(String conferenceId, String orderId, int numberOfSeats) {
        this.conferenceId = conferenceId;
        this.orderId = orderId;
        this.numberOfSeats = numberOfSeats;
    }

    public String getConferenceId() {
        return conferenceId;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CancelSeatsReservation that = (CancelSeatsReservation) o;

        if (numberOfSeats != that.numberOfSeats) return false;
        if (!conferenceId.equals(that.conferenceId)) return false;
        if (!orderId.equals(that.orderId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = conferenceId.hashCode();
        result = 31 * result + orderId.hashCode();
        result = 31 * result + numberOfSeats;
        return result;
    }

    @Override
    public String toString() {
        return "CancelSeatsReservation{" +
                "conferenceId='" + conferenceId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }

    protected CancelSeatsReservation() {
    }
}
