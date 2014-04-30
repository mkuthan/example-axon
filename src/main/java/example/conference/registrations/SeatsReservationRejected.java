package example.conference.registrations;

public class SeatsReservationRejected {

    private String orderId;

    public SeatsReservationRejected(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeatsReservationRejected that = (SeatsReservationRejected) o;

        if (!orderId.equals(that.orderId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return orderId.hashCode();
    }

    @Override
    public String toString() {
        return "SeatsReservationAccepted{" +
                "orderId='" + orderId + '\'' +
                '}';
    }

    protected SeatsReservationRejected() {
    }
}
