package example.conference.registrations.domain;

import example.conference.payments.api.PaymentReceived;
import example.conference.registrations.api.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.EndSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderSaga extends AbstractAnnotatedSaga {

    private static final Logger logger = LoggerFactory.getLogger(OrderSaga.class);

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient EventScheduler eventScheduler;

    private String orderId;
    private String conferenceId;
    private int numberOfSeats;

    private boolean seatsReservationAccepted = false;
    private boolean paymentReceived = false;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderPlaced event) {
        this.orderId = event.getOrderId();
        this.conferenceId = event.getConferenceId();
        this.numberOfSeats = event.getNumberOfSeats();

        logger.debug("A new order with identifier '{}' is placed.", orderId);

        MakeSeatsReservation makeSeatsReservation = new MakeSeatsReservation(conferenceId, orderId, numberOfSeats);
        commandGateway.send(makeSeatsReservation);

        OrderExpired orderExpired = new OrderExpired(orderId);
        eventScheduler.schedule(Duration.standardMinutes(event.getTimeout()), orderExpired);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(SeatsReservationAccepted event) {
        logger.debug("Seats reservation is accepted for order '{}'.", orderId);

        seatsReservationAccepted = true;

        if (paymentReceived) {
            end();
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentReceived event) {
        logger.debug("Payment for order '{}' is received.", orderId);

        paymentReceived = true;

        if (seatsReservationAccepted) {
            end();
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderExpired event) {
        logger.debug("Order '{}' is expired.", orderId);

        if (seatsReservationAccepted) {
            commandGateway.send(new CancelSeatsReservation(conferenceId, orderId, numberOfSeats));
        }

        if (paymentReceived) {
            // How to compensate payment???
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(SeatsReservationRejected event) {
        logger.debug("Seats reservation is rejected for order {}, rejection reason is: '{}'.", orderId, event.getRejectionReason());
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(SeatsReservationCancelled event) {
        logger.debug("Seats reservation is cancelled for order {}.", orderId);
    }

}
