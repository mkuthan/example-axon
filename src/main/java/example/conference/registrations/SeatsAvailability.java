package example.conference.registrations;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import static java.util.Objects.requireNonNull;

public class SeatsAvailability extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String conferenceId;

    private int availableNumberOfSeats;

    public SeatsAvailability(String conferenceId, int availableNumberOfSeats) {
        requireNonNull(conferenceId);
        checkNumberOfSeats(availableNumberOfSeats);

        apply(new SeatsAvailabilityCreated(conferenceId, availableNumberOfSeats));
    }

    public void makeSeatsReservation(String orderId, int numberOfSeats) {
        requireNonNull(orderId);
        checkNumberOfSeats(numberOfSeats);

        if (numberOfSeats > availableNumberOfSeats) {
            apply(new SeatsReservationAccepted(orderId, numberOfSeats));
        } else {
            String rejectionReason = "Insufficient number of seats: '" + availableNumberOfSeats + "' but requested: '" + numberOfSeats + "'";
            apply(new SeatsReservationRejected(orderId, rejectionReason));
        }
    }


    public void cancelSeatsReservation(String orderId, int numberOfSeats) {
        requireNonNull(orderId);
        checkNumberOfSeats(numberOfSeats);

        apply(new SeatsReservationCancelled(orderId, numberOfSeats));
    }

    @EventSourcingHandler
    private void handle(SeatsAvailabilityCreated event) {
        this.conferenceId = event.getConferenceId();
        this.availableNumberOfSeats = event.getAvailableNumberOfSeats();
    }

    @EventSourcingHandler
    private void handle(SeatsReservationAccepted event) {
        this.availableNumberOfSeats -= event.getNumberOfSeats();
    }

    @EventSourcingHandler
    private void handle(SeatsReservationCancelled event) {
        this.availableNumberOfSeats += event.getNumberOfSeats();
    }

    private void checkNumberOfSeats(int numberOfSeats) {
        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Number of seats must be grater than 0, but was: '" + numberOfSeats + "'");
        }
    }

    // constructor required by framework
    protected SeatsAvailability() {
    }
}
