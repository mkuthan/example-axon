package example.conference.registrations.api

@ValueObject
class OrderPlaced {
    String orderId
    String conferenceId
    int numberOfSeats
    int timeout
}
