package example.conference.registrations.api

import groovy.transform.Canonical


@Canonical
class SeatsReservationAccepted {
    String orderId
    int numberOfSeats
}
