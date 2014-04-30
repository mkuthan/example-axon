package example.conference.registrations.api

import groovy.transform.Canonical


@Canonical
class RegisterToConference {
    String orderId
    String conferenceId
    int numberOfSeats
}
