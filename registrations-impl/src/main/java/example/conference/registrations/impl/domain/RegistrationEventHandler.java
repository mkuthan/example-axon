package example.conference.registrations.impl.domain;

import example.conference.management.api.ConferenceCreated;
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
        commandGateway.send(new CreateSeatsAvailability(event.getConferenceId(), event.getConferenceId(), event.getAvailableNumberOfSeats()));
    }

}
