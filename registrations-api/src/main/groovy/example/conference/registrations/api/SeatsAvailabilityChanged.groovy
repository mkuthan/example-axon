package example.conference.registrations.api

@ValueObject
class SeatsAvailabilityChanged {
    String conferenceId
    String seatTypeId
    int quantity
}
