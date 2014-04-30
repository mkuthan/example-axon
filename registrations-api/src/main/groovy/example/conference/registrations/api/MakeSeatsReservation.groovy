package example.conference.registrations.api

import groovy.transform.Canonical


@Canonical
class MakeSeatsReservation {
    String conferenceId
    String orderId
    int numberOfSeats
}
