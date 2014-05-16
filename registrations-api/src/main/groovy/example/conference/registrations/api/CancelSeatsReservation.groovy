package example.conference.registrations.api

import example.conference.shared.Command

@Command
class CancelSeatsReservation {
    String conferenceId
    List<SeatQuantity> seats
    String orderId
}
