package example.conference.registrations.api

import example.conference.shared.Command

@Command
class AddSeats {
    String conferenceId
    String seatTypeId
    int quantity
}
