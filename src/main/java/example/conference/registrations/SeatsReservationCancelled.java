package example.conference.registrations;

public class SeatsReservationCancelled {

    private String orderId;
    private int numberOfSeats;

    public SeatsReservationCancelled(String orderId, int numberOfSeats) {
        this.orderId = orderId;
        this.numberOfSeats = numberOfSeats;
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

        SeatsReservationCancelled that = (SeatsReservationCancelled) o;

        if (numberOfSeats != that.numberOfSeats) return false;
        if (!orderId.equals(that.orderId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + numberOfSeats;
        return result;
    }

    @Override
    public String toString() {
        return "SeatsReservationCancelled{" +
                "orderId='" + orderId + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }


    protected SeatsReservationCancelled() {
    }
}
