package example.conference.registrations.impl.handlers;

import example.conference.management.api.ConferenceCreated;
import example.conference.management.api.SeatTypeCreated;
import example.conference.management.api.SeatTypeUpdated;
import example.conference.registrations.api.AddSeats;
import example.conference.registrations.api.CreateSeatsAvailability;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEventHandler {

    @Autowired
    private CommandGateway commandGateway;

    @EventHandler
    public void handle(ConferenceCreated event) {
        CreateSeatsAvailability command = new CreateSeatsAvailability(
                event.getConferenceId());

        commandGateway.send(command);
    }

    @EventHandler
    public void handle(SeatTypeCreated event) {
        AddSeats command = new AddSeats(
                event.getConferenceId(),
                event.getSeatTypeId(),
                event.getQuantity());

        commandGateway.send(command);
    }

    @EventHandler
    public void handle(SeatTypeUpdated event) {
        // TODO: hot to map event into AddSeats or RemoveSeats
    }

}
