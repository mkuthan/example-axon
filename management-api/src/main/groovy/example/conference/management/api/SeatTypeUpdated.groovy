package example.conference.management.api

import example.conference.shared.Event
import example.conference.shared.Money

@Event
class SeatTypeUpdated {
    String seatTypeId
    String type
    int quantity
    Money price
    String conferenceId;
}
