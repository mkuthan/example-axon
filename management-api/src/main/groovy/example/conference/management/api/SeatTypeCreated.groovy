package example.conference.management.api

@ValueObject
class SeatTypeCreated {
    String seatTypeId
    String type
    int quantity
    BigDecimal priceAmount
    String priceCurrency
    String conferenceId;
}
