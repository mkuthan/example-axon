package example.conference.registrations.api

@ValueObject
class SeatsReservationRejected {
    String orderId
    List<SeatQuantity> seats
    List<String> rejectionReasons
}
