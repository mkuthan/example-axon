package example.conference.registrations;

import example.conference.payments.PaymentAccepted;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.domain.IdentifierFactory;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderSaga extends AbstractAnnotatedSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    private boolean seatsReserved = false;
    private boolean paymentAccepted = false;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreated event) {
        // make reservation
        String reservationId = IdentifierFactory.getInstance().generateIdentifier();
        associateWith("reservationId", reservationId);

        commandGateway.send(new MakeReservation(event.getOrderId(), reservationId));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(SeatsReserved event) {
        seatsReserved = true;

        if (paymentAccepted) {
            end();
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentAccepted event) {
        paymentAccepted = true;

        if (seatsReserved) {
            end();
        }
    }

}
