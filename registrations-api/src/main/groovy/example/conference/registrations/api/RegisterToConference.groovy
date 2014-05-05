package example.conference.registrations.api

@ValueObject
class RegisterToConference {
    String orderId
    String conferenceId
    int numberOfSeats
}
