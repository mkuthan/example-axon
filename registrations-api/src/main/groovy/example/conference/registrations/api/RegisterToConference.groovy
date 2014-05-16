package example.conference.registrations.api

import example.conference.shared.Command

@Command
class RegisterToConference {
    String orderId
    String conferenceId
    List<SeatQuantity> seats
}
