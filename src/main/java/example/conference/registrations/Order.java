package example.conference.registrations;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import static java.util.Objects.requireNonNull;

public class Order extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String orderId;

    public Order(String orderId) {
        requireNonNull(orderId);

        apply(new OrderCreated(orderId));
    }

    @EventSourcingHandler
    private void handle(OrderCreated event) {
        this.orderId = event.getOrderId();
    }

    // constructor required by framework
    protected Order() {
    }
}
