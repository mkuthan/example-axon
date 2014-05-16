package example.conference.registrations.api

import example.conference.shared.Event

@Event
class SeatsReservationRejected {
    String orderId
    List<SeatQuantity> seats
    List<String> rejectionReasons
}
