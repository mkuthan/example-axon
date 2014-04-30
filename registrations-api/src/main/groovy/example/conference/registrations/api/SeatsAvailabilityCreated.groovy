package example.conference.registrations.api

import groovy.transform.Canonical


@Canonical
class SeatsAvailabilityCreated {
    String seatsAvailabilityId
    String conferenceId
    int availableNumberOfSeats
}