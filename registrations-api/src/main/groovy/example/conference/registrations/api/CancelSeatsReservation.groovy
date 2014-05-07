package example.conference.registrations.api

@ValueObject
class CancelSeatsReservation {
    String conferenceId
    List<SeatQuantity> seats
    String orderId
}
