package example.conference.management.api

import example.conference.shared.Event

@Event
class ConferenceUpdated {
    String conferenceId
    String name
    String location
    boolean published
}
