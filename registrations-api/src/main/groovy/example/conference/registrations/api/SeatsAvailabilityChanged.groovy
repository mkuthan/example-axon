package example.conference.registrations.api

import example.conference.shared.Event

@Event
class SeatsAvailabilityChanged {
    String conferenceId
    String seatTypeId
    int quantity
}
