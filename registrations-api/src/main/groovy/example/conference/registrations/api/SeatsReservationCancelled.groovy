package example.conference.registrations.api

import groovy.transform.Canonical


@Canonical
class SeatsReservationCancelled {
    String orderId
    int numberOfSeats
}
