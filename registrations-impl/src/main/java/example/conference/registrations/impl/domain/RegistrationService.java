package example.conference.registrations.impl.domain;

import example.conference.management.api.ConferenceCreated;
import example.conference.registrations.api.CancelSeatsReservation;
import example.conference.registrations.api.CreateSeatsAvailability;
import example.conference.registrations.api.MakeSeatsReservation;
import example.conference.registrations.api.RegisterToConference;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private EventSourcingRepository<Order> orderRepository;

    @Autowired
    private EventSourcingRepository<SeatsAvailability> seatsAvailabilityRepository;

    @CommandHandler
    public void registerToConference(RegisterToConference command) {
        Order order = new Order(command.getOrderId(), command.getConferenceId(), command.getNumberOfSeats());
        orderRepository.add(order);
    }

    @CommandHandler
    public void makeSeatsReservation(MakeSeatsReservation command) {
        SeatsAvailability seatsAvailability = seatsAvailabilityRepository.load(command.getConferenceId());
        seatsAvailability.makeSeatsReservation(command.getOrderId(), command.getNumberOfSeats());
    }

    @CommandHandler
    public void cancelSeatsReservation(CancelSeatsReservation command) {
        SeatsAvailability seatsAvailability = seatsAvailabilityRepository.load(command.getConferenceId());
        seatsAvailability.cancelSeatsReservation(command.getOrderId(), command.getNumberOfSeats());
    }

    @CommandHandler
    public void createSeatsAvailability(CreateSeatsAvailability command) {
        SeatsAvailability seatsAvailability = new SeatsAvailability(command.getSeatsAvailabilityId(), command.getConferenceId(), command.getAvailableNumberOfSeats());
        seatsAvailabilityRepository.add(seatsAvailability);
    }

    @EventHandler
    public void handle(ConferenceCreated event) {
        commandGateway.send(new CreateSeatsAvailability(event.getConferenceId(), event.getConferenceId(), event.getAvailableNumberOfSeats()));
    }

}
