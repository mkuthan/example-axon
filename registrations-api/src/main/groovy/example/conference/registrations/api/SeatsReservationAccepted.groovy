package example.conference.registrations.api

import example.conference.shared.Event

@Event
class SeatsReservationAccepted {
    String orderId
    List<SeatQuantity> seats
}
