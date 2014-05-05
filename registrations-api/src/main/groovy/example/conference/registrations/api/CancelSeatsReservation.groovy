package example.conference.registrations.api

@ValueObject
class CancelSeatsReservation {
    String conferenceId
    String orderId
    int numberOfSeats
}
