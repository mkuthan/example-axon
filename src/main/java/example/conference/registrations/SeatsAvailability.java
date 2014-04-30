package example.conference.registrations;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import static java.util.Objects.requireNonNull;

public class SeatsAvailability extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String conferenceId;

    private String orderId;

    public SeatsAvailability(String reservationId, String orderId) {
        requireNonNull(reservationId);
        requireNonNull(orderId);

        apply(new SeatsReserved(reservationId, orderId));
    }

    @EventSourcingHandler
    private void handle(SeatsReserved event) {
        this.reservationId = event.getReservationId();
        this.orderId = event.getOrderId();
    }

    // constructor required by framework
    protected SeatsAvailability() {
    }
}
