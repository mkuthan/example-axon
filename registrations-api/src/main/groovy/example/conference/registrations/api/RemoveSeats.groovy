package example.conference.registrations.api

@ValueObject
class RemoveSeats {
    String conferenceId
    String seatTypeId
    int quantity
}
