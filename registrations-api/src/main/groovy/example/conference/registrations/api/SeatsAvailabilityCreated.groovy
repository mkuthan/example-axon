package example.conference.registrations.api

@ValueObject
class SeatsAvailabilityCreated {
    String seatsAvailabilityId
    String conferenceId
    int availableNumberOfSeats
}