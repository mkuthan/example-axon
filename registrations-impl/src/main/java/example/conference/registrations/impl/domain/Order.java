package example.conference.registrations.impl.domain;

import example.conference.registrations.api.OrderPlaced;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import static java.util.Objects.requireNonNull;

public class Order extends AbstractAnnotatedAggregateRoot<String> {

    private static final int DEFAULT_TIMEOUT = 3;

    @AggregateIdentifier
    private String orderId;

    private String conferenceId;

    private int numberOfSeats;

    public Order(String orderId, String conferenceId, int numberOfSeats) {
        requireNonNull(orderId);
        requireNonNull(conferenceId);

        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Number of seats must be grater than 0, but was: '" + numberOfSeats + "'");
        }

        apply(new OrderPlaced(orderId, conferenceId, numberOfSeats, DEFAULT_TIMEOUT));
    }

    @EventSourcingHandler
    private void handle(OrderPlaced event) {
        this.orderId = event.getOrderId();
        this.conferenceId = event.getConferenceId();
        this.numberOfSeats = event.getNumberOfSeats();
    }

    protected Order() {
    }
}
