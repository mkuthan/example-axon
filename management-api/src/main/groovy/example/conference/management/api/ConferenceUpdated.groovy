package example.conference.management.api

@ValueObject
class ConferenceUpdated {
    String conferenceId
    String name
    String location
    List<SeatType> seatTypes
}
