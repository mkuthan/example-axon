package example.conference.payments;

public class PaymentReceived {

    private String orderId;
    private String paymentId;

    public PaymentReceived(String orderId, String paymentId) {
        this.orderId = orderId;
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentReceived that = (PaymentReceived) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (paymentId != null ? !paymentId.equals(that.paymentId) : that.paymentId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (paymentId != null ? paymentId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MakePayment{" +
                "orderId='" + orderId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }

    protected PaymentReceived() {
    }
}
