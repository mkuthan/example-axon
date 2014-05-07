package example.conference.registrations.impl.domain;

import example.conference.payments.api.PaymentReceived;
import example.conference.registrations.api.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RegistrationSaga extends AbstractAnnotatedSaga {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationSaga.class);

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient EventScheduler eventScheduler;

    private String conferenceId;
    private List<SeatQuantity> seats;

    private boolean seatsReservationAccepted = false;
    private boolean paymentReceived = false;


    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderPlaced event) {
        logger.debug("A new order '{}' is placed.", event.getOrderId());

        this.conferenceId = event.getConferenceId();
        this.seats = event.getSeats();

        MakeSeatsReservation makeSeatsReservation = new MakeSeatsReservation(conferenceId, seats, event.getOrderId());
        commandGateway.send(makeSeatsReservation);

        RegistrationExpired orderExpired = new RegistrationExpired(event.getOrderId());
        eventScheduler.schedule(Duration.standardMinutes(event.getTimeout()), orderExpired);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(SeatsReservationAccepted event) {
        logger.debug("Seats reservation for order '{}' is accepted.", event.getOrderId());

        seatsReservationAccepted = true;

        if (paymentReceived) {
            confirmOrder(event.getOrderId());
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentReceived event) {
        logger.debug("Payment for order '{}' is received.", event.getOrderId());

        paymentReceived = true;

        if (seatsReservationAccepted) {
            confirmOrder(event.getOrderId());
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(SeatsReservationRejected event) {
        if (logger.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder()
                    .append("Seats reservation is rejected for order: ")
                    .append(event.getOrderId())
                    .append(". Rejection reasons:");

            for (String reason : event.getRejectionReasons()) {
                sb.append("\n\t").append(reason);
            }
            logger.debug(sb.toString());
        }

        rejectOrder(event.getOrderId());
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(RegistrationExpired event) {
        logger.debug("Order '{}' is expired.", event.getOrderId());

        if (seatsReservationAccepted) {
            CancelSeatsReservation cancelSeatsReservation = new CancelSeatsReservation(conferenceId, seats, event.getOrderId());
            commandGateway.send(cancelSeatsReservation);
        }

        if (paymentReceived) {
            // TODO: How to compensate payment?
        }

        rejectOrder(event.getOrderId());
    }

    private void confirmOrder(String orderId) {
        ConfirmOrder confirmOrder = new ConfirmOrder(orderId);
        commandGateway.send(confirmOrder);
        end();

    }

    private void rejectOrder(String orderId) {
        RejectOrder rejectOrder = new RejectOrder(orderId);
        commandGateway.send(rejectOrder);
        end();
    }

}
