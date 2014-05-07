package example.conference.registrations.api

@ValueObject
class SeatsReservationAccepted {
    String orderId
    List<SeatQuantity> seats
}
