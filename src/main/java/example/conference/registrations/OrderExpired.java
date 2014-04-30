package example.conference.registrations;

public class OrderExpired {

    private String orderId;

    public OrderExpired(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderExpired that = (OrderExpired) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return orderId != null ? orderId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "OrderExpired{" +
                "orderId='" + orderId + '\'' +
                '}';
    }

    protected OrderExpired() {

    }
}
