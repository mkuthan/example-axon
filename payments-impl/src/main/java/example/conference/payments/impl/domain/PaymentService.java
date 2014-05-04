package example.conference.payments.impl.domain;

import example.conference.payments.api.MakePayment;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    @Autowired
    private EventSourcingRepository<Payment> paymentRepository;

    @CommandHandler
    public void makePayment(MakePayment command) {
        Payment payment = new Payment(command.getPaymentId(), command.getOrderId());
        paymentRepository.add(payment);
    }

}
