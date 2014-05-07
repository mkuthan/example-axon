package example.conference.registrations.api

@ValueObject
class RegisterToConference {
    String orderId
    String conferenceId
    List<SeatQuantity> seats
}
