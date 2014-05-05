package example.conference.registrations.api

@ValueObject
class SeatsReservationRejected {
    String orderId
    String rejectionReason
}
