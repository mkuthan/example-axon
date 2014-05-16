package example.conference.registrations.api

import example.conference.shared.Event

@Event
class SeatsAvailabilityCreated {
    String conferenceId
}
