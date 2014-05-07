package example.conference.registrations.impl.handlers;

import example.conference.registrations.api.*;
import example.conference.registrations.impl.domain.SeatsAvailability;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatsAvailabilityHandler {

    @Autowired
    private Repository<SeatsAvailability> repository;

    @CommandHandler
    public void handle(CreateSeatsAvailability command) {
        SeatsAvailability seatsAvailability = new SeatsAvailability(command.getConferenceId());
        repository.add(seatsAvailability);
    }

    @CommandHandler
    public void handle(AddSeats command) {
        SeatsAvailability seatsAvailability = repository.load(command.getConferenceId());
        seatsAvailability.addSeats(command.getSeatTypeId(), command.getQuantity());
    }

    @CommandHandler
    public void handle(RemoveSeats command) {
        SeatsAvailability seatsAvailability = repository.load(command.getConferenceId());
        seatsAvailability.removeSeats(command.getSeatTypeId(), command.getQuantity());
    }

    @CommandHandler
    public void handle(MakeSeatsReservation command) {
        SeatsAvailability seatsAvailability = repository.load(command.getConferenceId());
        seatsAvailability.makeSeatsReservation(command.getSeats(), command.getOrderId());
    }

    @CommandHandler
    public void handle(CancelSeatsReservation command) {
        SeatsAvailability seatsAvailability = repository.load(command.getConferenceId());
        seatsAvailability.cancelSeatsReservation(command.getSeats(), command.getOrderId());
    }


}
