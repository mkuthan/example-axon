package example.conference.registrations;

import example.conference.management.ConferenceCreated;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    @Qualifier("orderRepository")
    private EventSourcingRepository<Order> orderRepository;

    @Autowired
    @Qualifier("seatsAvailabilityRepository")
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
        SeatsAvailability seatsAvailability = new SeatsAvailability(command.getConferenceId(), command.getAvailableNumberOfSeats());
        seatsAvailabilityRepository.add(seatsAvailability);
    }

    @EventHandler
    public void handle(ConferenceCreated event) {
        commandGateway.send(new CreateSeatsAvailability(event.getConferenceId(), event.getAvailableNumberOfSeats()));
    }

}
