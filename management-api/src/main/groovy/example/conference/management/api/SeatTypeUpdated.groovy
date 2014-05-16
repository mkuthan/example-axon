package example.conference.management.api

import example.conference.shared.Event

@Event
class SeatTypeUpdated {
    String seatTypeId
    String type
    int quantity
    BigDecimal priceAmount
    String priceCurrency
    String conferenceId;
}
