package example.conference.payments;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import static java.util.Objects.requireNonNull;

public class Payment extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String paymentId;

    private String orderId;

    public Payment(String paymentId, String orderId) {
        requireNonNull(paymentId);
        requireNonNull(orderId);

        apply(new PaymentReceived(paymentId, orderId));
    }

    @EventSourcingHandler
    private void handle(PaymentReceived event) {
        this.paymentId = event.getPaymentId();
        this.orderId = event.getOrderId();
    }

    // constructor required by framework
    protected Payment() {
    }
}
