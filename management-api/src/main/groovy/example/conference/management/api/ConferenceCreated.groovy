package example.conference.management.api

import groovy.transform.Canonical

@Canonical
class ConferenceCreated {
    String conferenceId
    int availableNumberOfSeats
}
