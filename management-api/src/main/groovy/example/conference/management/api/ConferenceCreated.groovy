package example.conference.management.api

import example.conference.shared.Event

@Event
class ConferenceCreated {
    String conferenceId
    String name
    String location
}
