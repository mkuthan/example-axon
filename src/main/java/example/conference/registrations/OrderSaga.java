package example.conference.registrations;

import example.conference.payments.PaymentReceived;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.EndSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class OrderSaga extends AbstractAnnotatedSaga {

    public static final Duration ORDER_EXPIRATION_TIMEOUT = Duration.standardMinutes(5);

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient EventScheduler eventScheduler;

    private String conferenceId;
    private int numberOfSeats;

    private boolean seatsReservationAccepted = false;
    private boolean paymentReceived = false;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderPlaced event) {
        this.conferenceId = event.getConferenceId();
        this.numberOfSeats = event.getNumberOfSeats();

        MakeSeatsReservation makeSeatsReservation = new MakeSeatsReservation(conferenceId, event.getOrderId(), numberOfSeats);
        commandGateway.send(makeSeatsReservation);

        OrderExpired orderExpired = new OrderExpired(event.getOrderId());
        eventScheduler.schedule(ORDER_EXPIRATION_TIMEOUT, orderExpired);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(SeatsReservationAccepted event) {
        seatsReservationAccepted = true;

        if (paymentReceived) {
            end();
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentReceived event) {
        paymentReceived = true;

        if (seatsReservationAccepted) {
            end();
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderExpired event) {
        commandGateway.send(new CancelSeatsReservation(conferenceId, event.getOrderId(), numberOfSeats));
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(SeatsReservationRejected event) {
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(SeatsReservationCancelled event) {
    }

}
