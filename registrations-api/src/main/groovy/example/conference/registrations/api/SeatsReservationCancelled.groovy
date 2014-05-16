package example.conference.registrations.api

import example.conference.shared.Event

@Event
class SeatsReservationCancelled {
    String orderId
    List<SeatQuantity> seats
}
