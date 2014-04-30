package example.conference.registrations.api

import groovy.transform.Canonical


@Canonical
class SeatsReservationRejected {
    String orderId
    String rejectionReason
}
