package example.conference.management.api

@ValueObject
class SeatTypeUpdated {
    String seatTypeId
    String type
    int quantity
    BigDecimal priceAmount
    String priceCurrency
    String conferenceId;
}
