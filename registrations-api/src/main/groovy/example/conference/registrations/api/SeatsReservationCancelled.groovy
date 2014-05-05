package example.conference.registrations.api

@ValueObject
class SeatsReservationCancelled {
    String orderId
    int numberOfSeats
}
