package example.conference.registrations;

public class MakeSeatsReservation {

    private String reservationId;
    private String orderId;
    private String conferenceId;
    private int numberOfSeats;

    public MakeSeatsReservation(String reservationId, String orderId) {
        this.reservationId = reservationId;
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getReservationId() {
        return reservationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MakeSeatsReservation that = (MakeSeatsReservation) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (reservationId != null ? !reservationId.equals(that.reservationId) : that.reservationId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (reservationId != null ? reservationId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MakeSeatReservation{" +
                "orderId='" + orderId + '\'' +
                ", reservationId='" + reservationId + '\'' +
                '}';
    }

    protected MakeSeatsReservation() {
    }
}
