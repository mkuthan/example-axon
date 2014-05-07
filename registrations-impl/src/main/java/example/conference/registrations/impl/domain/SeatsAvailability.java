package example.conference.registrations.impl.domain;

import example.conference.registrations.api.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class SeatsAvailability extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String conferenceId;

    private Map<String, Integer> availableSeats = new HashMap<>();

    public SeatsAvailability(String conferenceId) {
        requireNonNull(conferenceId);

        apply(new SeatsAvailabilityCreated(conferenceId));
    }

    public void addSeats(String seatTypeId, int quantity) {
        checkNumberOfSeats(quantity);

        apply(new SeatsAvailabilityChanged(conferenceId, seatTypeId, quantity));
    }

    public void removeSeats(String seatTypeId, int quantity) {
        checkNumberOfSeats(quantity);

        apply(new SeatsAvailabilityChanged(conferenceId, seatTypeId, -quantity));
    }

    public void makeSeatsReservation(List<SeatQuantity> seats, String orderId) {
        requireNonNull(seats);
        // TODO: checkNumberOfSeats(quantity);
        requireNonNull(orderId);

        List<String> rejectionReasons = new ArrayList<>();
        for (SeatQuantity seat : seats) {
            String seatTypeId = seat.getSeatTypeId();
            int quantity = seat.getQuantity();

            Integer availableNumberOfSeats = availableSeats.get(seatTypeId);
            if (quantity > availableNumberOfSeats) {
                String rejectionReason = "Insufficient number of seats: " + availableNumberOfSeats + " but requested: " + quantity + ".";
                rejectionReasons.add(rejectionReason);
            }
        }

        if (rejectionReasons.isEmpty()) {
            apply(new SeatsReservationAccepted(orderId, seats));
        } else {
            apply(new SeatsReservationRejected(orderId, seats, rejectionReasons));
        }
    }


    public void cancelSeatsReservation(List<SeatQuantity> seats, String orderId) {
        requireNonNull(seats);
        //TODO: checkNumberOfSeats(quantity);
        requireNonNull(orderId);

        apply(new SeatsReservationCancelled(orderId, seats));
    }

    @EventSourcingHandler
    private void handle(SeatsAvailabilityCreated event) {
        this.conferenceId = event.getConferenceId();
    }

    @EventSourcingHandler
    private void handle(SeatsAvailabilityChanged event) {
        String seatTypeId = event.getSeatTypeId();
        int quantity = event.getQuantity();

        availableSeats.compute(seatTypeId, (k, v) -> v == null ? quantity : v + quantity);
    }

    @EventSourcingHandler
    private void handle(SeatsReservationAccepted event) {
        for (SeatQuantity seat : event.getSeats()) {
            String seatTypeId = seat.getSeatTypeId();
            int quantity = seat.getQuantity();
            availableSeats.compute(seatTypeId, (k, v) -> v - quantity);
        }
    }

    @EventSourcingHandler
    private void handle(SeatsReservationCancelled event) {
        for (SeatQuantity seat : event.getSeats()) {
            String seatTypeId = seat.getSeatTypeId();
            int quantity = seat.getQuantity();
            availableSeats.compute(seatTypeId, (k, v) -> v + quantity);
        }
    }

    private void checkNumberOfSeats(int numberOfSeats) {
        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Number of seats must be grater than 0, but was: " + numberOfSeats + ".");
        }
    }

    protected SeatsAvailability() {
    }
}
