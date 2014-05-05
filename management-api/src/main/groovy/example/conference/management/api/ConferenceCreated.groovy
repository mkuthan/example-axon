package example.conference.management.api

@ValueObject
class ConferenceCreated {
    String conferenceId
    String name
    String location
    List<SeatType> seatTypes
}
