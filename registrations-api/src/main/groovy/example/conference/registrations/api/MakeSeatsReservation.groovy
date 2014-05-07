package example.conference.registrations.api

@ValueObject
class MakeSeatsReservation {
    String conferenceId
    List<SeatQuantity> seats
    String orderId
}
