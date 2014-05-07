package example.conference.registrations.api

@ValueObject
class OrderPlaced {
    String orderId
    String conferenceId
    List<SeatQuantity> seats
    int timeout
}
