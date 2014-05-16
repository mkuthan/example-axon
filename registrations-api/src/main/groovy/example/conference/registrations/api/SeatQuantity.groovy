package example.conference.registrations.api

import example.conference.shared.ValueObject

@ValueObject
class SeatQuantity {
    String seatTypeId
    int quantity
}
