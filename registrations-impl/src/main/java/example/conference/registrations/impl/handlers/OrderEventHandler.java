package example.conference.registrations.impl.handlers;

import example.conference.registrations.api.RegisterToConference;
import example.conference.registrations.impl.domain.Order;
import example.conference.registrations.impl.domain.OrderItem;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    @Autowired
    private Repository<Order> orderRepository;

    @CommandHandler
    public void registerToConference(RegisterToConference command) {
        Order order = new Order(
                command.getOrderId(),
                command.getConferenceId(),
                OrderItem.of(command.getSeats()));

        orderRepository.add(order);
    }




}
