package example.conference.registrations.api

import groovy.transform.Canonical

@Canonical
class OrderPlaced {
    String orderId
    String conferenceId
    int numberOfSeats
    int timeout
}
