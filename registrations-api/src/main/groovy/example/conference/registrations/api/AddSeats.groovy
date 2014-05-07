package example.conference.registrations.api

@ValueObject
class AddSeats {
    String conferenceId
    String seatTypeId
    int quantity
}
