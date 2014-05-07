package example.conference.payments.impl.handlers;

import example.conference.payments.api.MakePayment;
import example.conference.payments.impl.domain.Payment;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentHandler {

    @Autowired
    private EventSourcingRepository<Payment> repository;

    @CommandHandler
    public void handle(MakePayment command) {
        Payment payment = new Payment(command.getPaymentId(), command.getOrderId());
        repository.add(payment);
    }

}
