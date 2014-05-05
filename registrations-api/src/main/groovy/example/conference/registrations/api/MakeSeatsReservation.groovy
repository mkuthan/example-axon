package example.conference.registrations.api

@ValueObject
class MakeSeatsReservation {
    String conferenceId
    String orderId
    int numberOfSeats
}
