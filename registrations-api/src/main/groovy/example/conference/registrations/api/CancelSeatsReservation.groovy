package example.conference.registrations.api

import groovy.transform.Canonical


@Canonical
class CancelSeatsReservation {
    String conferenceId
    String orderId
    int numberOfSeats
}
