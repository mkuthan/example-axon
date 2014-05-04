package example.conference.management.api

import groovy.transform.Canonical

@Canonical
class ConferenceUpdated {
    String conferenceId
    int availableNumberOfSeats
}
