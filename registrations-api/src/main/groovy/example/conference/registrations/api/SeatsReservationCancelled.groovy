package example.conference.registrations.api

@ValueObject
class SeatsReservationCancelled {
    String orderId
    List<SeatQuantity> seats
}
