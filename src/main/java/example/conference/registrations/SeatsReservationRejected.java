package example.conference.registrations;

public class SeatsReservationRejected {

    private String orderId;

    private String rejectionReason;

    public SeatsReservationRejected(String orderId, String rejectionReason) {
        this.orderId = orderId;
        this.rejectionReason = rejectionReason;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeatsReservationRejected that = (SeatsReservationRejected) o;

        if (!orderId.equals(that.orderId)) return false;
        if (!rejectionReason.equals(that.rejectionReason)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + rejectionReason.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SeatsReservationRejected{" +
                "orderId='" + orderId + '\'' +
                ", rejectionReason='" + rejectionReason + '\'' +
                '}';
    }

    protected SeatsReservationRejected() {
    }
}
