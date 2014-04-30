package example.conference.registrations.api

import groovy.transform.Canonical


@Canonical
class CreateSeatsAvailability {
    String seatsAvailabilityId
    String conferenceId
    int availableNumberOfSeats
}
