package example.conference.registrations.api

import example.conference.shared.Event

@Event
class OrderPlaced {
    String orderId
    String conferenceId
    List<SeatQuantity> seats
    int timeout
}
