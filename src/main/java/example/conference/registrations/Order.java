package example.conference.registrations;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import static java.util.Objects.requireNonNull;

public class Order extends AbstractAnnotatedAggregateRoot<String> {

    public enum Status {PLACED, REJECTED, CONFIRMED};

    @AggregateIdentifier
    private String orderId;

    private String conferenceId;

    private int numberOfSeats;

    private Status status;

    public Order(String orderId, String conferenceId, int numberOfSeats) {
        requireNonNull(orderId);
        requireNonNull(conferenceId);

        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Number of seats must be grater than 0, but was: '" + numberOfSeats + "'");
        }

        apply(new OrderPlaced(orderId, conferenceId, numberOfSeats));
    }

    @EventSourcingHandler
    private void handle(OrderPlaced event) {
        this.orderId = event.getOrderId();
        this.conferenceId = event.getConferenceId();
        this.numberOfSeats = event.getNumberOfSeats();
        this.status = Status.PLACED;
    }

    // constructor required by framework
    protected Order() {
    }
}
