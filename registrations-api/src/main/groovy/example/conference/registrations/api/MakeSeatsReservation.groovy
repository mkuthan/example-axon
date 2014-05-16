package example.conference.registrations.api

import example.conference.shared.Command

@Command
class MakeSeatsReservation {
    String conferenceId
    List<SeatQuantity> seats
    String orderId
}
