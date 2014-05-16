package example.conference.registrations.api

import example.conference.shared.Command

@Command
class RemoveSeats {
    String conferenceId
    String seatTypeId
    int quantity
}
