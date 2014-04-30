package example.conference.registrations;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

    @Autowired
    private EventSourcingRepository<Order> orderRepository;

    @Autowired
    private EventSourcingRepository<Reservation> reservationRepository;


    @CommandHandler
    public void placeOrder(PlaceOrder command) {
        Order order = new Order(command.getOrderId());
        orderRepository.add(order);
    }

    public void makeReservation(MakeReservation command) {
        Reservation reservation = new Reservation(command.getReservationId(), command.getOrderId());
        reservationRepository.add(reservation);
    }

}
