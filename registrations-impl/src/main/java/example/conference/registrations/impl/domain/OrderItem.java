package example.conference.registrations.impl.domain;

import example.conference.registrations.api.SeatQuantity;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class OrderItem {

    private String seatType;
    private int quantity;

    public OrderItem(String seatType, int quantity) {
        this.seatType = Objects.requireNonNull(seatType);
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero, but was" + quantity + ".");
        }

        this.quantity = quantity;
    }

    public static OrderItem of(SeatQuantity seat) {
        return new OrderItem(seat.getSeatTypeId(), seat.getQuantity());
    }

    public static List<OrderItem> of(List<SeatQuantity> seats) {
        return seats.stream().map(OrderItem::of).collect(toList());
    }

    public SeatQuantity toSeatQuantity() {
        return new SeatQuantity(seatType, quantity);
    }

}

