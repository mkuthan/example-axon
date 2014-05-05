package example.conference.registrations.api

@ValueObject
class CreateSeatsAvailability {
    String seatsAvailabilityId
    String conferenceId
    int availableNumberOfSeats
}
